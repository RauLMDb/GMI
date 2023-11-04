package upm.aed.indexedlist;

import java.util.Iterator;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.fifo.FIFO;
import es.upm.aedlib.fifo.FIFOList;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.lifo.LIFO;
import es.upm.aedlib.lifo.LIFOList;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.priorityqueue.HeapPriorityQueue;
import es.upm.aedlib.priorityqueue.PriorityQueue;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;
import es.upm.aedlib.tree.BinaryTree;
import es.upm.aedlib.tree.EmptyTreeException;
import es.upm.aedlib.tree.LinkedBinaryTree;
import es.upm.aedlib.tree.Tree;

public interface Utils<V, E> {

	public default boolean isEmpty(Iterable<E> iter) {
		Iterator<E> it = iter.iterator();
		return !it.hasNext();
	}

	private static <E> boolean member(E elem, Iterable<E> iter) {
		boolean res = false;
		Iterator<E> it = iter.iterator();
		while (it.hasNext() && !res) {
			E cursor = it.next();
			res |= elem.equals(cursor);
		}
		return res;
	}

	private static <E> boolean eqNull(E elem1, E elem2) {
		return elem1 == null && elem2 == null || elem1 != null && elem1.equals(elem2);
	}

	public static <E> IndexedList<E> deleteRepeated(IndexedList<E> l) {
		IndexedList<E> res = new ArrayIndexedList<>();
		for (int i = 0; i < l.size(); i++)
			if (!member(l.get(i), res)) {
				res.add(res.size(), l.get(i));
			}
		return res;
	}

	public static <E> boolean igualesPosition(PositionList<E> list1, PositionList<E> list2) {
		boolean res = true;
		Iterator<E> it1 = list1.iterator();
		Iterator<E> it2 = list2.iterator();
		if (list1.size() != list2.size())
			res = false;
		else {
			while (it1.hasNext() && it2.hasNext()) {
				res &= it1.next() == it2.next();
			}
		}
		return res;
	}

	public static boolean estaEquilibrado(Character[] texto) {
		boolean res = false;
		int tipo1 = 0;
		int tipo2 = 0;
		LIFO<Character> lifo = new LIFOList<>();
		for (int i = 0; i < texto.length; i++) {
			if (texto[i] == '(') {
				tipo1--;
				lifo.push(texto[i]);
			}
			if (texto[i] == '{') {
				tipo2--;
				lifo.push(texto[i]);
			}
			if (texto[i] == '}') {
				tipo2++;
				lifo.push(texto[i]);
			}
			if (texto[i] == ')') {
				tipo1++;
				lifo.push(texto[i]);
			}
		}
		if (tipo2 == 0 && tipo1 == 0)
			res = estaEquilibradoRec(lifo);
		return res;
	}

	public static int power(int base, int exponente) {
		if (exponente == 0)
			return 1;
		else
			return base * power(base, exponente - 1);
	}

	private static boolean estaEquilibradoRec(LIFO<Character> lifo) {
		boolean res = true;
		LIFO<Character> aux = lifo;
		int tipo1 = 0;
		int tipo2 = 0;
		while (!aux.isEmpty() && res) {
			Character c = aux.pop();
			if (c.equals(')'))
				tipo1++;
			else if (c.equals('}'))
				tipo2++;
			else if (c.equals('(')) {
				tipo1--;
				res = (tipo1 < 0) ? tipo1 > 0 : estaEquilibradoRec(aux);
			} else {
				tipo2--;
				res = (tipo2 < 0) ? tipo2 > 0 : estaEquilibradoRec(aux);
			}
		}
		return res;
	}

	public static PositionList<Integer> copiarHastaSumN(PositionList<Integer> list, int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		PositionList<Integer> res = new NodePositionList<>();
		int counter = 0;
		Position<Integer> cursor = list.first();
		while (cursor != null && counter < n) {
			if (cursor.element() + counter <= n)
				res.addLast(cursor.element());
			counter += cursor.element();
			cursor = list.next(cursor);
		}
		return res;
	}

