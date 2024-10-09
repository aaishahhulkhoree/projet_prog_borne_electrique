/**
 * @className Main
 * @description :  La classe principale du programme 
 * 				de configuration des zones de recharge au sein d'une 
 * 				communauté d'agglomération, permettant à l'utilisateur 
 * 				de définir les connexions entre les villes, d'ajouter ou 
 * 				de retirer des zones de recharge, tout en affichant 
 * 				les informations relatives à cette configuration.
 * @date 25/11/2023
 * @author CHAKER, HAMOUCHI, HULKHOREE
 */

package projet;

import java.util.Scanner;

/**
 * Classe principale du programme de configuration des zones de recharge dans
 * une communauté d'agglomération. Ce programme permet à l'utilisateur de
 * configurer les zones de recharge en utilisant un menu interactif.
 * L'utilisateur peut sélectionner différentes options à partir du menu affiché
 * via la console.
 */

public class Main {

	/**
	 * Méthode principale du programme. Elle initialise le logiciel et affiche le
	 * menu principal pour gérer les zones de recharge.
	 * 
	 * @param args Les arguments de la ligne de commande.
	 */

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Veuillez spécifier le chemin du fichier en argument de la ligne de commande.");
			return;
		}

		String cheminFichier = args[0];

		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Bienvenue dans le programme de configuration des zones de recharge.");

			CommunauteAgglomeration ca = new CommunauteAgglomeration();
			ca.afficherMenu(scanner, cheminFichier);
		} catch (Exception e) {
			System.err.println("Une erreur est survenue : " + e.getMessage());
		}
	}
}
