package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import Project.Pronostico;
public class Logica {

	    public static void main(String[] args) {
	    	
	    	try {
	    		
	    	JFileChooser fc = new JFileChooser();
	    	
	        String line = "";
	        String cvsSplitBy = ",";
	        List<Pronostico> Pronosticos = new ArrayList<>();
	        List<Partido> Partidos = new ArrayList<>();
	        
            System.out.println("Seleccione los resultados: ");
            
            
	    	fc.showOpenDialog(null);
	    	File resultados = fc.getSelectedFile();
	    	
	    	BufferedReader brResultados = new BufferedReader(new FileReader(resultados));
	    	
            while ((line = brResultados.readLine())!=null) {
            	
            String[] datos2 = line.split(cvsSplitBy);
            
            // Crea objeto Persona a partir de los datos de la línea
            String ronda = datos2[0];
            String eq1 = datos2[1];
            String descripcionEq1 = datos2[2];
            String golesEq1 = datos2[3];
            String golesEq2 = datos2[4];
            String eq2 = datos2[5];
            String descripcionEq2 = datos2[6];
            
            Equipo equi1 = new Equipo(eq1,descripcionEq1);
            Equipo equi2 = new Equipo(eq2,descripcionEq2);
            Partido p1 = new Partido(equi1,equi2,Integer.parseInt(golesEq1),Integer.parseInt(golesEq2));
            Partidos.set(Integer.parseInt(ronda),p1);
            System.out.println("Ronda "+ronda+" Equipo: "+eq1+" Descripcion: "+descripcionEq2+" Goles: "+golesEq1+" - "+" Equipo2: "+eq2+" Descripcion: "+descripcionEq2+" Goles: "+golesEq2);}
	        
        	fc.showOpenDialog(null);
	    	
	    	File pronosticos = fc.getSelectedFile();
	    	System.out.println("Seleccione los pronosticos: ");
	    	BufferedReader brPronosticos = new BufferedReader(new FileReader(pronosticos));

	            while ((line = brPronosticos.readLine()) != null) {

	                // Usa coma como separador
	                String[] datos = line.split(cvsSplitBy);
	                // Crea objeto Persona a partir de los datos de la línea
	                String nombre = datos[0];
	                String equipo1 = datos[1];
	                boolean gana = datos[2]!=null;
	                boolean empata = datos[3]!=null;
	                boolean pierde = datos[4]!=null;
	                String equipo2 = datos[5];
	                
	                String resultado;
	                ResultadoEnum r1;
	                if(gana) {
	                	resultado = "gana";
	                	r1 = ResultadoEnum.ganador;
	                }else {
	                	if(empata) {
	                		resultado="empata";
	                		r1 = ResultadoEnum.empate;
	                	}
	                	else {
	                		r1 = ResultadoEnum.perdedor;
	                		resultado="pierde";
	                	}
	                }
	                System.out.println("Nombre: "+nombre+" Predice: "+equipo1+" "+resultado+" contra "+equipo2);
	                }

	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	   

	}

}
