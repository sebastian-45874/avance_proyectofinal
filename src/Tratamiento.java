//Joan Chiscuet
public class Tratamiento {
    // Vinicio Ruiz

    private String cedulaPaciente;
    private String medicamento;
    private String dosis;
    private int cadaHoras;
    private int activo;

    public Tratamiento(String cedulaPaciente, String medicamento, String dosis, int cadaHoras, int activo) {
        this.cedulaPaciente = cedulaPaciente;
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.cadaHoras = cadaHoras;
        this.activo = activo;
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public String getDosis() {
        return dosis;
    }

    public int getCadaHoras() {
        return cadaHoras;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String toString() {
        String estado = (activo == 1) ? "ACTIVO" : "INACTIVO";
        return "Medicamento: " + medicamento +
                " Dosis: " + dosis +
                " Cada: " + cadaHoras + " Horas" +
                " Estado: " + estado;
    }
}
