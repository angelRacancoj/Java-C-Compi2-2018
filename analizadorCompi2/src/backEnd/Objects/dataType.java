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
public class dataType {

    int nameData;
    int dimension;
    int scope;

    public dataType(int nameData, int dimension, int scope) {
        this.nameData = nameData;
        this.dimension = dimension;
        this.scope = scope;
    }

    public int getNameData() {
        return nameData;
    }

    public void setNameData(int nameData) {
        this.nameData = nameData;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

}
