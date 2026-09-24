public class Colonizado extends Planeta {

    public Colonizado(String codigo, String nombre, long poblacion, String tipo) {
        super(codigo, nombre, poblacion, tipo);
    }

    public Colonizado() {
    }

    @Override
    public String clasificar() {

        if (getPoblacion() == 0) {
            setTipo("Muerto");

        } else if (getPoblacion() <= 100000000) {

            if (!getTipo().equalsIgnoreCase("Mortifero")
                    && !getTipo().equalsIgnoreCase("Salvaje")) {
                setTipo("Salvaje");
            }

        } else {

            if (!getTipo().equalsIgnoreCase("Colmena")
                    && !getTipo().equalsIgnoreCase("Forja")
                    && !getTipo().equalsIgnoreCase("Agricola")) {
                setTipo("Colmena");
            }
        }

        return getTipo();
    }



    @Override
    public String toString() {

        return super.toString()
                + " | Estado: Colonizado";
    }
}