import java.util.Arrays;

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


	 public static int nbDoublons(char[] mot, String motUtilisateur){
		int sommeDoublons =0;
		 motUtilisateur = motUtilisateur.toLowerCase();
		int[] tableauDoublons = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		for(int i =0; i< mot.length;i++){
			tableauDoublons[(int)(mot[i])-97] +=1;
			tableauDoublons[(int)(motUtilisateur.charAt(i))-97] +=1;
		}

		 for(int i =0; i< tableauDoublons.length;i++){
			 sommeDoublons+= tableauDoublons[i]==2?1:0;
		 }

		return sommeDoublons;
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

	 public static char[] generateMotShuffle(int nbLettres){
		char[] mot = new char[nbLettres];
		char[] lettres = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
				'r','s','t','u','v','w','x','y','z'};
		char memoire;

		//Melange des lettres selon l'algorythme de Fisher-Yates
		for(int i = lettres.length;i>1;i--){
			int p = alea(0,i-1);
			memoire = lettres[p];
			lettres[p] = lettres[i-1];
			lettres[i-1]=memoire;
		}
		return Arrays.copyOfRange(lettres,0,nbLettres-1);
	 }


	 public static boolean equals(char[] mot, String str){
		boolean result = true;
		str = str.toLowerCase();
		if(mot.length == str.length()){
			for(int i =0;i < mot.length;i++){
				if(mot[i] != str.charAt(i)){
					result = false;
				}
			}
		} else{
			result = false;
		}

		return result;
	 }

	 public static char generateChar(){

		return tableauLettres[alea(0,tableauLettres.length-1)];
	 }

	 public static String demanderMot(int nbLettres){
		boolean motValide = false;
		String mot = null;
		while(!motValide){
			mot = UtilitaireEntreesSorties.lireString("entrer un mot de "
					+nbLettres+" lettres",true);
			if(mot.equals("")){
				break;
			} else if (mot.length()==nbLettres){
				motValide = true;

			}


		}

		return mot;
	 }
}
