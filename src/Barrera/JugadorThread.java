package Barrera;
import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import Direcciones.Este;
import Direcciones.Norte;
import Direcciones.Oeste;
import Direcciones.Sur;
import tablero.Auto;
import tablero.Jugador;
import tablero.Pista;

public class JugadorThread extends Thread {
	    Socket s;
		private Jugador jugador;
		private Pista pista;
		private Semaphore semaforo;
		JugadorThread(Socket j, Pista p, Jugador jugador, Semaphore semaforoThreads)
		{
			this.s=j;
			this.jugador=jugador;
			this.pista=p;
			this.semaforo=semaforoThreads;
		}
	    
		public void run()
		{
			try{
			BufferedReader serverIn= new BufferedReader(new
			InputStreamReader(this.s.getInputStream()));
			PrintStream serverOut= new
			PrintStream(this.s.getOutputStream());
			serverOut.println("Ingresar nombre:");
			String nombreJugador = serverIn.readLine();
			this.jugador.setNombre(nombreJugador);
			
			serverOut.println(this.jugador.getNombre());
			serverOut.println(this.jugador.color);
			while(true)
			{
				sleep(6);
				if(this.jugador.autosDisponibles().size()==0)
				{ this.semaforo.acquire();}
				
				//tablero mostrarautosDisponibles
				
				String autos =this.jugador.autosDisponibles().toString();
				serverOut.println("Escoge un numero de auto de todos los disponibles :" + autos);
				//serverOut.print(this.jugador.autosDisponibles());
				//lee msj auto seleccionado
				String numeroAuto=serverIn.readLine();
				int numero=(Integer.parseInt(numeroAuto))-1;
				Auto a=this.jugador.autos.get(numero);
				
				//manda desea cambiar la dir
				serverOut.println("Su direccion actual es: " +a.getDireccionActual().toString()+ " ¿Desea cambiar la dirección actual del auto?");
				String rta=serverIn.readLine();
				if(rta.equals("Si")|| rta.equals("si"))
				//si es true, pide que ingrese la N la S la O o la E
				{
					serverOut.println("Ingrese la inicial correspondiente");
					String nuevaDir=serverIn.readLine();
				
				//busca el auto y setea en este if
				if(nuevaDir.equals("N")|| nuevaDir.equals("n"))
				{ a.cambiarDir(new Norte());}
				else {
					if(nuevaDir.equals("S")|| nuevaDir.equals("s"))
					{  a.cambiarDir(new Sur());}
					else {if(nuevaDir.equals("O")|| nuevaDir.equals("o"))
					        {  a.cambiarDir(new Oeste());}
					      else { a.cambiarDir(new Este());	}}}}
			
				a.esperando=false;
			}} catch ( Exception e ){
				e.printStackTrace();
						}
					}
			
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
		}

	}


