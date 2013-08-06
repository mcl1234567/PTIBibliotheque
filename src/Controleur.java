import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;

public class Controleur {
	/**
	 * Evènements de l'utilisateur
	 */
	public void actionPerformed(ActionEvent e) {
		// Bouton retour
		if(e.getSource() == Bibliotheque.Bretour) {
			if(inTable.equalsIgnoreCase("livre")) 		Blivre.doClick();
			if(inTable.equalsIgnoreCase("auteur"))		Bauteur.doClick();
			if(inTable.equalsIgnoreCase("edition")) 	Bedition.doClick();
			if(inTable.equalsIgnoreCase("lecteur")) 	Blecteur.doClick();
			if(inTable.equalsIgnoreCase("exemplaire")) 	Bexemplaire.doClick();
			if(inTable.equalsIgnoreCase("traduction")) 	Btraduction.doClick();
		}

		// Menu principal
		if(e.getSource() == Badmin) {
			Lnavigation.setText("");
			refresh("");
			Blivre		.setVisible(true);
			Bauteur		.setVisible(true);
			Bedition	.setVisible(true);
			Blecteur	.setVisible(true);
			Bexemplaire	.setVisible(true);
			Btraduction	.setVisible(true);

			Blire			.setVisible(false);
			Bacheter		.setVisible(false);
			Binscrire		.setVisible(false);
			Bconnecter_1	.setVisible(false);
			Bdeconnecter	.setVisible(false);
			Bprofil			.setVisible(false);
			Lnavigation		.setText("");
		} else if(e.getSource() == Bclient) {
			Lnavigation.setText("");
			refresh("");
			// Gestion client
			if(authentifie == false) {
				// Pas connecté
				pseudonyme = "";
				Blire		.setVisible(false);
				Bacheter	.setVisible(false);	
				Bprofil		.setVisible(false);
				Bdeconnecter.setVisible(false);
				Binscrire	.setVisible(true);
				Bconnecter_1.setVisible(true);
			} else {
				// Affichage si connecté
				Bconnecter_1.setVisible(false);
				Binscrire	.setVisible(false);
				Bdeconnecter.setVisible(true);
				Blire		.setVisible(true);
				Bacheter	.setVisible(true);
				Bprofil		.setVisible(true);
			}
		}

		// Menu barre en haut
		if(e.getSource() == Mabout) { this.about(); }

		// Menu secondaire
		if(e.getSource() == Blivre) {
			refresh("livre");
			Plivre.add(Brechercher);
		} else if(e.getSource() == Bauteur) {
			refresh("auteur");
			Pauteur.add(Brechercher);
		} else if(e.getSource() == Bedition) {
			refresh("edition");
			Pedition.add(Brechercher);
		} else if(e.getSource() == Blecteur) {
			refresh("lecteur");
			Plecteur.add(Brechercher);
		} else if(e.getSource() == Bexemplaire) {
			refresh("exemplaire");
			Pexemplaire.add(Brechercher);
		} else if(e.getSource() == Btraduction) {
			refresh("traduction");
			Ptraduction.add(Brechercher);
		}

		 //	Evènement d'upload
		if(e.getSource() == Buploader) {
			int retour = this.Fimage.showOpenDialog(getParent());
			if(retour == JFileChooser.APPROVE_OPTION) {
				String[] part = this.Fimage.getSelectedFile().getName().split("\\.");
				nameFichier = System.currentTimeMillis() + part[0] + "." + part[1];
				try {
					uploadFile(this.Fimage.getSelectedFile(), new File(rep, nameFichier));
					dirFichier = rep + "\\" + nameFichier;
				} catch(IOException exp) {
					exp.printStackTrace();
				}
			}
		}

		// Affichage générique - par pages
		if(e.getSource() == Bprecedent) {
			navigation(-1, "", "", new ArrayList<Integer>(), 0, null);
		} else if(e.getSource() == Bsuivant) {
			navigation(1, "", "", new ArrayList<Integer>(), 0, null);
		}
		// Affichage générique avec objets et pages par pages
		if(e.getSource() == BprecedentObjet) {
			navigationObjet(-1, "", "", new ArrayList<Object>(), 0, null);		
		} else if(e.getSource() == BsuivantObjet) {
			navigationObjet(1, "", "", new ArrayList<Object>(), 0, null);
		}

		// Recherche globale
		if(e.getSource() == Brechercher) {
			// Paramètre de navigation
			inType = "rechercher";
			refresh("formulaire");
			Pformulaire.add(Irecherche);
			Pformulaire.add(Bretour);
			Pformulaire.add(LinfoResultat);
			Irecherche.setText("Entrer votre recherche..");
		}

		// Livre
		if(e.getSource() == BajouterLivre_1) {
			inType = "ajouter";
			refresh("formulaire");
			Pformulaire.add(BajouterLivre_2);
			Pformulaire.add(Ltitre);
			Pformulaire.add(LDesc);
			Pformulaire.add(LtypeLivre);
			Pformulaire.add(Ititre);
			Pformulaire.add(IDescription);
			Pformulaire.add(ItypeLivre);
			Pformulaire.add(Lassocauteur);
			Pformulaire.add(IlettreAuteur);
			Pformulaire.add(ClisteAuteur);
			Pformulaire.add(Lfichier);
			Pformulaire.add(Buploader);
			Pformulaire.add(Bretour);
			ClisteAuteur.removeAllItems();
			ClisteAuteur.setVisible(true);
			ClisteAuteur.addItem("Aucun auteur disponible");
			Lfichier.setText("Image du livre : ");
		} else if(e.getSource() == BajouterLivre_2) {
			// Vérification contenu..
			String vt = Ititre.getText(), vd = IDescription.getText(), vty = ItypeLivre.getText();
			int lvt = 17, lvd = 23, lvty = 25;
			if(lvt > vt.length()) lvt = vt.length();
			if(lvd > vd.length()) lvd = vd.length();
			if(lvty > vty.length()) lvty = vty.length();
			if(vt.length() < 2 || vd.length() < 2 || vty.length() < 2) {
				erreur("Chaque champs doit renseigner au moins 2 caractères ! ");
			} else if(vt.equalsIgnoreCase("") 
					|| vd.equalsIgnoreCase("") 
					|| vty.equalsIgnoreCase("") 
					|| vt.substring(0, lvt).equalsIgnoreCase("Entrer le titre..") 
					|| vd.substring(0, lvd).equalsIgnoreCase("Entrer la description..") 
					|| vty.substring(0, lvty).equalsIgnoreCase("Entrer le type du livre..") 
					|| ((String) ClisteAuteur.getSelectedItem()).equalsIgnoreCase("")) {
				erreur("Un des champs est vide ou incomplet !");
			} else {
				// Formulaire valide..
				String selectedAuteur = (String) ClisteAuteur.getSelectedItem();
				String[] nouveau = {
						selectedAuteur,
						vt,
						vd,
						vty,
						dirFichier // lien image
				};
				boolean confirm = lL.ajouter(lA, nouveau);
				String mess = confirm ? "Le livre " + Ititre.getText() + " a été ajouté !" : "Echec de création !";
				informationShow(mess);
				setTextField();
				Blivre.doClick();
			}
		} else if(e.getSource() == BlisterLivre) {
			navigation(0, "livre", "lister", new ArrayList<Integer>(), 0, null);
		} else if(e.getSource() == BmodifierLivre_1) {
			navigation(0, "livre", "modifier", new ArrayList<Integer>(), 0, null);
		} else if(e.getSource() == Bmodifier_2 && inTable.equalsIgnoreCase("livre")) {
			try {
				refresh("formulaire");
				Pformulaire.removeAll();
				Pformulaire.add(Ltitre);
				Pformulaire.add(LDesc);
				Pformulaire.add(LtypeLivre);
				Pformulaire.add(LinfoLivre);
				Pformulaire.add(Ititre);
				Pformulaire.add(IDescription);
				Pformulaire.add(ItypeLivre);		
				Pformulaire.add(Bretour);
				Pformulaire.add(BmodifierLivre_3);
				String selection = listeRadio.getSelection().getActionCommand();
				info = lL.modifier(selection, false, 0, null, lA, "");
				Integer.parseInt(info[1]);
				Ititre.setText(info[2]); 
				IDescription.setText(info[3]);
				ItypeLivre.setText(info[4]);
			} catch(NullPointerException exp) {
				informationShow("Aucun livre sélectionné !");
			}
		} else if(e.getSource() == BmodifierLivre_3) {
			// Vérification contenu..
			String vt = Ititre.getText(), vd = IDescription.getText(), vty = ItypeLivre.getText();
			int lvt = 17, lvd = 23, lvty = 25; 
			if(lvt > vt.length()) lvt = vt.length();
			if(lvd > vd.length()) lvd = vd.length();
			if(lvty > vty.length()) lvty = vty.length();
			if(vt.equalsIgnoreCase("") 
			|| vd.equalsIgnoreCase("") 
			|| vty.equalsIgnoreCase("") 
			|| vt.substring(0, lvt).equalsIgnoreCase("Entrer le titre..") 
			|| vd.substring(0, lvd).equalsIgnoreCase("Entrer la description..") 
			|| vty.substring(0, lvty).equalsIgnoreCase("Entrer le type du livre..")) { 
				erreur("Un des champs est vide ou pas complet !");
			} else {
				String[] updateInfo = {
					Ititre.getText(), 
					IDescription.getText(), 
					ItypeLivre.getText()
				};
				String ancien_id = info[2];
				for(int i=2; i<info.length; i++) {
					if(!info[i].equalsIgnoreCase(updateInfo[(i-2)])) {
						lL.modifier(null, true, Integer.parseInt(info[0]), updateInfo, lA, ancien_id);							
					}
				}
				informationShow("Informations modifiées !");				
				setTextField();
				Blivre.doClick();
			}
		} else if(e.getSource() == BsupprimerLivre_1) {
			navigation(0, "livre", "supprimer", new ArrayList<Integer>(), 0, null);
		} else if(e.getSource() == Bsupprimer_2) {
			boolean confirm = false, noSelection = false;
			try {
				noSelection = true;
				String sDelete = listeRadio.getSelection().getActionCommand();
				if(inTable.equalsIgnoreCase("livre")) 		confirm = lL.supprimer(lEx, lA, lL, sDelete);
				if(inTable.equalsIgnoreCase("auteur")) 		confirm = lA.supprimer(sDelete);
				if(inTable.equalsIgnoreCase("edition")) 	confirm = lE.supprimer(sDelete);
				if(inTable.equalsIgnoreCase("lecteur")) 	confirm = lLect.supprimer(sDelete);
				if(inTable.equalsIgnoreCase("traduction")) 	confirm = lTr.supprimer(sDelete);				
			} catch (NullPointerException exp) {
				erreur(exp.getMessage() + " # supprimer etape 2");
			}
			String message = confirm ? inTable + " supprimé !" : "Echec !";
			message = !noSelection ? "Aucun " + inTable + " sélectionné !" : message;
			informationShow(message);
			if(inTable.equalsIgnoreCase("livre"))  		Blivre.doClick();
			if(inTable.equalsIgnoreCase("auteur")) 		Bauteur.doClick();
			if(inTable.equalsIgnoreCase("edition"))		Bedition.doClick();
			if(inTable.equalsIgnoreCase("lecteur")) 	Blecteur.doClick();
			if(inTable.equalsIgnoreCase("exemplaire")) 	Bexemplaire.doClick();
			if(inTable.equalsIgnoreCase("traduction")) 	Btraduction.doClick();
		}

		// Auteur
		if(e.getSource() == BajouterAuteur) {
			inType = "ajouter";
			refresh("formulaire");
			Pformulaire.add(LnomAuteur);
			Pformulaire.add(LprenomAuteur);
			Pformulaire.add(LpseudoAuteur);
			Pformulaire.add(LinfoAuteur);
			Pformulaire.add(IprenomAuteur);
			Pformulaire.add(InomAuteur);
			Pformulaire.add(IpseudoAuteur);
			Pformulaire.add(BajouterAuteur_2);
			Pformulaire.add(Buploader);
			Pformulaire.add(Bretour);
			Pformulaire.add(Lfichier);
			LinfoAuteur.setText("");		
		} else if(e.getSource() == BajouterAuteur_2) {
			lA.ajouter(InomAuteur, IprenomAuteur, IpseudoAuteur, dirFichier, LinfoAuteur);
			informationShow("L'auteur " + InomAuteur.getText() + " a été ajouté !");
			dirFichier = ""; // init répertoire
			setTextField();
			Bauteur.doClick();
		} else if(e.getSource() == BlisterAuteur) {
			//navigation(0, "auteur", "lister", new ArrayList<Integer>(), 0, null);
			navigationObjet(0, "auteur", "lister", new ArrayList<Object>(), 0, null);
		} else if(e.getSource() == BmodifierAuteur_1) {
			navigation(0, "auteur", "modifier", new ArrayList<Integer>(), 0, null);
		} else if(e.getSource() == Bmodifier_2 && inTable.equalsIgnoreCase("auteur")) {
			try {
				refresh("formulaire");
				Pformulaire.removeAll();
				Pformulaire.add(InomAuteur);
				Pformulaire.add(IprenomAuteur);
				Pformulaire.add(IpseudoAuteur);
				Pformulaire.add(LinfoAuteur);
				Pformulaire.add(LnomAuteur);
				Pformulaire.add(LprenomAuteur);
				Pformulaire.add(LpseudoAuteur);
				Pformulaire.add(Bretour);
				Pformulaire.add(BmodifierAuteur_3);
				String selection = listeRadio.getSelection().getActionCommand();
				info = lA.modifier(selection, false, 0, null);
				InomAuteur.setText(info[1]); 
				IprenomAuteur.setText(info[2]);
				IpseudoAuteur.setText(info[3]);
			} catch(NullPointerException exp) {
				informationShow("Aucun livre sélectionné !");
			}		
		} else if(e.getSource() == BmodifierAuteur_3 && inTable.equalsIgnoreCase("auteur")) {
			String[] confirm = {
				InomAuteur.getText(), 
				IprenomAuteur.getText(), 
				IpseudoAuteur.getText()
			};
			String message = "";
			for(int i=1; i<info.length; i++) {
				if(info[i].equalsIgnoreCase(confirm[(i-1)])) {
					try {
						message = lA.modifier(null, true, Integer.parseInt(info[0]), confirm) != null ? inTable + " modifié !" : "Erreur"; 
					} catch (NullPointerException exp) {
						message = "Echec !";
					}						
				}
			}
			informationShow(message);			
			setTextField();
			Bauteur.doClick();
		} else if(e.getSource() == BsupprimerAuteur) {
			navigation(0, "auteur", "supprimer", new ArrayList<Integer>(), 0, null);
		}

		// Edition
		if(e.getSource() == BajouterEdition_1) {
			inType = "ajouter";
			refresh("formulaire");
			Pformulaire.add(Bretour);
			Pformulaire.add(BajouterEdition_2);
			Pformulaire.add(Lmention);
			Pformulaire.add(Imention);			
		} else if(e.getSource() == BajouterEdition_2) {			
			String message = lE.ajouter(Imention) ? "Edition ajoutée !" : "Echec création !";
			informationShow(message);
			Bedition.doClick(200);			
			setTextField();
		} else if(e.getSource() == BlisterEdition) {
			navigation(0, "edition", "lister", new ArrayList<Integer>(), 0, null);
		} else if(e.getSource() == BmodifierEdition) {
			navigation(0, "edition", "modifier", new ArrayList<Integer>(), 0, null);
		} else if(e.getSource() == Bmodifier_2 && inTable.equalsIgnoreCase("edition")) {
			try {
				refresh("formulaire");
				Pformulaire.removeAll();
				Pformulaire.add(Imention);
				Pformulaire.add(Lmention);
				Pformulaire.add(Bretour);
				Pformulaire.add(BmodifierEdition_3);
				String selection = listeRadio.getSelection().getActionCommand();
				info = lE.modifier(selection, false, 0, null);
				Imention.setText(info[1]);
			} catch(NullPointerException exp) {
				informationShow("Aucune édition sélectionnée !");
			}
		} else if(e.getSource() == BmodifierEdition_3 && inTable.equalsIgnoreCase("edition")) {
			String[] confirm = {Imention.getText()};
			String message = "";
			for(int i=1; i<info.length; i++) {
				if(info[i].equalsIgnoreCase(confirm[(i-1)])) {
					try {
						message = lE.modifier(null, true, Integer.parseInt(info[0]), confirm) != null ? inTable + " modifié !" : "Erreur"; 
					} catch (NullPointerException exp) {
						message = "Echec !";
					}						
				}
			}
			informationShow(message);			
			setTextField();
			Bedition.doClick();
		} else if(e.getSource() == BsupprimerEdition) {
			navigation(0, "edition", "supprimer", new ArrayList<Integer>(), 0, null);
		}

		// Lecteur
		if(e.getSource() == BajouterLecteur_1) {
			inType = "ajouter";
			refresh("formulaire");
			Pformulaire.removeAll();
			Pformulaire.add(LnomLecteur);
			Pformulaire.add(LprenomLecteur);
			Pformulaire.add(LpseudonymeLecteur);
			Pformulaire.add(Lpcw);
			Pformulaire.add(InomLecteur);
			Pformulaire.add(IprenomLecteur);
			Pformulaire.add(IpseudonymeLecteur);
			Pformulaire.add(Ipcw);
			Pformulaire.add(BajouterLecteur_2);
			Pformulaire.add(Bretour);		
		} else if(e.getSource() == BajouterLecteur_2) {			
			// Vérification contenu..
			String vnom = InomLecteur.getText(), vpre = IprenomLecteur.getText(), vpseu = IpseudonymeLecteur.getText(), vpcw = Ipcw.getText();
			int lvnom = 17, lvpre = 23, lvpseu = 25, lvpcw = 20;
			if(lvnom > vnom.length()) lvnom = vnom.length();
			if(lvpre > vpre.length()) lvpre = vpre.length();
			if(lvpseu > vpseu.length()) lvpseu = vpseu.length();
			if(lvpcw > vpcw.length()) lvpcw = vpcw.length();
			if(vnom.length() < 2 
			|| vpre.length() < 2 
			|| vpseu.length() < 2 
			|| vpcw.length() < 2) {
				erreur("Chaque champs doit renseigner au moins 2 caractères ! ");
			} else if(vnom.equalsIgnoreCase("")
					|| vpre.equalsIgnoreCase("")
					|| vpseu.equalsIgnoreCase("")
					|| vpcw.equalsIgnoreCase("")
					|| vnom.substring(0, lvnom).equalsIgnoreCase("Entrer un nom..") 
					|| vpre.substring(0, lvpre).equalsIgnoreCase("Entrer un prénom..") 
					|| vpseu.substring(0, lvpseu).equalsIgnoreCase("Entrer un pseudonyme..")
					|| vpcw.substring(0, lvpcw).equalsIgnoreCase("Entrer un password..")) {
				erreur("Un des champs est vide ou incomplet !");
			} else {
				// Formulaire valide..
				String[] nouveau = {
					InomLecteur.getText(), 
					IprenomLecteur.getText(), 
					IpseudonymeLecteur.getText(),
					Ipcw.getText(),
					"3467-0985-4589-9654",
					String.valueOf(123),
					"06/2014",
				};
				boolean confirm = lLect.ajouter(nouveau);
				String message = confirm ? "Le compte lecteur " + InomLecteur.getText() + " a été créé !" : "Echec de création !";
				informationShow(message);
				setTextField();
				Blecteur.doClick();
			}
			dirFichier = ""; // init répertoire - à coder
		} else if(e.getSource() == BlisterLecteur) {
			navigation(0, "lecteur", "lister", new ArrayList<Integer>(), 0, null);			
		} else if(e.getSource() == BmodifierLecteur_1) {
			navigation(0, "lecteur", "modifier", new ArrayList<Integer>(), 0, null);
		} else if(e.getSource() == Bmodifier_2 && inTable.equalsIgnoreCase("lecteur")) {
			try {
				String selection = listeRadio.getSelection().getActionCommand();
				refresh("formulaire");
				Pformulaire.removeAll();
				Pformulaire.add(InomLecteur);
				Pformulaire.add(IprenomLecteur);
				Pformulaire.add(IpseudonymeLecteur);
				Pformulaire.add(LnomLecteur);
				Pformulaire.add(LprenomLecteur);
				Pformulaire.add(LpseudonymeLecteur);
				Pformulaire.add(Bretour);
				Pformulaire.add(BmodifierLecteur_3);
				info = lLect.modifier(selection, false, 0, null);
				InomLecteur.setText(info[1]); 
				IprenomLecteur.setText(info[2]);
				IpseudonymeLecteur.setText(info[3]);
			} catch(NullPointerException exp) {
				informationShow("Aucun lecteur sélectionné !");
			}
		} else if(e.getSource() == BmodifierLecteur_3 && inTable.equalsIgnoreCase("lecteur")) {
			String[] confirm = {
				InomLecteur.getText(), 
				IprenomLecteur.getText(),
				IpseudonymeLecteur.getText(),
				"",	//code cartebancaire
				String.valueOf(0),	//cryptogramme
				"",	//date d'expiration
				"",	//lien image
				String.valueOf(0),	//exemplaires achetés
				String.valueOf(0)	//historique
			};
			String message = "Erreur de modification !";
			boolean changement = false;
			for(int i=1; i<info.length; i++) {
				if(!info[i].equalsIgnoreCase(confirm[(i-1)])) {
					changement = true;
				}
			}
			if(changement) {
				try {
					message = lLect.modifier(null, true, Integer.parseInt(info[0]), confirm) != null ? inTable + " modifié !" : "Erreur d'insertion des données"; 
				} catch (NullPointerException exp) {
					message = "Echec !";
				}
				informationShow(message);				
				setTextField();
				Blecteur.doClick();
			}
		} else if(e.getSource() == BsupprimerLecteur) {
			navigation(0, "lecteur", "supprimer", new ArrayList<Integer>(), 0, null);
		}

		// Client
		// Lecteur
		// Créer lecteur 
		/*if(e.getSource() == Binscrire) {
			refresh("formulaire");
			Pformulaire.removeAll();
			Pformulaire.add(LnomLecteur);
			Pformulaire.add(LprenomLecteur);
			Pformulaire.add(LpseudonymeLecteur);
			Pformulaire.add(Lpcw);
			Pformulaire.add(InomLecteur);
			Pformulaire.add(IprenomLecteur);
			Pformulaire.add(IpseudonymeLecteur);
			Pformulaire.add(Ipcw);
			Pformulaire.add(BcreerLecteur_2);
			Pformulaire.add(Bretour);
		} else if(e.getSource() == BcreerLecteur_2) {
			// Vérification contenu..
			String vnom = InomLecteur.getText(), vpre = IprenomLecteur.getText(), vpseu = IpseudonymeLecteur.getText(), vpcw = Ipcw.getText();
			int lvnom = 17, lvpre = 23, lvpseu = 25, lvpcw = 20;
			if(lvnom > vnom.length()) { lvnom = vnom.length(); }
			if(lvpre > vpre.length()) { lvpre = vpre.length(); }
			if(lvpseu > vpseu.length()) { lvpseu = vpseu.length(); }
			if(lvpcw > vpcw.length())  { lvpcw = vpcw.length(); }
			if(vnom.length() < 2 
			|| vpre.length() < 2 
			|| vpseu.length() < 2 
			|| vpcw.length() < 2) {
				erreur("Chaque champs doit renseigner au moins 2 caractères ! ");
			} else if(vnom.equalsIgnoreCase("")
					|| vpre.equalsIgnoreCase("")
					|| vpseu.equalsIgnoreCase("")
					|| vpcw.equalsIgnoreCase("")
					|| vnom.substring(0, lvnom).equalsIgnoreCase("Entrer un nom..") 
					|| vpre.substring(0, lvpre).equalsIgnoreCase("Entrer un prénom..") 
					|| vpseu.substring(0, lvpseu).equalsIgnoreCase("Entrer un pseudonyme..")
					|| vpcw.substring(0, lvpcw).equalsIgnoreCase("Entrer un password..")) {
					erreur("Un des champs est vide ou incomplet !");
			} else {
				// Formulaire valide
				String[] nouveau = {
					InomLecteur.getText(), 
					IprenomLecteur.getText(), 
					IpseudonymeLecteur.getText(),
					Ipcw.getText(),
					"3467-0985-4589-9654",			// Code cb
					String.valueOf(123),			// Cryptogramme
					"06/2014",						// Date d'expiration
				};
				boolean confirm = lLect.ajouter(nouveau);								
				if(confirm) {
					informationShow("Le compte client " + InomLecteur.getText() + " a été créé !");
					setTextField();
					this.dirFichier = ""; // init répertoire
					compteCree = true;
					Bclient.doClick();
				}
				informationShow("erreur");
			}
		}*/
		// Client - Connexion
		if(e.getSource() == Bconnecter_1) {
			refresh("formulaire");
			Pformulaire.add(Bconnecter_2);
			Pformulaire.add(Bretour);
			Pformulaire.add(Lpcw);
			Pformulaire.add(LpseudonymeLecteur);
			Pformulaire.add(Ipcw);
			Pformulaire.add(IpseudonymeLecteur);
		}
		if(e.getSource() == Bconnecter_2) {
			login(IpseudonymeLecteur.getText(), Ipcw.getText());
		}
		// Client - Déconnexion
		if(e.getSource() == Bdeconnecter) {
			authentifie = false;
			Bclient.doClick();
		}
		// Client - Acheter
		if(e.getSource() == Bacheter) {
			navigation(0, "livre", "acheter", new ArrayList<Integer>(), 0, null);
		} else if(e.getSource() == Bacheter_2) {
			boolean selection = false;
			try {
				String toBuy = listeRadio.getSelection().getActionCommand();
				ArrayList<Integer> exToBuy = lL.getLesExemplaires(lL.rechercher(toBuy));
				if(exToBuy.size() > 0) {
					navigation(0, "exemplaire", "acheter", exToBuy, 0, null);
					selection = true;
				}
			} catch (NullPointerException exp) {
			}
			if(!selection) {
				informationShow("Aucun exemplaire disponible !");
			}
		}
		// Acheter client
		else if(e.getSource() == Bacheter_3) {
			boolean confirm = false, selection = false;
			try {
				String exToBuy = listeRadio.getSelection().getActionCommand();
				if(inTable.equalsIgnoreCase("exemplaire") 
				&& inType.equalsIgnoreCase("acheter")) {
					confirm = lAch.ajouter(lEx, lLect, exToBuy, pseudonyme);
				}
				selection = true;
			} catch (NullPointerException exp) {
				erreur(exp.getMessage() + " # acheter etape 3");
			}
			String message = confirm ? "Exemplaire acheté !" : "Erreur lors de l'achat!";
			message = !selection ? "Aucune sélection !" : message;
			informationShow(message);
			Bclient.doClick(); // redirection profil historique paiement
		} 
		// Client - Lire
		if(e.getSource() == Blire) {
			navigation(0, "livre", "lire", new ArrayList<Integer>(), 0, null);
		} else if(e.getSource() == Blire_2) {
			boolean selection = false;
			try {
				String to_buy = listeRadio.getSelection().getActionCommand();
				//ArrayList<Integer> exToBuy = lEx.rechercherNumeros(lL.rechercher(to_buy));
				ArrayList<Integer> exToBuy = lL.getLesExemplaires(lL.rechercher(to_buy));
				if(exToBuy.size() > 0) {
					navigation(0, "exemplaire", "lire", exToBuy, 0, null);
					selection = true;
				}
			} catch (NullPointerException exp) {
			}
			if(!selection) informationShow("Aucun exemplaire disponible !");
		} else if(e.getSource() == Blire_3) {
			boolean confirm = false, selection = false;
			try {
				String exToBuy = listeRadio.getSelection().getActionCommand();
				if(inTable.equalsIgnoreCase("exemplaire") 
				&& inType.equalsIgnoreCase("lire")) {
					confirm = lV.ajouter(lEx, lLect, exToBuy, pseudonyme);
				}
				selection = true;
			} catch (NullPointerException exp) {
				erreur(exp.getMessage() + " # lire etape 3");
			}
			String mess = confirm ? "Affichage en cours.. !" : "Erreur lors de lecture !";
			mess = (!selection) ? "Aucune sélection !" : mess;
			informationShow(mess);
			Bclient.doClick(); // redirection profil historique paiement
		}
		//	Profil compte client
		if(e.getSource() == Bprofil) {
			
		}

		// Exemplaires - Créer
		if(e.getSource() == BcreerExemplaire_1) {
			inType = "ajouter";
			refresh("formulaire");
			Pformulaire.add(BcreerExemplaire_2);
			Pformulaire.add(Lnombre);
			Pformulaire.add(Lprix);
			Pformulaire.add(IlettreLangue);
			Pformulaire.add(Inombre);
			Pformulaire.add(Iprix);
			Pformulaire.add(Lassoclivre);
			Pformulaire.add(Lassocedition);
			Pformulaire.add(Lassoclangue);
			Pformulaire.add(IlettreLivre);
			Pformulaire.add(IlettreEdition);
			Pformulaire.add(ClisteLivre);
			Pformulaire.add(ClisteEdition);
			Pformulaire.add(ClisteTraduction);
			Pformulaire.add(Bretour);
			ClisteLivre.removeAllItems();
			ClisteEdition.removeAllItems();
			ClisteTraduction.removeAllItems();
			ClisteLivre.setVisible(true);
			ClisteEdition.setVisible(true);
			ClisteTraduction.setVisible(true);
			ClisteLivre.addItem("Aucune oeuvre disponible");
			ClisteEdition.addItem("Aucun éditeur disponible");
			ClisteTraduction.addItem("Langue d'origine");
		} else if(e.getSource() == BcreerExemplaire_2) {
			// Vérification contenu..
			String vn = Inombre.getText(), vp = Iprix.getText();
			int lvn = 18, lvp = 16;
			if(lvn > vn.length()) lvn = vn.length();
			if(lvp > vp.length()) lvp = vp.length();
			if(vn.length() < 1 || vp.length() < 1) erreur("Chaque champs doit renseigner au moins 1 caractères ! ");
			else if(vn.equalsIgnoreCase("") 
					|| vp.equalsIgnoreCase("") 
					|| vn.substring(0, lvn).equalsIgnoreCase("Entrer un nombre d'exemplaires..") 
					|| vp.substring(0, lvp).equalsIgnoreCase("Entrer un prix..") 
					|| ((String) ClisteLivre.getSelectedItem()).equalsIgnoreCase("") 
					|| ((String) ClisteEdition.getSelectedItem()).equalsIgnoreCase("") 
					|| ((String) ClisteTraduction.getSelectedItem()).equalsIgnoreCase("")) {
				erreur("Un des champs est vide ou incomplet !");
			} else {
				// Formulaire valide..
				String selectedLivre = (String) ClisteLivre.getSelectedItem();
				String selectedEdition = (String) ClisteEdition.getSelectedItem();
				String selectedTraduction = (String) ClisteTraduction.getSelectedItem();
				boolean confirm = lEx.ajouter(selectedLivre, selectedEdition, selectedTraduction, vn, vp, lL, lE, lTr);
				String message = confirm ? "Les " + Inombre.getText() + " exemplaires de '" + selectedLivre + "' a été ajouté !" : "Echec de création !";
				informationShow(message);
				setTextField();
				Blivre.doClick();
			}
		} else if(e.getSource() == BlisterExemplaire) {
			navigation(0, "exemplaire", "lister", new ArrayList<Integer>(), 0, null);
		} else if(e.getSource() == BsupprimerExemplaire) {
			navigation(0, "exemplaire", "supprimer", new ArrayList<Integer>(), 0, null);
		}

		// Traduction
		if(e.getSource() == BajouterTraduction_1) {
			inType = "ajouter";
			refresh("formulaire");
			Pformulaire.add(BajouterTraduction_2);
			Pformulaire.add(Llangue);
			Llangue.setText("Langue : ");
			Pformulaire.add(Ilangue);
			Pformulaire.add(Bretour);
		} else if(e.getSource() == BajouterTraduction_2) {
			// Vérification contenu..
			String vla = Ilangue.getText(); 
			int lvla = 18;
			if(lvla > vla.length()) lvla = vla.length();
			if(vla.length() < 1) erreur("Chaque champs doit renseigner au moins 1 caractères ! ");
			else if(vla.equalsIgnoreCase("")  
				|| vla.substring(0, lvla).equalsIgnoreCase("Entrer un nombre d'exemplaires..")) {
				erreur("Un des champs est vide ou incomplet !");
			} else {
				//formulaire valide
				boolean confirm = lTr.ajouter(vla);
				String message = confirm ? "La traduction a été ajouté !" : "Echec de création !";
				informationShow(message);				
				setTextField();
				Btraduction.doClick();
			}
		} else if(e.getSource() == BsupprimerTraduction) {
			navigation(0, "traduction", "supprimer", new ArrayList<Integer>(), 0, null);
		} else if(e.getSource() == BlisterTraduction) {
			navigation(0, "traduction", "lister", new ArrayList<Integer>(), 0, null);
		}
	}

