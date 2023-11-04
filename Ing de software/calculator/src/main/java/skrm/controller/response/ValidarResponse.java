package skrm.controller.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.geotools.data.simple.SimpleFeatureCollection;
import skrm.utils.FeatureObject;

public class ValidarResponse {

    private boolean valido;
    private double area;
    private double presupuesto;

    public ValidarResponse(boolean valido, double area, double presupuesto) {
        this.valido = valido;
        this.area = area;
        this.presupuesto = presupuesto;
    }

    public boolean getValido() { return valido; }
    public int getArea() { return (int) area; }
    public int getPresupuesto() { return (int) presupuesto; }

}
