import java.util.ArrayList;

public class Exemplaire extends Element {
	private int numero;
	private float prix;
	private int nombre;
	private ArrayList<Integer> achatsReferences;
	private ArrayList<Integer> lecturesReferencees;

	public Exemplaire(int numero_exemplaire, int numero_livre, int numeroAchat, int nombre, float prix) {
		this.numero = numero_exemplaire;
		this.setPrix(prix);
		this.setNombre(nombre);
		this.achatsReferences = new ArrayList<Integer>();
		this.lecturesReferencees = new ArrayList<Integer>();
	}
	
	public Exemplaire(String[] ex) {
		this.numero = Integer.parseInt(ex[0]);
		this.nombre = Integer.parseInt(ex[4]);
		this.prix = Float.parseFloat(ex[5]);
		this.achatsReferences = new ArrayList<Integer>();
		this.lecturesReferencees = new ArrayList<Integer>();
	}

	public Exemplaire(int numero, String[] ex) {
		this.numero = numero;
		this.nombre = Integer.parseInt(ex[3]);
		this.prix = Float.parseFloat(ex[4]);
		this.achatsReferences = new ArrayList<Integer>();
		this.lecturesReferencees = new ArrayList<Integer>();
	}
	
	public boolean setExemplaires(String[] ex) {
		this.nombre = Integer.parseInt(ex[0]);
		this.prix = Float.parseFloat(ex[1]);
		return true;
	}
	
	/**
	 * ajout achats
	 * @param numero
	 */
	public boolean setAchats(int numero) {
		for(int i=0; i<this.achatsReferences.size(); i++) 
			if(this.achatsReferences.get(i) != numero) this.achatsReferences.add(numero);
		return true;
	}
	
	/**
	 * ajout lectures
	 * @param numero
	 */
	public boolean setLectures(int numero) {
		for(int i=0; i<this.lecturesReferencees.size(); i++) 
			if(this.lecturesReferencees.get(i) != numero) this.lecturesReferencees.add(numero);
		return true;
	}

	/**
	 * supression achats
	 * @param numero
	 */
	public boolean suppressionAchats(int numero) {
		for(int i=0; i<this.achatsReferences.size(); i++) 
			if(this.achatsReferences.get(i) == numero) this.achatsReferences.remove(i);
		return true;
	}

	/**
	 * suppression lectures
	 * @param numero
	 */
	public boolean suppressionLectures(int numero) {
		for(int i=0; i<this.lecturesReferencees.size(); i++) 
			if(this.lecturesReferencees.get(i) == numero) this.lecturesReferencees.remove(i);
		return true;
	}
	
	/** 
	 * @param numero_exemplaire
	 * @param cb_exemplaire
	 * @return boolean
	 */
	public boolean isAchatsReferences(int numero) {
		for(int i=0; i<this.achatsReferences.size(); i++) 
			if(this.achatsReferences.get(i) == numero) return true;
		return false;
	}
	
	/** 
	 * @param numero_exemplaire
	 * @param cb_exemplaire
	 * @return boolean
	 */
	public boolean isLecturesReferencees(int numero) {
		for(int i=0; i<this.lecturesReferencees.size(); i++) 
			if(this.lecturesReferencees.get(i) == numero) return true;
		return false;
	}

	public int getNumero() {
		return this.numero;
	}

	public float getPrix() {
		return this.prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	private void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public int getNombre() {
		return this.nombre;
	}

	public ArrayList<Integer> getLecturesReferencees() {
		return this.lecturesReferencees;
	}
	
	public ArrayList<Integer> getAchatsPossedes() {
		return this.achatsReferences;
	}

}