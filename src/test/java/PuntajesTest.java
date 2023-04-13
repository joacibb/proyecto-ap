import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Project.Equipo;
import Project.Partido;
import Project.Pronostico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuntajesTest {

    private List<Equipo> equipos;
    private List<Partido> partidos;
    private Map<String, List<Pronostico>> pronosticosPorPersona;

    @Before
    public void setUp() {
    	
    	pronosticosPorPersona = new HashMap<String, List<Pronostico>>();

        // Crear lista de equipos
        equipos = new ArrayList<Equipo>();
        equipos.add(new Equipo("Equipo 1",""));
        equipos.add(new Equipo("Equipo 2",""));
        equipos.add(new Equipo("Equipo 3",""));
        equipos.add(new Equipo("Equipo 4",""));

        // Crear lista de partidos
        partidos = new ArrayList<Partido>();
        partidos.add(new Partido(equipos.get(0), equipos.get(1),1,2));
        partidos.add(new Partido(equipos.get(2), equipos.get(3),0,0));

        // Crear map de pronósticos por persona
        List<Pronostico> pronosticos1 = new ArrayList<Pronostico>();
        pronosticos1.add(new Pronostico(equipos.get(0).getNombre(),"gana",equipos.get(1).getNombre()));
        pronosticos1.add(new Pronostico(equipos.get(2).getNombre(),"empata", equipos.get(3).getNombre()));
        pronosticosPorPersona.put("Persona 1", pronosticos1);
        
        List<Pronostico> pronosticos2 = new ArrayList<Pronostico>();
        pronosticos2.add(new Pronostico(equipos.get(0).getNombre(),"pierde",equipos.get(1).getNombre()));
        pronosticos2.add(new Pronostico(equipos.get(2).getNombre(),"gana",equipos.get(3).getNombre()));
        pronosticosPorPersona.put("Persona 2", pronosticos2);
       

    }

    @Test
    public void testPuntajeDosRondasConsecutivas() {

        // Crear objeto Puntajes
        Puntajes puntajes = new Puntajes();

        // Calcular puntaje de la primera ronda
        Map<String, Integer> puntosPorPersonaPrimeraRonda = puntajes.calcularPuntajes(equipos, partidos, pronosticosPorPersona);

        // Verificar que el puntaje se haya calculado correctamente
        Assert.assertEquals(1, puntosPorPersonaPrimeraRonda.get("Persona 1").intValue());
        Assert.assertEquals(1, puntosPorPersonaPrimeraRonda.get("Persona 2").intValue());
        
        
        // Crear lista de partidos 2 ronda
        partidos = new ArrayList<Partido>();
        partidos.add(new Partido(equipos.get(0), equipos.get(1),2,1));
        partidos.add(new Partido(equipos.get(2), equipos.get(3),0,0));
        
        // Actualizar pronósticos para la segunda ronda
        // Crear map de pronósticos por persona
        List<Pronostico> pronosticos1 = new ArrayList<Pronostico>();
        pronosticos1.add(new Pronostico(equipos.get(0).getNombre(),"gana",equipos.get(1).getNombre()));
        pronosticos1.add(new Pronostico(equipos.get(2).getNombre(),"empata", equipos.get(3).getNombre()));
        pronosticosPorPersona.put("Persona 1", pronosticos1);
        
        List<Pronostico> pronosticos2 = new ArrayList<Pronostico>();
        pronosticos2.add(new Pronostico(equipos.get(0).getNombre(),"pierde",equipos.get(1).getNombre()));
        pronosticos2.add(new Pronostico(equipos.get(2).getNombre(),"gana",equipos.get(3).getNombre()));
        pronosticosPorPersona.put("Persona 2", pronosticos2);

        System.out.println(pronosticosPorPersona.size());

        // Calcular puntaje de la segunda ronda
        Map<String, Integer> puntosPorPersonaSegundaRonda = puntajes.calcularPuntajes(equipos, partidos, pronosticosPorPersona);

        
        // Verificar que el puntaje se haya calculado correctamente
        Assert.assertEquals(2, puntosPorPersonaSegundaRonda.get("Persona 1").intValue());
        Assert.assertEquals(0, puntosPorPersonaSegundaRonda.get("Persona 2").intValue());

        // Calcular puntaje total de las dos rondas
        Map<String, Integer> puntosPorPersonaDosRondas = new HashMap<String, Integer>();
        for (String nombre : puntosPorPersonaPrimeraRonda.keySet()) {
            puntosPorPersonaDosRondas.put(nombre, puntosPorPersonaPrimeraRonda.get(nombre) + puntosPorPersonaSegundaRonda.get(nombre));
        }

        // Verificar que el puntaje total se haya calculado correctamente
        Assert.assertEquals(3, puntosPorPersonaDosRondas.get("Persona 1").intValue());
        Assert.assertEquals(1, puntosPorPersonaDosRondas.get("Persona 2").intValue());
        
        System.out.println("fin test");
    }
}

