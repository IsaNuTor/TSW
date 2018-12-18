package Agenda;



public class Semana {

	    // Representa una semana de lunes a viernes.
	
	    public static final int DIAS_RESERVABLES = 5;
	    
	    // Numero de la semana en el año (1-52).
	    
	    private final int numeroSemana;
	    private final Dia[] dias;

	    /**
	      * @param numeroSemana (1-52).
	     */
	    public Semana(int numeroSemana) throws DatoException
	    {
	    	if (numeroSemana < 1 || numeroSemana > 52) {
				
				throw new DatoException("La semana debe tomar valor entre 1 y 52");
			}
	        this.numeroSemana = numeroSemana;
	       
	        dias = new Dia[DIAS_RESERVABLES];
	        // TEST constructorSemana52(), NO PUEDE ESTAR EL diaDelAnio donde termina la semana
	        // corregimos poniendo el -1 pq sino con 365 se pasa de dias.
	        int diaDelAnio = (numeroSemana-1) * 7 + 1;
	        for(int dia = 0; dia < DIAS_RESERVABLES; dia++) {
	            dias[dia] = new Dia(diaDelAnio);
	            diaDelAnio++;
	        }
	    }

	    /**
	     * @param hora de la cita
	     * @param dia de la cita(1-5)
	     * @return Cita de un dia a una hora .
	     */
	    public String mostrarCita(int hora, int diaSemana)
	    {   	    	
	        return getDia(diaSemana).muestraCita(hora);
	    }

	    /**
	     * @param dia de la semana (1..DIAS_RESERVABLES).
	     * @return El Dia asociado, o null si dia de la semana
	     *         esta fuera de rango.
	     */
	    public Dia getDia(int diaSemana)
	    {
	        if(diaSemana >= 1 && diaSemana <= DIAS_RESERVABLES) {
	        	// TEST getDiaTest() ponemos -1 ya que está de 0-5
	            return dias[diaSemana-1];
	        }
	        else {
	            return null;
	        }
	    }

	    /**
	     * @return Numero de semana (0-52).
	     */
	    public int getNumeroSemana()
	    {
	        return numeroSemana;
	    }
	    
	   	    
	    public String primerHueco (int duracion) {
	    	
	    	String disponible;
	    	// dia = 1 a dia = 0 Arreglado por primerHuecoTest1
	    	for(int dia = 0; dia < DIAS_RESERVABLES; dia++) {
	    		int hueco=dias[dia].buscaSlot(duracion);
	    		if (hueco!=-1){
	    			
	    			disponible= diaSemana(dia) + " " + hueco + ":00";
	    			return disponible;
	             }
	    	}
	             
	    	return "No hay disponibilidad";
	    }
	    
	    /**
	     * @return Nombre Dia de la semana
	     */
	    public String diaSemana(int dia) {
	    
	    	String diaNombre;
	    	
	    	switch(dia) {
	    	 case 0: 
	    		 diaNombre="Lunes";
	    	     break;
	    	 case 1: 
	    		 diaNombre="Martes";
	    	     break;
	    	 case 2: 
	    		 diaNombre="Miercoles";
	    	     break;
	    	 case 3: 
	    		 diaNombre="Jueves";
	    	     break;
	    	 case 4: 
	    		 diaNombre="Viernes";
	    		// Failure, Arreglado con el test diaSemanaTest1()
	    		 break;
	    	 default: 
	    		 diaNombre="No citable";
	    	     break;
	    	 }
		    
		    return diaNombre;
	    }
	    
	    
	}
