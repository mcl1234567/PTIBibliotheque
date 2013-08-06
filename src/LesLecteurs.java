import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;

public class LesLecteurs {
	LinkedList<Lecteur> lesLecteurs;
	
	public LesLecteurs() {
		this.lesLecteurs = new LinkedList<Lecteur>();
	}

	public boolean isEnregistrer(String user, String pcw) {
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		boolean confirm = false;
		
		String[] champs_condition = {"pseudonyme_lecteur"};
		String[] valeur_condition = {user};
		String[] champs = {"pcw_lecteur"};
		String[][] req = bdd.lister(champs, "lecteur", champs_condition, valeur_condition);
		try {
			if(req[0][0].equalsIgnoreCase(pcw)) confirm = true;
		} catch (NullPointerException e) {
		}
		bdd.Deconnexion();
		return confirm;
	}

	/**
	 * Ajouter un objet et un enregistrement dans la base de données
	 * @param nv : tbleau d'éléments 
	 * @return booleen
	 */
	public boolean ajouter(String[] nv) {
		String[] valeur = {
			nv[0],	//nom
			nv[1], 	//prenom
			nv[2],	//pseudo
			nv[3],	//pcw
			nv[4],	//code cb
			nv[5],	// cryptogramme cb lecteur
			nv[6]	// date d'expiration de cb lecteur
		};
		String[] champs = {
			"nom_lecteur",
			"prenom_lecteur", 
			"pseudonyme_lecteur",
			"pcw_lecteur",
			"code_carte_bancaire_lecteur",
			"cryptogramme_lecteur",
			"date_expiration_lecteur"
		};
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		int numero_lecteur = bdd.insertion(champs, "lecteur", valeur);
		bdd.Deconnexion();
		Lecteur le = new Lecteur(numero_lecteur, nv);
		this.lesLecteurs.add(le);
		return (le != null);
	}

	public void importer() {
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		String[] champs = {
			"numero_lecteur", 
			"nom_lecteur", 
			"prenom_lecteur",
			"pseudonyme_lecteur",			 
			"code_carte_bancaire_lecteur", 
			"cryptogramme_lecteur", 
			"date_expiration_lecteur",
			"numero_exemplaires_achetes_lecteur",
			"historique_lecteur"
		};
		String[][] req = bdd.lister(champs, "lecteur");	
		for(int i=0; i<req.length; i++) {
			Integer.parseInt(req[i][0]);
			Lecteur le = new Lecteur(req[i]);
			this.lesLecteurs.add(le);
		}
		bdd.Deconnexion();
	}

	/**
	 * Synchronisation des objets 
	 * @param numero
	 * @param numeroAlbum
	 * @param synchro
	 * @return
	 */
	public String associerAchat(int numero, int numeroAchat, boolean synchro) {
		String message = "";
		boolean confirm = false;
		if(synchro) {
			for(int i=0; i<this.lesLecteurs.size(); i++) 
				if(this.lesLecteurs.get(i).getNumero() == numero) 
					confirm = this.lesLecteurs.get(i).setAchats(numeroAchat);
			message = confirm ? "Album référencé par l'artiste !" : "Album déjà référencé par cet artiste !";
		} else {
			for(int i=0; i<this.lesLecteurs.size(); i++) 
				if(this.lesLecteurs.get(i).isAchatsReferences(numeroAchat))
					confirm = this.lesLecteurs.get(i).suppressionAchats(numeroAchat);
			message = confirm ? "Suppression en cours.. !" : "Album pas référencer !";
		}
		return message;
	}
	
