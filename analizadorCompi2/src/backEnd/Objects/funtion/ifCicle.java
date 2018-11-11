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
public class ifCicle {

    cicleFlag ifFlag;
    cicleFlag jumpFlag;

    public ifCicle(cicleFlag ifFlag, cicleFlag jumpFlag) {
        this.ifFlag = ifFlag;
        this.jumpFlag = jumpFlag;
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
