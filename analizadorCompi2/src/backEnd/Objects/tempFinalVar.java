/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.Objects;

import java.util.LinkedList;

/**
 *
 * @author angel
 */
public class tempFinalVar {

    String id;
    tempVar dato;
    LinkedList<tempVar> dimensions;

    public tempFinalVar(String id, tempVar dato) {
        this.id = id;
        this.dato = dato;
        this.dimensions = null;
    }

    public tempFinalVar(String id, LinkedList<tempVar> dimensions) {
        this.id = id;
        this.dimensions = dimensions;
        this.dato = null;
    }

    public tempFinalVar(String id, tempVar dato, LinkedList<tempVar> dimensions) {
        this.id = id;
        this.dato = dato;
        this.dimensions = dimensions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public tempVar getDato() {
        return dato;
    }

    public void setDato(tempVar dato) {
        this.dato = dato;
    }

    public LinkedList<tempVar> getDimensions() {
        return dimensions;
    }

    public void setDimensions(LinkedList<tempVar> dimensions) {
        this.dimensions = dimensions;
    }

}
