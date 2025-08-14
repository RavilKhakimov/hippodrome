import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.MockedStatic;
import org.junit.jupiter.api.DisplayName;


public class HorseTest {
    @Test
    @DisplayName("Тест на то что конструктор выбросит IllegalArgumentException когда имя null")
    public void constructorThrowIllegalArgumentExceptionWhenNameIsNull(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10, 10));
    }
    @Test
    @DisplayName("Тест на то что конструктор при иссключении будет содержать сообщение Name cannot be null, когда имя null")
    public void constructorThrowIllegalArgumentExceptionWithMessageWhenNameIsNull(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10, 10));
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "\r", " \t\n\r"})
    @DisplayName("Тест на то что конструктор выбросит IllegalArgumentException когда имя содержит только пробельные символы")
    void constructorThrowIllegalArgumentExceptionWhenNameIsBlank(String blankName) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(blankName, 10, 10));
    }
    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "\r", " \t\n\r"})
    @DisplayName("Тест на то что конструктор при иссключении будет содержать сообщение Name cannot be blank, когда имя пустое или содержит только пробельные символы")
    public void constructorThrowIllegalArgumentExceptionWithMessageWhenNameIsBlank(String blankName){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(blankName, 10, 10));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    @DisplayName("Тест на то что конструктор выбросит IllegalArgumentException когда скорость отрицательная")
    public void constructorThrowIllegalArgumentExceptionWhenSpeedIsNegative(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -10, 10));
    }
    @Test
    @DisplayName("Тест на то что конструктор при иссключении будет содержать сообщение Speed cannot be negative, когда скорость отрицательная")
    public void constructorThrowIllegalArgumentExceptionWithMessageWhenSpeedIsNegative(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -10, 10));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    @DisplayName("Тест на то что конструктор выбросит IllegalArgumentException когда дистанция отрицательная")
    public void constructorThrowIllegalArgumentExceptionWhenDistanceIsNegative(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 10, -10));
    }
    @Test
    @DisplayName("Тест на то что конструктор при иссключении будет содержать сообщение Distance cannot be negative, когда дистанция отрицательная")
    public void constructorThrowIllegalArgumentExceptionWithMessageWhenDistanceIsNegative(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 10, -10));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    @DisplayName("Тест на то что метод move() будет вызывать метод getRandomDouble()")
    public void moveCallGetRandomDouble(){
      Horse horse = new Horse("Horse", 10.0, 10.0);

      try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
          // Задаём поведение getRandomDouble, чтобы не возвращал случайное значение
          mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

          horse.move();

          // Проверяем что getRandomDouble вызван с параметрами 0.2 и 0.9
          mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
      }
    }
    @Test
    @DisplayName("Тест на то что move() увеличивает дистанцию")
    void move_IncreasesDistance() {
      double speed = 10.0;
          double initialDistance = 5.0;
          Horse horse = new Horse("Horse", speed, initialDistance);

          double mockedRandomValue = 0.7;

          try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
              mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockedRandomValue);

              horse.move();

              double expectedDistance = initialDistance + speed * mockedRandomValue;
              assertEquals(expectedDistance, horse.getDistance(), 1e-6);
          }
    }
    @Test 
    @DisplayName("Тест на то что метод getName возвращает строку, которая была передана первым параметром в конструктор")
    public void getNameReturnName(){
        Horse horse = new Horse("Horse", 10, 10);
        assertEquals("Horse", horse.getName());
    }
    @Test
    @DisplayName("Тест на то что метод getSpeed возвращает число, которое было передано вторым параметром в конструктор")
    public void getSpeedReturnSpeed(){
        Horse horse = new Horse("Horse", 10, 10);
        assertEquals(10, horse.getSpeed());
    }
    @Test
    @DisplayName("Тест на то что метод getDistance возвращает число, которое было передано третьим параметром в конструктор") 
    public void getDistanceReturnDistance(){
        Horse horse = new Horse("Horse", 10, 10);
        assertEquals(10, horse.getDistance());
    }
    @Test
    @DisplayName("Тест на то что метод getDistance возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами")
    public void getDistanceReturnZeroWhenCreatedWithTwoParameters(){
        Horse horse = new Horse("Horse", 10);
        assertEquals(0, horse.getDistance());
    }
}
