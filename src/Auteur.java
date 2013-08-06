import java.io.File;
import java.util.ArrayList;

public class Auteur { 
	private int numero;
	private String nom;
	private String prenom;
	private String pseudonyme; 
	private String lienImage;
	//private ArrayList<String> livresPossedes = new ArrayList<String>();
	private ArrayList<Integer> livresPossedes = new ArrayList<Integer>();

	public Auteur(int id, String n, String p, String pseu, String lien) {
		this.numero = id;
		this.nom = n;
		this.prenom = p;
		this.pseudonyme = pseu;
		this.lienImage = lien;
		this.livresPossedes = new ArrayList<Integer>();
	}

	public Auteur(String[] a) {
		this.numero = Integer.parseInt(a[0]);
		this.nom = a[1];
		this.prenom = a[2];
		this.pseudonyme = a[3];
		this.lienImage = a[4];
		this.livresPossedes = new ArrayList<Integer>();
	}

	/**
	 * Initialize les livres de l'auteur
	 * @param titre
	 * @return
	 */
	public boolean setLivres(int numero) {
		for(int i=0; i<this.livresPossedes.size(); i++) if(this.livresPossedes.get(i) != numero) this.livresPossedes.add(numero);
		return true;
	}

	/**
	 * Suppression de la liste des livres de l'auteur
	 * @param titre
	 * @return
	 */
	public boolean suppressionLivres(int numero) {
		boolean confirm = false;
		for(int i=0; i<this.livresPossedes.size(); i++) {
			if(this.livresPossedes.get(i) == numero) {
				this.livresPossedes.remove(i);
				confirm = true;
			}
		}
		return confirm;
	}

	public boolean ajouterLivres(int numero) {
		for(int i=0; i<this.livresPossedes.size(); i++) if(this.livresPossedes.get(i) == numero) this.livresPossedes.add(numero); 			
		return true;
	}

	/**
	 * Initialize auteur
	 * @param modif
	 */
	public void set(String[] modif) {
		this.nom = modif[0];
		this.prenom = modif[1];
		this.pseudonyme = modif[2];
		//this.lien_image_auteur = modif[3]; à coder modification image auteur
	}

	/**
	 * Suppression fichiers
	 * @return vrai si supprimé
	 */
	public boolean removeFiles() {
		File f = new File(this.lienImage);
		if (!f.exists() || !f.canWrite()) return false;
		if(f.isFile()) return f.delete();
		return false;
	}

	/**
	 * Renvoi vrai si titre livre existant
	 * @param titre : titre du livre 
	 * @return vrai si présent
	 */
	public boolean isLivresPossedes(int numero) {
		for(int i=0; i<livresPossedes.size(); i++) if(this.livresPossedes.get(i) == numero) return true;
		return false;
	}

	/**
	 * Retourne les livres de l'auteur dans une liste
	 * @return ArrayList
	 */
	public ArrayList<Integer> getLivres() {
		return this.livresPossedes;
	}

	public int getNumero() {
		return this.numero;
	}

	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public String getPseudo() {
		return this.pseudonyme;
	}

	public String getLien() {
		return this.lienImage;
	}

}