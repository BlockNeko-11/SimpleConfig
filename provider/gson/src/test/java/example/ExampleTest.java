package example;

import io.github.blockneko11.simpleconfig.impl.provider.GsonConfigProvider;
import org.junit.jupiter.api.Test;

public class ExampleTest {
    @Test
    public void fileTest() {
        Example.file("json", new GsonConfigProvider());
    }
}
