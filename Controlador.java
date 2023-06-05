import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.function.Consumer;

public class Controlador {
  private Vistas vistas;
  private Utilidades utilidades;
  private Inventario_General general; // utilizar exhibición y bodega en su lugar
  private Inventario_Vendido vendidos;
  private Inventario_Recibido recibidos;
  private Inventario_Bodega bodega;
  private Inventario_Exhibicion exhibicion;

  Scanner scanner = new Scanner(System.in);

  public Controlador(Vistas vistas, Utilidades utilidades, Inventario_General general, Inventario_Vendido vendidos,
      Inventario_Recibido recibidos, Inventario_Bodega bodega, Inventario_Exhibicion exhibicion) {
    this.vistas = vistas;
    this.utilidades = utilidades;
    this.general = general;
    this.vendidos = vendidos;
    this.recibidos = recibidos;
    this.bodega = bodega;
    this.exhibicion = exhibicion;
  }

  public boolean Admin(User session) {
    boolean salir = false;
    boolean apagar = false;
    while (!salir) {
      Utilidades.limpiarPantalla();
      System.out.println("¡Bienvenido(a) " + session.getNombre() + "!\n");
      Vistas.MenuAdministrador();
      int opcion = scanner.nextInt();
      scanner.nextLine();
      switch (opcion) {
        case 1:// Realizar Venta;-----------------------------------------------------
          this.RealizarVenta(session);
          break;

        case 2:// Gestionar Productos;------------------------------------------------
          this.GestionarProductos();
          break;

        case 3:// Gestionar Empleados;------------------------------------------------
          this.GestionarEmpleados(session);
          break;

        case 4:// Generar Reportes;---------------------------------------------------
          this.GenerarReportes();
          break;

        case 5:// Buscar Producto;-----------------------------------------------------
          Utilidades.limpiarPantalla();
          System.out.print("MÉTODO EN DESARROLLO ");
          Utilidades.esperarPresionarEnter();
          break;

        case 6:// Buscar Transacción;--------------------------------------------------
          salir = false;
          /*while (!salir) {
            Utilidades.limpiarPantalla();
            Vistas.ModuloBuscarTransacciones();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion){
              case 1:{
                
              }
              case 2:{
                
              }
              case 3:{
                
              }
              default:{
                
              }
            }
          }*/
          break;
          
        case 7:// Ver Inventario;------------------------------------------------------
          Utilidades.limpiarPantalla();
          /*Vistas.ModuloVerInventario();
          opcion = scanner.nextInt();
          scanner.nextLine();
          switch (opcion) {
              case 1: // Ver Inventario Completo
                  Utilidades.limpiarPantalla();
                  System.out.println("Inventario Completo:");
                  general.mostrarInventarioCompleto();//Crear método
                  Utilidades.esperarPresionarEnter();
                  break;
      
              case 2: // Ver Inventario en Bodega
                  Utilidades.limpiarPantalla();
                  System.out.println("Inventario en Bodega:");
                  bodega.mostrarInventario();//Crear método
                  Utilidades.esperarPresionarEnter();
                  break;
      
              case 3: // Ver Inventario en Exhibición
                  Utilidades.limpiarPantalla();
                  System.out.println("Inventario en Exhibición:");
                  exhibicion.mostrarInventario();//Crear método
                  Utilidades.esperarPresionarEnter();
                  break;
      
              default:
                  System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
                  Utilidades.esperarPresionarEnter();
                  break;
          
          System.out.print("MÉTODO EN DESARROLLO ");
          Utilidades.esperarPresionarEnter();*/
          break;

        case 8: // Recibir Pedido
          Utilidades.limpiarPantalla();
          gestionarPedido(session);
          break;

          

        case 9:// Cerrar Sesión;-----------------------------------------------------
          salir = true;
          apagar = false;
          break;

        case 0:// Cerrar Aplicación;-----------------------------------------------------
          salir = true;
          apagar = true;
          break;

        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          Utilidades.esperarPresionarEnter();
          break;
      }
    }
    return apagar;
  }

