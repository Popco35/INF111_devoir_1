/**
 * Offre des fonctions utilitaires communes au projet
 *
 * @author Pierre Bélisle
 * @version H2023
 *
 */
public class UtilitaireFonctions {
	public static final char[] tableauLettres = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
			'r','s','t','u','v','w','x','y','z'};
	/**
	 * Retourne une nombre aléatoire dans un intervalle entier donné.
	 *
	 * ATTENTION : min doit être plus petit que max  mais aucune validation
	 *             n'est effectuée.
	 *
	 * @param min La plus petite valeur possible
	 * @param max La plus grande valeur possible
	 * @return Un nombre entre min et max (inclusivement)
	 */
	public static int alea(int min, int max){

		/**
		 * STRATÉGIE :  On utilise la fonction random de la classe Math et on
		 *                          ajuste la valeur reçue entre [0 et 1[
		 *                          tronqué (à l'aide de floor)
		 *
		 *                          Le calcul permet de déplacer dans l'intervalle
		 *                          [min, max] inclusivement
		 *
		 *                          La conversion de type, élimine la partie décimale.
		 */
		  return (int) Math.floor(Math.random()* (max - min + 1) + min);
	 }

	 public static boolean lettreDansTableau(char lettre, char[] tableau){
		boolean doublon = false;
		for(int i =0;i < tableau.length;i++){
			if(tableau[i] == lettre){
				doublon = true;
				break;
			}
		}
		return doublon;
	 }


	 public static char[] generateMot(int nbLettres){
		char[] mot = new char[nbLettres];
		mot[0] = generateChar();
		for(int i =1;i<nbLettres;i++){
			boolean lettreValide = false;
			char letter;
			while(!lettreValide){
				letter = generateChar();
				if(!lettreDansTableau(letter,mot)){
					lettreValide = true;
					mot[i] = letter;
				}
			}
		}


		return mot;
	 }

	 public static char generateChar(){

		return tableauLettres[alea(0,tableauLettres.length-1)];
	 }
}
