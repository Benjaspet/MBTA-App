/*
 * Copyright © 2024 Ben Petrillo. All rights reserved.
 *
 * Project licensed under the MIT License: https://www.mit.edu/~amini/LICENSE.md
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * All portions of this software are available for public use, provided that
 * credit is given to the original author(s).
 */

import com.google.gson.Gson;
import dev.benjaspet.Config;
import dev.benjaspet.http.RequestManager;
import dev.benjaspet.http.URIBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.net.URI;

import static org.junit.Assert.assertEquals;

public class APIRequestTests {

  private Gson gson;
  private OkHttpClient client;

  private Config config;
  private RequestManager requestManager;
  private String apiKey;

  private static final String BASE_URL = "https://api-v3.mbta.com";

  @Before
  public void setUp() throws FileNotFoundException {
    gson = new Gson();
    client = new OkHttpClient();
    config = new Config(gson);
    apiKey = config.getApiKey();
    requestManager = new RequestManager(client, gson, apiKey);
  }

  @Test(expected = RuntimeException.class)
  public void testInvalidAPIKey() {
    Request request = new Request.Builder()
        .addHeader("Accept", "application/json")
        .addHeader("x-api-key", "INVALID_API_KEY")
        .url("https://api-v3.mbta.com/stops?api_key=BAD_KEY&filter[route]=Blue")
        .build();
    this.requestManager.get(request.url().toString());
  }

  @Test
  public void testUri() {
    URI uri = new URIBuilder()
        .setBaseURL(BASE_URL)
        .setEndpoint("/stops")
        .addParam("api_key", apiKey)
        .addParam("filter[route]", "Blue")
        .build();
    String expected = String.format("%s%s?api_key=%s&filter[route]=Blue", BASE_URL, "/stops", apiKey);
    String actual = uri.toString();
    assertEquals(expected, actual);
  }
}