  public boolean Empleado(User session) {
    boolean salir = false;
    boolean apagar = false;
    while (!salir) {
      Utilidades.limpiarPantalla();
      System.out.println("¡Bienvenido(a) " + session.getNombre() + "!\n");
      Vistas.MenuEmpleado();
      int opcion = scanner.nextInt();
      scanner.nextLine();
      switch (opcion) {
        case 1:// Realizar Venta;-----------------------------------------------------
          Utilidades.limpiarPantalla();
          Vistas.ModuloVenta();
          opcion = scanner.nextInt();
          scanner.nextLine();
          System.out.print("MÉTODO EN DESARROLLO ");
          Utilidades.esperarPresionarEnter();
          break;

        case 2:// Consultar producto;------------------------------------------------
          System.out.print("MÉTODO EN DESARROLLO ");
          Utilidades.esperarPresionarEnter();
          break;

        case 3:// Realizar devolución;------------------------------------------------
          System.out.print("MÉTODO EN DESARROLLO ");
          Utilidades.esperarPresionarEnter();
          break;

        case 4:// Recibir pedido;---------------------------------------------------
          Utilidades.limpiarPantalla();
          // Vistas.ModuloGenerarReportes();
          // opcion = scanner.nextInt();
          // scanner.nextLine();
          System.out.print("MÉTODO EN DESARROLLO ");
          Utilidades.esperarPresionarEnter();
          break;

        case 5: // cerrar sesión
          salir = true;
          apagar = false;
          break;

        case 0: // apagar sistema.
          salir = true;
          apagar = true;
          break;

        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          Utilidades.esperarPresionarEnter();
          break;
      }
    }
    return apagar;
  }

  //---------------------------------------------------------------------------------------------------------
  // Método para realizar todas las opciones de Realizar Venta
  // Nota: Agregar desde Visual
  private void RealizarVenta(User session){
    boolean salir = false;
    int NumVentas = (vendidos.consultar_cantidad() + 1);
    String IdVenta = Integer.toString(NumVentas);
    Venta venta = new Venta(IdVenta,session);
    while (!salir){
      Utilidades.limpiarPantalla();
      System.out.println("----------MODULO VENTA----------\n");
      Vistas.ModuloVenta();
      int opcion = scanner.nextInt();
      scanner.nextLine();
      switch(opcion){
        case 1://Agregar Producto
          Utilidades.limpiarPantalla();
          System.out.println("----------AGREGAR PRODUCTO----------\n");
          System.out.println("Id de producto: ");
          String IdProducto = scanner.nextLine();
          Product producto = general.buscarProductoId(IdProducto);
          if (producto != null){//En caso de que el producto exista
            System.out.println("Cantidad deseada: ");
            int CantProducto = scanner.nextInt();
            scanner.nextLine();
            if(CantProducto < exhibicion.consultar_cantidad_unidades(producto)){//En caso de que  hayan suficientes unidades disponibles
              System.out.println("Se van a agregar "+ CantProducto + " unidades del producto con Id : " + IdProducto);
              if(utilidades.preguntaContinuar()){//En caso de que confirmen 
                venta.agregarProducto(producto, CantProducto);
                System.out.println("PRODUCTO AGREGADO EXITOSAMENTE AL CARRITO.");
                Utilidades.esperarPresionarEnter();
                break;
              } else {//En caso de que digan que no quieren agregar el producto
                System.out.println("EL PRODUCTO NO SE AGREGÓ AL CARRITO.");
                Utilidades.esperarPresionarEnter();
                break;
              }
            } else { //En caso de que no hayan suficientes unidades del producto
              int CantDisponible= exhibicion.consultar_cantidad_unidades(producto);
              System.out.println("Actualmente solo hay "+ CantDisponible + " unidades del producto en exhibición.\n");
              System.out.println("Desea llevar esta cantidad?");
              if(utilidades.preguntaContinuar()){//En caso de que si deseen llevar la cantidad disponible
                System.out.println("Se van a agregar "+ CantDisponible + " unidades del producto con Id : " + IdProducto);
                if(utilidades.preguntaContinuar()){//En caso de que confirmen 
                  venta.agregarProducto(producto, CantDisponible);
                  System.out.println("PRODUCTO AGREGADO EXITOSAMENTE AL CARRITO.");
                  Utilidades.esperarPresionarEnter();
                  break;
                } else {//En caso de que digan que no quieren agregar el producto
                  System.out.println("EL PRODUCTO NO SE AGREGÓ AL CARRITO.");
                  Utilidades.esperarPresionarEnter();
                  break;
                }
              }
            }
          } else {//En caso de que el producto no exista
            System.out.println("No existe un producto con el Id : " + IdProducto);
            Utilidades.esperarPresionarEnter();
          }

        break;
        case 2://Quitar Producto

          System.out.println("METODO EN DESARROLLO");
        break;
        case 3://Calcular total
          System.out.println("METODO EN DESARROLLO");
        break;
        case 4://Mostrar Carrito
          System.out.println("METODO EN DESARROLLO");
        break;
        case 5://Finalizar Venta
          System.out.println("METODO EN DESARROLLO");
        break;
        case 6://Cancelar Venta
          System.out.println("METODO EN DESARROLLO");
        break;
        

      }


    }


   }

