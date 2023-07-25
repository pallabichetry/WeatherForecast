package WeatherForecastReport;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Scanner;

public class WeatherForecastReport {

    private static final String url = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";


    public static void main(String[] args) {

        try {
            // calling http client to fetch the data
            HttpClientHelper httpClientHelper = new HttpClientHelper();
            httpClientHelper.GETRequest(WeatherForecastReport.url);
            String data = httpClientHelper.getData();
            // calling JSON client to parse response data to JSON object
            JSONParsing jsonParsing = new JSONParsing(data);
            JSONObject jsonObj = jsonParsing.getJSONObj();


            while (true) {

                System.out.println("Please provide a input from below \n" +
                        "1: Get Weather \n" +
                        "2 : Get Wind speed \n" +
                        "3 : Get Pressure \n" +
                        "0 : Exit \n" +
                        "");

                Scanner sc = new Scanner(System.in);
                String userInput = sc.next();


                if (userInput.equals("1")) {
                    System.out.println("Please enter the date for which weather report needs to be generated : \n");
                    Scanner reportDate = new Scanner(System.in);
                    String date = reportDate.next();

                    JSONArray list = jsonObj.getJSONArray("list");

                    Iterator<Object> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        JSONObject row = (JSONObject) iterator.next();
                        if (row.getString("dt_txt").contains(date)) {
                            System.out.println(row.getJSONArray("weather").getJSONObject(0));
                        }
                    }
                } else if (userInput.equals("3")) {
                    System.out.println("Please enter the date for which pressure report needs to be generated : \n");
                    Scanner reportDate = new Scanner(System.in);
                    String date = reportDate.next();
                    JSONArray list = jsonObj.getJSONArray("list");

                    Iterator<Object> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        JSONObject row = (JSONObject) iterator.next();
                        if (row.getString("dt_txt").contains(date)) {
                            System.out.println(row.getJSONObject("main").getDouble("pressure"));
                        }
                    }
                } else if (userInput.equals("2")) {
                    System.out.println("Please enter the date for which wind speed report needs to be generated : \n");
                    Scanner reportDate = new Scanner(System.in);
                    String date = reportDate.next();
                    JSONArray list = jsonObj.getJSONArray("list");
                    Iterator<Object> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        JSONObject row = (JSONObject) iterator.next();
                        if (row.getString("dt_txt").contains(date)) {
                            System.out.println(row.getJSONObject("wind").getDouble("speed"));
                        }
                    }
                } else if (userInput.equals("0")) {
                    System.out.println("Exiting from the program");
                    System.exit(0);
                } else {
                    System.out.println("wrong input :: retry ");
                }
            }
        } catch (Exception e) {
            System.out.println("Error while generating the report ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
