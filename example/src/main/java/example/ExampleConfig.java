package example;

import example.model.PlayerRecord;
import io.github.blockneko11.simpleconfig.api.Config;
import io.github.blockneko11.simpleconfig.api.annotation.Comment;
import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.collection.ListConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.collection.MapConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.number.DoubleConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.number.IntegerConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.base.BooleanConfigHolder;

@Comment({
        "Test Config",
        "Hello World!"
})
public class ExampleConfig implements Config {
    public final BooleanConfigHolder enabled = BooleanConfigHolder.builder()
            .defaults(true)
            .build();

    @Comment({
            "Minecraft Server host",
            "Default: localhost"
    })
    public final ConfigHolder<String> host = ConfigHolder.builder(String.class)
            .defaults("localhost")
            .build();

    public final IntegerConfigHolder port = IntegerConfigHolder.builder()
            .min(1)
            .max(65535)
            .defaults(25565)
            .build();

    public final ListConfigHolder<String> users = ListConfigHolder.builder(String.class)
            .build();

    public final MapConfigHolder<String, String> info = MapConfigHolder.builder(String.class, String.class)
            .build();

    public final DoubleConfigHolder velocity = DoubleConfigHolder.builder()
            .min(0.0D)
            .max(10.0D)
            .defaults(5.0D)
            .build();

//    public final ConfigHolder<PlayerRecord> player = ConfigHolder.builder(PlayerRecord.class)
//            .defaults(PlayerRecord::new)
//            .build();
}
