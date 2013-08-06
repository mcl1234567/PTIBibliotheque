import java.io.File;
import java.util.ArrayList;

public  class Edition extends Element{ 
	private	int numero;
	private String libelle;
	private ArrayList<Integer> num_exemplaires_possedes = new ArrayList<Integer>();
	private ArrayList<String> cb_exemplaires_possedes = new ArrayList<String>();
	private int nombre_exemplaires;
	private ArrayList<String> auteursReferences;
	private String lien_image_edition;
	private int nombre_auteurs;

	public Edition(int n, String m) {
		this.numero = n;
		this.libelle = m;
		this.nombre_exemplaires = 0;
	}

	public Edition(int numero_edition, String[] valeur) {
		this.numero = numero_edition;
		this.libelle = valeur[0];
		this.lien_image_edition = "";
		this.auteursReferences = new ArrayList<String>();
	}
	
	public void SetEdition(String[] modif) {
		this.libelle = modif[0];		
	}

	/**
	 * Initialize les auteurs de l'édition
	 * @param nom_auteur
	 * @return booléen
	 */
	public boolean SetLivres(String ref_auteur) {
		for(int i=0; i<this.auteursReferences.size(); i++) {
			if(this.auteursReferences.get(i).equalsIgnoreCase(ref_auteur)) {
				return false;
			}
		}
		this.auteursReferences.add(ref_auteur);
		this.setNombreAuteurs(this.getNombreAuteurs() + 1);
		return true;
	}
	
	/**
	 * INITIALISE LES EXEMPLAIRES
	 * @param numero_exemplaire
	 * @return boolean
	 */
	public boolean SetExemplaire(int numero_exemplaire) {
		for(int i=0; i<this.num_exemplaires_possedes.size(); i++) {
			if(this.num_exemplaires_possedes.get(i) == numero_exemplaire) return false;
		}
		this.num_exemplaires_possedes.add(numero_exemplaire);
		this.nombre_exemplaires++;
		return true;
	}
	
	/**
	 * INITIALISE LES EXEMPLAIRES
	 * @param numero_exemplaire
	 * @param cb_exemplaire
	 * @return boolean
	 */
	public boolean SetExemplaire(int numero_exemplaire, String cb_exemplaire) {
		for(int i=0; i<this.num_exemplaires_possedes.size(); i++) {
			if(this.num_exemplaires_possedes.get(i) == numero_exemplaire) return false;
		}
		this.num_exemplaires_possedes.add(numero_exemplaire);
		for(int i=0; i<this.cb_exemplaires_possedes.size(); i++) {
			if(this.cb_exemplaires_possedes.get(i).equalsIgnoreCase(cb_exemplaire)) return false;
		}
		this.cb_exemplaires_possedes.add(cb_exemplaire);
		this.nombre_exemplaires++;
		return true;
	}
	
	/**
	 * INITIALISE LES EXEMPLAIRES
	 * @param cb_exempaire
	 * @return boolean
	 */
	public boolean setExemplaire(String cb_exemplaire) {
		for(int i=0; i<this.cb_exemplaires_possedes.size(); i++) {
			if(this.cb_exemplaires_possedes.get(i).equalsIgnoreCase(cb_exemplaire)) return false;
		}
		this.cb_exemplaires_possedes.add(cb_exemplaire);
		this.nombre_exemplaires++;
		return true;
	}
	
	/**
	 * VERIFICATION  - EXEMPLAIRES POSSEDES 
	 * @param numero_exemplaire
	 * @return boolean
	 */
	public boolean isExemplairesPossedes(int numero_exemplaire) {
		for(int i=0; i<this.num_exemplaires_possedes.size(); i++) {
			if(this.num_exemplaires_possedes.get(i) == numero_exemplaire) return true;
		}		
		return false;
	}

	/**
	 * VERIFICATION AUGMENTEE - EXEMPLAIRES POSSEDES 
	 * @param numero_exemplaire
	 * @param cb_exemplaire
	 * @return boolean
	 */
	public boolean isExemplairesPossedes(int numero_exemplaire, String cb_exemplaire) {
		for(int i=0; i<this.num_exemplaires_possedes.size(); i++) {
			if(this.num_exemplaires_possedes.get(i) == numero_exemplaire && this.cb_exemplaires_possedes.get(i).equalsIgnoreCase(cb_exemplaire))
				return this.num_exemplaires_possedes.size() == this.cb_exemplaires_possedes.size();
		}
		return false;
	}

	/**
	 * Suppression par numero exemplaire
	 * @param numero_exemplaire
	 * @return boolean
	 */
	public boolean suppressionExemplaire(int numero_exemplaire) {
		for(int i=0; i<this.num_exemplaires_possedes.size(); i++) {
			if(this.num_exemplaires_possedes.get(i) == numero_exemplaire) {
				this.nombre_exemplaires--;
				this.num_exemplaires_possedes.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Suppression par code exemplaire
	 * @param cb_exemplaire
	 * @return boolean
	 */
	public boolean suppressionExemplaire(String cb_exemplaire) {
		for(int i=0; i<this.cb_exemplaires_possedes.size(); i++) {
			if(this.cb_exemplaires_possedes.get(i).equalsIgnoreCase(cb_exemplaire)) {
				this.nombre_exemplaires--;
				this.cb_exemplaires_possedes.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Suppression fichiers
	 * @return vrai si supprimé
	 */
	public boolean removeFiles() {
		File f = new File(this.lien_image_edition);
		if (!f.exists() || !f.canWrite()) return false;
		if(f.isFile()) return f.delete();
		return false;
	}

	
	/**
	 * Suppression par numero exemplaire & code exemplaire
	 * @param numero_exemplaire
	 * @param cb_exemplaire
	 * @return boolean
	 */
	public boolean suppressionExemplaire(int numero_exemplaire, String cb_exemplaire) {
		for(int i=0; i<this.num_exemplaires_possedes.size(); i++) {
			if(this.num_exemplaires_possedes.get(i) == numero_exemplaire && this.cb_exemplaires_possedes.get(i).equalsIgnoreCase(cb_exemplaire)) {
				this.nombre_exemplaires--;
				this.cb_exemplaires_possedes.remove(i);
				this.num_exemplaires_possedes.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Integer> getNumeroExemplairesPossedes() {
		return this.num_exemplaires_possedes;
	}

	public ArrayList<String> getCodeExemplairesPossedes() {
		return this.cb_exemplaires_possedes;
	}
	
	public int getNombreExemplaires() {
		return this.nombre_exemplaires;
	}

	public int getNumero() {
		return this.numero;
	}

	public String getLibelle() {
		String libelle = this.libelle != null ? this.libelle : "";
		return libelle;
	}

	public String getLien() {
		String lien = this.lien_image_edition != null ? this.lien_image_edition : ""; 
		return lien;
	}

	public ArrayList<String> getAuteursArray() {
		return this.auteursReferences;
	}

	public int getNombreAuteurs() {
		return nombre_auteurs;
	}

	public void setNombreAuteurs(int nombre_auteurs) {
		this.nombre_auteurs = nombre_auteurs;
	}

}