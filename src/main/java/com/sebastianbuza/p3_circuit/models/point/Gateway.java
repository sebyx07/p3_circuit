package com.sebastianbuza.p3_circuit.models.point;

import java.security.InvalidParameterException;

/**
 * Created by sebi on 2/15/2015.
 */
public class Gateway implements Point {

    private Point left;
    private Point right;
    private Boolean value;
    private Operation operation;

    public Gateway(Point left, Point right, String operationString) throws InvalidParameterException{
        this.left = left;
        this.right = right;
        this.operation = calculateOperation(operationString);
    }

    public Gateway(Point left, String operationString) throws InvalidParameterException{
        this.left = left;
        this.operation = calculateOperation(operationString);
    }

    @Override
    public Boolean isTrue() {
        if(value == null){
            value = calculateValue();
        }
        return value;
    }

    private Boolean calculateValue(){
        if(right != null){
            value = operation.runOperation(left, right);
        }else{
            value = operation.runOperation(left);
        }
        return value;
    }

    private Operation calculateOperation(String str) throws InvalidParameterException {
        Operation result = null;

        for(Operation o : Operation.values()){
            if (o.isEqualTo(str)){
                result = o;
                break;
            }
        }

        if(result == null){
            throw new InvalidParameterException("unknown operation");
        }

        if(result == Operation.NOT && right != null){
            throw new InvalidParameterException("not cannot work with 2 operators");
        }

        return result;
    }

    public Point getLeft() {
        return left;
    }

    public Point getRight() {
        return right;
    }

    public Operation getOperation() {
        return operation;
    }
}
