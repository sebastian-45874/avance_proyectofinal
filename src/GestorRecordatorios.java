//Andres Sigcha
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorRecordatorios {

    private List<Recordatorio> listaRecordatorios;

    public GestorRecordatorios() {
        listaRecordatorios = new ArrayList<Recordatorio>();
    }

    // Versión antigua (sin fecha/hora) por compatibilidad
    public void generarDesdeTratamientos(List<Tratamiento> tratamientosActivos) {
        listaRecordatorios.clear();
        for (Tratamiento t : tratamientosActivos) {
            String texto = "Paciente " + t.getCedulaPaciente() +
                    " Medicamento: " + t.getMedicamento() +
                    " Dosis: " + t.getDosis() +
                    " Cada " + t.getCadaHoras() + " horas";
            listaRecordatorios.add(new Recordatorio(t.getCedulaPaciente(), texto, "PENDIENTE"));
        }
    }

    // NUEVO: genera recordatorios para 7 días desde fechaInicio con la misma hora
    public void generarDesdeTratamientos(List<Tratamiento> tratamientosActivos, String fechaInicio, String hora) {
        listaRecordatorios.clear();

        LocalDate inicio = LocalDate.parse(fechaInicio);

        for (int d = 0; d < 7; d++) {
            LocalDate fechaDia = inicio.plusDays(d);
            String fechaTexto = fechaDia.toString();

            for (Tratamiento t : tratamientosActivos) {
                String texto = "Paciente " + t.getCedulaPaciente() +
                        " Medicamento: " + t.getMedicamento() +
                        " Dosis: " + t.getDosis() +
                        " Cada " + t.getCadaHoras() + " horas";
                listaRecordatorios.add(
                        new Recordatorio(t.getCedulaPaciente(), texto, "PENDIENTE", fechaTexto, hora)
                );
            }
        }
    }

    public List<Recordatorio> getTodos() {
        List<Recordatorio> copia = new ArrayList<Recordatorio>();
        copia.addAll(listaRecordatorios);
        return copia;
    }

    public List<Recordatorio> getPorCedula(String cedulaPaciente) {
        List<Recordatorio> resultado = new ArrayList<Recordatorio>();
        for (Recordatorio r : listaRecordatorios) {
            if (r.getCedulaPaciente().equals(cedulaPaciente)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    private int obtenerIndiceReal(String cedulaPaciente, int indiceFiltrado) {
        int contador = 0;
        for (int i = 0; i < listaRecordatorios.size(); i++) {
            Recordatorio r = listaRecordatorios.get(i);
            if (r.getCedulaPaciente().equals(cedulaPaciente)) {
                if (contador == indiceFiltrado) {
                    return i;
                }
                contador++;
            }
        }
        return -1;
    }

    public void eliminar(String cedulaPaciente, int indiceFiltrado) {
        int indiceReal = obtenerIndiceReal(cedulaPaciente, indiceFiltrado);
        if (indiceReal != -1) {
            listaRecordatorios.remove(indiceReal);
        }
    }

    // EDITA SOLO EL MEDICAMENTO dentro del texto
    public void editar(String cedulaPaciente, int indiceFiltrado, String nuevoMedicamento) {
        int real = obtenerIndiceReal(cedulaPaciente, indiceFiltrado);
        if (real == -1) return;

        Recordatorio r = listaRecordatorios.get(real);
        String desc = r.getDescripcion();

        String buscar1 = "Medicamento:";
        String buscar2 = " Dosis:";

        int i1 = desc.indexOf(buscar1);
        int i2 = desc.indexOf(buscar2);

        if (i1 != -1 && i2 != -1 && i2 > i1) {
            String antes = desc.substring(0, i1 + buscar1.length());
            String despues = desc.substring(i2);
            String nuevaDesc = antes + " " + nuevoMedicamento + despues;
            r.setDescripcion(nuevaDesc);
        } else {
            r.setDescripcion(nuevoMedicamento);
        }
    }

    public void registrarEstado(String cedulaPaciente, int indiceFiltrado, String nuevoEstado) {
        int real = obtenerIndiceReal(cedulaPaciente, indiceFiltrado);
        if (real != -1) {
            listaRecordatorios.get(real).setEstado(nuevoEstado);
        }
    }

    // NUEVO: editar solo la HORA
    public void editarHora(String cedulaPaciente, int indiceFiltrado, String nuevaHora) {
        int real = obtenerIndiceReal(cedulaPaciente, indiceFiltrado);
        if (real != -1) {
            listaRecordatorios.get(real).setHora(nuevaHora);
        }
    }

    // NUEVO: agenda diaria
    public List<Recordatorio> getAgendaDiaria(String cedula, String fecha) {
        List<Recordatorio> out = new ArrayList<Recordatorio>();
        for (Recordatorio r : listaRecordatorios) {
            if (r.getCedulaPaciente().equals(cedula) && r.getFecha().equals(fecha)) {
                out.add(r);
            }
        }
        return out;
    }

    // NUEVO: agenda semanal (7 días desde fechaInicio)
    public List<Recordatorio> getAgendaSemanal(String cedula, String fechaInicio) {
        List<Recordatorio> out = new ArrayList<Recordatorio>();

        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = inicio.plusDays(6);

        for (Recordatorio r : listaRecordatorios) {
            if (!r.getCedulaPaciente().equals(cedula)) continue;

            if (r.getFecha() == null || r.getFecha().isEmpty()) continue;

            LocalDate f = LocalDate.parse(r.getFecha());
            if (!f.isBefore(inicio) && !f.isAfter(fin)) {
                out.add(r);
            }
        }
        return out;
    }
}
