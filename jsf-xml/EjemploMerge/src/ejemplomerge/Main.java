package ejemplomerge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author rgonzaleza_ex
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer;

        File file = new File("test.xml");

        FileInputStream fis = null;
        fis = new FileInputStream(file);

        try {
            transformer = tFactory.newTransformer(new StreamSource("merge.xslt"));
            transformer.transform(new StreamSource(fis), new StreamResult(new File("simple.xml")));
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
