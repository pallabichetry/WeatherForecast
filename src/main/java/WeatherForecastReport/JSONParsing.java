package WeatherForecastReport;

import org.json.JSONObject;

public class JSONParsing {
    private String data;

    public JSONParsing(String data) {
        this.data = data;
    }

    public JSONObject getJSONObj() {

        JSONObject jsonObj = new JSONObject(data);
        return jsonObj;
    }

}
