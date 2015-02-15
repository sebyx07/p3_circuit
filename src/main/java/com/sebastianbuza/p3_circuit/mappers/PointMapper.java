package com.sebastianbuza.p3_circuit.mappers;

import com.sebastianbuza.p3_circuit.factories.PointFactory;
import com.sebastianbuza.p3_circuit.models.point.Gateway;
import com.sebastianbuza.p3_circuit.models.point.Point;
import com.sebastianbuza.p3_circuit.stores.PointStorage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by sebi on 2/15/2015.
 */
@Component
public class PointMapper {

    @Autowired
    private PointFactory pointFactory;

    @Autowired
    private PointStorage pointStorage;

    public PointMapper(PointFactory pointFactory, PointStorage pointStorage){
        this.pointFactory = pointFactory;
        this.pointStorage= pointStorage;
    }

    public PointMapper(){}

    private JSONParser parser = new JSONParser();

    public void map(FileReader fileReader) throws IOException, ParseException {
        Object obj = parser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;

        JSONArray points = (JSONArray) jsonObject.get("points");

        for (Object point : points) {
            JSONObject jsonPoint = (JSONObject) point;
            String name = (String) jsonPoint.get("name");
            String type = (String) jsonPoint.get("type");

            if (isPointStartPoint(type)) {
                Boolean value = (Boolean) jsonPoint.get("value");
                Point start = pointFactory.getPoint(type, value);
                //save
                pointStorage.getStore().put(name, start);
            } else {
                if (isPointGateway(type)) {
                    String operation = (String) jsonPoint.get("operation");
                    String left = (String) jsonPoint.get("left");
                    String right = (String) jsonPoint.get("right");
                    Point gateway = pointFactory.getPoint(type, operation, left, right);
                    //save
                    pointStorage.getStore().put(name, gateway);
                }
            }
        }

        for (Map.Entry<String, Point> entry : pointStorage.getStore().entrySet()){
            Point p = entry.getValue();

            if(p instanceof Gateway){
                Gateway pGateway = (Gateway) p;
                pGateway.setLeft(pointStorage.getStore().get(pGateway.getLeftString()));
                pGateway.setRight(pointStorage.getStore().get(pGateway.getRightString()));
            }
        }
    }

    private Boolean isPointStartPoint(String string){
        return string.equals("start");
    }

    private Boolean isPointGateway(String string){
        return string.equals("gateway");
    }
}
