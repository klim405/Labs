package utils;


public class ContractAlreadyCompleted extends Exception {
    public ContractAlreadyCompleted() {
        super("Контракт уже завершён");
    }
}
