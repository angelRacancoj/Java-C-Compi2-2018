/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.Objects.funtion;

import backEnd.Objects.tempVar;

/**
 *
 * @author angel
 */
public class cicleLable {

    ifCicle ifCicleStr;
    whileCicle whileCicleStr;
    tempVar operation;
    boolean cicleClosed;
    int cicleType;

    /**
     * This constructor is defined to the "IF" structure
     *
     * @param ifCicleStr
     * @param cicleType
     * @param operation
     */
    public cicleLable(ifCicle ifCicleStr, int cicleType, tempVar operation) {
        this.ifCicleStr = ifCicleStr;
        this.cicleType = cicleType;
        this.operation = operation;
        this.cicleClosed = false;
        this.whileCicleStr = null;
    }

    /**
     * This constructor is defined to the "WHILE" cycle
     *
     * @param whileCicleStr
     * @param cicleType
     */
    public cicleLable(whileCicle whileCicleStr, int cicleType) {
        this.whileCicleStr = whileCicleStr;
        this.cicleType = cicleType;
        this.operation = null;
        this.cicleClosed = false;
        this.ifCicleStr = null;
    }

    public ifCicle getIfCicleStr() {
        return ifCicleStr;
    }

    public void setIfCicleStr(ifCicle ifCicleStr) {
        this.ifCicleStr = ifCicleStr;
    }

    public whileCicle getWhileCicleStr() {
        return whileCicleStr;
    }

    public void setWhileCicleStr(whileCicle whileCicleStr) {
        this.whileCicleStr = whileCicleStr;
    }

    public tempVar getOperation() {
        return operation;
    }

    public void setOperation(tempVar operation) {
        this.operation = operation;
    }

    public boolean isCicleClosed() {
        return cicleClosed;
    }

    public void setCicleClosed(boolean cicleClosed) {
        this.cicleClosed = cicleClosed;
    }

    public int getCicleType() {
        return cicleType;
    }

    public void setCicleType(int cicleType) {
        this.cicleType = cicleType;
    }

}
