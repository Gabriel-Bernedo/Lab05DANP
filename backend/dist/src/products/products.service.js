"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProductsService = void 0;
const common_1 = require("@nestjs/common");
const prisma_service_1 = require("../prisma.service");
let ProductsService = class ProductsService {
    prisma;
    constructor(prisma) {
        this.prisma = prisma;
    }
    async onModuleInit() {
        const count = await this.prisma.product.count();
        if (count === 0) {
            await this.prisma.product.createMany({
                data: [
                    {
                        title: 'Fjallraven - Foldsack No. 1 Backpack',
                        price: 109.95,
                        description: 'Your perfect pack for everyday use and walks in the forest.',
                        category: 'men\'s clothing',
                        image: 'https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg'
                    },
                    {
                        title: 'Mens Casual Premium Slim Fit T-Shirts',
                        price: 22.3,
                        description: 'Slim-fitting style, contrast raglan sleeve, three-button henley placket.',
                        category: 'men\'s clothing',
                        image: 'https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg'
                    }
                ]
            });
        }
    }
    async findAll() {
        return this.prisma.product.findMany();
    }
};
exports.ProductsService = ProductsService;
exports.ProductsService = ProductsService = __decorate([
    (0, common_1.Injectable)(),
    __metadata("design:paramtypes", [prisma_service_1.PrismaService])
], ProductsService);
//# sourceMappingURL=products.service.js.map