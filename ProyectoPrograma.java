//Codigo Creado por Darius Filip Oras y David Ruiz Velazquez  labz6
//Codigo Creado por Darius Filip Oras y David Ruiz Velazquez  labz6
import java.io.File;
import java.util.Random;
import java.util.Scanner;
public class ProyectoPrograma{
	
	static public class InfoGame{
			String[] PalabrasPadre; //todas las de mas de 6 letras
			String[] PalabrasHija; 
			String padre[]; //la palabra elejida y su palabra desordenada

			public InfoGame() {	
				this.padre = new String[2];
				this.PalabrasPadre = new String[1000];
				this.PalabrasHija = new String[1000];
			}
		}	
		public static InfoGame InfoAncestros() {   //Rellena las palabras padre e hijas en arrays
			InfoGame juego = new InfoGame();
			String line;
			try {
				File f = new File("diccionario.txt");
				Scanner in = new Scanner(f);
				int contp = 0;
				int conth = 0;
				while(in.hasNextLine()) {
					line = in.nextLine();
					if(SixDigits(line) == true) {
						juego.PalabrasPadre[contp] = line;
						contp++;
					}
					if(Hijas(line)==true) {
						juego.PalabrasHija[conth] = line;
						conth++;
					}
				}
				in.close();
			}
			catch(Exception e){
				System.out.println("File Not Found");
			}
			return juego;
		}
		
		public static boolean SixDigits(String line) { //comprueba las palabras de 6 o mas letras
			boolean correct = false;
			
			if ((int)line.length() >= 6 ) {
				correct = true;
			}
			return correct;
		}
		public static boolean Hijas(String line) { 
			boolean correct = false;
			
			if ((int)line.length() >= 3 ) {
				correct = true;
			}
			return correct;
		}
		
		public static InfoGame GetPadre() {  //escoge una palabra de mas de 6 letras al azar como palabraPadre y la randomiza
			InfoGame infoAncestros = InfoAncestros(); //tener en cuenta 2 cosas: el array de valor 0 es la palabraa padre, y el array de valor 1 es la palabra desordenada
			InfoGame infoPadre = new InfoGame();
			int cantPadres = 0;
			for(int i = 0; i<1000;i++) {
				if (infoAncestros.PalabrasPadre[i] != null) {
					cantPadres++;
				}	
			}	
			Random aleatorio = new Random();
			int nAl = aleatorio.nextInt(cantPadres);
			String padre;
			padre = infoAncestros.PalabrasPadre[nAl];
			infoPadre.padre[0] = padre;  //valor 0 es la palabra elegida
			int longitudP = padre.length();
			int nAlP = aleatorio.nextInt(longitudP-1);
			String[] padreCombination = new String[longitudP];
			String padreComb=padre;
			for (int i = 0; i<padre.length();i++) {
				padreComb = padreComb.substring(1) + padreComb.charAt(0);
				padreCombination[i] = padreComb;
			}
			infoPadre.padre[1] = padreCombination[nAlP]; //valor 1 es la palabra desordenada
			return infoPadre;
		}
		public static boolean GetHijas(String padre, String hija) { 
		boolean correct = false;
		int cantLetraPadre = padre.length();
		int cantLetrasHija = hija.length();
		char [] letrasPadre = new char[cantLetraPadre];
		char [] letrasHija = new char[cantLetrasHija];
		int contadorFalsas = 0;
		for(int i = 0; i<padre.length(); i++) {
			letrasPadre[i] = padre.charAt(i);
		}
		for(int i = 0; i<hija.length(); i++) {
			letrasHija[i] = hija.charAt(i);
		}
		for(int x = 0; x<hija.length();x++) {             
			int apareceLetra = padre.indexOf(letrasHija[x]);
			if(apareceLetra == -1) {
				contadorFalsas++;
			}
		}
		int a = 0;
		int hijaRep = 0;
		int padreRep = 0;
		char b = ' ';
		int c = 0;
		if(contadorFalsas == 0) {
			if(hija !=padre) {
				if(hija.length()<=padre.length()) {
			for(int s = 0;s<cantLetrasHija;s++) {
				a = contarCaracter(hija,letrasHija[s]);	
				if(a>1) {
					hijaRep = a;
					b = letrasHija[s];
					}
				}
			}
			if(hijaRep>1) {
			c = contarCaracter(padre,b);
			if(c>1) padreRep = c;
			}
			if(padreRep>=hijaRep) correct = true;	
			}
		}
		return correct;
		
		}
		
		public static int contarCaracter(String cadena, char caracter) {
			int contador = 0;
			int pos = 0;
			pos = cadena.indexOf(caracter);
			while(pos!=-1) {
				contador++;
				pos = cadena.indexOf(caracter,pos+1);
			}
			return contador;
		}
		public static int[] Aleatorios(int cantPosiblesHija) {	
			int i=0, cantidad =5 , rango = cantPosiblesHija; //cantidad de numeros, y el rango de posibilidades
			int[] aleatorios = new int[cantidad];
			aleatorios[i]=(int)(Math.random()*rango);
			for(i=1;i<cantidad;i++) {
				aleatorios[i]=(int)(Math.random()*rango);
				for(int j=0;j<i;j++){
					if(aleatorios[i] == aleatorios[j]) {
						i--; 
					}
				}	
			}			
		return aleatorios;	
		}
		
		public static String Asteriscos(String line) {
            int numAst = line.length();
            String asteriscos="";
            for(int i =0;i<numAst;i++) {
            asteriscos = line.replace(line,asteriscos+"*");		
            }
            return asteriscos;
        }
		