	/**
	 * Evenement de souris
	 */
	public void mouseEntered(MouseEvent m) {}
	public void mouseExited(MouseEvent m) {}
	public void mousePressed(MouseEvent m) {}
	public void mouseReleased(MouseEvent m) {
		// Bouton recherche
		if(m.getSource() == Irecherche) {
			if(Irecherche.getText().equalsIgnoreCase("Entrer votre recherche..")) Irecherche.setText("");
			Irecherche.requestFocus();
		}
		if(m.getSource() != Irecherche) if(Irecherche.getText().equalsIgnoreCase("")) Irecherche.setText("Entrer votre recherche..");			

		// Boutons nom - auteur
		if(m.getSource() == InomAuteur) {
			if(InomAuteur.getText().equalsIgnoreCase("Entrer un nom..")) {
				InomAuteur.requestFocus();
			}
			InomAuteur.setText("");			
		}
		if(m.getSource() != InomAuteur) if(InomAuteur.getText().equalsIgnoreCase("")) InomAuteur.setText("Entrer un nom..");

		// Boutons prénom - auteur
		if(m.getSource() == IprenomAuteur) {
			if(IprenomAuteur.getText().equalsIgnoreCase("Entrer un prénom..")) {
				IprenomAuteur.setText("");
			}
			IprenomAuteur.requestFocus();
		}
		if(m.getSource() != IprenomAuteur && IprenomAuteur.getText().equalsIgnoreCase("")) {
			IprenomAuteur.setText("Entrer un prénom..");
		}

		// Boutons pseudonyme - auteur
		if(m.getSource() == IpseudoAuteur) {
			if(IpseudoAuteur.getText().equalsIgnoreCase("Entrer un pseudonyme..")) {
				IpseudoAuteur.setText("");
			}
			IpseudoAuteur.requestFocus();
		}
		if(m.getSource() != IpseudoAuteur && IpseudoAuteur.getText().equalsIgnoreCase("")) {
			IpseudoAuteur.setText("Entrer un pseudonyme..");
		}

		// Boutons titre - livre
		if(m.getSource() == Ititre) {
			if(Ititre.getText().equalsIgnoreCase("Entrer un titre..")) {
				Ititre.setText(""); 
			}
			Ititre.requestFocus();
		}
		if(m.getSource() != Ititre && Ititre.getText().equalsIgnoreCase("")) {
			Ititre.setText("Entrer un titre..");
		}

		// Boutons description - livre
		if(m.getSource() == IDescription) {
			if(IDescription.getText().equalsIgnoreCase("Entrer une description..")) {
				IDescription.setText("");
			}
			IDescription.requestFocus();
		}
		if(m.getSource() != IDescription && Description.getText().equalsIgnoreCase("")) {
			IDescription.setText("Entrer une description..");
		}

		// Boutons type - livre
		if(m.getSource() == ItypeLivre) {
			if(ItypeLivre.getText().equalsIgnoreCase("Entrer un type de livre..")) {
				ItypeLivre.setText("");
			}
			ItypeLivre.requestFocus();
		}
		if(m.getSource() != ItypeLivre && ItypeLivre.getText().equalsIgnoreCase("")) {
			ItypeLivre.setText("Entrer un type de livre..");
		}

		// Boutons recherche lettre auteur- livre
		if(m.getSource() == IlettreAuteur) {
			if(IlettreAuteur.getText().equalsIgnoreCase("Entrer l'auteur..")) {
				IlettreAuteur.setText("");
			}
			IlettreAuteur.requestFocus();
		}
		if(m.getSource() != IlettreAuteur && IlettreAuteur.getText().equalsIgnoreCase("")) {
			IlettreAuteur.setText("Entrer l'auteur..");
		}

		// Champs mention - édition
		if(m.getSource() == Imention) {
			if(Imention.getText().equalsIgnoreCase("Entrer une mention..")) { 
				Imention.setText("");
			}
			Imention.requestFocus();
		}
		if(m.getSource() != Imention && Imention.getText().equalsIgnoreCase("")) {
			Imention.setText("Entrer une mention..");
		}

		// Boutons nom - lecteur
		if(m.getSource() == InomLecteur) {
			if(InomLecteur.getText().equalsIgnoreCase("Entrer un nom..")) {
				InomLecteur.setText("");
			}
			InomLecteur.requestFocus();
		}
		if(m.getSource() != InomLecteur && InomLecteur.getText().equalsIgnoreCase("")) {
			InomLecteur.setText("Entrer un nom..");
		}

		// Boutons prénom - lecteur
		if(m.getSource() == IprenomLecteur) {
			if(IprenomLecteur.getText().equalsIgnoreCase("Entrer un prénom..")) {
				IprenomLecteur.setText("");
			}
			IprenomLecteur.requestFocus();
		}
		if(m.getSource() != IprenomLecteur && IprenomLecteur.getText().equalsIgnoreCase("")) {
			IprenomLecteur.setText("Entrer un prénom..");
		}

		// Boutons pseudonyme - lecteur
		if(m.getSource() == IpseudonymeLecteur) {
			if(IpseudonymeLecteur.getText().equalsIgnoreCase("Entrer un pseudonyme..")) {
				IpseudonymeLecteur.setText("");
			}
			IpseudonymeLecteur.requestFocus();
		}
		if(m.getSource() != IpseudonymeLecteur && IpseudonymeLecteur.getText().equalsIgnoreCase("")) {
			IpseudonymeLecteur.setText("Entrer un pseudonyme..");
		}

		// Boutons pcw - lecteur
		if(m.getSource() == Ipcw) {
			if(Ipcw.getText().equalsIgnoreCase("Entrer un password..")) {
				Ipcw.setText("");
			}
			Ipcw.requestFocus();
		}
		if(m.getSource() != Ipcw && Ipcw.getText().equalsIgnoreCase("")) { 
			Ipcw.setText("Entrer un password.."); 
		}

		// Boutons langue - exemplaire
		if(m.getSource() == IlettreLangue) {
			if(IlettreLangue.getText().equalsIgnoreCase("Entrer la langue..")) {
				IlettreLangue.setText("");
			}
			IlettreLangue.requestFocus();
		}
		if(m.getSource() != IlettreLangue && IlettreLangue.getText().equalsIgnoreCase("")) {
			IlettreLangue.setText("Entrer la langue..");
		}

		// Boutons nombre - exemplaire
		if(m.getSource() == Inombre) {
			if(Inombre.getText().equalsIgnoreCase("Entrer un nombre d'exemplaires..")) {
				Inombre.setText("");
			}
			Inombre.requestFocus();
		}
		if(m.getSource() != Inombre && Inombre.getText().equalsIgnoreCase("")) {
			Inombre.setText("Entrer un nombre d'exemplaires..");
		}

		// Boutons lettre livre - exemplaire
		if(m.getSource() == IlettreLivre) {
			if(IlettreLivre.getText().equalsIgnoreCase("Entrer le titre..")) {
				IlettreLivre.setText("");
			}
			IlettreLivre.requestFocus();
		}
		if(m.getSource() != IlettreLivre && IlettreLivre.getText().equalsIgnoreCase("")) {
			IlettreLivre.setText("Entrer le titre..");
		}

		// Boutons lettre éditeur - exemplaire
		if(m.getSource() == IlettreEdition) {
			if(IlettreEdition.getText().equalsIgnoreCase("Entrer l'éditeur..")) {
				IlettreEdition.setText("");
			}
			IlettreEdition.requestFocus();
		}
		if(m.getSource() != IlettreEdition && IlettreEdition.getText().equalsIgnoreCase("")) {
			IlettreEdition.setText("Entrer l'éditeur..");
		}

		// Boutons prix - exemplaire
		if(m.getSource() == Iprix) {
			if(Iprix.getText().equalsIgnoreCase("Entrer un prix..")) {
				Iprix.setText("");
			}
			Iprix.requestFocus();
		}
		if(m.getSource() != Iprix && Iprix.getText().equalsIgnoreCase("")) {
			Iprix.setText("Entrer un prix..");
		}

		// Boutons langue - traduction
		if(m.getSource() == Ilangue) {
			if(Ilangue.getText().equalsIgnoreCase("Entrer une langue..")) {
				Ilangue.setText("");
			}
			Ilangue.requestFocus();
		}
		if(m.getSource() != Ilangue && Ilangue.getText().equalsIgnoreCase("")) {
			Ilangue.setText("Entrer une langue..");
		}
	}

