package upm.aed.indexedlist;

public class EjerciciosProblemasFunciones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double raio1=2;
		double rado2=6;
		double altura=10;
		double densidad=3;
		double masa1=20;
		double masa2=50;
		double x1=7;
		double x2=15;
		double y1=14;
		double y2=28;
		System.out.println(areaCorona(5.18,10.35));
		System.out.println(areaCilindro(4,altura));
		System.out.println(volumenCorona(5.18,10.35,altura));
		System.out.println(masaArandela(5.18,10.35,altura,densidad));
		System.out.println(distancia(x1,x2,y1,y2));
		System.out.println(diaSemana2(3));
		System.out.println("El mes con el numero "+ 12 + " corresponde a: " + meses1(12));
		System.out.println("Con el eje x de " + 5 + " y el eje y de " + -4 + " pertenece al siguiente cuadrante: " 
				+ ejeCoordenadas(5, -4));
		System.out.println(sigMinutos(23,59,59));
		System.out.println(sigHoras(23,59,59));
		System.out.println(sigHoras(23,59,59)  );
		System.out.println(horario(23,59,59));
		System.out.println(horario(22,59,59));
		System.out.println(horario(22,59,58));
	}

	public static double longitudCircunferencia (double radio)
	{
		return (2 * Math.PI * radio)*2;
	}
	// El area del círculo es multiplicado *2 ya que es para hallar el área del cilindro
	public static double areaCirculo (double radio)
	{
		return  Math.PI * Math.pow (radio,2);
	}

	public static double areaRectangulo (double altura, double radio)
	{
		return  (2 * Math.PI*radio)*altura;
	}
	public static double areaCilindro (double radio, double altura )
	{
		return longitudCircunferencia(radio) + areaCirculo(radio) + areaRectangulo(radio,altura);
	}



	//area de una corona
	public static double areaCorona (double radio1, double radio2)
	{
		return areaCirculo(radio2)-areaCirculo(radio1);
	}

	//Volumen de una corona
	public static double volumenCilindro (double radio, double altura)
	{
		return Math.PI*Math.pow(radio, 2)*altura;
	}

	public static double volumenCorona(double radio1, double radio2,double altura)
	{
		return volumenCilindro(radio2,altura)-volumenCilindro(radio1,altura);
	}

	//Masa de una arandela

	public static double masaArandela (double radio1, double radio2,double altura, double densidad)
	{
		//		return (volumenCilindro(radio2,altura)-volumenCilindro(radio1,altura))*densidad;
		return volumenCorona(radio1,radio2,altura)*densidad;
	}

	//Atracción de dos masas

	public static double distancia (double x1,double x2, double y1, double y2)
	{
		return Math.sqrt((x2-x1)+(y2-y1));
	}
	public static final double G =6.67E-11;

	public static double fuerzaAtraccion (double masa1,double masa2,double x1,double x2, double y1, double y2)
	{
		return G*(masa1*masa2)/Math.pow(distancia(x1,x2,y1,y2), 2);
	}


	//PROBLEMAS DE ALTERNATIVAS

	public static int mayor (int a, int b) {
		if (a>b) 
			return a;
		else
			return b;

	}
	public static int mayor (int a, int b, int c) {
		if (a>b && a>c)
			return a;
		else if (b>a && b>c)
			return b;
		else
			return c;
	}

	public static boolean par (int a)
	{
		if (a%2 == 0) 
			return true;

		else
			return false;

	}

	public static String diaSemana (int ds) {

		if (ds == 1)
			return "lunes";
		else if (ds == 2)
			return "martes";
		else if (ds == 3)
			return "Miércoles";
		else if (ds == 4)
			return "Jueves";
		else if (ds == 5)
			return "Viernes";
		else if ( ds == 6 )
			return "Sábado";
		else 
			return "Domingo";


	}

	public static String diaSemana2 (int dia) {

		switch (dia) {
		case 1 : return "Lunes";
		case 2 : return "Martes";
		case 3 : return "Miércoles";
		case 4 : return "Jueves";
		case 5 : return "Viernes";
		case 6 : return "Sábado";
		default : return "Domingo";
		}

	}
	// Meses

	public static String meses (int mes) {
		switch (mes) {
		case 1 : return "Enero";
		case 2 : return "Febrero";
		case 3 : return "Marzo";
		case 4 : return "Abril";
		case 5 : return "Mayo";
		case 6 : return "Junio";
		case 7 : return "julio";
		case 8 : return "Agosto";
		case 9 : return "Septiembre";
		case 10 : return "Octubre";
		case 11: return "Nomviembre";
		default : return "Diciembre";
		}
	}

	public static String meses1 (int mes) {

		if (mes == 1)
			return "Enero";
		else if (mes == 2)
			return "Febrero";
		else if (mes == 3)
			return "Marzo";
		else if (mes == 3)
			return "Abril";
		else if (mes == 4)
			return "Mayo";
		else if (mes == 5)
			return "Junio";
		else if (mes == 6)
			return "Julio";
		else if (mes == 7)
			return "Agosto";
		else if (mes == 8)
			return "Agosto";
		else if (mes == 9)
			return "Septiembre";
		else if (mes == 10)
			return "Octubre";
		else if (mes == 11)
			return "Noviembre";
		else  
			return "Diciembre"	;
	}

	public static int diasMeses (int mes, int anio) {
		if (mes==2 && anyoBisiesto(anio))
			return 29;
		else if (mes==2 && !anyoBisiesto(anio))
			return 28;
		else if (mes==4 || mes==6 || mes==9 || mes==11)
			return 30;
		else 
			return 31;
	}

	public static int diasMeses1 (int mes,int anio) {

		switch (mes) {
		case 2 : if (!anyoBisiesto(anio))
			return 28;
		else
			return 29;
		case 4 : 
		case 6 : 
		case 9 : 
		case 11 : return 30;
		default  : return 31;

		}
	}

	// Año bisiesto
	public static boolean anyoBisiesto ( int anio) {

		return ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0)));
	}


	public static int ejeCoordenadas (double x, double y) {
		if (x>0 && y>0)
			return 1;
		else if (x<0 && y>0)
			return 2;
		else if (x<0 && y<0)
			return 3;
		else
			return 4;
	}
	// Ya se sabe que numero tiene el día	
	public static int sigDia (int dia,int mes, int anio) {
		if (dia == diasMeses( mes, anio))
			return 1;
		else
			return dia+1;
	}
	//dia del mes	
	public static int sigMes (int dia,int mes, int anio) {
		if (dia == diasMeses( mes, anio))
			// el resto de dividir es 1 seria Diciembre
			return (mes % 12) + 1;
		else
			return mes;
	}

	public static int sigAnio (int dia, int mes, int anio)
	{
		if (dia == diasMeses(mes,anio))
			if (mes == 12)
				return anio + 1;
			else 
				return anio;
		else
			return anio;
	}

	public static String fechaSiguiente (int dia, int mes, int anio)
	{
		return sigDia(dia,mes,anio) + "/" +
				sigMes(dia,mes,anio) + "/" +
				sigAnio (dia,mes, anio);
	}

	public static int sigSegundos (int hora, int minuto, int segundo) {
		if (segundo==59)
			return 0;
		else 
			return segundo+1;
	}
	public static int sigMinutos (int hora, int minuto, int segundo) {
		if (segundo == 59)
			if (minuto == 59)

				return 0;
			else
				return minuto+1;
		else 
			return minuto;
	}

	public static int sigHoras (int hora, int minuto, int segundo) {
		if (segundo == 59)
			if(minuto == 59)
				if(hora == 23)
					return 0;
				else 
					return hora+1;
			else
				return hora;
		else 
			return hora;


	}

	public static String horario (int hora, int minuto, int segundo) {
		return 	sigHoras(hora, minuto, segundo)+ ":" +
				sigMinutos(hora, minuto, segundo) + ":" +
				sigSegundos(hora, minuto, segundo);
	}


}



