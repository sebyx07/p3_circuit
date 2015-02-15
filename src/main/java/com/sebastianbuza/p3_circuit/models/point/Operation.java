package com.sebastianbuza.p3_circuit.models.point;

/**
 * Created by sebi on 2/15/2015.
 */
public enum Operation {
    AND("&&"),
    OR("||"),
    XOR("^"),
    NOT("!");

    private String operation;

    Operation(String operation){
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public Boolean isEqualTo(String str){
        return operation.equals(str);
    }

    public Boolean runOperation(Point first, Point second){
        switch (this){
            case AND:
                return first.isTrue() && second.isTrue();
            case OR:
                return first.isTrue() || second.isTrue();
            case XOR:
                return first.isTrue() ^ second.isTrue();
            case NOT:
                if(first != null){
                    return !first.isTrue();
                }else if(second != null){
                    return !second.isTrue();
                }
            default:
                return false;
        }
    }

    public Boolean runOperation(Point p) {
        return this == NOT && !p.isTrue();
    }
}
