package io.github.blockneko11.simpleconfig.impl.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import io.github.blockneko11.simpleconfig.api.provider.ConfigProvider;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GsonConfigProvider implements ConfigProvider {
    private final Gson gson;

    public GsonConfigProvider() {
        this(builder -> builder.serializeNulls()
                .setPrettyPrinting());
    }

    public GsonConfigProvider(Gson gson) {
        this.gson = gson;
    }

    public GsonConfigProvider(Consumer<GsonBuilder> consumer) {
        GsonBuilder builder = new GsonBuilder();
        consumer.accept(builder);
        this.gson = builder.create();
    }

    @Override
    public Map<String, Object> parse(String config) {
        try {
            return (Map<String, Object>) this.gson.fromJson(config, LinkedHashMap.class);
        } catch (JsonSyntaxException e) {
            return new HashMap<>();
        }
    }

    @Override
    public String serialize(Map<String, Object> config) {
        return this.gson.toJson(config);
    }
}
