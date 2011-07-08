package Barrera;

import java.util.concurrent.CyclicBarrier;



import tablero.Pista;

public class Turno extends CyclicBarrier{

	public int num;

	public Turno(Pista pista, int i) {
		super(16, pista);
		this.num=i;
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
