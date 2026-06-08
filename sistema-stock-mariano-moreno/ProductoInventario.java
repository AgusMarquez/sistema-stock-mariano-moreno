public abstract class ProductoInventario {
    private int id;
    private String nombre;
    private String unidadMedida;
    private double cantidadDisponible;
    private double stockMinimo;

    public ProductoInventario(int id, String nombre, String unidadMedida, double cantidadDisponible, double stockMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.cantidadDisponible = cantidadDisponible;
        this.stockMinimo = stockMinimo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public double getCantidadDisponible() {
        return cantidadDisponible;
    }

    public double getStockMinimo() {
        return stockMinimo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public void setCantidadDisponible(double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public void setStockMinimo(double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public void ingresarStock(double cantidad) throws StockException {
        if (cantidad <= 0) {
            throw new StockException("La cantidad a ingresar debe ser mayor a cero.");
        }

        this.cantidadDisponible += cantidad;
    }

    public void egresarStock(double cantidad) throws StockException {
        if (cantidad <= 0) {
            throw new StockException("La cantidad a egresar debe ser mayor a cero.");
        }

        if (cantidad > cantidadDisponible) {
            throw new StockException("No hay stock suficiente para realizar el egreso.");
        }

        this.cantidadDisponible -= cantidad;
    }

    public boolean tieneStockBajo() {
        return cantidadDisponible <= stockMinimo;
    }

    public String obtenerEstadoStock() {
        if (cantidadDisponible == 0) {
            return "SIN STOCK";
        }

        if (tieneStockBajo()) {
            return "STOCK BAJO";
        }

        return "STOCK NORMAL";
    }

    public abstract String getTipoProducto();

    @Override
    public String toString() {
        return "ID: " + id
                + " | Tipo: " + getTipoProducto()
                + " | Nombre: " + nombre
                + " | Cantidad: " + cantidadDisponible + " " + unidadMedida
                + " | Stock mínimo: " + stockMinimo
                + " | Estado: " + obtenerEstadoStock();
    }
}