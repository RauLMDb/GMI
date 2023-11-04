package upm.aed.indexedlist;

import java.util.Comparator;

import es.upm.aedlib.Entry;

public class cmp implements Comparator<Entry<Integer,String>>{
	public cmp() {}
	public int compare(Entry<Integer, String> o1, Entry<Integer, String> o2) {
		if(o1.getKey() == o2.getKey()) return o1.getValue().compareTo(o2.getValue());
		else return o1.getKey().compareTo(o2.getKey());
	}

}
