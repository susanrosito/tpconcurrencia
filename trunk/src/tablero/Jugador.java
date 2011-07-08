package tablero;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Jugador {
	public List<Auto> autos;
	String nombre;
	public String color;
	public Consola consola;

	public Jugador(List<Auto> autos, String color) {
		this.autos = autos;
		this.color = color;

	}

	public List<Auto> autosDisponibles() {
		List<Auto> autosA = new ArrayList<Auto>();
		
		for (Auto a : this.autos)  {
			if (!a.isChocado() && !a.movio) {
				autosA.add(a);
			}
			
		}
		return autosA;
	}

	public static void main(String[] args) {
		try {
			Socket socketcliente = new Socket("localhost", 2624);

			BufferedReader serverIn = new BufferedReader(new InputStreamReader(
					socketcliente.getInputStream()));
			PrintStream serverOut = new PrintStream(
					socketcliente.getOutputStream());
			Consola consolaImpresora = new Consola();
			consolaImpresora.outputConsola("Cargando...");
			
			String preg= serverIn.readLine();
			
			
			
			/* INGRESARNOMBREPORCONSOLAYMNADARLOSERVEROUT */
			consolaImpresora.inputConsola(preg);
			serverOut.println(consolaImpresora.getPalabra());
			
			/*Imprime las reglas*/
			String nombre = serverIn.readLine();
			String color = serverIn.readLine();
			
			consolaImpresora.outputConsola("" +
					"¡Bienvenido/a " + nombre + " a los autitos chocadores!" +"\n" +
					"Las reglas y datos del juego son las siguientes:" +"\n" +
					" * Tienes 8 autos de tu equipo." +"\n" +
					" * Un tesoro que te pertence." +"\n" +
					" * En la pista hay paredes como obstaculos, si chocas contra alguna no podrás traspasarla." +"\n" +
					" * En un turno podrás mover solamente una vez cada auto." +"\n" +
					" * Si tu auto es chocado cuando otro se mueve (sea de tu equipo o del contrario, pierdes el turno de mover en ese auto." +"\n" +
					" * Al finalizar cada turno los coches serán reparados y podrás volver a moverlos." +"\n" +
					" * El juego finaliza cuando cualquiera de los dos participantes encuentran el tesoro su mismo color." +"\n" +
					" * Tu equipo es el color " + color +"\n" +
					" ¡Buena suerte!");
			
			while (true) {
				// tablero muestra autos Disponibles
				preg=serverIn.readLine();
				consolaImpresora.inputConsola(preg);
				serverOut.println(consolaImpresora.getPalabra());
				

				// ACA INGRESAS POR CONSOLA EL NUM Y LO MANDA (lo simula el 1)//
			
				preg=serverIn.readLine();
				consolaImpresora.inputConsola(preg);
				serverOut.println(consolaImpresora.getPalabra());

				// manda desea cambiar la dir
				preg=serverIn.readLine();
				consolaImpresora.inputConsola(preg);
				serverOut.println(consolaImpresora.getPalabra());
				
				// ACA INGRESAS POR CONSOLA EL "SI" o el "NO" Y LO MANDA (lo
				
				if(consolaImpresora.getPalabra().equals("Si")|| consolaImpresora.getPalabra().equals("si"))
				{consolaImpresora.readConsola();
				serverOut.println(consolaImpresora.getPalabra());}

				if (consolaImpresora.getPalabra().equals("Si")|| consolaImpresora.getPalabra().equals("si"))

				{ // si es true, pide que ingrese la N la S la O o la E
					preg=serverIn.readLine();
					consolaImpresora.inputConsola(preg);
					serverOut.println(consolaImpresora.getPalabra());
					
					
					

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nom) {
 		this.nombre = nom;
	}

	public List<Auto> getAutos() {
		return autos;
	}

	public void setAutos(List<Auto> autos) {
		this.autos = autos;
	}

}
