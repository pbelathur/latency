package latency_troubleshooter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class Response {

    private UUID id;
    private String name;
    private String description;
    private Instant when;
}
