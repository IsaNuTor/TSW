package test;

import static org.junit.Assert.*;

import org.junit.*;

import Agenda.Cita;
import Agenda.DatoException;
import Agenda.Dia;

public class DiaTest {

	// Probando a crear el día 365
	@Test
	public void excepcionNumeroDia365() {
		
		try {
			Dia dia365 = new Dia(365);
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo en constructor día 365 no incluido");
		}
	}
	
	// Probando a crear el día 1
	@Test
	public void excepcionNumeroDia1() {
		
		try {
			Dia dia365 = new Dia(1);
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo en constructor día 1 no incluido");
		}
	}
	
	// Probando a crear el día r en el cual 1 <= r <= 365.
	@Test
	public void excepcionNumeroDiaR() {
		
		try {
			Dia dia365 = new Dia((int)(Math.random()*365) + 1);
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo en constructor día entre 1 y 365");
		}
	}
	
	// Probando a crear el día 366
	@Test(expected = DatoException.class)
	public void excepcionNumeroDia366() throws DatoException {
		
		Dia dia365 = new Dia(366);
	}
	
	// Probando a crear el día 0
	@Test(expected = DatoException.class)
	public void excepcionNumeroDia0() throws DatoException {
		
		Dia dia0 = new Dia(0);
	}

	// Busca una cita que supera el máximo de horas que hay en un día para citas.
	@Test
	public void buscaSlotCitaDeMasDeMaxTest() {
		
		try {
			Dia dia = new Dia((int)(Math.random()*365) + 1);
			
			assertEquals(-1, dia.buscaSlot(15));	
			
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Busca hueco para una cita.
	@Test
	public void buscaSlotCita() {
		Dia dia = null;
		try {
			dia = new Dia((int)(Math.random()*365) + 1);
			
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(9, dia.buscaSlot(1));	
	}
	
	// Busca una cita con array de citas vacio.
	@Test
	public void buscaSlotCitaConArrayCitasVacio() {
		
		try {
			Dia dia = new Dia((int)(Math.random()*365) + 1);
			
			assertEquals(9, dia.buscaSlot(1));	
			
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void asignarCitaTest() {
		
		Dia diaPrueba = null;
		try {
			diaPrueba = new Dia(25);
			Cita cita1 = new Cita("1reunion", 1);
			Cita cita17 = new Cita("cita17", 1);
			assertTrue(diaPrueba.asignarCita(9, cita1));
			assertTrue(diaPrueba.asignarCita(17, cita17));
			assertFalse(diaPrueba.asignarCita(17, cita1));
			
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCitaTest() {
		Dia diaPrueba = null;
		try {
			diaPrueba = new Dia(25);
			Cita cita1 = new Cita("1reunion", 1);
			diaPrueba.asignarCita(9, cita1);
			
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("1reunion",diaPrueba.getCita(9).getDescripcion());
		assertEquals(1,diaPrueba.getCita(9).getDuracion());	
	}
		
	@Test
	public void validaHoraTest1() {
		Dia diaPrueba = null;

		try {
			diaPrueba = new Dia(100);			
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(diaPrueba.validaHora(-9));
		assertFalse(diaPrueba.validaHora(0));
		assertFalse(diaPrueba.validaHora(8));
		assertTrue(diaPrueba.validaHora(9));
		assertTrue(diaPrueba.validaHora(12));
		assertTrue(diaPrueba.validaHora(17));
		assertFalse(diaPrueba.validaHora(18));
	}
		
	@Test
	public void huecoLibreTest1() {
		Dia diaPrueba = null;
		Dia diaPrueba1 = null;
		Cita cita1 = new Cita("2reunion", 1);
		Cita cita2 = new Cita("3reunion", 2);
		try {
			diaPrueba = new Dia(100);
			diaPrueba1 = new Dia(110);	
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		diaPrueba.asignarCita(9, cita1);
		assertFalse(diaPrueba.huecoLibre(9, 1));
		assertTrue(diaPrueba.huecoLibre(10, 1));
		assertTrue(diaPrueba.huecoLibre(16, 1));
		assertTrue(diaPrueba.huecoLibre(17, 1));
		
		diaPrueba1.asignarCita(9, cita2);
		assertFalse(diaPrueba1.huecoLibre(9, 1));
		assertFalse(diaPrueba1.huecoLibre(10, 1));
		assertFalse(diaPrueba1.huecoLibre(9, 2));
		assertTrue(diaPrueba1.huecoLibre(11, 1));
		assertTrue(diaPrueba1.huecoLibre(11, 3));
		assertTrue(diaPrueba1.huecoLibre(17, 1));
		
		assertFalse(diaPrueba1.huecoLibre(18, 1));
		
	}
	
	

}
