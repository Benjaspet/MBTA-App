package dev.benjaspet.model;

import java.util.List;

/**
 * Interface for heavy rail lines.
 * @param <T> the type of stop on the line
 */
public interface IHeavyRailLine<T> {

  List<T> getInboundStops();

  List<T> getOutboundStops();
}
