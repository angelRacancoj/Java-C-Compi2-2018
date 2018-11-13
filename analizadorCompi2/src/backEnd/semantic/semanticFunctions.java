/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.semantic;

import backEnd.Objects.funtion.*;
import backEnd.Objects.tempVar;
import backEnd.langConstants.languageConstants;
import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class semanticFunctions {

    private languageConstants constL = new languageConstants();

    LinkedList<cicleLable> cicleLables = new LinkedList<>();
    LinkedList<cicleLable> cicleLablesList = new LinkedList<>();
    int ifNumber = 0;
    int whileNumber = 0;
    semanticManager semanticM;

    public semanticFunctions(semanticManager semanticM) {
        this.semanticM = semanticM;
    }

    private boolean correctOpCicle(tempVar data, int row, int column) {
        if (data != null) {
            boolean correctOperator = (data.getCategory() == constL.BOOLEAN);
            if (!correctOperator) {
                semanticM.errorAndPlace(constL.AN_SEMANTICO, "Dato incorrecto, Linea: " + row + " Columna: " + column);
            }
            return correctOperator;
        } else {
            semanticM.errorAndPlace(constL.AN_SEMANTICO, "Dato incorrecto, Linea: " + row + " Columna: " + column);
            return false;
        }
    }

    /**
     * This method goes after the logic operator and the '{' that open the code
     * inside of the "if" function
     *
     * @param operator
     * @param row
     * @param column
     * @param rowOpen
     * @param columnOpen
     * @param returnFlag
     * @return
     */
    public cicleFlag ifCycleInit(tempVar operator, int row, int column, int rowOpen, int columnOpen, cicleFlag returnFlag) {
        if (correctOpCicle(operator, row, column)) {
            if (returnFlag != null) {
                cicleFlag tempFlag = new cicleFlag(ifNumber + 2, rowOpen, columnOpen);
                cicleLable temp = new cicleLable(new cicleStr(new cicleFlag(ifNumber, rowOpen, columnOpen), new cicleFlag(ifNumber + 1, rowOpen, columnOpen), tempFlag), constL.IF_ID, operator);
                cicleLables.addFirst(temp);
                cicleLablesList.addFirst(temp);
                semanticM.addTemp3DirCodeOp();

                semanticM.addTemp3DirCodeFuntion("\n" + constL.IF_NAME + " (" + operator.getId3Dir() + ") " + constL.GOTO_STR + " " + constL.IF_LABLE + "" + ifNumber);
                semanticM.addTemp3DirCodeFuntion(constL.GOTO_STR + " " + constL.IF_LABLE + "" + (ifNumber + 1));
                semanticM.addTemp3DirCodeFuntion(constL.IF_LABLE + "" + (ifNumber) + "" + constL.FLAG_STR);

                semanticM.addTemp4DirCodeFuntion(operator.getId3Dir(), constL.IF_ID, constL.IF_LABLE + "" + ifNumber);
                semanticM.addTemp4DirCodeFuntion((constL.IF_LABLE + "" + (ifNumber + 1)), constL.GOTO_STR_ID);
                semanticM.addTemp4DirCodeFuntion(constL.IF_LABLE + "" + (ifNumber), constL.FLAG_STR_ID);

                ifNumber += 3;
                return tempFlag;
            } else {
                cicleLable temp = new cicleLable(new cicleStr(new cicleFlag(ifNumber, rowOpen, columnOpen), new cicleFlag(ifNumber + 1, rowOpen, columnOpen), returnFlag), constL.IF_ID, operator);
                cicleLables.addFirst(temp);
                cicleLablesList.addFirst(temp);
                semanticM.addTemp3DirCodeOp();

                semanticM.addTemp3DirCodeFuntion("\n" + constL.IF_NAME + " (" + operator.getId3Dir() + ") " + constL.GOTO_STR + " " + constL.IF_LABLE + "" + ifNumber);
                semanticM.addTemp3DirCodeFuntion(constL.GOTO_STR + " " + constL.IF_LABLE + "" + (ifNumber + 1));
                semanticM.addTemp3DirCodeFuntion(constL.IF_LABLE + "" + (ifNumber) + "" + constL.FLAG_STR);

                semanticM.addTemp4DirCodeFuntion(operator.getId3Dir(), constL.IF_ID, constL.IF_LABLE + "" + ifNumber);
                semanticM.addTemp4DirCodeFuntion((constL.IF_LABLE + "" + (ifNumber + 1)), constL.GOTO_STR_ID);
                semanticM.addTemp4DirCodeFuntion(constL.IF_LABLE + "" + (ifNumber), constL.FLAG_STR_ID);

                ifNumber += 2;
                return returnFlag;
            }
        }
        return null;
    }

    /**
     * This method goes at the end of the "if" function
     *
     * @param row
     * @param column
     */
    public void closeIfCycle(int row, int column) {
        if (!cicleLables.isEmpty()) {
            if (cicleLables.getFirst().getCicleType() == constL.IF_ID) {
                semanticM.addTemp3DirCodeFuntion(constL.GOTO_STR + " " + constL.IF_LABLE + "" + cicleLables.getFirst().getIfCicleStr().getReturnFlag().getNumber());
                semanticM.addTemp3DirCodeFuntion(constL.IF_LABLE + "" + cicleLables.getFirst().getIfCicleStr().getJumpFlag().getNumber() + "" + constL.FLAG_STR);

                semanticM.addTemp4DirCodeFuntion(constL.IF_LABLE + "" + cicleLables.getFirst().getIfCicleStr().getReturnFlag().getNumber(), constL.GOTO_STR_ID);
                semanticM.addTemp4DirCodeFuntion(constL.IF_LABLE + "" + cicleLables.getFirst().getIfCicleStr().getJumpFlag().getNumber(), constL.FLAG_STR_ID);

                cicleLablePosition(cicleLables.getFirst()).setCicleClosed(true);
                cicleLables.removeFirst();
            } else {
                semanticM.errorAndPlace(constL.AN_SEMANTICO, "Error al cerrar ciclo if, Linea: " + row + " Columna: " + column);
            }
        } else {
            semanticM.errorAndPlace(constL.AN_SEMANTICO, "Dato incorrecto, Linea: " + row + " Columna: " + column);
        }
    }

    /**
     * This method add the last flag that is going to be necessary when the
     * structures need to get out of a "if" function
     *
     * @param jumpFlag
     */
    public void setJumpFlag(cicleFlag jumpFlag) {
        semanticM.addTemp3DirCodeFuntion(constL.IF_LABLE + "" + (jumpFlag.getNumber()) + "" + constL.FLAG_STR);
        semanticM.addTemp4DirCodeFuntion(constL.IF_LABLE + "" + (jumpFlag.getNumber()), constL.FLAG_STR_ID);
    }

    /**
     * This method is just to take care that the structure of the "else" is
     * correct
     *
     * @param row
     * @param column
     */
    public void elseCycle(int row, int column) {
        cicleLable temp = new cicleLable(null, constL.ELSE_ID, null);
        cicleLables.addFirst(temp);
        cicleLablesList.addFirst(temp);
    }

    /**
     * Close the verification of the "else" function
     *
     * @param row
     * @param column
     * @param jumpFlag
     */
    public void closeElseCycle(int row, int column, cicleFlag jumpFlag) {
        if (!cicleLables.isEmpty()) {
            if (cicleLables.getFirst().getCicleType() == constL.ELSE_ID) {
                cicleLablePosition(cicleLables.getFirst()).setCicleClosed(true);
                cicleLables.removeFirst();
                setJumpFlag(jumpFlag);
            } else {
                semanticM.errorAndPlace(constL.AN_SEMANTICO, "Error al cerrar 'else', Linea: " + row + " Columna: " + column);
            }
        } else {
            semanticM.errorAndPlace(constL.AN_SEMANTICO, "No existen ciclos por cerrar, Linea: " + row + " Columna: " + column);
        }
    }

    /**
     * Add the first "flag" of the while cycle
     *
     * @param row
     * @param column
     */
    public void startWhileCycle(int row, int column) {
        cicleLable temp = new cicleLable(new cicleStr(new cicleFlag(whileNumber, row, column)), constL.WHILE_ID);
        cicleLables.addFirst(temp);
        cicleLablesList.addFirst(temp);
        semanticM.addTemp3DirCodeFuntion(constL.WHILE_LABLE + whileNumber + ":");
        whileNumber++;
    }

    /**
     * This method add the next two flags
     *
     * @param operator
     * @param row
     * @param column
     * @param rowOpen
     * @param columnOpen
     */
    public void whileCycleInit(tempVar operator, int row, int column, int rowOpen, int columnOpen) {
        if (correctOpCicle(operator, row, column) && (!cicleLables.isEmpty())) {
            if (cicleLables.getFirst().getCicleType() == constL.WHILE_ID) {
                cicleLables.getFirst().getIfCicleStr().setIfFlag(new cicleFlag(whileNumber, rowOpen, columnOpen));
                cicleLables.getFirst().getIfCicleStr().setJumpFlag(new cicleFlag(whileNumber + 1, rowOpen, columnOpen));

                semanticM.addTemp3DirCodeOp();
                semanticM.addTemp3DirCodeFuntion(constL.IF_NAME + " (" + operator.getId3Dir() + ") " + constL.GOTO_STR + " " + constL.WHILE_NAME + "" + whileNumber);
                semanticM.addTemp3DirCodeFuntion(constL.GOTO_STR + " " + constL.WHILE_LABLE + "" + (whileNumber + 1));
                semanticM.addTemp3DirCodeFuntion(constL.WHILE_LABLE + "" + whileNumber + "" + constL.FLAG_STR);
                whileNumber += 2;
            } else {
                semanticM.errorAndPlace(constL.AN_SEMANTICO, "Error al iniciar ciclo while, Linea: " + row + " Columna: " + column);
            }
        } else {
            semanticM.errorAndPlace(constL.AN_SEMANTICO, "No existen iniciar por cerrar, Linea: " + row + " Columna: " + column);
        }
    }

    /**
     * Close the "While" cycle and add the last two flags
     *
     * @param row
     * @param column
     */
    public void closeWhileCycle(int row, int column) {
        if (!cicleLables.isEmpty()) {
            if (cicleLables.getFirst().getCicleType() == constL.WHILE_ID) {
                semanticM.addTemp3DirCodeFuntion(constL.GOTO_STR + " " + constL.WHILE_LABLE + "" + cicleLables.getFirst().getIfCicleStr().getReturnFlag().getNumber());
                semanticM.addTemp3DirCodeFuntion(constL.WHILE_LABLE + "" + cicleLables.getFirst().getIfCicleStr().getJumpFlag().getNumber() + "" + constL.FLAG_STR);

                semanticM.addTemp4DirCodeFuntion(constL.WHILE_LABLE + "" + cicleLables.getFirst().getIfCicleStr().getReturnFlag().getNumber(), constL.GOTO_STR_ID);
                semanticM.addTemp4DirCodeFuntion(constL.WHILE_LABLE + "" + cicleLables.getFirst().getIfCicleStr().getJumpFlag().getNumber(), constL.FLAG_STR_ID);
                cicleLablePosition(cicleLables.getFirst()).setCicleClosed(true);
                cicleLables.removeFirst();
            } else {
                semanticM.errorAndPlace(constL.AN_SEMANTICO, "Error al cerrar ciclo while, Linea: " + row + " Columna: " + column);
            }
        } else {
            semanticM.errorAndPlace(constL.AN_SEMANTICO, "No existen ciclos por cerrar, Linea: " + row + " Columna: " + column);
        }
    }

    /**
     * If the program found a "break" verify if it's inside of a "While", if
     * it's inside create a jump to to get out of the cycle
     *
     * @param row
     * @param column
     */
    public void breakFound(int row, int column) {
        if (!cicleLables.isEmpty()) {
            if (cicleLables.getFirst().getCicleType() == constL.WHILE_ID) {
                semanticM.addTemp3DirCodeFuntion(constL.GOTO_STR + " " + constL.WHILE_LABLE + "" + cicleLables.getFirst().getIfCicleStr().getJumpFlag().getNumber());
            } else if ((whileCicleOpen() != null) && (cicleLables.getFirst().getCicleType() != constL.WHILE_ID)) {
                semanticM.addTemp3DirCodeFuntion(constL.GOTO_STR + " " + constL.WHILE_LABLE + "" + whileCicleOpen().getIfCicleStr().getJumpFlag().getNumber());
            } else {
                semanticM.errorAndPlace(constL.AN_SEMANTICO, "Break fuera de ciclo While, Linea: " + row + " Columna: " + column);
            }
        } else {
            semanticM.errorAndPlace(constL.AN_SEMANTICO, ", Linea: " + row + " Columna: " + column);
        }
    }

    /**
     * Verify that the structure to print, have the correct values to work
     *
     * @param data
     * @param row
     * @param column
     */
    public void correctStringPrint(tempVar data, int row, int column) {
        if (data != null) {
            if (data.getCategory() != constL.NO_TYPE_AUX) {
                semanticM.addTemp3DirCodeOp();
            } else {
                semanticM.errorAndPlace(constL.AN_SEMANTICO, "Estructura de Impresion vacia, Linea: " + row + " Columna: " + column);
            }
        } else {
            semanticM.errorAndPlace(constL.AN_SEMANTICO, "Error al ingresar el texto, verificar errores anteriores cercanos a Linea: " + row + " Columna: " + column);
        }
    }

    /**
     * reset both lists and the counters
     */
    public void resetAll() {
        cicleLables.clear();
        cicleLablesList.clear();
        ifNumber = 0;
        whileNumber = 0;
    }

    private cicleLable cicleLablePosition(cicleLable cicle) {
        for (cicleLable cicleLablesList1 : cicleLablesList) {
            if (cicleLablesList1 == cicle) {
                return cicleLablesList1;
            }
        }
        return null;
    }

    private cicleLable whileCicleOpen() {
        for (cicleLable cicleL : cicleLables) {
            if (cicleL.getCicleType() == constL.WHILE_ID) {
                return cicleL;
            }
        }
        return null;
    }
}
