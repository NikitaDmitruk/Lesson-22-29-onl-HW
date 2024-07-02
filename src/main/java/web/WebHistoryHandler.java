package web;

import calculator.model.Operation;
import calculator.service.OperationService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;

public class WebHistoryHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OperationService service = new OperationService();
        Gson gson = new Gson();
        List<Operation> operations = service.getAllOperations();
        if (!operations.isEmpty()) {
            String history = gson.toJson(operations);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, history.length());
            exchange.getResponseBody().write(history.getBytes());
        } else {
            String message = "Operations not found";
            exchange.sendResponseHeaders(200, message.length());
            exchange.getResponseBody().write(message.getBytes());
        }
    }
}
