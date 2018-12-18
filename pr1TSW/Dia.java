package Agenda;

import static org.junit.Assert.assertEquals;

public class Dia {

	    public static final int PRIMERA_CITA = 9;
	    public static final int ULTIMA_CITA = 17;
	    public static final int MAX_CITAS_POR_DIA =	ULTIMA_CITA - PRIMERA_CITA + 1;
	    

	    private int diaNumero;
	    private Cita[] citas;

	    /**
	     * @param diaNumero numero del dia del año (1-365), si es correcto
	     */
	    public Dia(int diaNumero) throws DatoException{
	    
	    	// TEST excepcionNumeroDia0().
	    	// TEST excepcionNumeroDia365().
	    	if (diaNumero <= 0 || diaNumero > 365){
	    		
	    		throw new DatoException("La semana debe tomar valor entre 1 y 365");
	    	}
	        this.diaNumero = diaNumero;
	        citas = new Cita[MAX_CITAS_POR_DIA];
	    }

	    /**
	     * Busca hueco para una cita
	     * @param cita 
	     * @return Hora más temprana para adjudicar la cita
	     *         Devuelve -1 si no es posible encontrar hueco
	     */ 
	    public int buscaSlot(int duracion)
	    
	    {   int hora;  
	    	int slot = 0, sigSlot;
	    	
	    	// TEST buscaSlotCitaDeMasDeMaxTest(), la duración supera el maximo de horas para citas.
	    	if(duracion > MAX_CITAS_POR_DIA) {
	    		
	    		return -1;
	    	}
	    	
	    	while (slot < MAX_CITAS_POR_DIA){ 
	    		if(citas[slot] == null) {
	            	hora = slot;
	                if(duracion == 1) {
	                	// TEST  Busca hueco para una cita.
	                      return PRIMERA_CITA + hora;
	                }
	                else {
	        
	                	int numSlots = duracion;
	                	// Test citaNoCabeEntreCitasExistentes
	                	sigSlot= slot;
	                	while (numSlots > 0 && sigSlot < MAX_CITAS_POR_DIA && citas[sigSlot] == null )
	                     { numSlots--; 
	                     	// Test citaNoCabeEntreCitasExistentes
	                     	sigSlot++;
	                     }
	                	
	                    if(numSlots == 0) {
	                    	// TEST  Busca hueco para una cita.
	                        return PRIMERA_CITA + hora;
	                    }
	                    else {
	                    	slot= sigSlot;
	                    }
	                }
	             } 
	    	 slot++;
	        }
	    	
	    	return -1;
	    }
	        
	        

	    /**
	     * asignarCita.
	     * @param Hora Inicio
	     * @param cita
	     * @return true si se asigno la cita,
	     *         false en otro caso.
	     */
	    public boolean asignarCita(int hora, Cita cita)
	    {
	        if(validaHora(hora) && huecoLibre(hora, cita.getDuracion())) {
	            int horaIni = hora - PRIMERA_CITA;
	            // ERROR TEST asignarCitaTest(), cambio this.diaNumero por horaIni
	            if(citas[horaIni] == null) {
	                int duracion = cita.getDuracion();
	                for(int i = 0; i < duracion; i++) {
	                	// TEST asignacionSlotHorasSolapadas
	                	if(citas[horaIni + i] == null)
	                		citas[horaIni + i] = cita;
	                	else
	                		return false;
	                }
	                return true;
	            }
	            else {
	                return false;
	            }
	        }
	        else {
	            return false;
	        }
	    }
	    
	    /**
	     * @param hora de inicio de cita
	     * @return La cita asignada a esa hora. Devuelve null si la hora no 
	     *         es valida o no existe cita a la hora indicada
	     */
	    public Cita getCita(int hora)
	    {
	        if(validaHora(hora)) {
	            return citas[hora - PRIMERA_CITA];
	        }
	        else {
	            return null;
	        }
	    }

	    /**
	     * String que devuelve la descripción de la cita solicitada .
	     */
	    
	    public String muestraCita(int hora){      
	    	
	    	if (validaHora(hora)) { 
	    	    int horaIni = hora - PRIMERA_CITA; 
	            if(citas[horaIni] != null) {
	                return hora + ":00 " + citas[horaIni].getDescripcion();
	            }
	    	
	            else {
	                return "No existe";
	            }
	        }
	    	else {
	    		// 	TEST mostrarCitaTestHoraNoValida(), pone hora valida, ponemos no valida
                return "Hora no valida";
            }
	    }

	    /**
	     * @return Numero de dia dentro del año (1-366).
	     */
	    public int getDiaNumero()
	    {
	        return diaNumero;
	    }
	    
	    /**
	     * @return true si la hora esta en el intervalo valido
	     *         false en otro caso.
	     */
	    public boolean validaHora(int hora)
	    {
	        return hora >= PRIMERA_CITA && hora <= ULTIMA_CITA;
	    }
	    
	    public boolean huecoLibre(int hora, int duracion) {
	    	
	    	// huecoLibreTest1() hay que tener en cuenta que la hora esté dentro del rango
	    	// porque sino intenta acceder a una posición que se sale del array al ver si es null.
	    	if(validaHora(hora)) {
		    	int horaIni = hora - PRIMERA_CITA;
		  
		            if(citas[horaIni] == null) {
		            	duracion--;
			            for(int slot=horaIni+1; duracion > 0 && slot < MAX_CITAS_POR_DIA ; slot++) {
			          	  if (citas[slot] == null) {duracion--;}
			          	  	}
		   
		              return true;
		            }
		            else {return false;}
	    	}
	    	
	    	return false;
	    } 
	    
	    
	    
}
