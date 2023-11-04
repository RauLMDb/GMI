package aed.hashtable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import es.upm.aedlib.Entry;

public class pruebashashtable {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		HashTable<Integer, String> table = new HashTable<>(1);

		System.out.println(table.get(10));
		System.out.println(table.get(10));
		table.put(9, "prats");
		table.put(0, "boix");
		table.put(10, "soria");
		System.out.println(table.remove(0));
		System.out.println(table.isEmpty());
		System.out.println(table.remove(9));
	}

}