	public static int sumaElementos(PositionList<Integer> list) {
		if (list == null)
			throw new IllegalArgumentException();
		Position<Integer> cursor = list.first();
		return sumaElementosRec(list, cursor);
	}

	private static int sumaElementosRec(PositionList<Integer> list, Position<Integer> cursor) {
		int res = 0;
		if (cursor != null) {
			res = sumaElementosRec(list, list.next(cursor));
			if (cursor.element() != null)
				res += cursor.element();
		}
		return res;
	}

	public static <E> int maxRepConsecutivas(Iterable<E> list) {
		int maxRep = 1;
		int repetActual = 1;
		Iterator<E> it = list.iterator();
		if (it.hasNext()) {
			E elem = it.next();
			while (it.hasNext()) {
				E nextElem = it.next();
				if (elem.equals(nextElem))
					repetActual++;
				else
					repetActual = 1;
				maxRep = (repetActual >= maxRep) ? repetActual : maxRep;
				elem = nextElem;
			}
		}
		return maxRep;
	}

	public static int maximo(PositionList<Integer> list) {
		Position<Integer> cursor = list.first();
		return maximoRec(list, cursor);
	}

	private static int maximoRec(PositionList<Integer> list, Position<Integer> cursor) {
		int res = 0;
		if (cursor != null) {
			Position<Integer> nextCursor = list.next(cursor);
			res = (cursor.element() < maximoRec(list, nextCursor)) ? maximoRec(list, nextCursor) : cursor.element();
		}
		return res;
	}

	public static <E> PositionList<E> copiaCircular(PositionList<E> list, Position<E> pos) {
		PositionList<E> copiaCircular = new NodePositionList<>();
		Position<E> cursor = pos;
		while (cursor != null) {
			copiaCircular.addLast(cursor.element());
			cursor = list.next(cursor);
		}
		cursor = list.first();
		while (cursor != pos) {
			copiaCircular.addLast(cursor.element());
			cursor = list.next(cursor);

		}
		return copiaCircular;
	}

	public static Integer maximoIter(Iterable<Integer> iter) {
		Integer max = 0;
		Iterator<Integer> it = iter.iterator();
		while (it.hasNext()) {
			Integer actual = it.next();
			max = (max < actual) ? actual : max;
		}
		return max;
	}

	public static PositionList<Integer> multiplyAndClean(PositionList<Integer> list, int n) {
		if (list == null)
			throw new IllegalArgumentException();
		Position<Integer> cursor = list.first();
		PositionList<Integer> res = new NodePositionList<>();
		multiplyAndCleanRec(list, n, cursor, res);
		return res;
	}

	private static void multiplyAndCleanRec(PositionList<Integer> list, int n, Position<Integer> cursor,
			PositionList<Integer> res) {
		if (cursor != null) {
			if (cursor.element() != null) {
				res.addLast(n * cursor.element());
				cursor = list.next(cursor);
			} else
				cursor = list.next(cursor);
			multiplyAndCleanRec(list, n, cursor, res);
		}
	}

	public static PositionList<Integer> flatNub(PositionList<Integer>[] arr) {
		PositionList<Integer> res = new NodePositionList<>();
		if (arr == null || arr.length == 0)
			return res;
		for (int i = 0; i < arr.length; i++) {
			for (Position<Integer> cursor = arr[i].first(); cursor != null; cursor = arr[i].next(cursor)) {
				if (!member(cursor.element(), res))
					res.addLast(cursor.element());
			}
		}
		return res;
	}

	public static <E> boolean iguales(Iterable<E> iter1, Iterable<E> iter2) {
		Iterator<E> it1 = iter1.iterator();
		Iterator<E> it2 = iter2.iterator();
		boolean res = true;
		while (it1.hasNext() && it2.hasNext() && res) {
			E elem1 = it1.next();
			E elem2 = it2.next();
			res &= eqNull(elem1, elem2);
		}
		if (it1.hasNext() || it2.hasNext())
			res = false;
		return res;
	}

