import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
/**
 * Created by gwd on 9/22/2016.
 */
public class Debug {
    private static Logger logger=Logger.getLogger(Debug.class.getName());
    public static void main(String args[]){
        logger.debug("skdk");
        logger.error("kdksfj");
    }
}
