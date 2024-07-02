package web;

import calculator.model.Operation;
import calculator.service.OperationService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;

public class WebCalculatorHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        WebQueryParser parser = new WebQueryParser();
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> stringStringMap = parser.parseQuery(query);
        Operation operation = new Operation(Double.parseDouble(stringStringMap
                .get("num1")), Double.parseDouble(stringStringMap
                .get("num2")), stringStringMap
                .get("type"));
        OperationService operationService = new OperationService();
        operationService.execute(operation);
        String result = "Result=%s".formatted(operation.getResult());
        exchange.sendResponseHeaders(200, result.length());
        exchange.getResponseBody().write(result.getBytes());
        exchange.close();
    }
}
