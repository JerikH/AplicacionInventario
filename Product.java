public class Product {
  private String nombre;
  private String descripcion;
  private String id;
  private float precio;
  
  public Product(String nombre, String descripcion, String id, float precio) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.id = id;
    this.precio = precio;
  }
  
  // Getters y setters de los atributos
  
  public String getNombre() {
    return nombre;
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public String getDescripcion() {
    return descripcion;
  }
  
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public float getPrecio() {
    return precio;
  }
  
  public void setPrecio(float precio) {
    this.precio = precio;
  }
}
