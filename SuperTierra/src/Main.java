import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static RegistroPlanetario registro = new RegistroPlanetario();

    public static void main(String[] args) {

        int opcion;

        do {

            mostrarMenu();

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {

                case 1:
                    registrarPlaneta();
                    break;

                case 2:
                    buscarPlaneta();
                    break;

                case 3:
                    actualizarPoblacion();
                    break;

                case 4:
                    eliminarPlaneta();
                    break;

                case 5:
                    registro.mostrarTodos();
                    break;

                case 6:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 6);

        scanner.close();
    }

    // ==========================================
    // MENU
    // ==========================================

    public static void mostrarMenu() {

        System.out.println();
        System.out.println("===== REGISTRO PLANETARIO =====");
        System.out.println("1. Registrar planeta");
        System.out.println("2. Buscar planeta");
        System.out.println("3. Actualizar poblacion");
        System.out.println("4. Eliminar planeta");
        System.out.println("5. Mostrar todos los planetas");
        System.out.println("6. Salir");
        System.out.println();
    }

    // ==========================================
    // REGISTRAR
    // ==========================================

    public static void registrarPlaneta() {

        System.out.println();
        System.out.println("===== REGISTRAR PLANETA =====");

        System.out.print("Codigo planetario: ");
        String codigo = scanner.nextLine();

        if (registro.existeCodigo(codigo)) {

            System.out.println("Error: el codigo ya existe.");
            return;
        }

        System.out.print("Nombre del planeta: ");
        String nombre = scanner.nextLine();

        long poblacion = leerPoblacion();

        System.out.println();
        System.out.println("Estado de colonizacion:");
        System.out.println("1. Colonizado");
        System.out.println("2. No Colonizado");

        int estado = leerEntero("Seleccione: ");

        while (estado != 1 && estado != 2) {

            System.out.println("Estado invalido.");
            estado = leerEntero("Seleccione 1 o 2: ");
        }

        String tipo = seleccionarTipo(poblacion);

        Planeta planeta;

        if (estado == 1) {

            planeta = new Colonizado(
                    codigo,
                    nombre,
                    poblacion,
                    tipo
            );

        } else {

            planeta = new NoColonizado(
                    codigo,
                    nombre,
                    poblacion,
                    tipo
            );
        }

        registro.registrar(planeta);

        System.out.println("Planeta registrado correctamente.");
    }

    // ==========================================
    // SELECCIONAR TIPO
    // ==========================================

    public static String seleccionarTipo(long poblacion) {

        if (poblacion == 0) {

            System.out.println("La poblacion es 0.");
            System.out.println("El planeta sera clasificado como Muerto.");

            return "Muerto";
        }

        if (poblacion <= 100000000) {

            System.out.println();
            System.out.println("Seleccione la clasificacion:");
            System.out.println("1. Mortifero");
            System.out.println("2. Salvaje");

            int opcion = leerEntero("Seleccione: ");

            while (opcion != 1 && opcion != 2) {

                System.out.println("Clasificacion invalida.");
                opcion = leerEntero("Seleccione 1 o 2: ");
            }

            if (opcion == 1) {
                return "Mortifero";
            }

            return "Salvaje";
        }

        System.out.println();
        System.out.println("Seleccione la clasificacion:");
        System.out.println("1. Colmena");
        System.out.println("2. Forja");
        System.out.println("3. Agricola");

        int opcion = leerEntero("Seleccione: ");

        while (opcion < 1 || opcion > 3) {

            System.out.println("Clasificacion invalida.");
            opcion = leerEntero("Seleccione 1, 2 o 3: ");
        }

        switch (opcion) {

            case 1:
                return "Colmena";

            case 2:
                return "Forja";

            default:
                return "Agricola";
        }
    }

    // ==========================================
    // BUSCAR
    // ==========================================

    public static void buscarPlaneta() {

        System.out.print("Ingrese el codigo del planeta: ");
        String codigo = scanner.nextLine();

        Planeta planeta = registro.buscar(codigo);

        if (planeta == null) {

            System.out.println("El planeta no existe.");

        } else {

            System.out.println();
            System.out.println("Planeta encontrado:");
            System.out.println(planeta);
        }
    }

    // ==========================================
    // ACTUALIZAR POBLACION
    // ==========================================

    public static void actualizarPoblacion() {

        System.out.print("Ingrese el codigo del planeta: ");
        String codigo = scanner.nextLine();

        Planeta planeta = registro.buscar(codigo);

        if (planeta == null) {

            System.out.println("El planeta no existe.");
            return;
        }

        System.out.println();
        System.out.println("Planeta encontrado:");
        System.out.println(planeta);

        long nuevaPoblacion = leerPoblacion();

        planeta.setPoblacion(nuevaPoblacion);

        // Recalcular clasificación
        String nuevoTipo;

        if (nuevaPoblacion == 0) {

            nuevoTipo = "Muerto";

        } else {

            nuevoTipo = seleccionarTipo(nuevaPoblacion);
        }

        planeta.setTipo(nuevoTipo);

        System.out.println();
        System.out.println("Poblacion actualizada correctamente.");
        System.out.println("Nueva informacion:");
        System.out.println(planeta);
    }

    // ==========================================
    // ELIMINAR
    // ==========================================

    public static void eliminarPlaneta() {

        System.out.print("Ingrese el codigo del planeta: ");
        String codigo = scanner.nextLine();

        if (!registro.existeCodigo(codigo)) {

            System.out.println("El planeta no existe.");
            return;
        }

        boolean eliminado = registro.eliminar(codigo);

        if (eliminado) {

            System.out.println("Planeta eliminado correctamente.");

        } else {

            System.out.println("No fue posible eliminar el planeta.");
        }
    }

    // ==========================================
    // LEER POBLACION
    // ==========================================

    public static long leerPoblacion() {

        while (true) {

            try {

                System.out.print("Ingrese la poblacion: ");

                long poblacion = Long.parseLong(scanner.nextLine());

                if (poblacion < 0) {

                    System.out.println(
                            "Error: la poblacion no puede ser negativa."
                    );

                } else {

                    return poblacion;
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Error: debe ingresar un numero valido."
                );
            }
        }
    }

    // ==========================================
    // LEER ENTERO
    // ==========================================

    public static int leerEntero(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);

                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Error: debe ingresar un numero valido."
                );
            }
        }
    }
}