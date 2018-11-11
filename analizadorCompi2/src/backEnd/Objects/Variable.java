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
public class Variable {

    double vDouble;
    String vString;
    boolean vBool;
    int category;

    public Variable(int category) {
        this.category = category;
    }

    public Variable(double vDouble, int category) {
        this.vDouble = vDouble;
        this.category = category;
    }

    public Variable(String vString, int category) {
        this.vString = vString;
        this.category = category;
    }

    public Variable(boolean vBool, int category) {
        this.vBool = vBool;
        this.category = category;
    }

    public Variable(double vDouble, String vString, boolean vBool, int category) {
        this.vDouble = vDouble;
        this.vString = vString;
        this.vBool = vBool;
        this.category = category;
    }

    public String getvString() {
        return vString;
    }

    public void setvString(String vString) {
        this.vString = vString;
    }

    public boolean isvBool() {
        return vBool;
    }

    public void setvBool(boolean vBool) {
        this.vBool = vBool;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getvDouble() {
        return vDouble;
    }

    public void setvDouble(double vDouble) {
        this.vDouble = vDouble;
    }

}
