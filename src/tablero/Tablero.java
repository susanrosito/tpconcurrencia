package tablero;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

	// cuando finaliza el juego.
	// lo de ingresar comando es alto concurrente al juego.
	
	private int ancho;
	private int alto;
	private ArrayList<ArrayList<String>> matriz;

	public Tablero(int filas, int columnas) {
		this.ancho = columnas;
		this.alto = filas;
		this.setMatriz(new ArrayList<ArrayList<String>>());
		agregarColumnas();
		agregarFilas("[]");
		

	}

	public void agregarColumna(ArrayList<String> colum, int put) {
		this.getMatriz().add(put, colum);
	}

	public void agregarFila(String fila, int put) {
		List<String> current = this.getMatriz().get(put);

		for (int i = 0; i < alto; i++) {
			current.add(i, fila);
		}

	}
	public void agregarColumnas() {
		for (int i = 0; i < ancho; i++) {
			agregarColumna(new ArrayList<String>(), i);
		}
	}
	public void agregarFilas(String fila) {
		for (int i = 0; i < alto; i++) {
			agregarFila(fila, i);
		}
	}
	public void agregarElemento(Coordenada coord, String elem){
		List<String> columnaActual = this.getMatriz().get(coord.getColumna());
		columnaActual.add(coord.getFila(), elem);
	}
	
	public void sacarElemento(Coordenada coord){
		List<String> columnaActual = this.getMatriz().get(coord.getColumna());
		
		columnaActual.remove(coord.getFila());
		columnaActual.add(coord.getFila(),"[]");
	}
	
	public void imprimirTablero(){
		
	
	}
	public void imprimirColumnas(){
		for (int i = 0; i < ancho; i++) {
			ArrayList<String> col = this.getMatriz().get(i);
			imprimirFilas(col);
			System.out.println("--");
		}
	}
	public void imprimirFilas(List<String> columna){
		for (int i = 0; !columna.isEmpty(); i++) {
			
		}	
	}
	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}
	public void setMatriz(ArrayList<ArrayList<String>> matriz) {
		this.matriz = matriz;
	}
	
	public ArrayList<ArrayList<String>> getMatriz() {
		return matriz;
	}

	
public static void main(String[] args) {
	Tablero actual = new Tablero(4,4);
	Coordenada  coord = new Coordenada();
	coord.setFila(3);
	coord.setColumna(1);
	actual.agregarElemento(coord, "@");
	
}

	
}
