import jdk.nashorn.internal.ir.WhileNode;
import okhttp3.*;

import java.io.IOException;

public class Post {
    public String returnInfo;

    public String post(String url, FormBody formBody) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request r = new Request.Builder().url(url).post(formBody).build();
        Call call = okHttpClient.newCall(r);
        try (Response response = okHttpClient.newCall(r).execute()) {
            returnInfo = response.body().string();
            response.close();
            return returnInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnInfo;
    }
}