	public static <E> int getApariciones(PositionList<Pair<E, Integer>> mset, E elem) {
		int apariciones = 0;
		for (Pair<E, Integer> cursor : mset) {
			if (elem.equals(cursor.getLeft()))
				apariciones = cursor.getRight();
		}
		return apariciones;
	}

	public static boolean arrayOrdenada(int[] list) {
		boolean estaOrdenada = true;
		int i = 0;
		Integer actual = list[i];
		while (i < list.length - 1) {
			Integer next = list[i + 1];
			estaOrdenada &= actual <= next;
			actual = next;
			i = i + 1;
		}

		return estaOrdenada;
	}

	public static boolean estaOrdenada(Iterable<Integer> list) {
		boolean estaOrdenada = true;
		Iterator<Integer> it = list.iterator();
		if (it.hasNext()) {
			Integer actual = it.next();
			while (it.hasNext()) {
				Integer next = it.next();
				estaOrdenada &= actual <= next;
				actual = next;
			}
		}
		return estaOrdenada;
	}

	public static <E> void quitarIguales(PositionList<E> list, E elem) {
		if (list == null)
			throw new IllegalArgumentException();
		Position<E> cursor = list.first();
		Position<E> aux = null;
		while (cursor != null) {
			aux = cursor;
			if (eqNull(cursor.element(), elem))
				list.remove(aux);
			cursor = list.next(cursor);
		}
	}

	public static boolean areAllGreaterThan(Iterable<Integer> iter, Integer elem) {
		boolean res = true;
		for (Integer first : iter) {
			if (first != null)
				res &= first >= elem;
		}
		return res;
	}

	public static PositionList<Integer> sumarEnBaseDecimal(PositionList<Integer> list1, PositionList<Integer> list2) {
		PositionList<Integer> res = new NodePositionList<>();
		int diferencia = list1.size() - list2.size();
		Position<Integer> cursor1 = list1.first();
		Position<Integer> cursor2 = list2.first();
		if (diferencia >= 0)
			for (int i = 0; i < diferencia; i++) {
				res.addLast(cursor1.element());
				cursor1 = list1.next(cursor1);
			}
		else
			for (int i = diferencia; i < 0; i++) {
				res.addLast(cursor2.element());
				cursor2 = list2.next(cursor2);
			}
		while (cursor1 != null && cursor2 != null) {
			res.addLast(cursor1.element() + cursor2.element());
			cursor1 = list1.next(cursor1);
			cursor2 = list2.next(cursor2);
		}
		return res;
	}

	public static <E> PositionList<E> recolectarDeNenN(Iterable<E> iter, int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		PositionList<E> res = new NodePositionList<>();
		Iterator<E> it = iter.iterator();
		E elem = null;
		for (int i = 0; it.hasNext(); i++) {
			elem = it.next();
			if (i % n == 0)
				res.addLast(elem);
		}
		return res;
	}

	public static Map<Character, Integer> contarApariciones(String texto) {
		Map<Character, Integer> res = new HashTableMap<>();
		Character caracter = null;
		for (int i = 0; i < texto.length(); i++) {
			caracter = texto.charAt(i);
			if (res.containsKey((caracter)))
				res.put(caracter, res.get(caracter) + 1);
			else
				res.put(caracter, 1);
		}
		return res;
	}

	public static <E> PositionList<E> elementosUnicos(PositionList<E> list) {
		PositionList<E> res = new NodePositionList<>();
		Position<E> cursor = list.first();
		while (cursor != null) {
			res.addLast(cursor.element());
			cursor = list.next(cursor);
		}
		cursor = res.first();
		Position<E> next = null;
		Position<E> aux = null;
		int counter = 1;
		while (cursor != null) {
			next = res.next(cursor);
			while (next != null) {
				aux = res.next(next);
				if (eqNull(cursor.element(), next.element())) {
					counter++;
					res.remove(next);
				}
				next = aux;
			}
			aux = cursor;
			cursor = res.next(cursor);
			if (counter > 1) {
				res.remove(aux);
				counter = 1;
			}
		}
		return res;
	}

