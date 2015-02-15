package com.sebastianbuza.p3_circuit.models.point;

/**
 * Created by sebi on 2/15/2015.
 */
public class StartPoint implements Point {

    private Boolean value;

    public StartPoint(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean isTrue() {
        return value;
    }
}
