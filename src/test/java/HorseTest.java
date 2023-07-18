import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


public class HorseTest {
    @Test
    public void constructorNullFirst() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.3, 50));
    }

    @Test
    public void constructorNullFirstThrowMassage() {
        Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.3, 50));
        Assertions.assertEquals("Name cannot be null.", throwable.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    public void constructorSpacesFirst (String str) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(str, 2.3, 50));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    public void constructorSpacesFirstThrowMassage (String str) {
        Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(str, 2.3, 50));
        Assertions.assertEquals("Name cannot be blank.", throwable.getMessage());
    }

    @Test
    public void constructorNegativeSecond() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("vfvf", -2.3, 50));
    }

    @Test
    public void constructorNegativeSecondThrowMassage() {
        Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("dvfd", -2.3, 50));
        Assertions.assertEquals("Speed cannot be negative.", throwable.getMessage());
    }

    @Test
    public void constructorNegativeThird() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("vfvf", 2.3, -50));
    }

    @Test
    public void constructorNegativeThirdThrowMassage() {
        Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("dvfd", 2.3, -50));
        Assertions.assertEquals("Distance cannot be negative.", throwable.getMessage());
    }

    @Test
    public void getNameReturnNameFromConstructor() {
        Horse horse = new Horse("bla", 2.3, 50);
        Assertions.assertEquals("bla", horse.getName());
    }

    @Test
    public void getSpeedReturnSpeedFromConstructor() {
        Horse horse = new Horse("bla", 2.3, 50);
        Assertions.assertEquals(2.3, horse.getSpeed());
    }

    @Test
    public void getDistanceReturnDistanceFromConstructor() {
        Horse horse = new Horse("bla", 2.3, 50);
        Assertions.assertEquals(50, horse.getDistance());
    }

    @Test
    public void getDistanceReturnZeroFromSecondConstructor() {
        Horse horse = new Horse("bla", 2.3);
        Assertions.assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveCallGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("bla", 2.5,50);
            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
