import okhttp3.*;
import java.io.IOException;


public class Test1{
    static String url = "http://127.0.0.1:8000/test/";

    public static void main (String[] avgs) throws IOException, InterruptedException{
        Post g = new Post();
        FormBody formBody = new FormBody.Builder()
            .add("login_name","123")
            .add("login_password","123")
            .build();
        g.post(url, formBody);
        String a = g.returnInfo;
        System.out.println(a);
    }
}
