import java.util.ArrayList;
import java.util.LinkedList;

public class LesAchats {
	LinkedList<Achat> lesAchats;

	public LesAchats() {
		this.lesAchats = new LinkedList<Achat>();
	}

	/**
	 * Ajouter un objet et un enregistrement dans la base de données
	 * @param nv : tbleau d'éléments 
	 * @return booleen
	 */
	public boolean ajouter(LesExemplaires lEx, LesLecteurs lLect, String exemplaire, String lecteur) {
		int numeroExemplaire = Integer.parseInt(exemplaire);
		int numeroLecteur = Integer.parseInt(lecteur);
		String[] valeur = {
			exemplaire,
			lecteur
		};
		String[] champs = {
			"numero_exemplaire",
			"numero_lecteur"
		};
		Exploitation bdd = new Exploitation();
		bdd.chargerPilote();
		bdd.connexion();
		int numero = bdd.insertion(champs, "achat", valeur);
		lLect.associerAchat(numeroLecteur, numero, true);
		lEx.associerAchat(numeroExemplaire, numero, true);
		bdd.deconnexion();
		Achat a = new Achat(numero, valeur);
		this.lesAchats.add(a);
		return (numero != 0);
	}

	/**
	 * Recherche les exemplaires visionnés du lecteur
	 * @param lecteur : numero lecteur
	 * @return numero exemplaire
	 */
	public ArrayList<Integer> rechercherVisionnage(int lecteur) {
		ArrayList<Integer> liste = new ArrayList<Integer>();
		for(int i=0; i<this.lesAchats.size(); i++) 
			if(this.lesAchats.get(i).getNumeroLecteur() == lecteur) 
				liste.add(this.lesAchats.get(i).getNumeroExemplaire());
		return liste;
	}

	/**
	 * Recherche un numero d'exemplaire
	 * @param numero_ex : numero exemplaire
	 * @param numero_lecteur : numero lecteur
	 * @return booléen
	 */
	public boolean isAcheter(int numero_ex, int numero_lecteur) {
		for(int i=0; i<this.lesAchats.size(); i++) {
			if(this.lesAchats.get(i).getNumeroLecteur() == numero_lecteur 
			&& this.lesAchats.get(i).getNumeroExemplaire() == numero_ex) return true;
		}
		return false;
	}

	public LinkedList<Achat> getlesAchats() {
		return this.lesAchats;
	}

}
