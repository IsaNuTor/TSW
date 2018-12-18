package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Agenda.Cita;
import Agenda.DatoException;
import Agenda.Dia;
import Agenda.Semana;

public class SemanaTest {

	@Test
	public void constructorSemana52() {
		
		try {
			Semana semana52 = new Semana(52);
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected = DatoException.class)
	public void constructorSemana0() throws DatoException {
		
		Semana semana0 = new Semana(0);
	}
	
	//a.“No existe” si no hay Cita asignada a la hora recibida como parámetro.
	@Test
	public void mostrarCitaTestNoExiste() {
		
		try {
			Semana semana52 = new Semana(52);
			Cita cita = new Cita("clase", 1);
			// Asignamos una cita a ese día.
			semana52.getDia(1).asignarCita(9, cita);
			
			// Probamos el método que nos muestra la cita con una hora en la que no hay citas.
			String resultado = semana52.mostrarCita(10, 1);
			assertEquals("No existe", resultado);
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//b."Hora no valida” si la hora indicada no es valida
	@Test
	public void mostrarCitaTestHoraNoValida() {
		
		try {
			Semana semana52 = new Semana(52);
			Cita cita = new Cita("clase", 1);
			// Asignamos una cita a ese día.
			semana52.getDia(1).asignarCita(9, cita);
			
			// Probamos el método que nos muestra la cita
			String resultado = semana52.mostrarCita(18, 1);
			assertEquals("Hora no valida", resultado);
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// c. “hora:00 Descripción de la Cita”
	@Test
	public void mostrarCitaTest() {
		
		try {
			Semana semana52 = new Semana(52);
			Cita cita = new Cita("clase", 1);
			// Asignamos una cita a ese día.
			semana52.getDia(1).asignarCita(9, cita);
			
			// Probamos el método que nos muestra la cita
			String resultado = semana52.mostrarCita(9, 1);
			assertEquals("9:00 clase", resultado);
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getDiaTest() {
		
		Semana s = null;
		try {
			s = new Semana(1);
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Dia d = s.getDia(6); // Sabado
		Dia d7 = s.getDia(7); // Domingo
		
		Dia d4 = s.getDia(4); // Jueves
		
		assertNull(d);
		assertNull(d7);
		assertEquals(4, d4.getDiaNumero());
		
	}
	
	@Test
	public void getNumeroSemana() throws DatoException {
		
		Semana s = new Semana(2);
		Semana s1 = new Semana(1);
		Semana s52 = new Semana(52);
		
		assertEquals(2, s.getNumeroSemana());
		assertEquals(1, s1.getNumeroSemana());
		assertEquals(52, s52.getNumeroSemana());
	}

	@Test
	public void primerHuecoTest1() {
				
		Semana sb = null;
		Cita cita = null;
		try {
			sb = new Semana(10);
			cita = new Cita("clase", 1);
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("Lunes 9:00",sb.primerHueco(1));
		sb.getDia(1).asignarCita(9, cita);
		assertEquals("Lunes 10:00",sb.primerHueco(1));
		sb.getDia(1).asignarCita(10, cita);
		assertEquals("Lunes 11:00",sb.primerHueco(1));
		sb.getDia(1).asignarCita(11, cita);
		assertEquals("Lunes 12:00",sb.primerHueco(1));
		sb.getDia(1).asignarCita(12, cita);
		assertEquals("Lunes 13:00",sb.primerHueco(1));
		sb.getDia(1).asignarCita(13, cita);
		assertEquals("Lunes 14:00",sb.primerHueco(1));
		sb.getDia(1).asignarCita(14, cita);
		assertEquals("Lunes 15:00",sb.primerHueco(1));
		sb.getDia(1).asignarCita(15, cita);
		assertEquals("Lunes 16:00",sb.primerHueco(1));
		sb.getDia(1).asignarCita(16, cita);
		assertEquals("Lunes 17:00",sb.primerHueco(1));
		sb.getDia(1).asignarCita(17, cita);
		assertEquals("No hay disponibilidad",sb.primerHueco(10));
		
	}
	
	@Test
	public void diaSemanaTest1() {
		Semana sb = null;
		try {
			sb = new Semana(10);
		} catch (DatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Lunes",sb.diaSemana(0));
		assertEquals("Martes",sb.diaSemana(1));
		assertEquals("Miercoles",sb.diaSemana(2));
		assertEquals("Jueves",sb.diaSemana(3));
		assertEquals("Viernes",sb.diaSemana(4));
		assertEquals("No citable",sb.diaSemana(5));
		assertEquals("No citable",sb.diaSemana(6));
		assertEquals("No citable",sb.diaSemana(483));
	}
	

}
