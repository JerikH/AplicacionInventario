// Main.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  
  public static void main(String[] args) {

    //Declaración lista de usuarios
    List<User> usuarios = new ArrayList<>();
    Utilidades utilidades = new Utilidades(usuarios);
    Vistas vistas = new Vistas();
    Inventario_General general = new Inventario_General();
    Inventario_Vendido vendidos = new Inventario_Vendido();
    Inventario_Recibido recibidos = new Inventario_Recibido();
    Inventario_Bodega bodega = new Inventario_Bodega();
    Inventario_Exhibicion exhibicion = new Inventario_Exhibicion();
    
    Controlador controlador = new Controlador(vistas, utilidades, general, vendidos, recibidos, bodega, exhibicion);
    Scanner scanner = new Scanner(System.in);
    User session;
    //Declaración de usuario inicial para probar el programa
    usuarios.add(new User("ADMIN", "12345", "admin@gmail.com", "3", "0"));
    usuarios.add(new User("admin1", "admin1", "admin1@gmail.com", "1", "1"));
    usuarios.add(new User("emple1", "empl1", "empl1@gmail.com", "2", "2"));
    //Nota ->   Administrador=1; Empleado=2, Raiz = 3;
    //Login
    boolean apagar = false;
    while(!apagar){
      utilidades.limpiarPantalla();
      scanner.nextLine();
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
    


      

