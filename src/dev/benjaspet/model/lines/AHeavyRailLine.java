package dev.benjaspet.model.lines;

import dev.benjaspet.http.RequestManager;

import java.util.ArrayList;
import java.util.List;

public abstract class AHeavyRailLine<T> {

  protected final RequestManager reqManager;
  protected final List<T> stops;

  public AHeavyRailLine(RequestManager reqManager) {
    this.reqManager = reqManager;
    this.stops = new ArrayList<>();
  }

  public abstract List<T> getInboundStops();

  public abstract List<T> getOutboundStops();
}
