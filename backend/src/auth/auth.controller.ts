import { Controller, Post, Body, UnauthorizedException, HttpCode, HttpStatus } from '@nestjs/common';
import { AuthService } from './auth.service';
import { ApiTags, ApiOperation, ApiResponse } from '@nestjs/swagger';

@ApiTags('auth')
@Controller('auth/v1')
export class AuthController {
  constructor(private authService: AuthService) {}

  @Post('token')
  @HttpCode(HttpStatus.OK)
  @ApiOperation({ summary: 'Inicia sesion (Login)' })
  @ApiResponse({ status: 200, description: 'Sesión iniciada' })
  async login(@Body() body: any) {
    // Para simplificar, aceptamos un objeto any (idealmente sería un DTO)
    const user = await this.authService.validateUser(body.email, body.password);
    if (!user) throw new UnauthorizedException();
    return this.authService.login(user);
  }

  @Post('signup')
  @ApiOperation({ summary: 'Registra un usuario nuevo' })
  @ApiResponse({ status: 201, description: 'Usuario creado' })
  async signup(@Body() body: any) {
    return this.authService.signup(body);
  }
}
