package skrm.controller.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.geotools.data.simple.SimpleFeatureCollection;
import skrm.utils.FeatureObject;

public class CalcResponse {

    private String coordinates;
    private double area;
    private double presupuesto;

    public CalcResponse(String coordinates, double area, double presupuesto) {
        this.coordinates = coordinates;
        this.area = area;
        this.presupuesto = presupuesto;
    }

    public String getCoordinates() { return coordinates; }
    public int getArea() { return (int) area; }
    public int getPresupuesto() { return (int) presupuesto; }

}
