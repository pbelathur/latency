package latency_troubleshooter;

import java.time.Instant;
import java.util.UUID;

public class Response {

    private UUID id;
    private String name;
    private String description;
    private Instant when;

    public Response(UUID id, String name, String description, Instant when) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.when = when;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getWhen() {
        return when;
    }

    public void setWhen(Instant when) {
        this.when = when;
    }
}
