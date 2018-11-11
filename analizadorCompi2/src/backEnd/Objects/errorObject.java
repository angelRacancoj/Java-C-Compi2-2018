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
public class errorObject {

    int type;
    String information;
    languageConstants constant = new languageConstants();

    public errorObject(int type, String information) {
        this.type = type;
        this.information = information;
    }

    public int getType() {
        return type;
    }

    public String getTypeStr() {
        if (type == constant.AN_LEXICO) {
            return constant.AN_LEXICO_STR;
        } else if (type == constant.AN_SINTACTICO) {
            return constant.AN_SINTACTICO_STR;
        } else if (type == constant.AN_SEMANTICO) {
            return constant.AN_SEMANTICO_STR;
        }
        return null;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

}
