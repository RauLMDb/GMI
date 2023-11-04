package aed.tries;

import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.tree.GeneralTree;
import es.upm.aedlib.tree.LinkedGeneralTree;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;

public class DictImpl implements Dictionary {
	// A boolean because we need to know if a word ends in a node or not
	GeneralTree<Pair<Character,Boolean>> tree;

	public DictImpl() {
		tree = new LinkedGeneralTree<>();
		tree.addRoot(new Pair<>(null,false));
	}
	
	/**
	 * This method return the node in wich is located the last character of prefix whether it is true or not.
	 * If such does no exist return null 
	 * @param prefix
	 * @return
	 */
	private Position<Pair<Character,Boolean>> findPos(String prefix) {
		Position<Pair<Character,Boolean>> actual = tree.root();
		Iterator<Position<Pair<Character,Boolean>>> it = tree.children(actual).iterator();
		int i = 0;
		while (it.hasNext() && i < prefix.length() && actual != null) {
			actual = searchChildLabelledBy(prefix.charAt(i),actual);
			i++;
		}
		return actual;
	}
	/**
	 * This method return the node son which has the same chararcter as ch.
	 * If such does no exist return null
	 * @param ch
	 * @param pos
	 * @return
	 */
	private Position<Pair<Character,Boolean>> searchChildLabelledBy(char ch, Position<Pair<Character, Boolean>> pos) {
		Position<Pair<Character,Boolean>> res = null;
		Iterator<Position<Pair<Character,Boolean>>> it = tree.children(pos).iterator();
		while (it.hasNext() && res == null) {
			Position<Pair<Character,Boolean>> child = it.next();
			if (child.element().getLeft().equals(ch)) res = child;
		}
		return res;
	}
	/**
	 * This method add alphabetically a new node child of pos with pair.
	 * If such does exist and is false, it replace the pair in these node 
	 * @param pair
	 * @param pos
	 * @return
	 */
	
	private Position<Pair<Character,Boolean>> addChildAlphabetically(Pair<Character, Boolean> pair,	Position<Pair<Character,Boolean>> pos) {
		Position<Pair<Character,Boolean>> res = null;
		Iterator<Position<Pair<Character, Boolean>>> it = tree.children(pos).iterator();
		if (it.hasNext()) {
			Position<Pair<Character,Boolean>> cursor = it.next();
			while (it.hasNext() && cursor.element().getLeft().compareTo(pair.getLeft()) < 0) 
				cursor = it.next();
			if (cursor.element().getLeft().equals(pair.getLeft())) {
				if (Boolean.FALSE.equals(cursor.element().getRight())) tree.set(cursor,pair);
				res = cursor;
			} else if (cursor.element().getLeft().compareTo(pair.getLeft()) < 0) {
				res = tree.addChildLast(pos,pair);
			}
		}
		if (res == null) res = tree.addChildFirst(pos,pair);
		return res;
	}
	
	/**
	 * This method visit in pre-order all the nodes conected with pos 
	 * and generates a string with its characters concatened with prefix.
	 * If any node is true, add the string to words
	 * @param words
	 * @param pos
	 * @param prefix
	 */
	
	private void searchWordsBeguiningWithPrefix(PositionList<String> words,	Position<Pair<Character,Boolean>> pos,String prefix) {
		for (Position<Pair<Character,Boolean>> child : tree.children(pos)) {
			String word = prefix + child.element().getLeft();
			if (Boolean.TRUE.equals(child.element().getRight()))words.addLast(word);
			searchWordsBeguiningWithPrefix(words,child,word);
		}
	}

	public void add(String word) {
		if (word == null || word.equals("")) throw new IllegalArgumentException();
		Position<Pair<Character,Boolean>> pos = tree.root();
		for (int i = 0; i < word.length(); i++) {
			if (i == word.length() - 1)	addChildAlphabetically(new Pair<>(word.charAt(i),true),pos);
			else pos = addChildAlphabetically(new Pair<>(word.charAt(i),false),pos);
		}
	}

	public void delete(String word) {
		if (word == null || word.equals("")) throw new IllegalArgumentException();
		Position<Pair<Character,Boolean>> pos = findPos(word);
		if (pos != null) pos.element().setRight(false);
	}

	public boolean isIncluded(String word) {
		if (word == null || word.equals("")) throw new IllegalArgumentException();
		Position<Pair<Character, Boolean>> pos = findPos(word);
		return pos != null && pos.element().getRight();
	}

	public PositionList<String> wordsBeginningWithPrefix(String prefix) {
		if (prefix == null)	throw new IllegalArgumentException();
		PositionList<String> words = new NodePositionList<>();
		Position<Pair<Character,Boolean>> pos = findPos(prefix);
		pos = (pos != null) ? pos : tree.root();
		if (Boolean.TRUE.equals(pos.element().getRight())) words.addFirst(prefix);
		searchWordsBeguiningWithPrefix(words,pos,prefix);
		return words;
	}
}