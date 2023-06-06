import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Inventario_General {
    protected List<Product> general;
    protected Map<String, Integer> productos; 

  
    public Inventario_General() {
        general = new ArrayList<>();
        productos = new HashMap<>();
    }

    //Método para agregra un producto a la lista productos;
    public void agregarProductoMap(String Id, int Cant){
        productos.put(Id,Cant);
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

    //Método para mostrar inventario completo
    public void mostrarInventarioCompleto() {
        if (general.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            for (Product producto : general) {
                System.out.println("Id: " + producto.getId());
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Descripción: " + producto.getDescripcion());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("------------------");
            }
        }
    }

    //Método para mostrar los productos existentes en el inventario.
    public void mostrarInventario(Inventario_General general) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            for (Map.Entry<String, Integer> entry : productos.entrySet()) {
                String productId = entry.getKey();
                int cantidad = entry.getValue();
    
                // Obtener el producto correspondiente al Id
                Product producto = general.buscarProductoId(productId);
    
                if (producto != null) {
                    System.out.println("Id: " + productId);
                    System.out.println("Nombre: " + producto.getNombre());
                    System.out.println("Descripción: " + producto.getDescripcion());
                    System.out.println("Precio: " + producto.getPrecio());
                    System.out.println("Cantidad: " + cantidad);
                    System.out.println("------------------");
                } else {
                    System.out.println("No se encontró el producto con Id: " + productId);
                    System.out.println("Cantidad: " + cantidad);
                }
            }
        }
    }
    
    
}




