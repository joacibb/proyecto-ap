package Project;

public class Pronostico {
	private Partido partido;
	private Equipo equipo;
	private ResultadoEnum resultado;
	
	public Pronostico(Partido p1, Equipo equipo, ResultadoEnum resultado) {
		partido = p1;
		this.equipo =equipo;
		this.resultado=resultado;
	}
	
	public int puntos() {
		int puntos = 0;
		if(partido.resultado(equipo)==resultado) {
			puntos=1;
		}
		
		return puntos;
	}
}
