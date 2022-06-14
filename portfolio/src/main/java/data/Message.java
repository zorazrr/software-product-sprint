package data;

public final class Message {
    private final long id;
    private final String email;
    private final String message;
    private final String drink;
    private final long timestamp;

    public Message(long id, String email, String message, String drink, long timestamp) {
        this.id = id;
        this.email = email;
        this.message = message;
        this.drink = drink;
        this.timestamp = timestamp;
    }
}
