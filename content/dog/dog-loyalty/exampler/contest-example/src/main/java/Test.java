import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gwd on 5/18/2016.
 */
public class Test {
    public static  void main(String[] ab) throws IOException {
        String command="c:\\windows\\system32\\cmd.exe /c dir";
        ProcessBuilder pb=new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process=pb.start();
        BufferedReader inStreamReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        while(inStreamReader.readLine() != null){
            //do something with commandline output.
        }
    }
}
