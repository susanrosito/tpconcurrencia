package tablero;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

import Barrera.Turno;

public class Pista implements Runnable {

	List<Coordenada> obstaculos;
	Jugador jugadorBlanco; // no los inicialice para nada :S
	Jugador jugadorAzul;
	public Semaphore semaforoThreads;
	
	Turno turnoActual;

	public List<Auto> AutosAzules;
	public List<Auto> AutosBlancos;

	Coordenada tesoroBlanco;
	Coordenada tesoroAzul;
	Coordenada limite;

	public Pista() {

		this.semaforoThreads= new Semaphore(0);
		this.AutosAzules = new ArrayList<Auto>();
		this.AutosBlancos = new ArrayList<Auto>();
		this.obstaculos = new ArrayList<Coordenada>();

		this.limite = new Coordenada(80, 80); // LIMITE PRIMERO

		Random ran = new Random(); // CLASE RANDOM EN GENERAL

		this.obstaculos = this.renderObstaculos(ran);// RENDERIZO LOS OBSTACULOS

		 turnoActual = new Turno(this, 0); // SE CREA EL TURNO

		this.AutosAzules = this.renderAutos(ran, "Azul", turnoActual); // RENDERIZO LOS
																// AUTOS DEL
																// EQUIPO AZUL

		this.AutosBlancos = this.renderAutos(ran, "Blanco", turnoActual); // IDEM DEL
																// EQUIPO BLANCO

		this.tesoroAzul = this.ubicarTesoro(ran); // UBICO TESORO DEL EQUIPO
													// AZUL!!

		this.tesoroBlanco = this.ubicarTesoro(ran); // UBICO TESORO DEL EQUIPO
													// BLANCO

	}

	public Coordenada ubicarTesoro(Random ran) {
		int cx = ran.nextInt(80);
		int cy = ran.nextInt(80);
		Coordenada x = new Coordenada(cy, cx);
		while (!hayLugar(x)) {
			cx = ran.nextInt(80);
			cy = ran.nextInt(80);
			x = new Coordenada(cy, cx);
		}
		return x;
	}

	/**
	 * Este metodo lo que hace es renderizar los autos de un equipo dado.
	 * 
	 * @param ran
	 * @param nombre
	 * @param t
	 * @return
	 */

	public List<Auto> renderAutos(Random ran, String nombre, Turno t) {
		List<Auto> autos = new ArrayList<Auto>();
		int i = 0;
		int xi;
		int yi;
		Coordenada c;
		Auto a;
		while (i < 8) {
			xi = ran.nextInt(80);
			yi = ran.nextInt(80);
			c = new Coordenada(xi, yi);
			if (this.hayLugar(c)) {
				i++;
				a = new Auto(nombre, c, this, t, i);
				a.start();
				autos.add(a);
				
			}
		}
		return autos;
	}

	/**
	 * Este metodo renderiza los obstaculos de la pista.
	 * 
	 * @param ran
	 * @return
	 */
	public List<Coordenada> renderObstaculos(Random ran) {
		List<Coordenada> obstaculos = new ArrayList<Coordenada>();
		Coordenada c;
		int i = 0;
		while (i < 12) {
			int x = ran.nextInt(20);
			int y = ran.nextInt(40);
			c = new Coordenada(y, x);
			if (this.hayLugar(c)) {
				obstaculos.add(c);
				i++;
			}
		}

		return obstaculos;
	}

	public void run() {
		
		int numeroTurno =this.turnoActual.num+1;
		Turno nuevoTurno= new Turno(this, numeroTurno);
		this.repararCoches(nuevoTurno);
		this.semaforoThreads.release(2);
	}

	/**
	 * Este metodo repara los coches que han sido chocados. Es invocado al final
	 * de cada turno.
	 */
	void repararCoches(Turno t) {
		
		for (Auto a : this.AutosBlancos) {
			if (a.isChocado()) {
				a.reparar();
			}
			a.movio = false;
			a.turno=t;
			
		}
		
		for(Auto a : this.AutosAzules) {
			if (a.isChocado()) {
				a.reparar();
			}
			a.movio = false;
			a.turno=t;
			
		}
	}

	/**
	 * Revisa si en en la coordenada c pasada por parametro hay algun auto y
	 * retorna un booleano, true si lo hay y false si no hay.
	 * 
	 * @param Coordenada
	 *            c
	 * @return boolean
	 */
	synchronized boolean hayAutos(Coordenada c) {
		boolean aux = false;
		for (Auto auto : this.AutosBlancos) {
			aux = aux || auto.estaEnLaCoordenada(c);
		}
		for (Auto auto : this.AutosAzules) {
			aux = aux || auto.estaEnLaCoordenada(c);
		}

		return aux;

	}

	/**
	 * Revisa si en en la coordenada coord pasada por parametro esta fuera o
	 * dentro del limite y retorna un booleano, true si lo esta y false si no.
	 * 
	 * @param Coordenada
	 *            coord
	 * @return boolean
	 */
	boolean enLimite(Coordenada coord) {
		return coord.columna >= 0 && coord.columna <= limite.columna
				&& coord.fila >= 0 && coord.fila <= limite.fila;
	}

	/**
	 * Revisa si en en la coordenada x pasada por parametro hay algun obstaculo
	 * y retorna un booleano, true si lo hay y false si no hay.
	 * 
	 * @param Coordenada
	 *            x
	 * @return boolean
	 */
	boolean hayObstaculo(Coordenada x) {
		return this.obstaculos.contains(x);
	}

	/**
	 * Este metodo retorna un booleano representando si en esa coordenada hay
	 * lugar, es decir si esta vacia.
	 * 
	 * @param Coordenada
	 *            x
	 * @return boolean
	 */
	boolean hayLugar(Coordenada x) {
		return !hayObstaculo(x) && enLimite(x) && !hayAutos(x);
	}

	/**
	 * Este metodo retorna el auto que esta en la coordenada c, si es que hay
	 * alguno, de lo contrario, devuelve null.
	 * 
	 * @param Coordenada
	 *            c
	 * @return Auto
	 */
	Auto autoEnCoordenada(Coordenada c) {

		Auto chocado = null;
		int i = 0;
		for (Auto a : this.AutosBlancos)  {
			if (a.estaEnLaCoordenada(c)) {
				chocado = a;
				return chocado;
			}
			i++;
		}
		i = 0;
		for (Auto a : this.AutosAzules)  {
			if (a.estaEnLaCoordenada(c)) {
				chocado = a;
				return chocado;
			}
			i++;
		}
		return chocado;

	}

	/**
	 * Este metodo retorna un booleano representando si el auto pasado por
	 * parametro encontro el tesoro correspondiente a su equipo.
	 * 
	 * @param Auto
	 *            a
	 * @return booleano
	 */
	public boolean encontroElTesoro(Auto a) {
		if (a.getColor() == "Azul") {
			return a.getCoordenadaActual().equals(this.tesoroAzul);
		} else {
			return a.getCoordenadaActual().equals(this.tesoroBlanco);
		}
	}

}
