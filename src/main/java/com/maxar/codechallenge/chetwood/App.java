package com.maxar.codechallenge.chetwood;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxar.codechallenege.chetwood.json.JsonParser;
import com.maxar.codechallenge.chetwood.entity.Polygon;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JsonProcessingException {

        if(args.length == 0) {
            System.out.println("Please add GeoJSON file path");
            exit(0);
        }

        String filePath = args[0];

        Polygon polygon = null;
        try {
            polygon = (Polygon) JsonParser.getGeoJsonObject(new FileInputStream(filePath),
                Polygon.class);

            System.out.println("=================================================================");
            System.out.println();
            System.out.print("bbox=");
            System.out.println(Arrays.toString(polygon.getBbox()));
            System.out.println();
            System.out.println("=================================================================");
        } catch (IOException e) {
            System.out.println("Error: Unexpected I/O error has occurred.");
            e.printStackTrace();
        }
    }
}
