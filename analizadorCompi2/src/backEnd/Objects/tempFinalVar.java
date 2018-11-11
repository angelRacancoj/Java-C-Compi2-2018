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
public class tempFinalVar {

    String id;
    tempVar dato;

    public tempFinalVar(String id, tempVar dato) {
        this.id = id;
        this.dato = dato;
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

}
