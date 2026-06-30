import { PrismaClient } from '@prisma/client';
// @ts-ignore
import * as bcrypt from 'bcrypt';
// @ts-ignore
import { Pool } from 'pg';
import { PrismaPg } from '@prisma/adapter-pg';

const connectionString = process.env.DATABASE_URL || 'postgresql://postgres:password123@localhost:5432/lab05db?schema=public';
const pool = new Pool({ connectionString });
const adapter = new PrismaPg(pool);
const prisma = new PrismaClient({ adapter });

async function main() {
  // 1. Evitar reescribir la BD en cada reinicio del contenedor
  const existingUsers = await prisma.user.count();
  if (existingUsers > 0) {
    console.log('La base de datos ya contiene datos. Seeding omitido.');
    return;
  }

  // 2. Crear 3 Usuarios
  const passwordHash = await bcrypt.hash('password123', 10);
  
  const users = await Promise.all([
    prisma.user.create({
      data: {
        email: 'admin@lab05.com',
        password: passwordHash,
        firstName: 'Admin',
        lastName: 'System',
        phone: '123456789',
        cart: { create: {} }, // Crea su carrito automáticamente
      },
    }),
    prisma.user.create({
      data: {
        email: 'user1@lab05.com',
        password: passwordHash,
        firstName: 'John',
        lastName: 'Doe',
        phone: '987654321',
        cart: { create: {} },
      },
    }),
    prisma.user.create({
      data: {
        email: 'user2@lab05.com',
        password: passwordHash,
        firstName: 'Jane',
        lastName: 'Smith',
        phone: '555123456',
        cart: { create: {} },
      },
    }),
  ]);

  // 3. Crear 20 Productos
  const categories = ["Electronicos", "Sensores", "Actuadores", "Motores"];
  const productsData = Array.from({ length: 20 }).map((_, i) => ({
    title: `Componente Modelo X-${i + 1}`,
    price: parseFloat((Math.random() * 100 + 10).toFixed(2)),
    description: `Microcomponente de alta precisión para proyectos de electrónica. Serie número ${i + 1}.`,
    category: categories[i % categories.length],
    image: ""
  }));

  await prisma.product.createMany({
    data: productsData,
  });

  const allProducts = await prisma.product.findMany();

  // 4. Llenar los carritos con productos aleatorios
  for (const user of users) {
    const userCart = await prisma.cart.findUnique({ where: { userId: user.id } });
    if (userCart) {
      // Asignar entre 1 y 3 productos distintos al carrito
      const numItems = Math.floor(Math.random() * 3) + 1;
      
      for (let i = 0; i < numItems; i++) {
        const randomProduct = allProducts[Math.floor(Math.random() * allProducts.length)];
        
        // Upsert para evitar errores de restricción única si sale el mismo producto
        await prisma.cartItem.upsert({
          where: {
            cartId_productId: {
              cartId: userCart.id,
              productId: randomProduct.id
            }
          },
          update: {
            quantity: { increment: 1 }
          },
          create: {
            cartId: userCart.id,
            productId: randomProduct.id,
            quantity: 1
          }
        });
      }
    }
  }

  console.log('Seed completado exitosamente: 3 usuarios y 20 productos agregados.');
}

main()
  .catch((e) => {
    console.error(e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
