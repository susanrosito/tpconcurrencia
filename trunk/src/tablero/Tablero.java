package tablero;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
	
	private int ancho;
	private int alto;
	private ArrayList<ArrayList<String>> matriz;
	/**
	 * Esta es la clase Tablero, la que nos va a ayudar a poder mostrar nuestro juego, por Pantalla.
	 * Se inicializa con la cant de filas y columnas.
	 * 
	 * @param filas
	 * @param columnas
	 */
	public Tablero(int filas, int columnas) {
		this.ancho = columnas;
		this.alto = filas;
		this.setMatriz(new ArrayList<ArrayList<String>>()); // aca creo la matriz.
		agregarColumnas(); // aca creo las columnas.
		agregarFilas("[]"); // finalmente tambien creo las filas.
		

	}
	/**
	 * Este metodo agrega una columna, este metodo solamente se usa para inicializarla.
	 * Recibe una lista de String y un int que es el nro de columna a agregar.
	 * @param colum ArrayList<String>
	 * @param put int
	 */
	public void agregarColumna(ArrayList<String> colum, int put) {
		this.getMatriz().add(put, colum);
	}
	/**
	 * Este metodo agrega una fila a la columna indicada por el nro put, idem del punto anterior. 
	 * @param fila String 
	 * @param put int
	 */
	public void agregarFila(String fila, int put) {
		List<String> current = this.getMatriz().get(put);

		for (int i = 0; i < alto; i++) {
			current.add(i, fila);
		}

	}
	/**
	 * Este metodo agrega varias columnas,respetanto la cantidad que se pasa por el constructor del Tablero. 
	 */
	public void agregarColumnas() {
		for (int i = 0; i < ancho; i++) {
			agregarColumna(new ArrayList<String>(), i);
		}
	}
	/**
	 * Este metodo agrega varias filas,respetanto la cantidad que se pasa por el constructor del Tablero. 
	 */
	public void agregarFilas(String fila) {
		for (int i = 0; i < alto; i++) {
			agregarFila(fila, i);
		}
	}
	/**
	 * Este metodo agrega un elemento a la matriz y lo ubica en la coordenada que le pasan. 
	 */
	public void agregarElemento(Coordenada coord, String elem){
		
		List<String> columnaActual = this.getMatriz().get(coord.getColumna());
		columnaActual.add(coord.getFila(), elem);
	}
	/**
	 * Este metodo saca un elemento de la matriz, indicado por la coordenada que le pasan. 
	 */
	public void sacarElemento(Coordenada coord){
		List<String> columnaActual = this.getMatriz().get(coord.getColumna());
		
		columnaActual.remove(coord.getFila());
		columnaActual.add(coord.getFila(),"[]");
	}
	/**
	 * Este metodo Imprime el Tablero. Nos muesta su contenido. 
	 */
	public void imprimirTablero(){
		for(int i = 0;i<ancho;i++){ 
		    ArrayList<String> col = this.getMatriz().get(i);
			for(int j=0;j<alto;j++){ 
		        System.out.print(col.get(j)); 
		    } 
		    System.out.print("\n");//cambio de linea 
		}
	
	}
	/**
	 * Este metodo Dibuja la Pista que le pasan por parametro. 
	 */
	public void dibujarPista(Pista pista){
		dibujarParedes(pista);
		//dibujarAutos(pista);
		dibujarObstaculos(pista);
		//dibujarTesoros(pista);
	}
	/**
	 * Este metodo Dibuja las Paredes de la Pista que le pasan por parametro. 
	 */
	public void dibujarParedes(Pista pista){
		List<Coordenada> paredes  = pista.paredes;
		for (int i = 0; i< paredes.size(); i++) {
			Coordenada coord = paredes.get(i);
			agregarElemento(coord, "-");
		}
	}
	/**
	 * Este metodo Dibuja los Autos de la Pista que le pasan por parametro. 
	 */
	public void dibujarAutos(Pista pista){
		List<Auto> autosA  = pista.AutosAzules;
		List<Auto> autosB  = pista.AutosBlancos;
		// por cada auto.
//		for (int i = 0; ! paredes.isEmpty(); i++) {
//			Coordenada coord = paredes.get(i);
//			agregarElemento(coord, "-");
//		}
		
	}
	/**
	 * Este metodo Dibuja los Obstaculos de la Pista que le pasan por parametro. 
	 */
	public void dibujarObstaculos(Pista pista){
		List<Coordenada> obstaculos  = pista.obstaculos;
		for (int i = 0; i< obstaculos.size(); i++) {
			Coordenada coord = obstaculos.get(i);
			
			agregarElemento(coord, "#");
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
	actual.imprimirTablero();
}

	
}