  //-----------------------------------------------------------------------------------------------------------

  // Método para realizar todas las opciones de Gestión de Empleados
  private void GestionarEmpleados(User session) {
    boolean salir = false;
    while (!salir) {
      Utilidades.limpiarPantalla();
      Vistas.ModuloGestionEmpleados();
      int opcion = scanner.nextInt();
      scanner.nextLine();
      switch (opcion) {
        case 1:
          // Agregar usuario
          Utilidades.limpiarPantalla();
          System.out.print("ID de usuario: ");
          String nuevoId = scanner.nextLine();
          while (!Utilidades.IdValido(nuevoId)) {
            System.out.print("ID de usuario: ");
            nuevoId = scanner.nextLine();
          }
          if (utilidades.buscarUsuarioId(nuevoId) != null) {
            System.out.println("Ya existe un usuario con Id: " + nuevoId);
            Utilidades.esperarPresionarEnter();
            break;
          }
          String[] elementos = utilidades.pedirDatosUser("");
          utilidades.agregarUsuario(new User(elementos[0], elementos[1], elementos[2], elementos[3], nuevoId));
          System.out.println("El usuario fue creado con éxito.");
          Utilidades.esperarPresionarEnter();
          break;

        case 2:
          // Quitar usuario
          Utilidades.limpiarPantalla();
          System.out.print("ID de usuario a eliminar: ");
          String IdEliminar = scanner.nextLine();
          User usuarioEliminar = utilidades.buscarUsuarioId(IdEliminar);
          if (IdEliminar.equals(session.getId())) {
            System.out.println("No puedes eliminarte a tí mismo del sistema.\nPide ayuda al usuario raíz.");
            Utilidades.esperarPresionarEnter();
            break;
          } else if (usuarioEliminar.getNivel_acceso().equals("3")) {
            System.out.println("No se puede eliminar al usuario raíz.");
            Utilidades.esperarPresionarEnter();
            break;
          }
          if (usuarioEliminar != null) {
            System.out.print("Se eliminará al siguiente ");
            Vistas.InfoUsuario(usuarioEliminar);
            boolean continuar = utilidades.preguntaContinuar();
            if (continuar) {
              if (utilidades.quitarUsuario(IdEliminar)) {
                System.out.println("Usuario eliminado.");
              } else {
                System.out.println("Se encontró al usuario, Pero hubo un error al eliminarlo, intenta otra vez.");
              }
            } else {
              System.out.println("No se eliminó al usuario.");
            }
          } else {
            System.out.println("Usuario no encontrado.");
          }
          Utilidades.esperarPresionarEnter();
          break;

        case 3:
          // Modificar usuario
          Utilidades.limpiarPantalla();
          System.out.print("ID de usuario a modificar: ");
          String idusuario = scanner.nextLine();
          elementos = utilidades.pedirDatosUser("Nuevo ");
          if (utilidades.modificarUsuario(idusuario, elementos[0], elementos[1],
              elementos[2], elementos[3])) {
            System.out.println("Usuario modificado.");
          } else {
            System.out.println("Usuario no encontrado.");
          }
          Utilidades.esperarPresionarEnter();
          break;

        case 4:
          // Buscar usuario
          Utilidades.limpiarPantalla();
          System.out.print("ID de usuario a buscar: ");
          String IdBuscar = scanner.nextLine();
          User usuarioEncontrado = utilidades.buscarUsuarioId(IdBuscar);
          if (usuarioEncontrado != null) {
            Vistas.InfoUsuario(usuarioEncontrado);
          } else {
            System.out.println("Usuario no encontrado.");
          }
          Utilidades.esperarPresionarEnter();
          break;

        case 5:
          salir = true;
          break;

        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          Utilidades.esperarPresionarEnter();
          break;
      }
    }
  }

