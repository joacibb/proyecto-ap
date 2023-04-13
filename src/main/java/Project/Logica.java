package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logica {

    public static void main(String[] args) {
        
        try {
            
            File resultados = new File("/Users/joacib/eclipse-workspace/ap-project/src/main/java/resultados.csv");
            File pronosticos = new File("/Users/joacib/eclipse-workspace/ap-project/src/main/java/pronosticos.csv");
            
            String line = "";
            String cvsSplitBy = ";";
            
            List<Equipo> equipos = new ArrayList<Equipo>();
            List<Partido> partidos = new ArrayList<Partido>();
            Map<String, List<Pronostico>> pronosticosPorPersona = new HashMap<String, List<Pronostico>>();
            
            BufferedReader brResultados = new BufferedReader(new FileReader(resultados));
            while ((line = brResultados.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);
                String ronda = datos[0];
                String eq1 = datos[1];
                int golesEq1 = Integer.parseInt(datos[2]);
                int golesEq2 = Integer.parseInt(datos[3]);
                String eq2 = datos[4];

                Equipo equi1 = new Equipo(eq1,"");
                Equipo equi2 = new Equipo(eq2,"");
                equipos.add(equi1);
                equipos.add(equi2);
                
                Partido partido = new Partido(equi1,equi2,golesEq1,golesEq2);
                partidos.add(partido);
            }
            brResultados.close();
            
            BufferedReader brPronosticos = new BufferedReader(new FileReader(pronosticos));
            while ((line = brPronosticos.readLine()) != null) {
                String[] datos = line.split(cvsSplitBy);
                String nombre = datos[0];
                String eq1 = datos[1];
                String eq2 = datos[5];
                boolean gana = datos[2].equals("X");
                boolean empata = datos[3].equals("X");
                boolean pierde = datos[4].equals("X");

                String resultado = "";
                if (gana) {
                    resultado = "gana";
                } else {
                    if (empata) {
                        resultado = "empata";
                    } else {
                        resultado = "pierde";
                    }
                }
                
                Pronostico pronostico = new Pronostico(eq1, resultado, eq2);
                if (!pronosticosPorPersona.containsKey(nombre)) {
                    pronosticosPorPersona.put(nombre, new ArrayList<Pronostico>());
                }
                pronosticosPorPersona.get(nombre).add(pronostico);
            }
            brPronosticos.close();
            
            Map<String, Integer> puntosPorPersona = new HashMap<String, Integer>();
            
            // Calcular puntos por persona
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
                puntosPorPersona.put(nombre, puntos);
            }

            // Imprimir tabla de posiciones
            System.out.println("Tabla de posiciones:");
            System.out.println("Nombre\tPuntos");
            for (String nombre : puntosPorPersona.keySet()) {
                System.out.println(nombre + "\t" + puntosPorPersona.get(nombre));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
