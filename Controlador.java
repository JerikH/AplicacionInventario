import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.function.Consumer;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Controlador {
  // private Vistas vistas;
  private Utilidades utilidades;
  private Inventario_General general; // utilizar exhibición y bodega en su lugar
  private Inventario_Vendido vendidos;
  private Inventario_Recibido recibidos;
  private Inventario_Bodega bodega;
  private Inventario_Exhibicion exhibicion;
  private List<User> usuarios;
  private Inventario_Devuelto devolucion;

  Scanner scanner = new Scanner(System.in);

  public Controlador(Utilidades utilidades, Inventario_General general, Inventario_Vendido vendidos,
      Inventario_Recibido recibidos, Inventario_Bodega bodega, Inventario_Exhibicion exhibicion, List<User> usuarios, Inventario_Devuelto devolucion) {
    // this.vistas = vistas;
    this.utilidades = utilidades;
    this.general = general;
    this.vendidos = vendidos;
    this.recibidos = recibidos;
    this.bodega = bodega;
    this.exhibicion = exhibicion;
    this.usuarios = usuarios;
    this.devolucion = devolucion;
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
          this.BuscarProducto();
          break;

        case 6:// Buscar Transacción;
          boolean back = false;
          while (!back) {
            Utilidades.limpiarPantalla();
            Vistas.ModuloBuscarTransacciones();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion){
              case 1:{//buscar una venta
                this.buscarVenta();
                break;
              }
              case 2:{//buscar un pedido
                this.buscarPedido();
                break;
              }
              case 3:{//buscar una devolución
                this.buscarDevolucion();
                break;
              }
              case 4:{//regresar
                back = true;
                break;
              }
              default:{
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                utilidades.esperarPresionarEnter();
              }
            }
          }
          break;
          
        case 7:// Administrar inventarios;------------------------------------------------------
          AdministrarInventarios();
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
          utilidades.esperarPresionarEnter();
          break;
      }
    }
    return apagar;
  }

  public void buscarVenta(){
    boolean salir = false;
    while(!salir){
      Utilidades.limpiarPantalla();
      Vistas.ModuloBuscarVentas();
      int opcion = scanner.nextInt();
      scanner.nextLine();
      switch(opcion){
        case 1:{ //buscar venta por ID
          System.out.print("Ingrese el ID de la venta a buscar: ");
          String idVenta = scanner.nextLine();
          Venta venta = vendidos.buscarVenta(idVenta);
          if(venta != null){
            ventatoString(venta);
          }else{
            System.out.println("No se encontró la venta con el ID ingresado.");
          }
          utilidades.esperarPresionarEnter();
          break;
        }
        case 2: {//bucar venta por empleado
          System.out.print("Ingrese el ID del empleado: ");
          String idEmpleado = scanner.nextLine();
          User empleado = buscarEmpleadoID(idEmpleado);
          if (empleado != null){
            List<Venta> ventas = vendidos.get_lista_Ventas();
            List<Venta> ventasEmpleado = new ArrayList<>();
            for (Venta ve : ventas) {
              if (ve.getEmpleado().getId().equals(idEmpleado)) {
                ventasEmpleado.add(ve);
              }
            }
            if(!ventasEmpleado.isEmpty()){
              System.out.println("Ventas realizadas por el empleado " + empleado.getNombre() + " Con ID: "+ idEmpleado);
              for(Venta v : ventas){
                ventatoString(v);
              }
            }else{
              System.out.println("No se encontraron ventas realizadas por el empleado " + empleado.getNombre() + " Con ID: "+ idEmpleado);
            }
          }else{
            System.out.println("El ID del empleado ingresado no es válido.");
          }
          utilidades.esperarPresionarEnter();
          break;
        }
        case 3:{ //buscar venta por fecha
          System.out.print("Ingrese la fecha de la venta (dd/MM/yyyy): ");
          String fechaIngresada = scanner.nextLine();
          SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
          try {
              Date fechaBusqueda = formatoFecha.parse(fechaIngresada);
              List<Venta> ventas = vendidos.get_lista_Ventas();
              List<Venta> ventasPorFecha = new ArrayList<>();
              for (Venta ve : ventas) {
                  if (formatoFecha.format(ve.getFecha()).equals(formatoFecha.format(fechaBusqueda))) {
                      ventasPorFecha.add(ve);
                  }
              }
              if (!ventasPorFecha.isEmpty()) {
                  System.out.println("Ventas realizadas en la fecha: " + formatoFecha.format(fechaBusqueda));
                  for (Venta v : ventasPorFecha) {
                    ventatoString(v);
                  }
              } else {
                  System.out.println("No se encontraron ventas en la fecha: " + formatoFecha.format(fechaBusqueda));
              }
          } catch (ParseException e) {
              System.out.println("La fecha ingresada no es válida. Por favor, ingrese una fecha en el formato correcto (dd/MM/yyyy).");
          }
          utilidades.esperarPresionarEnter();
          break;
        }
        case 4:{
          salir = true;
          break;
        }
        default:{
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          utilidades.esperarPresionarEnter();
          break;
        }
      }
    }
  }

  public void buscarPedido() {
    boolean salir = false;
    while (!salir) {
        Utilidades.limpiarPantalla();
        Vistas.ModuloBuscarPedidos();
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1: //buscar pedido por ID
                System.out.print("Ingrese el ID del pedido a buscar: ");
                String idPedido = scanner.nextLine();
                Pedido pedido = recibidos.buscarPedido(idPedido);
                if (pedido != null) {
                    pedidoToString(pedido);
                } else {
                    System.out.println("No se encontró el pedido con el ID ingresado.");
                }
                utilidades.esperarPresionarEnter();
                break;
            case 2: //buscar pedido por empleado
                System.out.print("Ingrese el ID del empleado: ");
                String idEmpleadoPedido = scanner.nextLine();
                User empleado = buscarEmpleadoID(idEmpleadoPedido);
                if (empleado != null) {
                  List<Pedido> pedidosEmpleado = buscarPedidosPorEmpleado(idEmpleadoPedido);
                  if (!pedidosEmpleado.isEmpty()) {
                    System.out.println("Ventas realizadas por el empleado " + empleado.getNombre() + " Con ID: "+ idEmpleadoPedido);
                      for (Pedido p : pedidosEmpleado) {
                          pedidoToString(p);
                      }
                  } else {
                    System.out.println("No se encontraron pedidos realizadas por el empleado " + empleado.getNombre() + " Con ID: "+ idEmpleadoPedido);
                  }
                } else {
                    System.out.println("El ID del empleado ingresado no es válido.");
                }
                utilidades.esperarPresionarEnter();
                break;
            case 3: //buscar pedido por fecha
                System.out.print("Ingrese la fecha del pedido (dd/MM/yyyy): ");
                String fechaIngresada = scanner.nextLine();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fechaBusqueda = formatoFecha.parse(fechaIngresada);
                    List<Pedido> pedidos = recibidos.get_lista_Pedidos();
                    List<Pedido> pedidosPorFecha = new ArrayList<>();
                    for (Pedido ped : pedidos) {
                        if (formatoFecha.format(ped.getFecha()).equals(formatoFecha.format(fechaBusqueda))) {
                            pedidosPorFecha.add(ped);
                        }
                    }
                    if (!pedidosPorFecha.isEmpty()) {
                        System.out.println("Pedidos realizadas en la fecha: " + formatoFecha.format(fechaBusqueda));
                        for (Pedido v : pedidosPorFecha) {
                          pedidoToString(v);
                        }
                    } else {
                        System.out.println("No se encontraron pedidos en la fecha: " + formatoFecha.format(fechaBusqueda));
                    }
                } catch (ParseException e) {
                    System.out.println("La fecha ingresada no es válida. Por favor, ingrese una fecha en el formato correcto (dd/MM/yyyy).");
                }
                utilidades.esperarPresionarEnter();
                break;
            case 4: //buscar pedido por proveedor
                System.out.print("Ingrese el nombre del proveedor: ");
                String proveedorBusqueda = scanner.nextLine();
                List<Pedido> pedidos = recibidos.get_lista_Pedidos();
                List<Pedido> pedidosProveedor = new ArrayList<>();
                for (Pedido pe : pedidos) {
                    if (pe.getProveedor().equals(proveedorBusqueda)) {
                        pedidosProveedor.add(pe);
                    }
                }
                if (!pedidosProveedor.isEmpty()) {
                    System.out.println("Pedidos del proveedor: " + proveedorBusqueda);
                    for (Pedido p : pedidosProveedor) {
                        pedidoToString(p);
                    }
                } else {
                    System.out.println("No se encontraron pedidos del proveedor: " + proveedorBusqueda);
                }
                utilidades.esperarPresionarEnter();
                break;
            case 5: //salir
                salir = true;
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                utilidades.esperarPresionarEnter();
                break;
        }
    }
}

  // public void buscarDevolucion(){
  //   boolean salir = false;
  //   while(!salir){
  //     Utilidades.limpiarPantalla();
  //     Vistas.ModuloBuscarDevoluciones();
  //     int opcion = scanner.nextInt();
  //     scanner.nextLine();
  //     switch(opcion){
  //       case 1:{ //buscar devuelto por ID
  //         System.out.print("Ingrese el ID de la devulución a buscar: ");
  //         String iddevolución = scanner.nextLine();
  //         Devolución devuelto = vendidos.buscarDevolución(idVenta);
  //         if(devuelto != null){
  //           devueltotoString(devuelto);
  //         }else{
  //           System.out.println("No se encontró la devolución con el ID ingresado.");
  //         }
  //         utilidades.esperarPresionarEnter();
  //         break;
  //       }
  //       case 2: {//bucar devuelto por empleado
  //         System.out.print("Ingrese el ID del empleado: ");
  //         String idEmpleado = scanner.nextLine();
  //         User empleado = buscarEmpleadoID(idEmpleado);
  //         if (empleado != null){
  //           List<Venta> devueltos = vendidos.get_lista_Ventas();
  //           List<Venta> devueltosEmpleado = new ArrayList<>();
  //           for (Venta ve : devueltos) {
  //             if (ve.getEmpleado().getId().equals(idEmpleado)) {
  //               devueltosEmpleado.add(ve);
  //             }
  //           }
  //           if(!devueltosEmpleado.isEmpty()){
  //             System.out.println("Ventas realizadas por el empleado " + empleado.getNombre() + " Con ID: "+ idEmpleado);
  //             for(Venta v : devueltos){
  //               devueltotoString(v);
  //             }
  //           }else{
  //             System.out.println("No se encontraron devueltos realizadas por el empleado " + empleado.getNombre() + " Con ID: "+ idEmpleado);
  //           }
  //         }else{
  //           System.out.println("El ID del empleado ingresado no es válido.");
  //         }
  //         utilidades.esperarPresionarEnter();
  //         break;
  //       }
  //       case 3:{ //buscar devuelto por fecha
  //         System.out.print("Ingrese la fecha de la devuelto (dd/MM/yyyy): ");
  //         String fechaIngresada = scanner.nextLine();
  //         SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
  //         try {
  //             Date fechaBusqueda = formatoFecha.parse(fechaIngresada);
  //             List<Venta> devueltos = vendidos.get_lista_Ventas();
  //             List<Venta> devueltosPorFecha = new ArrayList<>();
  //             for (Venta ve : devueltos) {
  //                 if (formatoFecha.format(ve.getFecha()).equals(formatoFecha.format(fechaBusqueda))) {
  //                     devueltosPorFecha.add(ve);
  //                 }
  //             }
  //             if (!devueltosPorFecha.isEmpty()) {
  //                 System.out.println("Ventas realizadas en la fecha: " + formatoFecha.format(fechaBusqueda));
  //                 for (Venta v : devueltosPorFecha) {
  //                   devueltotoString(v);
  //                 }
  //             } else {
  //                 System.out.println("No se encontraron devueltos en la fecha: " + formatoFecha.format(fechaBusqueda));
  //             }
  //         } catch (ParseException e) {
  //             System.out.println("La fecha ingresada no es válida. Por favor, ingrese una fecha en el formato correcto (dd/MM/yyyy).");
  //         }
  //         utilidades.esperarPresionarEnter();
  //         break;
  //       }
  //       case 4:{
  //         salir = true;
  //         break;
  //       }
  //       default:{
  //         System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
  //         utilidades.esperarPresionarEnter();
  //         break;
  //       }
  //     }
  //   }
  // }

  public User buscarEmpleadoID(String idEmpleado){
      User empleado = null;
      for (User usuario : usuarios) {
          if (usuario.getId().equals(idEmpleado)) {
              empleado = usuario;
              break;
          } else {
              empleado = null;
          }
      }
      return empleado;
  }

  public List<Pedido> buscarPedidosPorEmpleado(String idEmpleado) {
    List<Pedido> pedidos = recibidos.get_lista_Pedidos();
    List<Pedido> pedidosEmpleado = new ArrayList<>();
    for (Pedido pe : pedidos) {
        if (pe.getEmpleado().getId().equals(idEmpleado)) {
            pedidosEmpleado.add(pe);
        }
    }
    return pedidosEmpleado;
  }

  public void ventatoString(Venta venta){
    System.out.println("Venta Realizada por: " + venta.getEmpleado().getNombre() + " Con ID: " + venta.getEmpleado().getId());
    System.out.println("Fecha: " + venta.getFecha());
    System.out.println("Id de la venta: " + venta.getId());
    System.out.println("Productos vendidos: ");
    venta.mostrarProductosEnCarrito();
    System.out.println("Total: " + venta.calcularTotal() + "\n\n");
  }

  public void pedidoToString(Pedido pedido){
    System.out.println("Pedido Realizada por: " + pedido.getEmpleado().getNombre() + " Con ID: " + pedido.getEmpleado().getId());
    System.out.println("Fecha: " + pedido.getFecha());
    System.out.println("Id del pedido: " + pedido.getId());
    System.out.println("Proveedor: " + pedido.getProveedor());
    System.out.println("Productos pedidos: ");
    pedido.mostrarProductosEnCarrito();
    System.out.println("Total: " + pedido.calcularTotal() + "\n\n");
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
          utilidades.esperarPresionarEnter();
          break;

        case 2:// Consultar producto;------------------------------------------------
          System.out.print("MÉTODO EN DESARROLLO ");
          utilidades.esperarPresionarEnter();
          break;

        case 3:// Realizar devolución;------------------------------------------------
          System.out.print("MÉTODO EN DESARROLLO ");
          utilidades.esperarPresionarEnter();
          break;

        case 4:// Recibir pedido;---------------------------------------------------
          Utilidades.limpiarPantalla();
          // Vistas.ModuloGenerarReportes();
          // opcion = scanner.nextInt();
          // scanner.nextLine();
          System.out.print("MÉTODO EN DESARROLLO ");
          utilidades.esperarPresionarEnter();
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
          utilidades.esperarPresionarEnter();
          break;
      }
    }
    return apagar;
  }

  //---------------------------------------------------------------------------------------------------------
  // Método para realizar todas las opciones de Realizar Venta
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
          System.out.print("Id de producto: ");
          String IdProducto = scanner.nextLine();
          Product producto = general.buscarProductoId(IdProducto);
          if (producto != null){//En caso de que el producto exista
            Vistas.InfoProducto(producto);
            System.out.print("Cantidad deseada: ");
            int CantProducto = scanner.nextInt();
            scanner.nextLine();
            if(CantProducto < exhibicion.consultar_cantidad_unidades(producto)){//En caso de que  hayan suficientes unidades disponibles
              System.out.println("Se van a agregar "+ CantProducto + " unidades del producto con Id : " + IdProducto);
              if(utilidades.preguntaContinuar()){//En caso de que confirmen 
                venta.agregarProducto(producto, CantProducto);
                System.out.println("PRODUCTO AGREGADO EXITOSAMENTE AL CARRITO.");
                utilidades.esperarPresionarEnter();
                break;
              } else {//En caso de que digan que no quieren agregar el producto
                System.out.println("EL PRODUCTO NO SE AGREGÓ AL CARRITO.");
                utilidades.esperarPresionarEnter();
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
                  utilidades.esperarPresionarEnter();
                  break;
                } else {//En caso de que digan que no quieren agregar el producto
                  System.out.println("EL PRODUCTO NO SE AGREGÓ AL CARRITO.");
                  utilidades.esperarPresionarEnter();
                  break;
                }
              }
            }
          } else {//En caso de que el producto no exista
            System.out.println("No existe un producto con el Id : " + IdProducto);
            utilidades.esperarPresionarEnter();
          }
        break;

        case 2://Quitar Producto
          Utilidades.limpiarPantalla();
          System.out.println("----------QUITAR PRODUCTO----------\n");
          System.out.print("Id de producto: ");
          String idProd = scanner.nextLine();
          producto = general.buscarProductoId(idProd);
          if(!venta.buscarProducto(idProd).equals(null)){//En caso de que el producto si esté en el carrito
            Vistas.InfoProducto(producto);
            int cantidad = venta.consultarCantUnidades(idProd);
            System.out.println("Cantidad: " + cantidad);
            System.out.println("Se va a eliminar el producto.");
            if(utilidades.preguntaContinuar()){//En caso de que confirmen
              venta.quitarProducto(idProd);
              System.out.println("PRODUCTO ELIMINADO DEL CARRITO.");
              utilidades.esperarPresionarEnter();
            } else { //En caso de que digan que no quieren eliminar el producto
              System.out.println("EL PRODUCTO NO SE ELIMINO DEL CARRITO.");
              utilidades.esperarPresionarEnter();
            }
          } else {//En caso de que el producto no se encuentre en el carrito
            System.out.println("El producto no se encuentra en el carrito.");
            utilidades.esperarPresionarEnter();
          }
        break;

        case 3://Calcular total
          Utilidades.limpiarPantalla();
          System.out.println("----------TOTAL----------\n");
          double total =  venta.calcularTotal();
          System.out.println("El total actual de su carrito es: $"+ total);
          utilidades.esperarPresionarEnter();
          break;
        case 4://Mostrar Carrito
          System.out.println("----------CARRITO----------\n");
          venta.mostrarProductosEnCarrito();
          total =  venta.calcularTotal();
          System.out.println("El total actual de su carrito es: $"+ total);
          utilidades.esperarPresionarEnter();
        break;
        case 5://Finalizar Venta
          System.out.println("---------FINALIZAR VENTA---------");
          System.out.println("Se finzalizará la venta...");
          if(!utilidades.preguntaContinuar()){//En caso de que si deseen finalizar la venta
            venta.finalizarVenta(vendidos, exhibicion);
          } else {//En caso de que no deseen finalizar la venta
            System.out.println("No se finalizó la venta....");
            utilidades.esperarPresionarEnter();
          }
          break;
        case 6://Cancelar Venta
          System.out.println("---------CANCELAR VENTA---------");
          System.out.println("Se cancelará la venta...");
          if(!utilidades.preguntaContinuar()){
            salir=true;
          } else {
            System.out.println("No se canceló la venta....");
            utilidades.esperarPresionarEnter();
          }
        break;
      }


    }


   }

  //-----------------------------------------------------------------------------------------------------------
  // Método para realizar la opción de buscar producto
  private void BuscarProducto(){
    boolean salir = false;
    while (!salir){
      Utilidades.limpiarPantalla();
      System.out.println("---------BUSCAR PRODUCTO---------");
      System.out.print("Id de producto: ");
      String IdProducto = scanner.nextLine();
      Product producto = general.buscarProductoId(IdProducto);
      if(!producto.equals(null)){//En caso de que el producto exista
        Vistas.InfoProducto(producto);
        int CantBodega = bodega.consultar_cantidad_unidades(producto);
        int CantExhibicion = exhibicion.consultar_cantidad_unidades(producto);
        System.out.print("Cantidad en Bodega: " + CantBodega);
        System.out.print("Cantidad en Exhibición: "+ CantExhibicion);
        salir = true;
        utilidades.esperarPresionarEnter();

      } else {//En caso de que el producto no exista
        System.out.print("El producto con Id  (" + IdProducto + ") no está en el sistema");
        salir = true;
        utilidades.esperarPresionarEnter();
      }
    }
  }

  //---------------------------------------------------------------------------------------------------------
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
            utilidades.esperarPresionarEnter();
            break;
          }
          String[] elementos = utilidades.pedirDatosUser("");
          utilidades.agregarUsuario(new User(elementos[0], elementos[1], elementos[2], elementos[3], nuevoId));
          System.out.println("El usuario fue creado con éxito.");
          utilidades.esperarPresionarEnter();
          break;

        case 2:
          // Quitar usuario
          Utilidades.limpiarPantalla();
          System.out.print("ID de usuario a eliminar: ");
          String IdEliminar = scanner.nextLine();
          User usuarioEliminar = utilidades.buscarUsuarioId(IdEliminar);
          if (usuarioEliminar != null) {
            if (IdEliminar.equals(session.getId())) {
              System.out.println("No puedes eliminarte a tí mismo del sistema.\nPide ayuda al usuario raíz.");
              utilidades.esperarPresionarEnter();
              break;
            } else if (usuarioEliminar.getNivel_acceso().equals("3")) {
              System.out.println("No se puede eliminar al usuario raíz.");
              utilidades.esperarPresionarEnter();
              break;
            }
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
          utilidades.esperarPresionarEnter();
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
          utilidades.esperarPresionarEnter();
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
          utilidades.esperarPresionarEnter();
          break;

        case 5:
          salir = true;
          break;

        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          utilidades.esperarPresionarEnter();
          break;
      }
    }
  }

  //-----------------------------------------------------------------------------------------------------------
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
            utilidades.esperarPresionarEnter();
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
          utilidades.esperarPresionarEnter();
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
          utilidades.esperarPresionarEnter();
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
          utilidades.esperarPresionarEnter();
          break;
        case 0:
          salir = true;
          break;

        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          utilidades.esperarPresionarEnter();
          break;
      }
    }
  }

  //-----------------------------------------------------------------------------------------------------------
  private void GenerarReportes() {
    boolean salir = false;
    while (!salir) {
      Utilidades.limpiarPantalla();
      Vistas.ModuloGenerarReportes();
      int opcion = scanner.nextInt();
      scanner.nextLine();
      switch (opcion) {
        case 1: {
          Utilidades.limpiarPantalla();
          System.out.println("------- Reporte General de Ventas -------");
          SimpleEntry<Pair<Map<User, Double>, Map<User, Integer>>, Double> resultado = vendidos.GenerarReporte();
          System.out.println("\nTotal de ventas general: $" + resultado.getValue());
          utilidades.esperarPresionarEnter();
        }
        case 2: {
          Utilidades.limpiarPantalla();
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

          utilidades.esperarPresionarEnter();
        }
        case 3: {
          salir = true;
          break;
        }
      }
    }
  }

  private void AdministrarInventarios(){
    boolean salir = false;
    while(!salir){
      Utilidades.limpiarPantalla();
      Vistas.ModuloAdministrarInventarios();
      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch(opcion){
        case 1:{
          VerInventarios(); //Método para ver inventarios.
          break;
        }
        case 2:{
          MoverInventarios(); //Método para mover productos en los inventarios.
          break;
        }
        case 3:{
          salir = true;
          break;
        }
        default:{
          System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
          utilidades.esperarPresionarEnter();
          break;
        }
      }
    }
  }

  // Metodo para gestioner pedidos
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
            utilidades.esperarPresionarEnter();
            break;
        }
        System.out.println("Ingrese la cantidad:");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después de la entrada numérica
    
        // Agregar el producto al carrito del pedido
        pedido.agregarProducto(producto, cantidad);
        System.out.println("Producto agregado al carrito.");
        utilidades.esperarPresionarEnter();
      break;

      case 2://Eliminar un producto del pedido
        System.out.println("Ingrese el ID del producto a eliminar:");
        String idProductoEliminar = scanner.nextLine();
    
        // Verificar si el producto existe en el carrito del pedido
        if (!pedido.getCarrito().containsKey(idProductoEliminar)) {
          System.out.println("El producto no está en el carrito del pedido.");
          utilidades.esperarPresionarEnter();
          break;
        }

        // Eliminar el producto del carrito del pedido
        pedido.getCarrito().remove(idProductoEliminar);
        System.out.println("Producto eliminado del carrito.");
        utilidades.esperarPresionarEnter();
          
      break;
      case 3: //Finalizar pedido
          // Verificar si hay artículos en el carrito del pedido
          if (pedido.getCarrito().isEmpty()) {
              System.out.println("El carrito del pedido está vacío. No se puede finalizar el pedido.");
              utilidades.esperarPresionarEnter();
              break;
          }
        pedido.finalizarPedido(bodega);
        utilidades.esperarPresionarEnter();  
        break;
      case 4: //salir
          salir = true;
          break;
      default:
        break;
    }
    
  }
}

  //Método para ver inventarios.
  private void VerInventarios(){
    boolean salir = false;
    while(!salir){
      Utilidades.limpiarPantalla();
      Vistas.ModuloVerInventarios();
      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch(opcion){
        case 1:{ // Ver Inventario Completo
          Utilidades.limpiarPantalla();
          System.out.println("Inventario Completo:");
          general.mostrarInventario();
          utilidades.esperarPresionarEnter();
          break;
        }
      
        case 2:{ // Ver Inventario en Bodega
          Utilidades.limpiarPantalla(); 
          System.out.println("Inventario en Bodega:");
          bodega.mostrarInventario();
          utilidades.esperarPresionarEnter();
          break;
        }
      
        case 3:{ // Ver Inventario en Exhibición
          Utilidades.limpiarPantalla();
          System.out.println("Inventario en Exhibición:");
          exhibicion.mostrarInventario();
          utilidades.esperarPresionarEnter();
          break;
        }

        case 4:{ //Ver lista de devolución
          Devoluciones();
          break;
        }

        case 5:{
          salir = true;
          break;
        }
      
        default:{
          System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
          utilidades.esperarPresionarEnter();
          break;
        }
      }  
    }
  }

  //Método para mover inventarios.
  private void MoverInventarios(){
    boolean salir = false;
    while(!salir){
      Utilidades.limpiarPantalla();
      Vistas.ModuloMoverInventarios();
      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch(opcion){
        case 1:{
          Utilidades.limpiarPantalla();
          System.out.println("----------BODEGA A EXHIBICION----------\n");
          System.out.println("ID de producto: ");
          String IdP = scanner.nextLine();
          Product productoamover = general.buscarProductoId(IdP);
          if (productoamover == null) {
            System.out.println("No hay existencias en bodega.");
            utilidades.esperarPresionarEnter();
            break;
          }
          System.out.println("Cantidad: ");
          int qty = scanner.nextInt();
          bodega.mover_a_exhibición(productoamover,qty,exhibicion);
          System.out.println("Se ha movido el producto.");
          utilidades.esperarPresionarEnter();
          break;
        }
        case 2:{
          Utilidades.limpiarPantalla();
          System.out.println("----------EXHIBICION A BODEGA----------\n");
          System.out.println("ID de producto: ");
          String IdP = scanner.nextLine();
          Product productoamover = general.buscarProductoId(IdP);
          if (productoamover == null) {
            System.out.println("No hay existencias en exhibicion.");
            utilidades.esperarPresionarEnter();
            break;
          }
          System.out.println("Cantidad: ");
          int qty = scanner.nextInt();
          exhibicion.mover_a_bodega(productoamover,qty,bodega);
          System.out.println("Se ha movido el producto.");
          utilidades.esperarPresionarEnter();
          break;
        }
        case 3:{
          Utilidades.limpiarPantalla();
          System.out.println("----------DEVOLUCIONES A BODEGA----------\n");
          System.out.println("ID de producto: ");
          String IdP = scanner.nextLine();
          Product productoamover = general.buscarProductoId(IdP);
          if (productoamover == null) {
            System.out.println("No hay existencias de este producto para devolución.");
            utilidades.esperarPresionarEnter();
            break;
          }
          System.out.println("Cantidad: ");
          int qty = scanner.nextInt();
          devolucion.mover_a_bodega(productoamover,qty,bodega);
          System.out.println("Se ha movido el producto.");
          utilidades.esperarPresionarEnter();
          break;
        }
        case 4:{
          salir = true;
          break;
        }
        default:{
          System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
          utilidades.esperarPresionarEnter();
          break;
        }
      }
    }
  }

  private void Devoluciones(){
    boolean salir = false;
    while(!salir){
      Utilidades.limpiarPantalla();
      Vistas.ModuloDevoluciones();
      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch(opcion){
        case 1:{
          Utilidades.limpiarPantalla();
          System.out.println("Historico de devoluciones:");
          devolucion.mostrarHistorico();
          utilidades.esperarPresionarEnter();
          break;
        }
        case 2:{
          Utilidades.limpiarPantalla();
          System.out.println("Lista de devoluciones actuales:");
          devolucion.mostrarDevoluciones();
          utilidades.esperarPresionarEnter();
          break;
        }
        case 3:{
          salir = true;
          break;
        }
        default:{
          System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
          utilidades.esperarPresionarEnter();
          break;
        }
      }
    }
  }
}