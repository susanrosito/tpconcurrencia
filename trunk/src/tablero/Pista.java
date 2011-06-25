package tablero;

import java.util.List;

import Direcciones.Direccion;

public class Pista {

	 List<Coordenada> paredes;
     List<Coordenada> obstaculos;
     List<Coordenada> ubicaciones;
     List<Auto> AutosAzules;
     List<Auto> AutosBlancos;
     
     Coordenada tesoroBlanco;
     Coordenada tesoroAzul;
     Coordenada limite;
     
     /**
      * Este metodo repara los coches que han sido chocados.
      * Es invocado al final de cada turno.
      */
     void repararCoches()
     {
    	int i=0;
    	for (Auto a = this.AutosAzules.get(i); i< this.AutosAzules.size(); ) {
    		  if(a.isChocado())
    		  {a.reparar();}
    		  i++;		
    		 }
    	i=0;
    	for (Auto a = this.AutosBlancos.get(i); i< this.AutosBlancos.size(); ) {
   		  if(a.isChocado())
   		  {a.reparar();}
   		  i++; }		
   		 }
    	
    	/**
    	 * Revisa si en en la coordenada c pasada por parametro hay algun auto
         * y retorna un booleano, true si lo hay y false si no hay.
         * 
         * @param Coordenada c
    	 * @return boolean
    	 */
    	boolean hayAutos(Coordenada c)
    	{
    		boolean aux = false;
    		int i=0;
    		for (Auto a = this.AutosBlancos.get(i); i< this.AutosBlancos.size(); ) 
    			{  
    	   		  aux= aux || a.estaEnLaCoordenada(c);
    	   		  i++;		}
    		i=0;
    		for (Auto a = this.AutosAzules.get(i); i< this.AutosAzules.size(); ) 
    			{  
    	   		  aux= aux || a.estaEnLaCoordenada(c);
    	   		  i++;		}
    	return aux;	
			
    	
     }
     
    /**
      * Revisa si en en la coordenada coord pasada por parametro esta fuera 
      * o dentro del limite y retorna un booleano, true si lo esta y false si no.
      * 
      * @param Coordenada coord
      * @return boolean
      */
     boolean enLimite(Coordenada coord)
     {
         return coord.columna >= 0 && coord.columna <= limite.columna && 
                coord.fila >= 0 && coord.fila <= limite.fila;
     }
	
     /**
      * Revisa si en en la coordenada x pasada por parametro hay alguna pared
      * y retorna un booleano, true si lo hay y false si no hay.
      * 
      * @param Coordenada x
      * @return boolean
      */
     boolean hayPared(Coordenada x)
     {
         return this.paredes.contains(x);
     }
     
     /**
      * Revisa si en en la coordenada x pasada por parametro hay algun obstaculo
      * y retorna un booleano, true si lo hay y false si no hay.
      * 
      * @param Coordenada x
      * @return boolean
      */
     boolean hayObstaculo(Coordenada x)
     {
         return this.obstaculos.contains(x);
     }
     
    
    /**
     * Este metodo retorna un booleano representando si en esa coordenada
     * hay lugar, es decir si esta vacia.
     *  
     * @param Coordenada x
     * @return boolean
     */
     boolean hayLugar(Coordenada x)
     {
         return !hayObstaculo(x) && !hayPared(x)&& 
                !enLimite(x)     && !hayAutos(x);
     }
     
     /**
      * Este metodo mueve el auto a en su direccion actual, haciendo que choque
      * si la siguiente coordenada esta ocupada por algún obstaculo u otro auto
      * y de no ser así, controla si es que el auto encontro el tesoro.
      * 
      * @param Auto a
      */
     public void mover(Auto a){
    	 Coordenada c= a.getCoordenadaActual();
    	 Direccion d= a.getDireccionActual();
    	 c= d.siguienteCoord(c);
    	 if(this.hayLugar(c))
    	 {a.setCoordenadaActual(c);
    	  if(this.encontroElTesoro(a))
    		  {
    		    //pararTODO!!
    		  }}
    	 else
    	 {
    		 Direccion contraria=d.contraria();
    		 if(this.hayAutos(c))
    		 {
    			Auto autoChocado=this.autoEnCoordenada(c);
    			this.chocar(autoChocado, d);
    		 }
    		 
    		 this.chocar(a, contraria);
    	 }
    	 }
     
     
     /**
      * Este metodo retorna el auto que esta en la coordenada c, si
      * es que hay alguno, de lo contrario, devuelve null.
      * 
      * @param Coordenada c
      * @return Auto
      */
      Auto autoEnCoordenada(Coordenada c) {

    	Auto chocado=null;
  		int i=0;
  		for (Auto a = this.AutosBlancos.get(i); i< this.AutosBlancos.size(); ) 
  			{  
  	   		  if(a.estaEnLaCoordenada(c))
  	   		  {   chocado=a;
  	   			  return chocado;}
  	   		  i++;		}
  		i=0;
  		for (Auto a = this.AutosAzules.get(i); i< this.AutosAzules.size(); ) 
  			{  
  			if(a.estaEnLaCoordenada(c))
	   		  { chocado=a;
	   			return chocado;}
  	   		  i++;		}
  		return chocado;
  	
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
    	 if(this.hayLugar(c))
    	 {a.setCoordenadaActual(c);
    	  a.chocar();}
     }
     
	/**
	 * Este metodo retorna un booleano representando si el auto pasado por
	 * parametro encontro el tesoro correspondiente a su equipo.
	 * 
	 * @param Auto a
	 * @return booleano
	 */
     public boolean encontroElTesoro(Auto a){
    	if(a.getColor()=="Azul"){
    	return a.getCoordenadaActual().equals(this.tesoroAzul);	}
    	else{
    	return a.getCoordenadaActual().equals(this.tesoroBlanco);}
     }
     
     
     public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
