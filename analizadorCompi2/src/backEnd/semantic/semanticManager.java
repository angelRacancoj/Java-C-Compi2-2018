/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.semantic;

import backEnd.Objects.finalStr.errorObject;
import backEnd.Objects.finalStr.cuarteta;
import backEnd.Objects.*;
import backEnd.files.ManejadorArchivo;
import backEnd.langConstants.languageConstants;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class semanticManager {

    languageConstants languageC = new languageConstants();
    LinkedList<dataType> typeList = new LinkedList<>();
    LinkedList<finalVar> varList = new LinkedList<>();
    LinkedList<String> threeDirectionsCode = new LinkedList<>();
    LinkedList<cuarteta> forthDirCode = new LinkedList<>();
    LinkedList<errorObject> errors = new LinkedList<>();

    public semanticOperations operations;
    public semanticFunctions functions;
    ManejadorArchivo files = new ManejadorArchivo();

    public semanticManager() {
        initDataType();
        operations = new semanticOperations(this);
        functions = new semanticFunctions(this);
    }

    public void resetVarList() {
        varList.clear();
    }

    /**
     * Return the value and id of a variable stored at the Variable List
     *
     * @param id
     * @param row
     * @param column
     * @return
     */
    public tempVar getTempVarFromList(String id, int row, int column) {
        finalVar tempFinalV = findVariable(id);

        if (tempFinalV != null) {
            if (tempFinalV.getdType().getNameData() == languageC.INTEGER) {
                return new tempVar(tempFinalV.getvInteger(), languageC.INTEGER, row, column, id);
            } else if (tempFinalV.getdType().getNameData() == languageC.FLOAT) {
                return new tempVar(tempFinalV.getvFloat(), languageC.FLOAT, row, column, id);
            } else if (tempFinalV.getdType().getNameData() == languageC.STRING) {
                return new tempVar(tempFinalV.getvFloat(), languageC.STRING, row, column, id);
            } else if (tempFinalV.getdType().getNameData() == languageC.BOOLEAN) {
                return new tempVar(tempFinalV.getvFloat(), languageC.BOOLEAN, row, column, id);
            } else {
                errorAndPlace(languageC.AN_SEMANTICO, "No existe el tipo de dato indicado\nPara: " + id + " en la linea: " + row + " Columna: " + column);
                reset3DirLists();
                return null;
            }
        } else {
            errorAndPlace(languageC.AN_SEMANTICO, "No existe la variable: " + id + "indicada en la linea: " + row + " Columna: " + column);
            reset3DirLists();
            return null;
        }
    }

    /**
     * Verify and add the modify over a Variable
     *
     * @param data
     * @param row
     */
    public void modifyValue(tempFinalVar data, int row) {
        finalVar tempVarFound = findVariable(data.getId());

        if ((tempVarFound != null) && (data != null)) {
            if ((tempVarFound.getdType().getNameData() == languageC.STRING) && (data.getDato().getCategory() == languageC.STRING)) {
                tempVarFound.setvString(data.getDato().getvString());
                addTemp3DirCodeOp();
            } else if ((tempVarFound.getdType().getNameData() == languageC.BOOLEAN) && (data.getDato().getCategory() == languageC.BOOLEAN)) {
                tempVarFound.setvBool(data.getDato().isvBool());
                LinkedList<cuarteta> listTemp = new LinkedList<>();
                addTemp3DirCodeOp();
                listTemp.addAll(data.getDato().getAndOrObject().getStructure());
                listTemp.add(new cuarteta(data.getDato().getAndOrObject().getTrueFlag(), languageC.FLAG_STR_ID));
                listTemp.add(new cuarteta(tempVarFound.getdType(), data.getId(), languageC.ASIGNAR_ID, "1"));
                listTemp.add(new cuarteta(languageC.LOGIC_LABLE + operations.getBoolCont(), languageC.GOTO_STR_ID));
                listTemp.add(new cuarteta(data.getDato().getAndOrObject().getFalseFlag(), languageC.FLAG_STR_ID));
                listTemp.add(new cuarteta(tempVarFound.getdType(), data.getId(), languageC.ASIGNAR_ID, "0"));
                listTemp.add(new cuarteta(languageC.LOGIC_LABLE + operations.getBoolCont(), languageC.FLAG_STR_ID));
                operations.addBoolCont();
                forthDirCode.addAll(listTemp);
                operations.resetTemp3VarList();

            } else if ((tempVarFound.getdType().getNameData() == languageC.INTEGER) && (data.getDato().getCategory() == languageC.INTEGER)) {
                tempVarFound.setvInteger(data.getDato().getvInteger());
                addTemp3DirCodeOp();
            } else if ((tempVarFound.getdType().getNameData() == languageC.FLOAT) && (data.getDato().getCategory() == languageC.FLOAT)) {
                tempVarFound.setvFloat(data.getDato().getvFloat());
                addTemp3DirCodeOp();
            } else {
                errorAndPlace(languageC.AN_SEMANTICO, "No es compatible la variable " + data.getId() + " del tipo " + languageC.getDataTypeName(data.getDato().getCategory()) + " linea: " + row);
                reset3DirLists();
            }
        } else if (tempVarFound == null) {
            errorAndPlace(languageC.AN_SEMANTICO, "No existe la variable " + data.getId() + " del tipo " + languageC.getDataTypeName(data.getDato().getCategory()) + " linea: " + row);
            reset3DirLists();
        } else {
            errorAndPlace(languageC.AN_SEMANTICO, "Existe la variable " + data.getId() + " pero el dato debe ser "
                    + languageC.getDataTypeName(tempVarFound.getdType().getNameData()) + ", revisar linea " + row);
            reset3DirLists();
        }
    }

    /**
     * Add the variables to list if doesn't exist in the variable List
     *
     * @param dType
     * @param data
     * @param row
     * @param column
     */
    public void addVariableToList(int dType, tempFinalVar data, int row, int column) {
        if (data != null) {
            finalVar tempVarFound = findVariable(data.getId());
            dataType tempType = findDType(dType);
            if ((tempVarFound == null) && (tempType != null) && (data.getDato().getCategory() != languageC.NO_TYPE_AUX)) {

                if (dType == languageC.INTEGER) {
                    varList.add(new finalVar(data.getId(), tempType, data.getDato().getvInteger(), languageC.VARIABLE));
                    addTemp3DirCodeOp();
                } else if (dType == languageC.FLOAT) {
                    varList.add(new finalVar(data.getId(), tempType, data.getDato().getvFloat(), languageC.VARIABLE));
                    addTemp3DirCodeOp();
                } else if ((dType == languageC.BOOLEAN) && (data.getDato().getCategory() == languageC.BOOLEAN)) {
                    varList.add(new finalVar(data.getId(), tempType, data.getDato().isvBool(), languageC.VARIABLE));
                    LinkedList<cuarteta> listTemp = new LinkedList<>();
                    addTemp3DirCodeOp();
                    listTemp.addAll(data.getDato().getAndOrObject().getStructure());
                    listTemp.add(new cuarteta(data.getDato().getAndOrObject().getTrueFlag(), languageC.FLAG_STR_ID));
                    listTemp.add(new cuarteta(tempType, data.getId(), languageC.ASIGNAR_ID, "1"));
                    listTemp.add(new cuarteta(languageC.LOGIC_LABLE + operations.getBoolCont(), languageC.GOTO_STR_ID));
                    listTemp.add(new cuarteta(data.getDato().getAndOrObject().getFalseFlag(), languageC.FLAG_STR_ID));
                    listTemp.add(new cuarteta(tempType, data.getId(), languageC.ASIGNAR_ID, "0"));
                    listTemp.add(new cuarteta(languageC.LOGIC_LABLE + operations.getBoolCont(), languageC.FLAG_STR_ID));
                    forthDirCode.addAll(listTemp);
                    operations.resetTemp3VarList();
                } else if ((dType == languageC.STRING) && (data.getDato().getCategory() == languageC.STRING)) {
                    varList.add(new finalVar(data.getId(), tempType, data.getDato().getvString(), languageC.VARIABLE));
                    addTemp3DirCodeOp();
                } else {
                    errorAndPlace(languageC.AN_SEMANTICO, "No es compatible la variable " + data.getId() + " del tipo " + languageC.getDataTypeName(data.getDato().getCategory())
                            + " con el tipo de indicado " + languageC.getDataTypeName(dType) + " linea: " + row);
                    reset3DirLists();
                }
            } else if ((tempVarFound == null) && (tempType != null) && (data.getDato().getCategory() == languageC.NO_TYPE_AUX)) {
                if (dType == languageC.INTEGER) {
                    varList.add(new finalVar(data.getId(), tempType, 0, languageC.VARIABLE));
                    addTemp3DirCodeOp();
                    forthDirCode.add(new cuarteta(tempType, data.getId(), languageC.ASIGNAR_ID, "0"));
                    threeDirectionsCode.add(data.getId() + " = " + 0);
                } else if (dType == languageC.FLOAT) {
                    varList.add(new finalVar(data.getId(), tempType, 0, languageC.VARIABLE));
                    addTemp3DirCodeOp();
                    forthDirCode.add(new cuarteta(tempType, data.getId(), languageC.ASIGNAR_ID, "0"));
                    threeDirectionsCode.add(data.getId() + " = " + 0);
                } else if ((dType == languageC.BOOLEAN)) {
                    varList.add(new finalVar(data.getId(), tempType, true, languageC.VARIABLE));
                    addTemp3DirCodeOp();
                    forthDirCode.add(new cuarteta(tempType, data.getId(), languageC.ASIGNAR_ID, languageC.TRUE_STR));
                    threeDirectionsCode.add(data.getId() + " = " + 1);
                } else if ((dType == languageC.STRING)) {
                    varList.add(new finalVar(data.getId(), tempType, "", languageC.VARIABLE));
                    addTemp3DirCodeOp();
                    forthDirCode.add(new cuarteta(tempType, data.getId(), languageC.ASIGNAR_ID, "."));
                    threeDirectionsCode.add(data.getId() + " = " + ".");
                }
            } else if ((tempVarFound != null) && (tempType != null)) {
                errorAndPlace(languageC.AN_SEMANTICO, "Ya se ha declarado la variable " + tempVarFound.getIdVar() + " de tipo "
                        + languageC.getDataTypeName(tempVarFound.getdType().getNameData()) + ", revisar linea " + row);
                reset3DirLists();
            } else {
                errorAndPlace(languageC.AN_SEMANTICO, "No existe el tipo de dato indicado, revisar linea " + row);
                reset3DirLists();
            }
        } else {
            errorAndPlace(languageC.AN_SEMANTICO, "Imposible agregar revisar linea " + row + " revisar errores anteriores");
            reset3DirLists();
        }
    }

    private String text3DirOut() {
        String textOut = "";
        for (String threeDirectionsCode1 : threeDirectionsCode) {
            textOut += (threeDirectionsCode1 + "\n");
        }
        return textOut;
    }

    /**
     * Create the file where is going to be saved the "3 Directions Code"
     *
     * @throws IOException
     */
    public void create3DirCodeDoc() throws IOException {
        if (errors.isEmpty()) {
            files.guardarArchivo("3DirectionsCode.txt", text3DirOut());
        }
        resetAll();
    }

    /**
     * Print the variables
     *
     * @return
     */
    public String getVars() {
        if (errors.isEmpty()) {
            String vars = "Variables:\n";
            for (finalVar var : varList) {
                vars += (var.textVar() + "\n");
            }
            return vars;
        } else {
            return "";
        }
    }

    /**
     * Return in text all the error found
     *
     * @return
     */
    public String Errors() {
        String er = "Errors:\n";
        er += "Errores Lexicos:\n";
        er = errors.stream().filter((error) -> (error.getType() == languageC.AN_LEXICO)).map((error) -> (error.getInformation() + "\n")).reduce(er, String::concat);
        er += "Errores Sintactico:\n";
        er = errors.stream().filter((error) -> (error.getType() == languageC.AN_SINTACTICO)).map((error) -> (error.getInformation() + "\n")).reduce(er, String::concat);
        er += "Errores Semantico:\n";
        er = errors.stream().filter((error) -> (error.getType() == languageC.AN_SEMANTICO)).map((error) -> (error.getInformation() + "\n")).reduce(er, String::concat);
        return er;
    }

    private void initDataType() {
        typeList.add(new dataType(languageC.INTEGER, languageC.FIRST_DIMESION, languageC.INIT_SCOPE));
        typeList.add(new dataType(languageC.FLOAT, languageC.FIRST_DIMESION, languageC.INIT_SCOPE));
        typeList.add(new dataType(languageC.BOOLEAN, languageC.FIRST_DIMESION, languageC.INIT_SCOPE));
        typeList.add(new dataType(languageC.STRING, languageC.FIRST_DIMESION, languageC.INIT_SCOPE));
    }

    /**
     * set the analyzer where the problem come from, and set the information
     * about the problem
     *
     * @param analyzer
     * @param problem
     */
    public void errorAndPlace(int analyzer, String problem) {
        errors.add(new errorObject(analyzer, problem));
    }

    protected finalVar findVariable(String id) {
        for (finalVar varList1 : varList) {
            if (varList1.getIdVar().equals(id)) {
                return varList1;
            }
        }
        return null;
    }

    protected dataType findDType(int type) {
        for (dataType typeList1 : typeList) {
            if (typeList1.getNameData() == type) {
                return typeList1;
            }
        }
        return null;
    }

    protected void addTemp3DirCodeOp() {
        threeDirectionsCode.addAll(operations.getTemp3dir());
        forthDirCode.addAll(operations.getTemp4thdir());
        operations.resetTemp3VarList();
    }

    /**
     * this method take care of add the text of the 3 directions code from the
     * functions to the list to be print after it works
     *
     * @param funtion
     */
    public void addTemp3DirCodeFuntion(String funtion) {
        threeDirectionsCode.add(funtion);
    }

    /**
     * this method take care of add structure to the 4th-list
     *
     * ((operation) id) if ( d3 ) goto lf1 (operation id)
     *
     * @param dType
     * @param operator
     * @param operation
     * @param operando_1
     */
    public void addTemp4DirCodeFuntion(String operator, int operation, String operando_1) {
        forthDirCode.add(new cuarteta(null, operator, operation, operando_1));
    }

    public void addAllTemp4DirCode(LinkedList<cuarteta> forthD) {
        forthDirCode.addAll(forthD);
    }

    /**
     * This method is to set "FLAGS" like
     *
     * -> lf2:
     *
     * -> goto lf2
     *
     * @param operator
     * @param operation
     */
    public void addTemp4DirCodeFuntion(String operator, int operation) {
        forthDirCode.add(new cuarteta(operator, operation));
    }

    private void reset3DirLists() {
        threeDirectionsCode.clear();
        operations.resetTemp3VarList();
    }

    private void reset4DirLists() {
        forthDirCode.clear();
        operations.resetTemp3VarList();
    }

    /**
     * return 3 Directions code (all directions and temporal), the counters, the
     * functions list and counters, and reset the flag for error Found
     */
    public void resetAll() {
        forthDirCode.clear();
        threeDirectionsCode.clear();
        operations.resetTemp3VarList();
        operations.resetContador();
        functions.resetAll();
        errors.clear();
    }
}
