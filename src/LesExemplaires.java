import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;

public class LesExemplaires {
	LinkedList<Exemplaire> lesExemplaires;
		
	public LesExemplaires() {
		this.lesExemplaires = new LinkedList<Exemplaire>();
	}
		
		/**
		 * Ajouter un objet et un enregistrement dans la base de données
		 * @param langue : langue traduite
		 * @param prix : prix des exemplaires
		 */
		public boolean ajouter(String livre, String edition, String langue, String nombre, String prix, LesLivres lL, LesEditions lE, LesTraductions lTr) {
			String numero_livre = String.valueOf(lL.rechercher(livre));
			String numero_edition = String.valueOf(lE.rechercher(edition));
			String numero_traduction = String.valueOf(lTr.rechercher(langue));
			String[] champs = {
				"numero_livre",
				"numero_edition",
				"numero_traduction",
				"nombre_exemplaire",
				"prix_exemplaire"
			};
			String[] valeur = {
				numero_livre,
				numero_edition,
				numero_traduction,
				nombre,
				prix
			};
			Exploitation bdd = new Exploitation();
			bdd.ChargerPilote();
			bdd.Connexion();
			int numero_exemplaire = bdd.insertion(champs, "lesexemplaires", valeur);
			bdd.Deconnexion();
			Exemplaire le = new Exemplaire(numero_exemplaire, valeur);
			this.lesExemplaires.add(le);
			return (le != null);
		}