	public void mouseClicked(MouseEvent m) {
		if(m.getSource() == infoVersion) {
			afficheUrl("mcalmel.fr", 0);
		}

		// Champs recherche
		if(m.getSource() == Irecherche) {
			if(Irecherche.getText().equalsIgnoreCase("Entrer votre recherche..")) {
				Irecherche.setText("");
			}
			Irecherche.requestFocus();
		}
		if(m.getSource() != Irecherche && Irecherche.getText().equalsIgnoreCase("")) {
			Irecherche.setText("Entrer votre recherche..");
		}

		// Champs nom - auteur
		if(m.getSource() == InomAuteur) {
			if(InomAuteur.getText().equalsIgnoreCase("Entrer un nom..")) {
				InomAuteur.setText("");
			}
			InomAuteur.requestFocus();
		}
		if(m.getSource() != InomAuteur && InomAuteur.getText().equalsIgnoreCase("")) {
			InomAuteur.setText("Entrer un nom..");
		}

		// Champs prénom - auteur
		if(m.getSource() == IprenomAuteur) {
			if(IprenomAuteur.getText().equalsIgnoreCase("Entrer un prénom..")) {
				IprenomAuteur.setText("");
			}
			IprenomAuteur.requestFocus();
		}
		if(m.getSource() != IprenomAuteur && IprenomAuteur.getText().equalsIgnoreCase("")) {
			IprenomAuteur.setText("Entrer un prénom..");
		}

		// Champs pseudonyme - auteur
		if(m.getSource() == IpseudoAuteur) {
			if(IpseudoAuteur.getText().equalsIgnoreCase("Entrer un pseudonyme..")) {
				IpseudoAuteur.setText("");
			}
			IpseudoAuteur.requestFocus();
		}
		if(m.getSource() != IpseudoAuteur && IpseudoAuteur.getText().equalsIgnoreCase("")) {
			IpseudoAuteur.setText("Entrer un pseudonyme..");
		}

		// Champs titre - livre
		if(m.getSource() == Ititre) {
			if(Ititre.getText().equalsIgnoreCase("Entrer un titre..")) {
				Ititre.setText("");
			}
			Ititre.requestFocus();
		}
		if(m.getSource() != Ititre && Ititre.getText().equalsIgnoreCase("")) {
			Ititre.setText("Entrer un titre..");
		}

		// Champs description - livre
		if(m.getSource() == IDescription) {
			if(IDescription.getText().equalsIgnoreCase("Entrer une description..")) {
				IDescription.setText("");
			}
			IDescription.requestFocus();
		}
		if(m.getSource() != IDescription && IDescription.getText().equalsIgnoreCase("")) {
			IDescription.setText("Entrer une description..");
		}

		// Champs type - livre
		if(m.getSource() == ItypeLivre) {
			if(ItypeLivre.getText().equalsIgnoreCase("Entrer un type de livre..")) {
				ItypeLivre.setText("");
			}
			ItypeLivre.requestFocus();
		}
		if(m.getSource() != ItypeLivre && ItypeLivre.getText().equalsIgnoreCase("")) {
			ItypeLivre.setText("Entrer un type de livre..");
		}

		// Champs lettre recherche auteur - livre
		if(m.getSource() == IlettreAuteur) {
			if(IlettreAuteur.getText().equalsIgnoreCase("Entrer l'auteur..")) {
				IlettreAuteur.setText("");
			}
			IlettreAuteur.requestFocus();
		}
		if(m.getSource() != IlettreAuteur && IlettreAuteur.getText().equalsIgnoreCase("")) {
			IlettreAuteur.setText("Entrer l'auteur..");
		}

		// Champs mention - édition
		if(m.getSource() == Imention) {
			if(Imention.getText().equalsIgnoreCase("Entrer une mention..")) {
				Imention.setText("");
			}
			Imention.requestFocus();
		}
		if(m.getSource() != Imention && Imention.getText().equalsIgnoreCase("")) {
			Imention.setText("Entrer une mention..");
		}

		// Champs nom - lecteur
		if(m.getSource() == InomLecteur) {
			if(InomLecteur.getText().equalsIgnoreCase("Entrer un nom..")) {
				InomLecteur.setText("");
			}
			InomLecteur.requestFocus();
		}
		if(m.getSource() != InomLecteur && InomLecteur.getText().equalsIgnoreCase("")) {
			InomLecteur.setText("Entrer un nom..");
		}

		// Champs prénom - lecteur
		if(m.getSource() == IprenomLecteur) {
			if(IprenomLecteur.getText().equalsIgnoreCase("Entrer un prénom..")) {
				IprenomLecteur.setText("");
			}
			IprenomLecteur.requestFocus();
		}
		if(m.getSource() != IprenomLecteur && IprenomLecteur.getText().equalsIgnoreCase("")) {
			IprenomLecteur.setText("Entrer un prénom..");
		}

		// Champs pseudonyme - lecteur
		if(m.getSource() == IpseudonymeLecteur) {
			if(IpseudonymeLecteur.getText().equalsIgnoreCase("Entrer un pseudonyme..")) {
				IpseudonymeLecteur.setText("");
			}
			IpseudonymeLecteur.requestFocus();
		}
		if(m.getSource() != IpseudonymeLecteur && IpseudonymeLecteur.getText().equalsIgnoreCase("")) {
			IpseudonymeLecteur.setText("Entrer un pseudonyme..");
		}

		// Champs pcw - lecteur
		if(m.getSource() == Ipcw) {
			if(Ipcw.getText().equalsIgnoreCase("Entrer un password..")) {
				Ipcw.setText("");
			}
			Ipcw.requestFocus();
		}
		if(m.getSource() != Ipcw && Ipcw.getText().equalsIgnoreCase("")) {
			Ipcw.setText("Entrer un password..");
		}

		// Champs langue - exemplaire
		if(m.getSource() == IlettreLangue) {
			if(IlettreLangue.getText().equalsIgnoreCase("Entrer la langue..")) {
				IlettreLangue.setText("");
			}
			IlettreLangue.requestFocus();
		}
		if(m.getSource() != IlettreLangue && IlettreLangue.getText().equalsIgnoreCase("")) {
			IlettreLangue.setText("Entrer la langue..");
		}

		// Champs nombre - exemplaire
		if(m.getSource() == Inombre) {
			if(Inombre.getText().equalsIgnoreCase("Entrer un nombre d'exmplaires..")) {
				Inombre.setText("");
			}
			Inombre.requestFocus();
		}
		if(m.getSource() != Inombre && Inombre.getText().equalsIgnoreCase("")) {
			Inombre.setText("Entrer un nombre d'exmplaires..");
		}

		//	Champs lettre_livre - exemplaire
		if(m.getSource() == IlettreLivre) {
			if(IlettreLivre.getText().equalsIgnoreCase("Entrer un titre..")) {
				IlettreLivre.setText("");
			}
			IlettreLivre.requestFocus();
		}
		if(m.getSource() != IlettreLivre && IlettreLivre.getText().equalsIgnoreCase("")) {
			IlettreLivre.setText("Entrer un titre..");
		}
		
		//	Champs lettre édition - exemplaire
		if(m.getSource() == IlettreEdition) {
			if(IlettreEdition.getText().equalsIgnoreCase("Entrer une édition..")) {
				IlettreEdition.setText("");
			}
			IlettreEdition.requestFocus();
		}
		if(m.getSource() != IlettreEdition && IlettreEdition.getText().equalsIgnoreCase("")) {
			IlettreEdition.setText("Entrer une édition..");
		}		

		//	Champs prix - exemplaire
		if(m.getSource() == Iprix) {
			if(Iprix.getText().equalsIgnoreCase("Entrer un prix..")) {
				Iprix.setText("");
			}
			Iprix.requestFocus();
		}
		if(m.getSource() != Iprix && Iprix.getText().equalsIgnoreCase("")) {
			Iprix.setText("Entrer un prix..");
		}

		//	Champs langue - traduction
		if(m.getSource() == Ilangue) {
			if(Ilangue.getText().equalsIgnoreCase("Entrer une langue..")) {
				Ilangue.setText("");
			}
			Ilangue.requestFocus();
		}
		if(m.getSource() != Ilangue && Ilangue.getText().equalsIgnoreCase("")) {
			Ilangue.setText("Entrer une langue..");
		}
	}

