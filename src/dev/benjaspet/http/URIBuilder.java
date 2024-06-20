package dev.benjaspet.http;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public final class URIBuilder {

  private String baseURL;
  private String endpoint;
  private final Map<String, String> params;

  public URIBuilder() {
    this.params = new HashMap<>();
  }

  public URIBuilder(String endpoint) {
    this.endpoint = endpoint;
    this.params = new HashMap<>();
  }

  public URIBuilder setBaseURL(String baseURL) {
    this.baseURL = baseURL;
    return this;
  }

  public URIBuilder setEndpoint(String endpoint) {
    this.endpoint = endpoint;
    return this;
  }

  public URIBuilder addParam(String param, String value) {
    this.params.put(param, value);
    return this;
  }

  public URI build() {
    return URI.create(this.baseURL + this.endpoint + "?" + this.params.entrySet().stream()
        .map(e -> e.getKey() + "=" + e.getValue())
        .reduce((a, b) -> a + "&" + b)
        .orElse(""));
  }

  public URIBuilder addParams(Map<String, String> params) {
    this.params.putAll(params);
    return this;
  }
}
