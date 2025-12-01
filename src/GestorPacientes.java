//Andres Sigcha
//Julian Gordon
import java.util.ArrayList;
import java.util.List;

//Permite agregar pacientes, buscarlos por número de cédula y obtener la lista completa
public class GestorPacientes {
//Lista que almacena todos los pacientes registrados
    private List<Paciente> listaPacientes;
//Constructor que inicializa la lista de pacientes
    public GestorPacientes() {
        listaPacientes = new ArrayList<Paciente>();
    }
//Agrega un paciente a la lista siempre que su cédula no esté registrada previamente
    public int agregarPaciente(Paciente p) {
        for (Paciente x : listaPacientes) {
            if (x.getCedula().equals(p.getCedula())) {
                return 0;
            }
        }
        listaPacientes.add(p);
        return 1;
    }
//Busca un paciente por su número de cédula
    public Paciente buscarPorCedula(String cedula) {
        for (Paciente p : listaPacientes) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }
//Obtiene la lista completa de pacientes registrados
    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }
}
