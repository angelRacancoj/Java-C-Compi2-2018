/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.Objects.funtion;

/**
 *
 * @author angel
 */
public class cicleStr {

    cicleFlag ifFlag;
    cicleFlag jumpFlag;
    cicleFlag returnFlag;

    /**
     * This constructor is designed to start the a "While" cycle
     *
     * @param returnFlag
     */
    public cicleStr(cicleFlag returnFlag) {
        this.ifFlag = null;
        this.jumpFlag = null;
        this.returnFlag = returnFlag;
    }

    /**
     * This constructor is designed just to be use when there is a already a
     * cycle flag created
     *
     * @param ifFlag
     * @param jumpFlag
     *
     * public ifCicle(cicleFlag ifFlag, cicleFlag jumpFlag) { this.ifFlag =
     * ifFlag; this.jumpFlag = jumpFlag; }
     */
    /**
     * this constructor is designed to be use at the "If"
     *
     * @param ifFlag
     * @param jumpFlag
     * @param returnFlag
     */
    public cicleStr(cicleFlag ifFlag, cicleFlag jumpFlag, cicleFlag returnFlag) {
        this.ifFlag = ifFlag;
        this.jumpFlag = jumpFlag;
        this.returnFlag = returnFlag;
    }

    public cicleFlag getReturnFlag() {
        return returnFlag;
    }

    public void setReturnFlag(cicleFlag returnFlag) {
        this.returnFlag = returnFlag;
    }

    public cicleFlag getIfFlag() {
        return ifFlag;
    }

    public void setIfFlag(cicleFlag ifFlag) {
        this.ifFlag = ifFlag;
    }

    public cicleFlag getJumpFlag() {
        return jumpFlag;
    }

    public void setJumpFlag(cicleFlag jumpFlag) {
        this.jumpFlag = jumpFlag;
    }

}
