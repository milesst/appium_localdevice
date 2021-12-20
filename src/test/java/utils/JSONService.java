package utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

public class JSONService {

    public static JSONObject readJsonFromFile(String path) {
        File file = new File(path);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static String getUrl(JSONObject jsonObject) throws JSONException {
        return jsonObject.getJSONObject("appium").getString("url");
    }

    public static JSONObject getCapabilities(JSONObject jsonObject) {
        try {
            return jsonObject.getJSONObject("appium").getJSONObject("capabilities");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
