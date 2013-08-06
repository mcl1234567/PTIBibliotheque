import java.util.ArrayList;
import java.util.LinkedList;

public class LesLectures {
	LinkedList<Lecture> lesLectures;
	
	public LesLectures() {
		this.lesLectures = new LinkedList<Lecture>();
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
			exemplaire,		//numero exemplaire
			lecteur 		//numero lecteur
		};
		String[] champs = {
			"numero_exemplaire",
			"numero_lecteur"
		};
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		int numero = bdd.insertion(champs, "visionnage", valeur);
		lEx.associerLecture(numeroExemplaire, numero, true);
		lLect.associerLecture(numeroLecteur, numero, true);
		bdd.Deconnexion();
		Lecture v = new Lecture(numero, valeur);
		this.lesLectures.add(v);
		return (v != null);
	}
	
	/**
	 * Recherche les exemplaires visionnés du lecteur
	 * @param lecteur : numero lecteur
	 * @return numero exemplaire
	 */
	public ArrayList<Integer> rechercherVisionnage(int lecteur) {
		ArrayList<Integer> liste = new ArrayList<Integer>();
		for(int i=0; i<this.lesLectures.size(); i++) {
			if(this.lesLectures.get(i).getNumeroLecteur() == lecteur) 
				liste.add(this.lesLectures.get(i).getNumeroExemplaire());
		}
		return liste;
	}

	/**
	 * Recherche un numero d'exemplaire
	 * @param numero_ex : numero exemplaire
	 * @param numero_lecteur : numero lecteur
	 * @return booléen
	 */
	public boolean isVisionner(int numero_ex, int numero_lecteur) {
		for(int i=0; i<this.lesLectures.size(); i++) {
			if(this.lesLectures.get(i).getNumeroLecteur() == numero_lecteur 
			&& this.lesLectures.get(i).getNumeroExemplaire() == numero_ex) return true;
		}
		return false;
	}
	
	public LinkedList<Lecture> getLesLectures() {
		return this.lesLectures;
	}

}
