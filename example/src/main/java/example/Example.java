package example;

import io.github.blockneko11.simpleconfig.api.manager.FileConfigManager;
import io.github.blockneko11.simpleconfig.api.manager.InMemoryConfigManager;
import io.github.blockneko11.simpleconfig.impl.Toml4JCommentConfigProvider;
import io.github.blockneko11.simpleconfig.impl.Toml4JConfigProvider;
import io.github.blockneko11.simpleconfig.impl.provider.*;
import org.jetbrains.annotations.TestOnly;

import java.io.File;
import java.util.Arrays;

@TestOnly
public class Example {
    public static void main(String[] args) {
        inMemory();
        file();
    }

    private static void inMemory() {
        try (InMemoryConfigManager<ExampleConfig> holder = InMemoryConfigManager.builder(ExampleConfig.class)
                .build()) {
            System.out.println("--- Test Start ---");
            holder.load();
            ExampleConfig config = holder.get();


            System.out.println("--- Config 'enabled' ---");
            System.out.println("Before: ");
            System.out.println(config.enabled.get());

            System.out.println("After: ");
            config.enabled.set(false);
            System.out.println(config.enabled.get());


            System.out.println("--- Config 'host' ---");
            System.out.println("Before: ");
            System.out.println(config.host.get());

            System.out.println("After: ");
            config.host.set("127.0.0.1");
            System.out.println(config.host.get());


            System.out.println("--- Config 'port' ---");
            System.out.println("Before: ");
            System.out.println(config.port.get());

            System.out.println("After: ");
            config.port.set(3306);
            System.out.println(config.port.get());

            System.out.println("Min: ");
            try {
                config.port.set(0);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println(config.port.get());

            System.out.println("Max: ");
            try {
                config.port.set(65536);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println(config.port.get());


            System.out.println("--- Config 'users' ---");
            System.out.println("Defaults: ");
            System.out.println(config.users.getDefaults());

            System.out.println("Get: ");
            System.out.println(config.users.get());

            System.out.println("Modify: ");
            config.users.addAll(Arrays.asList("BlockNeko_11", "114514", "awa", "Zi_Min"));
            System.out.println(config.users.get());

            System.out.println("Remove: ");
            config.users.remove("114514");
            System.out.println(config.users.get());

            System.out.println("Clear: ");
            config.users.clear();
            System.out.println(config.users.get());


            System.out.println("--- Config 'info' ---");
            System.out.println("Get: ");
            System.out.println(config.info.get());

            System.out.println("Put: ");
            config.info.put("Tel", "114514");
            config.info.put("City", "Beijing");
            System.out.println(config.info.get());

            System.out.println("Remove: ");
            config.info.remove("Tel");
            System.out.println(config.info.get());

            System.out.println("Clear: ");
            config.info.clear();
            System.out.println(config.info.get());


            System.out.println("--- Config 'velocity' ---");
            System.out.println("Before: ");
            System.out.println(config.velocity.get());

            System.out.println("After: ");
            config.velocity.set(8.0D);
            System.out.println(config.velocity.get());


//            System.out.println("--- Config 'player' ---");
//            System.out.println("Get: ");
//            System.out.println(config.player.get());
//
//            System.out.println("Set: ");
//            config.player.set(new PlayerRecord("BlockNeko", 114514));
//            System.out.println(config.player.get());
//
//            System.out.println("Set null: ");
//            config.player.set(null);
//            System.out.println(config.player.get());
            System.out.println("--- Test End ---");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void file() {
        File f = new File("run", "example.yaml");

        if (f.exists()) {
            f.delete();
        }

        try (FileConfigManager<ExampleConfig> holder = FileConfigManager.builder(ExampleConfig.class)
                .provider(new SnakeYamlCommentConfigProvider())
                .file(f)
                .build()) {
            System.out.println("--- Test Start ---");
            System.out.println("--- Load ---");
            holder.load();
            ExampleConfig config = holder.get();


            System.out.println("--- Config 'enabled' ---");
            System.out.println("Before: ");
            System.out.println(config.enabled.get());

            System.out.println("After: ");
            config.enabled.set(false);
            System.out.println(config.enabled.get());


            System.out.println("--- Config 'host' ---");
            System.out.println("Before: ");
            System.out.println(config.host.get());

            System.out.println("After: ");
            config.host.set("127.0.0.1");
            System.out.println(config.host.get());


            System.out.println("--- Config 'port' ---");
            System.out.println("Before: ");
            System.out.println(config.port.get());

            System.out.println("After: ");
            config.port.set(3306);
            System.out.println(config.port.get());

            System.out.println("Min: ");
            try {
                config.port.set(0);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println(config.port.get());

            System.out.println("Max: ");
            try {
                config.port.set(65536);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println(config.port.get());


            System.out.println("--- Config 'users' ---");
            System.out.println("Defaults: ");
            System.out.println(config.users.getDefaults());

            System.out.println("Get: ");
            System.out.println(config.users.get());

            System.out.println("Modify: ");
            config.users.addAll(Arrays.asList("BlockNeko_11", "114514", "awa", "Zi_Min"));
            System.out.println(config.users.get());

            System.out.println("Remove: ");
            config.users.remove("114514");
            System.out.println(config.users.get());


            System.out.println("--- Config 'info' ---");
            System.out.println("Get: ");
            System.out.println(config.info.get());

            System.out.println("Put: ");
            config.info.put("tel", "114514");
            config.info.put("city", "Beijing");
            System.out.println(config.info.get());

            System.out.println("Remove: ");
            config.info.remove("tel");
            System.out.println(config.info.get());


            System.out.println("--- Config 'velocity' ---");
            System.out.println("Before: ");
            System.out.println(config.velocity.get());

            System.out.println("After: ");
            config.velocity.set(8.0D);
            System.out.println(config.velocity.get());


//            System.out.println("--- Config 'player' ---");
//            System.out.println("Get: ");
//            System.out.println(config.player.get());
//
//            System.out.println("Set: ");
//            config.player.set(new PlayerRecord("BlockNeko", 114514));
//            System.out.println(config.player.get());


            System.out.println("--- Save ---");
            holder.save();
            holder.set(null);


            System.out.println("--- Reload ---");
            holder.load();

            System.out.println(config.enabled.get());
            System.out.println(config.host.get());
            System.out.println(config.port.get());
            System.out.println(config.users.get());
            System.out.println(config.info.get());
            System.out.println(config.velocity.get());
//            System.out.println(config.player.get());
            System.out.println("--- Test End ---");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
