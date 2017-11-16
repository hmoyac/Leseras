// Schematron classes
import schematron.Validator;
import schematron.Result;

// TrAX classes
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerException;

// DOM classes
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// java classes 
import java.util.Properties;

/** Test class for the Schematron Java API */
public class Schematron
{
   /**
    * Method main
    */
   public static void main(String args[])
      throws javax.xml.transform.TransformerException,
             javax.xml.parsers.ParserConfigurationException
   {    
      System.out.println("\n=== Java based Schematron validation ===");
    
      if (args.length < 2) {
         System.err.println("Usage: java Schematron <schema.xml> " +
                            "<xml-source.xml> [phase] [element]");
         return;
      }

      // set preprocessor parameters 
      Properties params = new Properties();
      if (args.length > 2)
         params.put("phase", new String(args[2]));

      Validator validator = null;
      try {
         // use default preprocessor
         validator = new Validator(new StreamSource(args[0]), params);
      }
      catch(TransformerException e) {
         System.err.println("in Main: " + e ) ;
         System.exit(1);
      }
	
      // ask for xsl:messages
      String[] msgs = validator.getWarnings();
      if (msgs.length != 0) {
         System.out.println("\nGot " + msgs.length + " Warning(s):");
         for (int i=0; i<msgs.length; i++)
            System.out.println(msgs[i]);
      }

      // output the title of the schema
      System.out.println("\nSchema title: " + validator.getTitle());

      // perform validation
      Result result = validator.validate(new StreamSource(args[1]));

      // everything ok?
      if (result.isBlank()) {
         System.out.println("\nValidation ok, no messages generated");
      }
      else {
         // just for debugging purposes: output the result XML
         String text = result.getResultAsText();
         System.out.println("\nResult:\n" + text);
        
         // look for messages for a specific element
         if (args.length > 3) {
            NodeList l = result.getAllMsgForElement(args[3]);
            System.out.println("Number of messages for <" + args[3] +
                            ">: " + l.getLength()); 
            for (int i=0; i<l.getLength(); i++) {
               Node n = l.item(i).getFirstChild();
               System.out.println(i + " - " + n.getNodeName() + ": " +
                                  n.getFirstChild().getNodeValue());
            }
         }
      }

      System.out.println("\n=== Schematron validation done ===");
   } 
}
