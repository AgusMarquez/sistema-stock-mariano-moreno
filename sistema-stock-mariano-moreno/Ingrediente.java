public class Ingrediente extends ProductoInventario {
    private String categoria;

    public Ingrediente(int id, String nombre, String unidadMedida, double cantidadDisponible, double stockMinimo, String categoria) {
        super(id, nombre, unidadMedida, cantidadDisponible, stockMinimo);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String getTipoProducto() {
        return "Ingrediente general";
    }

    @Override
    public String toString() {
        return super.toString() + " | Categoría: " + categoria;
    }
}