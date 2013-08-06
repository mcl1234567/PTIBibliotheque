
public class Lecture {
	private int numeroVue;
	private int numeroExemplaire;
	private int numeroLecteur;

	public Lecture(int[] numero) {
		this.setNumero(numero[0]); 
		this.setNumeroExemplaire(numero[1]);
		this.setNumeroLecteur(numero[2]);
	}

	public Lecture(int numero, String[] visio) {
		this.setNumero(numero); 
		this.setNumeroExemplaire(Integer.parseInt(visio[0]));
		this.setNumeroLecteur(Integer.parseInt(visio[1]));
	}
	
	public int getNumero() {
		return numeroVue;
	}

	public void setNumero(int numero) {
		this.numeroVue = numero;
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