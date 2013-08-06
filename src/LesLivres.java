import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;

public class LesLivres {
	LinkedList<Livre> lesLivres;

	public LesLivres() {
		lesLivres = new LinkedList<Livre>();
	}

	/**
	 * Ajouter un objet et un enregistrement danss la bdd
	 * @param auteur : auteur du livre
	 * @param ti : titre du livre
	 * @param desc : description du livre
	 * @param type : type / genre du livre
	 * @param lA : liste des auteurs
	 * @param lien : lien de l'image du livre
	 * @param Linfo_livre : information pour l'utilisateur
	 * @return numero_livre : numero du livre à insérer dans la liste des livres de l'auteur
	 */
	public boolean ajouter(LesAuteurs lA, String[] nv) {
		boolean confirm = false;
		String[] valeur = {
			nv[0],
			nv[1],
			nv[2], 
			nv[3], 
			nv[4]
		};
		String[] champs = {	
			"numero_auteur", 
			"titre_livre", 
			"description_livre", 
			"type_livre", 
			"lien_image_livre"
		};
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		int numero = bdd.insertion(champs, "livre", valeur);
		bdd.Deconnexion();
		Livre li = new Livre(numero, nv);
		confirm = (numero != 0);
		//if(lA.verificationPseudo(ref_auteur)) lA.associerAuteur("", ti.getText(), lA.rechercherNomAuteur(ref_auteur, 1), ref_auteur);
		//else lA.associerAuteur("", ti.getText(), ref_auteur, ""); // ref_auteur : supposé nom de l'auteur et pas de pseudo
		lesLivres.add(li);
		return confirm;
	}

	/**
	 * Importer des enregistrements de la bdd
	 * @return informations de la bdd pour instanciation des objets
	 */
	public String[][] importer(LesAuteurs lA) {
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		String[] champs = {
				"numero_auteur", 
				"numero_livre", 
				"titre_livre", 
				"description_livre", 
				"type_livre", 
				"lien_image_livre"
			};
		String[][] req = bdd.lister(champs, "livre");
		for(int i=0; i<req.length; i++) {
			Livre li = new Livre(req[i]);
			//lA.associerAuteur(req[i][2], Integer.parseInt(req[i][0]), true); // ASSOCIATION LIVRE - AUTEUR
			this.lesLivres.add(li);
		}
		bdd.Deconnexion();
		return req;
	}

	/**
	 * Synchronizer un livre avec un auteur
	 * @param ancien_livre : Suppression
	 * @param titre_livre : titre à ajouter
	 * @param nom_auteur : nom de l'auteur
	 * @param pseu_auteur : pseudonyme de l'auteur
	 * @return booléen
	 */
	public boolean associer(int numero, int numeroExemplaire, boolean synchro) {
		boolean confirm = false;
		if(synchro) {
			for(int i=0; i<this.lesLivres.size(); i++) {
				if(this.lesLivres.get(i).getNumero() == numero) {
					this.lesLivres.get(i).setExemplaires(numeroExemplaire);
					confirm = true;		
				}
			}
		} else {
			for(int i=0; i<this.lesLivres.size(); i++) {
				if(this.lesLivres.get(i).getNumero() == numero) {
					this.lesLivres.get(i).suppressionExemplaires(numeroExemplaire);
					confirm = true;
				}
			}
		}
		return confirm;
	}
	
