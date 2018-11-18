package backEnd.Objects.finalStr;

import backEnd.Objects.dataType;
import backEnd.langConstants.languageConstants;
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
    languageConstants languageC = new languageConstants();

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

    /**
     * This method create the text to be print into the out file of 3 Directions
     * Code
     *
     * @return
     */
    public String getText() {
        if (operation == languageC.MAS_ID) {
            return getOpText(languageC.MAS);
        } else if (operation == languageC.MENOS_ID) {
            return getOpText(languageC.MENOS);
        } else if (operation == languageC.POR_ID) {
            return getOpText(languageC.POR);
        } else if (operation == languageC.DIV_ID) {
            return getOpText(languageC.DIV);
        } else if (operation == languageC.IF_ID) {
            return (languageC.IF_NAME + languageC.PAR_ABIERTO + operator + languageC.PAR_CERRADO + " " + languageC.GOTO_STR + " " + operando_1);
        } else if (operation == languageC.GOTO_STR_ID) {
            return (languageC.GOTO_STR + " " + operator);
        } else if (operation == languageC.FLAG_STR_ID) {
            return (operator + languageC.FLAG_STR);
        } else if (operation == languageC.WRITE_ID) {
            return (languageC.WRITE + languageC.PAR_ABIERTO + operator + languageC.PAR_CERRADO);
        } else if (operation == languageC.ASIGNAR_ID) {
            return (operator + " " + languageC.ASIGNAR + " " + operando_1);
        } else {
            return null;
        }
    }

    /**
     * This method return the logic operation for '>', '<', '!=', '=='
     *
     * like: "x < 4" or "y != N"
     *
     * @return
     */
    public String getLogicOp() {
        if (operation == languageC.MAYOR_Q_ID) {
            return getOp(languageC.MAYOR_Q);
        } else if (operation == languageC.MENOR_Q_ID) {
            return getOp(languageC.MENOR_Q);
        } else if (operation == languageC.DIF_ID) {
            return getOp(languageC.DIF);
        } else if (operation == languageC.IGUAL_ID) {
            return getOp(languageC.IGUAL);
        } else {
            return null;
        }
    }

    private String getOpText(String op) {
        return (operator + " " + languageC.ASIGNAR + " " + getOp(op));
    }

    private String getOp(String op) {
        return (operando_1 + " " + op + " " + operando_2);
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
