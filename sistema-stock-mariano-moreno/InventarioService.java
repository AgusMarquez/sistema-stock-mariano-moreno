import java.util.ArrayList;
import java.util.Comparator;

public class InventarioService {
    private ArrayList<ProductoInventario> productos;
    private ArrayList<OrdenCompra> ordenesCompra;

    public InventarioService() {
        this.productos = new ArrayList<>();
        this.ordenesCompra = new ArrayList<>();
    }

    public void precargarDatos() {
        productos.add(new IngredienteEsencial(1, "Harina 000", "kg", 20, 5, "Secos", "Ingrediente base para masas"));
        productos.add(new IngredienteEsencial(2, "Huevos", "unidad", 60, 24, "Frescos", "Ingrediente frecuente en clases prácticas"));
        productos.add(new Ingrediente(3, "Azúcar", "kg", 10, 3, "Secos"));
        productos.add(new Ingrediente(4, "Sal", "kg", 5, 1, "Secos"));
        productos.add(new IngredienteEsencial(5, "Levadura", "kg", 1, 2, "Frescos", "Ingrediente crítico para panificados"));
    }

    public void agregarProducto(ProductoInventario producto) {
        productos.add(producto);
    }

    public ArrayList<ProductoInventario> getProductos() {
        return productos;
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados en el inventario.");
            return;
        }

        System.out.println("\nINVENTARIO ACTUAL");

        for (ProductoInventario producto : productos) {
            System.out.println(producto);
        }
    }

    public ProductoInventario buscarPorId(int id) {
        for (ProductoInventario producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }

        return null;
    }

    public ProductoInventario buscarPorNombre(String nombre) {
        for (ProductoInventario producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }

        return null;
    }

    public void buscarYMostrarPorNombre(String nombre) {
        ProductoInventario producto = buscarPorNombre(nombre);

        if (producto == null) {
            System.out.println("No se encontró un ingrediente con ese nombre.");
        } else {
            System.out.println("Ingrediente encontrado:");
            System.out.println(producto);
        }
    }

    public void registrarIngreso(int id, double cantidad) throws StockException {
        ProductoInventario producto = buscarPorId(id);

        if (producto == null) {
            throw new StockException("No existe un ingrediente con ese ID.");
        }

        producto.ingresarStock(cantidad);
        System.out.println("Ingreso registrado correctamente.");
    }

    public void registrarEgreso(int id, double cantidad) throws StockException {
        ProductoInventario producto = buscarPorId(id);

        if (producto == null) {
            throw new StockException("No existe un ingrediente con ese ID.");
        }

        producto.egresarStock(cantidad);
        System.out.println("Egreso registrado correctamente.");

        if (producto.tieneStockBajo()) {
            System.out.println("ALERTA: el ingrediente quedó con stock bajo.");
        }
    }

    public void ordenarPorNombre() {
        productos.sort(Comparator.comparing(ProductoInventario::getNombre));
        System.out.println("Inventario ordenado alfabéticamente.");
    }

    public OrdenCompra generarOrdenCompraPorStockBajo() throws StockException {
        OrdenCompra orden = new OrdenCompra();

        for (ProductoInventario producto : productos) {
            if (producto.tieneStockBajo()) {
                orden.agregarProducto(producto);
            }
        }

        if (orden.getCantidadProductos() == 0) {
            throw new StockException("No hay ingredientes con stock bajo para generar una orden de compra.");
        }

        ordenesCompra.add(orden);
        return orden;
    }

    public void listarOrdenesCompra() {
        if (ordenesCompra.isEmpty()) {
            System.out.println("No hay órdenes de compra registradas.");
            return;
        }

        for (OrdenCompra orden : ordenesCompra) {
            orden.mostrarOrden();
        }
    }

    public int generarNuevoId() {
        int mayor = 0;

        for (ProductoInventario producto : productos) {
            if (producto.getId() > mayor) {
                mayor = producto.getId();
            }
        }

        return mayor + 1;
    }
}