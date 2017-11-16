package com.gedesys.midlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransport;
import org.kxml2.io.KXmlParser;
import org.netbeans.microedition.databinding.el.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 * @author econtreras
 */
public class TestMIDlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    private String ns = "http://ws.gedesys.com/";
    private String url = "http://www.gedesys.com/MobileServices-war/GedesysWSService";

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command itemCommand;
    private Form form;
    private TextField tfUsuario;
    private TextField tfPassword;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The TestMIDlet constructor.
     */
    public TestMIDlet() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
        itemCommand = new Command("Login", Command.ITEM, 0);//GEN-LINE:|0-initialize|1|0-postInitialize
    // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
    // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
    // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|19-preAction
            if (command == exitCommand) {//GEN-END:|7-commandAction|1|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|19-postAction
            // write post-action user code here
            } else if (command == itemCommand) {//GEN-LINE:|7-commandAction|3|38-preAction
                Process process = new Process(this);
                process.start();
            // write pre-action user code here
//GEN-LINE:|7-commandAction|4|38-postAction
            // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|7-postCommandAction
        }//GEN-END:|7-commandAction|5|7-postCommandAction
    // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|6|
    //</editor-fold>//GEN-END:|7-commandAction|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
        // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of form component.
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            form = new Form("Login", new Item[] { getTfUsuario(), getTfPassword() });//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.addCommand(itemCommand);
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
        // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return form;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tfUsuario ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initiliazed instance of tfUsuario component.
     * @return the initialized component instance
     */
    public TextField getTfUsuario() {
        if (tfUsuario == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            tfUsuario = new TextField("Usuario:", "", 32, TextField.ANY);//GEN-LINE:|23-getter|1|23-postInit
        // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return tfUsuario;
    }
    //</editor-fold>//GEN-END:|23-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tfPassword ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initiliazed instance of tfPassword component.
     * @return the initialized component instance
     */
    public TextField getTfPassword() {
        if (tfPassword == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            tfPassword = new TextField("Contrase\u00F1a:", "", 32, TextField.ANY | TextField.PASSWORD);//GEN-LINE:|24-getter|1|24-postInit
        // write post-init user code here
        }//GEN-BEGIN:|24-getter|2|
        return tfPassword;
    }
    //</editor-fold>//GEN-END:|24-getter|2|



    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {

        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }
}

class Process implements Runnable {

    private TestMIDlet MIDlet;

    public Process(TestMIDlet MIDlet) {
        this.MIDlet = MIDlet;
        System.out.println("Consumiendo Servicio Web...");
    }

    public void run() {
        try {
            transmit();
        } catch (Exception error) {
            System.err.println(error.toString());
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        try {
            thread.start();
            System.out.println("Consumiendo...");
        } catch (Exception error) {
        }
    }

    private void transmit() throws IOException {
        String method = "loginAlumno";

        // Se crea llamada Soap
        SoapObject rpc = new SoapObject(MIDlet.getNs(), method);
        HttpTransport transport = new HttpTransport(MIDlet.getUrl());
        rpc.addProperty("nombreUsuario", MIDlet.getTfUsuario().getString());
        rpc.addProperty("clave", MIDlet.getTfPassword().getString());

        // Creando sobre
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = rpc;

        // Llamada a Servicio Web
        try {
            transport.call(method, envelope);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (XmlPullParserException ex) {
            ex.printStackTrace();
        }

        String respuesta = "";

        try {
            respuesta = envelope.getResponse().toString();
        } catch (SoapFault ex) {
            ex.printStackTrace();
        }

        if (respuesta.equals("")) {
            tryAgain();
        } else {
            menu(respuesta);
        }

        System.out.println("Fin Consumo...");
    }

    public void menu(String datosXml) {
        try {
            String email = "";
            List services = new List("Datos", Choice.EXCLUSIVE);
            KXmlParser parser = new KXmlParser();
            java.io.InputStream archivo = new ByteArrayInputStream(datosXml.getBytes());
            java.io.Reader r = new java.io.InputStreamReader(archivo);
            parser.setInput(r);
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "alumno");
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "persona");
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "rut");
            parser.nextText();
            parser.require(XmlPullParser.END_TAG, null, "rut");
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "dv");
            parser.nextText();
            parser.require(XmlPullParser.END_TAG, null, "dv");
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "nombre");
            parser.nextText();
            parser.require(XmlPullParser.END_TAG, null, "nombre");
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "paterno");
            parser.nextText();
            parser.require(XmlPullParser.END_TAG, null, "paterno");
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "materno");
            parser.nextText();
            parser.require(XmlPullParser.END_TAG, null, "materno");
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "sexo");
            parser.nextText();
            parser.require(XmlPullParser.END_TAG, null, "sexo");
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "celular");
            parser.nextText();
            parser.require(XmlPullParser.END_TAG, null, "celular");
            while (parser.nextTag() == XmlPullParser.START_TAG) {
                parser.require(XmlPullParser.START_TAG, null, "email");
                email = parser.nextText();
                System.out.print("Email: " + email);
                parser.require(XmlPullParser.END_TAG, null, "email");
            }
            parser.require(XmlPullParser.END_TAG, null, "persona");
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "fechaInicio");
            parser.nextText();
            parser.require(XmlPullParser.END_TAG, null, "fechaInicio");
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "vigencia");
            parser.nextText();
            parser.require(XmlPullParser.END_TAG, null, "vigencia");
            parser.nextTag();
            parser.require(XmlPullParser.END_TAG, null, "alumno");
            /*String method = "getOpcion";
            SoapObject rpc = new SoapObject(MIDlet.getNs(), method);
            HttpTransport transport = new HttpTransport(MIDlet.getUrl());
            rpc.addProperty("opcionId", opcionId);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = rpc;
            try {
            transport.call(method, envelope);
            } catch (IOException ex) {
            ex.printStackTrace();
            } catch (XmlPullParserException ex) {
            ex.printStackTrace();
            }
            String opcion = "";
            try {
            opcion = envelope.getResponse().toString();
            } catch (SoapFault ex) {
            ex.printStackTrace();
            }*/
            services.append(email, null);
            services.append("Salir", null);
            MIDlet.getDisplay().setCurrent(services);
        } catch (XmlPullParserException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void tryAgain() {
        Alert error = new Alert("Fallo en autenticación", "Por favor, intenta de nuevo", null, AlertType.ERROR);
        error.setTimeout(Alert.FOREVER);
        MIDlet.getTfUsuario().setString("");
        MIDlet.getTfPassword().setString("");
        MIDlet.getDisplay().setCurrent(error, MIDlet.getForm());
    }
}