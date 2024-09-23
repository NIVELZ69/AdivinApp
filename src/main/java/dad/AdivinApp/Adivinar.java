package dad.AdivinApp;

public class Adivinar {

	public static int comprobarNumero(int respuesta, int adivinar) {
		
		int resultado = 0;
		
		if (respuesta == adivinar) {
			resultado = 0;
		} else if (respuesta != adivinar) {
				
			if (adivinar < respuesta) {
				resultado = 1;
			} else if (adivinar > respuesta) {
				resultado = 2;
			}
			
		}
		
		return resultado;

	}

}
