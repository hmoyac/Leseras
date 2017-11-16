package com.siss.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import com.siss.web.log.Debug;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author hmoya
 *
 * Contiene utilerias para la manipulación de los bundles y mensajes.
 */
public class Util {

    /**
     * Enumeracion que almacena los nombres de las reglas de navegación.
     */
    public enum Navigation {
        error,
        main;
        public Object action() {
            return this;
        }
    }

    /**
     * Especifica el nombre del archivo properties.
     */
    private static String RESOURCE_BASE_NAME = null;
    /**
     * Cache para los diversos bundles
     */
    private static Map<Locale, ResourceBundle> RESOURCES = new HashMap<Locale, ResourceBundle>(
                2);

    /**
     * Constructor de la clase.
     */
    private Util() {
    }

    /**
     * Obtiene los bundles asociados  la aplicación en el web.xml y retorna el idioma.
     * @param context Contexto web de la aplicación.
     * @return Bundle con el idioma por defecto.
     */
    public static ResourceBundle getBundle(FacesContext context) {
        if (RESOURCE_BASE_NAME == null) {
            RESOURCE_BASE_NAME = context.getApplication()
                                        .getMessageBundle();
        }

//        Locale locale = context.getViewRoot()
//                               .getLocale();
        Locale locale = context.getExternalContext().getRequestLocale();
        ResourceBundle bundle = RESOURCES.get(locale);

        if (bundle == null) {
            try {
                bundle = ResourceBundle.getBundle(RESOURCE_BASE_NAME, locale);
                RESOURCES.put(locale, bundle);
            } catch (Exception e) {
                Debug.print("No pudo encontrar paquete de recursos.");
            }
        }

        return bundle;
    }

    /**
     * Obtiene un valor definido en el bundle, mediante key=value
     * @param context Contexto web de la aplicación.
     * @param key Texto que representa la entrada key=value.
     * @return Valor asociado a la llave(key) en el bundle.
     */
    public static String getString(
        FacesContext context,
        String key) {
        String text;

        try {
            text = getBundle(context)
                       .getString(key);
        } catch (Exception e) {
            text = "???" + key + "???";
        }

        return text;
    }

    /**
     * Agrega un mensaje al contexto.
     * @param context Contexto web de la aplicación.
     * @param clientId Identificador del componente web.
     * @param key Texto que representa la entrada key=value.
     * @param severity Representa el tipo de mensaje. FacesMessage.SEVERITY_INFO, FacesMessage.SEVERITY_FATAL, FacesMessage.SEVERITY_ERROR y FacesMessage.SEVERITY_WARN
     * @param params MessageFormat para formatear el mensaje
     * @see FacesMessage#severity
     */
    public static void addMessage(
        FacesContext context,
        String clientId,
        String key,
        FacesMessage.Severity severity,
        Object... params) {
        String text = getString(context, key);

        if ((params != null) && (params.length > 0)) {
            text = MessageFormat.format(text, params);
        }

        context.addMessage(
            clientId,
            new FacesMessage(severity, text, text));

        Debug.print(text);
    }

    /**
     * Agrega un mensaje de información al contexto.
     * @param mensaje Texto a mostrar en el mensaje.
     * @see #addMessage(javax.faces.context.FacesContext, java.lang.String, java.lang.String, javax.faces.application.FacesMessage.Severity, java.lang.Object[])
     */
    public static void mostrarMensaje(String mensaje) {
        Util.errorExeption(mensaje, "Informacion", FacesMessage.SEVERITY_INFO);
    }

    /**
     * Agrega un mensaje de error al contexto de la aplicación.
     * @param ex Exception capturada
     * @see #errorExeption(java.lang.String, java.lang.String, javax.faces.application.FacesMessage.Severity)
     */
    public static void mostrarError(Exception ex) {
        Util.errorExeption(getString(FacesContext.getCurrentInstance(), "ErrorFatal"), "Error", FacesMessage.SEVERITY_FATAL);
        Util.errorExeption(ex.getMessage(), "Error", FacesMessage.SEVERITY_FATAL);
    }

    /**
     * Agrega un mensaje al contexto web.
     * @param summary Texto del mensaje.
     * @param detail Detalle del mensaje.
     * @param severityLevel Nivel de severidad.  FacesMessage.SEVERITY_INFO, FacesMessage.SEVERITY_FATAL, FacesMessage.SEVERITY_ERROR y FacesMessage.SEVERITY_WARN
     * @see FacesMessage#SEVERITY_INFO
     * @see FacesMessage#SEVERITY_FATAL
     * @see FacesMessage#SEVERITY_ERROR
     * @see FacesMessage#SEVERITY_WARN
     */
    public static void errorExeption(String summary, String detail, FacesMessage.Severity severityLevel) {
        FacesMessage message = new FacesMessage();
        message.setDetail(detail);
        message.setSummary(summary);
        message.setSeverity(severityLevel);
        FacesContext.getCurrentInstance().addMessage("formMainPopup:messages", message);
    }

    /**
     * Método de ordenamiento de una lista con reportes de cumplimiento, el ordenamiento se produce dado un nombre de un campo del modelo de datos,
     * y un boolean indicando si es ascendente o descendente.
     * @param lista Lista a ordenar.
     * @param ascending Tipo de ordenamiento.
     * @return Lista ordenada de los reportes de cumplimiento.
     * @see Comparator
     * @see Collections#sort(java.util.List, java.util.Comparator)
     */
    public static List<String> sortPeriodos(List<String> lista, final boolean ascending) {
        Comparator comparator = new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                String c1 = (String) o1;
                String c2 = (String) o2;

                return ascending ? c1.compareTo(c2) : c2.compareTo(c1);
            }
        };
        Collections.sort(lista, comparator);
        return lista;
    }

    public static String getText(Exception ex) {
        String msg = "";
        String e = "Exception:";
        int indice = ex.getMessage().lastIndexOf(e);
        if (indice > 0) {
            msg = ex.getMessage().substring(indice + e.length(), ex.getMessage().length());
        }
        return msg;
    }
}
