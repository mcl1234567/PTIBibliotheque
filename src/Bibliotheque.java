import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.channels.FileChannel;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class Bibliotheque extends JFrame implements ActionListener, MouseListener, FocusListener, DocumentListener {
	JPanel Plivre = new JPanel();
	JPanel Pauteur = new JPanel();
	JPanel Pedition = new JPanel();
	JPanel Plecteur = new JPanel();
	JPanel PAffichage = new JPanel();
	JPanel Pexemplaire = new JPanel();
	JPanel Ptraduction = new JPanel();
	JPanel Pformulaire = new JPanel();

	JButton Badmin = new JButton("Administration");
	JButton Bclient = new JButton("Client");
	JButton Bauteur = new JButton("Auteur");
	JButton Bedition = new JButton("Edition");
	JButton Blivre = new JButton("Livre");
	JButton Blecteur = new JButton("Lecteur");
	JButton Bexemplaire = new JButton("Exemplaire");
	JButton Btraduction = new JButton("Traduction");

	JButton Buploader = new JButton("Uploader");
	JButton BajouterAuteur = new JButton("Ajouter un auteur");
	JButton BajouterAuteur_2 = new JButton("Ajouter l'auteur");	
	JButton BajouterEdition_1 = new JButton("Ajouter une édition");
	JButton BajouterEdition_2 = new JButton("Créer");
	JButton BajouterLivre_1 = new JButton("Ajouter un livre");
	JButton BajouterLivre_2 = new JButton("Ajouter un livre");
	JButton BlisterEdition = new JButton("Lister les éditions");
	JButton BlisterLivre = new JButton("Lister les livres");
	JButton BlisterAuteur = new JButton("Lister les auteurs");
	JButton BlisterLecteur = new JButton("Lister les lecteurs");
	JButton Brechercher = new JButton("Rechercher");
	JButton BmodifierLivre_1 = new JButton("Modifier un livre");
	JButton BmodifierLivre_3 = new JButton("Modifier");
	JButton BmodifierEdition = new JButton("Modifier une édition");
	JButton BmodifierExemplaire = new JButton("Modifier des exemplaires");
	JButton BmodifierAuteur_1 = new JButton("Modifier un auteur");
	JButton BmodifierAuteur_3 = new JButton("Modifier");
	JButton BmodifierLecteur_1 = new JButton("Modifier un lecteur");
	JButton BmodifierLecteur_3 = new JButton("Modifier");
	JButton BmodifierEdition_3 = new JButton("Modifier");
	JButton BsupprimerLecteur = new JButton("Supprimer un lecteur");
	JButton BsupprimerAuteur = new JButton("Supprimer un auteur");
	JButton BsupprimerEdition = new JButton("Supprimer une édition");
	JButton BsupprimerLivre_1 = new JButton("Supprimer un livre");
	JButton Bsupprimer_2 = new JButton("Supprimer");
	JButton Bacheter_2 = new JButton("Acheter");
	JButton Blire_2 = new JButton("Lire");
	JButton Blire_3 = new JButton("Lire");
	JButton Bmodifier_2 = new JButton("Modifier");
	static JButton Bretour = new JButton("Retour");
	JButton Bprecedent = new JButton("précédent");
	JButton Bsuivant = new JButton("suivant");
	JButton BajouterLecteur_1 = new JButton("Ajouter un lecteur");
	JButton BajouterLecteur_2 = new JButton("Ajouter");
	JButton BcreerExemplaire_1 = new JButton("Créer des exemplaires");
	JButton BcreerExemplaire_2 = new JButton("Créer");
	JButton BlisterExemplaire = new JButton("Lister des exemplaires");
	JButton BsupprimerExemplaire = new JButton("Supprimer des exemp.");
	JButton BajouterTraduction_1 = new JButton("Ajouter une traduction");
	JButton BajouterTraduction_2 = new JButton("Ajouter");
	JButton BsupprimerTraduction = new JButton("Supprimer une traduction");
	JButton BsupprimerTraduction_3 = new JButton("Supprimer");
	JButton BlisterTraduction = new JButton("Lister les traductions");
	JButton Bacheter_3 = new JButton("Acheter");

	// Client
	JButton Binscrire = new JButton("Créer un compte");
	JButton Bconnecter_1 = new JButton("Connexion");
	JButton Bconnecter_2 = new JButton("Valider");
	JButton BcreerLecteur_2 = new JButton("Enregistrer");
	JButton Bacheter = new JButton("Acheter");
	JButton Blire = new JButton("Lire");
	JButton Bprofil = new JButton("Profil");
	JButton Bdeconnecter = new JButton("Déconnexion");
	JButton BprecedentObjet = new JButton("Précédent");
	JButton BsuivantObjet = new JButton("Suivant");	

	JTextField InomAuteur = new JTextField();
	JTextField IprenomAuteur = new JTextField();
	JTextField Imention = new JTextField();
	JTextField InomLecteur = new JTextField();
	JTextField IprenomLecteur = new JTextField();
	JTextField IpseudonymeLecteur = new JTextField();
	JTextField IpseudoAuteur = new JTextField();
	JTextField Ititre = new JTextField();
	JTextField IDescription = new JTextField();
	JTextField ItypeLivre = new JTextField();	
	JTextField Iprix = new JTextField("Entrer un prix..");
	JTextField Ilangue = new JTextField("Entrer une langue..");
	JTextField Ipcw = new JTextField("Entrer un password..");
	JTextField Inombre = new JTextField("Entrer un nombre d'exemplaires..");
	JTextField Inavigation = new JTextField();
	public JTextField Irecherche = new JTextField();
	JTextField IlettreLangue = new JTextField("Entrer la langue..");
	JTextField IlettreAuteur = new JTextField("Entrer votre recherche..");
	JTextField IlettreLivre = new JTextField("Entrer le titre..");
	JTextField IlettreEdition = new JTextField("Entrer l'éditeur..");

	static private final JLabel infoVersion = new JLabel("Développé par CALMEL Morvan - Application 'Bibliothèque' bêta - site web: www.mcalmel.fr");
	static JLabel LinfoResultat = new JLabel("");
	JLabel Lgestion = new JLabel(" -- Gestion --");
	JLabel LnomAuteur = new JLabel("Nom : ");
	JLabel LprenomAuteur = new JLabel("Prénom : ");
	JLabel LpseudoAuteur = new JLabel("Pseudonyme : ");
	JLabel LnomLecteur = new JLabel("Nom : ");
	JLabel LprenomLecteur = new JLabel("Prénom : ");
	JLabel Ltitre = new JLabel("Titre : ");
	JLabel LDesc = new JLabel("Description : ");
	JLabel LtypeLivre = new JLabel("Genre du livre : ");
	JLabel Lmention = new JLabel("Mention : ");
	JLabel LlivreAffichage = new JLabel("");
	JLabel LinfoAuteur = new JLabel("");
	JLabel LinfoLivre = new JLabel("");
	JLabel Lassocauteur = new JLabel("Les auteurs disponibles : ");
	JLabel Lassoclangue = new JLabel("Les langues disponibles : ");
	JLabel Lassoclivre = new JLabel("Les livres disponibles : ");
	JLabel Lassocedition = new JLabel("Les éditions disponibles : ");
	JLabel Lfichier = new JLabel("");
	JLabel Lnavigation = new JLabel("");
	JLabel LinputNavigation = new JLabel(" pages");
	JLabel LinputNavigation2 = new JLabel("");
	JLabel LpseudonymeLecteur = new JLabel("Pseudonyme : ");
	JLabel Lpcw = new JLabel("Password : ");
	JLabel Lprix = new JLabel("Prix : ");
	JLabel Llangue = new JLabel("Langue : ");
	JLabel Lnombre = new JLabel("Nombre : ");

	JComboBox ClisteLivre = new JComboBox();
	JComboBox ClisteAuteur = new JComboBox();
	JComboBox ClisteEdition = new JComboBox();
	JComboBox ClisteTraduction = new JComboBox();

	JMenu Mmenu = new JMenu("Options");
	JMenuBar Mbarre = new JMenuBar();
	JMenuItem Mabout = new JMenuItem("A propos de");
	JFileChooser Fimage = new JFileChooser();
	ButtonGroup listeRadio = new ButtonGroup();

	private int numerotationDebut;							// tempo page de début
	private int numerotationFin;							// tempo page de fin
	private int pageTotal;									// tempo page total
	@SuppressWarnings("unused")
	private int numeroHidden;								// tempo numero de modification d'un objet
	private int pageEnCours;								// tempo page en cours
	@SuppressWarnings("unused")
	private int compteur;
	private boolean authentifie;									// tempo création compte
	private String[] info;
	private String nameFichier;								// tempo nom de l'image de la source
	private String dirFichier;								// tempo répertoire image de la source
	private String rep; 									// tempo répertoire image de stockage	
	private String inTable;									// tempo table
	private String inType = "";								// tempo type
	private String pseudonyme;								// tempo pseudo	
	private String inTableActuelle = "";					// tempo table actuelle
	private String rechercheTemp;

	LesLivres lL = new LesLivres();							// LES
	LesAuteurs lA = new LesAuteurs();						//		LISTES
	LesEditions lE = new LesEditions();						//				D'
	LesLecteurs lLect = new LesLecteurs();					//					OBJETS
	LesExemplaires lEx = new LesExemplaires();
	LesTraductions lTr = new LesTraductions();
	LesLectures lV = new LesLectures();
	LesAchats lAch = new LesAchats();

	String rep_app_url = "";
	String rep_app_pdf = "";
	String rep_app_doc = "";

	// A changer
	private ArrayList<Integer> resultatsRecherche = new ArrayList<Integer>(); 					// tableau temporaire de navigation
	// A revoir
	private ArrayList<Element> resultatsRechercheElement = new ArrayList<Element>();			// tableau temporaire de navigationElement 
	private ArrayList<Object> resultatsRechercheObjet = new ArrayList<Object>();				// Résultats par objet navigationObjet()

	ArrayList<Livre> lesLivres = new ArrayList<Livre>();
	ArrayList<Auteur> lesAuteurs = new ArrayList<Auteur>();
	ArrayList<Edition> lesEditions = new ArrayList<Edition>();
	ArrayList<Edition> lesElements = new ArrayList<Edition>();
	ArrayList<Lecteur> lesLecteurs = new ArrayList<Lecteur>();
	
	Controleur controleur;

	public Bibliotheque() {
		this.setLayout(null);
		this.setSize(950, 500);

		File dir = new File("C:/Users/Ultra/Pictures");					//Répertoire init
		this.Fimage.setFileSelectionMode(JFileChooser.FILES_ONLY);		//Valide que les fichiers
		this.Fimage.setCurrentDirectory(dir);							//Set directory init
		authentifie = false;
		nameFichier = dirFichier = "";
		rep = "C:\\eclipse\\Workspace\\pti_Bibliotheque\\src\\images";
		rep_app_url = "C:\\Users\\Ultra\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe";
		rep_app_pdf = "C:\\Users\\Ultra\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe";
		rep_app_doc = "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Microsoft Office";

		InomAuteur			.setText("Entrer un nom..");
		IprenomAuteur		.setText("Entrer un prénom..");
		IpseudoAuteur		.setText("Entrer un pseudonyme..");
		InomLecteur			.setText("Entrer un nom..");
		IprenomLecteur		.setText("Entrer un prénom..");
		Imention			.setText("Entrer une mention..");
		Ititre				.setText("Entrer un titre..");
		IDescription		.setText("Entrer une description..");
		ItypeLivre			.setText("Entrer un type de livre..");
		IlettreAuteur		.setText("Entrer l'auteur..");
		Irecherche			.setText("Entrer votre recherche..");
		IpseudonymeLecteur	.setText("Entrer un pseudonyme..");

		Lgestion			.setBounds(90, 0, 150, 20);
		LnomAuteur			.setBounds(30, 90, 150, 20);
		LnomLecteur			.setBounds(30, 90, 150, 20);
		Lmention			.setBounds(30, 90, 150, 20);
		Ltitre				.setBounds(30, 40, 150, 20);
		LprenomAuteur		.setBounds(30, 120, 150, 20);
		LpseudoAuteur		.setBounds(30, 150, 150, 20);
		LprenomLecteur		.setBounds(30, 120, 150, 20);
		LDesc				.setBounds(30, 70, 150, 20);
		LtypeLivre			.setBounds(30, 100, 150, 20);
		LinfoAuteur			.setBounds(20, 220, 250, 20);
		LinfoLivre			.setBounds(20, 220, 250, 20);
		Lassocauteur		.setBounds(200, 130, 150, 20);
		Lfichier			.setBounds(30, 250, 160, 20);
		Lnavigation			.setBounds(10, 0, 170, 20);
		LinfoResultat		.setBounds(320, 0, 200, 20);
		LinputNavigation	.setBounds(470, 378, 300, 20);	// Libellés des pages à entrer
		LinputNavigation2	.setBounds(480, 378, 300, 20);	// Libellés des pages à entrer
		LpseudonymeLecteur	.setBounds(30, 150, 150, 20);
		Lpcw				.setBounds(30, 180, 150, 20);
		Llangue				.setBounds(10, 40, 200, 20);
		Lnombre				.setBounds(30, 70, 100, 20);
		Lassoclivre			.setBounds(10, 168, 200, 20);
		Lassocedition		.setBounds(10, 208, 250, 20);
		Lassoclangue		.setBounds(10, 248, 250, 20);
		Lprix				.setBounds(30, 40, 100, 20);

		Badmin				.setBounds(10, 10, 117, 20);
		Blivre				.setBounds(30, 130, 100, 20);
		Bauteur				.setBounds(30, 90, 100, 20);
		Bedition			.setBounds(30, 170, 100, 20);
		Blecteur			.setBounds(30, 210, 100, 20);
		Bexemplaire			.setBounds(30, 250, 100, 20);
		Btraduction			.setBounds(30, 290, 100, 20);
		Bclient				.setBounds(10, 50, 117, 20);
		Bprofil				.setBounds(10, 110, 109, 20);
		Bacheter			.setBounds(10, 140, 109, 20);
		Blire				.setBounds(10, 170, 109, 20);
		Bconnecter_1		.setBounds(10, 130, 130, 20);
		Binscrire			.setBounds(10, 100, 130, 20);
		Bconnecter_2		.setBounds(150, 350, 120, 20);
		Bdeconnecter		.setBounds(10, 200, 109, 20);
		Brechercher			.setBounds(190, 330, 170, 20);	// générique
		Bmodifier_2			.setBounds(615, 0, 120, 20);	// générique
		Bsupprimer_2		.setBounds(615, 0, 120, 20);	// générique
		Bacheter_2			.setBounds(615, 0, 120, 20); 	//générique
		Bacheter_3			.setBounds(615, 0, 120, 20); 	//générique
		Blire_2				.setBounds(615, 0, 120, 20); 	//générique
		Blire_3 			.setBounds(615, 0, 120, 20); 	//générique
		Buploader			.setBounds(140, 250, 130, 20);

		BajouterLivre_1		.setBounds(10, 300, 170, 20);
		BlisterLivre		.setBounds(10, 330, 170, 20);
		BmodifierLivre_1	.setBounds(10, 360, 170, 20);
		BsupprimerLivre_1	.setBounds(190, 360, 170, 20);
		BajouterLivre_2		.setBounds(135, 350, 170, 20);		
		BmodifierLivre_3	.setBounds(150, 350, 170, 20);

		BajouterAuteur			.setBounds(10, 300, 170, 20);
		BajouterAuteur_2		.setBounds(150, 350, 170, 20);
		BlisterAuteur			.setBounds(10, 330, 170, 20);
		BmodifierAuteur_1		.setBounds(10, 360, 170, 20);
		BmodifierAuteur_3		.setBounds(150, 350, 170, 20);
		BsupprimerAuteur		.setBounds(190, 360, 170, 20);

		BajouterLecteur_1		.setBounds(10, 300, 170, 20);
		BlisterLecteur			.setBounds(10, 330, 170, 20);
		BsupprimerLecteur		.setBounds(190, 360, 170, 20);
		BmodifierLecteur_1		.setBounds(10, 360, 170, 20);
		BajouterLecteur_2		.setBounds(150, 350, 170, 20);		
		BmodifierLecteur_3		.setBounds(150, 350, 170, 20);		
		BcreerLecteur_2			.setBounds(150, 350, 170, 20);

		BmodifierEdition_3		.setBounds(150, 350, 170, 20);		
		BajouterEdition_1		.setBounds(10, 300, 170, 20);
		BajouterEdition_2		.setBounds(150, 350, 170, 20);
		BlisterEdition			.setBounds(10, 330, 170, 20);
		BmodifierEdition		.setBounds(10, 360, 170, 20);
		BsupprimerEdition		.setBounds(190, 360, 170, 20);
		
		BajouterTraduction_1	.setBounds(10, 300, 170, 20);
		BajouterTraduction_2	.setBounds(150, 350, 170, 20);
		BlisterTraduction		.setBounds(10, 330, 170, 20);
		BsupprimerTraduction	.setBounds(190, 360, 170, 20);

		BcreerExemplaire_1 		.setBounds(10, 300, 170, 20);
		BcreerExemplaire_2 		.setBounds(150, 350, 130, 20);
		BlisterExemplaire		.setBounds(10, 330, 170, 20);
		BsupprimerExemplaire	.setBounds(190, 360, 170, 20);

		Bretour					.setBounds(10, 350, 120, 20);
		Bprecedent				.setBounds(135, 350, 120, 20);
		Bsuivant				.setBounds(255, 350, 120, 20);

		Ipcw				.setBounds(130, 180, 150, 20);
		InomAuteur			.setBounds(130, 90, 150, 20);
		IprenomAuteur		.setBounds(130, 120, 150, 20);
		IpseudoAuteur		.setBounds(130, 150, 150, 20);
		InomLecteur			.setBounds(130, 90, 150, 20);
		IprenomLecteur		.setBounds(130, 120, 150, 20);
		Imention			.setBounds(130, 90, 150, 20);
		Ititre				.setBounds(130, 40, 150, 20);
		IDescription		.setBounds(130, 70, 150, 20);
		ItypeLivre			.setBounds(130, 100, 150, 20);
		IlettreAuteur		.setBounds(30, 160, 150, 20);
		Irecherche			.setBounds(160, 0, 150, 20);
		Inavigation			.setBounds(420, 380, 50, 20);
		IpseudonymeLecteur	.setBounds(130, 150, 150, 20);
		Inombre				.setBounds(130, 70, 200, 20);
		IlettreLivre		.setBounds(10, 190, 200, 20);
		IlettreEdition		.setBounds(10, 230, 200, 20);
		IlettreLangue		.setBounds(10, 270, 200, 20);
		Iprix				.setBounds(130, 40, 200, 20);
		Ilangue				.setBounds(100, 40, 130, 20);

		ClisteAuteur	.setBounds(200, 160, 150, 20);
		ClisteLivre		.setBounds(240, 190, 150, 20);
		ClisteEdition	.setBounds(240, 230, 150, 20);
		ClisteTraduction.setBounds(240, 270, 150, 20);
		ClisteLivre		.setVisible(true);
		ClisteAuteur	.setVisible(true);
		ClisteEdition	.setVisible(true);
		ClisteTraduction.setVisible(true);

		Plivre.	   	setBounds(140, 10, 800, 850);
		Pauteur.   	setBounds(140, 10, 800, 850);
		Pedition.  	setBounds(140, 10, 400, 450);		
		Plecteur.  	setBounds(140, 10, 400, 450);
		Pexemplaire.setBounds(140, 10, 400, 450);
		Ptraduction.setBounds(140, 10, 400, 450);
		PAffichage.	setBounds(140, 10, 750, 450);
		Pformulaire.setBounds(140, 10, 750, 450);

		this.setJMenuBar(Mbarre);
		Mbarre.add(Mmenu);
		Mmenu.add(Mabout);

		Pauteur.	setLayout(null);
		Plivre.		setLayout(null);
		Pedition.	setLayout(null);
		Pexemplaire.setLayout(null);
		Plecteur.	setLayout(null);
		Ptraduction.setLayout(null);
		PAffichage.	setLayout(null);
		Pformulaire.setLayout(null);

		Blivre.		setVisible(false);
		Bauteur.	setVisible(false);
		Bedition.	setVisible(false);
		Blecteur.	setVisible(false);
		Bexemplaire.setVisible(false);
		Btraduction.setVisible(false);

		Blire.			setVisible(false);
		Bacheter.		setVisible(false);
		Binscrire		.setVisible(false);
		Bprofil.		setVisible(false);
		Bconnecter_1.	setVisible(false);
		Bdeconnecter.	setVisible(false);

		Plivre.		setVisible(false);
		Pauteur.	setVisible(false);
		Pedition.	setVisible(false);
		Plecteur.	setVisible(false);
		Pexemplaire.setVisible(false);
		Ptraduction.setVisible(false);
		PAffichage.	setVisible(false);
		Pformulaire.setVisible(true);
		
		this.add(Plivre);
		this.add(Pauteur);
		this.add(Pedition);
		this.add(Plecteur);
		this.add(Pexemplaire);
		this.add(Ptraduction);
		this.add(PAffichage);
		this.add(Pformulaire);

		this.add(Badmin);
		this.add(Blivre);
		this.add(Bauteur);
		this.add(Bedition);
		this.add(Blecteur);
		this.add(Bexemplaire);
		this.add(Btraduction);
		
		this.add(Bclient);
		this.add(Bconnecter_1);
		this.add(Binscrire);
		this.add(Bprofil);
		this.add(Blire);
		this.add(Bacheter);
		this.add(Bdeconnecter);

		//boutons d'auteur
		Pauteur.add(Brechercher);
		Pauteur.add(BajouterAuteur);
		Pauteur.add(BlisterAuteur);
		Pauteur.add(BmodifierAuteur_1);
		Pauteur.add(BsupprimerAuteur);

		//boutons de lecteur
		Plecteur.add(BajouterLecteur_1);
		Plecteur.add(BsupprimerLecteur);
		Plecteur.add(BlisterLecteur);
		Plecteur.add(BmodifierLecteur_1);

		//boutons d'éditions
		Pedition.add(BajouterEdition_1);
		Pedition.add(BlisterEdition);
		Pedition.add(BmodifierEdition);
		Pedition.add(BsupprimerEdition);

		// Boutons de livre
		Plivre.add(Brechercher);
		Plivre.add(BajouterLivre_1);
		Plivre.add(BlisterLivre);
		Plivre.add(LinfoLivre);
		Plivre.add(BmodifierLivre_1);
		Plivre.add(BsupprimerLivre_1);

		// Boutons de exemplaire
		Pexemplaire.add(BcreerExemplaire_1);
		Pexemplaire.add(BlisterExemplaire);
		Pexemplaire.add(BsupprimerExemplaire);
		
		// Boutons de traduction
		Ptraduction.add(BajouterTraduction_1);
		Ptraduction.add(BsupprimerTraduction);
		Ptraduction.add(BlisterTraduction);

		BlisterTraduction.addActionListener(this);
		Bexemplaire.addActionListener(this);
		BcreerExemplaire_1.addActionListener(this);
		BcreerExemplaire_2.addActionListener(this);
		Badmin.addActionListener(this);
		Bclient.addActionListener(this);
		Bconnecter_1.addActionListener(this);
		Bconnecter_2.addActionListener(this);
		Bdeconnecter.addActionListener(this);
		BajouterAuteur.addActionListener(this);
		BajouterAuteur_2.addActionListener(this);
		BajouterEdition_1.addActionListener(this);
		BajouterEdition_2.addActionListener(this);
		BajouterLecteur_1.addActionListener(this);
		BajouterLecteur_2.addActionListener(this);
		BajouterLivre_1.addActionListener(this);
		BajouterLivre_2.addActionListener(this);
		Brechercher.addActionListener(this);
		BlisterEdition.addActionListener(this);
		BlisterAuteur.addActionListener(this);
		BlisterLivre.addActionListener(this);
		BmodifierAuteur_1.addActionListener(this);
		BmodifierAuteur_3.addActionListener(this);
		BmodifierLecteur_1.addActionListener(this);
		BmodifierLecteur_3.addActionListener(this);
		BmodifierEdition_3.addActionListener(this);
		BmodifierEdition.addActionListener(this);
		BmodifierExemplaire.addActionListener(this);
		BmodifierLivre_1.addActionListener(this);
		BmodifierLivre_3.addActionListener(this);
		Bmodifier_2.addActionListener(this);
		BsupprimerAuteur.addActionListener(this);
		BsupprimerEdition.addActionListener(this);
		BsupprimerLecteur.addActionListener(this);
		BsupprimerLivre_1.addActionListener(this);
		Bsupprimer_2.addActionListener(this);
		Bacheter_2.addActionListener(this);
		Bacheter_3.addActionListener(this);
		Blire_2.addActionListener(this);
		Blire_3.addActionListener(this);
		Buploader.addActionListener(this);
		Bretour.addActionListener(this);
		Blire.addActionListener(this);
		Bacheter.addActionListener(this);
		Bedition.addActionListener(this);
		Bauteur.addActionListener(this);
		Blecteur.addActionListener(this);
		Blivre.addActionListener(this);
		Bprecedent.addActionListener(this);
		Bsuivant.addActionListener(this);
		Mabout.addActionListener(this);
		ClisteAuteur.addActionListener(this);
		ClisteLivre.addActionListener(this);
		ClisteTraduction.addActionListener(this);
		ClisteEdition.addActionListener(this);
		Inavigation.addActionListener(this);
		Blire.addActionListener(this);
		Bacheter.addActionListener(this);
		Binscrire.addActionListener(this);
		BcreerLecteur_2.addActionListener(this);
		Bprofil.addActionListener(this);
		BlisterLecteur.addActionListener(this);
		BsupprimerExemplaire.addActionListener(this);
		Btraduction.addActionListener(this);
		BajouterTraduction_1.addActionListener(this);
		BajouterTraduction_2.addActionListener(this);
		BsupprimerTraduction.addActionListener(this);
		BsupprimerTraduction_3.addActionListener(this);
		BlisterExemplaire.addActionListener(this);

		IlettreLangue.addFocusListener(this);
		Inombre.addFocusListener(this);
		IlettreLivre.addFocusListener(this);
		IlettreEdition.addFocusListener(this);
		Ipcw.addFocusListener(this);
		Ititre.addFocusListener(this);
		IDescription.addFocusListener(this);
		ItypeLivre.addFocusListener(this);
		IlettreAuteur.addFocusListener(this);
		InomAuteur.addFocusListener(this);
		IprenomAuteur.addFocusListener(this);
		IpseudoAuteur.addFocusListener(this);
		Irecherche.addFocusListener(this);
		Imention.addFocusListener(this);
		InomLecteur.addFocusListener(this);
		IprenomLecteur.addFocusListener(this);
		IpseudonymeLecteur.addFocusListener(this);
		Iprix.addFocusListener(this);
		Ilangue.addFocusListener(this);

		Iprix.addMouseListener(this);
		IlettreLangue.addMouseListener(this);
		Inombre.addMouseListener(this);
		IlettreLivre.addMouseListener(this);
		IlettreEdition.addMouseListener(this);
		Ipcw.addMouseListener(this);
		Ititre.addMouseListener(this);
		IDescription.addMouseListener(this);
		ItypeLivre.addMouseListener(this);
		IlettreAuteur.addMouseListener(this);
		InomAuteur.addMouseListener(this);
		IprenomAuteur.addMouseListener(this);
		IpseudoAuteur.addMouseListener(this);
	    infoVersion.addMouseListener(this);
	    Irecherche.addMouseListener(this);
	    Imention.addMouseListener(this);
	    InomLecteur.addMouseListener(this);
	    IprenomLecteur.addMouseListener(this);
	    IpseudonymeLecteur.addFocusListener(this);
	    Ilangue.addFocusListener(this);

	    Irecherche.getDocument().addDocumentListener(this);
	    IlettreAuteur.getDocument().addDocumentListener(this);
	    IlettreEdition.getDocument().addDocumentListener(this);
	    IlettreLangue.getDocument().addDocumentListener(this);
	    IlettreLivre.getDocument().addDocumentListener(this);    

        addMouseListener(this);
	}

	public static void main(String args[]) {
		Bibliotheque bibliotheque = new Bibliotheque();
		bibliotheque.setVisible(true);
		bibliotheque.importer();
	}

	/**
	 * Importer des enregistrements de la bdd
	 * @return informations de la bdd pour instanciation des objets
	 */
	public String[][] importerLivre() {
		Exploitation bdd = new Exploitation();
		bdd.chargerPilote();
		bdd.connexion();
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
			Livre livre = new Livre(req[i]);
			this.lesLivres.add(livre);
		}
		bdd.deconnexion();
		return req;
	}

	public static void uploadFile(File sourceFile, File destFile) throws IOException {
	    FileChannel source = null;
	    FileChannel destination = null;
		if(!destFile.exists()) {
			destFile.createNewFile();
		}

	    try {
	        source = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destFile).getChannel();

	        // previous code: destination.transferFrom(source, 0, source.size());
	        // to avoid infinite loops, should be:
	        long count = 0;
	        long size = source.size();              
	        while((count += destination.transferFrom(source, 0, size-count)) < size);
	    } finally {
	        if(source != null) { source.close(); }
	        if(destination != null) { destination.close(); }
	    }
	}

	// Importation de la bdd
	public void importer() {
		lA.importer();
		lE.importer();
		lL.importer(lA);
		lLect.importer();
		lEx.importer();
		lTr.importer();
	}

	// Affiche un message d'erreur
	public void erreur(String message) {
		JOptionPane.showMessageDialog(this, message, message, JOptionPane.OK_OPTION);
	}

	// Affiche un message d'information
	public void informationShow(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	/**
	 * Ouverture d'URL dans navigateur
	 * @param urlName
	 */
	public void afficheUrl(String fName, int type) {
		String rep = "";
		if(type == 0) { 
			rep = rep_app_url; 
		}
		if(type == 1) { 
			rep = rep_app_pdf; 
		}
		if(type == 2) { 
			rep = rep_app_doc; 
		}
		try {
			Runtime.getRuntime().exec(rep + " " + fName);
		} catch(FileNotFoundException f) {
			String info = rep + "\n" + f.getMessage() + " introuvable !";
			System.err.println(info);
		} catch(IOException ioe) {
			String info = rep + "\n" + ioe;
			System.err.println(info);
		} catch(Exception e) {
			String info = e.getMessage() + "\n" + fName;
			System.err.println(info);
			JOptionPane.showMessageDialog(null, info);
		}
	}

	public void about() {
		JOptionPane.showMessageDialog(this, infoVersion, "A propos", JOptionPane.INFORMATION_MESSAGE);
	}

	// Initialiser les champs d'insertion / recherche.
	public void setTextField() {
		InomAuteur.setText("Entrer un nom..");
		IprenomAuteur.setText("Entrer un prénom..");
		IpseudoAuteur.setText("Entrer un pseudonyme..");
		InomLecteur.setText("Entrer un nom..");
		IprenomLecteur.setText("Entrer un prénom..");
		Imention.setText("Entrer une mention..");
		Ititre.setText("Entrer un titre..");
		IDescription.setText("Entrer une description..");
		ItypeLivre.setText("Entrer un type de livre..");
		IlettreAuteur.setText("Entrer l'auteur..");
		IlettreAuteur.setText("Entrer l'auteur..");
		IlettreAuteur.setText("Entrer l'auteur..");
		IlettreAuteur.setText("Entrer l'auteur..");
		Irecherche.setText("Entrer votre recherche..");

		dirFichier = "";
		// Initialisation.
		this.numeroHidden = 0;
	}

	/**
	* Navigation par objets
	*/
	public void navigation_Element_Objet(int direction, String classe, String type, ArrayList<Element> elements, int page) {
		refresh("affichage");
		PAffichage.removeAll();
		PAffichage.add(Lnavigation);
		PAffichage.add(Bretour);
		PAffichage.add(BprecedentObjet);
		PAffichage.add(BsuivantObjet);

		boolean resSpec = false; 		// affichage par la recherche 
		boolean resSpecWrong = false; 	// affichage par la recherche null ou vide
		boolean pageWrong = false; 		// défilement des pages personnalisé
		float pageMax = 0;
		@SuppressWarnings("unused")
		int debut = numerotationDebut, fin = numerotationFin, taille = 0, rpage = 0, numeroType = 0;
		@SuppressWarnings("unused")
		JButton bouton = null;
			
		if(!classe.equalsIgnoreCase(inTableActuelle) && !classe.equalsIgnoreCase("")
		|| !type.equalsIgnoreCase(inType) && !type.equalsIgnoreCase("")) {
			Inavigation.setText("");
		}
		// Initialisation type
		if(type.equalsIgnoreCase("")) { type = inType; } 
		else { inType = type; }

		// Initialisation table
		if(classe.equalsIgnoreCase("")) {
			classe = inTable;
		} else {
			inTable = classe; //obsolète
			inTableActuelle = classe;
		}
		// Initialisation recherche.
		if(elements.size() == 0) {
			elements = resultatsRechercheElement;
		} else {
			resultatsRechercheElement =  elements;
		}
		// Initialisation de la recherche.
		if(!type.equalsIgnoreCase("rechercher") 
		&& !type.equalsIgnoreCase("acheter") 
		&& !type.equalsIgnoreCase("lire")) {
			resultatsRechercheElement = new ArrayList<Element>();
		}
		// Libellé navigation
		String navigation = classe.substring(0, 1).toUpperCase() 
							+ classe.substring(1).toLowerCase() 
							+ " > " 
								+ type.substring(0, 1).toUpperCase() 
							+ type.substring(1).toLowerCase() 
							+ " > ";
		Lnavigation.setText(navigation);

		if(inType.equalsIgnoreCase("rechercher") 
		|| inType.equalsIgnoreCase("acheter") && !classe.equalsIgnoreCase("album")
		|| inType.equalsIgnoreCase("lire") && !classe.equalsIgnoreCase("album")) {
			resSpec = true;
			try {
				if(resultatsRechercheElement.size() == 0) {
					resSpecWrong = true;
				}
			} catch(NullPointerException e) {
				resSpecWrong = true;
			}
		}
		// Initialisation résultats par page & nombre de résultats
		if(classe.equalsIgnoreCase("livre")) {
			taille = resSpecWrong ? resultatsRechercheElement.size() : lesLivres.size();			
			if(!resSpecWrong) {
				taille = resSpec ? elements.size() : taille;
			}
			pageTotal = 3;	
		} else if(classe.equalsIgnoreCase("auteur")) {
			taille = resSpecWrong ? resultatsRechercheElement.size() : lesAuteurs.size();			
			if(!resSpecWrong) {
				taille = resSpec ? elements.size() : taille;
			}
			pageTotal = 2;
		}  else if(classe.equalsIgnoreCase("edition")) {
			taille = resSpecWrong ? resultatsRechercheElement.size() : lesEditions.size();			
			if(!resSpecWrong) {
				taille = resSpec ? elements.size() : taille;
			}
			pageTotal = 2;
		} else if(classe.equalsIgnoreCase("lecteur")) {
			taille = resSpecWrong ? resultatsRechercheElement.size() : lesLecteurs.size();			
			if(!resSpecWrong) {
				taille = resSpec ? elements.size() : taille;
			}
			pageTotal = 2;
		}
		rpage = pageTotal;
		pageMax = (float) taille / (float) pageTotal;
		pageMax = (float) Math.ceil(pageMax);
		if(pageMax < page) {
			pageWrong = true;
		}
		// Initialiser boutons (modifier, supprimer) et le type d'affichage
		if(type.equalsIgnoreCase("modifier")) {
			numeroType = 1;
			bouton = Bmodifier_2;
		} else if(type.equalsIgnoreCase("supprimer")) {
			numeroType = 2;
			bouton = Bsupprimer_2;
		} else if(type.equalsIgnoreCase("lister")) {
			numeroType = 0;
		} else if(type.equalsIgnoreCase("rechercher") && !resSpecWrong) {
			numeroType = 3;
			resultatsRechercheElement = elements;
			taille = resultatsRechercheElement.size();
		} else if(type.equalsIgnoreCase("acheter") && classe.equalsIgnoreCase("album")) {
			numeroType = 4;
			bouton = this.Bacheter_2;
		} else if(type.equalsIgnoreCase("acheter") && classe.equalsIgnoreCase("morceau") && !resSpecWrong) {
			numeroType = 5;
			bouton = this.Bacheter_3;
			resultatsRechercheElement = elements;
			taille = resultatsRechercheElement.size();
		} else if(type.equalsIgnoreCase("lire") && classe.equalsIgnoreCase("album")) {
			numeroType = 6;
			bouton = this.Blire_2;
		} else if(type.equalsIgnoreCase("lire") && classe.equalsIgnoreCase("morceau") && !resSpecWrong) {
			numeroType = 7;
			bouton = this.Blire_3;
			resultatsRechercheElement = elements;
			taille = resultatsRechercheElement.size();
		}
		if(direction == 0 && page == 0) {
			pageEnCours = 1;
		} else if(direction == 1) {
			pageEnCours = pageEnCours >= pageMax ? pageEnCours : pageEnCours+1;
			Inavigation.setText("");
		} else if (direction == -1) {
			pageEnCours = pageEnCours <= 0 ? pageEnCours : pageEnCours-1;
			Inavigation.setText("");
		}
		// Première page initialisée
		if(direction == 0) {
			debut = page > 0 ? numerotationDebut : 0;
			if(rpage > taille) {
				fin = taille;
			} else {
				fin = page > 0 ? numerotationFin : rpage;
			}
			// Recherche de résultats personnalisés			
			if(page != pageEnCours && !pageWrong && page > 0) {
				int page_actuelle = pageEnCours, saut_page = 0;				
				saut_page = pageTotal * (page - page_actuelle);
				debut = numerotationDebut + saut_page;
				fin = pageTotal + debut > taille ? taille : pageTotal + debut;
				pageEnCours = page;
			}
		// Page précédente
		} else if(direction == -1) {
			if(debut >= rpage) {
				debut -= rpage;
				fin = debut + rpage;
			}
		// Page suivante
		} else if(direction == 1) {
			if(taille > fin) {
				if(taille > rpage + debut && taille < rpage + fin) {			
					debut += rpage;
					fin = taille;
				} else {
					debut += rpage;
					fin += rpage;
				}
			} else if(taille < rpage) {
				debut = 0;
				fin = taille;
			}
		}
		LinputNavigation.setText("page demandée         || page actuelle : " + pageEnCours);
		if(type.equalsIgnoreCase("rechercher")) {
			// Critères du type 'rechercher'
			PAffichage.add(Irecherche);
			PAffichage.add(LinfoResultat);
			if(debut == 0 
			&& resultatsRechercheElement.size() < fin 
			|| resultatsRechercheElement.size() <= pageTotal) {
				PAffichage.remove(Bprecedent);
				PAffichage.remove(Bsuivant);
			}
			if(debut > 0 && resultatsRechercheElement.size() <= fin) {
				PAffichage.remove(Bsuivant);
			}
		}
		if(pageTotal > (fin-debut) || taille == (fin-debut) || fin == taille) {
			PAffichage.remove(Bsuivant);
		}
		if(debut == 0) {
			PAffichage.remove(Bprecedent);
		}
		// Affichage du input de pages
		if(((float) taille / (float) pageTotal) > 1) {
			PAffichage.add(LinputNavigation);
			PAffichage.add(LinputNavigation2);
			PAffichage.add(Inavigation);
		}
		numerotationDebut = debut;
		numerotationFin = fin;

		/*if(classe.equalsIgnoreCase("album") && type.equalsIgnoreCase("rechercher")) {
			lister(debut, fin, 0, null, resultatsRechercheElement);
		} else if(classe.equalsIgnoreCase("album")) {
			listeRadio = lister(debut, fin, numeroType, bouton, new ArrayList<Element>());
		} else if(classe == "album" && type.equalsIgnoreCase("acheter")) { 
			listeRadio = lister(debut, fin, numeroType, bouton, new ArrayList<Element>());
		}
		if(classe.equalsIgnoreCase("artiste") && type.equalsIgnoreCase("rechercher")) {
			lister(debut, fin, 0, null, resultatsRechercheElement);
		} else if(classe.equalsIgnoreCase("artiste")) {
			listeRadio = lister(debut, fin, numeroType, bouton, new ArrayList<Element>());
		}
		if(classe.equalsIgnoreCase("label") && type.equalsIgnoreCase("rechercher")) {
			lister(debut, fin, 0, null, resultatsRechercheElement);
		} else if(classe.equalsIgnoreCase("label")) {
			listeRadio = lister(debut, fin, numeroType, bouton, new ArrayList<Element>());
		}
		if(classe.equalsIgnoreCase("auditeur") && type.equalsIgnoreCase("rechercher")) {
			lister(debut, fin, 0, null, resultatsRechercheElement);
		} else if(classe.equalsIgnoreCase("auditeur")) {
			listeRadio = lister(debut, fin, numeroType, bouton, new ArrayList<Element>());
		}
		if(classe.equalsIgnoreCase("morceau") && type.equalsIgnoreCase("rechercher")) {
			lister(debut, fin, 0, null, resultatsRechercheElement );
		} else if(classe.equalsIgnoreCase("morceau") && type.equalsIgnoreCase("acheter")) {
			listeRadio =  lister(debut, fin, numeroType, bouton, resultatsRechercheElement);
		} else if(classe.equalsIgnoreCase("morceau") && type.equalsIgnoreCase("lire")) {
			listeRadio = lister(debut, fin, numeroType, bouton, resultatsRechercheElement);
		} else if(classe.equalsIgnoreCase("morceau")) {
			listeRadio = lister(debut, fin, numeroType, bouton, new ArrayList<Element>());
		}

		if(classe.equalsIgnoreCase("region") && type.equalsIgnoreCase("rechercher")) {
			lister(debut, fin, 0, null, resultatsRechercheElement);
		} else if(classe.equalsIgnoreCase("region")) {
			listeRadio = lister(debut, fin, numeroType, bouton, new ArrayList<Element>());
		}

		if(classe.equalsIgnoreCase("groupe") && type.equalsIgnoreCase("rechercher")) {
			lister(debut, fin, 0, null, resultatsRechercheElement);
		} else if(classe.equalsIgnoreCase("groupe")) {
			listeRadio = lister(debut, fin, numeroType, bouton, new ArrayList<Element>());
		}*/
	}

	/**
	 * Méthode d'affichage
	 * @param debut
	 * @param fin
	 * @param type
	 * @param Btype
	 * @param liste
	 * @return {@link ButtonGroup}
	 */
	public ButtonGroup lister_Element(int debut, int fin, int type, JButton Btype, ArrayList<Element> liste) {
		ButtonGroup listeRadio = new ButtonGroup();
		String listeVide = "Pour ajouter un livre à la bibliothèque : Livre -> Ajouter un livre.";
		int coords_x = 30, coords_y = 50, coords_w = 180, coords_h = 20;
		if(Btype != null) {
			PAffichage.add(Btype);
		}

		// Initialisation des composants d'interface
		if(this.lesLivres.size() > 0) {
			String pageTotal = liste.size() == 0 ? "sur " + String.valueOf(this.lesLivres.size()) : "sur " + String.valueOf(liste.size());

			JLabel Lelement = new JLabel("nom".toUpperCase());
			JLabel Lelement2 = new JLabel("prénom".toUpperCase());
			JLabel Lelement3 = new JLabel("pseudonyme".toUpperCase());
			JLabel Lelement4 = new JLabel("illustration".toUpperCase());
			JLabel LpageDebut = new JLabel("Résultats : de "  + String.valueOf(debut));
			JLabel LpageFin = new JLabel(" à " + String.valueOf(fin));			
			JLabel LpageTotal = new JLabel(pageTotal);

			Lelement	.setBounds(coords_x, 		(coords_y-20), coords_w, 		coords_h);
			Lelement2	.setBounds((coords_x+100), 	(coords_y-20), coords_w, 		coords_h);
			Lelement3	.setBounds((coords_x+220), 	(coords_y-20), coords_w, 		coords_h);
			Lelement4	.setBounds((coords_x+400), 	(coords_y-20), (coords_w+150), 	coords_h);
			LpageDebut	.setBounds(420, 			350, 			100, 			20);
			LpageFin	.setBounds(520, 			350, 			40, 			20);
			LpageTotal	.setBounds(550, 			350, 			70, 			20);

			PAffichage.add(LpageDebut);
			PAffichage.add(LpageFin);
			PAffichage.add(LpageTotal);
			PAffichage.add(Lelement);
			PAffichage.add(Lelement2);
			PAffichage.add(Lelement3);
			PAffichage.add(Lelement4);
		} else {
			JLabel LlisteVide = new JLabel(listeVide);
			LlisteVide.setBounds(coords_x, 25, 400, 20);
			PAffichage.add(LlisteVide);
		}

		// Affichage des résultats
		for(int i=debut; i<fin; i++) {
			if((i - debut) != 0) { coords_y += 140; }
			if(type != 0) {
				JRadioButton Bradio = new JRadioButton(lesElements.get(i).getNom());				
				Bradio.setBounds(650, coords_y+40, 20, 20);
				Bradio.setActionCommand(lesElements.get(i).getNom());
				listeRadio.add(Bradio);
				PAffichage.add(Bradio);
			}
			JLabel Lelement = new JLabel("");
			JLabel Lelement2 = new JLabel("");
			JLabel Lelement3 = new JLabel("");
			JLabel Lelement4 = new JLabel("");

			// Affichage spécifique
			if(liste.size() > 0) {			
				for(int y=0; y<lesElements.size(); y++) {
					if(liste.get(i).getNumero() == lesElements.get(y).getNumero()) {
						Lelement.setText(lesElements.get(y).getNom().substring(0, 1).toUpperCase() + lesElements.get(y).getNom().substring(1).toLowerCase());
						Lelement2.setText(lesElements.get(y).getPrenom().substring(0, 1).toUpperCase() + lesElements.get(y).getPrenom().substring(1).toLowerCase());
						if(lesElements.get(y).getPseudo().length() != 0) { 
							Lelement3.setText(lesElements.get(y).getPseudo().substring(0, 1).toUpperCase() + lesElements.get(y).getPseudo().substring(1).toLowerCase());
						}
						Lelement4.setText(String.valueOf(lesElements.get(i).getNombreAlbums()) + " albums(s) : ".toUpperCase());

						// Ajout albums
						/*ArrayList<String> albums = rechercher(lesArtistes.get(y).getLesAlbums());
						int m = 0, x = 0;
						while(x < albums.size() && x < 5) {							
							JLabel Louvrage = new JLabel(albums.get(x));					
							m = (x+1) * 17 + 10;
							Louvrage.setBounds(coords_x, coords_y+m, coords_w, coords_h);
							PAffichage.add(Louvrage);
							if(albums.size() > 5 && x == 4) {
								JLabel Lsuite = new JLabel("...");
								Lsuite.setBounds(coords_x, (coords_y+m+17), coords_w, coords_h);
								PAffichage.add(Lsuite);
							}
							x++;
						}*/

						JLabel Lelement5 = new JLabel(new ImageIcon(lesElements.get(y).getLien()), JLabel.CENTER);

						Lelement	.setBounds(coords_x-12, 	coords_y, 		coords_w+10, 	coords_h);
						Lelement2	.setBounds(coords_x+100, 	coords_y, 		coords_w, 		coords_h);
						Lelement3	.setBounds(coords_x+200, 	coords_y, 		coords_w+150, 	coords_h);
						Lelement4	.setBounds(coords_x, 		coords_y+15, 	coords_w, 		coords_h);
						Lelement5	.setBounds(coords_x+350, 	coords_y+25, 	coords_w+40, 	95);

						PAffichage.add(Lelement);
						PAffichage.add(Lelement2);
						PAffichage.add(Lelement3);
						PAffichage.add(Lelement5);
					}
				}
			}
			// Affichage basique
			else if(liste.size() == 0) {				
				JLabel Lelement5 = new JLabel(new ImageIcon(this.lesElements.get(i).getLien()), JLabel.CENTER);

				Lelement.setText(lesElements.get(i).getNom().substring(0, 1).toUpperCase()+ lesElements.get(i).getNom().substring(1).toLowerCase());
				Lelement2.setText(lesElements.get(i).getPrenom().substring(0, 1).toUpperCase() + lesElements.get(i).getPrenom().substring(1).toLowerCase());
				if (lesElements.get(i).getPseudo().length() != 0) {
					Lelement3.setText(lesElements.get(i).getPseudo().substring(0, 1).toUpperCase() + lesElements.get(i).getPseudo().substring(1).toLowerCase());
				}
				Lelement4.setText(String.valueOf(lesElements.get(i).getNombreAlbums()) + " albums(s) : ".toUpperCase());

				// Ajout albums
				/*ArrayList<String> albums = rechercher(lesArtistes.get(i).getAlbums());
				int m = 0, x = 0;
				while(x<albums.size() && x < 5) {					
					JLabel Louvrage = new JLabel(albums.get(x));					
					m = (x+1) * 17 + 10;
					Louvrage.setBounds(coords_x, (coords_y+m), coords_w, coords_h);
					PAffichage.add(Louvrage);
					if(albums.size() > 5 && x == 4) {
						JLabel Lsuite = new JLabel("...");
						Lsuite.setBounds(coords_x, (coords_y+m+17), coords_w, coords_h);
						PAffichage.add(Lsuite);
					}
					x++;					
				}*/

				Lelement	.setBounds(coords_x, 		coords_y, 		coords_w, coords_h);
				Lelement2	.setBounds((coords_x+100), 	coords_y, 		coords_w, coords_h);
				Lelement3	.setBounds((coords_x+220), 	coords_y, 		coords_w, coords_h);
				Lelement4	.setBounds((coords_x), 		coords_y+15, 	coords_w, coords_h);
				Lelement5	.setBounds((coords_x+350), 	coords_y+25, 	coords_w+40, 95);
				PAffichage.add(Lelement);
				PAffichage.add(Lelement2);
				PAffichage.add(Lelement3);
				PAffichage.add(Lelement4);
				PAffichage.add(Lelement5);
			}
		}
		return listeRadio;
	}

	/**
	 *	Rafraîchir les panels et les boutons
	 *	Gérer la navigation
	 *	@param table
	 */
	public void refresh(String table) {
		Plivre		.setVisible(false);
		Pauteur		.setVisible(false);
		Pedition	.setVisible(false);
		Plecteur	.setVisible(false);
		Pexemplaire	.setVisible(false);
		Ptraduction	.setVisible(false);
		Pformulaire	.setVisible(false);
		PAffichage	.setVisible(false);

		if(table.equalsIgnoreCase("livre")) {
			inType = "";
			Plivre.setVisible(true);
			Plivre.add(Lnavigation);
			Plivre.add(Lgestion);
		} else if(table.equalsIgnoreCase("auteur")) {
			inType = "";
			Pauteur.setVisible(true);
			Pauteur.add(Lnavigation);
			Pauteur.add(Lgestion);
		} else if(table.equalsIgnoreCase("edition")) {
			inType = "";
			Pedition.setVisible(true);
			Pedition.add(Lnavigation);
			Pedition.add(Lgestion);
		} else if(table.equalsIgnoreCase("lecteur")) {
			inType = "";
			Plecteur.setVisible(true);
			Plecteur.add(Lnavigation);
			Plecteur.add(Lgestion);
		} else if(table.equalsIgnoreCase("exemplaire")) {
			inType = "";
			Pexemplaire.setVisible(true);
			Pexemplaire.add(Lnavigation);
			Pexemplaire.add(Lgestion);
		}  else if(table.equalsIgnoreCase("traduction")) {
			inType = "";
			Ptraduction.setVisible(true);
			Ptraduction.add(Lnavigation);
			Ptraduction.add(Lgestion);
		} else if(table.equalsIgnoreCase("formulaire")) {
			Pformulaire.removeAll();
			Pformulaire.setVisible(true);
			Pformulaire.add(Lnavigation);
		} else if(table.equalsIgnoreCase("affichage")) {
			PAffichage.setVisible(true);
		} else if(table.equalsIgnoreCase("")) {
			Blivre		.setVisible(false);
			Bauteur		.setVisible(false);
			Bedition	.setVisible(false);
			Blecteur	.setVisible(false);
			Bexemplaire	.setVisible(false);
			Btraduction	.setVisible(false);
		}

		if(table.equalsIgnoreCase("formulaire") || table.equalsIgnoreCase("affichage") || table.equalsIgnoreCase("")) {} 
		else {
			// classe : livre auteur édition lecteur
			Lnavigation.setText(table.substring(0, 1).toUpperCase() 
								+ table.substring(1).toLowerCase() 
								+ " > " );
			Lgestion.setText(" -- Gestion " 
							+ table.substring(0, 1).toUpperCase() 
							+ table.substring(1).toLowerCase() 
							+ " -- ");
			inTable = table;
		}
		if(inType.equalsIgnoreCase("rechercher") || inType.equalsIgnoreCase("ajouter")) {
			//rechercher - ajouter
			Lnavigation.setText(inTable.substring(0, 1).toUpperCase()		
			+ inTable.substring(1).toLowerCase() 
			+ " > "
			+ inType.substring(0, 1).toUpperCase() 
			+ inType.substring(1).toLowerCase() 
			+ " > ");			
		}
	}

	/**
	 * Méthode de navigation
	 * Utilisation de variables temporaires
	 * Initialisations à la première page - les variables des pages précédentes ou suivantes ne sont que rarement initialisées 
	 * @param direction : direction de la page
	 * @param table : table employée ( livre, auteur, edition , lecteur , exemplaire, traduction..)
	 * @param type : type de la liste parcourue (modifier, supprimer, lister)
	 * @param liste_numeros : liste d'éléments sélectionnés
	 */
	public void navigation(int direction, String table, String type, ArrayList<Integer> liste_numeros, int page, Object parent) {
		refresh("affichage");
		PAffichage.removeAll();
		PAffichage.add(Lnavigation);
		PAffichage.add(Bretour);
		PAffichage.add(Bprecedent);
		PAffichage.add(Bsuivant);
		boolean resSpec = false; 		// affichage par la recherche 
		boolean resSpecWrong = false; 	// affichage par la recherche null ou vide
		boolean pageWrong = false; 		// défilement des pages personnalisé

		if(!table.equalsIgnoreCase(inTableActuelle) && !table.equalsIgnoreCase("")
		|| !type.equalsIgnoreCase(inType) && !type.equalsIgnoreCase("")) {
			Inavigation.setText("");
		}

		float page_max = 0;
		int debut = 0, fin = 0, taille = 0, rpage = 0, num_type = 0;
		debut = numerotationDebut;
		fin = numerotationFin;
		JButton bouton_liste = null;

		// Initialisation type
		if(type.equalsIgnoreCase("")) type = inType;
		else inType = type;

		// Initialisation table
		if(table.equalsIgnoreCase("")) table = inTable;
		else {
			inTable = table; //obsolète
			inTableActuelle = table;
		}

		// Initialisation recherche
		if(liste_numeros.size() == 0) liste_numeros = resultatsRecherche;
		else resultatsRecherche = liste_numeros;
		// Initialisation de la recherche
		if(!type.equalsIgnoreCase("rechercher") 
		&& !type.equalsIgnoreCase("acheter") 
		&& !type.equalsIgnoreCase("lire")) {
			resultatsRecherche = new ArrayList<Integer>();
		}

		// Libellé navigation
		String navigation = table.substring(0, 1).toUpperCase() 
							+ table.substring(1).toLowerCase() 
							+ " > " 
							+ type.substring(0, 1).toUpperCase() 
							+ type.substring(1).toLowerCase() 
							+ " > ";
		Lnavigation.setText(navigation);

		if(inType.equalsIgnoreCase("rechercher") 
		|| inType.equalsIgnoreCase("acheter") && !table.equalsIgnoreCase("livre")
		|| inType.equalsIgnoreCase("lire") && !table.equalsIgnoreCase("livre")) {
			resSpec = true;
			try {
				if(resultatsRecherche.size() == 0) {
					resSpecWrong = true;
				}
			} catch(NullPointerException e) {
				resSpecWrong = true;
			}
		}

		// Initialisation résultats par page & nombre de résultats
		if(table == "livre") {
			taille = resSpecWrong ? resultatsRecherche.size() : lL.getlesLivres().size();			
			if(!resSpecWrong) {
				taille = resSpec ? liste_numeros.size() : taille;
			}
			pageTotal = 3;	
		} else if(table == "auteur") {
			taille = resSpecWrong ? resultatsRecherche.size() : lA.getlesAuteurs().size();			
			if(!resSpecWrong) {
				taille = resSpec ? liste_numeros.size() : taille;
			}
			pageTotal = 2;
		}  else if(table == "edition") {
			taille = resSpecWrong ? resultatsRecherche.size() : lE.getlesEditions().size();			
			if(!resSpecWrong) {
				taille = resSpec ? liste_numeros.size() : taille;
			}
			pageTotal = 2;
		} else if(table == "lecteur") {
			taille = resSpecWrong ? resultatsRecherche.size() : lLect.getlesLecteurs().size();			
			if(!resSpecWrong) {
				taille = resSpec ? liste_numeros.size() : taille;
			}
			pageTotal = 2;
		} else if(table == "exemplaire") {
			taille = resSpecWrong ? resultatsRecherche.size() : lEx.getLesExemplaires().size();			
			if(!resSpecWrong) {
				taille = resSpec ? liste_numeros.size() : taille;
			}
			pageTotal = 2;
		} else if(table == "traduction") {
			taille = resSpecWrong ? resultatsRecherche.size() : lTr.getLesTraductions().size();			
			if(!resSpecWrong) taille = resSpec ? liste_numeros.size() : taille;
			pageTotal = 2;
		}
		rpage = pageTotal;

		page_max = (float) taille / (float) pageTotal;
		page_max = (float) Math.ceil(page_max);
		if(page_max < page) {
			pageWrong = true;
		}

		// Initialiser boutons (modifier, supprimer) et le type d'affichage
		if(type.equalsIgnoreCase("modifier")) {
			num_type = 1;
			bouton_liste = Bmodifier_2;
		} else if(type.equalsIgnoreCase("supprimer")) {
			num_type = 2;
			bouton_liste = Bsupprimer_2;
		} else if(type.equalsIgnoreCase("lister")) {
			num_type = 0;
		} else if(type == "rechercher" && !resSpecWrong) {
			num_type = 3;
			resultatsRecherche = liste_numeros;
			taille = resultatsRecherche.size();
		} else if(type.equalsIgnoreCase("acheter") && table.equalsIgnoreCase("livre")) {
			num_type = 4;
			bouton_liste = this.Bacheter_2;
		} else if(type.equalsIgnoreCase("acheter") && table.equalsIgnoreCase("exemplaire") && !resSpecWrong) {
			num_type = 5;
			bouton_liste = this.Bacheter_3;
			resultatsRecherche = liste_numeros;
			taille = resultatsRecherche.size();
		} else if(type.equalsIgnoreCase("lire") && table.equalsIgnoreCase("livre")) {
			num_type = 6;
			bouton_liste = this.Blire_2;
		} else if(type.equalsIgnoreCase("lire") && table.equalsIgnoreCase("exemplaire") && !resSpecWrong) {
			num_type = 7;
			bouton_liste = this.Blire_3;
			resultatsRecherche = liste_numeros;
			taille = resultatsRecherche.size();
		}

		if(direction == 0 && page == 0) {
			pageEnCours = 1;
		} else if(direction == 1) {
			pageEnCours = (pageEnCours >= page_max) ? pageEnCours : pageEnCours+1;
			Inavigation.setText("");
		} else if (direction == -1) {
			pageEnCours = (pageEnCours <= 0) ? pageEnCours : pageEnCours-1;
			Inavigation.setText("");
		}
		// Première page initialisée
		if(direction == 0) {
			debut = page > 0 ? numerotationDebut : 0;
			if(rpage > taille) { fin = taille; }
			else {
				fin = (page > 0) ? numerotationFin : rpage;
			}
			// Recherche de résultats personnalisés			
			if(page != pageEnCours && !pageWrong && page > 0) {
				int page_actuelle = pageEnCours, saut_page = 0;				
				saut_page = pageTotal * (page - page_actuelle);
				debut = numerotationDebut + saut_page;
				fin = pageTotal + debut > taille ? taille : pageTotal + debut;
				pageEnCours = page;
			}
		// Page précédente
		} else if(direction == -1) {
			if(debut >= rpage) {
				debut -= rpage;
				fin = debut + rpage;
			}
		// Page suivante
		} else if(direction == 1) {
			if(taille > fin) {
				if(taille > rpage + debut && taille < rpage + fin) {			
					debut += rpage;
					fin = taille;
				} else {
					debut += rpage;
					fin += rpage;
				}
			} else if(taille < rpage) {
				debut = 0;
				fin = taille;
			}
		}

		LinputNavigation.setText("page demandée         || page actuelle : " + pageEnCours);

		if(type.equalsIgnoreCase("rechercher")) {
			// Critères du type 'rechercher'
			PAffichage.add(Irecherche);
			PAffichage.add(LinfoResultat);
			if(debut == 0 
			&& resultatsRecherche.size() < fin 
			|| resultatsRecherche.size() <= pageTotal) {
				PAffichage.remove(Bprecedent);
				PAffichage.remove(Bsuivant);
			}
			if(debut > 0 && resultatsRecherche.size() <= fin) {
				PAffichage.remove(Bsuivant);
			}
		}

		if(pageTotal > (fin-debut) || taille == (fin-debut) || fin == taille) { PAffichage.remove(Bsuivant); }

		if(debut == 0) { PAffichage.remove(Bprecedent); }

		// Affichage du input de pages
		if(((float) taille / (float) pageTotal) > 1) {			
			PAffichage.add(LinputNavigation);
			PAffichage.add(Inavigation);
		}

		numerotationDebut = debut;
		numerotationFin = fin;

		if(table.equalsIgnoreCase("livre") && type.equalsIgnoreCase("rechercher")) {
			lL.lister(PAffichage, lA, debut, fin, 0, null, resultatsRecherche);
		} else if(table.equalsIgnoreCase("livre")) {
			listeRadio = lL.lister(PAffichage, lA, debut, fin, num_type, bouton_liste, new ArrayList<Integer>());
		}

		if(table.equalsIgnoreCase("auteur") && type.equalsIgnoreCase("rechercher")) {
			lA.lister(PAffichage, lL, debut, fin, 0, null, resultatsRecherche);
		} else if(table.equalsIgnoreCase("auteur")) {
			listeRadio = lA.lister(PAffichage, lL, debut, fin, num_type, bouton_liste, new ArrayList<Integer>());
		}

		if(table.equalsIgnoreCase("edition") && type.equalsIgnoreCase("rechercher")) {
			lE.lister(PAffichage, debut, fin, 0, null, resultatsRecherche);
		} else if(table.equalsIgnoreCase("edition")) {
			listeRadio = lE.lister(PAffichage, debut, fin, num_type, bouton_liste, new ArrayList<Integer>());
		}

		if(table.equalsIgnoreCase("lecteur") && type.equalsIgnoreCase("rechercher")) {
			lLect.lister(PAffichage, debut, fin, 0, null, resultatsRecherche);
		} else if(table.equalsIgnoreCase("lecteur")) {
			listeRadio = lLect.lister(PAffichage, debut, fin, num_type, bouton_liste, new ArrayList<Integer>());
		}

		if(table.equalsIgnoreCase("exemplaire") && type.equalsIgnoreCase("rechercher")) {
			lEx.lister(PAffichage, lL, lE, lTr, debut, fin, 0, null, resultatsRecherche);
		} else if(table.equalsIgnoreCase("exemplaire") && type.equalsIgnoreCase("acheter")) { 
			listeRadio =  lEx.lister(PAffichage, lL, lE, lTr, debut, fin, num_type, bouton_liste, resultatsRecherche);
		} else if(table.equalsIgnoreCase("exemplaire") && type.equalsIgnoreCase("lire")) { 
			listeRadio = lEx.lister(PAffichage, lL, lE, lTr, debut, fin, num_type, bouton_liste, resultatsRecherche);
		} else if(table.equalsIgnoreCase("exemplaire")) {
			listeRadio = lEx.lister(PAffichage, lL, lE, lTr, debut, fin, num_type, bouton_liste, new ArrayList<Integer>());
		}

		if(table.equalsIgnoreCase("traduction") && type.equalsIgnoreCase("rechercher")) {
			lTr.lister(PAffichage, debut, fin, 0, null, resultatsRecherche);
		} else if(table.equalsIgnoreCase("traduction")) {
			listeRadio = lTr.lister(PAffichage, debut, fin, num_type, bouton_liste, new ArrayList<Integer>());
		}
	}
	
	public ArrayList<Integer> navigationObjet(int direction, String table, String type, ArrayList<Object> listeObjet, int page, Object parent) {
		System.out.println("test0");
		ArrayList<Integer> pages = new ArrayList<Integer>();
		boolean resSpec = false; 		// affichage par la recherche
		boolean resSpecWrong = false; // affichage par la recherche null ou vide
		boolean pageWrong = false; 	// défilement des pages personnalisé
		float pageMax = 0;
		int debut = 0, fin = 0, taille = 0, rpage = 0, num_type = 0;
		JButton bouton = null;

		debut = numerotationDebut;
		fin = numerotationFin;

		refresh("affichage");
		PAffichage.removeAll();
		PAffichage.add(Lnavigation);
		PAffichage.add(Bretour);
		PAffichage.add(BprecedentObjet);
		PAffichage.add(BsuivantObjet);

		if(!table.equalsIgnoreCase(inTableActuelle) && !table.equalsIgnoreCase("")
		|| !type.equalsIgnoreCase(inType) && !type.equalsIgnoreCase("")) {
			Inavigation.setText("");
		}

		// Initialisation type
		if(type.equalsIgnoreCase("")) { type = inType; } 
		else { inType = type; }

		// Initialisation de la classe
		if(table.equalsIgnoreCase("")) { table = inTable; } 
		else {
			inTable = table; //obsolète
			inTableActuelle = table;
		}

		// Initialisation recherche
		if(listeObjet.size() == 0) { listeObjet = resultatsRechercheObjet; } 
		else  { resultatsRechercheObjet = listeObjet; }

		// Initialisation de la recherche
		if(!type.equalsIgnoreCase("rechercher")) {
			resultatsRechercheObjet = new ArrayList<Object>();
		}

		// Libellé navigation
		String navigation = table.substring(0, 1).toUpperCase() 
							+ table.substring(1).toLowerCase() 
							+ " > " 
							+ type.substring(0, 1).toUpperCase() 
							+ type.substring(1).toLowerCase() 
							+ " > ";
		Lnavigation.setText(navigation);

		if(inType.equalsIgnoreCase("rechercher")) {
			resSpec = true;
			try {
				if(resultatsRechercheObjet.size() == 0) { resSpecWrong = true; }
			} catch(NullPointerException e) {
				resSpecWrong = true;
			}
		}

		// Initialisation résultats par page & nombre de résultats
		if(table.equalsIgnoreCase("livre")) {
			if(parent != null) {
				taille = resSpecWrong ? resultatsRechercheObjet.size() : lesLivres.size(); 
			} else {
				taille = resSpecWrong ? resultatsRechercheObjet.size() : 0; 
			}
			if(!resSpecWrong) {
				taille = resSpec ? listeObjet.size() : taille;
			}
			pageTotal = 3;	
		} else if(table.equalsIgnoreCase("auteur")) {
			taille = resSpecWrong ? resultatsRechercheObjet.size() : lesAuteurs.size();			
			if(!resSpecWrong) {
				taille = resSpec ? listeObjet.size() : taille;
			}
			pageTotal = 2;
		}  else if(table.equalsIgnoreCase("lecteur")) {
			if(parent != null) { 
				taille = resSpecWrong ? resultatsRechercheObjet.size() : lesLecteurs.size(); 
			} else { 
				taille = resSpecWrong ? resultatsRechercheObjet.size() : 0; 
			}
			if(!resSpecWrong) {
				taille = resSpec ? listeObjet.size() : taille;
			}
			pageTotal = 2;
		} else if(table.equalsIgnoreCase("edition")) {
			if(parent != null) { 
				taille = resSpecWrong ? resultatsRechercheObjet.size() : lesEditions.size(); 
			} else { 
				taille = resSpecWrong ? resultatsRechercheObjet.size() : 0; 
			}
			if(!resSpecWrong) {
				taille = resSpec ? listeObjet.size() : taille;
			}
			pageTotal = 2;
		}
		rpage = pageTotal;

		pageMax = (float) taille / (float) pageTotal;
		pageMax = (float) Math.ceil(pageMax);
		if(pageMax < page) { pageWrong = true; }

		// Initialiser les boutons ( modifier, supprimer, rechercher ) et le type d'affichage
		if(type.equalsIgnoreCase("modifier")) {
			num_type = 1;
			bouton = Bmodifier_2;
		} else if(type.equalsIgnoreCase("supprimer")) {
			num_type = 2;
			bouton = Bsupprimer_2;
		} else if(type.equalsIgnoreCase("lister")) {
			num_type = 0;
		} else if(type.equalsIgnoreCase("rechercher") && !resSpecWrong) {
			num_type = 3;
			resultatsRechercheObjet = listeObjet;
			taille = resultatsRechercheObjet.size();		
		}

		if(direction == 0 && page == 0) {
			pageEnCours = 1;
		} else if(direction == 1) {
			pageEnCours = pageEnCours >= pageMax ? pageEnCours : pageEnCours+1;
			Inavigation.setText("");
		} else if (direction == -1) {
			pageEnCours = pageEnCours <= 0 ? pageEnCours : pageEnCours-1;
			Inavigation.setText("");
		}
		// Première page initialisée
		if(direction == 0) {
			debut = page > 0 ? numerotationDebut : 0;
			if(rpage > taille) {
				fin = taille;
			} else {
				fin = page > 0 ? numerotationFin : rpage;
			}
			// Recherche de résultats personnalisée			
			if(page != pageEnCours && !pageWrong && page > 0) {
				int page_actuelle = pageEnCours, saut_page = 0;				
				saut_page = pageTotal * (page - page_actuelle);
				debut = numerotationDebut + saut_page;
				fin = pageTotal + debut > taille ? taille : pageTotal + debut;
				pageEnCours = page;
			}
		// Page précédente
		} else if(direction == -1) {
			if(debut >= rpage) {
				debut -= rpage;
				fin = debut + rpage;
			}
		// Page suivante
		} else if(direction == 1) {
			if(taille > fin) {
				if(taille > rpage + debut && taille < rpage + fin) {			
					debut += rpage;
					fin = taille;
				} else {
					debut += rpage;
					fin += rpage;
				}
			} else if(taille < rpage) {
				debut = 0;
				fin = taille;
			}
		}

		LinputNavigation.setText("entrer la page voulue : ");
		LinputNavigation2.setText("page " + pageEnCours + " sur page " + (int) pageTotal);

		// Critères du type 'rechercher'
		if(type.equalsIgnoreCase("rechercher")) {
			PAffichage.add(Irecherche);
			PAffichage.add(LinfoResultat);
			if(debut == 0 
			&& resultatsRechercheObjet.size() < fin 
			|| resultatsRechercheObjet.size() <= pageTotal) {
				PAffichage.remove(BprecedentObjet);
				PAffichage.remove(BsuivantObjet);
			}
			if(debut > 0 && resultatsRechercheObjet.size() <= fin) {
				PAffichage.remove(Bsuivant);
			}
		}

		if(pageTotal > (fin-debut) 
		|| taille == (fin-debut) 
		|| fin == taille) {
			PAffichage.remove(BsuivantObjet);
		}

		if(debut == 0) { PAffichage.remove(BprecedentObjet); }

		// Affichage du champ de numéro de page
		if(((float) taille / (float) pageTotal) > 1) {			
			PAffichage.add(LinputNavigation);
			PAffichage.add(LinputNavigation2);
			PAffichage.add(Inavigation);
		}

		numerotationDebut = debut;
		numerotationFin = fin;

		if(table.equalsIgnoreCase("livre") && type.equalsIgnoreCase("rechercher")) {
			lister(PAffichage, debut, fin, resultatsRechercheObjet);
		} else if(table.equalsIgnoreCase("livre")) {
			listeRadio = listerLivre(PAffichage, debut, fin, num_type, bouton, new ArrayList<Livre>());
		}

		if(table.equalsIgnoreCase("auteur") && type.equalsIgnoreCase("rechercher")) {
			lister(PAffichage, debut, fin, resultatsRechercheObjet);
		} else if(table.equalsIgnoreCase("auteur")) {
			listeRadio = listerAuteur(PAffichage, debut, fin, num_type, bouton, new ArrayList<Auteur>());
		}

		if(table.equalsIgnoreCase("lecteur") && type.equalsIgnoreCase("rechercher")) {			
			lister(PAffichage, debut, fin, resultatsRechercheObjet);
		} else if(table.equalsIgnoreCase("lecteur")) {
			listeRadio = listerLecteur(PAffichage,  debut, fin, num_type, bouton, new ArrayList<Lecteur>());
		}

		if(table.equalsIgnoreCase("edition") && type.equalsIgnoreCase("rechercher")) {
			lister(PAffichage, debut, fin, resultatsRechercheObjet);
		} else if(table.equalsIgnoreCase("edition")) {
			listeRadio = listerEdition(PAffichage, debut, fin, num_type, bouton, new ArrayList<Edition>());
		}

		return pages;
	}

	public void lister(JPanel PAffichage, int debut, int fin, ArrayList<Object> liste) {
		int coords_x = 30, coords_y = 50, coords_w = 180, coords_h = 20;

		// Affichage menu contextuel
		if(liste.size() > 0) {
			JLabel Lelement = new JLabel("nom".toUpperCase());
			JLabel Lelement2 = new JLabel("Appartient à " + ((Element) liste.get(0)).getParent());
			JLabel Lelement3 = new JLabel("Nombre de " + ((Element) liste.get(0)).getEnfant());
			JLabel premiere_page = new JLabel("Résultats : de "  + String.valueOf(debut));
			JLabel derniere_page = new JLabel(" à " + String.valueOf(fin));
			JLabel pageTotal = new JLabel("");
			String numerotation = String.valueOf(liste.size());
			pageTotal.setText(numerotation);
			premiere_page	.setBounds(420, 			350, 			100, 	20);
			derniere_page	.setBounds(520, 			350, 			40, 	20);
			pageTotal		.setBounds(550, 			350, 			70, 	20);
			Lelement		.setBounds(coords_x, 		(coords_y-20), 	coords_w, coords_h);
			Lelement2		.setBounds(coords_x+100,	(coords_y-20), 	coords_w, coords_h);
			Lelement3		.setBounds(coords_x+200,	(coords_y-20), 	coords_w, coords_h);			
			PAffichage.add(premiere_page);
			PAffichage.add(derniere_page);
			PAffichage.add(pageTotal);
			PAffichage.add(Lelement);
			PAffichage.add(Lelement2);
			PAffichage.add(Lelement3);
		}

		// Affichage des résultats
		for(int i=debut; i<fin; i++) {
			if((i - debut) != 0) { coords_y += 140; }

			JLabel element = new JLabel("");
			JLabel element2 = new JLabel(((Element) liste.get(0)).getParent());
			JLabel element3 = new JLabel(((Element) liste.get(0)).getEnfant());

			for(int y=0; y<liste.size(); y++) {
				if(((Element) liste.get(i)).getNumero() == ((Element) liste.get(y)).getNumero()) {
					element.setText(((Element) liste.get(y)).getNom());
					//element2.setText(((Element) liste.get(y)).get()); obtenir le nom du prante parent.getNom()
					//element3.setText(((Element) liste.get(y)).get()); obtenir le nombre d'enfants : getEnfants().size()
					element.setBounds((coords_x-12), coords_y, (coords_w+10), coords_h);
					element2.setBounds((coords_x-12)+100, coords_y, (coords_w+10), coords_h);
					element3.setBounds((coords_x-12)+200, coords_y, (coords_w+10), coords_h);
					PAffichage.add(element);
					PAffichage.add(element2);
					PAffichage.add(element3);
				}
			}
		}
	}
	
	/**
	 * Affichage de la bibliothèque, images, descriptions des oeuvres par page
	 * @param PAffichage : affichage
	 * @param debut : ligne de début d'affichage de la bdd
	 * @param fin : ligne de fin d'affichage de la bdd
	 * @param type : type d'affichage
	 * @param ArrayList<Integer> : liste de modification
	 */
	public ButtonGroup listerLivre(JPanel PAffichage, int debut, int fin, int type, JButton Btype, ArrayList<Livre> liste) {		
		ButtonGroup listeRadio = new ButtonGroup();
		String listeVide = "Pour ajouter un livre à la bibliothèque : Livre -> Ajouter un livre.";
		int coords_x = 35, coords_y = 50, coords_w = 90, coords_h = 20;
		
		if(Btype != null) { PAffichage.add(Btype); }
		// Affichage menu contextuel
		if(lesLivres.size() > 0) {
			JLabel Lelement = new JLabel("Titre".toUpperCase());
			JLabel Lelement2 = new JLabel("Description".toUpperCase());
			JLabel Lelement3 = new JLabel("Auteur".toUpperCase());
			JLabel Lelement4 = new JLabel("Illustration".toUpperCase());
			JLabel Lelement5 = new JLabel("Type".toUpperCase());
			JLabel premiere_page = new JLabel("Résultats : de "  + String.valueOf(debut));  
			JLabel derniere_page = new JLabel(" à " + String.valueOf(fin));
			JLabel pageTotal = new JLabel("");
			String numerotation = liste.size() == 0 ? "sur " + String.valueOf(this.lesLivres.size()) : "sur " + String.valueOf(liste.size());
			pageTotal.setText(numerotation);			
			Lelement		.setBounds(coords_x, 		(coords_y-25), 210, 			20);
			Lelement2		.setBounds((coords_x+100), 	(coords_y-25), 210, 			20);
			Lelement3		.setBounds((coords_x+250), 	(coords_y-25), 210, 			20);
			Lelement4		.setBounds((coords_x+400), 	(coords_y-25), (coords_w+150), 	coords_h);
			Lelement5		.setBounds((coords_x+600), 	(coords_y-25), (coords_w+150), 	coords_h);
			premiere_page	.setBounds(420, 				350, 		100, 			20);
			derniere_page	.setBounds(520, 				350, 		40, 			20);
			pageTotal		.setBounds(550, 				350, 		70, 			20);			
			PAffichage.add(Lelement);
			PAffichage.add(Lelement2);
			PAffichage.add(Lelement3);
			PAffichage.add(Lelement4);
			PAffichage.add(Lelement5);
			PAffichage.add(premiere_page);
			PAffichage.add(derniere_page);
			PAffichage.add(pageTotal);
		} else {
			JLabel LlisteVide = new JLabel(listeVide);
			LlisteVide.setBounds(coords_x, 25, 400, 20);
			PAffichage.add(LlisteVide);
		}
		// Affichage des résultats
		for(int i=debut; i<fin; i++) {
			int x = i - debut;
			if(x != 0) { coords_y += 105; }
			if(type != 0) {
				if(type < 3) {
					// Modifier ou supprimer
					JRadioButton Bradio = new JRadioButton(lesLivres.get(i).getTitre());				
					Bradio.setBounds(650, (coords_y+40), 20, 20);
					Bradio.setActionCommand(lesLivres.get(i).getTitre());
					listeRadio.add(Bradio);
					PAffichage.add(Bradio);
				} else if(type == 4 || type == 6) {
					// Sélection livre pour acheter exemplaires
					JRadioButton Bradio = new JRadioButton(lesLivres.get(i).getTitre());				
					Bradio.setBounds(650, (coords_y+40), 20, 20);
					Bradio.setActionCommand(lesLivres.get(i).getTitre());
					listeRadio.add(Bradio);
					PAffichage.add(Bradio);
				}
			}
			JLabel Lelement = new JLabel("");
			JLabel Lelement2 = new JLabel("");
			JLabel Lelement3 = new JLabel("");
			JLabel Lelement4 = new JLabel("");
	
			if(liste.size() > 0) {
				// Affichage spécifique de la recherche
				for(int y=0; y<lesLivres.size(); y++) {
					if(liste.get(i).getNumero() == lesLivres.get(y).getNumero()) {
						Lelement.setText(lesLivres.get(y).getTitre().substring(0, 1).toUpperCase() 
										+ lesLivres.get(y).getTitre().substring(1).toLowerCase());
						Lelement2.setText(lesLivres.get(y).getDescription().substring(0, 1).toUpperCase() 
										+ lesLivres.get(y).getDescription().substring(1).toLowerCase());
						
						//String auteur = lA.rechercherPseudoAuteur(this.lesLivres.get(y).getTitre());
						//if(auteur == null) {
							//auteur = lA.rechercherNomAuteur(lesLivres.get(y).getTitre(), 0);
						//}

						//Lelement3.setText(auteur.substring(0, 1).toUpperCase() 
						//					+ auteur.substring(1).toLowerCase());

						Lelement4.setText(lesLivres.get(y).getType().substring(0, 1).toUpperCase() 
										+ lesLivres.get(y).getType().substring(1).toLowerCase());

						JLabel Lelement5 = new JLabel(new ImageIcon(lesLivres.get(y).getLien()), JLabel.CENTER);

						Lelement	.setBounds((coords_x-12), 	coords_y, 	coords_w+10, 	coords_h);
						Lelement2	.setBounds((coords_x+100), 	coords_y, 	coords_w, 		coords_h);
						Lelement3	.setBounds((coords_x+250), 	coords_y, 	coords_w+150, 	coords_h);						
						Lelement4	.setBounds((coords_x+600), 	coords_y, 	coords_w+150, 	coords_h);
						Lelement5	.setBounds((coords_x+370),	coords_y-5, coords_w+80, 	95);

						PAffichage.add(Lelement);
						PAffichage.add(Lelement2);
						PAffichage.add(Lelement3);						
						PAffichage.add(Lelement4);
						PAffichage.add(Lelement5);
					}
				}
			} else if(liste.size() == 0) {
				// Affichage de la base de données
				Lelement.setText(lesLivres.get(i).getTitre().substring(0, 1).toUpperCase() 
								+ lesLivres.get(i).getTitre().substring(1).toLowerCase());
				Lelement2.setText(lesLivres.get(i).getDescription().substring(0, 1).toUpperCase() 
								+ lesLivres.get(i).getDescription().substring(1).toLowerCase());

				//String auteur = lA.rechercherPseudoAuteur(lesLivres.get(i).getTitre());
				//if(auteur.length() > 2) {
					//Lelement3.setText(auteur.substring(0, 1).toUpperCase()				
					//					+ auteur.substring(1).toLowerCase());
				//}

				Lelement4.setText(lesLivres.get(i).getType().substring(0, 1).toUpperCase() 
								+ lesLivres.get(i).getType().substring(1).toLowerCase());

				JLabel Lelement5 = new JLabel(new ImageIcon(lesLivres.get(i).getLien()), JLabel.CENTER);

				Lelement	.setBounds((coords_x-12), coords_y, (coords_w+10), coords_h);
				Lelement2	.setBounds((coords_x+100), coords_y, (coords_w), coords_h);
				Lelement3	.setBounds((coords_x+250), coords_y, (coords_w+150), coords_h);				
				Lelement4	.setBounds((coords_x+600), coords_y, (coords_w+150), coords_h);
				Lelement5	.setBounds((coords_x+370), (coords_y-5), (coords_w+80), 95);

				PAffichage.add(Lelement);
				PAffichage.add(Lelement2);
				PAffichage.add(Lelement3);
				PAffichage.add(Lelement4);
				PAffichage.add(Lelement5);
			}
		}
		return listeRadio;
	}

	/**
	 * Lister les enregistrements de l'objet
	 * @param PAffichage
	 */
	public ButtonGroup listerEdition(JPanel PAffichage, int debut, int fin, int type, JButton Btype, ArrayList<Edition> liste) {	
		ButtonGroup listeRadio = new ButtonGroup();
		int coords_x = 30, coords_y = 50, coords_w = 180, coords_h = 20;
		String listeVide = "Pour ajouter une édition à la bibliothèque : Edition -> Ajouter une édition.";

		if(Btype != null) { PAffichage.add(Btype); }

		if(lesEditions.size() > 0) {
			JLabel premiere_page = new JLabel("Résultats : de "  + String.valueOf(debut));
			JLabel derniere_page = new JLabel(" à " + String.valueOf(fin));
			JLabel pageTotal = new JLabel("");
			String numerotation = liste.size() == 0 ? "sur " + String.valueOf(lesEditions.size()) : "sur " + String.valueOf(liste.size());
			pageTotal.setText(numerotation);
			JLabel Lelement = new JLabel(" - Libellé - ".toUpperCase());
			JLabel Lelement2 = new JLabel(" - Libellé - ".toUpperCase());
			Lelement.setBounds(coords_x, (coords_y-20), coords_w, coords_h);
			Lelement2.setBounds((coords_x+210), (coords_y-20), coords_w, coords_h);
			premiere_page	.setBounds(420, 				350, 			100, 20);
			derniere_page	.setBounds(520, 				350, 			40, 20);
			pageTotal		.setBounds(550, 				350, 			70, 20);
			PAffichage.add(Lelement);
			PAffichage.add(Lelement2);
			PAffichage.add(premiere_page);
			PAffichage.add(derniere_page);
			PAffichage.add(pageTotal);
		} else if(liste.size() == 0) {
			JLabel LlisteVide = new JLabel(listeVide);
			LlisteVide.setBounds(coords_x, 25, 400, 20);
			PAffichage.add(LlisteVide);
		}
		for(int i=debut; i<fin; i++) {
			if((i - debut) != 0) { coords_y += 140; }
			if(type != 0) {
				JRadioButton Bradio = new JRadioButton(lesEditions.get(i).getLibelle());				
				Bradio.setBounds(650, (coords_y+40), 20, 20);
				Bradio.setActionCommand(lesEditions.get(i).getLibelle());
				listeRadio.add(Bradio);
				PAffichage.add(Bradio);
			}
			JLabel Lelement = new JLabel("");

			if(liste.size() > 0) {
				// Affichage spécifique
				for(int y=0; y<lesEditions.size(); y++) {
					if(liste.get(i).getNumero() == lesEditions.get(y).getNumero()) {
						Lmention.setText(lesEditions.get(y).getLibelle().substring(0, 1).toUpperCase() 
										+ lesEditions.get(y).getLibelle().substring(1).toLowerCase());
						
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
						JLabel Lelement2 = new JLabel(new ImageIcon(lesEditions.get(y).getLien()), JLabel.CENTER);

						Lelement	.setBounds(coords_x-12, 	coords_y, 		coords_w+10, coords_h);
						Lelement2	.setBounds(coords_x+350, 	coords_y+25, 	coords_w+40, 95);

						PAffichage.add(Lelement);
						PAffichage.add(Lelement2);
					}
				}
			} else if(liste.size() == 0) {
				// Affichage de la base des auteurs
				Lelement.setText(lesEditions.get(i).getLibelle().substring(0, 1).toUpperCase() 
								+ lesEditions.get(i).getLibelle().substring(1).toLowerCase());				
				JLabel Lelement2 = new JLabel(new ImageIcon(lesEditions.get(i).getLien()), JLabel.CENTER);
				// Ajout oeuvres
				/*ArrayList<String> ouvrages = lesEditions.get(i).GetAuteursArray();
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

				Lelement	.setBounds(coords_x, 		coords_y, 		coords_w, 		coords_h);
				Lelement2	.setBounds(coords_x+350, 	coords_y+25, 	coords_w+40, 	95);

				PAffichage.add(Lelement);
				PAffichage.add(Lelement2);
			}
		}
		/*for(int i=0; i<this.lesEditions.size(); i++) {
			if(i%2 != 0 && i != 0) { coords_x = 30*8; }
			if(i%2 == 0 && i != 0) {
				coords_y += 50;
				coords_x = 30;
			}
			JLabel Lelement3 = new JLabel(this.lesEditions.get(i).GetLibelleEdition().substring(0, 1).toUpperCase() 
										+ this.lesEditions.get(i).GetLibelleEdition().substring(1).toLowerCase());
			Lelement3.setBounds((coords_x+15), coords_y, coords_w, coords_h);			
			PAffichage.add(Lelement3);
		}*/
		return listeRadio;
	}
	
	public ButtonGroup listerLecteur(JPanel PAffichage, int debut, int fin, int type, JButton Btype, ArrayList<Lecteur> liste) {
		ButtonGroup listeRadio = new ButtonGroup();
		String listeVide = "Pour ajouter un lecteur à la bibliothèque : Lecteur -> Ajouter un lecteur.";
		int coords_x = 30, coords_y = 50, coords_w = 180, coords_h = 20;

		if(Btype != null) { PAffichage.add(Btype); }

		if(lesLecteurs.size() >= 1) {
			JLabel Lelement = new JLabel("nom".toUpperCase());
			JLabel Lelement2 = new JLabel("prénom".toUpperCase());
			JLabel Lelement3 = new JLabel("pseudonyme".toUpperCase());
			JLabel Lelement4 = new JLabel("Historique".toUpperCase());
			JLabel premiere_page = new JLabel("Résultats : de "  + String.valueOf(debut));
			JLabel derniere_page = new JLabel(" à " + String.valueOf(fin));
			JLabel pageTotal = new JLabel("");
			String numerotation = liste.size() == 0 ? "sur " + String.valueOf(this.lesLecteurs.size()) : "sur " + String.valueOf(liste.size());
			pageTotal.setText(numerotation);
			Lelement		.setBounds(coords_x, 		coords_y-20, 	coords_w, 		coords_h);
			Lelement2		.setBounds(coords_x+100, 	coords_y-20, 	coords_w, 		coords_h);
			Lelement3		.setBounds(coords_x+220, 	coords_y-20, 	coords_w, 		coords_h);
			Lelement4		.setBounds(coords_x+400, 	coords_y-20, 	coords_w+150, 	coords_h);
			premiere_page	.setBounds(420,				350, 			100, 			20);
			derniere_page	.setBounds(520,				350, 			40, 			20);
			pageTotal		.setBounds(550, 			350, 			70, 			20);
			PAffichage.add(premiere_page);
			PAffichage.add(derniere_page);
			PAffichage.add(pageTotal);
			PAffichage.add(Lelement);
			PAffichage.add(Lelement2);
			PAffichage.add(Lelement3);
			PAffichage.add(Lelement4);
		} else {
			JLabel LlisteVide = new JLabel(listeVide);
			LlisteVide.setBounds(coords_x, 25, 400, 20);
			PAffichage.add(LlisteVide);
		}
		for(int i=debut; i<fin; i++) {
			if((i - debut) != 0) { coords_y += 140; }
			if(type != 0) {
				JRadioButton Bradio = new JRadioButton(String.valueOf(lesLecteurs.get(i).getNumero()));				
				Bradio.setBounds(650, coords_y+40, 20, 20);
				Bradio.setActionCommand(lesLecteurs.get(i).getNom());
				listeRadio.add(Bradio);
				PAffichage.add(Bradio);
			}
			JLabel Lelement = new JLabel("");
			JLabel Lelement2 = new JLabel("");
			JLabel Lelement3 = new JLabel("");

			if(liste.size() > 0) {
				// Affichage spécifique
				for(int y=0; y<lesLecteurs.size(); y++) {
					if(liste.get(i).getNumero() == lesLecteurs.get(y).getNumero()) {
						Lelement.setText(lesLecteurs.get(y).getNom().substring(0, 1).toUpperCase() 
										+ lesLecteurs.get(y).getNom().substring(1).toLowerCase());
						Lelement2.setText(lesLecteurs.get(y).getPrenom().substring(0, 1).toUpperCase() 
										+ lesLecteurs.get(y).getPrenom().substring(1).toLowerCase());
						if(lesLecteurs.get(y).getPseudonyme().length() != 0) {
							Lelement3.setText(lesLecteurs.get(y).getPseudonyme().substring(0, 1).toUpperCase() 
											+ lesLecteurs.get(y).getPseudonyme().substring(1).toLowerCase());
						}

						//Ajout ..
					  /*ArrayList<String> ouvrages = this.lesLecteurs.get(y).get..Array();
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

						JLabel Lelement4 = new JLabel(new ImageIcon(lesLecteurs.get(y).getLien()), JLabel.CENTER);

						Lelement	.setBounds((coords_x-12), coords_y, (coords_w+10), coords_h);
						Lelement2	.setBounds((coords_x+100), coords_y, (coords_w), coords_h);
						Lelement3	.setBounds((coords_x+200), coords_y, (coords_w+150), coords_h);
						Lelement4	.setBounds((coords_x+350), (coords_y+25), (coords_w+40), 95);

						PAffichage.add(Lelement);
						PAffichage.add(Lelement2);
						PAffichage.add(Lelement3);
						PAffichage.add(Lelement4);
					}
				}
			} else if(liste.size() == 0) {
				// Affichage de la base des auteurs
				Lelement.setText(lesLecteurs.get(i).getNom().substring(0, 1).toUpperCase() 
								+ lesLecteurs.get(i).getNom().substring(1).toLowerCase());
				Lelement2.setText(lesLecteurs.get(i).getPrenom().substring(0, 1).toUpperCase() 
								+ lesLecteurs.get(i).getPrenom().substring(1).toLowerCase());
				if(lesLecteurs.get(i).getPseudonyme().length() != 0) { 
					Lelement3.setText(lesLecteurs.get(i).getPseudonyme().substring(0, 1).toUpperCase() 
									+ lesLecteurs.get(i).getPseudonyme().substring(1).toLowerCase());
				}
				JLabel Lelement4 = new JLabel(new ImageIcon(lesLecteurs.get(i).getLien()), JLabel.CENTER);

				//Ajout ..
			 /* ArrayList<String> ouvrages = this.lesLecteurs.get(i).get..Array();
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

				Lelement	.setBounds(coords_x, 		coords_y, 		coords_w, 		coords_h);
				Lelement2	.setBounds(coords_x+100, 	coords_y, 		coords_w, 		coords_h);
				Lelement3	.setBounds(coords_x+220, 	coords_y, 		coords_w, 		coords_h);
				Lelement4	.setBounds(coords_x+350, 	coords_y+25, 	coords_w+40, 	95);

				PAffichage.add(Lelement);
				PAffichage.add(Lelement2);
				PAffichage.add(Lelement3);
				PAffichage.add(Lelement4);
			}
		}
		return listeRadio;
	}

	/**
	 * Méthode d'affichage des auteurs
	 * @param PAffichage
	 * @param debut
	 * @param fin
	 * @param type
	 * @param Btype
	 * @param liste
	 * @return ButtonGroup
	 */
	public ButtonGroup listerAuteur(JPanel PAffichage, int debut, int fin, int type, JButton Btype, ArrayList<Auteur> liste) {
		System.out.println("test1");
		ButtonGroup listeRadio = new ButtonGroup();
		String listeVide = "Pour ajouter un auteur à la bibliothèque : Auteur -> Ajouter un auteur.";
		int coords_x = 30, coords_y = 50, coords_w = 180, coords_h = 20;

		if(Btype != null) { PAffichage.add(Btype); }

		if(lesAuteurs.size() > 0) {
			JLabel Lelement = new JLabel("nom".toUpperCase());
			JLabel Lelement2 = new JLabel("prénom".toUpperCase());
			JLabel Lelement3 = new JLabel("pseudonyme".toUpperCase());
			JLabel Lelement4 = new JLabel("Illustration".toUpperCase());
			JLabel premiere_page = new JLabel("Résultats : de "  + String.valueOf(debut));
			JLabel derniere_page = new JLabel(" à " + String.valueOf(fin));
			JLabel pageTotal = new JLabel("");
			String numerotation = liste.size() == 0 ? "sur " + String.valueOf(lesAuteurs.size()) : "sur " + String.valueOf(liste.size());
			pageTotal.setText(numerotation);
			Lelement		.setBounds(coords_x, 		coords_y-20, 	coords_w, 		coords_h);
			Lelement2		.setBounds(coords_x+100, 	coords_y-20, 	coords_w, 		coords_h);
			Lelement3		.setBounds(coords_x+220, 	coords_y-20, 	coords_w, 		coords_h);
			Lelement4		.setBounds(coords_x+400, 	coords_y-20, 	coords_w+150, 	coords_h);
			premiere_page	.setBounds(420, 			350, 			100, 			20);
			derniere_page	.setBounds(520, 			350, 			40, 			20);
			pageTotal		.setBounds(550, 			350, 			70, 			20);
			PAffichage.add(premiere_page);
			PAffichage.add(derniere_page);
			PAffichage.add(pageTotal);
			PAffichage.add(Lelement);
			PAffichage.add(Lelement2);
			PAffichage.add(Lelement3);
			PAffichage.add(Lelement4);
		} else {
			JLabel LlisteVide = new JLabel(listeVide);
			LlisteVide.setBounds(coords_x, 25, 400, 20);
			PAffichage.add(LlisteVide);
		}
		for(int i=debut; i<fin; i++) {
			JLabel Lelement = new JLabel("");
			JLabel Lelement2 = new JLabel("");
			JLabel Lelement3 = new JLabel("");
			JLabel Lelement4 = new JLabel("");

			if((i - debut) != 0) { coords_y += 140; }
			if(type != 0) {
				JRadioButton Bradio = new JRadioButton(lesAuteurs.get(i).getNom());				
				Bradio.setBounds(650, coords_y+40, 20, 20);
				Bradio.setActionCommand(lesAuteurs.get(i).getNom());
				listeRadio.add(Bradio);
				PAffichage.add(Bradio);
			}

			if(liste.size() > 0) {
				// Affichage spécifique
				for(int y=0; y<lesAuteurs.size(); y++) {
					if(liste.get(i).getNumero() == lesAuteurs.get(y).getNumero()) {
						Lelement.setText(lesAuteurs.get(y).getNom().substring(0, 1).toUpperCase() 
										+ lesAuteurs.get(y).getNom().substring(1).toLowerCase());

						Lelement2.setText(lesAuteurs.get(y).getPrenom().substring(0, 1).toUpperCase() 
								 		+ lesAuteurs.get(y).getPrenom().substring(1).toLowerCase());

						if(lesAuteurs.get(y).getPseudo().length() != 0) { 
							Lelement3.setText(lesAuteurs.get(y).getPseudo().substring(0, 1).toUpperCase() 
											+ lesAuteurs.get(y).getPseudo().substring(1).toLowerCase());
						} else { Lelement3.setText("-"); }

						Lelement4.setText(String.valueOf(lesAuteurs.get(i).getLivres().size()) 
										+ " ouvrage(s) : ".toUpperCase());

						//Ajout oeuvres
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

						JLabel Lelement5 = new JLabel(new ImageIcon(lesAuteurs.get(y).getLien()), JLabel.CENTER);

						Lelement	.setBounds((coords_x-12), coords_y, (coords_w+10), coords_h);
						Lelement2	.setBounds((coords_x+100), coords_y, (coords_w), coords_h);
						Lelement3	.setBounds((coords_x+200), coords_y, (coords_w+150), coords_h);
						Lelement4	.setBounds(coords_x, (coords_y+15), coords_w, coords_h);
						Lelement5	.setBounds((coords_x+350), (coords_y+25), (coords_w+40), 95);

						PAffichage.add(Lelement);
						PAffichage.add(Lelement2);
						PAffichage.add(Lelement3);
						PAffichage.add(Lelement5);
					}
				}
			} else if(liste.size() == 0) {
				// Affichage de la base des auteurs
				Lelement.setText(lesAuteurs.get(i).getNom().substring(0, 1).toUpperCase() 
								+ lesAuteurs.get(i).getNom().substring(1).toLowerCase());
				
				Lelement2.setText(lesAuteurs.get(i).getPrenom().substring(0, 1).toUpperCase() 
								+ lesAuteurs.get(i).getPrenom().substring(1).toLowerCase());
				
				if(lesAuteurs.get(i).getPseudo().length() != 0) { 
					Lelement3.setText(lesAuteurs.get(i).getPseudo().substring(0, 1).toUpperCase() 
									+ lesAuteurs.get(i).getPseudo().substring(1).toLowerCase());
				} else { Lelement3.setText("-"); }

				Lelement4.setText(String.valueOf(lesAuteurs.get(i).getLivres().size()) 
												+ " ouvrage(s) : ".toUpperCase());

				JLabel Lelement5 = new JLabel(new ImageIcon(lesAuteurs.get(i).getLien()), JLabel.CENTER);

				// Ajout oeuvres
				/* ArrayList<String> livres = lL.rechercher(this.lesAuteurs.get(i).getLivres());
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

				Lelement	.setBounds(coords_x, 		coords_y, 		coords_w, 		coords_h);
				Lelement2	.setBounds(coords_x+100, 	coords_y, 		coords_w, 		coords_h);
				Lelement3	.setBounds(coords_x+220, 	coords_y, 		coords_w, 		coords_h);
				Lelement4	.setBounds(coords_x, 		coords_y+15, 	coords_w, 		coords_h);
				Lelement5	.setBounds(coords_x+350, 	coords_y+25, 	coords_w+40, 	95);

				PAffichage.add(Lelement);
				PAffichage.add(Lelement2);
				PAffichage.add(Lelement3);
				PAffichage.add(Lelement4);
				PAffichage.add(Lelement5);
			}
		}
		return listeRadio;
	}

	/*public ButtonGroup listerR(JPanel PAffichage, int debut, int fin, int type, JButton bouton, ArrayList<Object> liste) {
		ButtonGroup listeRadio = new ButtonGroup();
		String listeVide = "Pour ajouter un club à la compétition : Club -> Ajouter un club.";
		int coords_x = 30, coords_y = 50, coords_w = 180, coords_h = 20;

		if(Btype != null) { PAffichage.add(Btype); }

		// Affichage menu contextuel
		if(liste.size() > 0) {
			JLabel Lelement = new JLabel("nom".toUpperCase());
			JLabel Lelement2 = new JLabel("Appartient à " + ((Element) liste.get(0)).getParent());
			JLabel Lelement3 = new JLabel("Nombre de " + ((Element) liste.get(0)).getEnfant());
			JLabel page_prem = new JLabel("Résultats : de "  + String.valueOf(debut));
			JLabel page_dern = new JLabel(" à " + String.valueOf(fin));
			JLabel page_total = new JLabel("");
			String numerotation = String.valueOf(liste.size());
			pageTotal.setText(numerotation);
			Lelement		.setBounds(coords_x, 		(coords_y-20), 	coords_w, coords_h);
			Lelement2		.setBounds(coords_x+100,	(coords_y-20), 	coords_w, coords_h);
			Lelement3		.setBounds(coords_x+200,	(coords_y-20), 	coords_w, coords_h);
			page_prem	.setBounds(420, 				350, 			100, 20);
			page_dern	.setBounds(520, 				350, 			40, 20);
			pageTotal	.setBounds(550, 				350, 			70, 20);
			PAffichage.add(page_prem);
			PAffichage.add(page_dern);
			PAffichage.add(pageTotal);
			PAffichage.add(Lelement);
			PAffichage.add(Lelement2);
			PAffichage.add(Lelement3);
		}

		// Affichage des résultats
		for(int i=debut; i<fin; i++) {
			if((i - debut) != 0) { coords_y += 140; }

			JLabel element = new JLabel("");
			JLabel element2 = new JLabel(((Element) liste.get(0)).getParent());
			JLabel element3 = new JLabel(((Element) liste.get(0)).getEnfant());
			
			for(int y=0; y<liste.size(); y++) {
				if(((Element) liste.get(i)).getNumero() == ((Element) liste.get(y)).getNumero()) {
				element.setText(((Element) liste.get(y)).getNom());
				//element2.setText(((Element) liste.get(y)).get()); obtenir le nom du prante parent.getNom()
				//element3.setText(((Element) liste.get(y)).get()); obtenir le nombre d'enfants : getEnfants().size()
				element.setBounds((coords_x-12), coords_y, (coords_w+10), coords_h);
				element2.setBounds((coords_x-12)+100, coords_y, (coords_w+10), coords_h);
				element3.setBounds((coords_x-12)+200, coords_y, (coords_w+10), coords_h);
				PAffichage.add(element);
				PAffichage.add(element2);
				PAffichage.add(element3);
			}}
		}
		return 
	}*/

	private void login(String user, String pcw) {
		int _USER_LENGTH = 23, _PCW_LENGTH = 20;

		// Vérification contenu..
		if(_USER_LENGTH > user.length()) { _USER_LENGTH = user.length(); }
		if(_PCW_LENGTH > pcw.length()) { _PCW_LENGTH = pcw.length(); }
		if(user.length() < 2 || pcw.length() < 2) { erreur("Champs incomplet(s) ! "); }
		else if(user.equalsIgnoreCase("")
				|| pcw.equalsIgnoreCase("")
				|| user.substring(0, _USER_LENGTH).equalsIgnoreCase("Entrer un utilisateur..")
				|| pcw.substring(0, _PCW_LENGTH).equalsIgnoreCase("Entrer un password..")) {
			erreur("Un des champs est vide ou incomplet !");
		} else {
			// Formulaire validé..
			boolean verification = lLect.isEnregistrer(user, pcw);
			if(!verification) { informationShow("Le mot de passe est incorrect !"); }
			else {
				authentifie = true;
				pseudonyme = user;
				setTextField();
				Bclient.doClick();
			}
		}
	}

	//	Méthode de recherche par lettre clée
	public void recherche() {
    	refresh("formulaire");
		Pformulaire.add(Bretour);
    	Pformulaire.add(Irecherche);
		Pformulaire.add(LinfoResultat);
    	String recherche = Irecherche.getText();
    	int rechercheLength = recherche.length();
    	boolean visible = false;
    	boolean init = false;

    	if(recherche.equalsIgnoreCase("Entrer votre recherche..")) { init = true; }
        if(rechercheLength > 0 && !init) {
        	ArrayList<Integer> numeros = new ArrayList<Integer>();
        	if(inTable.equalsIgnoreCase("livre")) 				numeros = lL.getNumerosByLetter(recherche, rechercheLength);		        		
        	else if(inTable.equalsIgnoreCase("auteur")) 		numeros = lA.getNumerosByLetter(recherche, rechercheLength);		        		
        	else if(inTable.equalsIgnoreCase("edition")) 		numeros = lE.getNumerosByLetter(recherche, rechercheLength);
        	else if(inTable.equalsIgnoreCase("lecteur")) 		numeros = lLect.GetNumerosByLetter(recherche, rechercheLength);
        	//else if(in_table.equalsIgnoreCase("exemplaire")) 	numeros = lEx.getNumerosByLetter(recherche, rechercheLength);
        	else if(inTable.equalsIgnoreCase("traduction")) 	numeros = lTr.getNumerosByLetter(recherche, rechercheLength);

        	// Assigne au tableau de résultats
        	/*ArrayList<Integer> numeros_filtre = new ArrayList<Integer>();
        	for(int i=0; i<numeros.size(); i++) {
        		if(numeros.get(i) != 0) {
        			numeros_filtre.add(numeros.get(i));
        			LinfoResultat.setText("Résultats : ");
        		}
        	}*/

        	// Affichage de la recherche
        	if(numeros.size() > 0) {
        		visible = true;
        		if(inTable.equalsIgnoreCase("livre")) 			navigation(0, "livre", "rechercher", numeros, 0, null);
        		else if(inTable.equalsIgnoreCase("auteur")) 	navigation(0, "auteur", "rechercher", numeros, 0, null);
        		else if(inTable.equalsIgnoreCase("edition")) 	navigation(0, "edition", "rechercher", numeros, 0, null);
        		else if(inTable.equalsIgnoreCase("lecteur")) 	navigation(0, "lecteur", "rechercher", numeros, 0, null);
        		else if(inTable.equalsIgnoreCase("exemplaire")) navigation(0, "exemplaire", "rechercher", numeros, 0, null);
        		else if(inTable.equalsIgnoreCase("traduction")) navigation(0, "traduction", "rechercher", numeros, 0, null);
        	}
        }
		if(!visible && rechercheLength > 0 && !init) {
			LinfoResultat.setText("Aucun résultats");
		} else if(recherche.equalsIgnoreCase("") || init) {
			LinfoResultat.setText("");
		}
    }

	public void rechercheTraduction() {
		String recherche = IlettreLangue.getText();
    	int rechercheLength = recherche.length();
    	boolean visible = false;
    	boolean init = false;

    	ClisteTraduction.removeAllItems();
    	ClisteTraduction.addItem("Sélectionner une langue");
    	ClisteTraduction.addItem("Langue d'origine");
 
    	if(recherche.equalsIgnoreCase("Entrer la langue..")) { init = true; }
        if(rechercheLength > 0 && !init) {
        	String[] lesLangues = lTr.getNomsByLetter(recherche, rechercheLength);
        	for(int i=0; i<lesLangues.length; i++) {
        		try {
	        		if(!lesLangues[i].equalsIgnoreCase("")) {
	        			ClisteTraduction.addItem(lesLangues[i]);
	        			visible = true;
	        		}
	        	} catch (NullPointerException e) {}
        	}
        }
		if(!visible) {
			ClisteTraduction.removeAllItems();
	    	ClisteTraduction.addItem("Sélectionner une langue");
			ClisteTraduction.addItem("Langue d'origine");
		}
		ClisteTraduction.setVisible(true);
	}

	public void rechercheEdition() {
		String recherche = IlettreEdition.getText();
    	int rechercheLength = recherche.length();
    	boolean visible = false;
    	boolean init = false;

    	ClisteEdition.removeAllItems();
    	ClisteEdition.addItem("Sélectionner un éditeur");

    	if(recherche.equalsIgnoreCase("Entrer un éditeur..")) { init = true; }
        if(rechercheLength > 0 && !init) {
        	String[] lesEditeurs = lE.getNomsByLetter(recherche, rechercheLength);
        	for(int i=0; i<lesEditeurs.length; i++) {
        		try {
	        		if(!lesEditeurs[i].equalsIgnoreCase("")) {
	        			ClisteEdition.addItem(lesEditeurs[i]);
	        			visible = true;
	        		}
	        	} catch (NullPointerException e) {}
        	}
        }
		if(!visible) {
			ClisteEdition.removeAllItems();
			ClisteEdition.addItem("Aucun éditeur disponible");
		}
		ClisteEdition.setVisible(true);
	}

	/**
	 * Méthode de recherche de livres
	 */
	public void rechercheLivre() {
		String recherche = IlettreLivre.getText();
    	int rechercheLength = recherche.length();
    	boolean visible = false;
    	boolean init = false;

    	ClisteLivre.removeAllItems();
    	ClisteLivre.addItem("Sélectionner un livre");

    	if(recherche.equalsIgnoreCase("Entrer un titre..")) { init = true; }
        if(rechercheLength > 0 && !init) {
        	ArrayList<String> liste = lL.getNomsByLetter(recherche, rechercheLength);
        	for(int i=0; i<liste.size(); i++) {
        		try {
	        		if(!liste.get(i).equalsIgnoreCase("")) {
	        			ClisteLivre.addItem(liste.get(i));
	        			visible = true;
	        		}
	        	} catch (NullPointerException e) {}
        	}
        }
		if(!visible) {
			ClisteLivre.removeAllItems();
			ClisteLivre.addItem("Aucune oeuvre disponible");
		}
		ClisteLivre.setVisible(true);
	}
	
	/**
	 * Méthode de recherche d'auteurs
	 */
	public void rechercheAuteur() {
		String recherche = IlettreAuteur.getText();
    	int rechercheLength = recherche.length();
    	boolean visible = false;
    	ClisteAuteur.removeAllItems();
    	ClisteAuteur.addItem("Sélectionner un auteur");

        if(rechercheLength > 0) {
        	String[] auteurs = lA.getAuteursByLetter(recherche, rechercheLength);
        	for(int i=0; i<auteurs.length; i++) {
        		try {
	        		if(!auteurs[i].equalsIgnoreCase("")) {
	        			ClisteAuteur.addItem(auteurs[i]);
	        			visible = true;
	        		}
	        	} catch (NullPointerException e) {}
        	}
        	
        }
		if(!visible) {
			ClisteAuteur.removeAllItems();
			ClisteAuteur.addItem("Aucun auteur disponible");
		}
		ClisteAuteur.setVisible(true);
	}

	public void rechercheNavigation() {
		int numeroPage = 0;
		String page = "";
		boolean entier = true;

		try {
			if(!Inavigation.getText().isEmpty()) { 
				page = Inavigation.getText(); 
			}
			if(page.length() == 2) {
				Integer.parseInt(page.substring(1, 2)); // Vérification du second chiffre après le premier
				numeroPage = Integer.parseInt(page); 	// nombre valide
			} else { 
				numeroPage = Integer.parseInt(page); 
			}
		} catch (NumberFormatException e) { entier = false; }

		if(entier && numeroPage != 0 && numeroPage < 99 && numeroPage > 0) {
			navigation(0, "", "", new ArrayList<Integer>(), numeroPage, null);
		}
	}

	/**
	 * Retourne les identifiants en fonction de la recherche
	 * @param recherche : String
	 * @param rechercheLength : longueur de recherche
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getNumeroRecherche(String recherche, int rechercheLength, String classe) {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		if(classe.equalsIgnoreCase("libre")) {
			for(int i=0; i<this.lesLivres.size(); i++) {
				int valeurLength = this.lesLivres.get(i).getTitre().length();

				if(valeurLength >= rechercheLength) { valeurLength = rechercheLength; }
				if(this.lesLivres.get(i).getTitre().substring(0, valeurLength).equalsIgnoreCase(recherche)) {
					numeros.add(this.lesLivres.get(i).getNumero());
				}
			}
		} else if(classe.equalsIgnoreCase("auteur")) {
			for(int i=0; i<this.lesAuteurs.size(); i++) {
				int valeurLength = this.lesAuteurs.get(i).getNom().length();

				if(valeurLength >= rechercheLength) { valeurLength = rechercheLength; }					
				if(this.lesAuteurs.get(i).getNom().substring(0, valeurLength).equalsIgnoreCase(recherche)) {
					numeros.add(this.lesAuteurs.get(i).getNumero());
				}				
			}
		} else if(classe.equalsIgnoreCase("lecteur")) {
			for(int i=0; i<this.lesLecteurs.size(); i++) {
				int valeurLength = this.lesLecteurs.get(i).getNom().length();

				if(valeurLength >= rechercheLength) { valeurLength = rechercheLength; }
				if(this.lesLecteurs.get(i).getNom().substring(0, valeurLength).equalsIgnoreCase(recherche)) {
					numeros.add(this.lesLecteurs.get(i).getNumero());
				}
			}
		
		} else if(classe.equalsIgnoreCase("edition")) {
			for(int i=0; i<this.lesEditions.size(); i++) {
				int valeurLength = this.lesEditions.get(i).getNom().length();

				if(valeurLength >= rechercheLength) { valeurLength = rechercheLength; }
				if(this.lesEditions.get(i).getNom().substring(0, valeurLength).equalsIgnoreCase(recherche)) {
					numeros.add(this.lesEditions.get(i).getNumero());
				}
			}
		}
		return numeros;
	}

	public ArrayList<Object> getObjetRecherche(String recherche, int rechercheLength, String classe) {
		ArrayList<Object> objets = new ArrayList<Object>();
		if(classe.equalsIgnoreCase("libre")) {
			for(int i=0; i<this.lesLivres.size(); i++) {
				int valeurLength = this.lesLivres.get(i).getTitre().length();

				if(valeurLength >= rechercheLength) { valeurLength = rechercheLength; }
				if(this.lesLivres.get(i).getTitre().substring(0, valeurLength).equalsIgnoreCase(recherche)) {
					objets.add(this.lesLivres.get(i).getNumero());
				}
			}
		} else if(classe.equalsIgnoreCase("auteur")) {
			for(int i=0; i<this.lesAuteurs.size(); i++) {
				int valeurLength = this.lesAuteurs.get(i).getNom().length();

				if(valeurLength >= rechercheLength) { valeurLength = rechercheLength; }					
				if(this.lesAuteurs.get(i).getNom().substring(0, valeurLength).equalsIgnoreCase(recherche)) {
					objets.add(this.lesAuteurs.get(i).getNumero());
				}				
			}
		} else if(classe.equalsIgnoreCase("lecteur")) {
			for(int i=0; i<this.lesLecteurs.size(); i++) {
				int valeurLength = this.lesLecteurs.get(i).getNom().length();

				if(valeurLength >= rechercheLength) { valeurLength = rechercheLength; }
				if(this.lesLecteurs.get(i).getNom().substring(0, valeurLength).equalsIgnoreCase(recherche)) {
					objets.add(this.lesLecteurs.get(i).getNumero());
				}
			}
		
		} else if(classe.equalsIgnoreCase("edition")) {
			for(int i=0; i<this.lesEditions.size(); i++) {
				int valeurLength = this.lesEditions.get(i).getNom().length();

				if(valeurLength >= rechercheLength) { valeurLength = rechercheLength; }
				if(this.lesEditions.get(i).getNom().substring(0, valeurLength).equalsIgnoreCase(recherche)) {
					objets.add(this.lesEditions.get(i).getNumero());
				}
			}
		}
		return objets;
	}

	/**
	 *  Méthode de méthodes évènementielles abstraites
	 */
	public void evenement() {
		// Evènements sur les résultats de l'affichage spécialisé		
		Irecherche.getDocument().addDocumentListener(new DocumentListener() {
			public void UpdateData() {
				if(!Irecherche.getText().equalsIgnoreCase(rechercheTemp)) {
			     	String recherche = Irecherche.getText();
			    	int rechercheLength = Irecherche.getText().length();
			    	boolean visible = false;
			    	boolean init = false;		// Indique si l'utilisateur entre une valeur ( false )

			    	if(recherche.equalsIgnoreCase("Entrer votre recherche..")) { init = true; }
			        if(rechercheLength > 0 && !init) {
			       	// Envoi de la requête
			       	@SuppressWarnings("unused")
					ArrayList<Integer> numeros = new ArrayList<Integer>();
			       	ArrayList<Object> objets = new ArrayList<Object>();
		        	if(inTable.equalsIgnoreCase("livre")) 				objets = getObjetRecherche(recherche, rechercheLength, "livre");
		        	else if(inTable.equalsIgnoreCase("auteur"))			objets = getObjetRecherche(recherche, rechercheLength, "auteur");
		        	else if(inTable.equalsIgnoreCase( "edition"))		objets = getObjetRecherche(recherche, rechercheLength, "edition");
		        	else if(inTable.equalsIgnoreCase( "lecteur"))		objets = getObjetRecherche(recherche, rechercheLength, "lecteur");
		        	//else if(inTable.equalsIgnoreCase( "exemplaire"))	objets = getObjetRecherche(recherche, rechercheLength, "exemplaire");
		        	else if(inTable.equalsIgnoreCase( "traduction"))	objets = getObjetRecherche(recherche, rechercheLength, "traduction");

		        	// Assigne au tableau de résultats
		        	ArrayList<Object> objetsFiltre = new ArrayList<Object>();
		        	for(int i=0; i<objets.size(); i++) {
		        		if(objets.get(i) != null) {
		        			objetsFiltre.add(objets.get(i));
		        			LinfoResultat.setText("Résultats : ");
		        		}
		        	}

		        	// Affichage de la recherche
		        	if(objets.size() > 0) {
		        	 	visible = true;
		        	 	if(inTable.equalsIgnoreCase("livre")) 	{
		        	 		navigationObjet(0, "livre", "rechercher", objets, 0, null);
		        	 	} else if(inTable.equalsIgnoreCase("auteur")) {
		        	 		navigationObjet(0, "auteur", "rechercher", objets, 0, null);
		        	 	} else if(inTable.equalsIgnoreCase("lecteur")) {
		        	 		navigationObjet(0, "lecteur", "rechercher", objets, 0, null);
		        	 	} else if(inTable.equalsIgnoreCase("edition")) {
		        	 		navigationObjet(0, "edition", "rechercher", objets, 0, null);
		        	 	}
		        	 }
		        }
				if(!visible && !init && rechercheLength > 0) {
					LinfoResultat.setText("Aucun résultat enregistré");
				} else if(init || recherche.equalsIgnoreCase("")) {
					LinfoResultat.setText("");
				}
				rechercheTemp = Irecherche.getText();
			}
		}

		@Override
		public void changedUpdate(DocumentEvent evt) { }
		@Override
		public void insertUpdate(DocumentEvent evt) { UpdateData(); }
		@Override
		public void removeUpdate(DocumentEvent evt) { UpdateData(); }
	});

		/*
		// évènements sur la combolist des auteurs lors de l'ajout d'un livre
		Ilettre_auteur.getDocument().addDocumentListener(new DocumentListener() {		    
			@Override
			public void changedUpdate(DocumentEvent evt) {}
			@Override
		    public void insertUpdate(DocumentEvent evt) {	UpdateData();	}
			@Override
		    public void removeUpdate(DocumentEvent evt) { UpdateData();	}
		});

		// évènements sur la combolist des livres lors de l'ajout d'un exemplaire
		Ilettre_livre.getDocument().addDocumentListener(new DocumentListener() {		    
			@Override
			public void changedUpdate(DocumentEvent evt) {}
			@Override
		    public void insertUpdate(DocumentEvent evt) { 			}
			@Override
		    public void removeUpdate(DocumentEvent evt) {				}
		});

		// évènements sur la combolist des éditeurs lors de l'ajout d'un exemplaire
		Ilettre_edition.getDocument().addDocumentListener(new DocumentListener() {		    
			@Override
			public void changedUpdate(DocumentEvent evt) {}
			@Override
		    public void insertUpdate(DocumentEvent evt) {	}
			@Override
		    public void removeUpdate(DocumentEvent evt) { 	}
		});

		// évènements sur la combolist des langues lors de l'ajout d'un exemplaire
		Ilettre_langue.getDocument().addDocumentListener(new DocumentListener() {		    
			@Override
			public void changedUpdate(DocumentEvent evt) {}
			@Override
		    public void insertUpdate(DocumentEvent evt) {	}
			@Override
		    public void removeUpdate(DocumentEvent evt) {	}
		});*/

		//	évènements sur le changement de page
		Inavigation.getDocument().addDocumentListener(new DocumentListener() {
			// Fonction principale
			public void UpdateData() {
				if(!Inavigation.getText().equalsIgnoreCase(rechercheTemp)
				&& !Inavigation.getText().equalsIgnoreCase("")) {
					rechercheTemp = Inavigation.getText();
					try {
						// à revoir
						if(Integer.parseInt(Inavigation.getText()) < 99 
						&& Integer.parseInt(Inavigation.getText()) > 0
						&& inTable.equalsIgnoreCase("nondefini")) {
							//navigation(0, "", "", new ArrayList<Integer>(), Integer.parseInt(Inavigation.getText()), null);
						} else if(Integer.parseInt(Inavigation.getText()) < 99 
						&& Integer.parseInt(Inavigation.getText()) > 0
						&& (inTable.equalsIgnoreCase("livre") || inTable.equalsIgnoreCase("auteur") || inTable.equalsIgnoreCase("lecteur") || inTable.equalsIgnoreCase("edition"))) {
							navigationObjet(0, "", "", new ArrayList<Object>(), Integer.parseInt(Inavigation.getText()), null);
						}
					} catch(NumberFormatException exp) {
						informationShow("Vous avez rentré une lettre, uniquement des chiffres sont à renseigner !");
					}
				}
			}

			@Override
			public void changedUpdate(DocumentEvent evt) {}
			@Override
		    public void insertUpdate(DocumentEvent evt) { UpdateData(); }
			@Override
		    public void removeUpdate(DocumentEvent evt) { UpdateData(); }			
		});
	}

	/**
	 * Evènements de l'utilisateur
	 */
	public void actionPerformed(ActionEvent e) {
		// bouton retour
		if(e.getSource() == Bretour) {
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
	@Override
	public void mouseEntered(MouseEvent m) {}
	@Override
	public void mouseExited(MouseEvent m) {}
	@Override
	public void mousePressed(MouseEvent m) {}
	@Override
	public void mouseReleased(MouseEvent m) {
		//	Bouton recherche
		if(m.getSource() == Irecherche) {
			if(Irecherche.getText().equalsIgnoreCase("Entrer votre recherche..")) Irecherche.setText("");
			Irecherche.requestFocus();
		}
		if(m.getSource() != Irecherche) if(Irecherche.getText().equalsIgnoreCase("")) Irecherche.setText("Entrer votre recherche..");			

		//	Boutons nom - auteur
		if(m.getSource() == InomAuteur) {
			if(InomAuteur.getText().equalsIgnoreCase("Entrer un nom..")) {
				InomAuteur.requestFocus();
				InomAuteur.setText("");
			}
		}
		if(m.getSource() != InomAuteur) if(InomAuteur.getText().equalsIgnoreCase("")) InomAuteur.setText("Entrer un nom..");

		//	Boutons prénom - auteur
		if(m.getSource() == IprenomAuteur) {
			if(IprenomAuteur.getText().equalsIgnoreCase("Entrer un prénom..")) IprenomAuteur.setText("");
			IprenomAuteur.requestFocus();
		}
		if(m.getSource() != IprenomAuteur) if(IprenomAuteur.getText().equalsIgnoreCase("")) IprenomAuteur.setText("Entrer un prénom..");
		
		//	Boutons pseudonyme - auteur
		if(m.getSource() == IpseudoAuteur) {
			if(IpseudoAuteur.getText().equalsIgnoreCase("Entrer un pseudonyme..")) IpseudoAuteur.setText("");
			IpseudoAuteur.requestFocus();
		}
		if(m.getSource() != IpseudoAuteur) if(IpseudoAuteur.getText().equalsIgnoreCase("")) IpseudoAuteur.setText("Entrer un pseudonyme..");

		//	Boutons titre - livre
		if(m.getSource() == Ititre) {
			if(Ititre.getText().equalsIgnoreCase("Entrer un titre..")) Ititre.setText("");
			Ititre.requestFocus();
		}
		if(m.getSource() != Ititre) if(Ititre.getText().equalsIgnoreCase("")) Ititre.setText("Entrer un titre..");

		//	Boutons description - livre
		if(m.getSource() == IDescription) {
			if(IDescription.getText().equalsIgnoreCase("Entrer une description..")) IDescription.setText("");
			IDescription.requestFocus();
		}
		if(m.getSource() != IDescription) if(IDescription.getText().equalsIgnoreCase("")) IDescription.setText("Entrer une description..");

		//	Boutons type - livre
		if(m.getSource() == ItypeLivre) {
			if(ItypeLivre.getText().equalsIgnoreCase("Entrer un type de livre..")) ItypeLivre.setText("");
			ItypeLivre.requestFocus();
		}
		if(m.getSource() != ItypeLivre) if(ItypeLivre.getText().equalsIgnoreCase("")) ItypeLivre.setText("Entrer un type de livre..");

		//	Boutons recherche lettre auteur- livre
		if(m.getSource() == IlettreAuteur) {
			if(IlettreAuteur.getText().equalsIgnoreCase("Entrer l'auteur..")) IlettreAuteur.setText("");
			IlettreAuteur.requestFocus();
		}
		if(m.getSource() != IlettreAuteur) if(IlettreAuteur.getText().equalsIgnoreCase("")) IlettreAuteur.setText("Entrer l'auteur..");

		//	Champs mention - édition
		if(m.getSource() == Imention) {
			if(Imention.getText().equalsIgnoreCase("Entrer une mention..")) Imention.setText("");
			Imention.requestFocus();
		}
		if(m.getSource() != Imention) if(Imention.getText().equalsIgnoreCase("")) Imention.setText("Entrer une mention..");

		//	Boutons nom - lecteur
		if(m.getSource() == InomLecteur) {
			if(InomLecteur.getText().equalsIgnoreCase("Entrer un nom..")) InomLecteur.setText("");
			InomLecteur.requestFocus();
		}
		if(m.getSource() != InomLecteur) if(InomLecteur.getText().equalsIgnoreCase("")) InomLecteur.setText("Entrer un nom..");
		
		//	Boutons prénom - lecteur
		if(m.getSource() == IprenomLecteur) {
			if(IprenomLecteur.getText().equalsIgnoreCase("Entrer un prénom..")) IprenomLecteur.setText("");
			IprenomLecteur.requestFocus();
		}
		if(m.getSource() != IprenomLecteur) if(IprenomLecteur.getText().equalsIgnoreCase("")) IprenomLecteur.setText("Entrer un prénom..");
		
		//	Boutons pseudonyme - lecteur
		if(m.getSource() == IpseudonymeLecteur) {
			if(IpseudonymeLecteur.getText().equalsIgnoreCase("Entrer un pseudonyme..")) IpseudonymeLecteur.setText("");
			IpseudonymeLecteur.requestFocus();
		}
		if(m.getSource() != IpseudonymeLecteur) if(IpseudonymeLecteur.getText().equalsIgnoreCase("")) IpseudonymeLecteur.setText("Entrer un pseudonyme..");
		
		//	Boutons pcw - lecteur
		if(m.getSource() == Ipcw) {
			if(Ipcw.getText().equalsIgnoreCase("Entrer un password..")) Ipcw.setText("");
			Ipcw.requestFocus();
		}
		if(m.getSource() != Ipcw) if(Ipcw.getText().equalsIgnoreCase("")) Ipcw.setText("Entrer un password..");
		
		//	Boutons langue - exemplaire
		if(m.getSource() == IlettreLangue) {
			if(IlettreLangue.getText().equalsIgnoreCase("Entrer la langue..")) IlettreLangue.setText("");
			IlettreLangue.requestFocus();
		}
		if(m.getSource() != IlettreLangue) if(IlettreLangue.getText().equalsIgnoreCase("")) IlettreLangue.setText("Entrer la langue..");
		
		//	Boutons nombre - exemplaire
		if(m.getSource() == Inombre) {
			if(Inombre.getText().equalsIgnoreCase("Entrer un nombre d'exemplaires..")) Inombre.setText("");
			Inombre.requestFocus();
		}
		if(m.getSource() != Inombre) if(Inombre.getText().equalsIgnoreCase("")) Inombre.setText("Entrer un nombre d'exemplaires..");

		//	Boutons lettre livre - exemplaire
		if(m.getSource() == IlettreLivre) {
			if(IlettreLivre.getText().equalsIgnoreCase("Entrer le titre..")) IlettreLivre.setText("");
			IlettreLivre.requestFocus();
		}
		if(m.getSource() != IlettreLivre) if(IlettreLivre.getText().equalsIgnoreCase("")) IlettreLivre.setText("Entrer le titre..");
		
		//	Boutons lettre éditeur - exemplaire
		if(m.getSource() == IlettreEdition) {
			if(IlettreEdition.getText().equalsIgnoreCase("Entrer l'éditeur..")) IlettreEdition.setText("");
			IlettreEdition.requestFocus();
		}
		if(m.getSource() != IlettreEdition) if(IlettreEdition.getText().equalsIgnoreCase("")) IlettreEdition.setText("Entrer l'éditeur..");
		
		//	Boutons prix - exemplaire
		if(m.getSource() == Iprix) {
			if(Iprix.getText().equalsIgnoreCase("Entrer un prix..")) Iprix.setText("");
			Iprix.requestFocus();
		}
		if(m.getSource() != Iprix) if(Iprix.getText().equalsIgnoreCase("")) Iprix.setText("Entrer un prix..");
		
		//	Boutons langue - traduction
		if(m.getSource() == Ilangue) {
			if(Ilangue.getText().equalsIgnoreCase("Entrer une langue..")) Ilangue.setText("");
			Ilangue.requestFocus();
		}
		if(m.getSource() != Ilangue) if(Ilangue.getText().equalsIgnoreCase("")) Ilangue.setText("Entrer une langue..");
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		if(m.getSource() == infoVersion) afficheUrl("mcalmel.fr", 0);

		//	champs recherche
		if(m.getSource() == Irecherche) {
			if(Irecherche.getText().equalsIgnoreCase("Entrer votre recherche..")) Irecherche.setText("");
			Irecherche.requestFocus();
		}
		if(m.getSource() != Irecherche) if(Irecherche.getText().equalsIgnoreCase("")) Irecherche.setText("Entrer votre recherche..");

		//	Champs nom - auteur
		if(m.getSource() == InomAuteur) {
			if(InomAuteur.getText().equalsIgnoreCase("Entrer un nom..")) {
				InomAuteur.setText("");
			}
			InomAuteur.requestFocus();
		}
		if(m.getSource() != InomAuteur) if(InomAuteur.getText().equalsIgnoreCase("")) InomAuteur.setText("Entrer un nom..");

		//	Champs prénom - auteur
		if(m.getSource() == IprenomAuteur) {
			if(IprenomAuteur.getText().equalsIgnoreCase("Entrer un prénom..")) IprenomAuteur.setText("");
			IprenomAuteur.requestFocus();
		}
		if(m.getSource() != IprenomAuteur) if(IprenomAuteur.getText().equalsIgnoreCase("")) IprenomAuteur.setText("Entrer un prénom..");

		//	Champs pseudonyme - auteur
		if(m.getSource() == IpseudoAuteur) {
			if(IpseudoAuteur.getText().equalsIgnoreCase("Entrer un pseudonyme..")) IpseudoAuteur.setText("");
			IpseudoAuteur.requestFocus();
		}
		if(m.getSource() != IpseudoAuteur) if(IpseudoAuteur.getText().equalsIgnoreCase("")) IpseudoAuteur.setText("Entrer un pseudonyme..");

		//	Champs titre - livre
		if(m.getSource() == Ititre) {
			if(Ititre.getText().equalsIgnoreCase("Entrer un titre..")) Ititre.setText("");
			Ititre.requestFocus();
		}
		if(m.getSource() != Ititre) if(Ititre.getText().equalsIgnoreCase("")) Ititre.setText("Entrer un titre..");
		
		//	Champs description - livre
		if(m.getSource() == IDescription) {
			if(IDescription.getText().equalsIgnoreCase("Entrer une description..")) IDescription.setText("");
			IDescription.requestFocus();
		}
		if(m.getSource() != IDescription) if(IDescription.getText().equalsIgnoreCase("")) IDescription.setText("Entrer une description..");
		
		//	Champs type - livre
		if(m.getSource() == ItypeLivre) {
			if(ItypeLivre.getText().equalsIgnoreCase("Entrer un type de livre..")) ItypeLivre.setText("");
			ItypeLivre.requestFocus();
		}
		if(m.getSource() != ItypeLivre) if(ItypeLivre.getText().equalsIgnoreCase("")) ItypeLivre.setText("Entrer un type de livre..");

		//	Champs lettre recherche auteur - livre
		if(m.getSource() == IlettreAuteur) {
			if(IlettreAuteur.getText().equalsIgnoreCase("Entrer l'auteur..")) IlettreAuteur.setText("");
			IlettreAuteur.requestFocus();
		}
		if(m.getSource() != IlettreAuteur) if(IlettreAuteur.getText().equalsIgnoreCase("")) IlettreAuteur.setText("Entrer l'auteur..");

		//	Champs mention - édition
		if(m.getSource() == Imention) {
			if(Imention.getText().equalsIgnoreCase("Entrer une mention..")) Imention.setText("");
			Imention.requestFocus();
		}
		if(m.getSource() != Imention) if(Imention.getText().equalsIgnoreCase("")) Imention.setText("Entrer une mention..");

		//	Champs nom - lecteur
		if(m.getSource() == InomLecteur) {
			if(InomLecteur.getText().equalsIgnoreCase("Entrer un nom..")) InomLecteur.setText("");
			InomLecteur.requestFocus();
		}
		if(m.getSource() != InomLecteur) if(InomLecteur.getText().equalsIgnoreCase("")) InomLecteur.setText("Entrer un nom..");

		//	Champs prénom - lecteur
		if(m.getSource() == IprenomLecteur) {
			if(IprenomLecteur.getText().equalsIgnoreCase("Entrer un prénom..")) IprenomLecteur.setText("");
			IprenomLecteur.requestFocus();
		}
		if(m.getSource() != IprenomLecteur) if(IprenomLecteur.getText().equalsIgnoreCase("")) IprenomLecteur.setText("Entrer un prénom..");

		//	Champs pseudonyme - lecteur
		if(m.getSource() == IpseudonymeLecteur) {
			if(IpseudonymeLecteur.getText().equalsIgnoreCase("Entrer un pseudonyme..")) IpseudonymeLecteur.setText("");
			IpseudonymeLecteur.requestFocus();
		}
		if(m.getSource() != IpseudonymeLecteur) if(IpseudonymeLecteur.getText().equalsIgnoreCase("")) IpseudonymeLecteur.setText("Entrer un pseudonyme..");

		//	Champs pcw - lecteur
		if(m.getSource() == Ipcw) {
			if(Ipcw.getText().equalsIgnoreCase("Entrer un password..")) Ipcw.setText("");
			Ipcw.requestFocus();
		}
		if(m.getSource() != Ipcw) if(Ipcw.getText().equalsIgnoreCase("")) Ipcw.setText("Entrer un password..");

		//	Champs langue - exemplaire
		if(m.getSource() == IlettreLangue) {
			if(IlettreLangue.getText().equalsIgnoreCase("Entrer la langue..")) IlettreLangue.setText("");
			IlettreLangue.requestFocus();
		}
		if(m.getSource() != IlettreLangue) if(IlettreLangue.getText().equalsIgnoreCase("")) IlettreLangue.setText("Entrer la langue..");

		//	Champs nombre - exemplaire
		if(m.getSource() == Inombre) {
			if(Inombre.getText().equalsIgnoreCase("Entrer un nombre d'exmplaires..")) Inombre.setText("");
			Inombre.requestFocus();
		}
		if(m.getSource() != Inombre) if(Inombre.getText().equalsIgnoreCase("")) Inombre.setText("Entrer un nombre d'exmplaires..");

		//	Champs lettre_livre - exemplaire
		if(m.getSource() == IlettreLivre) {
			if(IlettreLivre.getText().equalsIgnoreCase("Entrer un titre..")) IlettreLivre.setText("");
			IlettreLivre.requestFocus();
		}
		if(m.getSource() != IlettreLivre) if(IlettreLivre.getText().equalsIgnoreCase("")) IlettreLivre.setText("Entrer un titre..");
		
		//	Champs lettre édition - exemplaire
		if(m.getSource() == IlettreEdition) {
			if(IlettreEdition.getText().equalsIgnoreCase("Entrer une édition..")) IlettreEdition.setText("");
			IlettreEdition.requestFocus();
		}
		if(m.getSource() != IlettreEdition) if(IlettreEdition.getText().equalsIgnoreCase("")) IlettreEdition.setText("Entrer une édition..");		

		//	Champs prix - exemplaire
		if(m.getSource() == Iprix) {
			if(Iprix.getText().equalsIgnoreCase("Entrer un prix..")) Iprix.setText("");
			Iprix.requestFocus();
		}
		if(m.getSource() != Iprix) if(Iprix.getText().equalsIgnoreCase("")) Iprix.setText("Entrer un prix..");

		//	Champs langue - traduction
		if(m.getSource() == Ilangue) {
			if(Ilangue.getText().equalsIgnoreCase("Entrer une langue..")) Ilangue.setText("");
			Ilangue.requestFocus();
		}
		if(m.getSource() != Ilangue) if(Ilangue.getText().equalsIgnoreCase("")) Ilangue.setText("Entrer une langue..");
	}

	/**
	 * Fonctionnalité Focus
	 */
	@Override
	public void focusGained(FocusEvent e) {
		//CHAMPS LIVRE
		if(e.getSource() == IlettreAuteur) {
			if(IlettreAuteur.getText().equalsIgnoreCase("Entrer l'auteur..")) IlettreAuteur.setText("");
			IlettreAuteur.requestFocus();
		}
		if(e.getSource() == Ititre) {
			if(Ititre.getText().equalsIgnoreCase("Entrer un titre..")) Ititre.setText("");
			Ititre.requestFocus();
		}
		if(e.getSource() == IDescription) {
			if(IDescription.getText().equalsIgnoreCase("Entrer une description..")) IDescription.setText("");
			IDescription.requestFocus();
		}
		if(e.getSource() == ItypeLivre) {
			if(ItypeLivre.getText().equalsIgnoreCase("Entrer un type de livre..")) ItypeLivre.setText("");
			ItypeLivre.requestFocus();
		}
		//CHAMPS auteur
		if(e.getSource() == InomAuteur) {
			if(IlettreAuteur.getText().equalsIgnoreCase("Entrer un nom..")) InomAuteur.setText("");
			InomAuteur.requestFocus();
		}
		if(e.getSource() == IprenomAuteur) {
			if(IprenomAuteur.getText().equalsIgnoreCase("Entrer un prénom..")) IprenomAuteur.setText("");
			IprenomAuteur.requestFocus();
		}
		if(e.getSource() == IpseudoAuteur) {
			if(IpseudoAuteur.getText().equalsIgnoreCase("Entrer un pseudonyme..")) IpseudoAuteur.setText("");
			IpseudoAuteur.requestFocus();
		}
		//CHAMPS édition
		if(e.getSource() == Imention) {
			if(Imention.getText().equalsIgnoreCase("Entrer une mention..")) Imention.setText("");
			Imention.requestFocus();
		}
		//CHAMPS lecteur
		if(e.getSource() == InomLecteur) {
			if(InomLecteur.getText().equalsIgnoreCase("Entrer un nom..")) InomLecteur.setText("");
			InomLecteur.requestFocus();
		}
		if(e.getSource() == IprenomLecteur) {
			if(IprenomLecteur.getText().equalsIgnoreCase("Entrer un prénom..")) IprenomLecteur.setText("");
			IprenomLecteur.requestFocus();
		}
		if(e.getSource() == IpseudonymeLecteur) {
			if(IpseudonymeLecteur.getText().equalsIgnoreCase("Entrer un pseudonyme..")) IpseudonymeLecteur.setText("");
			IpseudonymeLecteur.requestFocus();
		}
		if(e.getSource() == Ipcw) {
			if(Ipcw.getText().equalsIgnoreCase("Entrer un password..")) Ipcw.setText("");
			Ipcw.requestFocus();
		}
		if(e.getSource() == IlettreLangue) {
			if(IlettreLangue.getText().equalsIgnoreCase("Entrer la langue..")) IlettreLangue.setText("");
			IlettreLangue.requestFocus();
		}
		if(e.getSource() == Inombre) {
			if(Inombre.getText().equalsIgnoreCase("Entrer un nombre d'exemplaires..")) Inombre.setText("");
			Inombre.requestFocus();
		}
		if(e.getSource() == IlettreLivre) {
			if(IlettreLivre.getText().equalsIgnoreCase("Entrer le titre..")) IlettreLivre.setText("");
			IlettreLivre.requestFocus();
		}
		if(e.getSource() == IlettreEdition) {
			if(IlettreEdition.getText().equalsIgnoreCase("Entrer l'éditeur..")) IlettreEdition.setText("");
			IlettreEdition.requestFocus();
		}
		if(e.getSource() == Iprix) {
			if(Iprix.getText().equalsIgnoreCase("Entrer un prix..")) Iprix.setText("");
			Iprix.requestFocus();
		}
		if(e.getSource() == Ilangue) {
			if(Ilangue.getText().equalsIgnoreCase("Entrer une langue..")) Ilangue.setText("");
			Ilangue.requestFocus();
		}
	}

	/**
	 * Focus perdu
	 */
	@Override
	public void focusLost(FocusEvent e) {
		//	Champs livre
		if(e.getSource() == IlettreAuteur) if(IlettreAuteur.getText().equalsIgnoreCase("")) IlettreAuteur.setText("Entrer l'auteur..");
		if(e.getSource() == Ititre) if(Ititre.getText().equalsIgnoreCase("")) Ititre.setText("Entrer un titre..");
		if(e.getSource() == IDescription) if(IDescription.getText().equalsIgnoreCase("")) IDescription.setText("Entrer une description..");
		if(e.getSource() == ItypeLivre) if(ItypeLivre.getText().equalsIgnoreCase("")) ItypeLivre.setText("Entrer un type de livre..");
		//	Champs auteur
		if(e.getSource() == InomAuteur) if(InomAuteur.getText().equalsIgnoreCase("")) InomAuteur.setText("Entrer un nom..");
		if(e.getSource() == IprenomAuteur) if(IprenomAuteur.getText().equalsIgnoreCase("")) IprenomAuteur.setText("Entrer un prénom..");
		if(e.getSource() == IpseudoAuteur) if(IpseudoAuteur.getText().equalsIgnoreCase("")) IpseudoAuteur.setText("Entrer un pseudonyme..");
		//	Champs édition
		if(e.getSource() == Imention) if(Imention.getText().equalsIgnoreCase("")) Imention.setText("Entrer une mention..");
		//	Champs lecteur / client
		if(e.getSource() == InomLecteur) if(InomLecteur.getText().equalsIgnoreCase("")) InomLecteur.setText("Entrer un nom..");
		if(e.getSource() == IprenomLecteur) if(IprenomLecteur.getText().equalsIgnoreCase("")) IprenomLecteur.setText("Entrer un prénom..");
		if(e.getSource() == IpseudonymeLecteur) if(IpseudonymeLecteur.getText().equalsIgnoreCase("")) IpseudonymeLecteur.setText("Entrer un pseudonyme..");
		if(e.getSource() == Ipcw) if(Ipcw.getText().equalsIgnoreCase("")) Ipcw.setText("Entrer un password..");
		//	Champs exemplaire
		if(e.getSource() == Inombre) if(Inombre.getText().equalsIgnoreCase("")) Inombre.setText("Entrer un nombre d'exemplaires..");
		if(e.getSource() == IlettreLivre) if(IlettreLivre.getText().equalsIgnoreCase("")) IlettreLivre.setText("Entrer le titre..");
		if(e.getSource() == IlettreEdition) if(IlettreEdition.getText().equalsIgnoreCase("")) IlettreEdition.setText("Entrer l'éditeur..");
		if(e.getSource() == IlettreLangue) if(IlettreLangue.getText().equalsIgnoreCase("")) IlettreLangue.setText("Entrer la langue..");
		if(e.getSource() == Iprix) if(Iprix.getText().equalsIgnoreCase("")) Iprix.setText("Entrer un prix..");
		if(e.getSource() == Ilangue) if(Ilangue.getText().equalsIgnoreCase("")) Ilangue.setText("Entrer une langue..");
	}

	@Override
	public void changedUpdate(DocumentEvent e) {}

	@Override
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

	@Override
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