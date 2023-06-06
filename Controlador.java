import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.function.Consumer;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Controlador {
  private Utilidades utilidades;
  private Inventario_General general;
  private Inventario_Vendido vendidos;
  private Inventario_Recibido recibidos;
  private Inventario_Bodega bodega;
  private Inventario_Exhibicion exhibicion;
  private List<User> usuarios;
  private Inventario_Devuelto devueltos;

  Scanner scanner = new Scanner(System.in);

  public Controlador(Utilidades utilidades, Inventario_General general, Inventario_Vendido vendidos,
      Inventario_Recibido recibidos, Inventario_Bodega bodega, Inventario_Exhibicion exhibicion, List<User> usuarios, Inventario_Devuelto devueltos) {
    this.utilidades = utilidades;
    this.general = general;
    this.vendidos = vendidos;
    this.recibidos = recibidos;
    this.bodega = bodega;
    this.exhibicion = exhibicion;
    this.usuarios = usuarios;
    this.devueltos = devueltos;
  }

  public boolean Admin(User session) {
    boolean salir = false;
    boolean apagar = false;
    while (!salir) {
      Utilidades.limpiarPantalla();
      System.out.println("¡Bienvenido(a) " + session.getNombre() + "!\n");
      Vistas.MenuAdministrador();
      int opcion = Utilidades.nextIntCustom(scanner);
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
          this.buscarTransacciones();
          break;
          
        case 7:// Administrar inventarios;------------------------------------------------------
          AdministrarInventarios();
          break;

        case 8: // Recibir Pedido
          Utilidades.limpiarPantalla();
          gestionarPedido(session);
          break;
          
        case 9: //Gestionar Devolución
          Utilidades.limpiarPantalla();
          gestionarDevolucion(session);
        break;

        case 10:// Cerrar Sesión;-----------------------------------------------------
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

  //-----------------------------------------------------------------------------------------------------
  // Método para admnistrar las opciones de empleado

   public boolean Empleado(User session) {
    boolean salir = false;
    boolean apagar = false;
    while (!salir) {
      Utilidades.limpiarPantalla();
      System.out.println("¡Bienvenido(a) " + session.getNombre() + "!\n");
      Vistas.MenuEmpleado();
      int opcion = Utilidades.nextIntCustom(scanner);
      scanner.nextLine();
      switch (opcion) {
        case 1:// Realizar Venta;-----------------------------------------------------
          this.RealizarVenta(session);
          break;

        case 2:// Consultar producto;------------------------------------------------
          this.BuscarProducto();
          break;

        case 3:// Realizar devolución;------------------------------------------------
          Utilidades.limpiarPantalla();
          gestionarDevolucion(session);
          break;

        case 4:// Recibir pedido;---------------------------------------------------
          Utilidades.limpiarPantalla();
          gestionarPedido(session);
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

  //-----------------------------------------------------------------------------------------------------
  //Desde acá se crean las funciones utilizadas en el case
  //-----------------------------------------------------------------------------------------------------
  //Método para realizar la opción de buscar transacciones
  public void buscarTransacciones(){
    boolean back = false;
    while (!back) {
      Utilidades.limpiarPantalla();
      Vistas.ModuloBuscarTransacciones();
      System.out.print("Seleccione una opción: ");
      int opcion = Utilidades.nextIntCustom(scanner);
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
  }

 //-----------------------------------------------------------------------------------------------------
 //Método para realizar la función de buscar venta
  public void buscarVenta(){
    boolean salir = false;
    while(!salir){
      Utilidades.limpiarPantalla();
      Vistas.ModuloBuscarVentas();
      int opcion = Utilidades.nextIntCustom(scanner);
      scanner.nextLine();
      switch(opcion){
        case 1:{ //buscar venta por ID
          System.out.print("Ingrese el ID de la venta a buscar: ");
          String idVenta = scanner.nextLine();
          Venta venta = vendidos.buscarVenta(idVenta);
          if(venta != null){
            Vistas.ventatoString(venta);
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
              for(Venta v : ventasEmpleado){
                Vistas.ventatoString(v);
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
                    Vistas.ventatoString(v);
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

   //-----------------------------------------------------------------------------------------------------
   //Método para realizar la opción de buscar pedido
  public void buscarPedido() {
    boolean salir = false;
    while (!salir) {
        Utilidades.limpiarPantalla();
        Vistas.ModuloBuscarPedidos();
        int opcion = Utilidades.nextIntCustom(scanner);
        scanner.nextLine();
        switch (opcion) {
            case 1: //buscar pedido por ID
                System.out.print("Ingrese el ID del pedido a buscar: ");
                String idPedido = scanner.nextLine();
                Pedido pedido = recibidos.buscarPedido(idPedido);
                if (pedido != null) {
                    Vistas.pedidoToString(pedido);
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
                          Vistas.pedidoToString(p);
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
                          Vistas.pedidoToString(v);
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
                        Vistas.pedidoToString(p);
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


 //-----------------------------------------------------------------------------------------------------
 //Método para realizar la opción de buscar devolución
  public void buscarDevolucion(){
  boolean salir = false;
  while(!salir){
    Utilidades.limpiarPantalla();
    Vistas.ModuloBuscarDevoluciones();
    int opcion = Utilidades.nextIntCustom(scanner);
    scanner.nextLine();
    switch(opcion){
      case 1:{ //buscar devolucion por ID
        System.out.print("Ingrese el ID de la devolución a buscar: ");
        String idDevolucion = scanner.nextLine();
        Devolucion devolucion = devueltos.buscarDevolucion(idDevolucion);
        if(devolucion != null){
          Vistas.devoluciontoString(devolucion);
        }else{
          System.out.println("No se encontró la devolución con el ID ingresado.");
        }
        utilidades.esperarPresionarEnter();
        break;
      }
      case 2: {//bucar devolución por empleado
        System.out.print("Ingrese el ID del empleado: ");
        String idEmpleado = scanner.nextLine();
        User empleado = buscarEmpleadoID(idEmpleado);
        if (empleado != null){
          List<Devolucion> devoluciones = devueltos.get_lista_Devoluciones();
          List<Devolucion> historico = devueltos.get_lista_Historico();
          List<Devolucion> devolucionesEmpleado = new ArrayList<>();
          for (Devolucion dev : devoluciones) {
            if (dev.getEmpleado().getId().equals(idEmpleado)) {
              devolucionesEmpleado.add(dev);
            }
          }
          for (Devolucion dev : historico) {
            if (dev.getEmpleado().getId().equals(idEmpleado)) {
              devolucionesEmpleado.add(dev);
            }
          }
          if(!devolucionesEmpleado.isEmpty()){
            System.out.println("Devoluciones realizadas por el empleado " + empleado.getNombre() + " Con ID: "+ idEmpleado);
            for(Devolucion d : devolucionesEmpleado){
              Vistas.devoluciontoString(d);
            }
          }else{
            System.out.println("No se encontraron devoluciones realizadas por el empleado " + empleado.getNombre() + " Con ID: "+ idEmpleado);
          }
        }else{
          System.out.println("El ID del empleado ingresado no es válido.");
        }
        utilidades.esperarPresionarEnter();
        break;
      }
      case 3:{ //buscar devolucion por fecha
        System.out.print("Ingrese la fecha de la devolución (dd/MM/yyyy): ");
        String fechaIngresada = scanner.nextLine();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaBusqueda = formatoFecha.parse(fechaIngresada);
            List<Devolucion> devoluciones = devueltos.get_lista_Devoluciones();
            List<Devolucion> historico = devueltos.get_lista_Historico();
            List<Devolucion> devolucionesPorFecha = new ArrayList<>();
            for (Devolucion devo : devoluciones) {
                if (formatoFecha.format(devo.getFecha()).equals(formatoFecha.format(fechaBusqueda))) {
                    devolucionesPorFecha.add(devo);
                }
            }
            for (Devolucion devo : historico) {
                if (formatoFecha.format(devo.getFecha()).equals(formatoFecha.format(fechaBusqueda))) {
                    devolucionesPorFecha.add(devo);
                }
            }
            if (!devolucionesPorFecha.isEmpty()) {
                System.out.println("Devoluciones realizadas en la fecha: " + formatoFecha.format(fechaBusqueda));
                for (Devolucion devo : devolucionesPorFecha) {
                  Vistas.devoluciontoString(devo);
                }
            } else {
                System.out.println("No se encontraron devoluciones en la fecha: " + formatoFecha.format(fechaBusqueda));
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

  //-----------------------------------------------------------------------------------------------------
  //Método para buscar un empleado con el ID

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

   //-----------------------------------------------------------------------------------------------------
   //Método para buscar pedidos por Empleado

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
      System.out.print("Ingrese una opción:");
      int opcion = Utilidades.nextIntCustom(scanner);
      scanner.nextLine();
      switch(opcion){
        case 1://Agregar Producto
          Utilidades.limpiarPantalla();
          System.out.println("----------AGREGAR PRODUCTO----------\n");
          System.out.print("Id de producto: ");
          String IdProducto = scanner.nextLine();
          Product producto = general.buscarProductoId(IdProducto);
          if (producto != null){//En caso de que el producto exista
            if (exhibicion.consultar_cantidad_unidades(producto) > 0){//En caso de que si hayan unidades disponibles del producto
              Vistas.InfoProducto(producto);
            System.out.print("Cantidad deseada: ");
            int CantProducto = Utilidades.nextIntCustom(scanner);
            scanner.nextLine();
            if(CantProducto <= exhibicion.consultar_cantidad_unidades(producto)){//En caso de que  hayan suficientes unidades disponibles
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
          } else {//En caso de que no haya unidades del producto
            System.out.println("Error: Actualmente no hay existencias del producto en exhibición.");
            utilidades.esperarPresionarEnter();
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
          if(venta.buscarProducto(idProd) != null){//En caso de que el producto si esté en el carrito
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
          Utilidades.limpiarPantalla();
          System.out.println("----------CARRITO----------\n");
          venta.mostrarProductosEnCarrito();
          total =  venta.calcularTotal();
          System.out.println("El total actual de su carrito es: $"+ total);
          utilidades.esperarPresionarEnter();
        break;
        case 5://Finalizar Venta
        Utilidades.limpiarPantalla();
          System.out.println("---------FINALIZAR VENTA---------");
          if(venta.consultar_cantidad_carrito() > 0){//En caso de que hayan productos en el carrito
            System.out.println("Se finzalizará la venta...");
            if(utilidades.preguntaContinuar()){//En caso de que si deseen finalizar la venta
              venta.finalizarVenta(vendidos, exhibicion, general);
              utilidades.esperarPresionarEnter();
              salir=true;
            } else {//En caso de que no deseen finalizar la venta
              System.out.println("No se finalizó la venta....");
              utilidades.esperarPresionarEnter();
            }
          } else {
            System.out.println("Error: El carrito no tiene productos aún.");
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
        case 0:
          salir=true;
          break;
        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          utilidades.esperarPresionarEnter();
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
      if(general.buscarProductoId(IdProducto) != null){//En caso de que el producto exista
        Vistas.InfoProducto(producto);
        int CantBodega = bodega.consultar_cantidad_unidades(producto);
        int CantExhibicion = exhibicion.consultar_cantidad_unidades(producto);
        System.out.println("Cantidad en Bodega: " + CantBodega);
        System.out.println("Cantidad en Exhibición: "+ CantExhibicion);
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
      int opcion = Utilidades.nextIntCustom(scanner);
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
      System.out.print("Ingrese una opción:");
      int opcion = Utilidades.nextIntCustom(scanner);
      scanner.nextLine();

      switch (opcion) {
        case 1:// Crear Producto
          Utilidades.limpiarPantalla();
          System.out.println("----------CREAR PRODUCTO----------\n");
          System.out.print("ID de producto: ");
          String nuevoId = scanner.nextLine();
          if (general.buscarProductoId(nuevoId) != null) {//En caso de que el producto ya exista
            System.out.println("Ya existe un producto con Id: " + nuevoId);
            utilidades.esperarPresionarEnter();
            break;
          } else {//En caso de que el producto aún no exista
          String[] elementos = utilidades.pedirDatosProducto("");
          float precio = Float.parseFloat(elementos[2]);
          general.agregarProducto(new Product(elementos[0], elementos[1], nuevoId, precio));
          bodega.agregarProductoMap(nuevoId, 0);
          exhibicion.agregarProductoMap(nuevoId, 0);
          System.out.println("El nuevo producto fue creado correctamente.");
          utilidades.esperarPresionarEnter();
          }
          break;

        case 2:// Eliminar Producto
          Utilidades.limpiarPantalla();
          System.out.println("----------ELIMINAR PRODUCTO----------\n");
          System.out.print("ID de producto a eliminar: ");
          String IdEliminar = scanner.nextLine();
          Product productoEliminar = general.buscarProductoId(IdEliminar);
          if (productoEliminar != null) {
            System.out.println("Se eliminará al siguiente producto de los inventarios: ");
            Vistas.InfoProducto(productoEliminar);
            int CantBodega = bodega.consultar_cantidad_unidades(productoEliminar);
            int CantExhibicion = exhibicion.consultar_cantidad_unidades(productoEliminar);
            System.out.println("Cantidad en bodega: "+ CantBodega);
            System.out.println("Cantidad en exhibición: "+ CantExhibicion);
            if (utilidades.preguntaContinuar()) {//En caso de que confirmen que van a eliminar el producto
              general.eliminarProducto(IdEliminar); //
              bodega.eliminarProducto(IdEliminar);
              exhibicion.eliminarProducto(IdEliminar);
              System.out.println("El producto fue eliminado.");
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
          if(general.buscarProductoId(IdProducto) != (null)){//En caso de que el producto exista
            String [] elementos = utilidades.pedirDatosProducto("Nuevo ");
            float precio = Float.parseFloat(elementos[2]);
            if (general.ModificarProducto(IdProducto, elementos[0], elementos[1], precio)) {
              System.out.println("Producto modificado.");
          }else {
            System.out.println("ERROR: No se pudo modificar el producto.");
          }
        } else {//En caso de que el producto no exista
            System.out.println("El producto no existe en el sistema.");
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
  // Método para Generar Reportes de ventas

  private void GenerarReportes() {
    boolean salir = false;
    while (!salir) {
      Utilidades.limpiarPantalla();
      Vistas.ModuloGenerarReportes();
      int opcion = Utilidades.nextIntCustom(scanner);
      scanner.nextLine();
      switch (opcion) {
        case 1: {
          Utilidades.limpiarPantalla();
          System.out.println("------- Reporte General de Ventas -------");
          SimpleEntry<Pair<Map<User, Double>, Map<User, Integer>>, Double> resultado = vendidos.GenerarReporte();
          System.out.println("\nTotal de ventas general: $" + resultado.getValue());
          utilidades.esperarPresionarEnter();
          break;
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
                System.out.println("\n------\nEmpleado: " + empleado.getNombre() + " ID: " + empleado.getId()
                    + "\nCantidad de ventas: " + cantidad + "\nTotal: $" + total);
              }
            });
          } else {
            System.out.println("Los empleados no han realizado ventas.");
          }
          utilidades.esperarPresionarEnter();
        }
        break;
        case 3: {
          salir = true;
          break;
        }
      }
    }
  }

  //-----------------------------------------------------------------------------------------------------------
  // Método para Administrar Inventarios

  private void AdministrarInventarios(){
    boolean salir = false;
    while(!salir){
      Utilidades.limpiarPantalla();
      Vistas.ModuloAdministrarInventarios();
      int opcion = Utilidades.nextIntCustom(scanner);
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

  //-----------------------------------------------------------------------------------------------------------
  // Metodo para gestionar pedidos

 public void gestionarPedido(User session) {
  boolean salir = false;
  int NumPedido = (recibidos.consultar_cantidad()+1);
  String IdPedido = Integer.toString(NumPedido);
  System.out.print("Ingrese el nombre del proveedor:");
  String NombreProveedor = scanner.nextLine();

  Pedido pedido = new Pedido(IdPedido, session, NombreProveedor);
  while (!salir) {
    Utilidades.limpiarPantalla();
    Vistas.ModuloRecibirPedido();
    int opcion = Utilidades.nextIntCustom(scanner);
    scanner.nextLine();
    switch (opcion) {
      case 1://Agregar producto al pedido
        System.out.print("Ingrese el ID del producto:");
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
        int cantidad = Utilidades.nextIntCustom(scanner);
        scanner.nextLine(); // Consumir la nueva línea después de la entrada numérica
    
        // Agregar el producto al carrito del pedido
        pedido.agregarProducto(producto, cantidad);
        System.out.println("Producto agregado al carrito.");
        utilidades.esperarPresionarEnter();
      break;

      case 2://Eliminar un producto del pedido
        System.out.print("Ingrese el ID del producto a eliminar:");
        String idProductoEliminar = scanner.nextLine();
    
        // Verificar si el producto existe en el carrito del pedido
        Product productoEliminar = general.buscarProductoId(idProductoEliminar);
        if (productoEliminar == null) {
          System.out.println("El producto no está en el carrito del pedido.");
          utilidades.esperarPresionarEnter();
          break;
        }

        // Eliminar el producto del carrito del pedido
        pedido.eliminarProducto(productoEliminar);
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
        pedido.finalizarPedido(bodega,general);
        recibidos.agregarPedido(pedido);
        salir = true;
        utilidades.esperarPresionarEnter();  
        break;
      case 4: //salir
          salir = true;
          break;
      default:
        System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
        utilidades.esperarPresionarEnter();
        break;
    }
    
  }
}
  //-----------------------------------------------------------------------------------------------------------
  //Método para ver inventarios.

  private void VerInventarios(){
    boolean salir = false;
    while(!salir){
      Utilidades.limpiarPantalla();
      Vistas.ModuloVerInventarios();
      int opcion = Utilidades.nextIntCustom(scanner);
      scanner.nextLine();

      switch(opcion){
        case 1:{ // Ver Inventario Completo
          Utilidades.limpiarPantalla();
          System.out.println("Inventario Completo:");
          general.mostrarInventarioCompleto();
          utilidades.esperarPresionarEnter();
          break;
        }
      
        case 2:{ // Ver Inventario en Bodega
          Utilidades.limpiarPantalla(); 
          System.out.println("Inventario en Bodega:");
          bodega.mostrarInventario(general);
          utilidades.esperarPresionarEnter();
          break;
        }
      
        case 3:{ // Ver Inventario en Exhibición
          Utilidades.limpiarPantalla();
          System.out.println("Inventario en Exhibición:");
          exhibicion.mostrarInventario(general);
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

  //-----------------------------------------------------------------------------------------------------------
  //Método para mover entre inventarios.

  private void MoverInventarios(){
    boolean salir = false;
    while(!salir){
      Utilidades.limpiarPantalla();
      Vistas.ModuloMoverInventarios();
      int opcion = Utilidades.nextIntCustom(scanner);
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
          int qty = Utilidades.nextIntCustom(scanner);
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
          int qty = Utilidades.nextIntCustom(scanner);
          exhibicion.mover_a_bodega(productoamover,qty,bodega);
          System.out.println("Se ha movido el producto.");
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

  //-----------------------------------------------------------------------------------------------------------
  // Metodo para gestionar Devolución

  public void gestionarDevolucion(User session) {
  boolean salir = false;
  int NumDevolucion = (devueltos.consultar_cantidad_devoluciones()+1);
  String IdDevolucion = Integer.toString(NumDevolucion);

  Devolucion devolucionAct = new Devolucion(IdDevolucion, session);
  while (!salir) {
    Utilidades.limpiarPantalla();
    Vistas.ModuloGestionarDevolucion();
    int opcion = Utilidades.nextIntCustom(scanner);
    scanner.nextLine();
    switch (opcion) {

      case 1://Agregar producto al carrito de devolución
        Utilidades.limpiarPantalla();
        System.out.print("Ingrese el ID del producto:");
        String idProducto = scanner.nextLine();
            
        // Verificar si el producto existe en el inventario general
        Product producto = general.buscarProductoId(idProducto);
        if (producto == null) {
            System.out.println("El producto no existe en el inventario.");
            utilidades.esperarPresionarEnter();
            break;
        }
        System.out.print("Ingrese la cantidad de unidades que se devuelven:");
        int cantidad = Utilidades.nextIntCustom(scanner);
        scanner.nextLine();
    
        // Agregar el producto al carrito del devolucion
        devolucionAct.agregarProducto(producto, cantidad);
        System.out.println("Producto agregado al carrito de devolucion");
        utilidades.esperarPresionarEnter();
      break;

      case 2://Eliminar un producto del carrito la devolución
        Utilidades.limpiarPantalla();
        System.out.print("Ingrese el ID del producto a eliminar:");
        String idProductoEliminar = scanner.nextLine();
    
        // Verificar si el producto existe en el carrito de devolucion
        Product productoEliminar = general.buscarProductoId(idProductoEliminar);
        if (productoEliminar == null) {
          System.out.println("El producto no está en el carrito de devolucion.");
          utilidades.esperarPresionarEnter();
          break;
        }

        // Eliminar el producto del carrito de devolucion
        devolucionAct.eliminarProducto(productoEliminar);
        System.out.println("Producto eliminado del carrito de devolucion.");
        utilidades.esperarPresionarEnter();
      break;
      case 3: //Finalizar devolucion
          // Verificar si hay artículos en el carrito del pedido
          Utilidades.limpiarPantalla();
          if (devolucionAct.getCarrito().isEmpty()) {
              System.out.println("El carrito de devolucion está vacío. No se puede finalizar el pedido.");
              utilidades.esperarPresionarEnter();
              break;
          }
        // devolucionAct.finalizarDevolucion(devueltos, general);
        devueltos.agregarADevoluciones(devolucionAct);
        System.out.println("Se ha añadido a la lista de devoluciones activas.");
        utilidades.esperarPresionarEnter(); 
        break;
        case 4: // Mover devoluciones a bodega y pasar a historico
          Utilidades.limpiarPantalla();
          List <Devolucion> devoluciones= devueltos.getDevoluciones();
          System.out.println("LISTA ACTIVA DE LAS DEVOLUCIONES:");
         if (!devoluciones.isEmpty()){
          for (Devolucion devuelto:devoluciones) {
              System.out.println("----------------------------------------------------------------------------------------------------");
              System.out.println("Devolucion Realizada por: " + devuelto.getEmpleado().getNombre() + " Con ID: " + devuelto.getEmpleado().getId());
              System.out.println("Fecha: " + devuelto.getFecha());
              System.out.println("Id de la devolucion: " + devuelto.getId());
              System.out.println("Productos devueltos: ");
              devuelto.mostrarProductosEnCarrito();
              System.out.println("Total: " + devuelto.calcularTotal() + "\n\n");
            
          }
        }else{
          System.out.println("No hay productos en la lista de devoluciones.");
          utilidades.esperarPresionarEnter();
          break;
        }
        System.out.println("Estos se trasladaran a bodega.");
        boolean continuar = utilidades.preguntaContinuar();
        if(continuar){
          devueltos.moverABodega(bodega);
          devueltos.agregarAHistorico();
        }else{
          System.out.println("No se movio nada a bodega.");
          utilidades.esperarPresionarEnter();
          break;
        }
        Utilidades.limpiarPantalla();
        System.out.println("Las devoluciones se movieron a bodega.");
        utilidades.esperarPresionarEnter();
        break;
    
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

}