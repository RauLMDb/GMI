package skrm.controller.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.geotools.data.simple.SimpleFeatureCollection;
import skrm.utils.FeatureObject;

public class OcupadosResponse {

    private String[] coordinates;

    public OcupadosResponse(String[] coordinates) {
        this.coordinates = coordinates;
    }

    public String[] getCoordinates() { return this.coordinates; }

}