  // Método para realizar todas las opciones de Gestión de Productos
  private void GestionarProductos() {
    boolean salir = false;
    while (!salir) {
      Utilidades.limpiarPantalla();
      Vistas.ModuloGestionProductos();
      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch (opcion) {
        case 1:// Agregar Producto
          Utilidades.limpiarPantalla();
          System.out.println("----------AGREGAR PRODUCTO----------\n");
          System.out.println("ID de producto: ");
          String nuevoId = scanner.nextLine();
          if (general.buscarProductoId(nuevoId) != null) {
            System.out.println("Ya existe un producto con Id: " + nuevoId);
            Utilidades.esperarPresionarEnter();
            break;
          }
          String[] elementos = utilidades.pedirDatosProducto("");
          // crear el producto en bodega de forma nativa, dar la posibilidad de
          // 'agregarlo'(moverlo) a exhibicion agregando unidades (identificar total en
          // bodega y cantidad a mover a exhibicion)
          float precio = Float.parseFloat(elementos[2]);
          general.agregarProducto(new Product(elementos[0], elementos[1], nuevoId, precio));
          // de no indicarse antes, dar la posibilidad de agregar unidades a bodega.??
          System.out.println("El nuevo producto fue creado correctamente.");
          Utilidades.esperarPresionarEnter();
          break;

        case 2:// Eliminar Producto
          Utilidades.limpiarPantalla();
          System.out.println("----------ELIMINAR PRODUCTO----------\n");
          System.out.println("ID de producto a eliminar: ");
          String IdEliminar = scanner.nextLine();
          // verificar presencia en exhibicion (no se puede eliminar de exhibicion), se
          // mueve a bodega y entonces se elimina, indicarlo al usuario antes de hacerlo.
          Product productoEliminar = general.buscarProductoId(IdEliminar);
          if (productoEliminar != null) {
            System.out.print("Se eliminará al siguiente producto: ");
            Vistas.InfoProducto(productoEliminar);
            // informar que aún puede haber registro del producto en los inventarios de
            // venta y devolucion.
            boolean continuar = utilidades.preguntaContinuar();
            if (continuar) {
              general.eliminarProducto(IdEliminar); // de bodega
            } else {
              System.out.println("No se eliminó el producto.");
            }
          } else {
            System.out.println("No se encontró ningun producto con el Id : " + IdEliminar);
          }
          Utilidades.esperarPresionarEnter();
          break;
        case 3:// Modificar Producto
          Utilidades.limpiarPantalla();
          System.out.print("ID de producto a modificar: ");
          String IdProducto = scanner.nextLine();
          elementos = utilidades.pedirDatosProducto("Nuevo ");
          precio = Float.parseFloat(elementos[2]);
          if (general.ModificarProducto(IdProducto, elementos[0], elementos[1], precio)) {
            System.out.println("Producto modificado.");
          } else {
            System.out.println("Producto no encontrado.");
          }
          Utilidades.esperarPresionarEnter();
          break;
        case 0:
          salir = true;
          break;

        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          Utilidades.esperarPresionarEnter();
          break;
      }
    }
  }

