import java.io.File;
import java.util.ArrayList;

public class Lecteur { 
	private int numero;
	private String nom;
	private String prenom;
	private String pseudonyme;
	private String code_carte_bancaire;
	private int cryptogramme;
	private String date_expiration;
	private String lien_image_lecteur; // pas intégré
	private ArrayList<Integer> achatsReferences;
	private ArrayList<Integer> lecturesReferencees;

	public Lecteur(int numero, String nom, String pre, String pseu, String code, int crypto, String dateexp, String lien) {
		this.numero = numero; 
		this.nom = nom;
		this.prenom = pre;
		this.pseudonyme = pseu;
		this.setCode_carte_bancaire(code);
		this.setCryptogramme(crypto);
		this.setDate_expiration(dateexp);
		this.lien_image_lecteur = lien;
		this.achatsReferences = new ArrayList<Integer>();
		this.lecturesReferencees = new ArrayList<Integer>();
	}

	public Lecteur(String[] le) {
		this.numero = Integer.parseInt(le[0]);
		this.nom = le[1];
		this.prenom = le[2];
		this.pseudonyme = le[3];
		this.setCode_carte_bancaire(le[4]);
		this.setCryptogramme(Integer.parseInt(le[5]));
		this.setDate_expiration(le[6]);
		this.lien_image_lecteur = le[7];
		this.achatsReferences = new ArrayList<Integer>();
		this.lecturesReferencees = new ArrayList<Integer>();
	}
	
	/**
	 * 
	 * @param numero : numero lecteur de la bdd
	 * @param le
	 */
	public Lecteur(int numero, String[] le) {
		this.numero = numero;
		this.nom = le[0];
		this.prenom = le[1];
		this.pseudonyme = le[2];
		this.setCode_carte_bancaire(le[4]);
		this.setCryptogramme(Integer.parseInt(le[5]));
		this.setDate_expiration(le[6]);
		this.lien_image_lecteur = "";
	}

	/**
	 * Modification du lecteur
	 * @param modif
	 */
	public void setLecteur(String[] modif) {
		this.nom 						= modif[0];
		this.prenom 					= modif[1];
		this.pseudonyme 				= modif[2];
		this.setCode_carte_bancaire(modif[3]);
		this.setCryptogramme(Integer.parseInt(modif[4]));
		this.setDate_expiration(modif[5]);
		this.lien_image_lecteur 		= modif[6];
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
	 * Suppression fichiers
	 * @return vrai si supprimé
	 */
	public boolean removeFiles() {
		File f = new File(this.lien_image_lecteur);
		if (!f.exists() || !f.canWrite()) return false;
		if(f.isFile()) return f.delete();
		return false;
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

	public String getLien() {
		String lien = this.lien_image_lecteur != null ? this.lien_image_lecteur : "";
		return lien;
	}

	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}
	
	public String getPseudonyme() {
		return pseudonyme;
	}

	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}

	public String getDate_expiration() {
		return date_expiration;
	}

	public void setDate_expiration(String date_expiration) {
		this.date_expiration = date_expiration;
	}

	public int getCryptogramme() {
		return cryptogramme;
	}

	public void setCryptogramme(int cryptogramme) {
		this.cryptogramme = cryptogramme;
	}

	public String getCode_carte_bancaire() {
		return code_carte_bancaire;
	}

	public void setCode_carte_bancaire(String code_carte_bancaire) {
		this.code_carte_bancaire = code_carte_bancaire;
	}
	
	public ArrayList<Integer> getLesAchats() {
		return this.achatsReferences;
	}
	
	public ArrayList<Integer> getLesLectures() {
		return this.lecturesReferencees;
	}

}