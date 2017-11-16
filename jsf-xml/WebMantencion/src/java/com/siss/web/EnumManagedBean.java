package com.siss.web;

/**
 *
 * @author econtreras
 */

// Clase que permite exponer un Enum como un ManagedBean
public abstract class EnumManagedBean {
    private Class<?extends Enum> e;

    protected EnumManagedBean(Class<?extends Enum> e) {
        this.e = e;
    }

    public Enum getEnum(String enumName) {
        return Enum.valueOf(e, enumName);
    }
}