	/**
	 * Synchronisation des objets 
	 * @param numero
	 * @param numeroAlbum
	 * @param synchro
	 * @return
	 */
	public String associerLecture(int numero, int numeroLecture, boolean synchro) {
		String message = "";
		boolean confirm = false;
		if(synchro) {
			for(int i=0; i<this.lesLecteurs.size(); i++) 
				if(this.lesLecteurs.get(i).getNumero() == numero) 
					confirm = this.lesLecteurs.get(i).setLectures(numeroLecture);
			message = confirm ? "Album référencé par l'artiste !" : "Album déjà référencé par cet artiste !";
		} else {
			for(int i=0; i<this.lesLecteurs.size(); i++) 
				if(this.lesLecteurs.get(i).isAchatsReferences(numeroLecture))
					confirm = this.lesLecteurs.get(i).suppressionAchats(numeroLecture);
			message = confirm ? "Suppression en cours.. !" : "Album pas référencer !";
		}
		return message;
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
	public ButtonGroup lister(JPanel PAffichage, int nb_aff_debut, int nb_aff_fin, int type, JButton Btype, ArrayList<Integer> numeros_lecteur) {
		if(Btype != null) PAffichage.add(Btype);
		ButtonGroup liste_radio = new ButtonGroup();
		String listeVide = "Pour ajouter un auteur à la bibliothèque : Auteur -> Ajouter un auteur.";
		int coords_x = 30, coords_y = 50, coords_w = 180, coords_h = 20;
		if(this.lesLecteurs.size() >= 1) {
			JLabel lnom_auteur = new JLabel("nom".toUpperCase());
			JLabel lprenom_auteur = new JLabel("prénom".toUpperCase());
			JLabel lpseudo_auteur = new JLabel("pseudonyme".toUpperCase());
			JLabel Llib_ill_auteur = new JLabel("Historique".toUpperCase());
			JLabel page_prem = new JLabel("Résultats : de "  + String.valueOf(nb_aff_debut));
			JLabel page_dern = new JLabel(" à " + String.valueOf(nb_aff_fin));
			JLabel page_total = new JLabel("");
			String pt = (numeros_lecteur.size() == 0) ? "sur " + String.valueOf(this.lesLecteurs.size()) : "sur " + String.valueOf(numeros_lecteur.size());
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
				JRadioButton Bradio = new JRadioButton(String.valueOf(this.lesLecteurs.get(i).getNumero()));				
				Bradio.setBounds(650, (coords_y+40), 20, 20);
				Bradio.setActionCommand(this.lesLecteurs.get(i).getNom());
				liste_radio.add(Bradio);
				PAffichage.add(Bradio);
			}
			JLabel lnom_lecteur_valeur = new JLabel("");
			JLabel lprenom_lecteur_valeur = new JLabel("");
			JLabel lpseudo_lecteur_valeur = new JLabel("");

			if(numeros_lecteur.size() > 0) {
				//	Affichage spécifique
				for(int y=0; y<this.lesLecteurs.size(); y++) {
					if(numeros_lecteur.get(i) == this.lesLecteurs.get(y).getNumero()) {
						lnom_lecteur_valeur.setText(this.lesLecteurs.get(y).getNom().substring(0, 1).toUpperCase() 
													+ this.lesLecteurs.get(y).getNom().substring(1).toLowerCase());
						lprenom_lecteur_valeur.setText(this.lesLecteurs.get(y).getPrenom().substring(0, 1).toUpperCase() 
														+ this.lesLecteurs.get(y).getPrenom().substring(1).toLowerCase());
						if (this.lesLecteurs.get(y).getPseudonyme().length() != 0) 
							lpseudo_lecteur_valeur.setText(this.lesLecteurs.get(y).getPseudonyme().substring(0, 1).toUpperCase() 
														+ this.lesLecteurs.get(y).getPseudonyme().substring(1).toLowerCase());

						//Ajout ..
						/*
						ArrayList<String> ouvrages = this.lesLecteurs.get(y).get..Array();
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

						JLabel Limage_lecteur = new JLabel(new ImageIcon(this.lesLecteurs.get(y).getLien()), JLabel.CENTER);

						lnom_lecteur_valeur.				setBounds((coords_x-12), coords_y, (coords_w+10), coords_h);
						lprenom_lecteur_valeur.			setBounds((coords_x+100), coords_y, (coords_w), coords_h);
						lpseudo_lecteur_valeur.			setBounds((coords_x+200), coords_y, (coords_w+150), coords_h);
						Limage_lecteur.					setBounds((coords_x+350), (coords_y+25), (coords_w+40), 95);

						PAffichage.add(lnom_lecteur_valeur);
						PAffichage.add(lprenom_lecteur_valeur);
						PAffichage.add(lpseudo_lecteur_valeur);
						PAffichage.add(Limage_lecteur);
					}
				}
			} else if(numeros_lecteur.size() == 0) {
				//Affichage de la base des auteurs
				lnom_lecteur_valeur.setText(this.lesLecteurs.get(i).getNom().substring(0, 1).toUpperCase() 
											+ this.lesLecteurs.get(i).getNom().substring(1).toLowerCase());
				lprenom_lecteur_valeur.setText(this.lesLecteurs.get(i).getPrenom().substring(0, 1).toUpperCase() 
												+ this.lesLecteurs.get(i).getPrenom().substring(1).toLowerCase());
				if (this.lesLecteurs.get(i).getPseudonyme().length() != 0) 
					lpseudo_lecteur_valeur.setText(this.lesLecteurs.get(i).getPseudonyme().substring(0, 1).toUpperCase() 
													+ this.lesLecteurs.get(i).getPseudonyme().substring(1).toLowerCase());
				JLabel Limage_lecteur = new JLabel(new ImageIcon(this.lesLecteurs.get(i).getLien()), JLabel.CENTER);

				//Ajout ..
				/*
				ArrayList<String> ouvrages = this.lesLecteurs.get(i).get..Array();
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

				lnom_lecteur_valeur.	setBounds(coords_x, coords_y, coords_w, coords_h);
				lprenom_lecteur_valeur.	setBounds((coords_x+100), coords_y, coords_w, coords_h);
				lpseudo_lecteur_valeur.	setBounds((coords_x+220), coords_y, coords_w, coords_h);
				Limage_lecteur.			setBounds((coords_x+350), (coords_y+25), (coords_w+40), 95);

				PAffichage.add(lnom_lecteur_valeur);
				PAffichage.add(lprenom_lecteur_valeur);
				PAffichage.add(lpseudo_lecteur_valeur);
				PAffichage.add(Limage_lecteur);
			}
		}
		return liste_radio;
	}

	/**
	 * Exportation des enregistrements de l'objet
	 * @param valeur : tableau des valeurs à exporter
	 */
	public void exporter(String[] valeur) {
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		String[] champs = {
				"numero_lecteur", 
				"nom_lecteur", 
				"prenom_lecteur",
				"pseudonyme_lecteur",				 
				"code_carte_bancaire_lecteur", 
				"cryptogramme_lecteur", 
				"date_expiration_lecteur",
				"numero_exemplaires_achetes_lecteur",
				"historique_lecteur",
				"lien_image_lecteur"
		};
		for(int i=0; i<this.lesLecteurs.size(); i++) {
			bdd.insertion(champs, "lecteur", valeur);
		}
		bdd.Deconnexion();
	}

	/**
	 * 	Recherche d'un lecteur
	 * @param n
	 * @return
	 */
	public int rechercherPseudo(String pseudo) {
		for(int i=0; i<this.lesLecteurs.size(); i++)
			if(pseudo.equalsIgnoreCase(this.lesLecteurs.get(i).getPseudonyme())) 
				return this.lesLecteurs.get(i).getNumero();
		return 0;
	}

	/**
	 * Recherche des lecteurs par caractères
	 * Retourne une liste du numero des lecteurs
	 * @param letter
	 * @param nombre_lettre
	 * @return tableau des numéros des lecteurs
	 */
	public ArrayList<Integer> GetNumerosByLetter(String letter, int nombre_lettre) {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for(int i=0; i<this.lesLecteurs.size(); i++) {
			int long_nom = this.lesLecteurs.get(i).getNom().length();
			int long_pre = this.lesLecteurs.get(i).getPrenom().length();
			// gestion erreurs substring
			if(long_nom >= nombre_lettre) long_nom = nombre_lettre;
			if(long_pre >= nombre_lettre) long_pre = nombre_lettre;

			if(this.lesLecteurs.get(i).getNom().substring(0, long_nom).equalsIgnoreCase(letter) 
			|| this.lesLecteurs.get(i).getPrenom().substring(0, long_pre).equalsIgnoreCase(letter)) numeros.add(this.lesLecteurs.get(i).getNumero());
		}		
		return numeros;
	}

	/**
	 * Retourne numéro lecteur correspondant au nom entré en paramètre
	 * @param nom lecteur
	 * @return numéro lecteur
	 */
	public int rechercherNom(String s) {
		for(int i=0; i<this.lesLecteurs.size(); i++)
			if(s.equalsIgnoreCase(this.lesLecteurs.get(i).getNom())) 
				return this.lesLecteurs.get(i).getNumero();
		return 0;
	}

	/**
	 * Modification des enregistrements de l'objet dans la bdd
	 * @param valeur : tableau des valeurs à exporter en fonction du numéro de l'objet
	 */
	public boolean exporter(int numero, String[] valeur) {
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		String[] champs = {
				"nom_lecteur", 
				"prenom_lecteur",
				"pseudonyme_lecteur",
				"code_carte_bancaire_lecteur", 
				"cryptogramme_lecteur", 
				"date_expiration_lecteur",
				"lien_image_lecteur",
				"numero_exemplaires_achetes_lecteur",
				"historique_lecteur"
				
		};
		String champs_condition = "numero_lecteur";
		String valeur_condition = String.valueOf(numero);
		boolean confirm = bdd.modification(champs, "lecteur", valeur, champs_condition, valeur_condition);
		bdd.Deconnexion();
		return confirm;
	}
	
	/**
	 * Modification des informations d'un lecteur
	 * @param nom : nom du lecteur
	 * @param type : étape
	 * @param numero : numero du lecteur
	 * @param modif : valeurs à modifier
	 * @return les informations du livre
	 */
	public String[] modifier(String nom, boolean type, int numero, String[] modif) {
		if(!type) {
			for(int i=0; i<this.lesLecteurs.size(); i++) {
				if(this.lesLecteurs.get(i).getNom().equalsIgnoreCase(nom)) {
					String[] info_lecteur =	{
						String.valueOf(this.lesLecteurs.get(i).getNumero()), 
						this.lesLecteurs.get(i).getNom(),
						this.lesLecteurs.get(i).getPrenom(),
						this.lesLecteurs.get(i).getPseudonyme(),
						this.lesLecteurs.get(i).getCode_carte_bancaire(),
						String.valueOf(this.lesLecteurs.get(i).getCryptogramme()),
						this.lesLecteurs.get(i).getDate_expiration(),
						this.lesLecteurs.get(i).getLien()
					};
					return info_lecteur;
				}
			}
		} else {
			for(int i=0; i<this.lesLecteurs.size(); i++) {
				if(this.lesLecteurs.get(i).getNumero() == numero) {
					this.lesLecteurs.get(i).setLecteur(modif);
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
	 * Suppression d'un lecteur par valeur numérique
	 * @param nom lecteur
	 * @return booléen
	 */
	public boolean supprimer(String nom) {
		boolean confirm = false;
		Exploitation bdd = new Exploitation();
		bdd.ChargerPilote();
		bdd.Connexion();
		int numero = rechercherNom(nom);
		for(int i=0; i<this.lesLecteurs.size(); i++) {
			if(numero == this.lesLecteurs.get(i).getNumero()) {
				//confirm = this.lesLecteurs.get(i).RemoveFiles(); à coder
				confirm = true;
				this.lesLecteurs.remove(i);
			}
		}
		bdd.suppression("lecteur", numero);
		bdd.Deconnexion();
		return confirm;
	}

	/**
	 * Retourne la liste des lecteurs
	 * @return liste des objets Lecteur
	 */
	public LinkedList<Lecteur> getlesLecteurs() {
		return this.lesLecteurs;
	}

}