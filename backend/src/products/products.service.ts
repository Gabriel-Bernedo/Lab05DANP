import { Injectable, OnModuleInit } from '@nestjs/common';
import { PrismaService } from '../prisma.service';

@Injectable()
export class ProductsService implements OnModuleInit {
  constructor(private prisma: PrismaService) {}

  async onModuleInit() {
    // El seeding ahora se maneja mediante 'npx prisma db seed'
  }

  async findAll() {
    return this.prisma.product.findMany();
  }
}
