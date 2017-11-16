package com.siss.web;

import javax.el.ELResolver;
import javax.el.ELContext;
import javax.el.PropertyNotFoundException;
import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.Collections;

/**
 *
 * @author econtreras
 *
 * Marcador de clase que busca instancia de EnumManagedBean y si la encuentra
 * llama al método EnumManagedBean.getEnum() quien a su vez llama al método
 * Enum.valueOf() que retorna la instancia de Enum correcta
 *
 */
public class EnumResolver extends ELResolver {

    public Object getValue(
        ELContext elContext,
        Object base,
        Object property) {
        if (((base != null) && (property != null))
                && base instanceof EnumManagedBean) {
            elContext.setPropertyResolved(true);

            return ((EnumManagedBean) base).getEnum(property.toString());
        }

        return null;
    }

    public Class<?> getCommonPropertyType(
        ELContext elContext,
        Object base) {
        return EnumManagedBean.class;
    }

    public Class<?> getType(
        ELContext elContext,
        Object base,
        Object property) {
        if (((base != null) && (property != null))
                && base instanceof EnumManagedBean) {
            elContext.setPropertyResolved(true);
            throw new PropertyNotFoundException();
        }

        return null;
    }

    public void setValue(
        ELContext elContext,
        Object base,
        Object property,
        Object value) {
        if (((base != null) && (property != null))
                && base instanceof EnumManagedBean) {
            elContext.setPropertyResolved(true);
            throw new PropertyNotFoundException();
        }
    }

    public boolean isReadOnly(
        ELContext elContext,
        Object base,
        Object property) {
        if (((base != null) && (property != null))
                && base instanceof EnumManagedBean) {
            elContext.setPropertyResolved(true);

            return true;
        }

        return false;
    }

    public Iterator<FeatureDescriptor> getFeatureDescriptors(
        ELContext elContext,
        Object base) {
        return Collections.<FeatureDescriptor>emptyList()
                          .iterator();
    }
}
