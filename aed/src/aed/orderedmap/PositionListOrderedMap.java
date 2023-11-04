package aed.orderedmap;

import java.util.Comparator;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;

public class PositionListOrderedMap<K,V> implements OrderedMap<K,V> {
	private Comparator<K> cmp;
	private PositionList<Entry<K,V>> elements;

	/* Acabar de codificar el constructor */
	public PositionListOrderedMap(Comparator<K> cmp2) {
		this.cmp = cmp2;
		elements = new NodePositionList<>();
	}

	/* Metodos Auxiliares */

	/**
	 * If key is in the map, return the position of the corresponding
	 * entry.  Otherwise, return the position of the entry which
	 * should follow that of key.  If that entry is not in the map,
	 * return null.  Examples: assume key = 2, and l is the list of
	 * keys in the map.  For l = [], return null; for l = [1], return
	 * null; for l = [2], return a ref. to '2'; for l = [3], return a
	 * reference to [3]; for l = [0,1], return null; for l = [2,3],
	 * return a reference to '2'; for l = [1,3], return a reference to
	 * '3'. 
	 * @param key
	 * @return Position<Entry<K,V>>
	 */
	private Position<Entry<K,V>> findKeyPlace(K key){
		if (key == null) throw new IllegalArgumentException();
		Position<Entry<K,V>> res = elements.first() ;
		while (res != null && cmp.compare(res.element().getKey(), key) < 0) 
			res = elements.next(res);
		return res;
	}
	/**
	 * Devuelve la referencia a 'key', si no existe 'key' devuelve null.
	 * @param key
	 * @return Position<Entry<K,V>>
	 */
	private Position<Entry<K,V>> keyPosition(K key) {
		if (key == null) throw new IllegalArgumentException();
		Position<Entry<K,V>> res ;
		for (res = elements.first();res != null && !res.element().getKey().equals(key);res = elements.next(res));
		return res;
	}

	public boolean containsKey(K key) {
		if (key == null) throw new IllegalArgumentException();
		return keyPosition(key) != null;
	}

	public V get(K key) {
		if (key == null) throw new IllegalArgumentException();
		V res = null;
		if (containsKey(key)) res = keyPosition(key).element().getValue();// no puede ser null ya que si lo fuese containsKey seria false y no se ejcutaria
		return res;
	}

	public V put(K key, V value) {
		if (key == null) throw new IllegalArgumentException();
		V res = null;
		Entry<K,V> newItem = new EntryImpl<>(key,value);
		if (containsKey(key)) {
			Position<Entry<K,V>> pos = keyPosition(key);
			elements.addAfter(pos, newItem);
			res = elements.remove(pos).getValue();
		}else if (elements.size() > 0) {
			Position<Entry<K,V>> aux = findKeyPlace(key);
			if (aux != null) elements.addBefore(aux, newItem);
			else elements.addLast(newItem);
		}else elements.addFirst(newItem);
		return res;
	}

	public V remove(K key) {
		if (key == null) throw new IllegalArgumentException();
		V res = null;
		if (containsKey(key)) res = elements.remove(keyPosition(key)).getValue();
		return res;
	}

	public int size() {
		return elements.size();
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public Entry<K,V> floorEntry(K key) {
		if (key == null) throw new IllegalArgumentException();
		Entry<K,V> res = null;
		if (containsKey(key)) 
			res = keyPosition(key).element();// no puede ser null ya que si lo fuese containsKey seria false y no se ejcutaria
		else if (findKeyPlace(key) != elements.first() && findKeyPlace(key) != null) 
			res = elements.prev(findKeyPlace(key)).element();
		else if (findKeyPlace(key) == null && size() != 0) 
			res = elements.last().element();
		return res;
	}

	public Entry<K,V> ceilingEntry(K key) {
		if (key == null) throw new IllegalArgumentException();
		Entry<K,V> res = null;
		if (findKeyPlace(key) != null) res = findKeyPlace(key).element();
		return res;
	}

	public Iterable<K> keys() {
		PositionList<K> res = new NodePositionList<>();
		Position<Entry<K,V>> i = elements.first();
		while (i != null) {
			res.addLast(i.element().getKey());
			i = elements.next(i);
		}
		return res;
	}

	public String toString() {
		return elements.toString();
	}


}
