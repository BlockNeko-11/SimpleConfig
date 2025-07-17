package example;

import io.github.blockneko11.simpleconfig.impl.provider.TCCommentConfigProvider;
import io.github.blockneko11.simpleconfig.impl.provider.TCConfigProvider;
import org.junit.jupiter.api.Test;

public class ExampleTest {
    @Test
    public void fileTest() {
        Example.file("conf", new TCConfigProvider());
    }

    @Test
    public void fileTestWithComments() {
        Example.file("conf", new TCCommentConfigProvider());
    }
}
