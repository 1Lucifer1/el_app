import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;

public class Get {
    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}