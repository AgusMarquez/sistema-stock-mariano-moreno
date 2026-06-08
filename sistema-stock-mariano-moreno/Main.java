import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static InventarioService inventarioService = new InventarioService();

    public static void main(String[] args) {
        inventarioService.precargarDatos();

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    agregarIngredienteGeneral();
                    break;

                case 2:
                    agregarIngredienteEsencial();
                    break;

                case 3:
                    inventarioService.listarProductos();
                    break;

                case 4:
                    buscarIngrediente();
                    break;

                case 5:
                    registrarIngresoStock();
                    break;

                case 6:
                    registrarEgresoStock();
                    break;

                case 7:
                    inventarioService.ordenarPorNombre();
                    inventarioService.listarProductos();
                    break;

                case 8:
                    generarOrdenCompra();
                    break;

                case 9:
                    inventarioService.listarOrdenesCompra();
                    break;

                case 0:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n==========================================");
        System.out.println(" SISTEMA DE GESTIÓN DE STOCK");
        System.out.println(" Escuela de Chefs Mariano Moreno");
        System.out.println("==========================================");
        System.out.println("1. Agregar ingrediente general");
        System.out.println("2. Agregar ingrediente esencial");
        System.out.println("3. Listar inventario");
        System.out.println("4. Buscar ingrediente por nombre");
        System.out.println("5. Registrar ingreso de stock");
        System.out.println("6. Registrar egreso de stock");
        System.out.println("7. Ordenar inventario por nombre");
        System.out.println("8. Generar orden de compra por stock bajo");
        System.out.println("9. Listar órdenes de compra");
        System.out.println("0. Salir");
        System.out.println("==========================================");
    }

    private static void agregarIngredienteGeneral() {
        int id = inventarioService.generarNuevoId();

        String nombre = leerTexto("Nombre del ingrediente: ");
        String unidad = leerTexto("Unidad de medida: ");
        double cantidad = leerDouble("Cantidad disponible: ");
        double stockMinimo = leerDouble("Stock mínimo: ");
        String categoria = leerTexto("Categoría: ");

        Ingrediente ingrediente = new Ingrediente(id, nombre, unidad, cantidad, stockMinimo, categoria);
        inventarioService.agregarProducto(ingrediente);

        System.out.println("Ingrediente general agregado correctamente.");
    }

    private static void agregarIngredienteEsencial() {
        int id = inventarioService.generarNuevoId();

        String nombre = leerTexto("Nombre del ingrediente esencial: ");
        String unidad = leerTexto("Unidad de medida: ");
        double cantidad = leerDouble("Cantidad disponible: ");
        double stockMinimo = leerDouble("Stock mínimo: ");
        String categoria = leerTexto("Categoría: ");
        String motivo = leerTexto("Motivo por el cual es esencial: ");

        IngredienteEsencial ingrediente = new IngredienteEsencial(id, nombre, unidad, cantidad, stockMinimo, categoria, motivo);
        inventarioService.agregarProducto(ingrediente);

        System.out.println("Ingrediente esencial agregado correctamente.");
    }

    private static void buscarIngrediente() {
        String nombre = leerTexto("Ingrese el nombre del ingrediente a buscar: ");
        inventarioService.buscarYMostrarPorNombre(nombre);
    }

    private static void registrarIngresoStock() {
        inventarioService.listarProductos();

        int id = leerEntero("Ingrese el ID del ingrediente: ");
        double cantidad = leerDouble("Ingrese la cantidad a ingresar: ");

        try {
            inventarioService.registrarIngreso(id, cantidad);
        } catch (StockException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void registrarEgresoStock() {
        inventarioService.listarProductos();

        int id = leerEntero("Ingrese el ID del ingrediente: ");
        double cantidad = leerDouble("Ingrese la cantidad a egresar: ");

        try {
            inventarioService.registrarEgreso(id, cantidad);
        } catch (StockException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void generarOrdenCompra() {
        try {
            OrdenCompra orden = inventarioService.generarOrdenCompraPorStockBajo();
            System.out.println("Orden de compra generada correctamente.");
            orden.mostrarOrden();
        } catch (StockException e) {
            System.out.println("Aviso: " + e.getMessage());
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String entrada = scanner.nextLine().replace(",", ".");
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        String texto;

        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("El campo no puede quedar vacío.");
            }

        } while (texto.isEmpty());

        return texto;
    }
}