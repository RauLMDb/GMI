package aed.tries;

import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;

public class Primes {
	PositionList<Integer> primes;

	public Primes() {
		primes = new NodePositionList<>();
	}


	private void addChildOrdered(int n) {
		int i = 0;
		Iterator<Integer> it = primes.iterator();
		if (it.hasNext()) {
			Integer cursor = it.next();
			while (it.hasNext() && cursor < n)
				cursor = it.next();
			if (cursor == n) {
				i = 1;
			} else if (cursor < n) {
				primes.addLast(n);
				i = 1;
			}
		}
		if (i == 0) primes.addFirst(n);
	}

	public boolean add(int n) {
		if (n == 0) throw new IllegalArgumentException();
		Integer divisor = null; 
		Iterator<Integer> it  = primes.iterator();
		while (it.hasNext() && divisor == null)	{
			Integer prime	= it.next();
			divisor = n % prime == 0 && prime != 1 ? prime : null;
		}
		if (divisor == null) { 
			addChildOrdered(n); 
			return true;
		}else return false;
	}
	
	public void addNoOrder(int n) {
		if (n == 0) throw new IllegalArgumentException();
		Integer divisor = null; 
		Iterator<Integer> it  = primes.iterator();
		while (it.hasNext() && divisor == null)	{
			Integer prime	= it.next();
			divisor = n % prime == 0 && prime != 1 ? prime : null;
		}
		if (divisor == null) primes.addLast(n);
	}
}
