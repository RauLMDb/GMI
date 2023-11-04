package aed.orderedmap;

import java.util.Comparator;


public class StringComparator implements Comparator<String> {
    public StringComparator() { }

    public int compare(String o1, String o2) {
	return o1.compareTo(o2);
    }

  public String toString() {
    return "cmp";
  }
}

