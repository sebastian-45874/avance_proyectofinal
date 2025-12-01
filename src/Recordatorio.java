//Joan Chiscuet
public class Recordatorio {
    // Vinicio Ruiz

    private String cedulaPaciente;
    private String descripcion;
    private String estado;
    private String fecha;
    private String hora;

    // Constructor original (por compatibilidad)
    public Recordatorio(String cedulaPaciente, String descripcion, String estado) {
        this(cedulaPaciente, descripcion, estado, "", "");
    }

    // Constructor nuevo con fecha y hora
    public Recordatorio(String cedulaPaciente, String descripcion, String estado, String fecha, String hora) {
        this.cedulaPaciente = cedulaPaciente;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String toString() {
        String texto = descripcion + " Estado: " + estado;
        if (fecha != null && !fecha.isEmpty()) {
            texto += " Fecha: " + fecha;
        }
        if (hora != null && !hora.isEmpty()) {
            texto += " Hora: " + hora;
        }
        return texto;
    }
}
