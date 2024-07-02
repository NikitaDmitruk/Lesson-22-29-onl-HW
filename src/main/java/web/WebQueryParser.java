package web;

import java.util.HashMap;
import java.util.Map;

public class WebQueryParser {
    public Map<String, String> parseQuery(String query) {
        Map<String, String> result = new HashMap<>();
        String[] split = query.split("&");
        for (String s : split) {
            String[] keyValue = s.split("=");
            result.put(keyValue[0], keyValue[1]);
        }
        return result;
    }
}
