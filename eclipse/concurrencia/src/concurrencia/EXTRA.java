package concurrencia;

public class EXTRA {
	public static final int M = 1000;
	public static String[] strToA (String str) {
		int j = 0;
		int length = str.length();
		for (int i = 0;i<length-1;i++) {
			char c = str.charAt(i);
			char c1 = str.charAt(i+1);
			if ( c != ' ' && c1 == ' ') 
				j++;
		}
		if (str.charAt(length-1) != ' ')
			j++;
		String [] res = new String [j];
		String c = "";
		int k = 0;
		for (int i = 0;i<length;i++) {
			char ch = str.charAt(i);
			if ( ch != ' ' && i != length-1) {
				c += ch;
			}else {
				if (ch != ' ')	c += ch;
				res[k++] = c;
				c = "";
			}
		}
		return res;
	}

	public static void invertir(int[]enteros,int[]saltos) {
		int l=13;
		int s = 2;
		int m=0;
		int j = 0;
		int p = 0;
		for(int i=0; i< l; i++) {
			if(m==M||saltos[p++]== i||i == l-1) {
				if (i== l-1&& M !=m) {m++;}
				if ( m == M)saltos[s++] = 1-i;
				int aux = 0;
				int pointer = m%2 == 0? m/2 : (m-1)/2;
				for(int k=0; k<pointer; k++) {
					int a = i == l-1 && m != M? k + j*(i-m+1):k + j*(i-m);
					int x = i == l-1 && m != M? m + j*(i-m+1) - 1 - k:m + j*(i-m) - 1 - k;
					aux = enteros[a];
					enteros[a] = enteros[x];
					enteros[x] = aux;
				}
				//				for(int f=0; f<m;f++) {
				//					System.out.print(res[f]+" ");
				//				}
				j = 1;
				if(m == M) m = 1;
				else m = 0;
				//				System.out.print("\n");
			}
			else {
				p--;
				m++;
			}
		}
		for (int i = 0;i<l;i++) {boolean salto = false;boolean mM = false;
		int r = 0;
		while(!salto && r<s&& !mM) {
				salto = i == saltos[r];
				mM = i == -saltos[r++];
		}
		if (salto) {
			System.out.println();
		}else if(mM) {
			System.out.print(enteros[i]+"\n");
		}else {
			System.out.print(enteros[i] +" ");
		}
		}
	}

	public static void main(String[] args) {
		int[] enteros = {1,2,3,4,5,0,6,7,8,9,0,10,11,0,0,0,0,0,0,0};
		int[]saltos   = {5,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};		
		System.out.println();
		invertir(enteros,saltos);
	}
}
