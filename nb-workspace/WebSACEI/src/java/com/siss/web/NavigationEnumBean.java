package com.siss.web;

/**
 *
 * @author econtreras
 */

// Expone Enum Navigation de la clase Util como ManagedBean
public class NavigationEnumBean extends EnumManagedBean {
    public NavigationEnumBean() {
        super(Util.Navigation.class);
    }
}
