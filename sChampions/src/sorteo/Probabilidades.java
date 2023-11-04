package sorteo;

import permutacion.*;

public interface Probabilidades {

    public static int casos(String t1, String t2) {
        boolean team = false;
        int t = 0;
        if (!"".equals(t2)) {
            t = t2.charAt(0) - 48;
        }
        int[] indices;
        String[] elements = { "0", "1", "2", "3", "4", "5", "6", "7" };
        Permutaciones x = new Permutaciones(elements.length);
        StringBuilder permutation;
        int j = 0;
        while (x.hasMore()) {
            permutation = new StringBuilder();
            indices = x.getNext();
            for (int i = 0; i < indices.length; i++) {
                permutation.append(elements[indices[i]]);
            }
            boolean grupo = !"0".equals(permutation.substring(0, 1)) && !"1".equals(permutation.substring(1, 2))
                    && !"2".equals(permutation.substring(2, 3)) && !"3".equals(permutation.substring(3, 4))
                    && !"4".equals(permutation.substring(4, 5)) && !"5".equals(permutation.substring(5, 6))
                    && !"6".equals(permutation.substring(6, 7)) && !"7".equals(permutation.substring(7, 8));

            boolean pais = !"0".equals(permutation.substring(6, 7)) && !"0".equals(permutation.substring(3, 4))
                    && !"0".equals(permutation.substring(4, 5)) && !"2".equals(permutation.substring(0, 1))
                    && !"4".equals(permutation.substring(0, 1)) && !"3".equals(permutation.substring(2, 3))
                    && !"5".equals(permutation.substring(2, 3)) && !"6".equals(permutation.substring(2, 3));
            team = t1.equals(permutation.substring(t, t + 1));
            if ("".equals(t1) && "".equals(t2))
                team = true;
            if (t == -1 && !"".equals(t1))
                return -1;
            if (team && pais && grupo)
                j++;
        }
        return j;
    }
}
