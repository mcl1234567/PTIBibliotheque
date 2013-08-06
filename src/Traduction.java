
public class Traduction extends Element{
	private int numero;
	private String langue;
	
	public Traduction(int numero, String langue) {
		this.setNumero(numero);
		this.setLangue(langue);
	}
	
	public Traduction(int numero, String[] tra) {
		this.setNumero(numero);
		this.setLangue(tra[0]);
	}
	
	public Traduction(String[] tra) {
		this.setNumero(Integer.parseInt(tra[0]));
		this.setLangue(tra[1]);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}
	
}
