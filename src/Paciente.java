public class Paciente {

    private String cedula;
    private String nombre;
    private int edad;
    private String telefono;
    private String alerta;
    private Cuidador cuidador;

    public Paciente(String cedula, String nombre, int edad, String telefono, String alerta) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.alerta = alerta;
        this.cuidador = null;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getAlerta() {
        return alerta;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public int asignarCuidador(String cedulaCuidador, String nombreCuidador, String contactoCuidador) {
        if (cuidador != null) {
            return 0;
        }
        cuidador = new Cuidador(cedulaCuidador, nombreCuidador, contactoCuidador);
        return 1;
    }

    public String toString() {
        return "Paciente: " + nombre +
                " Cédula: " + cedula +
                " Edad: " + edad +
                " Teléfono: " + telefono +
                " Alerta: " + alerta;
    }
}
