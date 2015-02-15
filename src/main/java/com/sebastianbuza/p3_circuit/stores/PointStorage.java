package com.sebastianbuza.p3_circuit.stores;

import com.sebastianbuza.p3_circuit.models.point.Point;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sebi on 2/15/2015.
 */
@Component
public class PointStorage {

    private Map<String, Point> store;

    public PointStorage() {
        store = new HashMap<String, Point>();
    }

    public Map<String, Point> getStore() {
        return store;
    }
}