	/**
	 * Fonctionnalité Focus
	 */
	public void focusGained(FocusEvent e) {
		// Champs Livre
		if(e.getSource() == IlettreAuteur) {
			if(IlettreAuteur.getText().equalsIgnoreCase("Entrer l'auteur..")) {
				IlettreAuteur.setText("");
			}
			IlettreAuteur.requestFocus();
		}
		if(e.getSource() == Ititre) {
			if(Ititre.getText().equalsIgnoreCase("Entrer un titre..")) {
				Ititre.setText(""); 
			}
			Ititre.requestFocus();
		}
		if(e.getSource() == IDescription) {
			if(IDescription.getText().equalsIgnoreCase("Entrer une description..")) {
				IDescription.setText("");
			}
			IDescription.requestFocus();
		}
		if(e.getSource() == ItypeLivre) {
			if(ItypeLivre.getText().equalsIgnoreCase("Entrer un type de livre..")) {
				ItypeLivre.setText("");
			}
			ItypeLivre.requestFocus();
		}
		//CHAMPS auteur
		if(e.getSource() == InomAuteur) {
			if(IlettreAuteur.getText().equalsIgnoreCase("Entrer un nom..")) {
				InomAuteur.setText("");
			}
			InomAuteur.requestFocus();
		}
		if(e.getSource() == IprenomAuteur) {
			if(IprenomAuteur.getText().equalsIgnoreCase("Entrer un prénom..")) {
				IprenomAuteur.setText("");
			}
			IprenomAuteur.requestFocus();
		}
		if(e.getSource() == IpseudoAuteur) {
			if(IpseudoAuteur.getText().equalsIgnoreCase("Entrer un pseudonyme..")) {
				IpseudoAuteur.setText("");
			}
			IpseudoAuteur.requestFocus();
		}
		//CHAMPS édition
		if(e.getSource() == Imention) {
			if(Imention.getText().equalsIgnoreCase("Entrer une mention..")) {
				Imention.setText(""); 
			}
			Imention.requestFocus();
		}
		//CHAMPS lecteur
		if(e.getSource() == InomLecteur) {
			if(InomLecteur.getText().equalsIgnoreCase("Entrer un nom..")) {
				InomLecteur.setText("");
			}
			InomLecteur.requestFocus();
		}
		if(e.getSource() == IprenomLecteur) {
			if(IprenomLecteur.getText().equalsIgnoreCase("Entrer un prénom..")) {
				IprenomLecteur.setText("");
			}
			IprenomLecteur.requestFocus();
		}
		if(e.getSource() == IpseudonymeLecteur) {
			if(IpseudonymeLecteur.getText().equalsIgnoreCase("Entrer un pseudonyme..")) {
				IpseudonymeLecteur.setText("");
			}
			IpseudonymeLecteur.requestFocus();
		}
		if(e.getSource() == Ipcw) {
			if(Ipcw.getText().equalsIgnoreCase("Entrer un password..")) {
				Ipcw.setText("");
			}
			Ipcw.requestFocus();
		}
		if(e.getSource() == IlettreLangue) {
			if(IlettreLangue.getText().equalsIgnoreCase("Entrer la langue..")) {
				IlettreLangue.setText("");
			}
			IlettreLangue.requestFocus();
		}
		if(e.getSource() == Inombre) {
			if(Inombre.getText().equalsIgnoreCase("Entrer un nombre d'exemplaires..")) {
				Inombre.setText("");
			}
			Inombre.requestFocus();
		}
		if(e.getSource() == IlettreLivre) {
			if(IlettreLivre.getText().equalsIgnoreCase("Entrer le titre..")) {
				IlettreLivre.setText("");
			}
			IlettreLivre.requestFocus();
		}
		if(e.getSource() == IlettreEdition) {
			if(IlettreEdition.getText().equalsIgnoreCase("Entrer l'éditeur..")) { 
				IlettreEdition.setText("");
			}
			IlettreEdition.requestFocus();
		}
		if(e.getSource() == Iprix) {
			if(Iprix.getText().equalsIgnoreCase("Entrer un prix..")) { 
				Iprix.setText("");
			}
			Iprix.requestFocus();
		}
		if(e.getSource() == Ilangue) {
			if(Ilangue.getText().equalsIgnoreCase("Entrer une langue..")) { 
				Ilangue.setText("");
			}
			Ilangue.requestFocus();
		}
	}

