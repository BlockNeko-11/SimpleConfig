package example;

import io.github.blockneko11.simpleconfig.impl.provider.SnakeYamlCommentConfigProvider;
import io.github.blockneko11.simpleconfig.impl.provider.SnakeYamlConfigProvider;
import org.junit.jupiter.api.Test;

public class ExampleTest {
    @Test
    public void fileTest() {
        Example.file("yaml", new SnakeYamlConfigProvider());
    }

    @Test
    public void fileTestWithComments() {
        Example.file("yaml", new SnakeYamlCommentConfigProvider());
    }
}
