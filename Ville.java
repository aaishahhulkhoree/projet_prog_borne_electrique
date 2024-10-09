/**
 * @className Ville
 * @description : La classe représente une ville dans une communauté.
 *                Elle gère les attributs de la ville ainsi que l'ajout ou la suppression
 *                des zones de recharge associées à cette ville.
 * @date 12/12/2023
 * @author CHAKER, HAMOUCHI, HULKHOREE
 */
package projet;

public class Ville {
    private String nom;
    protected boolean zonesRecharge;

    /**
     * Constructeur pour initialiser une ville avec une zone de recharge.
     * @param zonesRecharge Indique si la ville a une zone de recharge.
     * @param nom Le nom de la ville.
     */
    public Ville(boolean zonesRecharge, String nom) {
        setZonesRecharge(true);
        this.nom = nom;
    }

    /**
     * Méthode de récupération du nom de la ville en lecture.
     * @return Le nom de la ville.
     */
    public String getNom() {
        return nom;
    }

    @Override
    /**
     * Redéfinition de la méthode toString pour obtenir une représentation textuelle de la ville.
     * @return Une représentation textuelle de la ville.
     */
    public String toString() {
        return "Ville : " + nom;
    }

    /**
     * Méthode d'ajout d'une zone de recharge à la ville.
     */
    public void ajouterZoneRecharge() {
        setZonesRecharge(true);
    }

    /**
     * Méthode de suppression de la zone de recharge de la ville.
     */
    public void retirerZoneRecharge() {
        setZonesRecharge(false);
    }

    /**
     * Méthode de modification de l'état de la zone de recharge de la ville.
     * @param zonesRecharge L'état de la zone de recharge à définir.
     */
    public void setZonesRecharge(boolean zonesRecharge) {
        this.zonesRecharge = zonesRecharge;
    }

    /**
     * Méthode de récupération de l'état de la zone de recharge de la ville en lecture.
     * @return true si la ville possède une zone de recharge, false sinon.
     */
    public boolean getZonesRecharge() {
        return zonesRecharge;
    }

    /**
     * Retourne le code de hachage de l'objet basé sur le nom de la ville.
     * @return Le code de hachage de l'objet.
     */
    @Override
    public int hashCode() {
        return nom.hashCode();
    }

    /**
     * Indique si l'objet est égal à un autre objet. Deux objets Ville sont
     * considérés égaux si leurs noms de ville sont égaux.
     * @param obj L'objet à comparer avec l'objet courant.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || !(obj instanceof Ville)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Ville autreVille = (Ville) obj;
        return nom.equals(autreVille.nom);
    }
}
