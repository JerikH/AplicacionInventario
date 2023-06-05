public class Vistas {
  // public static void MenuPrincipal() {
  //     System.out.println("Menú principal:");
  //     System.out.println("1. Agregar usuario");
  //     System.out.println("2. Quitar usuario");
  //     System.out.println("3. Modificar usuario");
  //     System.out.println("4. Buscar Usuario.");
  //     System.out.println("0. Salir");
  //     System.out.print("Seleccione una opción: ");
  // }

  public static void InfoUsuario(User usuario) {
      System.out.println("Usuario encontrado:");
      System.out.println("Nombre: " + usuario.getNombre());
      System.out.println("Correo: " + usuario.getCorreo());
      System.out.println("Nivel de acceso: " + usuario.getNivel_acceso());
  }

  public static void InfoProducto(Product producto) {
        System.out.println("Producto encontrado:");
        System.out.println("Nombre: " + producto.getNombre());
        System.out.println("Descripcion: " + producto.getDescripcion());
        System.out.println("Precio unitario: " + producto.getPrecio());
    }

  public static void MenuEmpleado() {
    System.out.println("Vista de Empleado");
    System.out.println("1. Realizar venta");
    System.out.println("2. Consultar producto");
    System.out.println("3. Recibir devolución");
    System.out.println("4. Recibir pedido.");
    System.out.println("5. Cerrar Sesión");
    System.out.println("0. Apagar Sistema");
    System.out.print("Seleccione una opción: ");
  }
  
  public static void MenuAdministrador() {
    System.out.println("Vista de Administrador");
    System.out.println("1. Realizar venta");
    System.out.println("2. Gestionar Productos");
    System.out.println("3. Gestionar Empleados");
    System.out.println("4. Generar Reportes");
    System.out.println("5. Buscar Producto");
    System.out.println("6. Buscar Transacción");
    System.out.println("7. Administrar Inventarios");
    System.out.println("8. Recibir Pedido");
    System.out.println("9. Cerrar Sesión");
    System.out.println("0. Apagar Sistema");
    System.out.print("Seleccione una opción: ");
  }
  public static void ModuloVenta() {
      System.out.println("Modulo De Venta");
      System.out.println("1. Agregar Producto");
      System.out.println("2. Quitar Producto");
      System.out.println("3. Calcular Total");
      System.out.println("4. Mostrar Carrito");
      System.out.println("5. Finalizar Venta");
      System.out.println("6. Cancelar Venta");
      System.out.println("7. Regresar");
    }
  public static void ModuloAdministrarInventarios() {
      System.out.println("Modulo De Visualizacion De Inventario");
      System.out.println("1. Ver inventarios");
      System.out.println("2. Mover productos");
      System.out.println("3. Regresar");
    }
  
  public static void ModuloGestionProductos() {
    System.out.println("Modulo De Gestion De Productos");
    System.out.println("1. Agregar Producto");
    System.out.println("2. Eliminar producto");
    System.out.println("3. Modificar producto"); //sub menu desde esta opcion, métodos de producto y manipulacion de unidades.
    System.out.println("4. Regresar");
  }
  
  public static void ModuloGestionEmpleados() {
    System.out.println("Modulo De Gestion De Empleados");
    System.out.println("1. Agregar Empleado");
    System.out.println("2. Eliminar Empleado");
    System.out.println("3. Modificar Empleado");
    System.out.println("4. Buscar Usuario");
    System.out.println("5. Regresar");
  }

  public static void ModuloGenerarReportes() {
    System.out.println("Modulo De Reportes");
    System.out.println("1. Reporte total de ventas");
    System.out.println("2. Reporte de ventas por empleado");
    System.out.println("3. Regresar");
  }

  public static void ModuloRecibirPedido() {
    System.out.println("Modulo De Recibir Pedido");
    System.out.println("1. Agregar Producto");
    System.out.println("2. Eliminar producto");
    System.out.println("3. Finalizar");
    System.out.println("4. Salir");
  }

  public static void ModuloBuscarTransacciones() {
    System.out.println("Modulo Busqueda de transacciones");
    System.out.println("1. Buscar una venta");
    System.out.println("2. Buscar un pedido");
    System.out.println("2. Buscar una devolución");
    System.out.println("3. Regresar");
  }

  public static void ModuloBuscarVentas() {
    System.out.println("Modulo Busqueda de Ventas");
    System.out.println("1. Buscar por Id");
    System.out.println("2. Buscar por empleado");
    System.out.println("3. Buscar por fecha");
    System.out.println("4. Regresar");
  }

  public static void ModuloBuscarPedidos() {
    System.out.println("Modulo Busqueda de Pedidos");
    System.out.println("1. Buscar por Id");
    System.out.println("2. Buscar por empleado");
    System.out.println("2. Buscar por fecha");
    System.out.println("3. Regresar");
  }

  public static void ModuloBuscarDevoluciones() {
    System.out.println("Modulo Busqueda de Devoluciones");
    System.out.println("1. Buscar por Id");
    System.out.println("2. Buscar por empleado");
    System.out.println("2. Buscar por fecha");
    System.out.println("3. Regresar");
  }

  public static void ModuloMoverInventarios(){
    System.out.println("Modulo Administración de Inventarios");
    System.out.println("1. Mover producto de bodega a exhibición.");
    System.out.println("2. Mover de exhibición a bodega.");
    System.out.println("3. Mover de devolución a bodega.");
    System.out.println("4. Regresar.");
  }

  public static void ModuloVerInventarios(){
    System.out.println("Modulo Ver Inventarios");
    System.out.println("1. Ver inventario completo.");
    System.out.println("2. Ver inventario bodega.");
    System.out.println("3. Ver inventario exhibicion.");
    System.out.println("4. Ver lista de devoluciones.");
    System.out.println("5. Regresar.");
  }
}
