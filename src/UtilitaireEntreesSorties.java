import java.util.Scanner;

/**
 * UtileValidation
 * Classe qui permet de lire et valider des valeurs
 * en provenance de la console 
 * 
 * @author Pierre Bélisle
 * @version H2023
 */
public class UtilitaireEntreesSorties {
	
        // Sert à retenir si l'utilisateur a annulé.
	private static boolean utilisateurAnnule = false;
	
	// Pour la lecture au clavier.
	private static Scanner clavier= new Scanner(System.in);
	
	
	
	/*******************************
	 * LIRE INT
	 *******************************/
	/**
	 * Affiche le message reçu et attend l'entrée d'un
	 * nombre entier entre les deux bornes (min et max).
	 * 
	 * Si l'utilisateur annule, la fonction retourne 0
	 * et la fonction utilisateurAnnule() retournera true au prochain appel.
	 * 
	 * @param msg Le message de sollicitation
	 * @param min limite inférieure acceptée
	 * @param max limite supérieure acceptée
	 * @return un entier entre min et max
	 */
	public static int lireInt(String msg,int min, int max){
				
		String valeurLue = null;
		boolean valide = false;
		int valInt = 0;
		utilisateurAnnule = false;
				
		while(!valide && !utilisateurAnnule){			
		    
		            //sollicitation de l'utilisateur
			    System.out.print(msg + " (<entrée> pour annuler) : ");
			    
		            //on lit toute la ligne		        
			    valeurLue = clavier.nextLine();
			    
			    //si l'utilisateur annule, c'est valide
			    if (valeurLue.equals("")){
			    	utilisateurAnnule = true; 
			    }//if
			    		    
			    //sinon on tente de convertir en entier
			    else 
		
					try{
						//si on réussit la conversion sans exception
						valInt = Integer.parseInt(valeurLue);
						
						//on évalue les bornes
						if(valInt < min || valInt > max)
						     System.out.println("la valeur entrée doit être " +
						     		            "entre "+min + " et" + max);						

				        //si on vient ici c'est que tout est beau
				        else
							valide = true;
					}//try
					
				    //s'il y a une exception lors de la conversion 
				    //on avise et valide reste faux
					catch (Exception e){
					     System.out.println("la valeur entrée doit être " +
			     		            "entre "+min + " et" + max);
					}//catch
		}//while
		
		return valInt;
	}
	

	/*******************************
	 * LIRE DOUBLE
	 *******************************/
	/**
	 * Affiche le message reçu et attend l'entrée d'un
	 * nombre réel entre les deux bornes (min et max).
	 * 
	 * Si l'utilisateur annule, la fonction retourne 0
	 * et la fonction utilisateurAnnule() retournera true au prochain appel.
	 * 
	 * @param msg Le message de sollicitation
	 * @param min limite inférieure acceptée
	 * @param max limite supérieure acceptée
	 * @return un réel entre min et max
	 */
	public static double lireDouble(String msg,double min, double max){
				
		String valeurLue = null;
		boolean valide = false;
		double valDouble = 0;
		utilisateurAnnule = false;

						
		while(!valide && !utilisateurAnnule){			

		            //sollicitation de l'utilisateur
			    System.out.print(msg + " (<entrée> pour annuler) : ");
			    
		            //on lit toute la ligne		        
			    valeurLue = clavier.nextLine();
			    
			    //si l'utilisateur annule, c'est valide
			    if (valeurLue.equals("")){
			    	utilisateurAnnule = true; 
			    }
			    		    
			    //sinon on tente de convertir en réel
			    else 
		
					try{
						//si on réussit la conversion sans exception
						valDouble = Double.parseDouble(valeurLue);
						
						//on évalue les bornes
						if(valDouble < min || valDouble > max)
						     System.out.println("la valeur entrée doit être " +
						     		            "entre "+min + " et" + max);
						
				        //si on vient ici c'est que tout est beau
				        else
							valide = true;
					}//try
					
				        //s'il y a une exception lors de la conversion 
				        //on avise et valide reste faux
					catch (Exception e){
					     System.out.println("la valeur entrée doit être " +
			     		            "entre "+min + " et" + max);
					}//catch
		}//while
		
		return valDouble;
	}

	/*******************************
	 * LIRE STRING
	 *******************************/
	/**
	 * Affiche le message reçu et attend l'entrée d'une
	 * chaîne.  L'utilisateur annule en entrant une chaîne vide et que entreePermis 
	 * est à true.

	 * Si l'utilisateur annule, la fonction retourne null
	 * et la fonction utilisateurAnnule() retournera true au prochain appel.
	 * 
	 * 
	 * @param msg Le message de sollicitation
	 * @param entreePermis Booléen qui indique si l'utilisateur peut abandonner
	 * 
	 * @return la chaîne lue
	 */
	public static String lireString(String msg, boolean entreePermis){
				
		String valeurLue = null;
		utilisateurAnnule = false;
		
		do{


			//sollicitation de l'utilisateur
			System.out.print(msg);

			if(entreePermis)
				System.out.print(" (<entrée> pour abandonner) : ");

			//on lit toute la ligne		        
			valeurLue = clavier.nextLine();

			//si l'utilisateur annule, c'est valide
			//et on retourne null
			if (valeurLue.equals("") && entreePermis){
				utilisateurAnnule = true;
				valeurLue = null;
			}
			else if(valeurLue.equals("")  & !entreePermis)
				System.out.println(" (<entrée> n'est  pas permis ici ");

		}while ( !utilisateurAnnule && valeurLue.equals("") && !entreePermis);

		return valeurLue;		
    }
	
	

	/*******************************
	 * UTILISATEUR ANNULE
	 *******************************/
	/**
	 * Retourne si l'utilisateur a annulé lors 
	 * de la dernière lecture au clavier
	 */
	public static boolean utilisateurAnnule() {

		return utilisateurAnnule;
	}

	
	/**
	 * Affiche les stats du jeu
	 * 
	 * @param stats  Statiqtiques à afficher
	 */
	public static void afficherStats(StatistiquesJeu stats){

		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("STATISTIQUES DU JEU");
		System.out.println("----------------------------------");

		System.out.println();
		System.out.println("Nombre de parties jouées : " + stats.nbParties);
		System.out.println("Nombre de parties réussies : " + stats.nbReussite);

		System.out.println("Nombre de parties abandonnées : " +
				(stats.nbParties - stats.nbReussite));

		//On évite la division par 0 avec l'opérateur ternaire
		System.out.println("Nombre d'essais en moyenne par partie : " + 
				((stats.nbParties > 0) ? ( stats.nbEssaisTotal / stats.nbParties) :0));

	}
}