		/**
		 * 
		 */
		public void importer() {
			String[] champs = {
				"numero_exemplaire",
				"numero_livre",
				"numero_edition",
				"numero_traduction",
				"nombre_exemplaire",
				"prix_exemplaire"
			};
			Exploitation bdd = new Exploitation();
			bdd.ChargerPilote();
			bdd.Connexion();
			String[][] req = bdd.lister(champs, "lesexemplaires");	
			for(int i=0; i<req.length; i++) {
				Integer.parseInt(req[i][0]);
				Exemplaire le = new Exemplaire(req[i]);
				this.lesExemplaires.add(le);
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
				for(int i=0; i<this.lesExemplaires.size(); i++) 
					if(this.lesExemplaires.get(i).getNumero() == numero) 
						confirm = this.lesExemplaires.get(i).setAchats(numeroAchat);
				message = confirm ? "Album référencé par l'artiste !" : "Album déjà référencé par cet artiste !";
			} else {
				for(int i=0; i<this.lesExemplaires.size(); i++) 
					if(this.lesExemplaires.get(i).isAchatsReferences(numeroAchat))
						confirm = this.lesExemplaires.get(i).suppressionAchats(numeroAchat);
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
				for(int i=0; i<this.lesExemplaires.size(); i++) 
					if(this.lesExemplaires.get(i).getNumero() == numero) 
						confirm = this.lesExemplaires.get(i).setLectures(numeroLecture);
				message = confirm ? "Album référencé par l'artiste !" : "Album déjà référencé par cet artiste !";
			} else {
				for(int i=0; i<this.lesExemplaires.size(); i++) 
					if(this.lesExemplaires.get(i).isAchatsReferences(numeroLecture))
						confirm = this.lesExemplaires.get(i).suppressionAchats(numeroLecture);
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
		public ButtonGroup lister(JPanel PAffichage, LesLivres lL, LesEditions lE, LesTraductions lTr, int nb_aff_debut, int nb_aff_fin, int type, JButton Btype, ArrayList<Integer> numeros_exemplaire) {
			if(Btype != null) PAffichage.add(Btype);
			ButtonGroup liste_radio = new ButtonGroup();
			String listeVide = "Pour ajouter des exemplaires : Exemplaire -> Créer des exemplaires.";
			int coords_x = 30, coords_y = 50, coords_w = 180, coords_h = 20; 
			if(this.lesExemplaires.size() >= 1) {
				JLabel Ltitre_ex = new JLabel("titre".toUpperCase());
				JLabel Ledition_ex = new JLabel("édition".toUpperCase());
				JLabel Llangue_ex = new JLabel("langue".toUpperCase());
				JLabel Lnombre_ex = new JLabel("nombre exemplaires".toUpperCase());
				JLabel Lprix_ex = new JLabel("prix".toUpperCase());
				JLabel page_prem = new JLabel("Résultats : de "  + String.valueOf(nb_aff_debut));
				JLabel page_dern = new JLabel(" à " + String.valueOf(nb_aff_fin));
				JLabel page_total = new JLabel("");
				String pt = (numeros_exemplaire.size() == 0) ? "sur " + String.valueOf(this.lesExemplaires.size()) : "sur " + String.valueOf(numeros_exemplaire.size());
				page_total.setText(pt);
				Ltitre_ex.	setBounds(coords_x, 		(coords_y-20), coords_w, coords_h);
				Ledition_ex.setBounds((coords_x+100), 	(coords_y-20), coords_w, coords_h);
				Llangue_ex.	setBounds((coords_x+220), 	(coords_y-20), coords_w, coords_h);
				Lnombre_ex.	setBounds((coords_x+300), 	(coords_y-20), coords_w, coords_h);
				Lprix_ex.	setBounds((coords_x+500), 	(coords_y-20), coords_w, coords_h);
				page_prem.			setBounds(420, 				350, 		100, 20);
				page_dern.			setBounds(520, 				350, 		40, 20);
				page_total.			setBounds(550, 				350, 		70, 20);
				PAffichage.add(page_prem);
				PAffichage.add(page_dern);
				PAffichage.add(page_total);
				PAffichage.add(Ltitre_ex);
				PAffichage.add(Ledition_ex);
				PAffichage.add(Llangue_ex);
				PAffichage.add(Lnombre_ex);
				PAffichage.add(Lprix_ex);
			} else {
				JLabel label_livre_affichage_lib1 = new JLabel(listeVide);
				label_livre_affichage_lib1.setBounds(coords_x, 25, 400, 20);
				PAffichage.add(label_livre_affichage_lib1);
			}
			for(int i=nb_aff_debut; i<nb_aff_fin; i++) {
				if((i - nb_aff_debut) != 0) coords_y += 140;
				
				JLabel ltitre_exemplaire_valeur = new JLabel("");
				JLabel ledition_exemplaire_valeur = new JLabel("");
				JLabel llangue_exemplaire_valeur = new JLabel("");
				JLabel lnombre_exemplaire_valeur = new JLabel("");
				JLabel lprix_exemplaire_valeur = new JLabel("");

				if(numeros_exemplaire.size() > 0) {
					//	Affichage spécifique
					for(int y=0; y<this.lesExemplaires.size(); y++) {
						if(numeros_exemplaire.get(i) == this.lesExemplaires.get(y).getNumero()) {
							if(type != 0) {
								if(type == 5 || type == 7) {
									JRadioButton Bradio = new JRadioButton(String.valueOf(this.lesExemplaires.get(y).getNumero()));
									Bradio.setBounds(650, (coords_y+40), 20, 20);
									Bradio.setActionCommand(String.valueOf(this.lesExemplaires.get(y).getNumero()));
									liste_radio.add(Bradio);
									PAffichage.add(Bradio);
								}
							}
							String titre = lL.rechercher(this.lesExemplaires.get(y).getNumero());
							String edition = lE.rechercher(this.lesExemplaires.get(y).getNumero());
							String langue = lTr.rechercher(this.lesExemplaires.get(y).getNumero());

							ltitre_exemplaire_valeur.setText(titre.substring(0, 1).toUpperCase() + titre.substring(1).toLowerCase());
							ledition_exemplaire_valeur.setText(edition.substring(0, 1).toUpperCase() + edition.substring(1).toLowerCase());
							llangue_exemplaire_valeur.setText(langue.substring(0, 1).toUpperCase() + langue.substring(1).toLowerCase());
							lnombre_exemplaire_valeur.setText(String.valueOf(this.lesExemplaires.get(y).getNombre()).substring(0, 1).toUpperCase()
															+ String.valueOf(this.lesExemplaires.get(y).getNombre()).substring(1).toLowerCase());
							lprix_exemplaire_valeur.setText(String.valueOf(this.lesExemplaires.get(y).getPrix()).substring(0, 1).toUpperCase()
															+ String.valueOf(this.lesExemplaires.get(y).getPrix()).substring(1).toLowerCase());


							ltitre_exemplaire_valeur	.setBounds((coords_x-12), coords_y, (coords_w+10), coords_h);
							ledition_exemplaire_valeur	.setBounds((coords_x+100), coords_y, (coords_w), coords_h);
							llangue_exemplaire_valeur	.setBounds((coords_x+200), coords_y, (coords_w+150), coords_h);
							lnombre_exemplaire_valeur	.setBounds((coords_x+300), coords_y, coords_w, coords_h);
							lprix_exemplaire_valeur		.setBounds((coords_x+500), coords_y, coords_w, coords_h);

							PAffichage.add(ltitre_exemplaire_valeur);
							PAffichage.add(ledition_exemplaire_valeur);
							PAffichage.add(llangue_exemplaire_valeur);
							PAffichage.add(lnombre_exemplaire_valeur);
							PAffichage.add(lprix_exemplaire_valeur);
						}
					}
				} else if(numeros_exemplaire.size() == 0) {
					//Affichage de la base de données
					String titre = lL.rechercher(this.lesExemplaires.get(i).getNumero());
					String edition = lE.rechercher(this.lesExemplaires.get(i).getNumero());
					String langue = lTr.rechercher(this.lesExemplaires.get(i).getNumero());

					ltitre_exemplaire_valeur.setText(titre.substring(0, 1).toUpperCase() + titre.substring(1).toLowerCase());
					ledition_exemplaire_valeur.setText(edition.substring(0, 1).toUpperCase() + edition.substring(1).toLowerCase());
					llangue_exemplaire_valeur.setText(langue.substring(0, 1).toUpperCase() + langue.substring(1).toLowerCase());
					lnombre_exemplaire_valeur.setText(String.valueOf(this.lesExemplaires.get(i).getNombre()).substring(0, 1).toUpperCase()
													+ String.valueOf(this.lesExemplaires.get(i).getNombre()).substring(1).toLowerCase());
					lprix_exemplaire_valeur.setText(String.valueOf(this.lesExemplaires.get(i).getPrix()).substring(0, 1).toUpperCase()
													+ String.valueOf(this.lesExemplaires.get(i).getPrix()).substring(1).toLowerCase());

					ltitre_exemplaire_valeur.	setBounds(coords_x, coords_y, coords_w, coords_h);
					ledition_exemplaire_valeur.	setBounds((coords_x+100), coords_y, coords_w, coords_h);
					llangue_exemplaire_valeur.	setBounds((coords_x+220), coords_y, coords_w, coords_h);
					lnombre_exemplaire_valeur	.setBounds((coords_x+300), coords_y, coords_w, coords_h);
					lprix_exemplaire_valeur		.setBounds((coords_x+500), coords_y, coords_w, coords_h);

					PAffichage.add(ltitre_exemplaire_valeur);
					PAffichage.add(ledition_exemplaire_valeur);
					PAffichage.add(llangue_exemplaire_valeur);
					PAffichage.add(lnombre_exemplaire_valeur);
					PAffichage.add(lprix_exemplaire_valeur);
				}
			}
			return liste_radio;
		}

		/**
		 * Exportation des enregistrements de l'objet
		 * @param valeur : tableau des valeurs à exporter
		 */
		public void exporterLe(String[] valeur) {
			Exploitation bdd = new Exploitation();
			bdd.ChargerPilote();
			bdd.Connexion();
			String[] champs = {
					"numero_exemplaire",
					"numero_livre",
					"numero_edition",
					"numero_traduction",
					"nombre_exemplaire",
					"prix_exemplaire"
			};
			for(int i=0; i<this.lesExemplaires.size(); i++) {
				bdd.insertion(champs, "lesexemplaires", valeur);
			}
			bdd.Deconnexion();
		}
	

		/**
		 * Recherche des exemplaires par caractères
		 * Retourne une liste du numero des exemplaires
		 * @param letter
		 * @param nombre_lettre
		 * @return tableau des numéros des exemplaires
		 
		public ArrayList<Integer> getNumerosExemplaireByLetter(String letter, int nombre_lettre) {
			ArrayList<Integer> numeros = new ArrayList<Integer>();
			for(int i=0; i<this.lesExemplaires.size(); i++) {
				int long_nom = this.lesExemplaires.get(i).get().length();
				// gestion erreurs substring
				if(long_nom >= nombre_lettre) long_nom = nombre_lettre;

				if(this.lesExemplaires.get(i).getCode().substring(0, long_nom).equalsIgnoreCase(letter)) 
					numeros.add(this.lesExemplaires.get(i).getNumeroExemplaire());
			}		
			return numeros;
		}**/

		/**
		 * Modification des enregistrements de l'objet dans la bdd
		 * @param valeur : tableau des valeurs à exporter en fonction du numéro de l'objet
		 */
		public boolean exporter(int numero, String[] valeur) {
			Exploitation bdd = new Exploitation();
			bdd.ChargerPilote();
			bdd.Connexion();
			String[] champs = {
				"nombre_exemplaire",
				"prix_exemplaire"
			};
			String champs_condition = "numero_exemplaire";
			String valeur_condition = String.valueOf(numero);
			boolean confirm = bdd.modification(champs, "lesexemplaires", valeur, champs_condition, valeur_condition);
			bdd.Deconnexion();
			return confirm;
		}
		
		/**
		 * Modification des informations d'un exemplaire
		 * @param nom : nom du exemplaire
		 * @param type : étape
		 * @param numero : numero du exemplaire
		 * @param modif : valeurs à modifier
		 * @return les informations des exemplaires
		 */
		public String[] modifier(int numero_exemplaire, boolean type, int numero, String[] modif) {
			if(!type) {
				for(int i=0; i<this.lesExemplaires.size(); i++) {
					if(this.lesExemplaires.get(i).getNumero() == numero_exemplaire) {
						String[] info_exemplaire =	{
							String.valueOf(this.lesExemplaires.get(i).getNombre()),
							String.valueOf(this.lesExemplaires.get(i).getPrix())
						};
						return info_exemplaire;
					}
				}
			} else {
				for(int i=0; i<this.lesExemplaires.size(); i++) {
					if(this.lesExemplaires.get(i).getNumero() == numero) {
						this.lesExemplaires.get(i).setExemplaires(modif);
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
		 * Suppression d'un livre par valeur chaînée
		 * @param lA
		 * @param titre_delete
		 * @return
		 */
		public boolean supprimer(LesLivres lL, int numero) {
			boolean confirm = false;		
			if(lL != null) {
				int numeroLivre = lL.get(numero);
				lL.associer(numeroLivre, numero, false);
			}
			for(int i=0; i<this.lesExemplaires.size(); i++) 
				if(numero == this.lesExemplaires.get(i).getNumero()) this.lesExemplaires.remove(i);
			Exploitation bdd = new Exploitation();
			bdd.ChargerPilote();
			bdd.Connexion();
			bdd.suppression("exemplaire", numero);
			bdd.Deconnexion();
			return confirm;
		}
		

		/**
		 * Retourne le tableau de morceaux de l'album
		 * @param numero
		 * @return ArrayList<Integer>
		 */
		public ArrayList<Integer> getLesAchats(int numero) {
			for(int i=0; i<this.lesExemplaires.size(); i++) if(this.lesExemplaires.get(i).getNumero() == numero)
					return this.lesExemplaires.get(i).getAchatsPossedes();
			return new ArrayList<Integer>();
		}
		

		/**
		 * Suppression d'un livre par valeur chaînée
		 * @param lA
		 * @param titre_delete
		 * @return
		 
		public boolean supprimer(LesLivres lL, String ref) {
			boolean confirm = false;

			int numero = this.rechercher(ref);
			int numeroLivre = lA.get(numero);
			for(int i=0; i<this.lesExemplaires.size(); i++) {
				if(numero == this.lesExemplaires.get(i).getNumero()) {
					for(int j : this.lesExemplaires.get(i).getExemplaires()) lEx.supprimer(j);
					//confirm = this.lesExemplaires.get(i).removeFiles();	// Suppression fichier
					this.lesExemplaires.remove(i); 						// Supression définitive
				}
			}
			lL.associer(numero, numeroLivre, false);
			Exploitation bdd = new Exploitation();
			bdd.ChargerPilote();
			bdd.Connexion();
			bdd.suppression("livre", numero);
			bdd.Deconnexion();
			return confirm;
		}*/

		/**
		 * Suppression d'un exemplaire par valeur numérique
		 * @param nom exemplaire
		 * @return booléen

		public boolean supprimer(int numero) {
			boolean confirm = false;

			for(int i=0; i<this.lesExemplaires.size(); i++) {
				if(numero == this.lesExemplaires.get(i).getNumero()) {
					confirm = true;
					this.lesExemplaires.remove(i);
				}
			}
			Exploitation bdd = new Exploitation();
			bdd.ChargerPilote();
			bdd.Connexion();
			bdd.suppression("exemplaire", numero);
			bdd.Deconnexion();
			return confirm;
		}		**/
		
		/**
		 * Retourne la liste des exemplaires
		 * @return liste des objets exemplaire
		 */
	public LinkedList<Exemplaire> getLesExemplaires() {
		return this.lesExemplaires;
	}

}
