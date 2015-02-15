package com.sebastianbuza.p3_circuit.factories;

import com.sebastianbuza.p3_circuit.models.point.Gateway;
import com.sebastianbuza.p3_circuit.models.point.Point;
import com.sebastianbuza.p3_circuit.models.point.StartPoint;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

/**
 * Created by sebi on 2/15/2015.
 */
@Component
public class PointFactory {
    Point getPoint(String type, Boolean value){
        if(type.equals("start")){
            return new StartPoint(value);
        }else{
            throw new InvalidParameterException("invalid point");
        }
    }

    Point getPoint(String type, Point left, Point right, String operation){
        if(type.equals("gateway")){
            return new Gateway(left, right, operation);
        }else{
            throw new InvalidParameterException("invalid point");
        }
    }
}
