package dev.benjaspet.mbta;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.benjaspet.http.RequestManager;

public class BlueLineRequests {

  private final RequestManager requestManager;

  public BlueLineRequests(RequestManager requestManager) {
    this.requestManager = requestManager;
  }

  public JsonArray updateAllBlueLineStops() {
    JsonObject o = this.requestManager.get("https://api-v3.mbta.com/stops?filter[route]=Blue");
    return o.getAsJsonArray("data");
  }
}
