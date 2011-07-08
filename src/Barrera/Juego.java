package Barrera;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import tablero.Jugador;
import tablero.Pista;

public class Juego {
	/**
	 * Este va a ser el servidor y cada Jugador va a ser 
	 * un cliente.
	 */
	
	
	Pista pista;
	Jugador jugadorUno;
	Jugador jugadorDos;
	
	
	Juego()
	{
		pista= new Pista();
		
		
	}
	
	
	

	

		
	
	public static void main(String[] args) {
		
		Juego j= new Juego();
		ServerSocket serverSocket;
		Socket serverSoc= null;
		
		try{
		serverSocket= new ServerSocket(2624);
			
			
			serverSoc= serverSocket.accept();
			j.jugadorUno = new Jugador(j.pista.AutosBlancos, "Blanco");
			JugadorThread jt= new JugadorThread(serverSoc, j.pista, j.jugadorUno, j.pista.semaforoThreads);
			jt.start();
			serverSoc= serverSocket.accept();
			j.jugadorDos = new Jugador(j.pista.AutosAzules, "Azul");
			JugadorThread jt2= new JugadorThread(serverSoc, j.pista, j.jugadorDos, j.pista.semaforoThreads);
			jt2.start();
			
		
		
		}
		
		
		catch(IOException io) {
		System.out.println("Could not listen on port: 2624");
		System.exit(-1);
		}
		}
	}




