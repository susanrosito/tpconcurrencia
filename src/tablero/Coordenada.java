package tablero;
import Direcciones.Direccion;


public class Coordenada {

	int fila;
	int columna;
	
	/**
	 * Este metodo devuelve una coordenada, que es la siguiente coordenada
	 * segun la direccion d pasada por parametro.
	 * @param Direccion d
	 * @return Coordenada
	 */
	Coordenada siguienteCoordenada(Direccion d){
		return d.siguienteCoord(this);
	}
	
	/**
	 * Este metodo se redefinio, para que una cordenada sea igual a otro
	 * si solo si:
	 * 				* el numero de filas es igual en ambas
	 * 				* el numero de columnas es igual en ambas
	 * @param Coordenada x
	 * @return boolean
	 */
	boolean equals(Coordenada x)
    {
   	 return this.fila == x.fila && this.columna == x.columna; 
    }
	


	public int getFila() {
		return fila;
	}



	public void setFila(int fila) {
		this.fila = fila;
	}



	public int getColumna() {
		return columna;
	}



	public void setColumna(int columna) {
		this.columna = columna;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
