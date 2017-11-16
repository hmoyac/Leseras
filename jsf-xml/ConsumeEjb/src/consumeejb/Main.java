package consumeejb;

import com.siss.app.util.locator.ServiceLocator;
import com.siss.ifacade.ptas.ResultadosCanalFacadeRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author econtreras
 */
public class Main {

    private final static String FACADE_PTAS = "com.siss.ifacade.ptas.ResultadosCanalFacadeRemote";
    private static ServiceLocator servicio;
    private static ResultadosCanalFacadeRemote resultadosCanalFacade;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        servicio = ServiceLocator.getInstance();
        try {
            resultadosCanalFacade = (ResultadosCanalFacadeRemote) servicio.getRemoteHome(FACADE_PTAS, ResultadosCanalFacadeRemote.class);
//            resultadosCanalFacade.enableResultadosCanalWSC();
            List<String> infoTimer = resultadosCanalFacade.getInfoTimer();
            for(int i = 0; i < infoTimer.size(); i++) {
                System.out.println(infoTimer.get(i));
            }
//            resultadosCanalFacade.disableResultadosCanalWSC();
        } catch (NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
