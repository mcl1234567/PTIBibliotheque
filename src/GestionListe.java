import java.util.ArrayList;
import java.util.LinkedList;

@SuppressWarnings("hiding")

public abstract class GestionListe <Element> {

	private LinkedList<Element> liste;
	private static String table;
	private static ArrayList<String> champs;
	private static ArrayList<String> valeur;

	/**
	 * M�thode d'ajout
	 * @param nouveau
	 * @param element
	 */
	public void ajouter(ArrayList<String> nouveau, Element element) {
		Exploitation bdd = new Exploitation();
		bdd.chargerPilote();
		bdd.connexion();
		//champs
		valeur = nouveau;
		bdd.insertion(table, champs, valeur);
		bdd.deconnexion();
		liste.add(element);
	}

	/**
	 * M�thode d'association
	 * @param numero
	 * @param numeroFils
	 * @param synchro
	 */
	public void associer(int numero, int numeroFils, boolean synchro) { }

	/**
	 * Retourne l'identifiant cha�n�
	 * @param numero
	 * @param liste
	 * @return string
	 */
	public String rechercher(int numero) {
		for(int i=0; i<liste.size(); i++) {
			//� d�bugger
			/*if(numero == ((Element) liste.get(i)).getNumero() 
			&& !((Object) liste.get(i)).getNom().equalsIgnoreCase("")) {
				return ((Element) liste.get(i)).getNom();
			}*/
		}
		return "";
	}

	/**
	 * Retourne l'identifiant num�rique
	 * @param nom
	 * @return num�ro
	 */
	public int rechercher(String nom, LinkedList<Element> liste) {
		for(int i=0; i<liste.size(); i++) {
			//� d�bugger
			/*if(nom.equalsIgnoreCase(((Element) liste.get(i)).getStr()) 
			&& !((Element) liste.get(i)).getStr().equalsIgnoreCase("")) {
				return ((Element) liste.get(i)).getNumero();
			}*/
		}
		return 0;
	}

	/**
	 * M�thode de suppression
	 * @param numero
	 * @param associe
	 */
	public void supprimer(int numero, boolean associe) {
		//if(associe) {
			//for(L e : liste) {
				//if(( (Element) e).getNumero() == numero) {
					//liste.get(e).getListes();
				//}
			//}
		//}
		Exploitation bdd = new Exploitation();
		bdd.chargerPilote();
		bdd.connexion();
		//champs
		bdd.suppression(table, numero);
		bdd.deconnexion();
		for(Element element : liste) {
			// � d�bugger
			/*if(( (Element) element).getNumero() == numero) {
				liste.remove(element);
			}*/
		}
	}

	public LinkedList<Element> getLesListes() {
		return null;
	}

}