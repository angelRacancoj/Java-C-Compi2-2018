/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.Objects;

/**
 *
 * @author angel
 */
public class tempVar extends Variable {

    int row;
    int column;
    String id3Dir;

    /**
     * This variable is to do the arithmetic calculus storing the Integer values
     *
     * @param category
     * @param row
     * @param vInteger
     * @param column
     */
    public tempVar(int vInteger, int row, int column, int category) {
        super(vInteger, category);
        this.row = row;
        this.column = column;
        this.id3Dir = "";
    }

    /**
     * This variable is to do the arithmetic calculus storing the Float values
     *
     * @param vFloat
     * @param row
     * @param column
     * @param category
     */
    public tempVar(float vFloat, int row, int column, int category) {
        super(vFloat, category);
        this.row = row;
        this.column = column;
        this.id3Dir = "";
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
    public tempVar(String vString, int category, int row, int column, String id3Dir) {
        super(vString, category);
        this.row = row;
        this.column = column;
        this.id3Dir = id3Dir;
    }

    /**
     * This variable is to do the logic calculus
     *
     * @param vBool
     * @param category
     * @param row
     * @param column
     * @param id3Dir
     */
    public tempVar(boolean vBool, int category, int row, int column, String id3Dir) {
        super(vBool, category);
        this.row = row;
        this.column = column;
        this.id3Dir = id3Dir;
    }

    public tempVar(int category, int row, int column) {
        super(0, 0, "", true, category);
        this.row = row;
        this.column = column;
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
