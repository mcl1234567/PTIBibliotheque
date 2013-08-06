import java.io.File;
import java.util.ArrayList;

public class Livre extends Element {
	private int numero;
	private String titre;
	private String description;
	private String type; 
	private String lienImage;
	private ArrayList<Integer> exemplairesReferences;

	public Livre(int nA, int nL, String ti, String des, String type, String lien) {
		super();		
		this.numero = nL;
		this.titre = ti;
		this.description = des;
		this.type = type;
		this.lienImage = lien;
		this.exemplairesReferences = new ArrayList<Integer>();
	}

	public Livre(String[] li) {
		this.numero = Integer.parseInt(li[1]);
		this.titre = li[2];
		this.description = li[3];
		this.type = li[4];
		this.lienImage = li[5];
		this.exemplairesReferences = new ArrayList<Integer>();
	}
	
	public Livre(int numero, String[] li) {
		this.numero = numero;
		this.titre = li[1];
		this.description = li[2];
		this.type = li[3];
		this.lienImage = li[4];
		this.exemplairesReferences = new ArrayList<Integer>();
	}

	public void setLivre(String[] modif) {
		this.titre = modif[0];
		this.description = modif[1];
		this.type = modif[2];
		//this.lien_image_livre = modif[4]; a coder modification d'une image
	}

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
	public boolean isExemplairesPossedes(int numero) {
		for(int i=0; i<exemplairesReferences.size(); i++) if(this.exemplairesReferences.get(i) == numero) return true;
		return false;
	}

	public void setExemplaires(int numero) {
		for(int i=0; i<this.exemplairesReferences.size(); i++) 
			if(this.exemplairesReferences.get(i) != numero) 
				this.exemplairesReferences.add(numero);
	}

	public void suppressionExemplaires(int numero) {
		for(int i=0; i<this.exemplairesReferences.size(); i++) 
			if(this.exemplairesReferences.get(i) == numero) 
				this.exemplairesReferences.remove(numero);
	}

	/**
	 * Retourne les livres de l'auteur dans une liste
	 * @return ArrayList
	 */
	public ArrayList<Integer> getExemplaires() {
		return this.exemplairesReferences;
	}
	
	public int getNumero() {
		return this.numero;
	}

	public String getTitre() {
		return this.titre;
	}

	public String getDescription() {
		return this.description;
	}

	public String getType() {
		return this.type;
	}
	public String getLien() {
		return this.lienImage;
	}

}