/**
 * @className CommunauteAgglomeration
 * @description : La classe permet de modéliser et gérer 
 * 				les relations entre différentes villes au sein d'une 
 * 				agglomération, en utilisant une liste d'adjacence pour 
 * 				définir les connexions routières.
 * @date 20/12/2023
 * @version 2.0
 * @author CHAKER, HAMOUCHI, HULKHOREE
 */

package projet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CommunauteAgglomeration {

	private List<Ville> villes;
	private Map<Ville, List<Ville>> adjacence;

	public CommunauteAgglomeration() {
		this.adjacence = new HashMap<>();
		this.villes = new ArrayList<>();
	}

	/**
	 * @description : Méthode de récupération en lecture de la liste d'adjacence
	 * 
	 * @return : liste adjacence pour les villes
	 * 
	 */
	public Map<Ville, List<Ville>> getAdjacence() {
		return adjacence;
	}

	/**
	 * @description : Méthode de récuperation en ecriture de la liste d'adjacence
	 * 
	 * @param : adjacence
	 */
	public void setAdjacence(Map<Ville, List<Ville>> adjacence) {
		this.adjacence = adjacence;
	}

	/**
	 * @description : Méthode d'ajout dans la liste d'adjacence
	 * 
	 * @param : Ville
	 */
	public void ajouterVille(Ville ville) throws IllegalArgumentException {
		if (ville == null) {
			throw new IllegalArgumentException("La ville fournie est null.");
		}
		try {
			villes.add(ville);
		} catch (Exception e) {
			System.err.println("Erreur lors de l'ajout de la ville : " + e.getMessage());
		}
	}

	/**
	 * @description : Méthode qui affiche la liste des villes dans la CA
	 * 
	 */
	public void afficherVille() {
		if (villes == null || villes.isEmpty()) {
			throw new IllegalStateException("La liste de villes est vide.");
		}

		List<String> nomsVilles = new ArrayList<>();
		Set<String> villesAffichees = new HashSet<>();

		for (Ville ville : villes) {
			if (!villesAffichees.contains(ville.getNom())) {
				nomsVilles.add(ville.getNom());
				villesAffichees.add(ville.getNom());
			}
		}

		StringBuffer buffer = new StringBuffer();
		int totalVilles = nomsVilles.size();
		for (int i = 0; i < totalVilles; i++) {
			if (i > 0) {
				if (i == totalVilles - 1 && totalVilles > 1) {
					buffer.append(" et ");
				} else {
					buffer.append(", ");
				}
			}
			buffer.append(nomsVilles.get(i));
		}

		System.out.println(buffer.toString() + ".");
	}

	/**
	 * @description : Méthode d'ajout d'une route entre deux villes
	 * 
	 * @param villeDepart  : Ville de départ de la route
	 * 
	 * @param villeArrivee : Ville d'arrivée de la route
	 */
	public void ajouterRoute(Ville villeDepart, Ville villeArrivee) {
		if (villeDepart == null || villeArrivee == null) {
			throw new IllegalArgumentException("Les villes spécifiées n'existent pas.");
		}

		if (!villes.contains(villeDepart) || !villes.contains(villeArrivee)) {
			throw new IllegalArgumentException("Les villes spécifiées ne sont pas présentes dans la liste des villes.");
		}

		if (villeDepart.equals(villeArrivee)) {
			throw new IllegalArgumentException("Impossible d'ajouter une route de la ville à elle-même.");
		}

		if (adjacence == null) {
			throw new IllegalStateException("La liste d'adjacence n'est pas correctement initialisée.");
		}
		if (!adjacence.containsKey(villeDepart)) {
			adjacence.put(villeDepart, new ArrayList<>());
		}
		adjacence.get(villeDepart).add(villeArrivee);
		System.out
				.println("La route " + villeDepart.getNom() + " et " + villeArrivee.getNom() + " ont bien été crées.");

	}

	/**
	 * @description : Méthode d'affichage pour la Liste d'Adjacence
	 */
	public void afficherListeAdjacence() {
		for (Ville ville : adjacence.keySet()) {
			List<Ville> villesConnectees = adjacence.get(ville);

			if (ville != null && villesConnectees != null) {
				System.out.print(ville.getNom() + " est reliée à : ");
				for (Ville villeConnectee : villesConnectees) {
					if (villeConnectee != null) {
						System.out.print(villeConnectee.getNom() + ", ");
					}
				}
				System.out.println();
			} else {
				System.out.println("Impossible d'afficher la liste d'adjacence. Veuillez vérifier les données.");
			}
		}
	}

	/**
	 * @description : Méthode qui permet de créer des Villes par l'utilisateur
	 * 
	 * @param scanner
	 */
	public void creerVilles(Scanner scanner) {
		int nbVilles;
		do {
			System.out.println("Veuillez entrer le nombre de villes à créer :");
			nbVilles = scanner.nextInt();
		} while (nbVilles <= 0);

		scanner.nextLine();

		for (int i = 1; i <= nbVilles; i++) {
			System.out.println("Entrez le nom de la ville " + i + " :");
			String nomVille;
			do {
				nomVille = scanner.nextLine().trim();
				if (nomVille.isEmpty()) {
					System.out.println("Le nom de la ville ne peut pas être vide. Réessayez :");
				}
			} while (nomVille.isEmpty());
			Ville ville = new Ville(false, nomVille);
			villes.add(ville);
		}
	}

	/**
	 * @description : Méthode privée pour trouver une ville
	 *
	 * @param nomVille : Nom de la ville à rechercher
	 * @return : Ville correspondante ou null si non trouvée
	 */
	private Ville trouverVille(String nomVille) {
		if (nomVille == null || nomVille.isEmpty()) {
			return null;
		}

		for (Ville ville : villes) {
			if (ville.getNom().equals(nomVille)) {
				return ville;
			}
		}
		return null;
	}

	/**
	 * @description : Methode permet de trouver l'indice d'une ville dans la liste
	 *              des villes.
	 * 
	 * @param listeVilles  : La liste des villes.
	 * @param nomRecherche : Le nom de la ville à rechercher.
	 * 
	 * @return L'indice de la ville dans la liste, ou -1 si la ville n'est pas
	 *         trouvée.
	 */
	public int trouverIndiceVille(List<Ville> listeVilles, String nomRecherche) {
		for (int i = 0; i < listeVilles.size(); i++) {
			if (listeVilles.get(i).getNom().equals(nomRecherche)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @description : Méthode pour vérifier si la route entre deux villes existe
	 *              déjà.
	 * 
	 * @param ville1 Première ville
	 * @param ville2 Deuxième ville
	 * @return true si la route existe déjà, false sinon
	 */
	public boolean routeExisteDeja(Ville ville1, Ville ville2) {
		List<Ville> villesConnectees = adjacence.getOrDefault(ville1, new ArrayList<>());
		return villesConnectees.contains(ville2);
	}

	/**
	 * @description: Méthode pour ajouter une route entre les villes
	 * 
	 * @param scanner : Scanner pour la saisie utilisateur
	 */

	public void ajouterRoute(Scanner scanner) {
		System.out.println("Entrez le nom de la première ville : ");
		String nomVille1 = scanner.next();

		Ville ville1 = trouverVille(nomVille1);
		if (nomVille1 == null) {
			System.out.println("La ville " + ville1 + " n'existe pas dans la communauté.");
			return;
		}

		System.out.println("Entrez le nom de la deuxième ville : ");
		String nomVille2 = scanner.next();

		Ville ville2 = trouverVille(nomVille2);

		if (nomVille2 == null) {
			System.out.println("La ville " + ville2 + " n'existe pas dans la communauté.");
			return;
		}

		// Vérifier si la route existe déjà
		if (routeExisteDeja(ville1, ville2)) {
			System.out.println("La route entre " + ville1.getNom() + " et " + ville2.getNom() + " existe déjà.");
			return;
		}

		ajouterRoute(ville1, ville2);
		ajouterRoute(ville2, ville1);
	}

	/**
	 * @description : Méthode permet de gerer les routes en utilisant un menu
	 * @param scanner
	 */
	public void gererRoutes(Scanner scanner) {
		int choix = 0;
		int cpt = 0;

		while (choix != 2) {
			try {
				if (cpt >= 0) {
					System.out.println("\nMenu pour la gestion des routes entre les villes: ");
					System.out.println("1. Ajouter une route");
					System.out.println("2. Fin");
					System.out.println("Veuillez saisir votre choix : ");
					choix = scanner.nextInt();
					scanner.nextLine();
				} else {
					choix = 1;
					cpt++;
				}

				switch (choix) {
				case 1:
					ajouterRoute(scanner);
					System.out.println();
					break;
				case 2:
					System.out.println(
							"Vous avez terminé votre communauté d'agglomération et vous avez quitté le programme.");
					System.out.println("\nListe des villes: ");
					afficherVille();
					System.out.println("\nListe d'adjacence: ");
					afficherListeAdjacence();
					System.out.println();
					break;
				default:
					System.out.println("Veuillez saisir soit 1 ou 2 pour votre choix.");
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Veuillez saisir un choix valide (entier).");
				scanner.next();
				choix = 0;
			}
		}
	}

	/**
	 * @description : Méthode d'ajout d'une zone de recharge
	 * 
	 * @param : ville
	 */
	public void ajouterZoneRecharge(Ville ville) {
		if (ville == null) {
			throw new IllegalArgumentException("La ville spécifiée n'existe pas.");
		}
		if (!villes.contains(ville)) {
			throw new IllegalArgumentException("La ville spécifiée n'est pas présente dans la liste des villes.");
		}

		boolean villeAccessible = false;
		for (Ville v : adjacence.keySet()) {
			if (adjacence.get(v).contains(ville)) {
				villeAccessible = true;
				break;
			}
		}

		if (!villeAccessible) {
			throw new IllegalArgumentException(
					"La ville spécifiée n'est pas accessible depuis une autre ville. Impossible d'ajouter une zone de recharge.");
		}

		ville.setZonesRecharge(true);

	}

	/**
	 * @description Méthode qui demande à l'utilisateur de confirmer son choix pour
	 *              une ville spécifique
	 * @param scanner
	 * @param nomVille
	 * @return true si l'utilisateur ecrit oui sinon false
	 */
	public boolean verificationUtilisateurVille(Scanner scanner, String nomVille) {
		boolean confirmation = false;
		String reponse;

		System.out.println("Confirmez votre choix pour : '" + nomVille + "'");
		System.out.println("o : OK");
		System.out.println("n/autre : Non");
		System.out.println("Votre choix : ");
		reponse = scanner.next();

		if (reponse.equalsIgnoreCase("o")) {
			confirmation = true;
		}

		return confirmation;
	}

	/**
	 * @description : méthode qui permet d'ajouter une zone de recharge pour une
	 *              ville specifiee par l'utilisateur
	 * @param scanner : utilisé pour lire l'entrée de l'utilisateur
	 */
	public void ajouterZoneRecharge(Scanner scanner) {
		boolean verif = false;
		int cpt = 0;
		StringBuffer nomVilleAjout = new StringBuffer();

		while (!verif) {
			System.out.println(((cpt > 0) ? "Entrez à nouveau le nom de la ville pour ajouter une zone de recharge : "
					: "Entrez le nom de la ville pour ajouter une zone de recharge : "));
			nomVilleAjout.setLength(0);
			nomVilleAjout.append(scanner.next());
			verif = verificationUtilisateurVille(scanner, nomVilleAjout.toString());
			cpt++;
		}

		if (nomVilleAjout != null && !nomVilleAjout.isEmpty()) {
			Ville villeAjout = trouverVille(nomVilleAjout.toString());

			if (villeAjout != null) {
				if (estZoneDeRecharge(villeAjout)) {
					System.out.println("La ville " + nomVilleAjout + " possède déjà une zone de recharge.");
				} else {
					if (verifierContrainteAccessibilitePourVille(villeAjout)) {
						ajouterZoneRecharge(villeAjout);
						System.out.println("Zone de recharge ajoutée avec succès à la ville " + nomVilleAjout);
					} else {
						System.out.println("L'ajout de la zone de recharge à la ville " + nomVilleAjout
								+ " ne respecte pas la contrainte d'accessibilité.");
					}
				}
			} else {
				System.out.println("La ville " + nomVilleAjout + " n'existe pas dans la communauté.");
			}
		} else {
			System.out.println("Le nom de la ville entré n'est pas conforme.");
		}
	}

	/**
	 * @description : Méthode d'affichage de la liste des zones de recharge
	 */
	public void afficherVillesAvecZonesRecharge() {
		System.out.println("--------------------------------------");
		for (Ville ville : villes) {
			if (estZoneDeRecharge(ville)) {
				System.out.println("La ville " + ville.getNom() + " possède une zone de recharge");
			} else {
				System.out.println("La ville " + ville.getNom() + " ne possède pas de zone de recharge");
			}
		}
		System.out.println("--------------------------------------");
	}

	/**
	 * @description Méthode qui permet de retirer une zone de recharge pour une
	 *              ville spécifiée par l'utilisateur. Elle vérifie si le nom de
	 *              ville n'est ni nul ni vide. Elle trouve la ville correspondante
	 *              et verifie si elle possède une zone de recharge Elle verifie
	 *              egalement si la suppression de la zone de recharge respecte les
	 *              contraintes d'accessibilité pour la ville.
	 * @param scanner
	 */
	public void retirerZoneRecharge(Scanner scanner) {
		boolean verif = false;
		int cpt = 0;
		StringBuffer nomVilleSuppr = new StringBuffer();
		while (!verif) {
			System.out.println(
					((cpt > 0) ? "À nouveau e" : "E") + "ntrez le nom de la ville pour retirer la zone de recharge: ");
			nomVilleSuppr.append(scanner.next());
			verif = verificationUtilisateurVille(scanner, nomVilleSuppr.toString());
			if (!verif)
				nomVilleSuppr.setLength(0);
		}

		if (nomVilleSuppr.toString() != null && !nomVilleSuppr.isEmpty()) {
			Ville villeSuppr = trouverVille(nomVilleSuppr.toString());

			if (villeSuppr != null) {
				if (!estZoneDeRecharge(villeSuppr)) {
					System.out.println("La ville " + villeSuppr.getNom() + " ne possède pas de zone de recharge.");
				} else {
					retirerZoneRecharge(villeSuppr);

					List<Ville> villesConnectees = adjacence.get(villeSuppr);
					boolean autreZoneRechargeTrouvee = false;

					if (villesConnectees != null) {
						for (Ville villeConnectee : villesConnectees) {
							if (estZoneDeRecharge(villeConnectee)) {
								autreZoneRechargeTrouvee = true;
								List<Ville> villesConnecteesConnectee = adjacence.get(villeConnectee);
								boolean zoneRechargeConnecteeTrouvee = false;

								if (villesConnecteesConnectee != null) {
									for (Ville autreVilleConnectee : villesConnecteesConnectee) {
										if (estZoneDeRecharge(autreVilleConnectee)) {
											zoneRechargeConnecteeTrouvee = true;
											break;
										}
									}
								}

								if (!zoneRechargeConnecteeTrouvee) {
									System.out.println("La suppression de cette zone de recharge n'est pas permise car "
											+ "elle est reliée à une zone de recharge qui n'a pas d'autre zone de recharge connectée.");
									System.out.println("La zone de recharge de la ville " + villeSuppr.getNom()
											+ " a donc été rétablie.");
									villeSuppr.ajouterZoneRecharge();
									return;
								}
							}
						}

						if (!autreZoneRechargeTrouvee) {
							villeSuppr.ajouterZoneRecharge();
							System.out.println("La suppression de cette zone de recharge n'est pas permise car "
									+ "elle ne possède aucune autre zone de recharge connectée.");
						} else {
							retirerZoneRecharge(villeSuppr);
							System.out
									.println("Zone de recharge retirée avec succès de la ville " + villeSuppr.getNom());
						}
					}
				}
			} else {
				System.out.println("La ville spécifiée n'existe pas dans la communauté.");
			}
		}
	}

	/**
	 * @description : Méthode de suppression d'une zone de recharge
	 * 
	 * @param : ville la ville pour laquelle la zone de recharge doit être retirée.
	 */
	public void retirerZoneRecharge(Ville ville) {
		if (ville == null) {
			throw new IllegalArgumentException("La ville n'est pas conforme.");
		}

		if (!villes.contains(ville)) {
			throw new IllegalArgumentException("La ville spécifiée n'est pas présente dans la liste des villes.");
		}

		if (ville != null && (estZoneDeRecharge(ville))) {
			if (verifierContrainteAccessibiliteApresRetrait(ville)) {
				ville.retirerZoneRecharge();
			}
		}

	}

	/**
	 * @description Méthode pour vérifier si la suppression d'une zone de recharge
	 *              enfreint la contrainte d'accessibilité après avoir retiré la
	 *              zone de recharge d'une ville.
	 * 
	 * @param ville Ville pour laquelle on vérifie la contrainte après retrait de la
	 *              zone de recharge
	 * @return true si la contrainte est respectée après retrait, false sinon
	 */
	public boolean verifierContrainteAccessibiliteApresRetrait(Ville ville) {
		if (ville == null) {
			throw new IllegalArgumentException("La ville passée en paramètre n'est pas conforme.");
		}

		boolean contrainteRespectee;
		if (estZoneDeRecharge(ville)) {
			ville.retirerZoneRecharge();
			contrainteRespectee = verifierContrainteAccessibilitePourVille(ville);
			ville.ajouterZoneRecharge();
		} else {
			contrainteRespectee = false;
		}
		return contrainteRespectee;
	}

	/**
	 * @description : Méthode qui verifie la contrainte pour toutes les villes
	 * @return false si contrainte pas respectee sinon true
	 */

	public boolean verifierContrainteAccessibilite() {
		boolean contrainteVerif;
		for (Ville ville : villes) {
			{
				contrainteVerif = verifierContrainteAccessibilitePourVille(ville);
				return contrainteVerif;
			}
		}
		return false;
	}

	/**
	 * @description : Méthode qui vérifie la contrainte d'accessibilité pour chaque
	 *              ville
	 * @param ville
	 * @return false si la contrainte n'est pas respectée ou true s'il est respectée
	 * @throws IllegalArgumentException si la ville est null
	 */
	public boolean verifierContrainteAccessibilitePourVille(Ville ville) {
		if (ville == null) {
			throw new IllegalArgumentException("La ville n'est pas conforme.");
		}
		if (!villes.contains(ville)) {
			throw new IllegalArgumentException("La ville spécifiée n'existe pas.");
		}

		return estZoneDeRecharge(ville) || estRelieeAZoneDeRecharge(ville);
	}

	/**
	 * @description : Méthode qui permet de dire si une ville specifié est une zone
	 *              de recharge
	 * @param ville
	 * @return true si la ville est une zone de recharge, sinon false
	 * @throws IllegalArgumentException si la ville est null
	 */
	private boolean estZoneDeRecharge(Ville ville) {
		if (ville == null) {
			throw new IllegalArgumentException("La ville est invalide.");
		}
		return ville.getZonesRecharge();
	}

	/**
	 * 
	 * @description : Méthode qui permet de dire la ville specifiée est reliée à une
	 *              autre dans la CA
	 * @param ville
	 * @param villesVisitees
	 * @return true si la ville est reliée à une zone de recharge, sinon false
	 * @throws IllegalArgumentException si la ville est null
	 */
	private boolean estRelieeAZoneDeRecharge(Ville ville, Set<Ville> villesVisitees) {
		if (ville == null) {
			throw new IllegalArgumentException("La ville est invalide.");
		}

		if (villesVisitees.contains(ville)) {
			return false;
		}

		villesVisitees.add(ville);

		try {
			List<Ville> villesAdjacentes = adjacence.get(ville);
			if (villesAdjacentes != null) {
				for (Ville adjacente : villesAdjacentes) {
					try {
						if (estZoneDeRecharge(adjacente) || estRelieeAZoneDeRecharge(adjacente, villesVisitees)) {
							return true;
						}
					} catch (Exception e) {
						System.err.println("Une exception s'est produite : " + e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Une exception s'est produite : " + e.getMessage());
		}

		return false;
	}

	/**
	 * @description : Méthode qui retourne vrai si la ville specifiée est reliée à
	 *              une zone de recharge
	 * @param ville
	 * @return true si elle est reliée sinon false
	 */
	public boolean estRelieeAZoneDeRecharge(Ville ville) {
		return estRelieeAZoneDeRecharge(ville, new HashSet<>());
	}

	/**
	 * @description : Methode qui calcule le score à savoir le nombre total de zones
	 *              de recharge ici
	 * 
	 * @return : nombre total de zones de recharge nécessaire
	 */
	public int calculerScore() {
		int score = 0;

		if (villes != null) {
			for (Ville ville : villes) {
				if (estZoneDeRecharge(ville)) {
					score++;
				}
			}
		}
		return score;
	}

	/**
	 * @description : Méthode qui donne une approximation pour la contrainte
	 *              d'economie
	 * @param nbLancement
	 */
	public void algorithmeContrainteEconomie(int nbLancement) {
		int i = 0;
		int scoreCourant = calculerScore();

		while (i < nbLancement) {
			try {
				Random random = new Random();
				int indexVilleAleatoire = random.nextInt(villes.size());
				Ville villeAleatoire = villes.get(indexVilleAleatoire);

				if (villeAleatoire.getZonesRecharge()) {
					villeAleatoire.retirerZoneRecharge();
				} else {
					ajouterZoneRecharge(villeAleatoire);
				}
				int nouveauScore = calculerScore();

				if (nouveauScore < scoreCourant) {
					i = 0;
					scoreCourant = nouveauScore;
				} else {
					i++;
				}
			} catch (IllegalStateException | IllegalArgumentException ex) {
				System.err.println("La contrainte d'économie n'a pas pu aboutir" + ex.getMessage());
			}
		}
	}

	/**
	 * @description : Méthode permet de gerer les zones de recharge en utilisant un
	 *              menu
	 * 
	 * @param scanner
	 */
	public void gererZonesRecharge(Scanner scanner) {
		int choixZonesRecharge;
		do {
			// Menu pour la gestion des zones de recharge
			System.out.println("______________________________________________________");
			System.out.println("\nMenu pour la gestion des zones de recharge :");
			System.out.println("1) Ajouter une zone de recharge");
			System.out.println("2) Retirer une zone de recharge");
			System.out.println("3) Fin");
			System.out.println("Choix : ");

			// Vérification de la saisie utilisateur
			while (!scanner.hasNextInt()) {
				System.out.println("Veuillez saisir un nombre correspondant à votre choix.");
				scanner.next();
			}
			choixZonesRecharge = scanner.nextInt();
			scanner.nextLine();

			switch (choixZonesRecharge) {
			case 1:
				ajouterZoneRecharge(scanner);
				afficherVillesAvecZonesRecharge();
				System.out.println();
				break;
			case 2:
				retirerZoneRecharge(scanner);
				afficherVillesAvecZonesRecharge();
				System.out.println();
				break;
			case 3:
				afficherVillesAvecZonesRecharge();
				System.out.println("Vous avez terminé de gérer les zones de recharge. \n");
				break;
			default:
				System.out.println(
						"Nous ne comprenons pas le choix. Faites une autre tentative en tapant soit 1, 2 ou 3.");
				break;
			}
		} while (choixZonesRecharge != 3);
	}

	/**
	 * @description : Méthode qui lit un fichier spécifié pour initialiser les
	 *              villes et les route entre elles.
	 * 
	 * @param cheminFichier : Le chemin du fichier à lire.
	 */
	public void lireFichier(String cheminFichier) {
		boolean fichierValide = true;
		try {
			BufferedReader lecteur = new BufferedReader(new FileReader(new File(cheminFichier)));
			String ligne;

			List<String> villesAvecRecharge = new ArrayList<>();
			boolean zonesRechargeExistent = false;

			while ((ligne = lecteur.readLine()) != null) {
				if (ligne.contains("ville")) {
					String[] parties = ligne.split("\\("); // split avec le delimiteur '(' dans la ligne
					String nomVille = parties[1].split("\\)")[0]; // split avec le delimiteur ')' dans la ligne
					Ville uneVille = new Ville(true, nomVille);
					ajouterVille(uneVille);
					System.out.println("Nom de la ville : " + nomVille);
				}

				if (ligne.contains("route")) {
					String[] parties = ligne.split("[()]");
					String lesvilles = parties[1];
					String ville1 = lesvilles.split(",")[0];
					String ville2 = lesvilles.split(",")[1];

					int index1 = trouverIndiceVille(this.villes, ville1);
					int index2 = trouverIndiceVille(this.villes, ville2);
					ajouterRoute(villes.get(index1), villes.get(index2));
					ajouterRoute(villes.get(index2), villes.get(index1));

				}
				if (ligne.contains("recharge")) {
					String[] parties = ligne.split("\\(");
					String nomRecharge = parties[1].split("\\)")[0];
					if (nomRecharge.matches("[a-zA-Z\\s-]+")) {
						nomRecharge = nomRecharge.trim();
						Ville villeRecharge = trouverVille(nomRecharge);

						if (villeRecharge != null) {
							ajouterZoneRecharge(villeRecharge);
							zonesRechargeExistent = true;
							villesAvecRecharge.add(nomRecharge);
							System.out.println("Zone de recharge ajoutée à la ville " + nomRecharge);
							for (Ville ville : villes) {
								String nomVille = ville.getNom();
								if (!villesAvecRecharge.contains(nomVille) && estZoneDeRecharge(ville)) {
									ville.retirerZoneRecharge();
								}

							}
						} else {
							System.out.println("La ville " + nomRecharge + " n'existe pas dans la communauté.");
						}
					} else {
						System.out.println("Le nom de la ville pour la recharge est invalide.");
					}
				}
			}

			verifierZonesRecharge(zonesRechargeExistent, villesAvecRecharge);
			lecteur.close();
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
			fichierValide = false;
		} catch (Exception e) {
			System.err.println("Une erreur inattendue est survenue : " + e.getMessage());
			fichierValide = false;
		}

	}

	/**
	 * @description : Méthode qui permet de vérifier l'existence de zones de
	 *              recharge dans le fichier. Si aucune zone de recharge n'existe
	 *              dans le fichier, initialiase la solution naïve et s'il y a des
	 *              zones de recharge dans le fichier, on verifie chaque ville et on
	 *              retire la zone de recharge si elle n'est pas présente dans la
	 *              liste (on la force)
	 * @param zonesRechargeExistent
	 * @param villesAvecRecharge
	 */
	private void verifierZonesRecharge(boolean zonesRechargeExistent, List<String> villesAvecRecharge) {
		if (!zonesRechargeExistent) {
			System.out.println(/* "--------------" */);
			System.out.println("Il n'y a aucune zone de recharge définie dans le fichier.");
			initialiserAvecSolutionNaive();
			System.out.println(/* "--------------" */);
			afficherVillesAvecZonesRecharge();
			System.out.println(/* "--------------" */);
		} else {
			for (Ville ville : villes) {
				String nomVille = ville.getNom();
				if (!villesAvecRecharge.contains(nomVille) && estZoneDeRecharge(ville)) {
					ville.retirerZoneRecharge();
				}
			}
		}
	}

	/**
	 * @description : Méthode privée qui a pour objectif d'initialiser une solution
	 *              de manière naïve en ajoutant une zone de recharge dans chaque
	 *              ville de la communauté d'agglomération.
	 * 
	 */
	private void initialiserAvecSolutionNaive() {
		for (Ville ville : villes) {
			ville.ajouterZoneRecharge();
		}
		System.out.println("--------------");
		System.out.println("Solution naïve initialisée : une zone de recharge ajoutée dans chaque ville.");
	}

	/**
	 * @description : Méthode qui permet de résoudre manuellement la gestion des
	 *              zones de recharge dans la CA
	 * 
	 * @param scanner
	 */

	public void resoudreManuellement(Scanner scanner) {
		boolean fichierTrouve = false;

		while (!fichierTrouve) {
			try {
				System.out.println("----------------------------");
				gererRoutes(scanner);

				if (villes.stream().noneMatch(Ville::getZonesRecharge)) {
					initialiserAvecSolutionNaive();

				}

				boolean zonesRechargeExistent = villes.stream().anyMatch(Ville::getZonesRecharge);

				if (!zonesRechargeExistent) {

					System.out
							.println("Aucune zone de recharge définie dans les villes. Impossible d'afficher le menu.");
					fichierTrouve = true;
				} else {
					gererZonesRecharge(scanner);
					fichierTrouve = true;
				}

			} catch (Exception e) {
				System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
				System.out.println("Voulez-vous réessayer ? (Oui/Non) : ");
				String reessayer = scanner.nextLine();
				if (!reessayer.equalsIgnoreCase("Oui")) {
					System.out.println("Fin du programme.");
					fichierTrouve = true;
				}
			}
		}

	}

	/**
	 * @description : Méthode qui retourne simplement la valeur maximale possible
	 *              d'un entier en Java.
	 * @return la constante Integer.MAX_VALUE représente la valeur maximale qu'un
	 *         entier de type int peut avoir
	 */
	public int valMax() {
		return Integer.MAX_VALUE;
	}

	/**
	 * @description : Méthode qui permet de résoudre automatiquement un problème
	 *              spécifique concernant les zones de recharge dans une communauté
	 *              d'agglomération.
	 * 
	 * @param scanner
	 */
	public void resoudreAutomatiquement(Scanner scanner) {
		boolean zonesRechargeExistent = villes.stream().anyMatch(Ville::getZonesRecharge);
		if (!zonesRechargeExistent) {
			initialiserAvecSolutionNaive();
		}
		resoudreAutomatiquement();

	}

	/**
	 * @description : Méthode qui a pour but d'effectuer une résolution automatique
	 *              à l'aide d'un algorithme qui cherche à réduire le nombre de
	 *              zones de recharge dans la communauté d'agglomération
	 * 
	 */
	public void resoudreAutomatiquement() {
		int NOMBRE_LANCEMENTS_ALGO = 10;
		int scoreM = valMax();

		try {
			if (!verifierContrainteAccessibilite()) {
				System.out.println(
						"La solution actuelle ne respecte pas les contraintes nécessaires pour exécuter l'algorithme.");
			}

			if (verifierContrainteAccessibilite()) {
				for (int i = 0; i < NOMBRE_LANCEMENTS_ALGO; i++) {
					algorithmeContrainteEconomie(NOMBRE_LANCEMENTS_ALGO);

					int val = this.calculerScore();
					if (val < scoreM) {
						scoreM = val;
					}
				}

				// Affichage du résultat
				System.out.println("\nSolution automatique calculée avec succès.\n");
				System.out.println("Valeur du score Max: \n" + scoreM + " pour " + NOMBRE_LANCEMENTS_ALGO
						+ " lancement de l'algorithme qui permet de réduire le nombre de zones de recharge\n");
			}
		} catch (Exception e) {
			System.err.println("Une erreur s'est produite : " + e.getMessage());
		}

	}

	/**
	 * @description : Vérifie l'existence d'un fichier et le crée s'il n'existe pas.
	 *              Si la création échoue, une IOException est déclenchée
	 *
	 * @param cheminFichier Chemin complet du fichier à vérifier ou créer.
	 */
	private void creerFichierSiNecessaire(String cheminFichier) {
		try {
			File file = new File(cheminFichier);

			if (!file.exists()) {
				if (!file.createNewFile()) {
					throw new IOException("Erreur lors de la création du fichier.");
				}
				System.out.println("Le fichier a été créé avec succès.");
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la création du fichier : " + e.getMessage());

		}
	}

	/**
	 * @description : Méthode qui sauvegarde la solution dans un fichier spécifié.
	 *
	 * @param cheminFichier Le chemin du fichier de sauvegarde.
	 */
	public void sauvegarderSolution(String cheminFichier) {
		creerFichierSiNecessaire(cheminFichier);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier, false))) {
			for (Ville ville : villes) {
				writer.write("ville(" + ville.getNom() + ")\n");
			}

			Set<String> pairesVillesDejaErites = new HashSet<>();

			for (Map.Entry<Ville, List<Ville>> entry : adjacence.entrySet()) {
				Ville villeSource = entry.getKey();
				List<Ville> villesReliees = entry.getValue();

				for (Ville villeCible : villesReliees) {
					String paireVilles = villeSource.getNom() + "-" + villeCible.getNom();
					String paireVillesInverse = villeCible.getNom() + "-" + villeSource.getNom();

					if (!pairesVillesDejaErites.contains(paireVilles)
							&& !pairesVillesDejaErites.contains(paireVillesInverse)) {
						writer.write("route(" + villeSource.getNom() + "," + villeCible.getNom() + ")\n");
						pairesVillesDejaErites.add(paireVilles);
						pairesVillesDejaErites.add(paireVillesInverse);
					}
				}

			}
			for (Ville ville : villes) {
				if (ville.zonesRecharge == true) {
					writer.write("recharge(" + ville.getNom() + ")\n");
				}

			}
			System.out.println("La sauvegarde a été effectuée avec succès dans le fichier : " + cheminFichier);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @description : Vérifie si le fichier spécifié est valide pour une lecture
	 *              ultérieure dans le programme.
	 * 
	 * @param cheminFichier Le chemin absolu du fichier à vérifier.
	 * @return true si le fichier existe, est un fichier et peut être lu ; sinon,
	 *         false.
	 */
	public boolean verifierFichierValide(String cheminFichier) {
		File file = new File(cheminFichier);

		if (file.exists() && file.isFile()) {
			if (file.canRead()) {
				String extension = cheminFichier.toLowerCase();
				if (extension.endsWith(".txt") || extension.endsWith(".ca") || extension.endsWith(".csv")) {
					return true;
				} else {
					System.err.println("Le fichier n'a pas l'extension attendue.");
					return false;
				}
			} else {
				System.err.println("Le fichier ne peut pas être lu.");
				return false;
			}
		} else {
			System.err.println("Le fichier n'existe pas ou n'est pas un fichier.");
			return false;
		}
	}

	/**
	 * @description : Méthode qui affiche le menu principal du programme et gère les
	 *              différentes fonctionnalités selon le choix de l'utilisateur. Ce
	 *              menu propose les options suivantes : 1) Résoudre manuellement :
	 *              permet de résoudre le problème manuellement en lisant un fichier
	 *              spécifié par l'utilisateur. 2) Résoudre automatiquement : permet
	 *              de résoudre le problème automatiquement en lisant un fichier
	 *              spécifié par l'utilisateur. 3) Sauvegarder : permet de
	 *              sauvegarder la solution actuelle dans un fichier spécifié par
	 *              l'utilisateur. 4) Fin : permet de quitter le programme.
	 * 
	 * @param scanner Le scanner pour la saisie utilisateur.
	 */
	public void afficherMenuIDE(Scanner scanner) {
		boolean continuer = true;

		while (continuer) {
			try {
				System.out.println("Menu :");
				System.out.println("1) Résoudre manuellement");
				System.out.println("2) Résoudre automatiquement");
				System.out.println("3) Sauvegarder");
				System.out.println("4) Fin");
				System.out.print("Choix : ");

				int choix = scanner.nextInt();
				scanner.nextLine();

				switch (choix) {
				case 1:
					String cheminFichierManu;
					boolean fichierManuValide = false;

					do {
						System.out.println(
								"\nEntrez le chemin du fichier pour résoudre manuellement (.txt, .ca ou .csv) : ");
						cheminFichierManu = scanner.nextLine();

						if (verifierFichierValide(cheminFichierManu)) {
							fichierManuValide = true;
						} else {
							System.out.println("Le chemin du fichier n'est pas valide. Veuillez réessayer.");
						}
					} while (!fichierManuValide);

					lireFichier(cheminFichierManu);
					resoudreManuellement(scanner);

					break;
				case 2:
					String cheminFichierAuto;
					boolean fichierAutoValide = false;

					do {
						System.out.println(
								"\nEntrez le chemin du fichier pour résoudre automatiquement (.txt, .ca ou .csv) : ");
						cheminFichierAuto = scanner.nextLine();

						if (verifierFichierValide(cheminFichierAuto)) {
							fichierAutoValide = true;
						} else {
							System.out.println("Le chemin du fichier n'est pas valide. Veuillez réessayer.");
						}
					} while (!fichierAutoValide);

					lireFichier(cheminFichierAuto);
					resoudreAutomatiquement(scanner);

					break;

				case 3:
					System.out.println("Chemin du fichier pour sauvegarder la solution : ");
					String cheminFichierSauvegarde = scanner.nextLine();
					sauvegarderSolution(cheminFichierSauvegarde);
					break;

				case 4:
					System.out.println("\nFin du programme.");
					continuer = false;
					break;
				default:
					System.out.println("Ce choix n'est pas valide. Veuillez choisir 1, 2 , 3 ou 4.");
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Erreur de saisie. Veuillez entrer un nombre.");
				scanner.nextLine();
			} catch (Exception e) {
				System.err.println("Une erreur est survenue : " + e.getMessage());
				continuer = false;
			}
		}
	}

	/**
	 * @description : Méthode qui affiche le menu principal du programme et gère les
	 *              différentes fonctionnalités selon le choix de l'utilisateur. Ce
	 *              menu propose les options suivantes : 1) Résoudre manuellement :
	 *              permet de résoudre le problème manuellement en lisant un fichier
	 *              spécifié par l'utilisateur. 2) Résoudre automatiquement : permet
	 *              de résoudre le problème automatiquement en lisant un fichier
	 *              spécifié par l'utilisateur. 3) Sauvegarder : permet de
	 *              sauvegarder la solution actuelle dans un fichier spécifié par
	 *              l'utilisateur. 4) Fin : permet de quitter le programme.
	 * 
	 * @param scanner
	 * @param chemin
	 */
	public void afficherMenu(Scanner scanner, String chemin) {
		boolean continuer = true;

		while (continuer) {
			try {
				System.out.println("Menu :");
				System.out.println("1) Résoudre manuellement");
				System.out.println("2) Résoudre automatiquement");
				System.out.println("3) Sauvegarder");
				System.out.println("4) Fin");
				System.out.print("Choix : ");

				int choix = scanner.nextInt();
				scanner.nextLine();

				switch (choix) {
				case 1:
				case 2:
					if (verifierFichierValide(chemin)) {
						lireFichier(chemin);
						if (choix == 1) {
							resoudreManuellement(scanner);
						} else {
							resoudreAutomatiquement(scanner);
						}
					} else {
						System.out.println("Le chemin du fichier n'est pas valide. Veuillez entrer un fichier sous format txt, ca ou bien csv.");
					}
					break;
				case 3:
					System.out.println("Chemin du fichier pour sauvegarder la solution : ");
					String cheminFichier1 = scanner.nextLine();
					sauvegarderSolution(cheminFichier1);
					break;
				case 4:
					System.out.println("Fin du programme.");
					continuer = false;
					break;
				default:
					System.out.println("Ce choix n'est pas valide. Veuillez choisir 1, 2 , 3 ou 4.");
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Erreur de saisie. Veuillez entrer un nombre.");
				scanner.nextLine();
			} catch (Exception e) {
				System.err.println("Une erreur est survenue : " + e.getMessage());
				continuer = false;
			}
		}
	}

}