	public static PositionList<Pair<Integer, Integer>> getDistanciasMayores(Iterable<Integer> iter, int min) {
		if (min < 0)
			throw new IllegalArgumentException();
		PositionList<Pair<Integer, Integer>> res = new NodePositionList<>();
		Iterator<Integer> it = iter.iterator();
		if (it.hasNext()) {
			Integer cursor = it.next();
			while (it.hasNext()) {
				Integer next = it.next();
				if (Math.abs(cursor - next) > min) {
					Pair<Integer, Integer> pair = new Pair<>(cursor, next);
					res.addLast(pair);
				}
				cursor = next;
			}
		}
		return res;
	}

	public static <E> PositionList<E> barajar(PositionList<E> list) {
		if (list.size() == 0)
			throw new IllegalArgumentException();
		PositionList<E> res = new NodePositionList<>();
		Position<E> start = list.first();
		Position<E> end = list.last();
		barajarRec(res, list, start, end);
		return res;
	}

	private static <E> void barajarRec(PositionList<E> res, PositionList<E> list, Position<E> start, Position<E> end) {
		if (start == end) {
			res.addLast(start.element());
		} else {
			res.addLast(start.element());
			res.addLast(end.element());
			barajarRec(res, list, list.next(start), list.prev(end));
		}
	}

	public static <E> void imprimirCaminosHojas(Tree<E> tree) {
		if (tree.isEmpty())
			return;
		imprimirTodosCaminos(tree, tree.root(), "");
	}

	private static <E> void imprimirTodosCaminos(Tree<E> tree, Position<E> v, String path) {
		path += v.element().toString();
		if (tree.isExternal(v))
			System.out.println(path);
		for (Position<E> w : tree.children(v))
			imprimirTodosCaminos(tree, w, path);
	}

	public static <E> PositionList<Position<E>> getExternal(Tree<E> tree, Position<E> root,
			PositionList<Position<E>> externals) {
		int counter = 0;
		for (Position<E> cursor : tree.children(root))
			if (!tree.isExternal(cursor)) {
				counter++;
				externals = getExternal(tree, cursor, externals);
			} else if (counter == 0)
				externals.addLast(cursor);
		return externals;
	}

	public static <E> int getDepth(Tree<E> tree) {
		return deepSearch(tree, tree.root(), 0, 0);
	}

	private static <E> int deepSearch(Tree<E> tree, Position<E> node, Integer depth, Integer actualDepth) {
		for (Position<E> cursor : tree.children(node)) {
			if (!tree.isExternal(cursor)) {
				depth = deepSearch(tree, cursor, depth, actualDepth + 1);
			} else
				depth = depth <= actualDepth + 1 ? actualDepth + 2 : depth;
		}
		return depth;
	}

	public static <E> PositionList<Position<E>> preorder(Tree<E> tree) {
		PositionList<Position<E>> res = new NodePositionList<>();
		preorderer(tree, tree.root(), res);
		return res;
	}

	private static <E> void preorderer(Tree<E> tree, Position<E> root, PositionList<Position<E>> res) {
		// AQUI " visitamos " el nodo "v"
		res.addLast(root);
		for (Position<E> cursor : tree.children(root)) {
			preorderer(tree, cursor, res);
		}
	}

	public static <E> PositionList<Position<E>> anchura(Tree<E> tree) {
		PositionList<Position<E>> res = new NodePositionList<>();
		FIFO<Position<E>> fifo = new FIFOList<>();
		fifo.enqueue(tree.root());
		while (!fifo.isEmpty()) {
			Position<E> v = fifo.dequeue();
			// AQUI " visitamos " el nodo "v"
			res.addLast(v);
			for (Position<E> w : tree.children(v)) {
				fifo.enqueue(w);
			}
		}
		return res;
	}

	public static <E> boolean memberTree(Tree<E> t, E elem) {
		boolean member = false;
		member = t.root().element().equals(elem);
		return preordererMember(t, t.root(), member, elem);
	}

