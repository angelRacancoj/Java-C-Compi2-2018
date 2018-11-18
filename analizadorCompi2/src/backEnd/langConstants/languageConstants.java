/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.langConstants;

import backEnd.Objects.finalStr.cuarteta;
import backEnd.Objects.tempVar;
import backEnd.exceptions.InputsVaciosException;
import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class languageConstants {

    public languageConstants() {
    }

    /*aux Operators*/
    //public final int DOUBLE_AUX = -1;
    //public final int BOOL_AUX = 2;
    //public final int STRING_AUX = 3;

    /*Data type*/
    public final String NO_TYPE = "Dont have type";
    public final int NO_TYPE_AUX = 0;
    public final int INTEGER = 1;
    public final int FLOAT = 2;
    public final int BOOLEAN = 3;
    public final int STRING = 4;
    public final String INTEGER_NAME = "Integer";
    public final String FLOAT_NAME = "Float";
    public final String BOOLEAN_NAME = "Boolean";
    public final String STRING_NAME = "String";

    /*Dimensions*/
    public final int FIRST_DIMESION = 1;

    /*Scope (ambito)*/
    public final int INIT_SCOPE = 0;

    /*Categories*/
    public final int VARIABLE = 1;

    /*The "String" structure for the flags inside the code
    -> lf2:
    -> goto lf2
     */
    public final String GOTO_STR = "goto";
    public final String FLAG_STR = ":";

    /*This are the lables to be use inside the "While" and "if" functions*/
    public final String IF_NAME = "if";
    public final String WHILE_NAME = "While";
    public final String ELSE_NAME = "else";

    public final String IF_LABLE = "lf";
    public final String WHILE_LABLE = "lw";
    public final String else_LABLE = "";

    /*Lables for logic operations*/
    public final String LOGIC_LABLE = "lb";
    public final String LOGIC_FLAG_LABLE = "b";

    /*The front name of the variables*/
    public final String VARIABLE_LABLE = "";

    /*Etiquetas para analizadores*/
    public final int AN_LEXICO = 1;
    public final int AN_SINTACTICO = 2;
    public final int AN_SEMANTICO = 3;
    public final String AN_LEXICO_STR = "Lexico";
    public final String AN_SINTACTICO_STR = "Sintactico";
    public final String AN_SEMANTICO_STR = "Semantico";

    /**
     * This list have all the logic, arithmetic and others operator
     */
    public final String MAS = "+";
    public final String MENOS = "-";
    public final String POR = "*";
    public final String DIV = "/";
    public final String MAYOR_Q = ">";
    public final String MENOR_Q = "<";
    public final String IGUAL = "==";
    public final String DIF = "!=";
    public final String AND = "&&";
    public final String OR = "||";
    public final String ASIGNAR = "=";
    public final String TRUE_STR = "true";
    public final String FALSE_STR = "false";
    public final String PAR_ABIERTO = "(";
    public final String PAR_CERRADO = ")";
    public final String WRITE = "print";

    /*Etiquetas para ciclos*/
    public final int GOTO_STR_ID = -1;
    public final int FLAG_STR_ID = 0;
    public final int IF_ID = 1;
    public final int WHILE_ID = 2;
    public final int ELSE_ID = 3;

    /*
    *Operation codes to be use at the "Cuatetas"
    *
    *NOTE: the logic operations are going to be taken as
    *default that are going to be inside of an IF function
     */
    public final int MAS_ID = 4;
    public final int MENOS_ID = 5;
    public final int POR_ID = 6;
    public final int DIV_ID = 7;
    public final int MAYOR_Q_ID = 8;
    public final int MENOR_Q_ID = 9;
    public final int IGUAL_ID = 10;
    public final int DIF_ID = 11;
    public final int AND_ID = 12;
    public final int OR_ID = 13;
    public final int ASIGNAR_ID = 14;
    public final int ARRAY_ID = 15;
    public final int TRUE_STR_ID = 16;
    public final int FALSE_STR_ID = 17;
    public final int WRITE_ID = 18;
    public final int _ID = 0;

    public boolean stringIsEmpty(String textoIn) {
        if (textoIn != null) {
            return textoIn.replaceAll(" ", "").replaceAll("\t", "").isEmpty();
        } else {
            return true;
        }
    }

    public boolean isInteger(double variable) {
        return (variable == (int) variable);
//        return ((variable == Math.floor(variable)) && !Double.isInfinite(variable));
    }

    public String clearString(String textoIn) {
        return textoIn.replaceAll("\"", "");
    }

    public String valueParsed(tempVar dato) throws InputsVaciosException {
        switch (dato.getCategory()) {
            case BOOLEAN:
                return String.valueOf(dato.isvBool());
            case FLOAT:
                return String.valueOf(dato.getvFloat());
            case INTEGER:
                return String.valueOf(dato.getvInteger());
            case STRING:
                return dato.getvString();
            default:
                return null;
        }
    }

    public String getDataTypeName(int type) {
        switch (type) {
            case BOOLEAN:
                return BOOLEAN_NAME;
            case STRING:
                return STRING_NAME;
            case INTEGER:
                return INTEGER_NAME;
            case FLOAT:
                return FLOAT_NAME;
            default:
                return null;
        }
    }

    public String getOpType(int codOp) {
        switch (codOp) {
            case MAYOR_Q_ID:
                return MAYOR_Q;
            case MENOR_Q_ID:
                return MENOR_Q;
            case IGUAL_ID:
                return IGUAL;
            case DIF_ID:
                return DIF;
            default:
                return null;
        }
    }

    public String getID_Value(tempVar dato) throws InputsVaciosException {
        if (stringIsEmpty(dato.getId3Dir())) {
            return valueParsed(dato);
        } else {
            return dato.getId3Dir();
        }
    }

    public void changeLables(LinkedList<cuarteta> structure, String oldLable, String newLable) {
        if (!structure.isEmpty()) {
            for (int i = 0; i < structure.size(); i++) {
                if (structure.get(i).getOperation() == IF_ID) {
                    if (structure.get(i).getOperando_1().equalsIgnoreCase(oldLable)) {
                        structure.get(i).setOperando_1(newLable);
                    }
                } else if (structure.get(i).getOperation() == GOTO_STR_ID) {
                    if (structure.get(i).getOperator().equalsIgnoreCase(oldLable)) {
                        structure.get(i).setOperator(newLable);
                    }
                }
            }
        }
    }
}
//      Structures to follow to create the 3 directions code

