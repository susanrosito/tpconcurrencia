package tablero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Consola {

	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private String palabra;

	public String readConsola() throws IOException {
		this.palabra = in.readLine();
		return palabra;
	}
	
	public String inputConsola(String s) throws IOException {
		this.outputConsola(s);
		this.palabra = in.readLine();
		return palabra;
	}
   public void outputConsola(String s)
   {
	   System.out.println(s);
   }
public String getPalabra() {
	
	return palabra;
}
}
