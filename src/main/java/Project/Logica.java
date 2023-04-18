package Project;

import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logica {

    public static void main(String[] args) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "");
            Statement stmt = con.createStatement();

            // Seleccionar todos los resultados de la tabla "resultados"
            ResultSet rsResultados = stmt.executeQuery("SELECT * FROM resultados");

            List<Equipo> equipos = new ArrayList<Equipo>();
            List<Partido> partidos = new ArrayList<Partido>();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Cuantos puntos quiere que de acertar el resultado?");
            int puntaje = scanner.nextInt();

            while (rsResultados.next()) {
                String ronda = rsResultados.getString("ronda");
                String eq1 = rsResultados.getString("eq1");
                int golesEq1 = rsResultados.getInt("golesEq1");
                int golesEq2 = rsResultados.getInt("golesEq2");
                String eq2 = rsResultados.getString("eq2");

                Equipo equi1 = new Equipo(eq1,"");
                Equipo equi2 = new Equipo(eq2,"");
                equipos.add(equi1);
                equipos.add(equi2);

                Partido partido = new Partido(equi1,equi2,golesEq1,golesEq2);
                partidos.add(partido);
            }

            // Seleccionar todos los pronósticos de la tabla "pronosticos"
            ResultSet rsPronosticos = stmt.executeQuery("SELECT * FROM pronosticos");

            Map<String, List<Pronostico>> pronosticosPorPersona = new HashMap<String, List<Pronostico>>();

            while (rsPronosticos.next()) {
                String nombre = rsPronosticos.getString("nombre");
                String eq1 = rsPronosticos.getString("eq1");
                String eq2 = rsPronosticos.getString("eq2");
                boolean gana = rsPronosticos.getString("gana")!=null;
                boolean empata = rsPronosticos.getString("empata")!=null;
                boolean pierde = rsPronosticos.getString("pierde")!=null;

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

            // Calcular puntos por persona
            Map<String, Integer> puntosPorPersona = new HashMap<String, Integer>();

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
    	    
		 } catch(Exception e){ System.out.println(e);}
        }	
    }
