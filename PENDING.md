# Consideraciones Pendientes (Supabase Backend)

Este documento registra los compromisos y pendientes restantes tras migrar la aplicación de FakeStore API a **Supabase**.

## 1. Gestión de Modelos y Mapeo de Datos (DTOs)
- **Situación actual:** Al migrar a Supabase, se mantiene temporalmente la estructura de datos compatible con FakeStore (ej. mapear `title` a `name` localmente).
- **Ajuste futuro:** 
  - Renombrar las columnas en Supabase de `title` a `name` para eliminar la necesidad de adaptaciones locales en la app y simplificar los modelos de UI.
  - Implementar lógica de precios originales y descuentos que actualmente la UI soporta pero la base de datos no.

## 2. Autenticación y Seguridad
- ✅ **Completado:** Se migró exitosamente la autenticación desde FakeStore a **Supabase Auth**, resolviendo el problema del uso de `username` por `email` y permitiendo un registro real.
- **Ajuste futuro:**
  - Persistir el Token de sesión a nivel de dispositivo (DataStore / SharedPreferences) para evitar que el usuario tenga que iniciar sesión cada vez que abre la app.

## 3. Órdenes y Carrito de Compras
- **Situación actual:** La lógica del Carrito se mantiene localmente en memoria dentro de la app Android.
- **Ajuste futuro:**
  - Crear tablas `orders` y `order_items` en Supabase.
  - Implementar endpoints en Retrofit (`/rest/v1/orders`) para sincronizar el carrito de compras y realizar el checkout.

## 4. Estructura de Rutas (Endpoints)
- ✅ **Completado:** Se actualizaron las rutas para apuntar a la API REST y GoTrue de Supabase.
