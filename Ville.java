/**
 * @className Ville
 * @description : La classe repr�sente une ville dans une communaut�.
 *                Elle g�re les attributs de la ville ainsi que l'ajout ou la suppression
 *                des zones de recharge associ�es � cette ville.
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
     * M�thode de r�cup�ration du nom de la ville en lecture.
     * @return Le nom de la ville.
     */
    public String getNom() {
        return nom;
    }

    @Override
    /**
     * Red�finition de la m�thode toString pour obtenir une repr�sentation textuelle de la ville.
     * @return Une repr�sentation textuelle de la ville.
     */
    public String toString() {
        return "Ville : " + nom;
    }

    /**
     * M�thode d'ajout d'une zone de recharge � la ville.
     */
    public void ajouterZoneRecharge() {
        setZonesRecharge(true);
    }

    /**
     * M�thode de suppression de la zone de recharge de la ville.
     */
    public void retirerZoneRecharge() {
        setZonesRecharge(false);
    }

    /**
     * M�thode de modification de l'�tat de la zone de recharge de la ville.
     * @param zonesRecharge L'�tat de la zone de recharge � d�finir.
     */
    public void setZonesRecharge(boolean zonesRecharge) {
        this.zonesRecharge = zonesRecharge;
    }

    /**
     * M�thode de r�cup�ration de l'�tat de la zone de recharge de la ville en lecture.
     * @return true si la ville poss�de une zone de recharge, false sinon.
     */
    public boolean getZonesRecharge() {
        return zonesRecharge;
    }

    /**
     * Retourne le code de hachage de l'objet bas� sur le nom de la ville.
     * @return Le code de hachage de l'objet.
     */
    @Override
    public int hashCode() {
        return nom.hashCode();
    }

    /**
     * Indique si l'objet est �gal � un autre objet. Deux objets Ville sont
     * consid�r�s �gaux si leurs noms de ville sont �gaux.
     * @param obj L'objet � comparer avec l'objet courant.
     * @return true si les objets sont �gaux, false sinon.
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
