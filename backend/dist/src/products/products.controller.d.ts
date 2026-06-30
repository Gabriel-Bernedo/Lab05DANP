import { ProductsService } from './products.service';
export declare class ProductsController {
    private readonly productsService;
    constructor(productsService: ProductsService);
    findAll(): Promise<{
        id: number;
        description: string;
        title: string;
        price: number;
        category: string;
        image: string;
    }[]>;
}
