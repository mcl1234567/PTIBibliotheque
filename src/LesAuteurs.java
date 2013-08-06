import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;

public class LesAuteurs {

	LinkedList<Auteur> lesAuteurs;
	private String[] champs = {"numero_auteur", "nom_auteur", "prenom_auteur", "pseudonyme_auteur", "lien_image_auteur"};

	/**
	 *  Instancier un auteur et insertion dans la bdds
	 */
	public LesAuteurs() {
		this.lesAuteurs = new LinkedList<Auteur>();
	}

	/**
	 * Ajouter un objet et un enregistrement dans la base de données
	 * @param n : nom
	 * @param p : prenom
	 * @param pseu : pseudonyme
	 * @param lien : lien de l'image
	 * @param Linfo_auteur : référence auteur
	 */
	public void ajouter(JTextField n, JTextField p, JTextField pseu, String lien, JLabel Linfo_auteur) {
		String[] champs = {"nom_auteur", "prenom_auteur", "pseudonyme_auteur", "lien_image_auteur"};
		String 	new_n = n.getText(), 
				new_p = p.getText(), 
				new_pseu = pseu.getText();
		if(n.getText().length() > 10) {
			new_n = n.getText().substring(0, 10).equalsIgnoreCase("Entrer un ") ? "" : n.getText();
		}
		if(p.getText().length() > 10) {
			new_p = p.getText().substring(0, 10).equalsIgnoreCase("Entrer un ") ? "" : p.getText();
		}
		if(pseu.getText().length() > 10) {
			new_pseu = pseu.getText().substring(0, 10).equalsIgnoreCase("Entrer un ") ? "" : pseu.getText();
		}
		n.setText(new_n); 
		pseu.setText(new_pseu); 
		p.setText(new_p);
		String[] valeur = {
			n.getText(), 
			p.getText(), 
			pseu.getText(), 
			lien
		};		
		
		Exploitation bdd = new Exploitation();
		bdd.chargerPilote();
		bdd.connexion();		
		int numero_auteur = bdd.insertion(champs, "auteur", valeur);
		bdd.deconnexion();

		Auteur a = new Auteur(numero_auteur, n.getText(), p.getText(), pseu.getText(), lien);
		this.lesAuteurs.add(a);
	}

	/**
	 * Synchronizer un livre avec un auteur
	 * @param ancien_livre : Suppression
	 * @param titre_livre : titre à ajouter
	 * @param nom_auteur : nom de l'auteur
	 * @param pseu_auteur : pseudonyme de l'auteur
	 * @return booléen
	
	public boolean associerAuteur(String ancien_livre, String titre_livre, String nom_auteur, String pseu_auteur) {
		boolean confirm = false;
		boolean suppression = true;

		for(int i=0; i<this.lesAuteurs.size(); i++) {
			if(this.lesAuteurs.get(i).getPseudo().equalsIgnoreCase("")) {
				// Pseudo inexistant
				if(this.lesAuteurs.get(i).getNom().equalsIgnoreCase(nom_auteur)) {
					if(!this.lesAuteurs.get(i).isLivresPossedes(titre_livre)) {
						this.lesAuteurs.get(i).setLivres(titre_livre);
						confirm = true;
						if(ancien_livre.equalsIgnoreCase("")) {
							suppression = false;
						}
						if(suppression) {
							this.lesAuteurs.get(i).suppressionLivres(ancien_livre);
						}
					}
				}
			} else if(pseu_auteur.equalsIgnoreCase(this.lesAuteurs.get(i).getPseudo())) {
				// avec pseudonyme
				if(!this.lesAuteurs.get(i).isLivresPossedes(titre_livre)) {
					this.lesAuteurs.get(i).setLivres(titre_livre);
					confirm = true;
					if(ancien_livre.equalsIgnoreCase("")) {
						suppression = false;
					}
					if(suppression) {
						this.lesAuteurs.get(i).suppressionLivres(ancien_livre);
					}
				}
			}
		}
		return confirm;
	}*/
	
