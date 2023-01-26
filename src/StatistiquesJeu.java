/**
 * Simule un enregistrement classique en utilisant une classe
 * avec attributs publiques.
 *
 * Représente les statistiques du jeu qui doivent être maintenues à jour
 * durant les parties de Jotto.
 *
 * @author Pierre Bélisle
 * @version H2023
 *
 */
public class StatistiquesJeu {

	// Une partie gagnée compte pour 1
	public int nbReussite = 0;

	// Le nombre de parties (abandon inclus)
	public int nbParties = 0;

	// chaque essai est comptablisé.
	public double nbEssaisTotal = 0;

}
