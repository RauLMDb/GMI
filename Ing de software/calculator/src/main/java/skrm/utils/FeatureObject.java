package skrm.utils;

import com.google.gson.Gson;

public class FeatureObject {
    private String[] coordinates;

    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }

    private Double[][][] _StringToDouble(String[] str) {
        Double[][][] res = new Double[str.length][][];

        for (int i = 0; i < coordinates.length; i++) {
            res[i] = new Gson().fromJson(str[i], Double[][].class);
        }

        return res;
    }

    public Double[][][] getCoordinates() {
        return _StringToDouble(coordinates);
    }
}
