import { Controller, Get, Post, Delete, Body, UseGuards, Request } from '@nestjs/common';
import { CartService } from './cart.service';
import { AuthGuard } from '@nestjs/passport';
import { ApiTags, ApiOperation, ApiBearerAuth, ApiResponse } from '@nestjs/swagger';

@ApiTags('cart')
@ApiBearerAuth()
@UseGuards(AuthGuard('jwt'))
@Controller('rest/v1/cart')
export class CartController {
  constructor(private readonly cartService: CartService) {}

  @Get()
  @ApiOperation({ summary: 'Obtener el carrito del usuario' })
  async getCart(@Request() req: any) {
    return this.cartService.getCart(req.user.userId);
  }

  @Post('items')
  @ApiOperation({ summary: 'Agregar o actualizar un producto en el carrito' })
  async addToCart(@Request() req: any, @Body() body: { productId: number; quantity: number }) {
    return this.cartService.addToCart(req.user.userId, body.productId, body.quantity || 1);
  }

  @Delete()
  @ApiOperation({ summary: 'Vaciar el carrito completo' })
  async clearCart(@Request() req: any) {
    return this.cartService.clearCart(req.user.userId);
  }
}
