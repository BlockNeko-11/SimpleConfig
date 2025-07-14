package example.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = false)
public final class PlayerRecord {
    private String name = "";
    private int score = 0;
}