	private static <E> boolean preordererMember(Tree<E> tree, Position<E> root, boolean member, E elem) {
		Iterator<Position<E>> it = tree.children(root).iterator();
		while (it.hasNext() && !member) {
			Position<E> cursor = it.next();
			member |= cursor.element().equals(elem);
			if (!member)
				member = preordererMember(tree, cursor, member, elem);
		}
		return member;
	}

	public static <E> PositionList<Position<E>> postorder(Tree<E> tree) {
		PositionList<Position<E>> res = new NodePositionList<>();
		postorderer(tree, tree.root(), res);
		return res;
	}

	public static <E> void postorderer(Tree<E> tree, Position<E> v, PositionList<Position<E>> res) {
		for (Position<E> w : tree.children(v)) {
			postorderer(tree, w, res);
		}
		// AQUI " visitamos " el nodo "v"
		res.addLast(v);
	}

	public static int sumaNodos2Hijos(BinaryTree<Integer> tree) {
		if (tree == null)
			throw new IllegalArgumentException();
		Position<Integer> root = tree.root();
		int suma = tree.hasLeft(root) && tree.hasRight(root) ? root.element() : 0;
		return sumaTree2Hijos(tree, tree.root(), suma);
	}

	private static int sumaTree2Hijos(BinaryTree<Integer> tree, Position<Integer> root, int suma) {
		for (Position<Integer> cursor : tree.children(root)) {
			if (tree.hasLeft(cursor) && tree.hasRight(cursor) && cursor.element() != null)
				suma += cursor.element();
			suma = sumaTree2Hijos(tree, cursor, suma);
		}
		return suma;
	}

	public static <E> E moreLefted(BinaryTree<E> tree) {
		int position = 0;
		HeapPriorityQueue<Integer, Position<E>> registry = new HeapPriorityQueue<>();
		positioning(tree, tree.root(), registry, position);
		return registry.dequeue().getValue().element();
	}

	private static <E> void positioning(BinaryTree<E> tree, Position<E> v,
			HeapPriorityQueue<Integer, Position<E>> registry, Integer pos) {
		for (Position<E> cursor : tree.children(v)) {
			if (cursor == tree.left(v)) {
				pos--;
				registry.enqueue(pos, cursor);
				positioning(tree, cursor, registry, pos);
			} else {
				pos++;
				registry.enqueue(pos - 1, cursor);
				positioning(tree, cursor, registry, pos);
			}
		}
	}

	public static <E> E leftmostLeaf(BinaryTree<E> t) throws EmptyTreeException {
		if (t.isEmpty())
			throw new EmptyTreeException("El árbol está vacío");
		Position<E> pos = t.root();
		while (!t.isExternal(pos)) {
			pos = t.hasLeft(pos) ? t.left(pos) : t.right(pos);
		}
		return pos.element();
	}

	public static <E> void leftSpine(BinaryTree<E> tree) {
		Position<E> pos = tree.root();
		Position<E> right = null;
		while (!tree.isExternal(pos)) {
			right = tree.right(pos);
			if (right != null)
				tree.removeSubTree(right);
			pos = tree.left(pos);
		}
	}

	public static <E> BinaryTree<E> espejo(BinaryTree<E> t) {
		BinaryTree<E> espejo = new LinkedBinaryTree<>();
		espejo.addRoot(t.root().element());
		copyPreorder(t, t.root(), espejo, espejo.root());
		return espejo;
	}

	private static <E> void copyPreorder(BinaryTree<E> t, Position<E> v, BinaryTree<E> copy, Position<E> w) {
		for (Position<E> cursor : t.children(v)) {
			if (cursor == t.right(v))
				copyPreorder(t, cursor, copy, copy.insertLeft(w, cursor.element()));
			else
				copyPreorder(t, cursor, copy, copy.insertRight(w, cursor.element()));

		}
	}

	public static void sumTree(BinaryTree<Integer> tree) {
		sumaPostorder(tree, tree.root());
	}