	/**
	 * Focus perdu
	 */
	public void focusLost(FocusEvent e) {
		// Champs livre
		if(e.getSource() == IlettreAuteur && IlettreAuteur.getText().equalsIgnoreCase("")) {
			IlettreAuteur.setText("Entrer l'auteur..");
		}
		if(e.getSource() == Ititre && Ititre.getText().equalsIgnoreCase("")) {
			Ititre.setText("Entrer un titre..");
		}
		if(e.getSource() == IDescription && IDescription.getText().equalsIgnoreCase("")) {
			IDescription.setText("Entrer une description..");
		}
		if(e.getSource() == ItypeLivre && ItypeLivre.getText().equalsIgnoreCase("")) {
			ItypeLivre.setText("Entrer un type de livre.."); 
		}
		// Champs auteur
		if(e.getSource() == InomAuteur && InomAuteur.getText().equalsIgnoreCase("")) { 
			InomAuteur.setText("Entrer un nom..");
		}
		if(e.getSource() == IprenomAuteur && IprenomAuteur.getText().equalsIgnoreCase("")) {
			IprenomAuteur.setText("Entrer un prénom..");
		}
		if(e.getSource() == IpseudoAuteur && IpseudoAuteur.getText().equalsIgnoreCase("")) {
			IpseudoAuteur.setText("Entrer un pseudonyme..");
		}
		// Champs édition
		if(e.getSource() == Imention && Imention.getText().equalsIgnoreCase("")) {
			Imention.setText("Entrer une mention..");
		}
		// Champs lecteur / client
		if(e.getSource() == InomLecteur && InomLecteur.getText().equalsIgnoreCase("")) {
			InomLecteur.setText("Entrer un nom..");
		}
		if(e.getSource() == IprenomLecteur && IprenomLecteur.getText().equalsIgnoreCase("")) {
			IprenomLecteur.setText("Entrer un prénom..");
		}
		if(e.getSource() == IpseudonymeLecteur && IpseudonymeLecteur.getText().equalsIgnoreCase("")) {
			IpseudonymeLecteur.setText("Entrer un pseudonyme..");
		}
		if(e.getSource() == Ipcw && Ipcw.getText().equalsIgnoreCase("")) {
			Ipcw.setText("Entrer un password..");
		}
		// Champs exemplaire
		if(e.getSource() == Inombre && Inombre.getText().equalsIgnoreCase("")) {
			Inombre.setText("Entrer un nombre d'exemplaires..");
		}
		if(e.getSource() == IlettreLivre && IlettreLivre.getText().equalsIgnoreCase("")) {
			IlettreLivre.setText("Entrer le titre..");
		}
		if(e.getSource() == IlettreEdition && IlettreEdition.getText().equalsIgnoreCase("")) {
			IlettreEdition.setText("Entrer l'éditeur..");
		}
		if(e.getSource() == IlettreLangue && IlettreLangue.getText().equalsIgnoreCase("")) {
			IlettreLangue.setText("Entrer la langue..");
		}
		if(e.getSource() == Iprix && Iprix.getText().equalsIgnoreCase("")) {
			Iprix.setText("Entrer un prix..");
		}
		if(e.getSource() == Ilangue && Ilangue.getText().equalsIgnoreCase("")) {
			Ilangue.setText("Entrer une langue..");
		}
	}

