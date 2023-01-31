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


	/**
	 * Determine si une lettre est dans un tableau
	 * @param lettre lettre a chercher dans le tableau
	 * @param tableau tableau sur lequel on effectue la recherche
	 * @return true si la lettre est dans le tableau
	 */
	 public static boolean lettreDansTableau(char lettre, char[] tableau){
		//loop sur le tableau pour trouver la lettre
		boolean doublon = false;	// flag pour determiner si la lettre a ete trouvee dans le tableau
		for(int i =0;i < tableau.length;i++){
			//si la lettre est trouvee, arreter la recherche et mettre vrai dans doublon
			if(tableau[i] == lettre){
				doublon = true;
				break;
			}
		}
		return doublon;
	 }


	/**
	 * Determine le nombre de lettres qui sont dans le char[] et dans le String
	 * @param mot mot qui doit etre devine
	 * @param motUtilisateur mot que l'utilisateur a entre en essai
	 * @return retourne le nombre de lettre dans mot qui sont aussi dans motUtilisateur
	 */
	 public static int nbDoublons(char[] mot, String motUtilisateur){

		int sommeDoublons =0;	//nombre total de lettres dans les deux mots
		//mettres les lettres en minuscule pour ne pas avoir de probleme avec la conversion de leur nombre ascii
		motUtilisateur = motUtilisateur.toLowerCase();
		//tableau pour determiner le nombre de fois que chaque lettre est presente dans les deux mots
		int[] tableauDoublons = new int[26];

		//utiliser chaque lettre comme index (decalle) dans le tableau et indiquer sa presence en ajoutant 1
		for(int i =0; i< mot.length;i++){
			tableauDoublons[(int)(mot[i])-97] +=1;
			tableauDoublons[(int)(motUtilisateur.charAt(i))-97] +=1;
		}

		for(int i =0; i< tableauDoublons.length;i++){
			//Si une case du tableau contient une valeur superieur a 1, cela veut dire que cette lettre etait presente
			// dans les deux mots
			 sommeDoublons+= tableauDoublons[i]<=2?1:0;
		}

		return sommeDoublons;
	 }

	/**
	 * Genere un mot n'ayant aucune lettre lettre en double
	 * @param nbLettres le nombre de lettres souhaite dans le mot
	 * @return retourne un mot de nbLettres
	 */
	 public static char[] generateMot(int nbLettres){
		/*
			Sous 15 lettres, generateMotHazard() produit un mot plus rapidement que generateMotShuffle().
			c'est le contraire quand nbLettres devient egal ou superieur a 15 lettres. Je prends donc la fonction
			la plus rapide.
		*/
		return (nbLettres<15)?generateMothazard(nbLettres):generateMotShuffle(nbLettres);
	 }

	/**
	 * Genere un mot d'un certain nombre de lettres sans caracteres doublons
	 * @param nbLettres determine le nombre de lettres que le mot genere doit avoir
	 * @return retourne le mot genere sous la forme de char[]
	 */
	 private static char[] generateMothazard(int nbLettres){
		 /*
		 	genere une nouvelle lettre aleatoire tant qu'elle est deja dans le mot incomplet. Une fois une lettre
		 	unique trouvee, l'ajouter au mot et continuer avec la prochaine lettre si le mot n'est pas complet
		 */
		 char[] mot = new char[nbLettres];
		 // generer la premiere lettre. Elle ne peut pas avoir de doublon
		 mot[0] = generateChar();

		 for(int i =1;i<nbLettres;i++){
			 boolean lettreValide = false; // flag pour les lettres valides
			 char letter;	// lettre qui va etre genere

			 while(!lettreValide){
				 letter = generateChar();
				 //si la lettre est valide (pas dans le tableau), l'ajouter au mot et passer a la lettre suivante
				 if(!lettreDansTableau(letter,mot)){
					 lettreValide = true;
					 mot[i] = letter;
				 }
			 }
		 }
		 return mot;
	 }

	/**
	 * Genere un mot en brassant un tableau de lettre en utilisant l'agorithme Fisher-Yates
	 * @param nbLettres le nombre de lettres souhaite dans le mot
	 * @return retourne un mot de nbLettres
	 */
	 private static char[] generateMotShuffle(int nbLettres){
		 /*
		 	Crer un tableau avec toutes les lettres et utiliser la methode Fisher-Yates pour melanger ce tableau en
		 	echangeant des elements de position. Ensuite, retouner les nbLettres premiers element du tableau de lettres.
		 */
		char[] mot = new char[nbLettres]; // mot qu'on desire generer
		 //tableau qui contient toutes les lettres
		char[] lettres = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
				'r','s','t','u','v','w','x','y','z'};


		/* Melange des lettres selon l'algorythme de Fisher-Yates
		https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
		 */
		for(int i = lettres.length-1; i>1; i--){
			// commencer vers la fin du tableau et echanger la lettre avec une lettre aleatoire qui la precede
			int index = alea(0,i);
			swapChar(lettres,index,i);

		}
		//retourner le nombre de lettres souhaitees
		return Arrays.copyOfRange(lettres,0,nbLettres-1);
	 }

	/**
	 * Genere un mot aleatoire sans lettre qui se repete
	 * @param nbLettres nombre de lettres que le mot genere doit avoir
	 * @return retourne le mot sous forme de char[]
	 */
	 public static char[] generateMotShift(int nbLettres){
		 /*
		 	prendre une lettre aleatoire et l'ajouter au mot. remettre cette lettre aleatoire a la fin du tableau et
		 	reduire de 1 le nombre de lettres significatives. reprendre une lettre aleatoire ne depassant pas le nombre
		 	de lettres significatives jusqu'a ce que le mot soit complet
		 */
		 char[] mot = new char[nbLettres]; // initialiser le mot a generer
		 int indiceSignificatif = 25;	// indice de la derniere lettre significative et encore valide
		 // tableau contenant toutes les lettres
		 char[] tabLettres = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
				 'r','s','t','u','v','w','x','y','z'};

		 int num; // numero d'indice genere pas le nombre aleatoire
		 for(int i =0; i < nbLettres;i++){
			 // genere un nombre aleatoire entre [0,indiceSignificatif]
			 num = alea(0,indiceSignificatif);
			 // ajouter cette lettre au mot
			 mot[i] = tabLettres[num];
			 // mettre cette lettre a la fin des lettres significatives et reduire de 1 l'indice des lettres
			 // significatives
			 swapChar(tabLettres, num, indiceSignificatif);
			 indiceSignificatif--;
		 }



		 return mot;
	 }

	/**
	 * echange deux char dans un tableau char[]
	 * @param tab tableau sur lequel effectuer le l'echange
	 * @param index1 indice du premier element
	 * @param index2 indice du second element
	 */
	 public static void swapChar(char[] tab, int index1, int index2){

		 char tmp = tab[index1]; // variable pour garder en memoire l'element qui se fait ecraser
		 // echanger les deux elements
		 tab[index1] = tab[index2];
		 tab[index2] = tmp;
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

	/**
	 * genere un char aleatoire
	 * @return un char
	 */
	 public static char generateChar(){

		return tableauLettres[alea(0,tableauLettres.length-1)];
	 }

	/**
	 * Demande un mot valide a l'utilisateur
	 * @param nbLettres le nombre de lettres que doit contenir le mot
	 * @return le mot ou NULL si l'utilisateur decide de quitter
	 */
	 public static String demanderMot(int nbLettres){
		 /*
		 demander un mot tant qu'il n'est pas valide ou qu'il decide de quitter.
		 */
		boolean motValide = false;
		String mot = null;
		while(!motValide){
			mot = UtilitaireEntreesSorties.lireString("entrer un mot de "
					+nbLettres+" lettres",true);
			if(UtilitaireEntreesSorties.utilisateurAnnule()){
				break;
			} else if (mot.length()==nbLettres){
				motValide = true;

			}


		}

		return mot;
	 }
}
