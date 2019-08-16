import edu.study.base.java.nio.CrunchifyNIOClient;
import edu.study.base.java.nio.CrunchifyNIOServer;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

/**
 * Created by gwd on 8/28/2016.
 */
public class Test {
    private static Logger logger= Logger.getLogger(Test.class.getName());
    //Test method
    @Before
    public void before(){
        logger.debug("-----before-----");
    }

    @After
    public void after(){
        logger.debug("-----after-----");
    }

    @org.junit.Test
    public void test(){
        try {
            CrunchifyNIOClient.getServices();

        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("info about 2016");
    }
    //Test support method
    public void support(){

    }
}
