// Idea: Nuevo atributo en inv_general, lista general de productos, los demás inventarios solo guardan los id de producto y la cantidad, el objeto se consulta en el inventario general.

// Razón: Al modificar un objeto producto esto permitirá que el cambio se vea reflejado en todos los inventarios.
// Contra: Conflicto al eliminar un producto, ya no sería posible consultar la información del id desde los demás inventarios.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Inventario_General {
    private List<Product> general;
    private Map<String, Integer> productos; 

  
    public Inventario_General() {
        general = new ArrayList<>();
        productos = new HashMap<>();
    }

    //Método para agregar un producto a la lista General
    public void agregarProducto(Product producto) {
        general.add(producto);
    }

    //Método para eliminar un producto de la lista General
    public void eliminarProducto(String Id) {
        for (Product producto : general) {
            if (producto.getId().equals(Id)) {
                general.remove(producto);
                break;
            }
        }
    }

    //Método para buscar un producto con su Id dentro de la lista General
    public Product buscarProductoId(String Id) {
        for (Product producto : general) {
            if (producto.getId().equals(Id)) {
                return producto;
            }
        }
        return null;
    }

    //Método para consultar la cantidad total de productos en el inventario
    public int consultar_cantidad_inventario() {
        int cantidadTotal = 0;
        for (int cantidad : productos.values()) {
            cantidadTotal += cantidad;
        }
        return cantidadTotal;
    }

    //Método para consultar la cantidad de unidades de un producto  
    public int consultar_cantidad_unidades(Product producto) {
        String producto_id = producto.getId();
        Integer cantidad = productos.get(producto_id);
        return (cantidad != null) ? cantidad : 0;
    }

    //Método para agregar unidades de un producto 
    public void agregar_unidades(Product producto, int qty) {
        String producto_id = producto.getId();
        Integer cantidad = productos.get(producto_id);
        if (cantidad != null) {
            productos.put(producto_id, cantidad + qty);
        }
    }

    //Método para eliminar unidades de un producto
    public void quitar_unidades(Product producto, int qty) {
        String producto_id = producto.getId();
        Integer cantidad = productos.get(producto_id);
        if (cantidad != null) {
            int nuevaCantidad = cantidad - qty;
            if (nuevaCantidad >= 0) {
                productos.put(producto_id, nuevaCantidad);
            } else { //se puede solicitar quitar más de lo que hay y no habrá error.
                productos.put(producto_id, 0);
            }
        }
    }

    //Método para Modificar los atributos de un producto dentro de la lista general
   public boolean ModificarProducto(String IdProducto, String NuevoNombre, String NuevaDescripcion, Float NuevoPrecio){
        for(Product producto: general){
            if (producto.getId().equals(IdProducto)){
                if (!NuevoNombre.isEmpty()){
                    producto.setNombre(NuevoNombre);
                }
                if(!NuevaDescripcion.isEmpty()){
                    producto.setDescripcion(NuevaDescripcion);
                }
                if(!Float.isNaN(NuevoPrecio)){
                    producto.setPrecio(NuevoPrecio);
                }
                return true;
            }
        }
        return false;

    }
}
