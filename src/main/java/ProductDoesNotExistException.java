public class ProductDoesNotExistException extends NullPointerException {
    public ProductDoesNotExistException(String message) {
        super(message);
    }

    public ProductDoesNotExistException() {
        super("Product does not exist");
    }
}
