import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        String command="cmd /c C:\\Users\\gwd\\IdeaProjects\\test\\out\\production\\test\\test.sh";
        String dosCommand = "cmd /c C:\\Users\\gwd\\IdeaProjects\\test\\out\\production\\test\\test.bat";
        String temp="cmd /c dir c:\\Windows";

        Process process = Runtime.getRuntime().exec(command);
        //System.out.println("the output stream: "+process.getOutputStream());
        Main.inputStream(process.getInputStream());


        BufferedReader reader=new BufferedReader( new InputStreamReader(process.getInputStream()));
        String s;
        while ((s = reader.readLine()) !=null){
            System.out.println(s);
        }

       command=temp;
        String[] cmd=new String[]{"cmd.exe","/c","dir"};
        String str="cmd /c echo 'abc' ";
        String[] strs={"C:\\Users\\gwd\\IdeaProjects\\test\\out\\production\\test\\test.bat"};
        command=str;
        process=Runtime.getRuntime().exec(strs[0]);
       // process.destroy();
    }

    public static void inputStream(InputStream inputStream) throws IOException {
        int ch;
        while((ch=inputStream.read())!=-1){
            System.out.print((char)ch);
        }
    }
}