	private static void sumaPostorder(BinaryTree<Integer> t, Position<Integer> v) {
		Integer suma = 0;
		for (Position<Integer> cursor : t.children(v)) {
			sumaPostorder(t, cursor);
			suma += cursor.element();
		}
		if (!t.isExternal(v))
			t.set(v, suma);
	}

	public static <E> int ocurrencias(BinaryTree<E> t, E elem) {
		int counter = 0;
		if (t.root().element().equals(elem))
			counter++;
		return preorderCounter(t, elem, counter, t.root());
	}

	private static <E> int preorderCounter(BinaryTree<E> t, E elem, int counter, Position<E> v) {
		for (Position<E> w : t.children(v)) {
			if (w.element().equals(elem))
				counter++;
			counter = preorderCounter(t, elem, counter, w);
		}
		return counter;
	}

	public static <E> String toStringExp(BinaryTree<E> tree) {
		return toStringExpRec(tree, tree.root());
	}

	public static <E> String toStringExpRec(BinaryTree<E> tree, Position<E> node) {
		if (tree.isExternal(node))
			return node.element().toString();
		String s = "(";
		s += toStringExpRec(tree, tree.left(node));
		s += node.element().toString();
		s += toStringExpRec(tree, tree.right(node));
		s += ")";
		return s;
	}

	public static PositionList<String> ordenarPorNota(Map<String, Integer> map) {
		PositionList<String> res = new NodePositionList<>();
		PriorityQueue<Integer, String> aux = new HeapPriorityQueue<>();
		for (Entry<String, Integer> cursor : map.entries()) {
			aux.enqueue(cursor.getValue(), cursor.getKey());
		}
		int size = aux.size();
		for (int i = 0; i < size; i++) {
			res.addLast(aux.dequeue().getValue());
		}
		return res;
	}

	public static void printWordsInTrie(Tree<Pair<Character, Boolean>> t) {
		printWordsInTrieRec(t, t.root(), "");
	}

	private static void printWordsInTrieRec(Tree<Pair<Character, Boolean>> t, Position<Pair<Character, Boolean>> v,
			String res) {
		String aux = res;
		for (Position<Pair<Character, Boolean>> cursor : t.children(v)) {
			res += cursor.element().getLeft();
			if (cursor.element().getRight() == true) {
				System.out.println(res);
			}
			printWordsInTrieRec(t, cursor, res);
			res = aux;
		}
	}

	public static <V, E> PositionList<Vertex<V>> reachableNodesWithGrade(DirectedGraph<V, E> g, Vertex<V> v,
			int grade) {
		Set<Vertex<V>> visited = new HashTableMapSet<>();
		PositionList<Vertex<V>> res = new NodePositionList<>();
		reachableNodesWithGrade(g, v, grade, visited, res);
		return res;
	}

	private static <V, E> void reachableNodesWithGrade(DirectedGraph<V, E> g, Vertex<V> v, int grade,
			Set<Vertex<V>> visited, PositionList<Vertex<V>> res) {
		Iterator<Edge<E>> edges = g.outgoingEdges(v).iterator();
		if (!visited.contains(v)) {
			visited.add(v);
			if (g.outDegree(v) <= grade) {
				res.addLast(v);
			}
		}
		while(edges.hasNext()) {
			v = g.endVertex(edges.next());
			while (!visited.contains(v) && visited.size() != g.size()) {
				reachableNodesWithGrade(g, v, grade, visited, res);
			}
		}
	}

	private static int[] potencias(int n, int hasta) {
		int[] res = new int[hasta];
		for (int i = 0; i < hasta; i++) {
			res[i] = power(n, i);
		}
		return res;
	}

	public static PositionList<Integer> unidades(int n) {
		PositionList<Integer> res = new NodePositionList<>();
		for (int i = 1; i < n; i++) {
			if (mcd(i, n) == 1)
				res.addLast(i);
		}
		return res;
	}

