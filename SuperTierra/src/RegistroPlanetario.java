import java.util.ArrayList;

public class RegistroPlanetario {

    private ArrayList<Planeta> planetas;

    public RegistroPlanetario() {
        planetas = new ArrayList<>();
    }

    // =========================
    // VALIDAR EXISTENCIA
    // =========================

    public boolean existeCodigo(String codigo) {

        for (Planeta planeta : planetas) {

            if (planeta.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }

        return false;
    }

    // =========================
    // BUSCAR
    // =========================

    public Planeta buscar(String codigo) {

        for (Planeta planeta : planetas) {

            if (planeta.getCodigo().equalsIgnoreCase(codigo)) {
                return planeta;
            }
        }

        return null;
    }

    // =========================
    // REGISTRAR
    // =========================

    public boolean registrar(Planeta planeta) {

        if (existeCodigo(planeta.getCodigo())) {
            return false;
        }

        planetas.add(planeta);

        return true;
    }

    // =========================
    // ELIMINAR
    // =========================

    public boolean eliminar(String codigo) {

        Planeta planeta = buscar(codigo);

        if (planeta == null) {
            return false;
        }

        planetas.remove(planeta);

        return true;
    }

    // =========================
    // ACTUALIZAR POBLACIÓN
    // =========================

    public boolean actualizarPoblacion(String codigo, long nuevaPoblacion) {

        Planeta planeta = buscar(codigo);

        if (planeta == null) {
            return false;
        }

        planeta.setPoblacion(nuevaPoblacion);

        return true;
    }

    // =========================
    // MOSTRAR TODOS
    // =========================

    public void mostrarTodos() {

        if (planetas.isEmpty()) {
            System.out.println("No existen planetas registrados.");
            return;
        }

        for (Planeta planeta : planetas) {
            System.out.println(planeta);
        }
    }
}