	/**
	 * Affichage de la bibliothèque, images, descriptions des oeuvres par page
	 * @param PAffichage : affichage
	 * @param lA : liste des auteurs
	 * @param nb_aff_debut : ligne de début d'affichage de la bdd
	 * @param nb_aff_fin : ligne de fin d'affichage de la bdd
	 */
	public ButtonGroup lister(JPanel PAffichage, LesAuteurs lA, int nb_aff_debut, int nb_aff_fin, int type, JButton Btype, ArrayList<Integer> liste) {
		if(Btype != null) PAffichage.add(Btype);
		ButtonGroup listeRadio = new ButtonGroup();
		String listeVide = "Pour ajouter un livre à la bibliothèque : Livre -> Ajouter un livre.";
		int coords_x = 35, coords_y = 50, coords_w = 90, coords_h = 20;
		
		//Affichage menu contextuel
		if(this.lesLivres.size() >= 1) {
			JLabel ltitre_livre = new JLabel("Titre".toUpperCase());
			JLabel ldesc_livre = new JLabel("Description".toUpperCase());
			JLabel lauteur_livre = new JLabel("Auteur".toUpperCase());
			JLabel Limage_titre = new JLabel("Illustration".toUpperCase());
			JLabel Ltype_livre = new JLabel("Type".toUpperCase());
			JLabel page_prem = new JLabel("Résultats : de "  + String.valueOf(nb_aff_debut));  
			JLabel page_dern = new JLabel(" à " + String.valueOf(nb_aff_fin));
			JLabel page_total = new JLabel("sur " + String.valueOf(this.lesLivres.size()));
			if(liste.size() > 0) page_total.setText("sur " + String.valueOf(liste.size())); 
			ltitre_livre.		setBounds(coords_x, 		(coords_y-25), 210, 20);
			ldesc_livre.		setBounds((coords_x+100), 	(coords_y-25), 210, 20);
			lauteur_livre.		setBounds((coords_x+250), 	(coords_y-25), 210, 20);
			Limage_titre.		setBounds((coords_x+400), 	(coords_y-25), (coords_w+150), coords_h);
			Ltype_livre.		setBounds((coords_x+600), 	(coords_y-25), (coords_w+150), coords_h);
			page_prem.			setBounds(420, 				350, 			100, 20);
			page_dern.			setBounds(520, 				350, 			40, 20);
			page_total.			setBounds(550, 				350, 			70, 20);			
			PAffichage.add(ltitre_livre);
			PAffichage.add(ldesc_livre);
			PAffichage.add(lauteur_livre);
			PAffichage.add(Limage_titre);
			PAffichage.add(Ltype_livre);
			PAffichage.add(page_prem);
			PAffichage.add(page_dern);
			PAffichage.add(page_total);
		}
		//Affichage du message de bibliothèque vide 
		else {
			//PAffichage.removeAll();	 // suppression du bouton 'retour'		
			JLabel label_livre_affichage_lib1 = new JLabel(listeVide);
			label_livre_affichage_lib1.setBounds(coords_x, 25, 400, 20);
			PAffichage.add(label_livre_affichage_lib1);
		}
		//Affichage des oeuvres
		for(int i=nb_aff_debut; i<nb_aff_fin; i++) {
			int x = i - nb_aff_debut;
			if(x != 0) {
				coords_y += 105;
			}
			if(type != 0) {
				if(type < 3) {
					//modifier ou supprimer
					JRadioButton Bradio = new JRadioButton(this.lesLivres.get(i).getTitre());				
					Bradio.setBounds(650, (coords_y+40), 20, 20);
					Bradio.setActionCommand(this.lesLivres.get(i).getTitre());
					listeRadio.add(Bradio);
					PAffichage.add(Bradio);
				} else if(type == 4 || type == 6) {
					// sélection livre pour acheter exemplaires
					JRadioButton Bradio = new JRadioButton(this.lesLivres.get(i).getTitre());				
					Bradio.setBounds(650, (coords_y+40), 20, 20);
					Bradio.setActionCommand(this.lesLivres.get(i).getTitre());
					listeRadio.add(Bradio);
					PAffichage.add(Bradio);
				}
			}
			JLabel ltitre_livre_valeur = new JLabel("");
			JLabel ldesc_livre_valeur = new JLabel("");
			JLabel lauteur_livre_valeur = new JLabel("");
			JLabel ltype_livre_valeur = new JLabel("");
	
			if(liste.size() > 0) {
				//	Affichage spécifique de la recherche
				for(int y=0; y<this.lesLivres.size(); y++) {
					if(liste.get(i) == this.lesLivres.get(y).getNumero()) {
						ltitre_livre_valeur.setText(this.lesLivres.get(y).getTitre().substring(0, 1).toUpperCase() 
													+ this.lesLivres.get(y).getTitre().substring(1).toLowerCase());
						ldesc_livre_valeur.setText(this.lesLivres.get(y).getDescription().substring(0, 1).toUpperCase() 
													+ this.lesLivres.get(y).getDescription().substring(1).toLowerCase());
						
						//String auteur = lA.rechercherPseudoAuteur(this.lesLivres.get(y).getTitre());
						//if(auteur == null) auteur = lA.rechercherNomAuteur(this.lesLivres.get(y).getTitre(), 0);
						
						//lauteur_livre_valeur.setText(auteur.substring(0, 1).toUpperCase() 
							//						+ auteur.substring(1).toLowerCase());
						
						ltype_livre_valeur.setText(this.lesLivres.get(y).getType().substring(0, 1).toUpperCase() 
													+ this.lesLivres.get(y).getType().substring(1).toLowerCase());
						
						JLabel Limage_oeuvre = new JLabel(new ImageIcon(this.lesLivres.get(y).getLien()), JLabel.CENTER);

						ltitre_livre_valeur.	setBounds((coords_x-12), coords_y, (coords_w+10), coords_h);
						ldesc_livre_valeur.		setBounds((coords_x+100), coords_y, (coords_w), coords_h);
						lauteur_livre_valeur.	setBounds((coords_x+250), coords_y, (coords_w+150), coords_h);
						Limage_oeuvre.			setBounds((coords_x+370), (coords_y-5), (coords_w+80), 95);
						ltype_livre_valeur.		setBounds((coords_x+600), coords_y, (coords_w+150), coords_h);

						PAffichage.add(ltitre_livre_valeur);
						PAffichage.add(ldesc_livre_valeur);
						PAffichage.add(lauteur_livre_valeur);
						PAffichage.add(Limage_oeuvre);
						PAffichage.add(ltype_livre_valeur);
					}
				}
			} else if(liste.size() == 0) {
				//	Affichage de la base de données
				ltitre_livre_valeur.setText(this.lesLivres.get(i).getTitre().substring(0, 1).toUpperCase() 
											+ this.lesLivres.get(i).getTitre().substring(1).toLowerCase());
				ldesc_livre_valeur.setText(this.lesLivres.get(i).getDescription().substring(0, 1).toUpperCase() 
											+ this.lesLivres.get(i).getDescription().substring(1).toLowerCase());
				
				//String auteur = lA.rechercherPseudoAuteur(this.lesLivres.get(i).getTitre());
				//if(auteur.length() > 2) lauteur_livre_valeur.setText(auteur.substring(0, 1).toUpperCase()				
					//												+ auteur.substring(1).toLowerCase());

				ltype_livre_valeur.setText(this.lesLivres.get(i).getType().substring(0, 1).toUpperCase() 
											+ this.lesLivres.get(i).getType().substring(1).toLowerCase());
				
				JLabel Limage_oeuvre = new JLabel(new ImageIcon(this.lesLivres.get(i).getLien()), JLabel.CENTER);

				ltitre_livre_valeur.	setBounds((coords_x-12), coords_y, (coords_w+10), coords_h);
				ldesc_livre_valeur.		setBounds((coords_x+100), coords_y, (coords_w), coords_h);
				lauteur_livre_valeur.	setBounds((coords_x+250), coords_y, (coords_w+150), coords_h);
				Limage_oeuvre.			setBounds((coords_x+370), (coords_y-5), (coords_w+80), 95);
				ltype_livre_valeur.		setBounds((coords_x+600), coords_y, (coords_w+150), coords_h);

				PAffichage.add(ltitre_livre_valeur);
				PAffichage.add(ldesc_livre_valeur);
				PAffichage.add(lauteur_livre_valeur);
				PAffichage.add(Limage_oeuvre);
				PAffichage.add(ltype_livre_valeur);
			}
		}
		return listeRadio;
	}

