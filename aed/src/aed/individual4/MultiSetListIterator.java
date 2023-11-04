package aed.individual4;

import java.util.Iterator;
import java.util.NoSuchElementException;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;

public class MultiSetListIterator<E> implements Iterator<E> {
	PositionList<Pair<E,Integer>> list;
	Position<Pair<E,Integer>> prevCursor;
	Position<Pair<E,Integer>> cursor;
	int counter;
	public MultiSetListIterator(PositionList<Pair<E,Integer>> list) {
		this.list = list;
		cursor= list.first();
		counter = 0;
		prevCursor= null;
	}

	public boolean hasNext() {
		boolean hasNext = true;
		if (cursor != null && cursor.element()!= null) {
			int n = cursor.element().getRight();
			if ((counter == n && list.next(cursor) == null)) hasNext = false;
		}else hasNext = false;
		return hasNext;
	}

	public E next() {
		if(!hasNext()) throw new NoSuchElementException();
		prevCursor = cursor;
		E res = cursor.element().getLeft();
		int n = cursor.element().getRight();
		if (counter < n-1) counter++;
		else { 
			counter = 0;
			cursor = list.next(cursor);
		}
		return res;
	}
	@Override
	public void remove() {
		if (prevCursor == null) throw new IllegalStateException();
		if (counter == 0) {
			removeAux(list,prevCursor);		
		}else {
			removeAux(list,cursor);
			counter--;
		}		
		prevCursor = null;
	}
	private static <E> void removeAux(PositionList<Pair<E,Integer>> list,Position<Pair<E,Integer>> cursor) {
		int n = cursor.element().getRight();
		cursor.element().setRight(n-1);
		n = cursor.element().getRight();
		if (n==0) list.remove(cursor);
	}
}
