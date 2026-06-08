import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrdenCompra {
    private static int contadorOrdenes = 1;

    private int numeroOrden;
    private LocalDateTime fecha;
    private String estado;
    private ArrayList<ProductoInventario> productosSolicitados;

    public OrdenCompra() {
        this.numeroOrden = contadorOrdenes++;
        this.fecha = LocalDateTime.now();
        this.estado = "Pendiente";
        this.productosSolicitados = new ArrayList<>();
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public String getEstado() {
        return estado;
    }

    public void agregarProducto(ProductoInventario producto) {
        productosSolicitados.add(producto);
    }

    public int getCantidadProductos() {
        return productosSolicitados.size();
    }

    public void mostrarOrden() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\nORDEN DE COMPRA Nº " + numeroOrden);
        System.out.println("Fecha: " + fecha.format(formato));
        System.out.println("Estado: " + estado);
        System.out.println("Productos solicitados:");

        for (ProductoInventario producto : productosSolicitados) {
            System.out.println("- " + producto.getNombre()
                    + " | Stock actual: " + producto.getCantidadDisponible() + " " + producto.getUnidadMedida()
                    + " | Stock mínimo: " + producto.getStockMinimo());
        }
    }
}