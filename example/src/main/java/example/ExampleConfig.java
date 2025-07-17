package example;

import example.model.Result;
import io.github.blockneko11.simpleconfig.api.Config;
import io.github.blockneko11.simpleconfig.api.annotation.Comment;
import io.github.blockneko11.simpleconfig.api.holder.base.ObjectConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.collection.ListConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.collection.MapConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.number.DoubleConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.number.IntegerConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.base.BooleanConfigHolder;
import io.github.blockneko11.simpleconfig.impl.adapter.lang.EnumAdapter;
import io.github.blockneko11.simpleconfig.impl.adapter.lang.OptionalAdapter;

import java.util.Optional;

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
    public final ObjectConfigHolder<String> host = ObjectConfigHolder.builder(String.class)
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

    public final ObjectConfigHolder<Optional<String>> author = ObjectConfigHolder.builder(
            (Class<Optional<String>>) (Class<?>) Optional.class)
            .adapter(new OptionalAdapter<>())
            .defaults(Optional::empty)
            .build();

    public final ObjectConfigHolder<Result> result = ObjectConfigHolder.builder(Result.class)
            .adapter(new EnumAdapter<>(Result.class))
            .defaults(Result.PASS)
            .build();

//    public final ConfigHolder<PlayerRecord> player = ConfigHolder.builder(PlayerRecord.class)
//            .defaults(PlayerRecord::new)
//            .build();
}
