package comp1110.ass2;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

//Author: Mei Yee Chin
public class TestNeighbour {
    @Rule
    public Timeout globalTimeout = Timeout.millis(20000);

    @Test
    public void Corners() {
        TestNeighbour(0, new int[] {1,8,9,0,0,0,0,0});
        TestNeighbour(7, new int[] {6,14,15,0,0,0,0,0});
        TestNeighbour(24, new int[] {16,17,25,0,0,0,0,0});
        TestNeighbour(31, new int[] {22,23,30,0,0,0,0,0});

    }

    @Test
    public void HorizontalEdges() {
        TestNeighbour(1, new int[] {0,2,8,9,10,0,0,0,0});
        TestNeighbour(2, new int[] {1,3,9,10,11,0,0,0,0});
        TestNeighbour(3, new int[] {2,4,10,11,12,0,0,0});
        TestNeighbour(4, new int[] {3,5,11,12,13,0,0,0});
        TestNeighbour(5, new int[] {4,6,12,13,14,0,0,0});
        TestNeighbour(6, new int[] {5,7,13,14,15,0,0,0});

    }

    @Test
    public void VerticalEdges() {
        TestNeighbour(8, new int[] {0,1,9,16,17,0,0,0});
        TestNeighbour(16, new int[] {8,9,17,24,25,0,0,0});
    }

    @Test
    public void Middle() {
        TestNeighbour(9, new int[] {0,1,2,8,10,16,17,18});
        TestNeighbour(10, new int[] {1,2,3,9,11,17,18,19});
        TestNeighbour(11, new int[] {2,3,4,10,12,18,19,20});
        TestNeighbour(12, new int[] {3,4,5,11,13,19,20,21});
        TestNeighbour(13, new int[] {4,5,6,12,14,20,21,22});
        TestNeighbour(14, new int[] {5,6,7,13,15,21,22,23});
        TestNeighbour(17, new int[] {8,9,10,16,18,24,25,26});
        TestNeighbour(18, new int[] {9,10,11,17,19,25,26,27});
        TestNeighbour(19, new int[] {10,11,12,18,20,26,27,28});
        TestNeighbour(20, new int[] {11,12,13,19,21,27,28,29});
        TestNeighbour(21, new int[] {12,13,14,20,22,28,29,30});
        TestNeighbour(22, new int[] {13,14,15,21,23,29,30,31});

    }

    public void TestNeighbour (int positionID, int[] expected) {
        int[] output = Node.getNeighbours(positionID);
        if (output[0]!=expected[0]||
                output[1]!=expected[1]||
                output[2]!=expected[2]||
                output[3]!=expected[3]||
                output[4]!=expected[4]||
                output[5]!=expected[5]||
                output[6]!=expected[6]||
                output[7]!=expected[7]) throw new AssertionError();

    }
}
