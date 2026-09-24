public abstract class Planeta implements Clasificable {

    private String codigo;
    private String nombre;
    private long poblacion;
    private String tipo;

    public Planeta() {
    }

    public Planeta(String codigo, String nombre, long poblacion, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public long getPoblacion() {
        return poblacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPoblacion(long poblacion) {
        this.poblacion = poblacion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Método que determina qué tipos son válidos
    // según la población.

    public boolean tipoValido() {

        if (poblacion == 0) {
            return tipo.equalsIgnoreCase("Muerto");
        }

        if (poblacion >= 1 && poblacion <= 100000000) {
            return tipo.equalsIgnoreCase("Mortifero")
                    || tipo.equalsIgnoreCase("Salvaje");
        }

        if (poblacion > 100000000) {
            return tipo.equalsIgnoreCase("Colmena")
                    || tipo.equalsIgnoreCase("Forja")
                    || tipo.equalsIgnoreCase("Agricola");
        }

        return false;
    }

    @Override
    public String toString() {

        return "Codigo: " + codigo
                + " | Nombre: " + nombre
                + " | Poblacion: " + poblacion
                + " | Tipo: " + tipo;
    }

    // Cada clase hija debe implementar clasificar()
    @Override
    public abstract String clasificar();
}