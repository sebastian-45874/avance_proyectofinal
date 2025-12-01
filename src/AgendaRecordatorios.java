import java.util.*;

public class AgendaRecordatorios {

    private Queue<Recordatorio> colaRecordatorios;

    public AgendaRecordatorios() {
        colaRecordatorios = new LinkedList<Recordatorio>();
    }

    public void generarDesdeTratamientos(List<Tratamiento> tratamientosActivos) {
        colaRecordatorios.clear();

        for (Tratamiento t : tratamientosActivos) {
            String texto = "Medicamento: " + t.getMedicamento()
                    + "  Dosis: " + t.getDosis()
                    + "  Cada " + t.getCadaHoras() + " Horas";

            Recordatorio r = new Recordatorio(t.getCedulaPaciente(), texto, "PENDIENTE");

            colaRecordatorios.offer(r);
        }
    }

    public List<Recordatorio> getTodos() {
        return new ArrayList<Recordatorio>(colaRecordatorios);
    }
}
