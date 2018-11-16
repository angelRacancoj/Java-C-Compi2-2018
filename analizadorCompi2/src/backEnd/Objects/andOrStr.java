/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.Objects;

import backEnd.Objects.finalStr.cuarteta;
import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class andOrStr {

    LinkedList<cuarteta> structure;
    String trueFlag;
    String falseFlag;

    public andOrStr(String trueFlag, String falseFlag) {
        this.trueFlag = trueFlag;
        this.falseFlag = falseFlag;
    }

    public andOrStr(LinkedList<cuarteta> structure, String trueFlag, String falseFlag) {
        this.structure = structure;
        this.trueFlag = trueFlag;
        this.falseFlag = falseFlag;
    }

    public LinkedList<cuarteta> getStructure() {
        return structure;
    }

    public void setStructure(LinkedList<cuarteta> structure) {
        this.structure = structure;
    }

    public String getTrueFlag() {
        return trueFlag;
    }

    public void setTrueFlag(String trueFlag) {
        this.trueFlag = trueFlag;
    }

    public String getFalseFlag() {
        return falseFlag;
    }

    public void setFalseFlag(String falseFlag) {
        this.falseFlag = falseFlag;
    }
}
