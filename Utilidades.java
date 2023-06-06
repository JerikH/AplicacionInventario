import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Utilidades {
    private List<User> usuarios;
    Scanner scanner = new Scanner(System.in);
    public Utilidades(List<User> usuarios) {
        this.usuarios = usuarios;
    }

    // Método para verificar las credenciales de inicio de sesión
    public boolean verificarCredenciales(String Id, String contraseña) {
        for (User usuario : usuarios) {
            if (usuario.getId().equals(Id) && usuario.getContraseña().equals(contraseña)) {
                return true;
            }
        }
        return false;
    }

    public void agregarUsuario(User usuario) {
        usuarios.add(usuario);
    }

    public boolean quitarUsuario(String IdEliminar) {
      try{
        User usuario = buscarUsuarioId(IdEliminar);
        if (usuario != null) {
            usuarios.remove(usuario);
            return true;
        }
        return false;
      }catch(Exception e){
        return false;
      }
    }
  
    public boolean modificarUsuario(String idUsuario, String nuevoNombre, String nuevaContraseña, String nuevoCorreo, String nuevoNivelAcceso) {
      for (User usuario : usuarios) {
          if (usuario.getId().equals(idUsuario)) {
              if (!nuevoNombre.isEmpty()) {
                  usuario.setNombre(nuevoNombre);
              }
              if (!nuevaContraseña.isEmpty()) {
                  usuario.setContraseña(nuevaContraseña);
              }
              if (!nuevoCorreo.isEmpty()) {
                  usuario.setCorreo(nuevoCorreo);
              }
              if (!nuevoNivelAcceso.isEmpty()) {
                  usuario.setNivel_acceso(nuevoNivelAcceso);
              }
              return true;
          }
      }
      return false;
    }
    //Buscar usuario con Id
    public User buscarUsuarioId(String id) {
        for (User usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    //Buscar usuario con Nombre
    public User buscarUsuarioNombre(String nombre) {
        for (User usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
            }
        }
        return null;
    }

    public static void limpiarPantalla() {
      try {
          String os = System.getProperty("os.name").toLowerCase();
          if (os.contains("win")) {
              new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
          } else {
              System.out.print("\033[H\033[2J");
              System.out.flush();
          }
      } catch (Exception e) {
          System.out.println("Error al limpiar pantalla: " + e.getMessage());
      }
    }
    
    public void esperarPresionarEnter() {
      System.out.println("\nPresione ENTER para continuar...");
      scanner.nextLine();
    }

    public boolean preguntaContinuar(){
      System.out.print("¿Desea continuar? (si/no): ");
      String continuar = scanner.nextLine();
      continuar = continuar.toLowerCase();
      while(!continuar.equals("si") && !continuar.equals("no")){
        System.out.print("¿Desea continuar? (si/no): ");
        continuar = scanner.nextLine();
        continuar = continuar.toLowerCase();
      }
      return continuar.equals("si");
    }

    //Método para pedir datos de Usuario
    public String[] pedirDatosUser(String prefix){
        System.out.print(prefix + "Nombre de usuario: ");
        String Nombre = scanner.nextLine();
        System.out.print(prefix + "Contraseña: ");
        String Contraseña = scanner.nextLine();
        System.out.print(prefix + "Correo: ");
        String Correo = scanner.nextLine();
        System.out.print(prefix + "Nivel de acceso: ");
        String nivelAcceso = scanner.nextLine();
        while(!nivelAcceso.equals("1") && !nivelAcceso.equals("2")){
          System.out.print(prefix + "Nivel de acceso (1/2): ");
          nivelAcceso = scanner.nextLine();
        }
        String[] elementos = {Nombre, Contraseña, Correo, nivelAcceso};
        return elementos;
    }

    //Método para pedir datos de Producto
    public String[] pedirDatosProducto(String prefix){
        System.out.print(prefix + "Nombre de producto: ");
        String Nombre = scanner.nextLine();
        System.out.print(prefix + "Descripción de producto: ");
        String Descripcion = scanner.nextLine();
        System.out.print(prefix + "Precio: $");
        String Precio = scanner.nextLine();
        String[] elementos = {Nombre, Descripcion, Precio};
        return elementos;
    }

    public static boolean IdValido(String id) {
        String regex = "^(?!\\s*$).+";
        return id.matches(regex);
    }
  
    public static int nextIntCustom(Scanner scanner) {
        int numero = 0;
        boolean valido = false;
        
        while (!valido) {
            try {
                numero = scanner.nextInt();
                valido = true;
            } catch (InputMismatchException e) {
                scanner.next(); // Descartar la entrada incorrecta
            }
        }
        
        return numero;
    }
}