/**
 * @className Route
 * @description : La classe permet de repr�senter une route reliant deux villes.
 *                Elle contient les informations sur le nom de la route et les villes
 *                associ�es � cette route.
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
     * @param villeA La premi�re ville connect�e � cette route.
     * @param villeB La deuxi�me ville connect�e � cette route.
     */
    public Route(Ville villeA, Ville villeB) {
        this.setVilleA(villeA);
        this.setVilleB(villeB);
    }

    /**
     * M�thode de r�cup�ration du nom de la route en lecture.
     * @return Le nom de la route.
     */
    public String getNom() {
        return nom;
    }

    /**
     * M�thode de r�cup�ration de la premi�re ville en lecture.
     * @return La premi�re ville connect�e � cette route.
     */
    public Ville getVilleA() {
        return villeA;
    }

    /**
     * M�thode de modification de la premi�re ville connect�e � cette route.
     * @param villeA La premi�re ville � associer � cette route.
     */
    public void setVilleA(Ville villeA) {
        this.villeA = villeA;
    }

    /**
     * M�thode de r�cup�ration de la deuxi�me ville en lecture.
     * @return La deuxi�me ville connect�e � cette route.
     */
    public Ville getVilleB() {
        return villeB;
    }

    /**
     * M�thode de modification de la deuxi�me ville connect�e � cette route.
     * @param villeB La deuxi�me ville � associer � cette route.
     */
    public void setVilleB(Ville villeB) {
        this.villeB = villeB;
    }

    @Override
    /**
     * Red�finition de la m�thode toString pour obtenir une repr�sentation textuelle de la route.
     * @return Une repr�sentation textuelle de la route.
     */
    public String toString() {
        return "Route : " + nom;
    }
}