	public static boolean esCiclico(PositionList<Integer> g, int n) {
		boolean esCiclico = false;
		Iterator<Integer> it = g.iterator();
		while (it.hasNext()) {
			HashTableMap<Integer, Integer> restos = new HashTableMap<>();
			int[] potencias = potencias(it.next(), n + 1);
			int i = 0;
			while (i < potencias.length) {
				int resto = potencias[i] % n;
				if (!restos.containsKey(resto)) {
					restos.put(resto, null);
				}
				i++;
			}
			esCiclico |= g.size() == restos.size();
		}
		return esCiclico;
	}

	public static int fact(int n) {
		if (n < 0)
			return 0;
		int res = 1;
		for (int i = 1; i <= n; i++)
			res *= i;
		return res;
	}

	private static int mcd(int n, int m) {
		int aux;
		int b = m;
		int a = n;
		while (b != 0) {
			aux = b;
			b = a % b;
			a = aux;
		}
		return a;
	}

	public static void probInt(float[] p) {
		int i = 1;
		float x = 0;
		while (i < p.length) {
			x += (1 - p[i - 1]) * p[i];
			System.out.println("(" + (i - 1) + "," + i + "]");
			System.out.println((1 - p[i - 1]) * p[i++]);

		}
		System.out.println(x);
	}

	public static final int M = 3;

	public static int invertir(int[] enteros, int m, int res) {
		System.out.println("hola");
		if (m == M || res != 1) {
			System.out.println("trabajo");
			int pointer = m % 2 == 0 ? m / 2 : (m - 1) / 2;
			for (int k = 0; k < pointer; k++) {
				int x = m - 1 - k;
				int aux = enteros[k];
				enteros[k] = enteros[x];
				enteros[x] = aux;
			}
			for(int j = 0;j<m;j++) {
				System.out.print(enteros[j]+" ");
			}
			System.out.println();
			m = 0;
		}
		System.out.println("adios");
		return m;
	}

	public static int[] multiplicarMatrices(String matriz) {
		int[] matrix = atoi(strToA(matriz));
		int m = matrix[0];
		int n = matrix[1];
		int p = matrix[2];
		int[] res = new int[m * p];
		int cont = 0;
		int sizeM2 = n * p;
		int controlM2 = 0;
		int pos = 0;
		int j = sizeM2 + m;
		int in = m;
		int i = m;
		while (i < matrix.length - sizeM2) {
			while (controlM2 < n) {
				int j1 = j + p * controlM2++;
				System.out.println("estado : i = "+i+",pos = "+pos+" , cont = "+cont+" ,control = "+controlM2+" ,j = "+j+" ,start = "+in+" ,j1 = "+j1);
				res[pos] += matrix[i++] * matrix[j1];
			}
			i = in;
			cont++;
			controlM2 = 0;
			j++;
			pos++;
			if (cont == m) {
				cont = 0;
				j = sizeM2 + m;
				in += n;
				i = in;
			}
		}
		for (int k = 0; k < res.length; k++) {
			System.out.print(res[k] + " ");
			if (k != 0 && k % m == m - 1)
				System.out.println();
		}
		return res;
	}

	/**
	 * separadores : ' ' y '\n'
	 * 
	 * @param str
	 * @return
	 */
	private static String[] strToA(String str) {
		int j = 0;
		int length = str.length();
		for (int i = 0; i < length - 1; i++) {
			char c = str.charAt(i);
			char c1 = str.charAt(i + 1);
			boolean separador = (c != ' ' && c != '\n') && (c1 == ' ' || c1 == '\n');
			if (separador)
				j++;
		}
		char last = str.charAt(length - 1);
		if (last != ' ' && last != '\n')
			j++;
		String[] res = new String[j];
		String c = "";
		int k = 0;
		for (int i = 0; i <= length; i++) {
			char ch = ' ';
			if (i < length)
				ch = str.charAt(i);
			if (ch != ' ' && ch != '\n') {
				c += ch;
			} else {
				res[k++] = c;
				c = "";
			}
		}
		return res;
	}

