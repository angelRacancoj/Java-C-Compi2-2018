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
public class whileCicle extends ifCicle {

    cicleFlag returnFlag;

    public whileCicle(cicleFlag returnFlag) {
        super(null, null);
        this.returnFlag = returnFlag;
    }

    public cicleFlag getReturnFlag() {
        return returnFlag;
    }

    public void setReturnFlag(cicleFlag returnFlag) {
        this.returnFlag = returnFlag;
    }

    @Override
    public cicleFlag getIfFlag() {
        return ifFlag;
    }

    @Override
    public void setIfFlag(cicleFlag ifFlag) {
        this.ifFlag = ifFlag;
    }

    @Override
    public cicleFlag getJumpFlag() {
        return jumpFlag;
    }

    @Override
    public void setJumpFlag(cicleFlag jumpFlag) {
        this.jumpFlag = jumpFlag;
    }

}
