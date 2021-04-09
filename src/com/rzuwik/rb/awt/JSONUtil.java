

package com.rzuwik.rb.awt;

import lombok.Getter;
import lombok.SneakyThrows;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class JSONUtil {

    private static final String USER_AGENT = "Bot/3.0.0 (http://rzuwikt.tk)";
    private static final String UA = "User-Agent";
    private static final String AUTH = "Authorization";
    @Getter
    private static final OkHttpClient client = new OkHttpClient();

    public static byte[] download(String url, String authorization) throws IOException {
        Response res = downloadResponse(url, authorization);
        return res.body() == null ? new byte[0] : res.body().bytes();
    }

    public static Response downloadResponse(String url, String authorization) throws IOException {
        return downloadResponse(url, authorization, new HashMap<>());
    }

    public static Response downloadResponse(String url, String authorization, Map<String, String> headers) throws IOException {
        Request.Builder req = new Request.Builder()
                .header(UA, USER_AGENT);
        if (authorization != null) req = req.header(AUTH, authorization);
        for (Map.Entry<String, String> e : headers.entrySet()) {
            req.header(e.getKey(), e.getValue());
        }
        req = req.url(url);
        return client.newCall(req.build()).execute();
    }

    public static Response headRequest(String url) throws IOException {
        return client.newCall(new Request.Builder().head().header(UA, USER_AGENT).url(url).build()).execute();
    }

    public static Response postRequest(String url, MediaType type, String content, String authorization) throws IOException {
        Request.Builder req = new Request.Builder()
                .header(UA, USER_AGENT);
        if (authorization != null) req = req.header(AUTH, authorization);
        req = req.url(url)
                .post(RequestBody.create(type, content));
        return client.newCall(req.build()).execute();
    }

    @SneakyThrows
    public static JSONResponse getJson(String url) {
        try {
            Request req = new Request.Builder()
                    .header(UA, USER_AGENT)
                    .url(url)
                    .build();
            Response res = client.newCall(req).execute();
            return res.body() == null ? null : new JSONResponse(res.body().string(), res.code());
        } catch (IOException | JSONException ignored) {
        }
        return null;
    }
}



