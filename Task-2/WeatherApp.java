import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherApp {

  public static void main(String[] args) {

    try {
      double latitude = 19.0760;
      double longitude = 72.8777;

      String url = "https://api.open-meteo.com/v1/forecast?latitude="
          + latitude + "&longitude=" + longitude
          + "&current_weather=true";

      HttpClient client = HttpClient.newHttpClient();

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(url))
          .GET()
          .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      String body = response.body();

      // 👇 ONLY extract current_weather block
      String currentWeatherBlock = body.split("\"current_weather\":\\{")[1].split("\\}")[0];

      String time = currentWeatherBlock.split("\"time\":\"")[1].split("\"")[0];
      String temperature = currentWeatherBlock.split("\"temperature\":")[1].split(",")[0];
      String windspeed = currentWeatherBlock.split("\"windspeed\":")[1].split(",")[0];
      String weatherCode = currentWeatherBlock.split("\"weathercode\":")[1].split(",")[0];

      System.out.println("=================================");
      System.out.println("         WEATHER REPORT");
      System.out.println("=================================");
      System.out.println("Location     : Mumbai, India");
      System.out.println("Time         : " + time);
      System.out.println("Temperature  : " + temperature + " °C");
      System.out.println("Wind Speed   : " + windspeed + " km/h");
      System.out.println("Weather Code : " + weatherCode);
      System.out.println("=================================");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}