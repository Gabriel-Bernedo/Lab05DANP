import { OnModuleInit } from '@nestjs/common';
import { PrismaService } from '../prisma.service';
export declare class ProductsService implements OnModuleInit {
    private prisma;
    constructor(prisma: PrismaService);
    onModuleInit(): Promise<void>;
    findAll(): Promise<{
        id: number;
        description: string;
        title: string;
        price: number;
        category: string;
        image: string;
    }[]>;
}
