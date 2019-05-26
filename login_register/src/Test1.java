import okhttp3.*;
import java.io.IOException;


public class Test1{
    static String url = "http://127.0.0.1:8000/el/";

    public static void main (String[] avgs) throws IOException, InterruptedException{
        Post p = new Post();
        FormBody formBody = new FormBody.Builder()
                .add("name","123")
                .build();
        String loginCode = p.post(url+"test/", formBody);
    }
}
