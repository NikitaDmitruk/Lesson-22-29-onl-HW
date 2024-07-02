package calculator.storage;



import calculator.model.Operation;

import java.util.List;

public interface OperationStorage {
    void save(Operation operation);
    List<Operation> findAll();
}
