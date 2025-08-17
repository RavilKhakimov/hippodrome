import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {
    @Test
    @Disabled("Отключен, чтобы не замедлять общий прогон тестов")
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void main_ShouldCompleteWithin22Seconds() throws Exception {
        // Запускаем main метод в отдельном потоке
        Thread testThread = new Thread(() -> {
            try {
                Main.main(new String[]{});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        testThread.start();
        testThread.join(22000); // Ожидаем завершения не более 22 секунд

        if (testThread.isAlive()) {
            testThread.interrupt(); // Прерываем если не завершился
            throw new AssertionError("Метод main выполнялся дольше 22 секунд");
        }
    }
}