	public void changedUpdate(DocumentEvent e) {}

	public void insertUpdate(DocumentEvent e) {
		if(e.getDocument() == Irecherche.getDocument()) {
			recherche();
			Irecherche.requestFocus();
		}
		if(e.getDocument() == Inavigation.getDocument()) {
			rechercheNavigation();
			Inavigation.requestFocus();
		}
		if(e.getDocument() == IlettreAuteur.getDocument()) {
			rechercheAuteur();
			IlettreAuteur.requestFocus();
		}
		if(e.getDocument() == IlettreLivre.getDocument()) {
			rechercheLivre();
			IlettreLivre.requestFocus();
		}
		if(e.getDocument() == IlettreEdition.getDocument()) {
			rechercheEdition();
			IlettreAuteur.requestFocus();
		}
		if(e.getDocument() == IlettreLangue.getDocument()) {
			rechercheTraduction();
			IlettreLangue.requestFocus();
		}
	}

	public void removeUpdate(DocumentEvent e) { 
		if(e.getDocument() == Irecherche.getDocument()) {
			recherche();
			Irecherche.requestFocus();
		}
		if(e.getDocument() == Inavigation.getDocument()) {
			rechercheNavigation();
			Inavigation.requestFocus();
		}
		if(e.getDocument() == IlettreAuteur.getDocument()) {
			rechercheAuteur();
			IlettreAuteur.requestFocus();
		}
		if(e.getDocument() == IlettreLivre.getDocument()) {
			rechercheLivre();
			IlettreLivre.requestFocus();
		}
		if(e.getDocument() == IlettreEdition.getDocument()) {
			rechercheEdition();
			IlettreAuteur.requestFocus();
		}
		if(e.getDocument() == IlettreLangue.getDocument()) {
			rechercheTraduction();
			IlettreLangue.requestFocus();
		}
	}
}
