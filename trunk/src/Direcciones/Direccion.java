package Direcciones;

import tablero.Coordenada;

public abstract class Direccion {

	/**
	 * Este metodo delega la responsabilidad en la direccion de saber cual es la
	 * siguiente coordenada. Para ello la coordenada actual es pasada por
	 * parametro y cada direccion (Norte, Sur, Este y Oeste) calculan por medio
	 * de restar o sumar, filas o columnas según corresponda, la siguiente
	 * coordenada que es retornada.
	 * 
	 * @param Coordenada
	 *            c
	 * @return Coordenada
	 */
	public abstract Coordenada siguienteCoord(Coordenada c);

	/**
	 * Este metodo retorna la direccion contraria a la que es enviado el
	 * mensaje, a saber: *Norte -> Sur *Este -> Oeste *Oeste -> Este *Sur ->
	 * Norte
	 * 
	 * @return Direccion
	 */
	public abstract Direccion contraria();

}
