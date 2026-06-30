import { Controller, Get, UseGuards } from '@nestjs/common';
import { ProductsService } from './products.service';
import { ApiTags, ApiOperation, ApiResponse, ApiBearerAuth } from '@nestjs/swagger';
import { AuthGuard } from '@nestjs/passport';

@ApiTags('products')
@Controller('rest/v1/products')
export class ProductsController {
  constructor(private readonly productsService: ProductsService) {}

  @Get()
  @UseGuards(AuthGuard('jwt'))
  @ApiBearerAuth()
  @ApiOperation({ summary: 'Obtiene todos los productos' })
  @ApiResponse({ status: 200, description: 'Lista de productos.' })
  async findAll() {
    // La app Android envía ?select=* pero lo ignoraremos y devolveremos todo
    return this.productsService.findAll();
  }
}
