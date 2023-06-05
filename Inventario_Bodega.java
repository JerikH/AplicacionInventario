public class Inventario_Bodega extends Inventario_General {

  public Inventario_Bodega() {
    super();
  }

  //Método para mover productos del inventario bodega a exhibición
  public void mover_a_exhibición(Product producto, int qty, Inventario_Exhibicion inventario_exhibicion) {
    int cantidadEnBodega = consultar_cantidad_unidades(producto);
    if (cantidadEnBodega >= qty) {
      quitar_unidades(producto, qty);
      inventario_exhibicion.agregar_unidades(producto, qty);
    } else {
      System.out.println("No hay suficientes unidades en la bodega.");
    }
  }
}