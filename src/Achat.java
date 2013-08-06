
public class Achat {
	private int numeroAchat;
	private int numeroExemplaire;
	private int numeroLecteur;

	public Achat(int[] numero) {
		this.setNumero(numero[0]); 
		this.setNumeroExemplaire(numero[1]);
		this.setNumeroLecteur(numero[2]);
	}

	public Achat(int numero, String[] achat) {
		this.setNumero(numero); 
		this.setNumeroExemplaire(Integer.parseInt(achat[0]));
		this.setNumeroLecteur(Integer.parseInt(achat[1]));
	}
	
	public int getNumero() {
		return numeroAchat;
	}

	public void setNumero(int numero) {
		this.numeroAchat = numero;
	}

	public int getNumeroExemplaire() {
		return numeroExemplaire;
	}

	public void setNumeroExemplaire(int numeroExemplaire) {
		this.numeroExemplaire = numeroExemplaire;
	}

	public int getNumeroLecteur() {
		return numeroLecteur;
	}

	public void setNumeroLecteur(int numeroLecteur) {
		this.numeroLecteur = numeroLecteur;
	}

	
}