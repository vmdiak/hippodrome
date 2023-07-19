import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class HippodromeTest {
    @Test
    public void constructorNullFirst() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void constructorNullFirstThrowMassage() {
        Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        Assertions.assertEquals("Horses cannot be null.", throwable.getMessage());
    }

    @Test
    public void constructorVoidListFirst() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void constructorVoidListFirstThrowMassage() {
        Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        Assertions.assertEquals("Horses cannot be empty.", throwable.getMessage());
    }

    @Test
    public void getHorsesReturnListSameToConstructorList() {
        ArrayList<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse_" + i, 2.3, 50));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertArrayEquals(horses.toArray(), hippodrome.getHorses().toArray());
    }

    @Test
    public void moveCallAllHorsesMove() {
        List<Horse> mockHorses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            mockHorses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();

        for (Horse mockHorse:
             mockHorses) {
            verify(mockHorse).move();
        }
    }

    @Test
    public void getWinnerReturnGreatestDistance() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse_" + i, 2.3, 50 + i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        double maxDistance = 0;
        for (Horse val:
             horses) {
            maxDistance = Math.max(maxDistance, val.getDistance());
        }

        Assertions.assertEquals(maxDistance, hippodrome.getWinner().getDistance());
    }
}
