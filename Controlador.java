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
          
        case 7:// Administrar inventarios;------------------------------------------------------
          AdministrarInventarios();
          break;

        case 8: // Recibir Pedido
          
          // Esta es la estructura para mostrar la interfaz en bucle
          // boolean salir = false;
          // while (!salir) {
          //   utilidades.limpiarPantalla();
          //   vistas.ModuloRecibirPedido();
          //   System.out.print("Ingrese la acción a realizar: ");
          //   int opcion = scanner.nextInt();
          //   scanner.nextLine();
          //   switch (opcion){
          //------------lógica del switch----------
          //   }
          //}
          //break;

          
          Utilidades.limpiarPantalla();
          /*Vistas.ModuloRecibirPedido();
          System.out.print("Ingrese la opcion a realizar: ");
          opcion = scanner.nextInt();
          scanner.nextLine();
          Pedido pedido = null; //LISTO Creo que hace falta instanciar el objeto Pedido
          switch (opcion) {
             case 1: // Agregar Producto
                Utilidades.limpiarPantalla();
                //Mostramos los en pantalla los productos
                transaccion.mostrarProductosEnCarrito(); //esto muestra el carrito actual (necesario verlo para agregar uno nuevo?)
                //No se usa a 'transaccion' se usa al objeto Pedido.
              
                // Solicitar información del producto al usuario
                System.out.println("Ingrese los detalles del producto:");
                System.out.print("ID: ");
                String idProducto = scanner.nextLine();
                
                //verificar existencia del producto en la lista 'general' del inventario general
                //En caso de no existir:
                //Opcion 1:
                    //Se piden los datos para crearlo en el inventario general (y lo que esto implica)
                //Opción 2:
                    //Se pide que primero se vaya a administrar el inventario y cree el producto en el sistema
                    //break para cerrar el diálogo y volver al menu de acciones (hay que esperar el enter del usuario para que lo pueda leer).
                //No sé cuál de las dos opciones sea la más adecuada, quizás la 2 es más restrictiva pero más segura y a mi parecer, menos complicada.
              
                // En caso de existir, se trae el objeto del inventario por medio del id ingresado y se muestra al usuario para pedir confirmación.
                // Ese objeto que se trajo es el que se ingresará al carrito.

                //-------------------------Bloque opcional------------------------------------------
                //Los siguientes datos solo se pediría en caso de No existe el ID en el inventario y decidimos implementar la opción 1.
                System.out.print("Nombre: ");
                String nombreProducto = scanner.nextLine();
                //hace falta pedir descripción (puede ser vacío?)
                System.out.print("Precio: ");
                float precioProducto = scanner.nextFloat();
                System.out.print("Cantidad: ");
                int cantidadProducto = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                
                // Crear el objeto Producto con la información proporcionada
                Product producto = new Product(idProducto, nombreProducto, precioProducto); //el constructor tiene otra estructura
                // Product(String nombre, String descripcion, String id, float precio)
                //---------------------------------------------------------------------

              
                // Agregar el producto al carrito de la transacción
                transaccion.agregarProducto(producto, cantidadProducto); //se usa el objeto Pedido
                
                System.out.println("Producto agregado al carrito.");
                Utilidades.esperarPresionarEnter();
                break;
      
              case 2: // Eliminar Producto
              
                  // Verificar si hay productos en el carrito de la transacción
                  if (transaccion.consultar_Carrito().isEmpty()) { //se usa el objeto Pedido, 
                      System.out.println("El carrito está vacío. No hay productos para eliminar.");
                      Utilidades.esperarPresionarEnter();
                      break;
                  }
                  
                  // Mostrar los productos en el carrito para seleccionar uno para eliminar
                  System.out.println("Productos en el carrito:");
                  // verificar funcionamiento de Pedido.mostrarProductosEnCarrito(), se supone que ese método hace lo que se necesita aquí.
                  // Lo puede modificar si lo cree necesario (lo modificaría en Transacción)
                  int index = 1;
                  for (Map.Entry<Product, Integer> entry : transaccion.getCarrito().entrySet()) {
                      Product prod = entry.getKey();
                      int cantidad = entry.getValue();
                      System.out.println(index + ". " + prod.getNombre() + " (Cantidad: " + cantidad + ")");
                      index++;
                  }

                  //La lógica de aquí hacia adelante está bien, pero, es más adecuado pedir el id y usar
                  //Pedido.quitarProducto(id)
              
                  // Solicitar al usuario el índice del producto a eliminar
                  System.out.print("Ingrese el número del producto a eliminar: "); //pedir id (String)
                  int opcionEliminar = scanner.nextInt();
                  scanner.nextLine();

                  // verificar presenciad del id en el carrito
                  // usar Pedido.quitarProducto(id)
                  
                  // Obtener el producto seleccionado para eliminar
                  int contador = 1;
                  Product productoEliminar = null;
                  for (Map.Entry<Product, Integer> entry : transaccion.consultar_Carrito().entrySet()) {
                      if (contador == opcionEliminar) {
                          productoEliminar = entry.getKey();
                          break;
                      }
                      contador++;
                  }
                  
                  // Verificar si se encontró el producto para eliminar
                  if (productoEliminar != null) {
                      // Eliminar el producto del carrito de la transacción
                      transaccion.eliminarProducto(productoEliminar); //el metodo es quitarProducto y recibe el id como parámetro
                      System.out.println("Producto eliminado del carrito.");
                  } else {
                      System.out.println("No se encontró el producto seleccionado.");
                  }
                  
                  Utilidades.esperarPresionarEnter();
                  break;
      
              case 3: // Finalizar Pedido
                  // Llamar al método finalizarPedido de la clase Pedido
                  if (transaccion instanceof Pedido) { //al inicio se instancia el pedido, por lo que esto no es necesario
                      Pedido pedido = (Pedido) transaccion; //pedido existe desde el inicio de case 8
                      pedido.finalizarPedido();
                      //A continuación se debe usar la instancia de recibidos y hacer
                      //recibidos.agregarPedido(pedido);
                      transacciones.add(pedido); // Agregar el pedido a la lista de transacciones
                      System.out.println("Pedido finalizado y registrado.");
                  } else {
                      System.out.println("No se puede finalizar el pedido. La transacción no es un Pedido.");
                  }
                  
                  Utilidades.esperarPresionarEnter();
                  break;
      
              case 4: // Salir
                  salir = true;
                  break;
      
              default:
                  System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                  Utilidades.esperarPresionarEnter();
                  break;
          }*/
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
          Utilidades.esperarPresionarEnter();
          break;
        }
      }
    }
  }

  //Método para ver inventarios.
  private void VerInventarios(){
    boolean salir = false;
    while(!salir){
      Utilidades.limpiarPantalla();
      Vistas.ModuloVerInvenarios();
      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch(opcion){
        case 1:{ // Ver Inventario Completo
          Utilidades.limpiarPantalla();/* 
          System.out.println("Inventario Completo:");
          general.mostrarInventarioCompleto();//Crear método
          Utilidades.esperarPresionarEnter();*/
          break;
        }
      
        case 2:{ // Ver Inventario en Bodega
          Utilidades.limpiarPantalla();/* 
          System.out.println("Inventario en Bodega:");
          bodega.mostrarInventario();//Crear método
          Utilidades.esperarPresionarEnter();*/
          break;
        }
      
        case 3:{ // Ver Inventario en Exhibición
          Utilidades.limpiarPantalla();/*
          System.out.println("Inventario en Exhibición:");
          exhibicion.mostrarInventario();//Crear método
          Utilidades.esperarPresionarEnter();*/
          break;
        }

        case 4:{
          salir = true;
          break;
        }
      
        default:{
          System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
          Utilidades.esperarPresionarEnter();
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
            Utilidades.esperarPresionarEnter();
            break;
          }
          System.out.println("Cantidad: ");
          int qty = scanner.nextInt();
          bodega.mover_a_exhibición(productoamover,qty,exhibicion);
          System.out.println("Se ha movido el producto.");
          Utilidades.esperarPresionarEnter();
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
            Utilidades.esperarPresionarEnter();
            break;
          }
          System.out.println("Cantidad: ");
          int qty = scanner.nextInt();
          exhibicion.mover_a_bodega(productoamover,qty,bodega);
          System.out.println("Se ha movido el producto.");
          Utilidades.esperarPresionarEnter();
          break;
        }
        case 3:{
          break;
        }
        case 4:{
          salir = true;
          break;
        }
        default:{
          System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
          Utilidades.esperarPresionarEnter();
          break;
        }
      }
    }
  }
}