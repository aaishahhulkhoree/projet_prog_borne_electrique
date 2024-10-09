/**
 * @className Route
 * @description : La classe permet de représenter une route reliant deux villes.
 *                Elle contient les informations sur le nom de la route et les villes
 *                associées à cette route.
 * @date 26/10/2023
 * @author CHAKER, HAMOUCHI, HULKHOREE
 */
package projet;

public class Route {
    // attributs
    private String nom;
    private Ville villeA;
    private Ville villeB;

    /**
     * Constructeur pour initialiser une route avec un nom.
     * @param nom Le nom de la route.
     */
    public Route(String nom) {
        this.nom = nom;
    }

    /**
     * Constructeur pour initialiser une route avec deux villes.
     * @param villeA La première ville connectée à cette route.
     * @param villeB La deuxième ville connectée à cette route.
     */
    public Route(Ville villeA, Ville villeB) {
        this.setVilleA(villeA);
        this.setVilleB(villeB);
    }

    /**
     * Méthode de récupération du nom de la route en lecture.
     * @return Le nom de la route.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode de récupération de la première ville en lecture.
     * @return La première ville connectée à cette route.
     */
    public Ville getVilleA() {
        return villeA;
    }

    /**
     * Méthode de modification de la première ville connectée à cette route.
     * @param villeA La première ville à associer à cette route.
     */
    public void setVilleA(Ville villeA) {
        this.villeA = villeA;
    }

    /**
     * Méthode de récupération de la deuxième ville en lecture.
     * @return La deuxième ville connectée à cette route.
     */
    public Ville getVilleB() {
        return villeB;
    }

    /**
     * Méthode de modification de la deuxième ville connectée à cette route.
     * @param villeB La deuxième ville à associer à cette route.
     */
    public void setVilleB(Ville villeB) {
        this.villeB = villeB;
    }

    @Override
    /**
     * Redéfinition de la méthode toString pour obtenir une représentation textuelle de la route.
     * @return Une représentation textuelle de la route.
     */
    public String toString() {
        return "Route : " + nom;
    }
}
