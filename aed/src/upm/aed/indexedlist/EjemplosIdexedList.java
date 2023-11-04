package upm.aed.indexedlist;

import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;

public class EjemplosIdexedList {
	public static void main(String[] args) {

		IndexedList<Integer> list = new ArrayIndexedList<>(); 
		
		list.add(0, 4);             // [4]
		list.add(list.size(), 5);   // [4,5]
		list.add(0, 1);             // [1,4,5]
		list.add(1, 8);             // [1,8,4,5]

		System.out.println(list);
		
//		System.out.println("La posicion del 4 es: " + list.indexOf(4));
//		
//		System.out.println("La posicion del 14 es: " + list.indexOf(14));
//		
//		System.out.println("El que ocupa la posición 3 es el: " + list.get(3));
//		
//		System.out.println("Ahora borramos");
//		
//		list.remove(4);             // [1,8,5]
//		list.removeElementAt(0);    // [8,5]
//		
//		System.out.println("Size: "+ list.size());
//		System.out.println("IsEmpty: " + list.isEmpty());
//		
//		IndexedList<Character> listaCaracteres = new ArrayIndexedList<Character>(); 
//		
//		listaCaracteres.add(0,'a'); 
//		listaCaracteres.add(0,'a'); 
//		listaCaracteres.add(1,'c'); 
//		listaCaracteres.add(2,'a'); 
//		
//		System.out.println(listaCaracteres);
//		
//		System.out.println(member(listaCaracteres,'*'));
//		
	}

	// Método que imprime todos los elementos de una lista y su correspondiente índice
	public static <E> void show(IndexedList<E> list) {
		for (int i = 0; i < list.size(); i ++) { 
			System.out.println("[" + i + "]:" + list.get(i));
		}
	}
	
	public static boolean buscarAsterisco (char array[]) {
		int i = 0; 
		while (i < array.length && array[i] != '*') {
			i++;
		}
		return i < array.length;
	}

	// OJO: No controla los NullPointers...
	public static boolean buscarAsterisco (IndexedList<Character> list) {
		int i = 0; 
		while (i < list.size() && !list.get(i).equals('*')) {
			i++;
		}
		return i < list.size();
	}
	
	public static <E> boolean member (IndexedList<E> list, E elem) {
		int i = 0; 
		while (i < list.size() && !eqNull(list.get(i),elem)) {
			i++;
		}
		return i < list.size();
	}
	
	public static boolean eqNull (Object o1, Object o2) {
		return o1 == o2 || (o1!= null && o1.equals(o2));
	}

}
