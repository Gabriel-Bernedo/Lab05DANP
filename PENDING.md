# Consideraciones Pendientes (FakeStore API vs Backend Propio)

Este documento registra las limitaciones temporales y los compromisos (trade-offs) asumidos al integrar la aplicación con **FakeStore API**. 

Cuando se reemplace FakeStore por un **Backend Propio**, se deben abordar los siguientes puntos para recuperar la funcionalidad completa de la aplicación:

## 1. Gestión de Modelos y Mapeo de Datos (DTOs)
- **Situación actual:** La API de FakeStore envía objetos anidados (ej. `name: { firstname, lastname }` o `address: { city, street... }`). Para evitar romper la interfaz de usuario, se usaron DTOs (Data Transfer Objects) para mapear estas respuestas a los modelos simples de la UI. En el caso de `Product`, se modificó el modelo directamente adaptando `title` por `name`.
- **Ajuste futuro (Backend Propio):** El backend propio debería enviar los datos en la estructura plana que requiere la app nativamente:
  - En `User`: enviar `name` (string completo), `address` (string o estructura acordada).
  - En `Product`: enviar `name` en lugar de `title`, y restablecer la lógica de `originalPrice` y `discountPrice` (FakeStore no provee descuentos).

## 2. Autenticación y Seguridad
- **Situación actual:** FakeStore usa `username` en lugar de `email` para iniciar sesión. Al registrarse vía `/users`, no se guarda un estado persistente real que permita hacer un login posterior.
- **Ajuste futuro (Backend Propio):** 
  - Restablecer el login usando `email` y `password`.
  - Implementar un flujo real de registro y persistencia de tokens de sesión/JWT robustos.

## 3. Órdenes y Carrito de Compras
- **Situación actual:** FakeStore no maneja el estado de las órdenes (`PENDIENTE`, `ENTREGADO`), ni envía los objetos `Product` embebidos dentro de las respuestas de carritos (solo el `productId`), ni calcula el precio total (`totalAmount`). Por lo tanto, toda la lógica del Carrito se tuvo que mantener **localmente en memoria** (o con persistencia local) dentro de la app Android, omitiendo la sincronización real con FakeStore.
- **Ajuste futuro (Backend Propio):**
  - Implementar endpoints para sincronizar el carrito de compras del usuario.
  - Implementar el checkout que convierta un Carrito en una Orden.
  - Asegurar que el endpoint de obtener órdenes envíe el `status`, el `totalAmount` y la lista completa de ítems con detalles de productos.

## 4. Estructura de Rutas (Endpoints)
- **Situación actual:** Se consume directo de las rutas base (`/products`, `/users`, `/auth/login`).
- **Ajuste futuro (Backend Propio):** Se recomienda volver a agrupar los servicios bajo un prefijo común como `/api/`, restableciendo endpoints estructurados (ej. `/api/auth/login`, `/api/products`, `/api/orders`).
