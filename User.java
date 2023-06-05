class User {
  private String nombre;
  private String contraseña;
  private String correo;
  private String nivel_acceso;
  private String id;

  public User(String nombre, String contraseña, String correo, String nivel_acceso, String id) {
    this.nombre = nombre;
    this.contraseña = contraseña;
    this.correo = correo;
    this.nivel_acceso = nivel_acceso;
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getContraseña() {
    return contraseña;
  }

  public void setContraseña(String contraseña) {
    this.contraseña = contraseña;
  }

  public String getNivel_acceso() {
    return nivel_acceso;
  }

  public void setNivel_acceso(String nivel_acceso) {
    this.nivel_acceso = nivel_acceso;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void clean(){
    // Destructive method
    this.id = "";
    this.nombre = "";
    this.contraseña = "";
    this.correo = "";
    this.nivel_acceso = "";
  }

}