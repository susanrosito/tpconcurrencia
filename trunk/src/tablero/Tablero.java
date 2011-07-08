package tablero;

import java.io.ObjectInputStream.GetField;
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
		agregarFilas(); // finalmente tambien creo las filas.
		agregarColumnas("[]"); // aca creo las columnas.

	}
	/**
	 * Este metodo agrega una columna, este metodo solamente se usa para inicializarla.
	 * Recibe un String y un int que es el nro de columna a agregar.
	 * @param colum String
	 * @param put int
	 */
	public void agregarColumna(String colum, int put) {
		List<String> current = this.getMatriz().get(put);
		for (int i = 0; i < ancho; i++) {
			current.add(i, colum);
		}
	}
	/**
	 * Este metodo agrega una fila a la columna indicada por el nro put, idem del punto anterior. 
	 * @param fila ArrayList<String> 
	 * @param put int
	 */
	public void agregarFila(ArrayList<String> fila, int put) {
		this.getMatriz().add(put, fila);
	}
	/**
	 * Este metodo agrega varias columnas,respetanto la cantidad que se pasa por el constructor del Tablero. 
	 */
	public void agregarColumnas(String columna) {
		for (int i = 0; i < alto; i++) {
			agregarColumna(columna, i);
		}
	}
	/**
	 * Este metodo agrega varias filas,respetanto la cantidad que se pasa por el constructor del Tablero. 
	 */
	public void agregarFilas() {
		for (int i = 0; i < alto; i++) {
			agregarFila(new ArrayList<String>(), i);
		}
	}
	/**
	 * Este metodo agrega un elemento a la matriz y lo ubica en la coordenada que le pasan. 
	 */
	public void agregarElemento(Coordenada coord, String elem){
		
		List<String> filaActual = this.getMatriz().get(coord.getFila());
		filaActual.remove(coord.getColumna());
		filaActual.add(coord.getColumna(), elem);
	}
	/**
	 * Este metodo saca un elemento de la matriz, indicado por la coordenada que le pasan. 
	 */
	public void sacarElemento(Coordenada coord){
		List<String> filaActual = this.getMatriz().get(coord.getFila());
		
		filaActual.remove(coord.getColumna());
		filaActual.add(coord.getColumna(),"[]");
	}
	/**
	 * Este metodo Imprime el Tablero. Nos muesta su contenido. 
	 */
	public void imprimirTablero(){
		for(int i = 0;i<alto;i++){ 
		    ArrayList<String> fila = this.getMatriz().get(i);
			for(int j=0;j<ancho;j++){ 
				System.out.print(fila.get(j)); 
		    } 
		    System.out.print("\n");//cambio de linea 
		}
	
	}
	/**
	 * Este metodo Dibuja la Pista que le pasan por parametro. 
	 */
	public void dibujarPista(Pista pista){
		dibujarAutos(pista);
		dibujarObstaculos(pista);
		dibujarTesoros(pista);
	}
	
	/**
	 * Este metodo Dibuja los Autos de la Pista que le pasan por parametro. 
	 */
	public void dibujarAutos(Pista pista){
		List<Auto> autosA  = pista.AutosAzules;
		List<Auto> autosB  = pista.AutosBlancos;
//		 por cada auto del Jugador A.
		for (int i = 0; i < autosA.size(); i++) {
			Auto autoA = autosA.get(i);
			agregarElemento(autoA.getCoordenadaActual(), "A"+i);
		}
//		 por cada auto del Jugador B.
		for (int i = 0; i < autosB.size(); i++) {
			Auto autoB = autosB.get(i);
			agregarElemento(autoB.getCoordenadaActual(), "B"+i);
		}
	}
	/**
	 * Este metodo Dibuja los Obstaculos de la Pista que le pasan por parametro. 
	 */
	public void dibujarObstaculos(Pista pista){
		List<Coordenada> obstaculos  = pista.obstaculos;
		for (int i = 0; i< obstaculos.size(); i++) {
			Coordenada coord = obstaculos.get(i);
			 agregarElemento(coord, "//");
		}
	}
	/**
	 * Este metodo Dibuja los tesoros de cada equipo. 
	 */
	public void dibujarTesoros(Pista pista){
		Coordenada tesoroA = pista.tesoroAzul;
		Coordenada tesoroB = pista.tesoroBlanco;
		agregarElemento(tesoroA, "aa");	
		agregarElemento(tesoroB,"bb");
		
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
	public List<Coordenada> coordDeUnaColumna(int nroFila){
		
		List<Coordenada> respuesta = new ArrayList<Coordenada>();
		if (nroFila <= alto){
			for (int i = 0; i < ancho; i++) {
				Coordenada coord = new Coordenada(nroFila,i);
				 respuesta.add(coord);
			}
		}
		return respuesta;
	}
public List<Coordenada> coordDeUnaFila(int nroColum){
		
		List<Coordenada> respuesta = new ArrayList<Coordenada>();
		if (nroColum <= ancho){
			for (int i = 0; i < alto; i++) {
				Coordenada coord = new Coordenada(i,nroColum);
				respuesta.add(coord);
			}
		}
		return respuesta;
	}
public static void main(String[] args) {
	// ESTO ES REALMENTE ESPANTOSO !!! :P MIRA TODO LO QUE TUBE QUE ESCRIBIR JEJE :P
	// YA TENGO LO DE CONSOLA!! ESTA RE BUENO.. LUEGO LO INCORPORO!
	Tablero actual = new Tablero(10,10);
	Pista nivel = new Pista();
	Coordenada ee = new Coordenada(7,6);
	Coordenada tt = new Coordenada(1,3);
	Coordenada ii = new Coordenada(7,5);

//	
//	//Auto auto1 = new Auto("Blanco", ee);
//	//Auto auto2 = new Auto("Blanco", tt);
//	//Auto auto3 = new Auto("Blanco", ii);
//	List<Auto> autosAzu = new ArrayList<Auto>();
//	
//	List<Auto> autosBla = new ArrayList<Auto>();
//	//autosBla.add(auto1);
//	//autosBla.add(auto2);
//	//autosBla.add(auto3);
//	Coordenada oo = new Coordenada(3,5);
//	Coordenada pp = new Coordenada(1,2);
//
//	Coordenada yy = new Coordenada(7,2);
//
//	
//	Auto auto5 = new Auto("Azul", oo);
//	Auto auto6 = new Auto("Azul", pp);
//	Auto auto7 = new Auto("Azul", yy);
//	autosAzu.add(auto5);
//	autosAzu.add(auto6);
//	autosAzu.add(auto7);
//	
//	List<Coordenada> taculos = new ArrayList<Coordenada>();
//	List<Coordenada> paredes = new ArrayList<Coordenada>();
//	List<Coordenada> etaculos = new ArrayList<Coordenada>();
//	List<Coordenada> eparedes = new ArrayList<Coordenada>();
//	List<Coordenada> obsta = new ArrayList<Coordenada>();
//	
//	Coordenada gg = new Coordenada(4,3);
//	nivel.tesoroAzul = gg; 
//	Coordenada hh = new Coordenada(8,8);
//	nivel.tesoroBlanco = hh; 
//	
//	Coordenada coord = new Coordenada(3,2);
//	obsta.add(coord);
//	
//	Coordenada coord2 = new Coordenada(4,2);
//	obsta.add(coord2);
//	
//	Coordenada coord3 = new Coordenada(5,2);
//	obsta.add(coord3);
//	
//	Coordenada coord4 = new Coordenada(5,3);
//
//	obsta.add(coord4);
//	
//	Coordenada coord5 = new Coordenada(5,4);
//	coord5.setFila(5);
//	coord5.setColumna(4);
//	obsta.add(coord5);
//	nivel.obstaculos = obsta;
//	nivel.AutosAzules = autosAzu;
//	nivel.AutosBlancos = autosBla;
//	
//	actual.dibujarObstaculos(nivel);
//	actual.dibujarAutos(nivel);
//	actual.dibujarTesoros(nivel);
//	actual.imprimirTablero();
}
	


	
}
