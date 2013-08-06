import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;

public class LesEditions {
	LinkedList<Edition> lesEditions;

	public LesEditions() {
		this.lesEditions = new LinkedList<Edition>();
	}

	/**
	 * Ajouter un objet et un enregistrement danss la bdd
	 * @param m
	 * @return
	 */
	public boolean ajouter(JTextField m) {
		String[] champs = {
			"libelle_edition"
		};
		String[] valeur = {
			m.getText()
		};

		Exploitation bdd = new Exploitation();
		bdd.chargerPilote();
		bdd.connexion();
		int numero = bdd.insertion(champs, "edition", valeur);

		Edition e = new Edition(numero, valeur);
		this.lesEditions.add(e);
		return (numero != 0);
	}

	/**
	 * Importer des enregistrements de la bdd
	 */
	public void importer() {
		String[] champs = {
			"numero_edition", 
			"libelle_edition"
		};
		
		Exploitation bdd = new Exploitation();
		bdd.chargerPilote();
		bdd.connexion();
		String[][] req = bdd.lister(champs, "edition");

		for(int i=0; i<req.length; i++) {
			Edition e = new Edition(Integer.parseInt(req[i][0]), req[i][1]);
			this.lesEditions.add(e);
		}
		bdd.deconnexion();
	}

	/**
	 * Lister les enregistrements de l'objet
	 * @param PAffichage
	 */
	public ButtonGroup lister(JPanel PAffichage, int nb_aff_debut, int nb_aff_fin, int type, JButton Btype, ArrayList<Integer> numerosEdition) {
		ButtonGroup liste_radio = new ButtonGroup();
		int coords_x = 30, coords_y = 50, coords_w = 180, coords_h = 20;
		try {
			PAffichage.add(Btype);
		} catch (NullPointerException e) {	}

		if(this.lesEditions.size() == 0) {
			JLabel lvide = new JLabel("Pour ajouter une édition à la bibliothèque : Edition -> Ajouter une édition.");
			lvide.setBounds(coords_x, 25, 400, 20);
			PAffichage.add(lvide);
		} else if(this.lesEditions.size() > 0) {
			JLabel page_prem = new JLabel("Résultats : de "  + String.valueOf(nb_aff_debut));
			JLabel page_dern = new JLabel(" à " + String.valueOf(nb_aff_fin));
			JLabel page_total = new JLabel("");
			String pt = numerosEdition.size() == 0 ? "sur " + String.valueOf(this.lesEditions.size()) : "sur " + String.valueOf(numerosEdition.size());
			page_total.setText(pt);
			JLabel ledition_1 = new JLabel(" - Libellé - ".toUpperCase());
			JLabel ledition_2 = new JLabel(" - Libellé - ".toUpperCase());
			ledition_1.setBounds(coords_x, coords_y-20, coords_w, coords_h);
			ledition_2.setBounds(coords_x+210, coords_y-20, coords_w, coords_h);
			PAffichage.add(ledition_1);
			PAffichage.add(ledition_2);
			page_prem	.setBounds(420,	350, 100, 20);
			page_dern	.setBounds(520,	350, 40, 20);
			page_total	.setBounds(550,	350, 70, 20);
			PAffichage.add(page_prem);
			PAffichage.add(page_dern);
			PAffichage.add(page_total);
		}
		for(int i=nb_aff_debut; i<nb_aff_fin; i++) {
			if((i - nb_aff_debut) != 0) {
				coords_y += 140;
			}
			if(type != 0) {
				JRadioButton Bradio = new JRadioButton(this.lesEditions.get(i).getLibelle());				
				Bradio.setBounds(650, (coords_y+40), 20, 20);
				Bradio.setActionCommand(this.lesEditions.get(i).getLibelle());
				liste_radio.add(Bradio);
				PAffichage.add(Bradio);
			}
			JLabel Lmention = new JLabel("");

			if(numerosEdition.size() > 0) {
				// Affichage spécifique
				for(int y=0; y<this.lesEditions.size(); y++) {
					if(numerosEdition.get(i) == this.lesEditions.get(y).getNumero()) {
						Lmention.setText(this.lesEditions.get(y).getLibelle().substring(0, 1).toUpperCase() 
								+ this.lesEditions.get(y).getLibelle().substring(1).toLowerCase());
						
						// Ajout auteurs
						/*ArrayList<String> ouvrages = this.lesEditions.get(y).GetAuteursArray();
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
						}*/
						JLabel Limage_auteur = new JLabel(new ImageIcon(this.lesEditions.get(y).getLien()), JLabel.CENTER);

						Lmention.setBounds((coords_x-12), coords_y, (coords_w+10), coords_h);
						Limage_auteur.setBounds((coords_x+350), (coords_y+25), (coords_w+40), 95);

						PAffichage.add(Lmention);
						PAffichage.add(Limage_auteur);
					}
				}
			} else if(numerosEdition.size() == 0) {
				// Affichage de la base des auteurs
				Lmention.setText(this.lesEditions.get(i).getLibelle().substring(0, 1).toUpperCase() 
								+ this.lesEditions.get(i).getLibelle().substring(1).toLowerCase());				
				JLabel Limage_auteur = new JLabel(new ImageIcon(this.lesEditions.get(i).getLien()), JLabel.CENTER);
				//Ajout oeuvres
				/*ArrayList<String> ouvrages = this.lesEditions.get(i).GetAuteursArray();
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

				Lmention.		setBounds(coords_x, coords_y, coords_w, coords_h);
				Limage_auteur.	setBounds((coords_x+350), (coords_y+25), (coords_w+40), 95);

				PAffichage.add(Lmention);
				PAffichage.add(Limage_auteur);
			}
		}
		/*for(int i=0; i<this.lesEditions.size(); i++) {
			if(i%2 != 0 && i != 0) coords_x = 30*8;
			if(i%2 == 0 && i != 0) {
				coords_y += 50;
				coords_x = 30;
			}
			JLabel label_auteur_affichage_lib = new JLabel(this.lesEditions.get(i).GetLibelleEdition().substring(0, 1).toUpperCase() 
															+ this.lesEditions.get(i).GetLibelleEdition().substring(1).toLowerCase());
			label_auteur_affichage_lib.setBounds((coords_x+15), coords_y, coords_w, coords_h);			
			PAffichage.add(label_auteur_affichage_lib);
		}*/
		return liste_radio;
	}
	
	/**
	 * Cherche les éditions par la première lettre
	 * @param letter : lettres clées
	 * @param nombre_lettre : nombre de lettres
	 * @return
	 */
	public String[] getNomsByLetter(String letter, int nombre_lettre) {
		String[] les_editions = new String[this.lesEditions.size()];

		for(int i=0; i<this.lesEditions.size(); i++) {
			int long_lib = this.lesEditions.get(i).getLibelle().length();

			if(long_lib >= nombre_lettre) {
				long_lib = nombre_lettre;
			}
			if(this.lesEditions.get(i).getLibelle().substring(0, long_lib).equalsIgnoreCase(letter)) {
				les_editions[i] = this.lesEditions.get(i).getLibelle().substring(0, 1).toUpperCase() 
								+ this.lesEditions.get(i).getLibelle().substring(1).toLowerCase(); 
			}
		}	
		return les_editions;
	}

	/**
	 * Retourne le libellé de l'édition
	 * @param : numéro de édition
	 * @return : libellé de édition
	**/
	public String rechercher(int numero) {
		for(int i=0; i<this.lesEditions.size(); i++) { 
			if(numero == this.lesEditions.get(i).getNumero()) {
				return this.lesEditions.get(i).getLibelle();
			}
		}
		return "";
	}
	
	/**
	 * Exportation des enregistrements de l'objet
	 * @param valeur
	 */
	public boolean exporter(int numero, String[] valeur) {
		String[] champs = {"numero_edition", "libelle_edition"};
		String champs_condition = String.valueOf(numero);
		String valeur_condition = "numero_edition";
		Exploitation bdd = new Exploitation();
		
		bdd.chargerPilote();
		bdd.connexion();
		boolean confirm = bdd.modification(champs, "edition", valeur, champs_condition, valeur_condition);
		bdd.deconnexion();

		return confirm;
	}

	/**
	 * Recherche des éditions par caractères
	 * Retourne une liste du numero des éditions
	 * @param letter
	 * @param nombre_lettre
	 * @return tableau de numéros d'éditions
	 */
	public ArrayList<Integer> getNumerosByLetter(String letter, int nombre_lettre) {
		ArrayList<Integer> numeros = new ArrayList<Integer>();

		for(int i=0; i<this.lesEditions.size(); i++) {
			int long_nom = this.lesEditions.get(i).getLibelle().length();
			// gestion erreurs substring
			if(long_nom >= nombre_lettre) long_nom = nombre_lettre;			
			if(this.lesEditions.get(i).getLibelle().substring(0, long_nom).equalsIgnoreCase(letter)) {
				numeros.add(this.lesEditions.get(i).getNumero());
			}
		}		
		return numeros;
	}

	
	/**
	 * Modification des informations de l'édition
	 * @param t : titre
	 * @param type : étape
	 * @param n : numero_livre
	 * @param modif : valeurs à modifier
	 * @return les informations du livre
	 */
	public String[] modifier(String mention, boolean type, int numero, String[] modif) {
		if(!type) {
			for(int i=0; i<this.lesEditions.size(); i++) {
				if(this.lesEditions.get(i).getLibelle().equalsIgnoreCase(mention)) {
					String[] info_livre = {String.valueOf(this.lesEditions.get(i).getNumero()), this.lesEditions.get(i).getLibelle()};
					return info_livre;
				}
			}
		} else {
			for(int i=0; i<this.lesEditions.size(); i++) {
				if(this.lesEditions.get(i).getNumero() == numero) {
					this.lesEditions.get(i).SetEdition(modif);
					boolean confirm = this.exporter(numero, modif);
					String[] valid = {"modifie"};
					if(confirm) return valid;
					else return null;
				}
			}
		}
		return null;
	}

	/**
	 *	Suppression d'une édition par valeur numérique 
	Obsolète ?
	public void SupprimerEdition(String n) {
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		int valeur = RechercherEdition(n);
		for(int i=0; i<this.lesEditions.size(); i++) {
			if(valeur == this.lesEditions.get(i).GetNumeroEdition()) this.lesEditions.remove(i);
		}
		bdd.Suppression("edition", valeur);
		bdd.Deconnexion();
	}
*/
	/**
	 *	Recherche d'une édition par valeur chaînée
	 *	@return int
	**/
	public int rechercher(String n) {
		for(int i=0; i<this.lesEditions.size(); i++) {
			if(n.equalsIgnoreCase(this.lesEditions.get(i).getLibelle())) {
				return this.lesEditions.get(i).getNumero();
			}
		}
		return 0;
	}

	/**
	 * Suppression d'une édition par valeur numérique
	 * @param n
	 * @param nom
	 * @return booléen
	 */
	public boolean supprimer(String mention) {
		boolean confirm = false;
		Exploitation bdd = new Exploitation();

		bdd.chargerPilote();
		bdd.connexion();

		int numero = rechercher(mention);

		for(int i=0; i<this.lesEditions.size(); i++) {
			if(numero == this.lesEditions.get(i).getNumero()) {
				confirm = this.lesEditions.get(i).removeFiles();
				this.lesEditions.remove(i);
			}
		}
		bdd.suppression("edition", numero);
		bdd.deconnexion();

		return confirm;
	}
	
	/**
	 * Retourne la liste des éditions
	 * @return
	 */
	public LinkedList<Edition> getlesEditions() { return this.lesEditions; }

}