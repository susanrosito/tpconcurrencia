package Direcciones;

import tablero.Coordenada;


public class Sur  extends Direccion {

	public Coordenada siguienteCoord(Coordenada c) {
		int fila= c.getFila()-1;
		c.setFila(fila);
		return c;
		
	}
	public static void main(String[] args) {
		
	

	}

	public Direccion contraria() {
		
		return new Norte();
	}

 

}
