package backEnd.Objects;

import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class cuarteta {

    dataType dType;
    String operator;
    int operation;
    String operando_1;
    String operando_2;
    LinkedList<Integer> arrayDimensions;

    /**
     * This constructor is to set "FLAGS" like
     *
     * -> lf2:
     *
     * -> goto lf2
     *
     * @param operator
     * @param operation
     */
    public cuarteta(String operator, int operation) {
        this.operator = operator;
        this.operation = operation;
    }

    /**
     * Structure just to set values a = 10
     *
     * if ( d3 ) goto lf1 (operation id)
     *
     * @param dType
     * @param operator
     * @param operation
     * @param operando_1
     */
    public cuarteta(dataType dType, String operator, int operation, String operando_1) {
        this.dType = dType;
        this.operator = operator;
        this.operation = operation;
        this.operando_1 = operando_1;
        this.arrayDimensions = null;
        this.operando_2 = null;
    }

    /**
     * This method is to create a logic, arithmetic, and concatenation and other
     * kind of methods
     *
     * a = 10 + d2 ((operation) id)
     *
     * @param dType
     * @param operator
     * @param operation
     * @param operando_1
     * @param operando_2
     */
    public cuarteta(dataType dType, String operator, int operation, String operando_1, String operando_2) {
        this.dType = dType;
        this.operator = operator;
        this.operation = operation;
        this.operando_1 = operando_1;
        this.operando_2 = operando_2;
        this.arrayDimensions = null;
    }

    /**
     * This method is specific to create a new array object
     *
     * @param dType
     * @param operator
     * @param operation
     * @param arrayDimensions
     */
    public cuarteta(dataType dType, String operator, int operation, LinkedList<Integer> arrayDimensions) {
        this.dType = dType;
        this.operator = operator;
        this.operation = operation;
        this.arrayDimensions = arrayDimensions;
        this.operando_1 = null;
        this.operando_2 = null;

    }

    public dataType getdType() {
        return dType;
    }

    public void setdType(dataType dType) {
        this.dType = dType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public String getOperando_1() {
        return operando_1;
    }

    public void setOperando_1(String operando_1) {
        this.operando_1 = operando_1;
    }

    public String getOperando_2() {
        return operando_2;
    }

    public void setOperando_2(String operando_2) {
        this.operando_2 = operando_2;
    }

    public LinkedList<Integer> getArrayDimensions() {
        return arrayDimensions;
    }

    public void setArrayDimensions(LinkedList<Integer> arrayDimensions) {
        this.arrayDimensions = arrayDimensions;
    }

}
