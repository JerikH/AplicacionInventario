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

    public int consultar_cantidad() {
        //Retorna la cantidad de pedidos realizados históricamente
          return pedidos.size();
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

    public List<Pedido> get_lista_Pedidos() {
        return pedidos;
    }
}
