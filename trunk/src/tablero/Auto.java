package tablero;

import java.sql.Time;
import java.util.concurrent.BrokenBarrierException;

import Barrera.Turno;
import Direcciones.*;
import tablero.Coordenada;
public class Auto extends Thread {

	Coordenada coordenadaActual;
	Direccion direccionActual;
	String color;
	boolean chocado;
	boolean movio;
	Pista pista;
	Turno turno;
	int id;
	public boolean esperando;

	 Auto(String color, Coordenada coord,Pista p, Turno turno, int id) {

		this.chocado = false;
		this.movio = false;
		this.color = color;
		this.esperando = true;
		this.coordenadaActual = coord;
		this.direccionActual = new Norte();
		this.pista=p;
		this.turno=turno;
		this.id=id;
	}
	
	public void run()
	{
		while(true)
	{  
	  while(this.esperando){ try {
		sleep(5);
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}}
	  this.moverse();
	  try {
		this.turno.await();
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	} catch (BrokenBarrierException e) {
		
		e.printStackTrace();
	}}
	}
	/**
	 * Este metodo retorna un booleano que indica si esta la coordenada pasada
	 * por parametro.
	 * 
	 * @param Coordenada
	 *            coord
	 * @return boolean
	 */
	boolean estaEnLaCoordenada(Coordenada coord) {
		return this.getCoordenadaActual().equals(coord);
	}
	/**
     * Este metodo mueve el auto a en su direccion actual, haciendo que choque
     * si la siguiente coordenada esta ocupada por algún obstaculo u otro auto
     * y de no ser así, controla si es que el auto encontro el tesoro.
     * 
     * @param Auto a
     */
	public void moverse(){
    	 Coordenada c= this.getCoordenadaActual();
    	 Direccion d= this.getDireccionActual();
    	 c= d.siguienteCoord(c);
    	 if(this.pista.hayLugar(c))
    	 {this.setCoordenadaActual(c);
    	  if(this.pista.encontroElTesoro(this))
    		  {
    		    //pararTODO!!
    		  }}
    	 else
    	 {
    		 Direccion contraria=d.contraria();
    		 if(this.pista.hayAutos(c))
    		 {
    			Auto autoChocado=this.pista.autoEnCoordenada(c);
    			this.chocar(autoChocado, d);
    		 }
    		 
    		 this.chocar(this, contraria);
    	 }
    	 this.movio=true;
    	 }
	/**
     * Este metodo simula el choque provocado al auto a que
     * debe desplazarse en la direccion d.
     * 
     * @param Auto a
     * @param Direccion d
     */
	public void chocar(Auto a, Direccion d)
   {
  	 Coordenada c= a.getCoordenadaActual();
  	 c= d.siguienteCoord(c);
  	 if(this.pista.hayLugar(c))
  	 {a.setCoordenadaActual(c);
  	  a.chocar();}
  	 this.chocado=true;
   }
	/**
	 * Este metodo representa que el auto esta chocado, poniendo su variable
	 * "chocado" en true.
	 */
	public void chocar() {
		this.chocado = true;
	}

	/**
	 * Este metodo representa la reparacion de un auto que fue chocado, poniendo
	 * su variable "chocado" en false.
	 */
	public void reparar() {
		this.chocado = false;
	}

	/**
	 * Este metodo cambia la direccion actual del coche por la Direccion d
	 * pasada por parametro.
	 * 
	 * @param Direccion
	 *            d
	 */
	public void cambiarDir(Direccion d) {
		this.setDireccionActual(d);
	}

	@Override
	public String toString() {
		return "Auto" + " " + this.color + " nro: " + this.id;
	}
	public boolean isChocado() {
		return chocado;
	}

	public void setChocado(boolean chocado) {
		this.chocado = chocado;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Direccion getDireccionActual() {
		return direccionActual;
	}

	public void setDireccionActual(Direccion direccionActual) {
		this.direccionActual = direccionActual;
	}

	public Coordenada getCoordenadaActual() {
		return coordenadaActual;
	}

	public void setCoordenadaActual(Coordenada coordenadaActual) {
		this.coordenadaActual = coordenadaActual;
	}

}
