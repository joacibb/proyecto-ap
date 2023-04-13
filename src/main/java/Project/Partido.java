package Project;

public class Partido {

    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEquipo1;
    private int golesEquipo2;

	public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {
	    this.equipo1 = equipo1;
	    this.equipo2 = equipo2;
	    this.golesEquipo1 = golesEquipo1;
	    this.golesEquipo2 = golesEquipo2;
	}
	
	public Equipo getEquipo1() {
	    return equipo1;
	}
	
	public Equipo getEquipo2() {
	    return equipo2;
	}
	
	public int getGolesEquipo1() {
	    return golesEquipo1;
	}
	
	public int getGolesEquipo2() {
	    return golesEquipo2;
	}
	
	public ResultadoEnum resultado(Equipo equipo) {
			
			ResultadoEnum resultadoE = null;
			
			if(equipo.equals(equipo1) && golesEquipo1 !=golesEquipo2) {
					if(golesEquipo1>golesEquipo2) 
						resultadoE = ResultadoEnum.ganador;
					else 
						if(golesEquipo1<golesEquipo2) 
							resultadoE = ResultadoEnum.perdedor;			
			}
			else {
					if(equipo.equals(equipo2) && golesEquipo1 !=golesEquipo2) {
						if(golesEquipo1<golesEquipo2) 
							resultadoE = ResultadoEnum.ganador;
						else 
							if(golesEquipo1>golesEquipo2) 
								resultadoE = ResultadoEnum.perdedor;
							
						}
					else
						resultadoE = ResultadoEnum.empate;
				} 
				
			
					
					return resultadoE;
		}
	
		public Equipo getEquipoLocal() {
			// TODO Auto-generated method stub
		return equipo1;
	}
	
	public Equipo getEquipoVisitante() {
		// TODO Auto-generated method stub
		return equipo2;
	}

	public String getResultado() {
		// TODO Auto-generated method stub
		return this.resultado(equipo1).toString();
	}

	public int getGolesEquipoLocal() {
		// TODO Auto-generated method stub
		return golesEquipo1;
	}
	
	public int getGolesEquipoVisitante() {
		// TODO Auto-generated method stub
		return golesEquipo2;
	}


}