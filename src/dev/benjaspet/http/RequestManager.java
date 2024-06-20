package dev.benjaspet.http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public final class RequestManager {

  private final OkHttpClient client;
  private final Gson gson;
  private final String apiKey;

  public RequestManager(OkHttpClient client, Gson gson, String apiKey) {
    this.client = client;
    this.gson = gson;
    this.apiKey = apiKey;
  }

  public JsonObject get(String url) throws RuntimeException {

    Request request = new Request.Builder()
        .addHeader("Accept", "application/json")
        .addHeader("x-api-key", this.apiKey)
        .url(url)
        .build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new RuntimeException("Unexpected code " + response);
      }
      ResponseBody body = response.body();
      System.out.println("Response: " + response.code());
      if (body == null) {
        throw new RuntimeException("Response body is null");
      }
      return gson.fromJson(body.string(), JsonObject.class);
    } catch (IOException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

}