	/**
	 * Recherche de livre par titre
	 * @param : titre_livre 
	 * @return : numero_livre sinon return 0
	**/
	public int rechercher(String titre) {
		for(int i=0; i<this.lesLivres.size(); i++) if(titre.equalsIgnoreCase(this.lesLivres.get(i).getTitre())) return this.lesLivres.get(i).getNumero();
		return 0;
	}
	
	/**
	 * Recherche de livre par titre
	 * @param : titre_livre 
	 * @return : numero_livre sinon return 0
	**/
	public String rechercher(int numero) {
		for(int i=0; i<this.lesLivres.size(); i++) if(numero  == this.lesLivres.get(i).getNumero()) return this.lesLivres.get(i).getTitre();
		return null;
	}

	/**
	 * Modifications des enregistrements de l'objet
	 * @param valeur : tableau de valeur à insérer dans la bdd
	 */
	public boolean exporter(int numero, String[] valeur) {
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		String[] champs = {
				"titre_livre", 
				"description_livre", 
				"type_livre"
		};
		String champs_condition = "numero_livre";
		String valeur_condition = String.valueOf(numero);
		boolean confirm = bdd.modification(champs, "livre", valeur, champs_condition, valeur_condition);
		bdd.Deconnexion();
		return confirm;
	}

