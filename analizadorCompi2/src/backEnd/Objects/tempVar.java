/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.Objects;

import backEnd.exceptions.InputsVaciosException;
import backEnd.langConstants.languageConstants;

/**
 *
 * @author angel
 */
public class tempVar extends Variable {

    int row;
    int column;
    String id3Dir;
    tempVar right;
    tempVar left;
    int operation;
    andOrStr andOrObject;

    languageConstants languageC = new languageConstants();

    /**
     * This constructor take care of the OR and AND logic operation
     *
     * @param operation
     * @param andOrObject
     * @param category
     */
    public tempVar(andOrStr andOrObject, int operation, int category) {
        super(category);
        this.operation = operation;
        this.andOrObject = andOrObject;
    }

    /**
     * This variable is to do the arithmetic calculus storing the Integer values
     *
     * @param category
     * @param row
     * @param vInteger
     * @param column
     */
    public tempVar(int vInteger, int category, int row, int column) {
        super(vInteger, category);
        this.row = row;
        this.column = column;
        this.id3Dir = "";
        this.right = null;
        this.left = null;
        this.andOrObject = null;
    }

    /**
     * This variable is to do the arithmetic calculus storing the Float values
     *
     * @param vFloat
     * @param row
     * @param column
     * @param category
     */
    public tempVar(float vFloat, int category, int row, int column) {
        super(vFloat, category);
        this.row = row;
        this.column = column;
        this.id3Dir = "";
        this.right = null;
        this.left = null;
        this.andOrObject = null;
    }

    /**
     * This variable is to do the string concatenation
     *
     * @param vString
     * @param category
     * @param row
     * @param column
     */
    public tempVar(String vString, int category, int row, int column) {
        super(vString, category);
        this.row = row;
        this.column = column;
        this.id3Dir = "";
        this.right = null;
        this.left = null;
        this.andOrObject = null;
    }

    /**
     * This variable is to do the logic calculus
     *
     * @param vBool
     * @param category
     * @param row
     * @param column
     */
    public tempVar(boolean vBool, int category, int row, int column) {
        super(vBool, category);
        this.row = row;
        this.column = column;
        this.id3Dir = "";
        this.right = null;
        this.left = null;
        this.andOrObject = null;
    }

    /**
     * This variable is to do the arithmetic calculus storing the Integer values
     *
     * @param category
     * @param row
     * @param vInteger
     * @param column
     * @param id3Dir
     */
    public tempVar(int vInteger, int row, int column, int category, String id3Dir) {
        super(vInteger, category);
        this.row = row;
        this.column = column;
        this.id3Dir = id3Dir;
        this.right = null;
        this.left = null;
        this.andOrObject = null;
    }

    /**
     * This variable is to do the arithmetic calculus storing the Float values
     *
     * @param vFloat
     * @param row
     * @param column
     * @param category
     * @param id3Dir
     */
    public tempVar(float vFloat, int row, int column, int category, String id3Dir) {
        super(vFloat, category);
        this.row = row;
        this.column = column;
        this.id3Dir = id3Dir;
        this.right = null;
        this.left = null;
        this.andOrObject = null;
    }

    /**
     * This variable is to do the string concatenation
     *
     * @param vString
     * @param category
     * @param row
     * @param column
     * @param id3Dir
     */
    public tempVar(String vString, int row, int column, int category, String id3Dir) {
        super(vString, category);
        this.row = row;
        this.column = column;
        this.id3Dir = id3Dir;
        this.right = null;
        this.left = null;
        this.andOrObject = null;
    }

    /**
     * This variable is to do the logic calculus
     *
     * @param vBool
     * @param category
     * @param row
     * @param column
     * @param id3Dir
     * @param left
     * @param right
     * @param operation
     */
    public tempVar(boolean vBool, int category, int row, int column, tempVar left, tempVar right, int operation) {
        super(vBool, category);
        this.row = row;
        this.column = column;
        this.id3Dir = null;
        this.right = right;
        this.left = left;
        this.operation = operation;
        this.andOrObject = null;
    }

    public tempVar(int category, int row, int column) {
        super(0, 0, "", true, category);
        this.row = row;
        this.column = column;
        this.right = null;
        this.left = null;
        this.andOrObject = null;
    }

    public String getLogicOp() throws InputsVaciosException {
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

    private String getOp(String op) throws InputsVaciosException {
        return (languageC.getID_Value(this.left) + " " + op + " " + languageC.getID_Value(this.right));
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public andOrStr getAndOrObject() {
        return andOrObject;
    }

    public void setAndOrObject(andOrStr andOrObject) {
        this.andOrObject = andOrObject;
    }

    public tempVar getRight() {
        return right;
    }

    public void setRight(tempVar right) {
        this.right = right;
    }

    public tempVar getLeft() {
        return left;
    }

    public void setLeft(tempVar left) {
        this.left = left;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getId3Dir() {
        return id3Dir;
    }

    public void setId3Dir(String id3Dir) {
        this.id3Dir = id3Dir;
    }

    @Override
    public String getvString() {
        return vString;
    }

    @Override
    public void setvString(String vString) {
        this.vString = vString;
    }

    @Override
    public boolean isvBool() {
        return vBool;
    }

    @Override
    public void setvBool(boolean vBool) {
        this.vBool = vBool;
    }

    @Override
    public int getCategory() {
        return category;
    }

    @Override
    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public int getvInteger() {
        return vInteger;
    }

    @Override
    public void setvInteger(int vInteger) {
        this.vInteger = vInteger;
    }

    @Override
    public float getvFloat() {
        return vFloat;
    }

    @Override
    public void setvFloat(float vFloat) {
        this.vFloat = vFloat;
    }

}
