import { Controller, Get, Post, UseGuards, Request } from '@nestjs/common';
import { OrdersService } from './orders.service';
import { AuthGuard } from '@nestjs/passport';
import { ApiTags, ApiOperation, ApiBearerAuth } from '@nestjs/swagger';

@ApiTags('orders')
@ApiBearerAuth()
@UseGuards(AuthGuard('jwt'))
@Controller('rest/v1/orders')
export class OrdersController {
  constructor(private readonly ordersService: OrdersService) {}

  @Get()
  @ApiOperation({ summary: 'Obtener historial de pedidos del usuario' })
  async getOrders(@Request() req: any) {
    return this.ordersService.getOrders(req.user.userId);
  }

  @Post()
  @ApiOperation({ summary: 'Confirmar pedido a partir del carrito actual' })
  async createOrder(@Request() req: any) {
    return this.ordersService.createOrder(req.user.userId);
  }
}
