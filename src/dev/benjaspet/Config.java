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

package dev.benjaspet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;

public final class Config {

  private final Gson gson;
  private JsonReader reader;

  public Config(Gson gson) throws FileNotFoundException {
    this.gson = gson;
    this.load();
  }

  /**
   * Load the configuration file. The configuration file must be named "config.json"
   * and must be in the root directory.
   *
   * @throws FileNotFoundException if the configuration file is not found.
   */
  public void load() throws FileNotFoundException {
    try {
      this.reader = new JsonReader(new FileReader(Paths.get("config.json").toFile()));
    } catch (FileNotFoundException e) {
      System.out.println("Error: " + e.getMessage());
      throw e;
    }
  }

  /**
   * Gets the API key from the configuration file, as a string. If the key is not
   * found (due to the key not being present in the configuration file, or the JSON
   * being malformed, or the JSON key is misnamed or at the incorrect expected level)
   * then this method will return {@code null}.
   *
   * @return the API key as a string. If it is not found, it will return {@code null}.
   */
  public String getApiKey() {
    JsonObject config = this.gson.fromJson(this.reader, JsonObject.class);
    return config.get("mbta-api-key").getAsString();
  }
}
