/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.Objects;

import backEnd.langConstants.languageConstants;

/**
 *
 * @author angel
 */
public class finalVar extends Variable {

    languageConstants languageC = new languageConstants();

    int vInteger;
    float vFloat;
    dataType dType;
    String idVar;

    public finalVar(int vInteger, dataType dType, String idVar, int category) {
        super(category);
        this.vInteger = vInteger;
        this.dType = dType;
        this.idVar = idVar;
    }

    public finalVar(float vFloat, dataType dType, String idVar, int category) {
        super(category);
        this.vFloat = vFloat;
        this.dType = dType;
        this.idVar = idVar;
    }

    public finalVar(String idVar, dataType dType, String vString, int category) {
        super(vString, category);
        this.dType = dType;
        this.idVar = idVar;
    }

    public finalVar(String idVar, dataType dType, boolean vBool, int category) {
        super(vBool, category);
        this.dType = dType;
        this.idVar = idVar;
    }

    public String textVar() {
        if (this.dType.nameData == languageC.INTEGER) {
            return textIntegerVar();
        } else if (this.dType.nameData == languageC.FLOAT) {
            return textFloatVar();
        } else if (this.dType.nameData == languageC.STRING) {
            return textStringVar();
        } else if (this.dType.nameData == languageC.BOOLEAN) {
            return textBoolVar();
        }
        return null;
    }

    private String textIntegerVar() {
        return ("Valor: " + this.vInteger + ", id: " + this.idVar + " Tipo: " + languageC.getDataTypeName(dType.nameData) + " Category " + this.category);
    }

    private String textFloatVar() {
        return ("Valor: " + this.vFloat + ", id: " + this.idVar + " Tipo: " + languageC.getDataTypeName(dType.nameData) + " Category " + this.category);
    }

    private String textStringVar() {
        return ("Valor: " + this.vString + ", id: " + this.idVar + " Tipo: " + languageC.getDataTypeName(dType.nameData) + " Category " + this.category);
    }

    private String textBoolVar() {
        return ("Valor: " + this.vBool + ", id: " + this.idVar + " Tipo: " + languageC.getDataTypeName(dType.nameData) + " Category " + this.category);
    }

    public dataType getdType() {
        return dType;
    }

    public void setdType(dataType dType) {
        this.dType = dType;
    }

    public int getvInteger() {
        return vInteger;
    }

    public void setvInteger(int vInteger) {
        this.vInteger = vInteger;
    }

    public float getvFloat() {
        return vFloat;
    }

    public void setvFloat(float vFloat) {
        this.vFloat = vFloat;
    }

    public String getIdVar() {
        return idVar;
    }

    public void setIdVar(String idVar) {
        this.idVar = idVar;
    }

    @Override
    public double getvDouble() {
        return vDouble;
    }

    @Override
    public void setvDouble(double vDouble) {
        this.vDouble = vDouble;
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

}
