public class Inventario_Exhibicion extends Inventario_General {

  public Inventario_Exhibicion() {
    super();
  }

  //Método para mover productos del inventario exhibición a bodega
  public void mover_a_bodega(Product producto, int qty, Inventario_Bodega inventario_bodega) {
    int cantidadEnExhibicion = consultar_cantidad_unidades(producto);
    if (cantidadEnExhibicion >= qty) {
      quitar_unidades(producto, qty);
      inventario_bodega.agregar_unidades(producto, qty);
    } else {
      System.out.println("No hay suficientes unidades en exhibición.");
    }
  }
}