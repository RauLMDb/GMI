package aed.loops;

public class Utils {
  public static int maxNumRepeated(Integer[] a, Integer elem) {
    int maxCount = 0;
    int currentCount = 0;
    
    for (int i=0; i < a.length; i++) 
      if (a[i].equals(elem)) {
	++currentCount;
      } else {
	  maxCount = Math.max(currentCount,maxCount);
        currentCount = 0;
      }

    return Math.max(currentCount,maxCount);
  }
}