	/**
	 * Cherche les livres par la première lettre
	 * @param letter
	 * @param nombre_lettre
	 * @return
	public String[] getLivresByLetter(String letter, int nombre_lettre) {
		String[] les_titres = new String[this.lesLivres.size()];
		for(int i=0; i<this.lesLivres.size(); i++) {
			int long_titre = this.lesLivres.get(i).getTitre().length();
			if(long_titre >= nombre_lettre) long_titre = nombre_lettre;
			if(this.lesLivres.get(i).getTitre().substring(0, long_titre).equalsIgnoreCase(letter)) {
				les_titres[i] = this.lesLivres.get(i).getTitre().substring(0, 1).toUpperCase() 
								+ this.lesLivres.get(i).getTitre().substring(1).toLowerCase(); 
			}
		}	
		return les_titres;
	}*/

	/**
	 * Modification des informations d'un livre
	 * @param t : titre
	 * @param type : étape
	 * @param n : numero_livre
	 * @param modif : valeurs à modifier
	 * @param lA : liste
	 * @param ancien_id : ancien numero
	 * @return les informations du livre
	 */
	public String[] modifier(String titre, boolean type, int numero, String[] modif, LesAuteurs lA, String ancien_id) {
		if(!type) {
			for(int i=0; i<this.lesLivres.size(); i++) {
				if(this.lesLivres.get(i).getTitre().equalsIgnoreCase(titre)) {
					String[] info_livre = {
						String.valueOf(this.lesLivres.get(i).getNumero()), 
						this.lesLivres.get(i).getTitre(), 
						this.lesLivres.get(i).getDescription(), 
						this.lesLivres.get(i).getType()
					};
					return info_livre;
				}
			}
		} else {
			for(int i=0; i<this.lesLivres.size(); i++) {
				if(this.lesLivres.get(i).getNumero() == numero) {
					this.lesLivres.get(i).setLivre(modif);				// Modification objet livre
					this.exporter(numero, modif); 						// Modification bdd
					String[] valid = {"modifie"};
					return valid;
				}
			}
		}
		return null;		
	}

	/**
	 * Suppression d'un livre par valeur chaînée
	 * @param lA
	 * @param titre_delete
	 * @return
	 */
	public boolean supprimer(LesExemplaires lEx, int numero) {
		boolean confirm = false;

		/*int numero = this.rechercher(ref);
		lA.associer(numero, numeroLivre, false);*/
		for(int i=0; i<this.lesLivres.size(); i++) {
			if(numero == this.lesLivres.get(i).getNumero()) {
				for(int j : this.lesLivres.get(i).getExemplaires()) lEx.supprimer(null, j);
				confirm = this.lesLivres.get(i).removeFiles();	// Suppression fichier
				this.lesLivres.remove(i); 						// Supression définitive
			}
		}
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		bdd.suppression("livre", numero);
		bdd.Deconnexion();
		return confirm;
	}

	public int get(int numero) {
		for(int i=0; i<this.lesLivres.size(); i++)
			if(this.lesLivres.get(i).isExemplairesPossedes(numero))
				return this.lesLivres.get(i).getNumero();
		return 0;
	}

