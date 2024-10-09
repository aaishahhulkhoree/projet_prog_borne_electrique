/**
 * @className Main
 * @description :  La classe principale du programme 
 * 				de configuration des zones de recharge au sein d'une 
 * 				communaut� d'agglom�ration, permettant � l'utilisateur 
 * 				de d�finir les connexions entre les villes, d'ajouter ou 
 * 				de retirer des zones de recharge, tout en affichant 
 * 				les informations relatives � cette configuration.
 * @date 25/11/2023
 * @author CHAKER, HAMOUCHI, HULKHOREE
 */

package projet;

import java.util.Scanner;

/**
 * Classe principale du programme de configuration des zones de recharge dans
 * une communaut� d'agglom�ration. Ce programme permet � l'utilisateur de
 * configurer les zones de recharge en utilisant un menu interactif.
 * L'utilisateur peut s�lectionner diff�rentes options � partir du menu affich�
 * via la console.
 */

public class Main {

	/**
	 * M�thode principale du programme. Elle initialise le logiciel et affiche le
	 * menu principal pour g�rer les zones de recharge.
	 * 
	 * @param args Les arguments de la ligne de commande.
	 */

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Veuillez sp�cifier le chemin du fichier en argument de la ligne de commande.");
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
