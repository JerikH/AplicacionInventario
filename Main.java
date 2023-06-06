import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class Main {
  
  public static void main(String[] args) {

    //Declaración lista de usuarios
    List<User> usuarios = new ArrayList<>();
    Utilidades utilidades = new Utilidades(usuarios);
    // Vistas vistas = new Vistas();
    Inventario_General general = new Inventario_General();
    Inventario_Vendido vendidos = new Inventario_Vendido();
    Inventario_Recibido recibidos = new Inventario_Recibido();
    Inventario_Bodega bodega = new Inventario_Bodega();
    Inventario_Exhibicion exhibicion = new Inventario_Exhibicion();
    Inventario_Devuelto devueltos = new Inventario_Devuelto();
    
    Controlador controlador = new Controlador(utilidades, general, vendidos, recibidos, bodega, exhibicion, usuarios, devueltos);
    Scanner scanner = new Scanner(System.in);
    User session;
    //Declaración de usuario inicial para probar el programa
    usuarios.add(new User("ADMIN", "12345", "admin@gmail.com", "3", "0"));
    usuarios.add(new User("admin1", "admin1", "admin1@gmail.com", "1", "1"));
    usuarios.add(new User("emple1", "empl1", "empl1@gmail.com", "2", "2"));
    //Nota ->   Administrador=1; Empleado=2, Raiz = 3;
    //Declaración de productos iniciales para probar el programa
    Product producto1 = new Product("Cuaderno Norma", "Cuaderno de 100 hojas cuadriculado", "1", 4500);
    general.agregarProducto(producto1);
    bodega.agregarProductoMap("1", 10);
    exhibicion.agregarProductoMap("1", 20);
    Product producto2 = new Product("Lapiz B8", "Mina de Grafito", "2", 1500);
    general.agregarProducto(producto2);
    bodega.agregarProductoMap("2", 2);
    exhibicion.agregarProductoMap("2", 5);
    Product producto3 = new Product("Memoria USB", "64GB", "3", 25000);
    general.agregarProducto(producto3);
    bodega.agregarProductoMap("3", 50);
    exhibicion.agregarProductoMap("3", 60);
    Product producto4 = new Product("Margarita", "Limón", "4", 3500);
    general.agregarProducto(producto4);
    bodega.agregarProductoMap("4", 10);
    exhibicion.agregarProductoMap("4", 15);
    Product producto5 = new Product("Margarita", "Pollo", "5", 3500);
    general.agregarProducto(producto5);
    bodega.agregarProductoMap("5", 8);
    exhibicion.agregarProductoMap("5", 18);

    //declaración de ventas
    Venta venta1 = new Venta("1", usuarios.get(0));
    venta1.agregarProducto(producto1, 2);
    venta1.agregarProducto(producto2, 1);
    venta1.setFecha(new Date());
    vendidos.agregarVenta(venta1);
    Venta venta2 = new Venta("2", usuarios.get(1));
    venta2.agregarProducto(producto3, 1);
    venta2.agregarProducto(producto4, 2);
    venta2.setFecha(new Date());
    vendidos.agregarVenta(venta2);

    //declaración de pedidos
    Pedido pedido1 = new Pedido("1", usuarios.get(0), "Papelería mundial");
    pedido1.agregarProducto(producto1, 4);
    pedido1.agregarProducto(producto2, 5);
    pedido1.setFecha(new Date());
    recibidos.agregarPedido(pedido1);
    Pedido pedido2 = new Pedido("2", usuarios.get(1), "Biblioteca municipal");
    pedido2.agregarProducto(producto3, 3);
    pedido2.agregarProducto(producto5, 1);
    pedido2.setFecha(new Date());
    recibidos.agregarPedido(pedido2);

    //declaración de devoluciones
    Devolucion devolucion1 = new Devolucion("1", usuarios.get(0));
    devolucion1.agregarProducto(producto1, 1);
    devolucion1.agregarProducto(producto2, 2);
    devolucion1.setFecha(new Date());
    devueltos.agregarADevoluciones(devolucion1);
    Devolucion devolucion2 = new Devolucion("2", usuarios.get(1));
    devolucion2.agregarProducto(producto3, 4);
    devolucion2.agregarProducto(producto4, 3);
    devolucion2.setFecha(new Date());
    devueltos.agregarADevoluciones(devolucion2);

    devueltos.agregarAHistorico();

    Devolucion devolucion3 = new Devolucion("3", usuarios.get(0));
    devolucion3.agregarProducto(producto1, 2);
    devolucion3.agregarProducto(producto2, 4);
    devolucion3.setFecha(new Date());
    devueltos.agregarADevoluciones(devolucion3);
    Devolucion devolucion4 = new Devolucion("2", usuarios.get(2));
    devolucion4.agregarProducto(producto5, 1);
    devolucion4.agregarProducto(producto4, 5);
    devolucion4.setFecha(new Date());
    devueltos.agregarADevoluciones(devolucion4);




    //Login
    boolean apagar = false;
    while(!apagar){
      Utilidades.limpiarPantalla();
      System.out.print("----------LOGIN----------\n");
      System.out.print("Id del usuario: ");
      String IdUsuario = scanner.nextLine();
      System.out.print("Contraseña: ");
      String contraseña = scanner.nextLine();
      boolean credencialesValidas = utilidades.verificarCredenciales(IdUsuario, contraseña);
      if (credencialesValidas) {
        session = utilidades.buscarUsuarioId(IdUsuario);
        String nivel_acceso = session.getNivel_acceso();
        switch(nivel_acceso){
          case "3":
          case "1":{//Opciones para administrador---------------------------------------------
            apagar = controlador.Admin(session);
            break;
          }
          case "2":{
            apagar = controlador.Empleado(session);
            break;
          }
        }
        session.clean();
        IdUsuario = "";
        contraseña = "";
        credencialesValidas = false;
      }else{
          System.out.println("Credenciales inválidas. Por favor, intenta nuevamente.");
          utilidades.esperarPresionarEnter();
      }
    }
    scanner.close();   
  }
}
    


      

