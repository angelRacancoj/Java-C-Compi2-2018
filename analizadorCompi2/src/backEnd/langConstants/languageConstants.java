/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.langConstants;

import backEnd.Objects.tempVar;
import backEnd.exceptions.InputsVaciosException;

/**
 *
 * @author angel
 */
public class languageConstants {

    public languageConstants() {
    }

    /*aux Operators*/
    public final int DOUBLE_AUX = -1;
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
    public int MAS_ID = 4;
    public int MENOS_ID = 5;
    public int POR_ID = 6;
    public int DIV_ID = 7;
    public int MAYOR_Q_ID = 8;
    public int MENOR_Q_ID = 9;
    public int IGUAL_ID = 10;
    public int DIF_ID = 11;
    public int AND_ID = 12;
    public int OR_ID = 13;
    public int ASIGNAR_ID = 14;
    public int ARRAY_ID = 15;
    public int TRUE_STR_ID = 16;
    public int FALSE_STR_ID = 17;
    public int _ID = 0;

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
            case DOUBLE_AUX:
                return String.valueOf(dato.getvDouble());
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
}
//      Structures to follow to create the 3 directions code

