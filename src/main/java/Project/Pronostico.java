package Project;

public class Pronostico {

    private String equipo1;
    private String equipo2;
    private String resultado;

    public Pronostico(String equipo1, String resultado, String equipo2) {
        this.equipo1 = equipo1;
        this.resultado = resultado;
        this.equipo2 = equipo2;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public String getResultado() {
        return resultado;
    }

	public String getEquipoLocal() {
		// TODO Auto-generated method stub
		return equipo1;
	}

	public Object getEquipoVisitante() {
		// TODO Auto-generated method stub
		return equipo2;
	}
}