		public static void main(String[] args) {
			int puntosAcumulados =0;
			boolean decision = true;
			while(decision !=false) {
			InfoGame infoAncestros = InfoAncestros();
			InfoGame infoPadre = GetPadre();
			System.out.println(infoPadre.padre[1]+" (Palabra Inicial)");
			String[] cincoHijas = new String [5];  //aqui se almacenan  las 5 hijas ya al azar;
			int contHijasPosibles = infoAncestros.PalabrasHija.length;
			int contHijasVerdaderas =0;
			for(int i=0;i<infoAncestros.PalabrasHija.length;i++) {
				if(infoAncestros.PalabrasHija[i]==null)contHijasPosibles--;
			}
			for(int i=0;i<contHijasPosibles;i++) {
				if(GetHijas(infoPadre.padre[0],infoAncestros.PalabrasHija[i])==true) {
					contHijasVerdaderas++;
				}
			}
			String[] hijasVerdaderas = new String[contHijasVerdaderas]; 
			int z=0;
			for(int i=0;i<contHijasPosibles;i++) {
				if(GetHijas(infoPadre.padre[0],infoAncestros.PalabrasHija[i])==true) {
					hijasVerdaderas[z] = infoAncestros.PalabrasHija[i];
					z++;
				}
			}
			int[] aleatorios = Aleatorios(contHijasVerdaderas);  //de 0 a x rango de valor para los numeros al azar
			for(int i=0;i<5;i++) {
				cincoHijas[i]=hijasVerdaderas[aleatorios[i]];  //almacena las 5 aleatorias.
			}
			String[] asteriscos = new String[5];
			int aciertos =0;
			int puntos = 0;
			int letras = 0;
			int texto = 0;	
            Scanner palabrasJugador = new Scanner(System.in);
            for(int i=0;i<5;i++) {
            	asteriscos[i] = Asteriscos(cincoHijas[i]);
            	System.out.println(asteriscos[i]);
            }
			while(aciertos !=5) {
				boolean  extra = false;
				boolean  inv = false;
				boolean verdadera = false;
				boolean noDic = false;
			     System.out.println("\nIntroduce la palabra a probar");
			     if(texto>0) System.out.println(infoPadre.padre[1]+" (Palabra Inicial)");
			     texto++;
			    String intento = palabrasJugador.nextLine();
			    int contInv=0;
			    for(int i=0;i<contHijasVerdaderas;i++) {
					if(hijasVerdaderas[i].equals(intento)) {
						hijasVerdaderas[i] ="rkmrwofuidjioñsjovid12d45435fkgoijsdfgaujkvbeidjfgodjpofgj389394857345";
						puntos++;
						puntosAcumulados++;
						extra = true;
						inv = false;
					}
					else contInv++;
			    }
			    if(contInv==contHijasVerdaderas) {
			    	if(GetHijas(infoPadre.padre[0],intento)==true ) {
			    		if(intento.length()>2) {
			    		noDic=true;
			    		extra = false;
			    		verdadera=false;
			    		}
			    		else {
			    			noDic=false;
			    			inv=true;
			    			extra=false;
			    			verdadera=false;
			    		}
			    	}
			    	else {
			    	inv = true;
			    	verdadera=false;
			    	extra = false;
			    	noDic=false;
			    	}
			   }
			   if(extra==true||verdadera==true||inv==true) noDic=false; 
			    System.out.println(""); //salto de linea
				for(int i=0;i<5;i++) {
					if(cincoHijas[i].equals(intento)) {
						cincoHijas[i] ="rkmrwofuidjioñsjovid12d45435fkgoijsdfgaujkvbeidjfgodjpofgj389394857345";
						asteriscos[i] = intento;
						aciertos++;
						letras = intento.length()-1;
						puntos+=letras;
						puntosAcumulados+=letras;
						extra = false;
						inv =false;
						verdadera = true;
						noDic=false;
					}
					System.out.println(asteriscos[i]);
				}
				 if(verdadera ==true&& extra==false &&inv ==false && noDic==false)System.out.println("Palabra Correcta!");
				 if(inv==true) System.out.println("Palabra Invalida!");
				 if(extra==true) System.out.println("Palabra Extra!");	
				 if(noDic==true&&inv==false) System.out.println("No se encuentra en el diccionario o esta repetida anteriormente");	 
			}
			//final del juego y toma de decision
			Scanner in = new Scanner(System.in);
			System.out.println(""); //salto de linea
			System.out.println("FIN DE LA PARTIDA");
			  System.out.println("Puntos de esta partida: "+puntos);
	            System.out.println("Puntos Acumulados: " +puntosAcumulados);
	            System.out.println("Otra partida?: si/no");
			String respuesta = in.next();
			if(!respuesta.equals("si") && !respuesta.equals("no") && !respuesta.equals("Si") && !respuesta.equals("No")&&!respuesta.equals("SI")&&!respuesta.equals("NO")) {
				boolean rep = false;
				while(rep == false) {
					System.out.println("introduzca los caracteres adecuados: si/no");
					respuesta = in.next();
					if(respuesta.equals("si") || respuesta.equals("no")|| respuesta.equals("NO")||respuesta.equals("SI")|| respuesta.equals("Si")|| respuesta.equals("No")) rep = true;
				}
			}
			if(respuesta.charAt(0) == 'n'||respuesta.charAt(0) == 'N') {
				if(respuesta.charAt(1) == 'o'||respuesta.charAt(1) == 'O') {
					System.out.println("El juego ha concluido!");
						in.close();
						palabrasJugador.close();
			decision = false;
			}
				}	
		}
		}		
		}