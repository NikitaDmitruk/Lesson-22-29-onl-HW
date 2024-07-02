package calculator;

import com.sun.net.httpserver.HttpServer;
import web.WebCalculatorHttpHandler;
import web.WebHistoryHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebApplication implements Application {
    @Override
    public void run() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        httpServer.createContext("/calc", new WebCalculatorHttpHandler());
        httpServer.createContext("/history", new WebHistoryHandler());
        httpServer.start();
    }
}