	/**
	 * Synchronizer un livre avec un auteur
	 * @param ancien_livre : Suppression
	 * @param titre_livre : titre à ajouter
	 * @param nom_auteur : nom de l'auteur
	 * @param pseu_auteur : pseudonyme de l'auteur
	 * @return booléen
	 */
	public boolean associer(int numero, int numeroLivre, boolean synchro) {
		boolean confirm = false;

		if(synchro) {
			for(int i=0; i<this.lesAuteurs.size(); i++) {
				if(this.lesAuteurs.get(i).getNumero() == numero) {
					this.lesAuteurs.get(i).setLivres(numeroLivre);
					confirm = true;		
				}
			}
		} else {
			for(int i=0; i<this.lesAuteurs.size(); i++) {
				if(this.lesAuteurs.get(i).getNumero() == numero) {
					this.lesAuteurs.get(i).suppressionLivres(numeroLivre);
					confirm = true;
				}
			}
		}
		return confirm;
	}

	/**
	 * Vérifie l'existance du pseudonyme parmis les auteurs
	 * @param pseudo
	 * @return booléen
	 */
	public boolean verificationPseudo(String pseudo) {
		for(int i=0; i<this.lesAuteurs.size(); i++) {
			if(this.lesAuteurs.get(i).getPseudo().equalsIgnoreCase(pseudo)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Vérifie l'existance du nom parmis les auteurs
	 * @param nom
	 * @return booléen
	 */
	public boolean verificationNom(String nom) {
		for(int i=0; i<this.lesAuteurs.size(); i++) {
			if(this.lesAuteurs.get(i).getNom().equalsIgnoreCase(nom)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Importer des enregistrements de la bdd
	 */
	public void importer() {
		Exploitation bdd = new Exploitation();

		bdd.chargerPilote();
		bdd.connexion();
		String[][] req = bdd.lister(this.champs, "auteur");

		for(int i=0; i<req.length; i++) {
			Integer.parseInt(req[i][0]);
			Auteur a = new Auteur(req[i]);
			this.lesAuteurs.add(a);
		}
		bdd.deconnexion();
	}

	/**
	 * Modification des enregistrements de l'objet dans la bdd
	 * @param valeur
	 */
	public boolean exporter(int numero, String[] valeur) {
		String[] champs = {
				"nom_auteur", 
				"prenom_auteur", 
				"pseudonyme_auteur"
		};
		String champs_condition = "numero_auteur";
		String valeur_condition = String.valueOf(numero);

		Exploitation bdd = new Exploitation();
		bdd.chargerPilote();
		bdd.connexion();		
		
		boolean confirm = bdd.modification(champs, "auteur", valeur, champs_condition, valeur_condition);
		bdd.deconnexion();
		return confirm;
	}

	public boolean associer() {
		
		return true;
	}

	/**
	 * Synchronise les ouvrages de l'Artiste : ajouter / supprimer
	 * @param numero
	 * @param numeroLivre
	 * @param synchro : true : ajouter - false : supprimer
	 * @return
	 
	public String associer(int numero, int numeroLivre, boolean synchro) {
		String message = "";
		boolean confirm = false;
		if(synchro) {
			for(int i=0; i<this.lesAuteurs.size(); i++) {
				if(this.lesAuteurs.get(i).getNumero() == numero) confirm = this.lesAuteurs.get(i).setLivres(numeroLivre);
			message = confirm ? "Album référencé par l'artiste !" : "Album déjà référencé par cet artiste !";
		} else {
			for(int i=0; i<this.lesAuteurs.size(); i++) 
				if(this.lesAuteurs.get(i).isLivresPossedes(numeroLivre))
					confirm = this.lesAuteurs.get(i).suppressionLivres(numeroLivre);
			message = confirm ? "Suppression en cours.. !" : "Album pas référencer !";
		}
		return message;
	}**/

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
	public ButtonGroup lister(JPanel PAffichage, LesLivres lL, int nb_aff_debut, int nb_aff_fin, int type, JButton Btype, ArrayList<Integer> numerosAuteur) {
		ButtonGroup listeRadio = new ButtonGroup();
		String listeVide = "Pour ajouter un auteur à la bibliothèque : Auteur -> Ajouter un auteur.";
		int coords_x = 30, coords_y = 50, coords_w = 180, coords_h = 20;
		if(Btype != null) {
			PAffichage.add(Btype);
		}

		if(this.lesAuteurs.size() > 0) {
			JLabel lnom_auteur = new JLabel("nom".toUpperCase());
			JLabel lprenom_auteur = new JLabel("prénom".toUpperCase());
			JLabel lpseudo_auteur = new JLabel("pseudonyme".toUpperCase());
			JLabel Llib_ill_auteur = new JLabel("Illustration".toUpperCase());
			JLabel page_prem = new JLabel("Résultats : de "  + String.valueOf(nb_aff_debut));
			JLabel page_dern = new JLabel(" à " + String.valueOf(nb_aff_fin));
			JLabel page_total = new JLabel("");
			String pt = numerosAuteur.size() == 0 ? "sur " + String.valueOf(this.lesAuteurs.size()) : "sur " + String.valueOf(numerosAuteur.size());
			page_total.setText(pt);
			lnom_auteur.		setBounds(coords_x, 		(coords_y-20), coords_w, coords_h);
			lprenom_auteur.		setBounds((coords_x+100), 	(coords_y-20), coords_w, coords_h);
			lpseudo_auteur.		setBounds((coords_x+220), 	(coords_y-20), coords_w, coords_h);
			Llib_ill_auteur.	setBounds((coords_x+400), 	(coords_y-20), (coords_w+150), coords_h);
			page_prem.			setBounds(420, 				350, 			100, 20);
			page_dern.			setBounds(520, 				350, 			40, 20);
			page_total.			setBounds(550, 				350, 			70, 20);
			PAffichage.add(page_prem);
			PAffichage.add(page_dern);
			PAffichage.add(page_total);
			PAffichage.add(lnom_auteur);
			PAffichage.add(lprenom_auteur);
			PAffichage.add(lpseudo_auteur);
			PAffichage.add(Llib_ill_auteur);
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
				JRadioButton Bradio = new JRadioButton(this.lesAuteurs.get(i).getNom());				
				Bradio.setBounds(650, (coords_y+40), 20, 20);
				Bradio.setActionCommand(this.lesAuteurs.get(i).getNom());
				listeRadio.add(Bradio);
				PAffichage.add(Bradio);
			}
			JLabel lnom_auteur_valeur = new JLabel("");
			JLabel lprenom_auteur_valeur = new JLabel("");
			JLabel lpseudo_auteur_valeur = new JLabel("");
			JLabel lnombre_ouvrage_auteur_valeur = new JLabel("");

			if(numerosAuteur.size() > 0) {
				// Affichage spécifique
				for(int y=0; y<this.lesAuteurs.size(); y++) {
					if(numerosAuteur.get(i) == this.lesAuteurs.get(y).getNumero()) {
						lnom_auteur_valeur.setText(this.lesAuteurs.get(y).getNom().substring(0, 1).toUpperCase() 
													+ this.lesAuteurs.get(y).getNom().substring(1).toLowerCase());
						
						lprenom_auteur_valeur.setText(this.lesAuteurs.get(y).getPrenom().substring(0, 1).toUpperCase() 
													+ this.lesAuteurs.get(y).getPrenom().substring(1).toLowerCase());
						
						if(this.lesAuteurs.get(y).getPseudo().length() != 0) { 
							lpseudo_auteur_valeur.setText(this.lesAuteurs.get(y).getPseudo().substring(0, 1).toUpperCase() 
														+ this.lesAuteurs.get(y).getPseudo().substring(1).toLowerCase());
						} else {
							lpseudo_auteur_valeur.setText("-");
						}

						lnombre_ouvrage_auteur_valeur.setText(String.valueOf(this.lesAuteurs.get(i).getLivres().size()) + " ouvrage(s) : ".toUpperCase());
						
						// Ajout oeuvres
						/*ArrayList<String> livres = lL.rechercher(this.lesAuteurs.get(y).getLivres());
						int m = 0, x = 0;
						while(x < livres.size() && x < 5) {							
							JLabel Louvrage = new JLabel(livres.get(x));					
							m = (x+1) * 17 + 10;
							Louvrage.setBounds(coords_x, (coords_y+m), coords_w, coords_h);
							PAffichage.add(Louvrage);
							if(livres.size() > 5 && x == 4) {
								JLabel Lsuite = new JLabel("...");
								Lsuite.setBounds(coords_x, (coords_y+m+17), coords_w, coords_h);
								PAffichage.add(Lsuite);
							}
							x++;
						}*/

						JLabel Limage_auteur = new JLabel(new ImageIcon(this.lesAuteurs.get(y).getLien()), JLabel.CENTER);

						lnom_auteur_valeur.				setBounds((coords_x-12), coords_y, (coords_w+10), coords_h);
						lprenom_auteur_valeur.			setBounds((coords_x+100), coords_y, (coords_w), coords_h);
						lpseudo_auteur_valeur.			setBounds((coords_x+200), coords_y, (coords_w+150), coords_h);
						lnombre_ouvrage_auteur_valeur.	setBounds(coords_x, (coords_y+15), coords_w, coords_h);
						Limage_auteur.					setBounds((coords_x+350), (coords_y+25), (coords_w+40), 95);

						PAffichage.add(lnom_auteur_valeur);
						PAffichage.add(lprenom_auteur_valeur);
						PAffichage.add(lpseudo_auteur_valeur);
						PAffichage.add(Limage_auteur);
					}
				}
			} else if(numerosAuteur.size() == 0) {
				// Affichage de la base des auteurs
				lnom_auteur_valeur.setText(this.lesAuteurs.get(i).getNom().substring(0, 1).toUpperCase() 
										+ this.lesAuteurs.get(i).getNom().substring(1).toLowerCase());
				
				lprenom_auteur_valeur.setText(this.lesAuteurs.get(i).getPrenom().substring(0, 1).toUpperCase() 
											+ this.lesAuteurs.get(i).getPrenom().substring(1).toLowerCase());
				
				if(this.lesAuteurs.get(i).getPseudo().length() != 0) { 
					lpseudo_auteur_valeur.setText(this.lesAuteurs.get(i).getPseudo().substring(0, 1).toUpperCase() 
							+ this.lesAuteurs.get(i).getPseudo().substring(1).toLowerCase());
				} else {
					lpseudo_auteur_valeur.setText("-");					
				}

				lnombre_ouvrage_auteur_valeur.setText(String.valueOf(this.lesAuteurs.get(i).getLivres().size()) 
													+ " ouvrage(s) : ".toUpperCase());

				JLabel Limage_auteur = new JLabel(new ImageIcon(this.lesAuteurs.get(i).getLien()), JLabel.CENTER);

				// Ajout oeuvres
				/*	ArrayList<String> livres = lL.rechercher(this.lesAuteurs.get(i).getLivres());
				int m = 0, x = 0;
				while(x<livres.size() && x < 5) {					
					JLabel Louvrage = new JLabel(livres.get(x));					
					m = (x+1) * 17 + 10;
					Louvrage.setBounds(coords_x, (coords_y+m), coords_w, coords_h);
					PAffichage.add(Louvrage);
					if(livres.size() > 5 && x == 4) {
						JLabel Lsuite = new JLabel("...");
						Lsuite.setBounds(coords_x, (coords_y+m+17), coords_w, coords_h);
						PAffichage.add(Lsuite);
					}
					x++;					
				}*/

				lnom_auteur_valeur				.setBounds(coords_x, 		coords_y, coords_w, coords_h);
				lprenom_auteur_valeur			.setBounds(coords_x+100, 	coords_y, coords_w, coords_h);
				lpseudo_auteur_valeur			.setBounds(coords_x+220, 	coords_y, coords_w, coords_h);
				lnombre_ouvrage_auteur_valeur	.setBounds(coords_x, 		coords_y+15, coords_w, coords_h);
				Limage_auteur					.setBounds(coords_x+350, 	coords_y+25, coords_w+40, 95);

				PAffichage.add(lnom_auteur_valeur);
				PAffichage.add(lprenom_auteur_valeur);
				PAffichage.add(lpseudo_auteur_valeur);
				PAffichage.add(lnombre_ouvrage_auteur_valeur);
				PAffichage.add(Limage_auteur);
			}
		}
		return listeRadio;
	}

	/**
	 * Retourne les numéros
	 * @param : numéros du Album
	 * @return : titres du Album
	 */
	public ArrayList<String> rechercher(ArrayList<Integer> numeros) {
		ArrayList<String> titres = new ArrayList<String>();

		for(int i=0; i<this.lesAuteurs.size(); i++) {
			for(int j=0; j<numeros.size(); j++) {
				if(numeros.get(j) == this.lesAuteurs.get(i).getNumero()) {
					titres.add(this.lesAuteurs.get(i).getNom());
				}
			}
		}
		return titres;
	}
	
	/**
	 * Rechercher un nom d'auteur avec titre de livre, le prenom ou le pseudo de l'auteur
	 * @param liaison : chaîne contenant le lien à l'auteur
	 * @param type : type du lien avec l'auteur (<br />0 : titre du livre <br /> 1 : pseudo de l'auteur <br /> 2 : prenom de l'auteur)
	 * @return retourne la chaine contenant le nom de l'auteur	
	public String rechercherNomAuteur(String liaison, int type) {
		} else if(type == 1) {
			//pseudo_auteur
			for(int i=0; i<this.lesAuteurs.size(); i++) {
				if(this.lesAuteurs.get(i).getPseudo().equalsIgnoreCase(liaison)) {
					return this.lesAuteurs.get(i).getNom();
				}
			}
		} else if(type == 2) {
			//prenom_auteur
			for(int i=0; i<this.lesAuteurs.size(); i++) {
				if(this.lesAuteurs.get(i).getPrenom().equalsIgnoreCase(liaison)) {
					return this.lesAuteurs.get(i).getNom();
				}
			}
		}
		return null;
	} */

	/**
	*	Rechercher un auteur avec numero_livre
	*	@param n : numero du livre
	*	@return int : numero de l'auteur

	public int rechercherAuteurByNum(String titre) {
		for(int i=0; i<this.lesAuteurs.size(); i++) {
			if(this.lesAuteurs.get(i).isLivresPossedes(titre)) {
				return this.lesAuteurs.get(i).getNumero();
			}
		}
		return 0;
	}**/

	/**
	 * Rechercher un auteur avec son pseudo ou son nom
	 * @param nom : nom de l'auteur
	 * @return numero de l'auteur
	 */
	public int rechercher(String auteur_ref) {
		for(int i=0; i<this.lesAuteurs.size(); i++) {
			if(auteur_ref.equalsIgnoreCase(this.lesAuteurs.get(i).getPseudo()) || auteur_ref.equalsIgnoreCase(this.lesAuteurs.get(i).getNom())) {
				return this.lesAuteurs.get(i).getNumero();
			}
		}
		return 0;
	}

	/**
	 * Modification des informations d'un auteur
	 * @param nom : nom auteur
	 * @param type : étape
	 * @param numero : numero auteur
	 * @param modif : valeurs à modifier
	 * @return les informations du auteur
	 */
	public String[] modifier(String nom, boolean type, int numero, String[] modif) {
		if(!type) {
			for(int i=0; i<this.lesAuteurs.size(); i++) {
				if(this.lesAuteurs.get(i).getNom().equalsIgnoreCase(nom)) {
					String[] info_auteur = {
						String.valueOf(this.lesAuteurs.get(i).getNumero()), 
						this.lesAuteurs.get(i).getNom(), 
						this.lesAuteurs.get(i).getPrenom(), 
						this.lesAuteurs.get(i).getPseudo()
					};
					return info_auteur;
				}
			}
		} else {
			for(int i=0; i<this.lesAuteurs.size(); i++) {
				if(this.lesAuteurs.get(i).getNumero() == numero) {
					this.lesAuteurs.get(i).set(modif);
					boolean confirm = this.exporter(numero, modif);
					String[] valid = {"modifie"};
					if(confirm) {
						return valid;
					} else {
						return null;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Suppression d'un auteur par valeur numérique
	 * @param n
	 * @param nom
	 * @return
	 */
	public boolean supprimer(String nom) {
		boolean confirm = false;
		int numero = rechercher(nom);

		for(int i=0; i<this.lesAuteurs.size(); i++) {
			if(numero == this.lesAuteurs.get(i).getNumero()) {
				confirm = this.lesAuteurs.get(i).removeFiles();
				this.lesAuteurs.remove(i);
			}
		}

		Exploitation bdd = new Exploitation();
		bdd.chargerPilote();
		bdd.connexion();
		bdd.suppression("auteur", numero);
		bdd.deconnexion();

		return confirm;
	}

	/**
	 * Cherche les auteurs par la première lettre
	 * @param letter
	 * @param nombre_lettre
	 * @return
	 */
	public String[] getAuteursByLetter(String letter, int nombre_lettre) {
		String[] les_noms = new String[this.lesAuteurs.size()];
		int j = 0;

		for(int i=0; i<this.lesAuteurs.size(); i++) {
			int long_nom = this.lesAuteurs.get(i).getNom().length();
			int long_pre = this.lesAuteurs.get(i).getPrenom().length();
			int long_pseu = this.lesAuteurs.get(i).getPseudo().length();

			// Gestion erreurs substring
			if(long_nom >= nombre_lettre) {
				long_nom = nombre_lettre;
			}
			if(long_pre >= nombre_lettre) {
				long_pre = nombre_lettre;
			}
			if(long_pseu >= nombre_lettre) {
				long_pseu = nombre_lettre;
			}

			if(this.lesAuteurs.get(i).getPseudo().length() == 0) {
				// recherche sans pseudo
				if(this.lesAuteurs.get(i).getNom().substring(0, long_nom).equalsIgnoreCase(letter) 
				|| this.lesAuteurs.get(i).getPrenom().substring(0, long_pre).equalsIgnoreCase(letter)) {
					// Association valide
					les_noms[j] = this.lesAuteurs.get(i).getNom().substring(0, 1).toUpperCase() + this.lesAuteurs.get(i).getNom().substring(1).toLowerCase(); 
					j++;
				}
			} else {
				// SI pseudo existant
				if(this.lesAuteurs.get(i).getNom().substring(0, long_nom).equalsIgnoreCase(letter) 
				|| this.lesAuteurs.get(i).getPrenom().substring(0, long_pre).equalsIgnoreCase(letter) 
				|| this.lesAuteurs.get(i).getPseudo().substring(0, long_pseu).equalsIgnoreCase(letter)) {
					// Association valide
					les_noms[j] = this.lesAuteurs.get(i).getPseudo().substring(0, 1).toUpperCase() 
								+ this.lesAuteurs.get(i).getPseudo().substring(1).toLowerCase(); 
					j++;
				}
			}
		}		
		return les_noms;
	}

	/**
	 * Recherche des auteurs par caractères
	 * Retourne une liste du numero des auteurs
	 * @param letter
	 * @param nombre_lettre
	 * @return
	 */
	public ArrayList<Integer> getNumerosByLetter(String letter, int nombre_lettre) {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for(int i=0; i<this.lesAuteurs.size(); i++) {
			int long_nom = this.lesAuteurs.get(i).getNom().length();
			int long_pre = this.lesAuteurs.get(i).getPrenom().length();
			int long_pseu = this.lesAuteurs.get(i).getPseudo().length();
			// Gestion erreurs substring
			if(long_nom >= nombre_lettre) {
				long_nom = nombre_lettre;
			}
			if(long_pre >= nombre_lettre) {
				long_pre = nombre_lettre;
			}
			if(long_pseu >= nombre_lettre) {
				long_pseu = nombre_lettre;
			}
			if(this.lesAuteurs.get(i).getNom().substring(0, long_nom).equalsIgnoreCase(letter) 
			|| this.lesAuteurs.get(i).getPrenom().substring(0, long_pre).equalsIgnoreCase(letter) 
			|| this.lesAuteurs.get(i).getPseudo().substring(0, long_pseu).equalsIgnoreCase(letter)) {
				numeros.add(this.lesAuteurs.get(i).getNumero());
			}
		}		
		return numeros;
	}
	
	/**
	 * Retourne l'auteur du livre
	 * @param numero
	 * @return entier
	 */
	public int get(int numero) {
		for(int i=0; i<this.lesAuteurs.size(); i++) {
			if(this.lesAuteurs.get(i).isLivresPossedes(numero)) {
				return this.lesAuteurs.get(i).getNumero();
			}
		}
		return 0;
	}

	/**
	 * Retourne la liste des auteurs
	 * @return liste des objets Auteur
	 */
	public LinkedList<Auteur> getlesAuteurs() { return this.lesAuteurs; }
	
}