	private static int[] atoi(String[] matrix) {
		int l = matrix.length;
		int[] res = new int[l];
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < matrix[i].length(); j++) {
				char c = matrix[i].charAt(0);
				char ch = matrix[i].charAt(j);
				boolean positivo = (c >= 48 && c <= 57);
				if (!positivo) {
					if (j == 0) ;
					else if (j == matrix[i].length() - 1) {
						res[i] = -1 * (res[i] * 10 + (ch - 48));
					} else {
						res[i] = res[i] * 10 + (ch - 48);
					}
				} else {
					res[i] = res[i] * 10 + (ch - 48);
				}
			}
		}
		return res;
	}

	public static int resolverEc2(double a, double b, double c, double px1, double px2) {
		int caso = 0;
		double real = a == 0 ? -c / b : -b / 2 * a;
		double im = a == 0 ? 0 : (b * b - 4 * a * c) / 4 * a * a;
		if (a == 0 && b == 0) {
			caso = 3;
			px1 = 0;
			px2 = 0;
		}
		if (im < 0 && caso != 3) {
			caso = 2;
			im = Math.sqrt(-im);
		} else if (im == 0 && caso != 3) {
			im = Math.sqrt(-1);
			caso = 4;
		}
		px1 += real;
		px2 += im;
		if (im > 0 && caso != 3) {
			caso = 1;
			im = Math.sqrt(im);
			px1 = real + im;
			px2 = real - im;
		}

		return caso;
	}

	public static int maxmin(float[] n) {
		float min = n[0];
		float max = n[0];
		for (int i = 0;i<n.length;i++) {
			min = min > n[i] ? n[i] : min;
			max = max < n[i] ? n[i] : max;
		}
		System.out.println("min = "+min+"\nmax = "+max);
		return 0;
	}
	

	public static int matrizDinamica(int m,int p) {
		long[][] matrix = new long[m][p];
		for(int i = 0;i<m;i++) {
			for(int j = 0;j<p;j++) {
				if (i == 0|| j == 0)
					matrix[i][j] = 1;
				else { 
					matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
				}
			}
		}
		for(int i = 0;i<m;i++) {
			for(int j = 0;j<p;j++) {
				matrix[i][j] = matrix[i][j] > 1000000 ? 1 : matrix[i][j];
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		return 0;
	}


//	public static void main(String[] args) {
//		int[] indices;
//		String[] elements = {"a","b", "c", "d","e", "f","g","h"};
//		Permutaciones x = new Permutaciones(elements.length);
//		StringBuilder permutation;
//		int j = 1;
//		while (x.hasMore()) {
//			permutation = new StringBuilder();
//			indices = x.getNext();
//			for (int i = 0; i < indices.length; i++) {
//				permutation.append(elements[indices[i]]);
//			}
//			boolean grupo = !"a" .equals(permutation.substring(0,1))&&!"b" .equals(permutation.substring(1,2)) && !"c" .equals(permutation.substring(2,3)) 
//					&& !"d".equals(permutation.substring(3,4)) &&!"e" .equals(permutation.substring(4,5)) &&!"f" .equals(permutation.substring(5,6))
//					&&!"g".equals(permutation.substring(6,7))&&!"h" .equals(permutation.substring(7,8));
//			
//			boolean pais =
//					!"a".equals(permutation.substring(6,7))
//					&&!"a" .equals(permutation.substring(3,4))
//					&&!"a" .equals(permutation.substring(4,5))
//					&&!"c" .equals(permutation.substring(0,1))&&!"e" .equals(permutation.substring(0,1)) 
//					&&!"d" .equals(permutation.substring(2,3)) &&!"f".equals(permutation.substring(2,3))&&!"g" .equals(permutation.substring(2,3));
//			if("a".equals(permutation.substring(7,8))&&
//					
//					pais
//					&&grupo) {
//				System.out.println("12345678");
//				System.out.println(permutation.toString()+"|"+j++);
//			}
//		}
//
//	}

	public static void main(String[] args) { 
		System.out.print("HOLA"); 
	}
}