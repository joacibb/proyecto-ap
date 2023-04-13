import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Project.Equipo;
import Project.Partido;
import Project.Pronostico;

public class Puntajes {

    public Map<String, Integer> calcularPuntajes(List<Equipo> equipos, List<Partido> partidos,
            Map<String, List<Pronostico>> pronosticosPorPersona) {
        
        // Inicializar map para almacenar puntajes
        Map<String, Integer> puntajes = new HashMap<String, Integer>();

        for (String nombre : pronosticosPorPersona.keySet()) {
            int puntos = 0;
            List<Pronostico> pronosticos1 = pronosticosPorPersona.get(nombre);
            for (int i = 0; i < pronosticos1.size(); i++) {
                Pronostico pronostico = pronosticos1.get(i);
                for (int j = 0; j < partidos.size(); j++) {
                    Partido partido = partidos.get(j);
                    if (pronostico.getEquipoLocal().equals(partido.getEquipoLocal().getNombre()) &&
                            pronostico.getEquipoVisitante().equals(partido.getEquipoVisitante().getNombre())) {
                        if (pronostico.getResultado().equals(partido.getResultado())) {
                                          puntos += 1; // Modificación aquí
                        } else if (pronostico.getResultado().equals("gana") && partido.getGolesEquipoLocal() > partido.getGolesEquipoVisitante()) {
                            puntos += 1;
                        } else if (pronostico.getResultado().equals("empata") && partido.getGolesEquipoLocal() == partido.getGolesEquipoVisitante()) {
                            puntos += 1;
                        } else if (pronostico.getResultado().equals("pierde") && partido.getGolesEquipoVisitante() > partido.getGolesEquipoLocal()) {
                            puntos += 1;
                        }
                    }
                }
            }
           puntajes.put(nombre, puntos);
        }

        // Retornar map con puntajes
        return puntajes;
    }


}
