package example;

import io.github.blockneko11.simpleconfig.impl.Toml4JCommentedConfigProvider;
import io.github.blockneko11.simpleconfig.impl.Toml4JConfigProvider;
import org.junit.jupiter.api.Test;

public class ExampleTest {
    @Test
    public void fileTest() {
        Example.file("toml", new Toml4JConfigProvider());
    }

    @Test
    public void fileTestWithComments() {
        Example.file("toml", new Toml4JCommentedConfigProvider());
    }
}
