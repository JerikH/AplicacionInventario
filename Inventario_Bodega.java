import java.util.HashMap;
import java.util.Map;

public class Inventario_Bodega extends Inventario_General {

  public Inventario_Bodega() {
    super();
  }

  public void mover_a_exhibición(Product producto, int qty) {
    int cantidadEnBodega = consultar_cantidad_unidades(producto);
    if (cantidadEnBodega >= qty) {
      quitar_unidades(producto, qty);
      Inventario_Exhibicion.agregar_unidades(producto, qty);
    } else {
      System.out.println("No hay suficientes unidades en la bodega.");
    }
  }
}