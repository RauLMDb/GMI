package aed.recursion;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.*;
import es.upm.aedlib.positionlist.*;


public class Utils {

	public static int multiply(int a, int b) {
		return ((a<0) ? -1 : 1)*multiplyAux(a,b,0);
	}

	private static int multiplyAux(int a, int b,int sum) {
		if(a!=0) {
			if(a%2!=0)  sum=sum+b;
			sum=multiplyAux(a/2,b*2,sum);
		}
		return sum;
	}
	public static <E extends Comparable<E>> int findBottom(IndexedList<E> l) {
		return  (l == null || l.size() == 0) ? -1 : findBottomBinRec (l,0,l.size()-1);
	}

	private static<E extends Comparable<E>> int findBottomBinRec (IndexedList<E> list,int start,int end) {
		int res = -1;
		int indice = 0;
		switch (end-start) {
		case 0:	
			res = start;
			break;
		case 1: 
			res = list.get(start).compareTo(list.get(end))<=0 ? start : end;
			break;
		default:
			indice = (start + end)/2;
			if (list.get(indice).compareTo(list.get(indice+1))<=0 && list.get(indice).compareTo(list.get(indice-1))<=0)	res = indice;
			else res = list.get(indice).compareTo(list.get(indice-1))<=0 ? findBottomBinRec(list,indice+1,end) : findBottomBinRec(list,start,indice-1);
			break;
		}
		return res;
	}

	public static <E extends Comparable<E>> NodePositionList<Pair<E,Integer>>
	joinMultiSets(NodePositionList<Pair<E,Integer>> l1,NodePositionList<Pair<E,Integer>> l2) {
		NodePositionList<Pair<E, Integer>> res = new NodePositionList<>();
		insertOrden(res,l2,l2.first());
		insertOrden(res,l1,l1.first());
		return res;
	}

	public static  <E extends Comparable<E>> void insertOrden(PositionList<Pair<E,Integer>> list, PositionList<Pair<E,Integer>> list2,Position<Pair<E,Integer>> cursor) {
		if (cursor!=null) {
			insertRec(list, cursor.element(), list.first());
			insertOrden(list,list2, list2.next(cursor));
		}
	}

	private static <E extends Comparable<E>> void insertRec( PositionList<Pair<E,Integer>> list,Pair<E,Integer> elem, Position<Pair<E,Integer>> cursor) {
		if (cursor == null) { // CASO BASICO
			list.addLast(elem); // SOLUCION BASICA
		}
		else if (elem.getLeft().compareTo(cursor.element().getLeft())<0) { // CASO BASICO
			list.addBefore(cursor, elem); // SOLUCION BASICA
		}
		else if (elem.getLeft().compareTo(cursor.element().getLeft())==0){
			int counter = elem.getRight();
			cursor.element().setRight(cursor.element().getRight() + counter);
		}else insertRec(list, elem, list.next(cursor));
	}
}
