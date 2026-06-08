public class IngredienteEsencial extends Ingrediente {
    private String motivoEsencial;

    public IngredienteEsencial(int id, String nombre, String unidadMedida, double cantidadDisponible, double stockMinimo, String categoria, String motivoEsencial) {
        super(id, nombre, unidadMedida, cantidadDisponible, stockMinimo, categoria);
        this.motivoEsencial = motivoEsencial;
    }

    public String getMotivoEsencial() {
        return motivoEsencial;
    }

    public void setMotivoEsencial(String motivoEsencial) {
        this.motivoEsencial = motivoEsencial;
    }

    @Override
    public String getTipoProducto() {
        return "Ingrediente esencial";
    }

    @Override
    public String toString() {
        return super.toString() + " | Motivo: " + motivoEsencial;
    }
}