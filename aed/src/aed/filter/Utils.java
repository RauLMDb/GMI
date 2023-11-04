package aed.filter;

import java.util.Iterator;
import java.util.function.Predicate;
import es.upm.aedlib.positionlist.*;


public class Utils {

	public static <E> Iterable<E> filter(Iterable<E> d, Predicate<E> pred){
		if (d==null) throw new IllegalArgumentException();
		PositionList <E> res = new NodePositionList<>();
		Iterator<E> it = d.iterator();
		while (it.hasNext()) {
			E e = it.next();
			if (e != null && pred.test(e))
				res.addLast(e);
		}

		return res;
	}
}

