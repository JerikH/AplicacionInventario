import java.util.ArrayList;
import java.util.List;

public class Inventario_Recibido extends Inventario_General {
    private List<Pedido> pedidos;

    public Inventario_Recibido() {
        super();
        pedidos = new ArrayList<>();
    }

    //Método para agregar un pedido a la lista del pedidos
    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }
    
    //Método para eliminar un pedido de la lista de pedidos
    public void eliminarPedido(Pedido pedido) {
        pedidos.remove(pedido); 
    }

    //Método pata buscar un pedido en la lista de pedidos
    public Pedido buscarPedido(String idPedido){
      for (Pedido pedido: pedidos){
        if (pedido.getId().equals(idPedido)){
          return pedido;
        }
      }
      return null;
    }



    /*public void mostrarMenu() { //esto se define en controlador
        Scanner scanner = new Scanner(System.in);
        boolean finalizado = false;

        while (!finalizado) {
            Vistas.ModuloRecibirPedido();
            System.out.print("Ingrese la opción deseada: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de leer el entero

            switch (opcion) {
                case 1:
                    agregarPedido();
                    break;
                case 2:
                    eliminarProducto();
                    break;
                case 3:
                    finalizarPedido();
                    finalizado = true;
                    break;
                case 4:
                    finalizado = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    // private void agregarProducto() {
    //     Scanner scanner = new Scanner(System.in);

    //     System.out.print("Ingrese el nombre del producto: ");
    //     String nombreProducto = scanner.nextLine();
    //     System.out.print("Ingrese la cantidad del producto: ");
    //     Int cantidadProducto = scanner.nextInt();
    //     Product producto = buscarProducto(nombreProducto);
    //     if (producto != null) {
    //         pedido.agregarProducto(producto, cantidadProducto);
    //         System.out.println("Producto agregado al pedido.");
    //     } else {
    //         System.out.println("El producto no existe en el inventario.");
    //     }
    // }

    private void eliminarProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del producto a eliminar: ");
        int idProducto = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de leer el entero

        pedido.quitarProducto(idProducto);
        System.out.println("Producto eliminado del pedido.");
    }

    private void finalizarPedido() {
        pedido.generarRecibo();
    }*/
}
