package Direcciones;

import tablero.Coordenada;


public class Norte extends Direccion {
	
	public Coordenada siguienteCoord(Coordenada c) {
		int fila= c.getFila()+1;
		c.setFila(fila);
		return c;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	public Direccion contraria() {
		
		return new Sur();
	}

}
