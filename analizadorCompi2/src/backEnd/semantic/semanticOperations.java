/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.semantic;

import backEnd.Objects.finalStr.cuarteta;
import backEnd.Objects.*;
import backEnd.exceptions.InputsVaciosException;
import backEnd.langConstants.languageConstants;
import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class semanticOperations {

    private languageConstants constL = new languageConstants();
    private LinkedList<String> temp3dir = new LinkedList<>();
    private LinkedList<cuarteta> temp4thdir = new LinkedList<>();
    private int contador = 0;
    semanticManager semanticM;

    public semanticOperations(semanticManager semanticM) {
        this.semanticM = semanticM;
    }

    private void catchIncorrectValues(String operator, tempVar dato1, tempVar dato2, int row, int column, int type1, int type2) throws InputsVaciosException {
        if ((dato1.getCategory() != type1) && (dato2.getCategory() != type2)) {
            if (!constL.stringIsEmpty(dato1.getId3Dir()) && !constL.stringIsEmpty(dato2.getId3Dir())) {
                sendError(dato1.getId3Dir(), operator, dato1.getRow(), dato1.getColumn());
                sendError(dato2.getId3Dir(), operator, dato2.getRow(), dato2.getColumn());
            } else if (!constL.stringIsEmpty(dato1.getId3Dir())) {
                sendError(dato1.getId3Dir(), operator, dato1.getRow(), dato1.getColumn());
                sendError(constL.valueParsed(dato2), operator, row, column);
            } else if (!dato2.getId3Dir().replaceAll(" ", "").replaceAll("\t", "").isEmpty()) {
                sendError(constL.valueParsed(dato2), operator, row, column);
                sendError(dato2.getId3Dir(), operator, dato2.getRow(), dato2.getColumn());
            } else {
                sendError(constL.valueParsed(dato1), operator, row, column);
                sendError(constL.valueParsed(dato2), operator, row, column);
            }
        } else if (dato1.getCategory() != type1) {
            if (!constL.stringIsEmpty(dato1.getId3Dir())) {
                sendError(dato1.getId3Dir(), operator, dato1.getRow(), dato1.getColumn());
            } else {
                sendError(constL.valueParsed(dato1), operator, row, column);
            }
        } else {
            if (!constL.stringIsEmpty(dato2.getId3Dir())) {
                sendError(dato2.getId3Dir(), operator, dato2.getRow(), dato2.getColumn());
            } else {
                sendError(constL.valueParsed(dato2), operator, row, column);
            }
        }
        resetTemp3VarList();
    }

    private void sendError(String name, String operator, int row, int column) {
        semanticM.errorAndPlace(constL.AN_SEMANTICO, "Dato incompatible para la operacion>> " + operator + " << \nDato: " + name + " Fila: " + row + " Columna: " + column);
        //throw new InputsVaciosException("Dato incompatible para la operacion>> " + operator + " << \nDato: " + name + " Fila: " + row + " Columna: " + column);
    }

    private void sendError(String errorMessage) {
        semanticM.errorAndPlace(constL.AN_SEMANTICO, errorMessage);
    }

    /**
     * this method verify if the "id" already exist in the variable list, if it
     * doesn't verify the data then it return the "tempFinalVar" ready to be add
     * into the "Variables List"
     *
     * @param id
     * @param dato
     * @return
     */
    public tempFinalVar varToSave(String id, tempVar dato) {
        if (dato != null) {
            if (constL.stringIsEmpty(dato.getId3Dir())) {
                if (dato.getCategory() == constL.FLOAT) {
                    addToTemp3dir(id, String.valueOf(dato.getvFloat()), dato.getCategory());
                } else if (dato.getCategory() == constL.INTEGER) {
                    addToTemp3dir(id, String.valueOf(dato.getvInteger()), dato.getCategory());
                } else if (dato.getCategory() == constL.BOOLEAN) {
                    addToTemp3dir(id, String.valueOf(dato.isvBool()), dato.getCategory());
                } else if (dato.getCategory() == constL.STRING) {
                    addToTemp3dir(id, dato.getvString(), dato.getCategory());
                } else if (dato.getCategory() == constL.NO_TYPE_AUX) {
                    //addToTemp3dir(id);
                } else {
                    sendError("Dato incompatible para realizar la operacion en linea: " + dato.getRow());
                    resetTemp3VarList();
                    return null;
                }
            } else {
                addToTemp3dir(id, dato.getId3Dir(), dato.getCategory());
            }

            return new tempFinalVar(id, dato);
        } else {
            return null;
            //return new tempFinalVar(id, new tempVar(0, constL.NO_TYPE_AUX, 0, 0));
        }
    }

    /**
     * This method take care of all the possible operation (logic, arithmetic
     * and String concatenation)
     *
     * @param operator
     * @param dato1
     * @param dato2
     * @param row
     * @param column
     * @return
     * @throws InputsVaciosException
     */
    public tempVar operation(String operator, tempVar dato1, tempVar dato2, int row, int column) throws InputsVaciosException {
        if ((dato1 != null) && (dato2 != null)) {
            if (operator.equals(constL.MAS)) {
                if (((dato1.getCategory() == constL.FLOAT) || (dato1.getCategory() == constL.INTEGER)) && ((dato2.getCategory() == constL.FLOAT) || (dato2.getCategory() == constL.INTEGER))) {
                    return arithmeticOp(operator, dato1, dato2, row, column);
                } else {
                    return StringOp(dato1, dato2, row, column);
                }
            } else if (operator.equals(constL.MENOS) || operator.equals(constL.POR) || operator.equals(constL.DIV)) {
                return arithmeticOp(operator, dato1, dato2, row, column);
            } else if (operator.equals(constL.MAYOR_Q) || operator.equals(constL.MENOR_Q) || operator.equals(constL.IGUAL) || operator.equals(constL.DIF) || operator.equals(constL.AND) || operator.equals(constL.OR)) {
                return logicOp(operator, dato1, dato2, row, column);
            } else {
                sendError("Operacion: >> " + operator + " << inexistente");
                resetTemp3VarList();
                return null;
            }
        } else {
            return null;
        }
    }

    private tempVar logicOp(String operator, tempVar dato1, tempVar dato2, int row, int column) throws InputsVaciosException {
        if (operator.equals(constL.MAYOR_Q)) {
            if ((dato1.getCategory() == constL.DOUBLE_AUX) && (dato2.getCategory() == constL.DOUBLE_AUX)) {
                return new tempVar(dato1.getvDouble() > dato2.getvDouble(), constL.BOOLEAN, row, column, nameToTemp3Dir(dato1, dato2, constL.MAYOR_Q, constL.MAYOR_Q_ID));
            } else {
                catchIncorrectValues(operator, dato1, dato2, row, column, constL.DOUBLE_AUX, constL.DOUBLE_AUX);
                return null;
            }
        } else if (operator.equals(constL.MENOR_Q)) {
            if ((dato1.getCategory() == constL.DOUBLE_AUX) && (dato2.getCategory() == constL.DOUBLE_AUX)) {
                return new tempVar(dato1.getvDouble() < dato2.getvDouble(), constL.BOOLEAN, row, column, nameToTemp3Dir(dato1, dato2, constL.MENOR_Q, constL.MENOR_Q_ID));
            } else {
                catchIncorrectValues(operator, dato1, dato2, row, column, constL.DOUBLE_AUX, constL.DOUBLE_AUX);
                return null;
            }
        } else if (operator.equals(constL.IGUAL)) {
            if ((dato1.getCategory() == constL.DOUBLE_AUX) && (dato2.getCategory() == constL.DOUBLE_AUX)) {
                return new tempVar(dato1.getvDouble() == dato2.getvDouble(), constL.BOOLEAN, row, column, nameToTemp3Dir(dato1, dato2, constL.IGUAL, constL.IGUAL_ID));
            } else if ((dato1.getCategory() == constL.BOOLEAN) && (dato2.getCategory() == constL.BOOLEAN)) {
                return new tempVar(dato1.isvBool() == dato2.isvBool(), constL.BOOLEAN, row, column, nameToTemp3Dir(dato1, dato2, constL.IGUAL, constL.IGUAL_ID));
            } else if ((dato1.getCategory() == constL.STRING) && (dato2.getCategory() == constL.STRING)) {
                return new tempVar(dato1.getvString().equals(dato2.getvString()), constL.BOOLEAN, row, column, nameToTemp3Dir(dato1, dato2, constL.IGUAL, constL.IGUAL_ID));
            } else {
                catchIncorrectValues(operator, dato1, dato2, row, column, constL.DOUBLE_AUX, constL.DOUBLE_AUX);
                return null;
            }

        } else if (operator.equals(constL.DIF)) {
            if ((dato1.getCategory() == constL.DOUBLE_AUX) && (dato2.getCategory() == constL.DOUBLE_AUX)) {
                return new tempVar(dato1.getvDouble() != dato2.getvDouble(), constL.BOOLEAN, row, column, nameToTemp3Dir(dato1, dato2, constL.DIF, constL.DIF_ID));
            } else if ((dato1.getCategory() == constL.BOOLEAN) && (dato2.getCategory() == constL.BOOLEAN)) {
                return new tempVar(dato1.isvBool() != dato2.isvBool(), constL.BOOLEAN, row, column, nameToTemp3Dir(dato1, dato2, constL.DIF, constL.DIF_ID));
            } else if ((dato1.getCategory() == constL.STRING) && (dato2.getCategory() == constL.STRING)) {
                return new tempVar(!dato1.getvString().equals(dato2.getvString()), constL.BOOLEAN, row, column, nameToTemp3Dir(dato1, dato2, constL.DIF, constL.DIF_ID));
            } else {
                catchIncorrectValues(operator, dato1, dato2, row, column, constL.DOUBLE_AUX, constL.DOUBLE_AUX);
                return null;
            }

        } else if (operator.equals(constL.AND)) {
            if ((dato1.getCategory() == constL.BOOLEAN) && (dato2.getCategory() == constL.BOOLEAN)) {
                return new tempVar(dato1.isvBool() && dato2.isvBool(), constL.BOOLEAN, row, column, nameToTemp3Dir(dato1, dato2, constL.AND, constL.AND_ID));
            } else {
                catchIncorrectValues(operator, dato1, dato2, row, column, constL.BOOLEAN, constL.BOOLEAN);
                return null;
            }

        } else if (operator.equals(constL.OR)) {
            if ((dato1.getCategory() == constL.BOOLEAN) && (dato2.getCategory() == constL.BOOLEAN)) {
                return new tempVar(dato1.isvBool() || dato2.isvBool(), constL.BOOLEAN, row, column, nameToTemp3Dir(dato1, dato2, constL.OR, constL.OR_ID));
            } else {
                catchIncorrectValues(operator, dato1, dato2, row, column, constL.BOOLEAN, constL.BOOLEAN);
                return null;
            }
        } else {
            sendError("Operacion: >> " + operator + " << inexistente");
            resetTemp3VarList();
            return null;
        }
    }

    private tempVar arithmeticOp(String operator, tempVar dato1, tempVar dato2, int row, int column) throws InputsVaciosException {
        if (((dato1.getCategory() == constL.FLOAT) || (dato1.getCategory() == constL.INTEGER)) && ((dato2.getCategory() == constL.FLOAT) || (dato2.getCategory() == constL.INTEGER))) {
            if (operator.equals(constL.MAS)) {
                if ((dato1.getCategory() == constL.FLOAT) || (dato2.getCategory() == constL.FLOAT)) {
                    return new tempVar(0, constL.FLOAT, row, column, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));
                } else {
                    return new tempVar(0, constL.INTEGER, row, column, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));
                }
            } else if (operator.equals(constL.MENOS)) {
                if ((dato1.getCategory() == constL.FLOAT) || (dato2.getCategory() == constL.FLOAT)) {
                    return new tempVar(0, constL.FLOAT, row, column, nameToTemp3Dir(dato1, dato2, constL.MENOS, constL.MENOS_ID));
                } else {
                    return new tempVar(0, constL.INTEGER, row, column, nameToTemp3Dir(dato1, dato2, constL.MENOS, constL.MENOS_ID));
                }
            } else if (operator.equals(constL.POR)) {
                if ((dato1.getCategory() == constL.FLOAT) || (dato2.getCategory() == constL.FLOAT)) {
                    return new tempVar(0, constL.FLOAT, row, column, nameToTemp3Dir(dato1, dato2, constL.POR, constL.POR_ID));
                } else {
                    return new tempVar(0, constL.INTEGER, row, column, nameToTemp3Dir(dato1, dato2, constL.POR, constL.POR_ID));
                }
            } else if (operator.equals(constL.DIV)) {
                if ((dato1.getCategory() == constL.FLOAT) || (dato2.getCategory() == constL.FLOAT)) {
                    return new tempVar(0, constL.FLOAT, row, column, nameToTemp3Dir(dato1, dato2, constL.DIV, constL.DIV_ID));
                } else {
                    return new tempVar(0, constL.INTEGER, row, column, nameToTemp3Dir(dato1, dato2, constL.DIV, constL.DIV_ID));
                }
            } else {
                sendError("Operacion: >> " + operator + " << inexistente");
                return null;
            }
        } else {
            catchIncorrectValues(operator, dato1, dato2, row, column, constL.INTEGER, constL.INTEGER);
            return null;
        }
    }

    private tempVar StringOp(tempVar dato1, tempVar dato2, int row, int column) throws InputsVaciosException {
        if (((dato1.getCategory() == constL.INTEGER) || (dato1.getCategory() == constL.FLOAT)) && (dato2.getCategory() == constL.STRING)) {
            return new tempVar("", constL.STRING, row, column, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if (((dato1.getCategory() == constL.INTEGER) || (dato1.getCategory() == constL.FLOAT)) && (dato2.getCategory() == constL.BOOLEAN)) {
            return new tempVar("", constL.STRING, row, column, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if ((dato1.getCategory() == constL.BOOLEAN) && ((dato2.getCategory() == constL.INTEGER) || (dato2.getCategory() == constL.FLOAT))) {
            return new tempVar("", constL.STRING, row, column, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if ((dato1.getCategory() == constL.BOOLEAN) && (dato2.getCategory() == constL.BOOLEAN)) {
            return new tempVar("", constL.STRING, row, column, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if ((dato1.getCategory() == constL.BOOLEAN) && (dato2.getCategory() == constL.STRING)) {
            return new tempVar("", constL.STRING, row, column, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if ((dato1.getCategory() == constL.STRING) && ((dato2.getCategory() == constL.INTEGER) || (dato2.getCategory() == constL.FLOAT))) {
            return new tempVar("", constL.STRING, row, column, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if ((dato1.getCategory() == constL.STRING) && (dato2.getCategory() == constL.BOOLEAN)) {
            return new tempVar("", constL.STRING, row, column, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if ((dato1.getCategory() == constL.STRING) && (dato2.getCategory() == constL.STRING)) {
            return new tempVar("", constL.STRING, row, column, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else {
            catchIncorrectValues(constL.MAS, dato1, dato2, row, column, constL.STRING, constL.STRING);
            return null;
        }
    }

    private String nameToTemp3Dir(tempVar dato1, tempVar dato2, String operator, int opCode) throws InputsVaciosException {
        dataType tempType = semanticM.findDType(dato1.getCategory());
        if (constL.stringIsEmpty(dato1.getId3Dir()) && constL.stringIsEmpty(dato2.getId3Dir())) {
            String temp = constL.VARIABLE_LABLE + "" + contador;
            temp3dir.add(temp + "" + constL.ASIGNAR + "" + constL.valueParsed(dato1) + " " + operator + " " + constL.valueParsed(dato2));
            temp4thdir.add(new cuarteta(tempType, temp, opCode, constL.valueParsed(dato1), constL.valueParsed(dato2)));
            contador++;
            return temp;
        } else if (!constL.stringIsEmpty(dato1.getId3Dir()) && constL.stringIsEmpty(dato2.getId3Dir())) {
            String temp = constL.VARIABLE_LABLE + "" + contador;
            temp3dir.add(temp + "" + constL.ASIGNAR + "" + dato1.getId3Dir() + " " + operator + " " + constL.valueParsed(dato2));
            temp4thdir.add(new cuarteta(tempType, temp, opCode, dato1.getId3Dir(), constL.valueParsed(dato2)));
            contador++;
            return temp;
        } else if (constL.stringIsEmpty(dato1.getId3Dir()) && !constL.stringIsEmpty(dato2.getId3Dir())) {
            String temp = constL.VARIABLE_LABLE + "" + contador;
            temp3dir.add(temp + "" + constL.ASIGNAR + "" + constL.valueParsed(dato1) + " " + operator + " " + dato2.getId3Dir());
            temp4thdir.add(new cuarteta(tempType, temp, opCode, constL.valueParsed(dato1), dato2.getId3Dir()));
            contador++;
            return temp;
        } else {
            String temp = constL.VARIABLE_LABLE + "" + contador;
            temp3dir.add(temp + "" + constL.ASIGNAR + "" + dato1.getId3Dir() + " " + operator + " " + dato2.getId3Dir());
            temp4thdir.add(new cuarteta(tempType, temp, opCode, dato1.getId3Dir(), dato2.getId3Dir()));
            contador++;
            return temp;
        }
    }

    /**
     * just add the last step of the 3 directions code 'x = 2'
     *
     * @param id1
     * @param id2
     */
    private void addToTemp3dir(String id1, String id2, int tempT) {
        temp3dir.add(id1 + "" + constL.ASIGNAR + "" + id2);
        dataType tempType = semanticM.findDType(tempT);
        temp4thdir.add(new cuarteta(tempType, id1, constL.ASIGNAR_ID, id2));

    }

    /**
     * Just clear the 3 Directions temporal list, and 4th directions too.
     */
    public void resetTemp3VarList() {
        temp3dir.clear();
        temp4thdir.clear();
    }

    /**
     * return counter to 0
     */
    public void resetContador() {
        contador = 0;
    }

    public LinkedList<String> getTemp3dir() {
        return temp3dir;
    }

    public void setTemp3dir(LinkedList<String> temp3dir) {
        this.temp3dir = temp3dir;
    }

    public LinkedList<cuarteta> getTemp4thdir() {
        return temp4thdir;
    }

    public void setTemp4thdir(LinkedList<cuarteta> temp4thdir) {
        this.temp4thdir = temp4thdir;
    }

}
