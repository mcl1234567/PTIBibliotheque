import java.util.ArrayList;

public class Element {

	public ArrayList<String> champs = new ArrayList<String>();
	
	public Element() {}
	public Element(String[] champs) {}
	
	public void setEnfant(Object[] enfant) {}
	public void setParent(Object parent) {}
	public void setListe(Object nouveau) {}
	
	public int getNumero() 			{ return 0; }
	public int getNombreAlbums() 	{ return 0; }
	public float getPrix() 			{ return 0; }
	public String getParent() 		{ return ""; }
	public String getEnfant() 		{ return ""; }
	public String getNom() 			{ return ""; }
	public String getPrenom() 		{ return ""; }
	public String getPseudo() 		{ return ""; }
	public String getLien() 		{ return ""; }
	public String getTitre() 		{ return ""; }
	public String getDescription() 	{ return ""; }
	public String getType() 		{ return ""; }

public void importer() {
		
	}
}
