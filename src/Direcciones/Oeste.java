package Direcciones;

import tablero.Coordenada;

public class Oeste extends Direccion {

	public Coordenada siguienteCoord(Coordenada c) {
		int columna = c.getColumna() - 1;
		c.setColumna(columna);
		return c;

	}

	public Direccion contraria() {

		return new Este();
	}

}
