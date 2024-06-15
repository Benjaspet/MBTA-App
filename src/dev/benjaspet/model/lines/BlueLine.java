package dev.benjaspet.model.lines;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.benjaspet.http.RequestManager;
import dev.benjaspet.mbta.BlueLineRequests;
import dev.benjaspet.model.BlueLineStop;
import dev.benjaspet.model.IHeavyRailLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlueLine extends AHeavyRailLine<BlueLineStop> implements IHeavyRailLine<BlueLineStop> {

  public BlueLine(RequestManager reqManager) {
    super(reqManager);
  }

  public List<BlueLineStop> getInboundStops() {

    BlueLineRequests blueLineRequests = new BlueLineRequests(this.reqManager);

    JsonArray dataArray = blueLineRequests.updateAllBlueLineStops();

    for (int i = 0; i < dataArray.size(); i++) {
      JsonObject stop = dataArray.getAsJsonArray().get(i).getAsJsonObject().get("attributes").getAsJsonObject();
      String stopName = stop.get("name").getAsString();
      String stopId = dataArray.getAsJsonArray().get(i).getAsJsonObject().get("id").getAsString();
      BigDecimal latitude = stop.get("latitude").getAsBigDecimal();
      BigDecimal longitude = stop.get("longitude").getAsBigDecimal();
      String municipality = stop.get("municipality").getAsString();
      String address = stop.get("address").getAsString();

      BlueLineStop blueLineStop = new BlueLineStop(latitude, longitude, stopName, municipality, stopId, address);

      this.stops.add(blueLineStop);

    }

    return Collections.unmodifiableList(this.stops);
  }

  public List<BlueLineStop> getOutboundStops() {
    List<BlueLineStop> copy = new ArrayList<>(this.getInboundStops());
    Collections.reverse(copy);
    return Collections.unmodifiableList(copy);
  }
}