	/**
	 * Suppression d'un livre par valeur chaînée
	 * @param lA
	 * @param titre_delete
	 * @return
	 */
	public boolean supprimer(LesExemplaires lEx, LesAuteurs lA, LesLivres lL, String ref) {
		boolean confirm = false;

		int numero = this.rechercher(ref);
		int numeroLivre = lA.get(numero);
		for(int i=0; i<this.lesLivres.size(); i++) {
			if(numero == this.lesLivres.get(i).getNumero()) {
				for(int j : this.lesLivres.get(i).getExemplaires()) lEx.supprimer(lL, j);
				confirm = this.lesLivres.get(i).removeFiles();	// Suppression fichier
				this.lesLivres.remove(i); 						// Supression définitive
			}
		}
		lA.associer(numero, numeroLivre, false);
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		bdd.suppression("livre", numero);
		bdd.Deconnexion();
		return confirm;
	}
	
	/**
	 * Cherche les numero des livres par lettres
	 * @param letter
	 * @param nombre_lettre
	 * @return
	 */
	public ArrayList<Integer> getNumerosByLetter(String letter, int nombre_lettre) {
		ArrayList<Integer> numeros = new ArrayList<Integer>(); 
		for(int i=0; i<this.lesLivres.size(); i++) {
			int long_tit = this.lesLivres.get(i).getTitre().length();
			int long_desc = this.lesLivres.get(i).getDescription().length();
			int long_type = this.lesLivres.get(i).getType().length();
			// gestion erreurs substring
			if(long_tit >= nombre_lettre) {
				long_tit = nombre_lettre;
			}
			if(long_desc >= nombre_lettre) {
				long_desc = nombre_lettre;
			}
			if(long_type >= nombre_lettre) {
				long_type = nombre_lettre;
			}			
			if(this.lesLivres.get(i).getTitre().substring(0, long_tit).equalsIgnoreCase(letter) 
			|| this.lesLivres.get(i).getDescription().substring(0, long_desc).equalsIgnoreCase(letter) 
			|| this.lesLivres.get(i).getType().substring(0, long_type).equalsIgnoreCase(letter)) 
				numeros.add(this.lesLivres.get(i).getNumero());
		}
		return numeros;
	}
	
	/**
	 * Cherche les numero des livres par lettres
	 * @param letter
	 * @param nombre_lettre
	 * @return
	 */
	public ArrayList<String> getNomsByLetter(String letter, int nombre_lettre) {
		ArrayList<String> liste = new ArrayList<String>(); 
		for(int i=0; i<this.lesLivres.size(); i++) {
			int long_tit = this.lesLivres.get(i).getTitre().length();
			int long_desc = this.lesLivres.get(i).getDescription().length();
			int long_type = this.lesLivres.get(i).getType().length();
			// gestion erreurs substring
			if(long_tit >= nombre_lettre) {
				long_tit = nombre_lettre;
			}
			if(long_desc >= nombre_lettre) {
				long_desc = nombre_lettre;
			}
			if(long_type >= nombre_lettre) {
				long_type = nombre_lettre;
			}			
			if(this.lesLivres.get(i).getTitre().substring(0, long_tit).equalsIgnoreCase(letter) 
			|| this.lesLivres.get(i).getDescription().substring(0, long_desc).equalsIgnoreCase(letter) 
			|| this.lesLivres.get(i).getType().substring(0, long_type).equalsIgnoreCase(letter)) 
				liste.add(this.lesLivres.get(i).getTitre());
		}
		return liste;
	}

	public ArrayList<Integer> getLesExemplaires(int numero) {
		for(int i=0; i<this.lesLivres.size(); i++) if(this.lesLivres.get(i).getNumero() == numero) 
			return this.lesLivres.get(i).getExemplaires();
		return new ArrayList<Integer>();
	}

	/**
	 * Retourne la liste des livres
	 * @return liste des objets Livre
	 */
	public LinkedList<Livre> getlesLivres() {
		return this.lesLivres;
	}

}