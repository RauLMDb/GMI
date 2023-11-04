package skrm.controller.rest;

import com.google.gson.Gson;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.geometry.coordinate.Polygon;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skrm.controller.response.CalcResponse;
import org.locationtech.jts.geom.GeometryFactory;
import skrm.controller.response.OcupadosResponse;
import skrm.controller.response.ValidarResponse;
import skrm.utils.FeatureObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BackendCalculator {

    private DefaultFeatureCollection Ocupados = null;
    @CrossOrigin
    @PostMapping(path = "/calcSuperficie")
    public ResponseEntity<CalcResponse> calcSuperficie(@RequestBody FeatureObject featureObject) throws IOException, FactoryException, TransformException {
        Calculator calculator = new Calculator();

        // Creamos la featureCollection -- Solo un polígono posible
        DefaultFeatureCollection featureCollection = new DefaultFeatureCollection();
        Double[][][] coordinates = featureObject.getCoordinates();
        SimpleFeature feature = _coordsToFeature(coordinates[0]);
        featureCollection.add(feature);

        // Calculamos el área y el presupuesto
        double area = calculator.calcularSuperficie(featureCollection);
        double presupuesto = calculator.calcularPresupuesto(featureCollection, Calculator.TipoArea.RESIDENCIAL);

        CalcResponse calcResponse = new CalcResponse(_getCoordinatesFromCollection(featureCollection)[0], area, presupuesto);

        return new ResponseEntity<CalcResponse>(calcResponse,HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(path = "/validar")
    public ResponseEntity<ValidarResponse> validar(@RequestBody FeatureObject featureObject) throws IOException, FactoryException, TransformException {
        Calculator calculator = new Calculator();
        boolean zonaValida = false;
        // Creamos la featureCollection -- Solo un polígono posible
        DefaultFeatureCollection featureCollection = new DefaultFeatureCollection();
        Double[][][] coordinates = featureObject.getCoordinates();
        SimpleFeature feature = _coordsToFeature(coordinates[0]);
        featureCollection.add(feature);
        // Calculamos el área y el presupuesto
        double area = calculator.calcularSuperficie(featureCollection);
        double presupuesto = calculator.calcularPresupuesto(featureCollection, Calculator.TipoArea.RESIDENCIAL);
        zonaValida = _validarZona(featureObject,Ocupados);
        ValidarResponse validarResponse = new ValidarResponse(zonaValida, area, presupuesto);

        return new ResponseEntity<ValidarResponse>(validarResponse,HttpStatus.OK);
    }
    private boolean _validarZona(FeatureObject featureSeleccionado, DefaultFeatureCollection Ocupados) throws IOException, FactoryException{
        boolean isOcupado = false;
        boolean madrid = false;
        int i = 0;
        FileReader reader = new FileReader("src/main/java/skrm/controller/pruebas/madridCoords.json");
        FeatureObject obj = (new Gson()).fromJson(reader, FeatureObject.class);
        // Creamos la featureCollection -- Solo un polígono posible
        DefaultFeatureCollection featureCollectionMadrid = new DefaultFeatureCollection();
        Double[][][] coordinates = obj.getCoordinates();
        for (Double[][] coords : coordinates) {
            SimpleFeature featureMadrid = _coordsToFeature(coords);
            featureCollectionMadrid.add(featureMadrid);
        }
        SimpleFeatureIterator itMadrid = featureCollectionMadrid.features();
        SimpleFeatureIterator itOcupados = Ocupados.features();
        while(itOcupados.hasNext() && !isOcupado){
            SimpleFeature featureOcupados = itOcupados.next();
            isOcupado |= _estaOcupado(featureSeleccionado, featureOcupados);
        }
        while (itMadrid.hasNext() && !madrid)
        {
            SimpleFeature featureMadrid = itMadrid.next();
            madrid |= _isInMadrid(featureSeleccionado,featureMadrid);
        }
        return isOcupado || !madrid ? false : true;
    }
    private boolean _estaOcupado(FeatureObject featureSeleccionado, SimpleFeature featureOcupados){
        boolean isOcupado = false;
        GeometryFactory factory = new GeometryFactory();
        Geometry gOcupados = (Geometry) featureOcupados.getDefaultGeometry();
        Coordinate[] coordselected = new Coordinate[featureSeleccionado.getCoordinates()[0].length];
        for(int i = 0; i< featureSeleccionado.getCoordinates()[0].length;i++) {
            coordselected[i] = new Coordinate(featureSeleccionado.getCoordinates()[0][i][0], featureSeleccionado.getCoordinates()[0][i][1]);
        }
        Coordinate[] coordOcupado = gOcupados.getCoordinates();

        Geometry geometrySelect = factory.createPolygon(new CoordinateArraySequence(coordselected));
        Geometry geometryOcuped = factory.createPolygon(new CoordinateArraySequence(coordOcupado));
        isOcupado = !geometrySelect.disjoint(geometryOcuped);

        return isOcupado;
    }
    private boolean _isInMadrid(FeatureObject featureSeleccionado, SimpleFeature featureMadrid){
        boolean madrid = false;
        GeometryFactory factory = new GeometryFactory();
        Geometry gMadrid = (Geometry) featureMadrid.getDefaultGeometry();
        Coordinate[] coordselected = new Coordinate[featureSeleccionado.getCoordinates()[0].length];
        for(int i = 0; i< featureSeleccionado.getCoordinates()[0].length;i++) {
            coordselected[i] = new Coordinate(featureSeleccionado.getCoordinates()[0][i][0], featureSeleccionado.getCoordinates()[0][i][1]);
        }
        Coordinate[] coordMadrid = gMadrid.getCoordinates();

        Geometry geometrySelect = factory.createPolygon(new CoordinateArraySequence(coordselected));
        Geometry geometryMadrid = factory.createPolygon(new CoordinateArraySequence(coordMadrid));
        madrid = geometrySelect.within(geometryMadrid);
        return madrid;
    }
    @CrossOrigin
    @GetMapping(path = "/getOcupados")
    public ResponseEntity<OcupadosResponse> getOcupados() throws IOException, FactoryException {
        FileReader reader = new FileReader("src/main/java/skrm/controller/pruebas/testEnvio.json");
        FeatureObject obj = (new Gson()).fromJson(reader, FeatureObject.class);

        // Creamos la featureCollection -- Solo un polígono posible
        DefaultFeatureCollection featureCollection = new DefaultFeatureCollection();
        Double[][][] coordinates = obj.getCoordinates();
        for (Double[][] coords : coordinates) {
            SimpleFeature feature = _coordsToFeature(coords);
            featureCollection.add(feature);
        }

        OcupadosResponse ocupadosResponse = new OcupadosResponse(_getCoordinatesFromCollection(featureCollection));
        Ocupados = featureCollection;
        return new ResponseEntity<OcupadosResponse>(ocupadosResponse,HttpStatus.OK);
    }

    private String _readFile(String path) throws FileNotFoundException {
        File featuresJsonStringFile = new File(path);
        Scanner myReader = new Scanner(featuresJsonStringFile);
        StringBuilder featuresJsonString = new StringBuilder();
        while (myReader.hasNextLine())  featuresJsonString.append(myReader.nextLine());
        myReader.close();

        return featuresJsonString.toString();
    }

    private SimpleFeature _coordsToFeature(Double[][] coordinates) throws FactoryException {
        Gson gson = new Gson();

        // Creamos un SimpleFeatureType para nuestro objeto Feature
        SimpleFeatureTypeBuilder featureTypeBuilder = new SimpleFeatureTypeBuilder();
        featureTypeBuilder.setName("MiFeatureType");
        featureTypeBuilder.setCRS(CRS.decode("EPSG:4326")); // Establecemos un CRS
        featureTypeBuilder.add("geom", Polygon.class); // Añadimos un atributo para la geometría
        featureTypeBuilder.setDefaultGeometry("geom"); // Establecemos la geometría predeterminada
        SimpleFeatureType featureType = featureTypeBuilder.buildFeatureType();

        // Creamos un SimpleFeatureBuilder para nuestro objeto Feature
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(featureType);

        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

        Coordinate[] coords = new Coordinate[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            Double[] point = coordinates[i];
            coords[i] = new Coordinate(point[0], point[1]);
        }

        featureBuilder.add(geometryFactory.createPolygon(coords));

        return featureBuilder.buildFeature(null);
    }

    private String[] _getCoordinatesFromCollection(DefaultFeatureCollection featureCollection) {
        String[] res = new String[featureCollection.size()];
        SimpleFeatureIterator it = featureCollection.features();

        int index = 0;
        while (it.hasNext()) {
            res[index] = _getCoordinatesFromFeature(it.next());
            index++;
        }

        return res;
    }

    private String _getCoordinatesFromFeature(SimpleFeature feature) {
        Geometry g = (Geometry) feature.getDefaultGeometry();
        // System.out.print(g.getCoordinates().length);
        Coordinate[] coords = g.getCoordinates();
        Double[][] coordinates = new Double[coords.length][2];

        for (int i = 0; i < coords.length; i++) {
            coordinates[i][0] = coords[i].x;
            coordinates[i][1] = coords[i].y;
        }

        return new Gson().toJson(coordinates);

    }

}
