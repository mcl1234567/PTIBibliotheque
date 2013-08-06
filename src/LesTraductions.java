import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LesTraductions {
	LinkedList<Traduction> lesTraductions; 
	
	public LesTraductions() {
		this.lesTraductions = new LinkedList<Traduction>();
	}

	/**
	 * Ajouter un objet et un enregistrement dans la base de données
	 * @param langue : langue traduite
	 */
	public boolean ajouter(String langue) {
		String[] champs = {
				"langue_traduction"
		};
		String[] valeur = {
			langue,
		};
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		int numero = bdd.insertion(champs, "traduction", valeur);
		bdd.Deconnexion();
		Traduction tr = new Traduction(numero, valeur);
		this.lesTraductions.add(tr);
		return (tr != null);
	}
	
	/**
	 * Affichage JLabel dans Jpanel
	 * @param PAffichage
	 * @param nb_aff_debut 	: numéro de début d'affichage inférieur à nb_aff_fin
	 * @param nb_aff_fin 	: numéro de fin d'affichage supérieur à nb_aff_debut
	 * @param type
	 * @param Btype : bouton de suppression ou de modification
	 * @param numeros_auteur : tableau d'auteurs envoyé lors de la Recherche d'auteurs
	 * @return
	 */
	public ButtonGroup lister(JPanel PAffichage, int nb_aff_debut, int nb_aff_fin, int type, JButton Btype, ArrayList<Integer> numeros_traduction) {
		if(Btype != null) PAffichage.add(Btype);
		ButtonGroup liste_radio = new ButtonGroup();
		String listeVide = "Pour ajouter une langue : Traduction -> Ajouter une langue";
		int coords_x = 30, coords_y = 50, coords_w = 180, coords_h = 20; 
		if(this.lesTraductions.size() >= 1) {
			JLabel lmaternelle = new JLabel("Langue maternelle".toUpperCase());
			JLabel ltraduite = new JLabel("Langue traduite".toUpperCase());
			JLabel page_prem = new JLabel("Résultats : de "  + String.valueOf(nb_aff_debut));
			JLabel page_dern = new JLabel(" à " + String.valueOf(nb_aff_fin));
			JLabel page_total = new JLabel("");
			String pt = (numeros_traduction.size() == 0) ? "sur " + String.valueOf(this.lesTraductions.size()) : "sur " + String.valueOf(numeros_traduction.size());
			page_total.setText(pt);
			lmaternelle.setBounds(coords_x, 		(coords_y-20), coords_w, coords_h);
			ltraduite.	setBounds((coords_x+140), 	(coords_y-20), coords_w, coords_h);
			page_prem.	setBounds(420, 				350, 			100, 20);
			page_dern.	setBounds(520, 				350, 			40, 20);
			page_total.	setBounds(550, 				350, 			70, 20);
			PAffichage.add(page_prem);
			PAffichage.add(page_dern);
			PAffichage.add(page_total);
			PAffichage.add(lmaternelle);
			PAffichage.add(ltraduite);
		} else {
			JLabel label_livre_affichage_lib1 = new JLabel(listeVide);
			label_livre_affichage_lib1.setBounds(coords_x, 25, 400, 20);
			PAffichage.add(label_livre_affichage_lib1);
		}
		for(int i=nb_aff_debut; i<nb_aff_fin; i++) {
			if((i - nb_aff_debut) != 0) {
				coords_y += 140;
			}
			if(type != 0) {
				/*JRadioButton Bradio = new JRadioButton(this.lesExemplaires.get(i).getNomexemplaire());				
				Bradio.setBounds(650, (coords_y+40), 20, 20);
				//Bradio.setActionCommand(this.lesExemplaires.get(i).getNomexemplaire());
				liste_radio.add(Bradio);
				PAffichage.add(Bradio);*/
			}
			JLabel lnom_exemplaire_valeur = new JLabel("");
			JLabel lprenom_exemplaire_valeur = new JLabel("");
			JLabel lpseudo_exemplaire_valeur = new JLabel("");
			if(numeros_traduction.size() > 0) {
				//	Affichage spécifique
				for(int y=0; y<this.lesTraductions.size(); y++) {
					if(numeros_traduction.get(i) == this.lesTraductions.get(y).getNumero()) {
						lnom_exemplaire_valeur.setText(this.lesTraductions.get(y).getLangue().substring(0, 1).toUpperCase() 
														+ this.lesTraductions.get(y).getLangue().substring(1).toLowerCase());/*
						lprenom_exemplaire_valeur.setText(this.lesExemplaires.get(y).getPrenomexemplaire().substring(0, 1).toUpperCase() 
														+ this.lesExemplaires.get(y).getPrenomexemplaire().substring(1).toLowerCase());
						if (this.lesExemplaires.get(y).getPseudonyme().length() != 0) 
							lpseudo_exemplaire_valeur.setText(this.lesExemplaires.get(y).getPseudonyme().substring(0, 1).toUpperCase() 
														+ this.lesExemplaires.get(y).getPseudonyme().substring(1).toLowerCase());

						*///Ajout ..
						/*
						ArrayList<String> ouvrages = this.LesExemplaires.get(y).get..Array();
						int m = 0, x = 0;
						while(x < ouvrages.size() && x < 5) {							
							JLabel Louvrage = new JLabel(ouvrages.get(x));					
							m = (x+1) * 17 + 10;
							Louvrage.setBounds(coords_x, (coords_y+m), coords_w, coords_h);
							PAffichage.add(Louvrage);
							if(ouvrages.size() > 5 && x == 4) {
								JLabel Lsuite = new JLabel("...");
								Lsuite.setBounds(coords_x, (coords_y+m+17), coords_w, coords_h);
								PAffichage.add(Lsuite);
							}
							x++;
						}
						*/

						lnom_exemplaire_valeur.				setBounds((coords_x-12), coords_y, (coords_w+10), coords_h);
						lprenom_exemplaire_valeur.			setBounds((coords_x+100), coords_y, (coords_w), coords_h);
						lpseudo_exemplaire_valeur.			setBounds((coords_x+200), coords_y, (coords_w+150), coords_h);

						PAffichage.add(lnom_exemplaire_valeur);
						PAffichage.add(lprenom_exemplaire_valeur);
						PAffichage.add(lpseudo_exemplaire_valeur);
					}
				}
			} else if(numeros_traduction.size() == 0) {
				//Affichage de la base des auteur
				lnom_exemplaire_valeur.setText(this.lesTraductions.get(i).getLangue().substring(0, 1).toUpperCase() 
												+ this.lesTraductions.get(i).getLangue().substring(1).toLowerCase());/*
				lprenom_exemplaire_valeur.setText(this.lesExemplaires.get(i).getPrenomexemplaire().substring(0, 1).toUpperCase() 
												+ this.lesExemplaires.get(i).getPrenomexemplaire().substring(1).toLowerCase());
				if (this.lesExemplaires.get(i).getPseudonyme().length() != 0) 
					lpseudo_exemplaire_valeur.setText(this.lesExemplaires.get(i).getPseudonyme().substring(0, 1).toUpperCase() 
													+ this.lesExemplaires.get(i).getPseudonyme().substring(1).toLowerCase());

				//Ajout ..
				/*
				ArrayList<String> ouvrages = this.LesExemplaires.get(i).get..Array();
				int m = 0, x = 0;
				while(x<ouvrages.size() && x < 5) {					
					JLabel Louvrage = new JLabel(ouvrages.get(x));					
					m = (x+1) * 17 + 10;
					Louvrage.setBounds(coords_x, (coords_y+m), coords_w, coords_h);
					PAffichage.add(Louvrage);
					if(ouvrages.size() > 5 && x == 4) {
						JLabel Lsuite = new JLabel("...");
						Lsuite.setBounds(coords_x, (coords_y+m+17), coords_w, coords_h);
						PAffichage.add(Lsuite);
					}
					x++;					
				}*/

				lnom_exemplaire_valeur		.setBounds(coords_x, coords_y, coords_w, coords_h);
				lprenom_exemplaire_valeur	.setBounds((coords_x+100), coords_y, coords_w, coords_h);
				lpseudo_exemplaire_valeur	.setBounds((coords_x+220), coords_y, coords_w, coords_h);

				PAffichage.add(lnom_exemplaire_valeur);
				PAffichage.add(lprenom_exemplaire_valeur);
				PAffichage.add(lpseudo_exemplaire_valeur);
			}
		}
		return liste_radio;
	}
	
	/**
	 * Importation des données de traduction
	 */
	public void importer() {
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		String[] champs = {
			"numero_traduction",
			"langue_traduction"
		};
		String[][] req = bdd.lister(champs, "traduction");	
		for(int i=0; i<req.length; i++) {
			Integer.parseInt(req[i][0]);
			Traduction tr = new Traduction(req[i]);
			this.lesTraductions.add(tr);
		}
		bdd.Deconnexion();
	}
	
	/**
	 * Retourne le langue du traduction
	 * @param : numéro de traduction
	 * @return : langue de traduction
	**/
	public String rechercher(int numero) {
		for(int i=0; i<this.lesTraductions.size(); i++) {
			if(numero == this.lesTraductions.get(i).getNumero()) {
				return this.lesTraductions.get(i).getLangue();
			}
		}
		return null;
	}
	
	/**
	 * Cherche les traductions par la première lettre
	 * @param letter : lettres clées
	 * @param nombre_lettre : nombre de lettres
	 * @return
	 */
	public String[] getNomsByLetter(String letter, int nombre_lettre) {
		String[] les_traductions = new String[this.lesTraductions.size()];
		for(int i=0; i<this.lesTraductions.size(); i++) {
			int longueur = this.lesTraductions.get(i).getLangue().length();
			if(longueur >= nombre_lettre) longueur = nombre_lettre;
			if(this.lesTraductions.get(i).getLangue().substring(0, longueur).equalsIgnoreCase(letter)) {
				les_traductions[i] = this.lesTraductions.get(i).getLangue().substring(0, 1).toUpperCase() 
								+ this.lesTraductions.get(i).getLangue().substring(1).toLowerCase(); 
			}
		}
		return les_traductions;
	}
	
	/**
	 * Recherche des traduction par caractères
	 * Retourne une liste du numero des traductions
	 * @param letter : mot clé
	 * @param nombre_lettre : nombre de lettres
	 * @return tableau des numéros des traductions
	 */
	public ArrayList<Integer> getNumerosByLetter(String letter, int nombre_lettre) {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for(int i=0; i<this.lesTraductions.size(); i++) {
			int longueur = this.lesTraductions.get(i).getLangue().length();
			// gestion erreurs substring
			if(longueur >= nombre_lettre) longueur = nombre_lettre;
			if(this.lesTraductions.get(i).getLangue().substring(0, longueur).equalsIgnoreCase(letter)) 
				numeros.add(this.lesTraductions.get(i).getNumero());
		}		
		return numeros;
	}
	
	
	/**
	 *	Recherche d'une traduction par valeur chaînée
	 *	@return int
	**/
	public int rechercher(String langue) {
		for(int i=0; i<this.lesTraductions.size(); i++) {
			if(langue.equalsIgnoreCase(this.lesTraductions.get(i).getLangue())) return this.lesTraductions.get(i).getNumero();
		}
		return 0;
	}

	/**
	 * Suppression d'une traduction par valeur numérique
	 * @param  langue
	 * @return booléen
	 */
	public boolean supprimer(String langue) {
		boolean confirm = false;
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		int numero = rechercher(langue);
		for(int i=0; i<this.lesTraductions.size(); i++) {
			if(numero == this.lesTraductions.get(i).getNumero()) {
				confirm = true;
				this.lesTraductions.remove(i);
			}
		}
		bdd.suppression("traduction", numero);
		bdd.Deconnexion();
		return confirm;
	}

	public LinkedList<Traduction> getLesTraductions() {
		return this.lesTraductions;
	}
	
}
