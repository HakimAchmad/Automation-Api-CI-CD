package Utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumber {
    int random = ThreadLocalRandom.current().nextInt();

    public String randomUsername(){
        return "hakim" + + random;
    }
}