  private void GenerarReportes() {
    boolean salir = false;
    while (!salir) {
      utilidades.limpiarPantalla();
      vistas.ModuloGenerarReportes();
      int opcion = scanner.nextInt();
      scanner.nextLine();
      switch (opcion) {
        case 1: {
          utilidades.limpiarPantalla();
          System.out.println("------- Reporte General de Ventas -------");
          SimpleEntry<Pair<Map<User, Double>, Map<User, Integer>>, Double> resultado = vendidos.GenerarReporte();
          System.out.println("\nTotal de ventas general: $" + resultado.getValue());
          utilidades.esperarPresionarEnter();
        }
        case 2: {
          utilidades.limpiarPantalla();
          System.out.println("------- Reporte individual de Ventas -------");
          SimpleEntry<Pair<Map<User, Double>, Map<User, Integer>>, Double> resultado = vendidos.GenerarReporte();
          Map<User, Double> totalVentasPorEmpleado = resultado.getKey().getKey();
          Map<User, Integer> cantidadVentasPorEmpleado = resultado.getKey().getValue();
          if (!totalVentasPorEmpleado.isEmpty() && !cantidadVentasPorEmpleado.isEmpty()) {
            System.out.println("Total de ventas por empleado:");
            totalVentasPorEmpleado.entrySet().forEach(new Consumer<Map.Entry<User, Double>>() {
              @Override
              public void accept(Map.Entry<User, Double> entry) {
                User empleado = entry.getKey();
                double total = entry.getValue();
                int cantidad = cantidadVentasPorEmpleado.get(empleado);
                System.out.println("\n------\nEmpleado: " + empleado.getNombre() + "ID: " + empleado.getId()
                    + "\nCantidad de ventas: " + cantidad + "\nTotal: $" + total);
              }
            });
          } else {
            System.out.println("Los empleados no han realizado ventas.");
          }
          // totalVentasPorEmpleado.forEach((empleado, total) -> {
          // int cantidad = cantidadVentasPorEmpleado.get(empleado);
          // System.out.println("Empleado: " + empleado.getNombre() + "ID: " +
          // empleado.getId() + "\nCantidad de ventas: " + cantidad + "\nTotal: $" +
          // total);
          // });

          Utilidades.esperarPresionarEnter();
        }
        case 3: {
          salir = true;
          break;
        }
      }
    }
  }

  public void gestionarPedido(User session) {
    boolean salir = false;
    int NumPedido = (recibidos.consultar_cantidad()+1);
    String IdPedido = Integer.toString(NumPedido);
    System.out.println("Ingrese el nombre del proveedor:");
    String NombreProveedor = scanner.nextLine();

    Pedido pedido = new Pedido(IdPedido, session, NombreProveedor);
    while (!salir) {
      Utilidades.limpiarPantalla();
      Vistas.ModuloRecibirPedido();
      int opcion = scanner.nextInt();
      scanner.nextLine();
      switch (opcion) {
        case 1://Agregar producto al pedido
          System.out.println("Ingrese el ID del producto:");
          String idProducto = scanner.nextLine();
          //scanner.nextLine();  Consumir la nueva línea después de la entrada numérica
      
          // Verificar si el producto existe en el inventario general
          Product producto = general.buscarProductoId(idProducto);
          if (producto == null) {
              System.out.println("El producto no existe en el inventario.");
              Utilidades.esperarPresionarEnter();
              break;
          }
          System.out.println("Ingrese la cantidad:");
          int cantidad = scanner.nextInt();
          scanner.nextLine(); // Consumir la nueva línea después de la entrada numérica
      
          // Agregar el producto al carrito del pedido
          pedido.agregarProducto(producto, cantidad);
          System.out.println("Producto agregado al carrito.");
          Utilidades.esperarPresionarEnter();
        break;

        case 2://Eliminar un producto del pedido
          System.out.println("Ingrese el ID del producto a eliminar:");
          String idProductoEliminar = scanner.nextLine();
      
          // Verificar si el producto existe en el carrito del pedido
          if (!pedido.getCarrito().containsKey(idProductoEliminar)) {
            System.out.println("El producto no está en el carrito del pedido.");
            Utilidades.esperarPresionarEnter();
            break;
          }
  
          // Eliminar el producto del carrito del pedido
          pedido.getCarrito().remove(idProductoEliminar);
          System.out.println("Producto eliminado del carrito.");
          Utilidades.esperarPresionarEnter();
            
        break;
        case 3: //Finalizar pedido
            // Verificar si hay artículos en el carrito del pedido
            if (pedido.getCarrito().isEmpty()) {
                System.out.println("El carrito del pedido está vacío. No se puede finalizar el pedido.");
                Utilidades.esperarPresionarEnter();
                break;
            }
          pedido.finalizarPedido(bodega);  
          break;
        case 4: //salir
            salir = true;
            break;
        default:
          break;
      }
      
    }
}


}