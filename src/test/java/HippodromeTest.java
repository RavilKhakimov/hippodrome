import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class HippodromeTest {
    @Test
    @DisplayName("Конструктор выбросит IllegalArgumentException когда передан null")
    public void constructorThrowIllegalArgumentExceptionWhenIsNull(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    @DisplayName("Конструктор, когда передан null, выбросит IllegalArgumentException с сообщением \"Horses cannot be null.\"")
    public void constructorThrowIllegalArgumentExceptionWithMessageWhenIsNull(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Конструктор выбросит IllegalArgumentException когда список isEmpty")
    public void constructorThrowIllegalArgumentExceptionWhenIsEmpty(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
    }

    @Test
    @DisplayName("Если в конструктор, передан список isEmpty, выбросит IllegalArgumentException с сообщением \"Horses cannot be empty.\"")
    public void constructorThrowIllegalArgumentExceptionWithMessageWhenIsEmpty(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("Метод getHorses() возвращает те же объекты в том же порядке")
    public void getHorses_ReturnsSameObjectsInSameOrder() {

        List<Horse> originalHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
               originalHorses.add(new Horse("Horse " + i, 10 + i));
        }

        Hippodrome hippodrome = new Hippodrome(originalHorses);

        List<Horse> returnHorses = hippodrome.getHorses();

        assertEquals(30, returnHorses.size());
        assertSame(originalHorses.get(0), returnHorses.get(0));
        assertSame(originalHorses.get(29), returnHorses.get(29));
        assertEquals(originalHorses, returnHorses, "Весь список должен совпадать");
    }

    @Test
    @DisplayName("Метод move() вызывает move() у всех лошадей")
    public void move_CallsMoveOnAllHorses() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockHorses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(mockHorses);

        hippodrome.move();

        for (Horse mockHorse : mockHorses){
            verify(mockHorse, times(1)).move();
        }
    }

    @Test
    @DisplayName("Метод getWinner() возвращает лошадь с наибольшим distance")
    void getWinner_ReturnsHorseWithMaxDistance() {
        Horse expectedWinner = new Horse("Winner", 10, 100);
        List<Horse> horses = List.of(
                new Horse("Horse1", 10, 20),
                new Horse("Horse2", 10, 30),
                expectedWinner,
                new Horse("Horse24", 10, 50)
        );

        Hippodrome hippodrome = new Hippodrome(horses);

        Horse actualWinner = hippodrome.getWinner();

        assertSame(actualWinner, expectedWinner);
    }
}
