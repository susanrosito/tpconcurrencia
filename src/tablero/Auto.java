package tablero;

import Direcciones.*;

public class Auto extends Thread {

	Coordenada coordenadaActual;
	Direccion direccionActual;
	String color;
	boolean chocado;

	Auto(String color, Coordenada coord) {

		this.chocado = false;
		this.color = color;
		this.coordenadaActual = coord;
		this.direccionActual = new Norte();

	}

	/**
	 * Este metodo retorna un booleano que indica si esta la coordenada pasada
	 * por parametro.
	 * 
	 * @param Coordenada
	 *            coord
	 * @return boolean
	 */
	boolean estaEnLaCoordenada(Coordenada coord) {
		return this.getCoordenadaActual().equals(coord);
	}

	/**
	 * Este metodo representa que el auto esta chocado, poniendo su variable
	 * "chocado" en true.
	 */
	public void chocar() {
		this.chocado = true;
	}

	/**
	 * Este metodo representa la reparacion de un auto que fue chocado, poniendo
	 * su variable "chocado" en false.
	 */
	public void reparar() {
		this.chocado = false;
	}

	/**
	 * Este metodo cambia la direccion actual del coche por la Direccion d
	 * pasada por parametro.
	 * 
	 * @param Direccion
	 *            d
	 */
	public void cambiarDir(Direccion d) {
		this.setDireccionActual(d);
	}

	public boolean isChocado() {
		return chocado;
	}

	public void setChocado(boolean chocado) {
		this.chocado = chocado;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Direccion getDireccionActual() {
		return direccionActual;
	}

	public void setDireccionActual(Direccion direccionActual) {
		this.direccionActual = direccionActual;
	}

	public Coordenada getCoordenadaActual() {
		return coordenadaActual;
	}

	public void setCoordenadaActual(Coordenada coordenadaActual) {
		this.coordenadaActual = coordenadaActual;
	}

}
