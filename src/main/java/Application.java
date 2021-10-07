import java.io.IOException;

/**
 * @author Abishek Dulal on 2021-10-07
 */
public class Application {

    public static void main(String[] args) throws IOException {
        MonkeyClientProcessor clientProcessor = new MonkeyClientProcessor();
        clientProcessor.process();
    }

}
