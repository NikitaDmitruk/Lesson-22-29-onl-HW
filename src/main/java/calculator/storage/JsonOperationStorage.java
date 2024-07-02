package calculator.storage;


import calculator.model.Operation;
import calculator.service.OperationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.text.html.Option;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonOperationStorage implements OperationStorage {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private final File HISTORY_JSON_FILE = new File("history.json");

    public void save(Operation operation) {
        try {
            if (!HISTORY_JSON_FILE.exists()) {
                System.out.println(HISTORY_JSON_FILE.createNewFile());
            }
            FileReader reader = new FileReader(HISTORY_JSON_FILE);
            ArrayList<Operation> list = new ArrayList<>();
            if (HISTORY_JSON_FILE.length() == 0) {
                reader.close();
                FileWriter writer = new FileWriter(HISTORY_JSON_FILE);
                list.add(operation);
                gson.toJson(list, writer);
                writer.close();
            } else {
                TypeToken<ArrayList<Operation>> collectionType = new TypeToken<>() {
                };
                list = gson.fromJson(reader, collectionType.getType());
                list.add(operation);
                reader.close();
                FileWriter writer = new FileWriter(HISTORY_JSON_FILE);
                gson.toJson(list, writer);
                writer.close();
            }
        } catch (NullPointerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Operation> findAll() {
        try {
            if (HISTORY_JSON_FILE.exists()) {
                FileReader reader = new FileReader(HISTORY_JSON_FILE);
                TypeToken<ArrayList<Operation>> collectionType = new TypeToken<>() {
                };
                return gson.fromJson(reader, collectionType);
            } else {
                return new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
