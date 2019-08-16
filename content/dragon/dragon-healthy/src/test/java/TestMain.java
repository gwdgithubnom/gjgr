import org.apache.log4j.Logger;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gwd on 2016/8/25.
 */
public class TestMain {
    private static Logger logger= Logger.getLogger(TestMain.class.getName());
    //Test method
    @Before
    public void before(){
        logger.debug("-----before-----");
    }

    @After
    public void after(){
        logger.debug("-----after-----");
    }

    @Test
    public void test(){
        //DirectBuffer.test(null);
      //  FileChannelCopy.test();
        try {
            //AutoBoxReflect.main(null);
            //BigNumberTest.main(null);
            //IntAndTest.main(null);
            //IntegerTest.main(null);
            //SampleChineseSort.main(null);
            //CrunchifyNIOServer.services();
            //CrunchifyNIOClient.getServices();
            //logger.debug(ISeason.get(3).getNote());
            //logger.debug(Season.spring);
            //NewlyFactory.newInstance(BaseEntity.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Test support method
    public void support(){

    }
}
