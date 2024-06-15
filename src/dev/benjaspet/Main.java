package dev.benjaspet;

import com.google.gson.Gson;
import dev.benjaspet.http.RequestManager;
import dev.benjaspet.model.BlueLineStop;
import dev.benjaspet.model.IHeavyRailLine;
import dev.benjaspet.model.lines.BlueLine;
import okhttp3.OkHttpClient;

import java.io.FileNotFoundException;
import java.util.List;

public final class Main {


  public static void main(String[] args) {
    System.out.println("Hello, World!");

    try {
      Gson gson = new Gson();
      Config config = new Config(gson);
      config.load();
      String key = config.getApiKey();

      OkHttpClient client = new OkHttpClient();
      System.out.println("API Key: " + key);

      try {

        RequestManager requestManager = new RequestManager(client, gson, key);
        System.out.println("Blue Line Stops: " + 12);
        IHeavyRailLine<BlueLineStop> blueLine = new BlueLine(requestManager);

        List<BlueLineStop> blueLineStops = blueLine.getOutboundStops();

        for (BlueLineStop stop : blueLineStops) {
          System.out.println(stop.getFriendlyName());
        }

      } catch (RuntimeException e) {
        System.out.println("Error: " + e.getMessage());
      }



    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }

  }
}