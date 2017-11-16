package consumews;


import molwsres.MolWs;
import molwsres.MolWsSoap;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author econtreras
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //try {
        
            // TODO code application logic here
            MolWs servicio = new MolWs();
            MolWsSoap port = servicio.getMolWsSoap();

            String canales = port.datosEquiposCanalesText("online", "mo1234");

            String resultado = port.ultimosResultadosText("online", "mo1234","24","91","01/12/2010 12:00");

            InputStream xmlCanales = new ByteArrayInputStream(canales.getBytes()) ;
//            InputStream xmlResultado = new ByteArrayInputStream(resultado.getBytes()) ;

            javax.xml.bind.JAXBContext jc = null;
            try {
                jc = javax.xml.bind.JAXBContext.newInstance("generated");
            } catch (javax.xml.bind.JAXBException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            javax.xml.bind.Unmarshaller unmarshaller = null;
            try {
                unmarshaller = jc.createUnmarshaller();
            } catch (javax.xml.bind.JAXBException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            generated.CanalesEquipos canalesEquipos = null;
//            generated.ResultadosLab resultadosLab = null;
            try {
                canalesEquipos = (generated.CanalesEquipos)unmarshaller.unmarshal(xmlCanales);
//                resultadosLab = (generated.ResultadosLab)unmarshaller.unmarshal(xmlResultado);
            } catch (javax.xml.bind.JAXBException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.print("pausa");

    }

}
