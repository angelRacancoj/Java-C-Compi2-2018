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
    private LinkedList<cuarteta> temp4thdir = new LinkedList<>();
    private int contador = 0;
    private int boolCont = 0;
    semanticManager semanticM;
    public semanticArray semArray;

    public semanticOperations(semanticManager semanticM) {
        this.semanticM = semanticM;
        this.semArray = new semanticArray(semanticM);
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
        semanticM.errorAndPlace(constL.AN_SEMANTICO, "Dato incompatible para la operacion>> " + operator + " << \nDato: " + name + " Linea: " + row + " Columna: " + column);
        //throw new InputsVaciosException("Dato incompatible para la operacion>> " + operator + " << \nDato: " + name + " Fila: " + row + " Columna: " + column);
    }

    /**
     * This method add the error to the principal error list
     *
     * @param name
     * @param textOut
     * @param operator
     * @param row
     */
    public void sendError(String name, String textOut, String operator, int row) {
        semanticM.errorAndPlace(constL.AN_SEMANTICO, textOut + " >> " + operator + " << \nDato requerido: " + name + " Linea: " + row);
    }

    /**
     * Indicate the text to the message
     *
     * @param errorMessage
     */
    public void sendError(String errorMessage) {
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
            if (((dato1.getCategory() == constL.INTEGER) || (dato1.getCategory() == constL.FLOAT)) && ((dato2.getCategory() == constL.INTEGER) || (dato2.getCategory() == constL.FLOAT))) {
                return new tempVar(true, constL.BOOLEAN, row, column, dato1, dato2, constL.MAYOR_Q_ID);
            } else {
                catchIncorrectValues(operator, dato1, dato2, row, column, constL.FLOAT, constL.FLOAT);
                return null;
            }
        } else if (operator.equals(constL.MENOR_Q)) {
            if (((dato1.getCategory() == constL.INTEGER) || (dato1.getCategory() == constL.FLOAT)) && ((dato2.getCategory() == constL.INTEGER) || (dato2.getCategory() == constL.FLOAT))) {
                return new tempVar(true, constL.BOOLEAN, row, column, dato1, dato2, constL.MENOR_Q_ID);
            } else {
                catchIncorrectValues(operator, dato1, dato2, row, column, constL.FLOAT, constL.FLOAT);
                return null;
            }
        } else if (operator.equals(constL.IGUAL)) {
            if (((dato1.getCategory() == constL.INTEGER) || (dato1.getCategory() == constL.FLOAT)) && ((dato2.getCategory() == constL.INTEGER) || (dato2.getCategory() == constL.FLOAT))) {
                return new tempVar(true, constL.BOOLEAN, row, column, dato1, dato2, constL.IGUAL_ID);
            } else if ((dato1.getCategory() == constL.BOOLEAN) && (dato2.getCategory() == constL.BOOLEAN)) {
                return new tempVar(true, constL.BOOLEAN, row, column, dato1, dato2, constL.IGUAL_ID);
            } else if ((dato1.getCategory() == constL.STRING) && (dato2.getCategory() == constL.STRING)) {
                return new tempVar(true, constL.BOOLEAN, row, column, dato1, dato2, constL.IGUAL_ID);
            } else {
                catchIncorrectValues(operator, dato1, dato2, row, column, constL.FLOAT, constL.FLOAT);
                return null;
            }

        } else if (operator.equals(constL.DIF)) {
            if (((dato1.getCategory() == constL.INTEGER) || (dato1.getCategory() == constL.FLOAT)) && ((dato2.getCategory() == constL.INTEGER) || (dato2.getCategory() == constL.FLOAT))) {
                return new tempVar(true, constL.BOOLEAN, row, column, dato1, dato2, constL.DIF_ID);
            } else if ((dato1.getCategory() == constL.BOOLEAN) && (dato2.getCategory() == constL.BOOLEAN)) {
                return new tempVar(true, constL.BOOLEAN, row, column, dato1, dato2, constL.DIF_ID);
            } else if ((dato1.getCategory() == constL.STRING) && (dato2.getCategory() == constL.STRING)) {
                return new tempVar(true, constL.BOOLEAN, row, column, dato1, dato2, constL.DIF_ID);
            } else {
                catchIncorrectValues(operator, dato1, dato2, row, column, constL.FLOAT, constL.FLOAT);
                return null;
            }

        } else if (operator.equals(constL.AND)) {
            if ((dato1.getCategory() == constL.BOOLEAN) && (dato2.getCategory() == constL.BOOLEAN)) {
                return andStr(dato1, dato2, constL.AND_ID);
            } else {
                catchIncorrectValues(operator, dato1, dato2, row, column, constL.BOOLEAN, constL.BOOLEAN);
                return null;
            }

        } else if (operator.equals(constL.OR)) {
            if ((dato1.getCategory() == constL.BOOLEAN) && (dato2.getCategory() == constL.BOOLEAN)) {
                return orStr(dato1, dato2, constL.OR_ID);
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
                    return new tempVar(0, row, column, constL.FLOAT, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));
                } else {
                    return new tempVar(0, row, column, constL.INTEGER, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));
                }
            } else if (operator.equals(constL.MENOS)) {
                if ((dato1.getCategory() == constL.FLOAT) || (dato2.getCategory() == constL.FLOAT)) {
                    return new tempVar(0, row, column, constL.FLOAT, nameToTemp3Dir(dato1, dato2, constL.MENOS, constL.MENOS_ID));
                } else {
                    return new tempVar(0, row, column, constL.INTEGER, nameToTemp3Dir(dato1, dato2, constL.MENOS, constL.MENOS_ID));
                }
            } else if (operator.equals(constL.POR)) {
                if ((dato1.getCategory() == constL.FLOAT) || (dato2.getCategory() == constL.FLOAT)) {
                    return new tempVar(0, row, column, constL.FLOAT, nameToTemp3Dir(dato1, dato2, constL.POR, constL.POR_ID));
                } else {
                    return new tempVar(0, row, column, constL.INTEGER, nameToTemp3Dir(dato1, dato2, constL.POR, constL.POR_ID));
                }
            } else if (operator.equals(constL.DIV)) {
                if ((dato1.getCategory() == constL.FLOAT) || (dato2.getCategory() == constL.FLOAT)) {
                    return new tempVar(0, row, column, constL.FLOAT, nameToTemp3Dir(dato1, dato2, constL.DIV, constL.DIV_ID));
                } else {
                    return new tempVar(0, row, column, constL.INTEGER, nameToTemp3Dir(dato1, dato2, constL.DIV, constL.DIV_ID));
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
            return new tempVar("", row, column, constL.STRING, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if (((dato1.getCategory() == constL.INTEGER) || (dato1.getCategory() == constL.FLOAT)) && (dato2.getCategory() == constL.BOOLEAN)) {
            return new tempVar("", row, column, constL.STRING, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if ((dato1.getCategory() == constL.BOOLEAN) && ((dato2.getCategory() == constL.INTEGER) || (dato2.getCategory() == constL.FLOAT))) {
            return new tempVar("", row, column, constL.STRING, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if ((dato1.getCategory() == constL.BOOLEAN) && (dato2.getCategory() == constL.BOOLEAN)) {
            return new tempVar("", row, column, constL.STRING, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if ((dato1.getCategory() == constL.BOOLEAN) && (dato2.getCategory() == constL.STRING)) {
            return new tempVar("", row, column, constL.STRING, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if ((dato1.getCategory() == constL.STRING) && ((dato2.getCategory() == constL.INTEGER) || (dato2.getCategory() == constL.FLOAT))) {
            return new tempVar("", row, column, constL.STRING, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if ((dato1.getCategory() == constL.STRING) && (dato2.getCategory() == constL.BOOLEAN)) {
            return new tempVar("", row, column, constL.STRING, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else if ((dato1.getCategory() == constL.STRING) && (dato2.getCategory() == constL.STRING)) {
            return new tempVar("", row, column, constL.STRING, nameToTemp3Dir(dato1, dato2, constL.MAS, constL.MAS_ID));

        } else {
            catchIncorrectValues(constL.MAS, dato1, dato2, row, column, constL.STRING, constL.STRING);
            return null;
        }
    }

    private String nameToTemp3Dir(tempVar dato1, tempVar dato2, String operator, int opCode) throws InputsVaciosException {
        dataType tempType = semanticM.findDType(dato1.getCategory());
        if (constL.stringIsEmpty(dato1.getId3Dir()) && constL.stringIsEmpty(dato2.getId3Dir())) {
            String temp = constL.VARIABLE_LABLE + "" + contador;
            temp4thdir.add(new cuarteta(tempType, temp, opCode, constL.valueParsed(dato1), constL.valueParsed(dato2)));
            contador++;
            return temp;
        } else if (!constL.stringIsEmpty(dato1.getId3Dir()) && constL.stringIsEmpty(dato2.getId3Dir())) {
            String temp = constL.VARIABLE_LABLE + "" + contador;
            temp4thdir.add(new cuarteta(tempType, temp, opCode, dato1.getId3Dir(), constL.valueParsed(dato2)));
            contador++;
            return temp;
        } else if (constL.stringIsEmpty(dato1.getId3Dir()) && !constL.stringIsEmpty(dato2.getId3Dir())) {
            String temp = constL.VARIABLE_LABLE + "" + contador;
            temp4thdir.add(new cuarteta(tempType, temp, opCode, constL.valueParsed(dato1), dato2.getId3Dir()));
            contador++;
            return temp;
        } else {
            String temp = constL.VARIABLE_LABLE + "" + contador;
            temp4thdir.add(new cuarteta(tempType, temp, opCode, dato1.getId3Dir(), dato2.getId3Dir()));
            contador++;
            return temp;
        }
    }

    /**
     * This method create the structure for the or option at an logic operation
     *
     * this method use recursive way of the syntactic analyzer to create the OR
     * structure
     *
     * @param dato1
     * @param dato2
     * @param operation
     * @return
     * @throws InputsVaciosException
     */
    protected tempVar orStr(tempVar dato1, tempVar dato2, int operation) throws InputsVaciosException {
        LinkedList<cuarteta> listTemp = new LinkedList<>();
        if ((dato1.getOperation() != constL.AND_ID && dato1.getOperation() != constL.OR_ID) && (dato2.getOperation() != constL.AND_ID && dato2.getOperation() != constL.OR_ID)) {
            listTemp.add(new cuarteta(null, constL.getID_Value(dato1.getLeft()) + " " + constL.getOpType(dato1.getOperation()) + " " + constL.getID_Value(dato1.getRight()), constL.IF_ID, constL.LOGIC_LABLE + boolCont));
            listTemp.add(new cuarteta(null, constL.getID_Value(dato2.getLeft()) + " " + constL.getOpType(dato2.getOperation()) + " " + constL.getID_Value(dato2.getRight()), constL.IF_ID, constL.LOGIC_LABLE + boolCont));
            listTemp.add(new cuarteta(constL.LOGIC_LABLE + (boolCont + 1), constL.GOTO_STR_ID));
            tempVar varT = new tempVar(new andOrStr(listTemp, constL.LOGIC_LABLE + boolCont, constL.LOGIC_LABLE + (boolCont + 1)), operation, constL.BOOLEAN);
            boolCont += 2;
            return varT;
        } else if ((dato1.getOperation() == constL.AND_ID || dato1.getOperation() == constL.OR_ID) && (dato2.getOperation() != constL.AND_ID && dato2.getOperation() != constL.OR_ID)) {
            listTemp.addAll(dato1.getAndOrObject().getStructure());
            listTemp.removeLast();
            listTemp.add(new cuarteta(dato1.getAndOrObject().getFalseFlag(), constL.FLAG_STR_ID));
            listTemp.add(new cuarteta(null, constL.getID_Value(dato2.getLeft()) + " " + constL.getOpType(dato2.getOperation()) + " " + constL.getID_Value(dato2.getRight()), constL.IF_ID, dato1.getAndOrObject().getTrueFlag()));
            listTemp.add(new cuarteta(constL.LOGIC_LABLE + boolCont, constL.GOTO_STR_ID));
            tempVar varT = new tempVar(new andOrStr(listTemp, dato1.getAndOrObject().getTrueFlag(), constL.LOGIC_LABLE + boolCont), operation, constL.BOOLEAN);
            boolCont++;
            return varT;
        } else if ((dato1.getOperation() != constL.AND_ID && dato1.getOperation() != constL.OR_ID) && (dato2.getOperation() == constL.AND_ID || dato2.getOperation() == constL.OR_ID)) {
            listTemp.add(new cuarteta(null, constL.getID_Value(dato1.getLeft()) + " " + constL.getOpType(dato1.getOperation()) + " " + constL.getID_Value(dato1.getRight()), constL.IF_ID, constL.LOGIC_LABLE + boolCont));
            listTemp.addAll(dato2.getAndOrObject().getStructure());
            constL.changeLables(listTemp, dato2.getAndOrObject().getTrueFlag(), constL.LOGIC_LABLE + boolCont);
            tempVar varT = new tempVar(new andOrStr(listTemp, constL.LOGIC_LABLE + boolCont, dato2.getAndOrObject().getFalseFlag()), operation, constL.BOOLEAN);
            boolCont++;
            return varT;
        } else {
            listTemp.addAll(dato1.getAndOrObject().getStructure());
            listTemp.removeLast();
            listTemp.add(new cuarteta(dato1.getAndOrObject().getFalseFlag(), constL.FLAG_STR_ID));
            listTemp.addAll(dato2.getAndOrObject().getStructure());
            constL.changeLables(listTemp, dato2.getAndOrObject().getTrueFlag(), dato1.getAndOrObject().getTrueFlag());
            tempVar varT = new tempVar(new andOrStr(listTemp, dato1.getAndOrObject().getTrueFlag(), dato2.getAndOrObject().getFalseFlag()), operation, constL.BOOLEAN);
            return varT;
        }
    }

    /**
     * This method create the structure for the or option at an logic operation
     *
     * this method use recursive way of the syntactic analyzer to create the AND
     * structure
     *
     * @param dato1
     * @param dato2
     * @param operation
     * @return
     * @throws InputsVaciosException
     */
    protected tempVar andStr(tempVar dato1, tempVar dato2, int operation) throws InputsVaciosException {
        LinkedList<cuarteta> listTemp = new LinkedList<>();
        if ((dato1.getOperation() != constL.AND_ID && dato1.getOperation() != constL.OR_ID) && (dato2.getOperation() != constL.AND_ID && dato2.getOperation() != constL.OR_ID)) {
            listTemp.add(new cuarteta(null, constL.getID_Value(dato1.getLeft()) + " " + constL.getOpType(dato1.getOperation()) + " " + constL.getID_Value(dato1.getRight()), constL.IF_ID, constL.LOGIC_LABLE + boolCont));
            listTemp.add(new cuarteta(constL.LOGIC_LABLE + (boolCont + 1), constL.GOTO_STR_ID));
            listTemp.add(new cuarteta(constL.LOGIC_LABLE + boolCont, constL.FLAG_STR_ID));
            listTemp.add(new cuarteta(null, constL.getID_Value(dato2.getLeft()) + " " + constL.getOpType(dato2.getOperation()) + " " + constL.getID_Value(dato2.getRight()), constL.IF_ID, constL.LOGIC_LABLE + (boolCont + 2)));
            listTemp.add(new cuarteta(constL.LOGIC_LABLE + (boolCont + 1), constL.GOTO_STR_ID));
            tempVar varT = new tempVar(new andOrStr(listTemp, constL.LOGIC_LABLE + (boolCont + 2), constL.LOGIC_LABLE + (boolCont + 1)), operation, constL.BOOLEAN);
            boolCont += 3;
            return varT;
        } else if ((dato1.getOperation() == constL.AND_ID || dato1.getOperation() == constL.OR_ID) && (dato2.getOperation() != constL.AND_ID && dato2.getOperation() != constL.OR_ID)) {
            listTemp.addAll(dato1.getAndOrObject().getStructure());
            listTemp.add(new cuarteta(dato1.getAndOrObject().getTrueFlag(), constL.FLAG_STR_ID));
            listTemp.add(new cuarteta(null, constL.getID_Value(dato2.getLeft()) + " " + constL.getOpType(dato2.getOperation()) + " " + constL.getID_Value(dato2.getRight()), constL.IF_ID, constL.LOGIC_LABLE + boolCont));
            listTemp.add(new cuarteta(dato1.getAndOrObject().getFalseFlag(), constL.GOTO_STR_ID));
            tempVar varT = new tempVar(new andOrStr(listTemp, constL.LOGIC_LABLE + boolCont, dato1.getAndOrObject().getFalseFlag()), operation, constL.BOOLEAN);
            boolCont++;
            return varT;
        } else if ((dato1.getOperation() != constL.AND_ID && dato1.getOperation() != constL.OR_ID) && (dato2.getOperation() == constL.AND_ID || dato2.getOperation() == constL.OR_ID)) {
            listTemp.add(new cuarteta(null, constL.getID_Value(dato1.getLeft()) + " " + constL.getOpType(dato1.getOperation()) + " " + constL.getID_Value(dato1.getRight()), constL.IF_ID, constL.LOGIC_LABLE + boolCont));
            listTemp.add(new cuarteta(constL.LOGIC_LABLE + (boolCont + 1), constL.GOTO_STR_ID));
            listTemp.add(new cuarteta(constL.LOGIC_LABLE + boolCont, constL.FLAG_STR_ID));
            listTemp.addAll(dato2.getAndOrObject().getStructure());
            constL.changeLables(listTemp, dato2.getAndOrObject().getFalseFlag(), constL.LOGIC_LABLE + (boolCont + 1));
            tempVar varT = new tempVar(new andOrStr(listTemp, dato2.getAndOrObject().getTrueFlag(), constL.LOGIC_LABLE + (boolCont + 1)), operation, constL.BOOLEAN);
            boolCont += 2;
            return varT;
        } else {
            listTemp.addAll(dato1.getAndOrObject().getStructure());
            listTemp.add(new cuarteta(dato1.getAndOrObject().getTrueFlag(), constL.FLAG_STR_ID));
            listTemp.addAll(dato2.getAndOrObject().getStructure());
            constL.changeLables(listTemp, dato2.getAndOrObject().getFalseFlag(), dato1.getAndOrObject().getFalseFlag());
            tempVar varT = new tempVar(new andOrStr(listTemp, dato2.getAndOrObject().getTrueFlag(), dato1.getAndOrObject().getFalseFlag()), operation, constL.BOOLEAN);
            return varT;
        }
    }

    /**
     * just add the last step of the 3 directions code 'x = 2'
     *
     * @param id1
     * @param id2
     */
    private void addToTemp3dir(String id1, String id2, int tempT) {
        dataType tempType = semanticM.findDType(tempT);
        temp4thdir.add(new cuarteta(tempType, id1, constL.ASIGNAR_ID, id2));

    }

    /**
     * Just clear the 4 Directions temporal list.
     */
    public void resetTemp3VarList() {
        temp4thdir.clear();
    }

    /**
     * return counter to 0
     */
    public void resetContador() {
        contador = 0;
        boolCont = 0;
    }

    public void addBoolCont() {
        boolCont++;
    }

    public int getBoolCont() {
        return boolCont;
    }

    public void setBoolCont(int boolCont) {
        this.boolCont = boolCont;
    }

    public LinkedList<cuarteta> getTemp4thdir() {
        return temp4thdir;
    }

    public void setTemp4thdir(LinkedList<cuarteta> temp4thdir) {
        this.temp4thdir = temp4thdir;
    }

}
