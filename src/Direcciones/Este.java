package Direcciones;

import tablero.Coordenada;

public class Este extends Direccion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Coordenada siguienteCoord(Coordenada c) {
		int columna= c.getColumna()+1;
		c.setColumna(columna);
		return c;
		
	}

	
	public Direccion contraria() {
		
		return new Oeste();
	}
	
 


}
