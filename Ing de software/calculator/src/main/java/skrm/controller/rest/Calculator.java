package skrm.controller.rest;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.locationtech.jts.geom.Geometry;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Calculator {

    public enum TipoArea { AGRARIA, RESIDENCIAL }

    double calcularSuperficie(SimpleFeatureCollection featureCollection) throws FactoryException, TransformException {

        double area = 0.0;
        SimpleFeatureIterator fit = featureCollection.features();

        CoordinateReferenceSystem inputCRS = CRS.decode("EPSG:4326"); // WGS84
        CoordinateReferenceSystem outputCRS = CRS.decode("EPSG:3857"); // Web Mercator
        MathTransform transform = CRS.findMathTransform(inputCRS, outputCRS);

        while (fit.hasNext()) {
            SimpleFeature feature = fit.next();
            Geometry geometry = (Geometry) feature.getDefaultGeometry();

            // Transformar la geometría al sistema de coordenadas de salida
            Geometry transformedGeometry = JTS.transform(geometry, transform);

            // Calcular el área en metros cuadrados
            area += transformedGeometry.getArea();
        }

        return 0.759 * area;
    }

    double calcularPresupuesto(SimpleFeatureCollection featureCollection, TipoArea tipoArea) throws FactoryException, TransformException {
        double x = calcularSuperficie(featureCollection);
        switch (tipoArea) {
            case AGRARIA:
                return (94.64 + 165 + 257.67 + (1233.33 + 900 + 965 + 25.02*3) / 263700) * x * 0.01 * 0.04725;
            case RESIDENCIAL:
                return (94.64 + (1233.33 + 900 + 965 + 25.02 + 1200) / 82.9) * x * 0.2 * 0.5;
            default:
                return 0;
        }
    }


}
