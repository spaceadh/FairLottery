import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FairLotteryTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void testValidInput() {
        // Set up your input data
        String input = "100,200,300\nAlice,Bob,Charlie\nexit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to the outContent stream
        System.setOut(new PrintStream(outContent));

        // Call the main method
        FairLottery.main(new String[0]);

        // Verify that the output is as expected
        String expectedOutput = "1. Alice:300\n2. Bob:200\n3. Charlie:100\n";
        assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void testInvalidInput() {
        // Set up your input data with non-integer prize values
        String input = "100,abc,300\nAlice,Bob,Charlie\nexit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to the outContent stream
        System.setOut(new PrintStream(outContent));

        // Call the main method
        FairLottery.main(new String[0]);

        // Verify that the error message is displayed
        assertTrue(getOutput().contains("Invalid input for prize values. Please enter integers only."));
    }

    @Test
    public void testAllocationOfPrizes() {
        // Set up your input data
        String input = "100,200,300\nAlice,Bob,Charlie\nexit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to the outContent stream
        System.setOut(new PrintStream(outContent));

        // Call the main method
        FairLottery.main(new String[0]);

        // Verify that the prizes are allocated fairly among the winners
        Map<String, List<Integer>> allocation = FairLottery.getAllocation();  // Modify to use appropriate method from FairLottery
        // Add your assertions here based on the expected allocation
    }

    @Test
    public void testEdgeCaseNoWinners() {
        // Set up your input data with no winners
        String input = "100,200,300\n\nexit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to the outContent stream
        System.setOut(new PrintStream(outContent));

        // Call the main method
        FairLottery.main(new String[0]);

        // Verify that the program handles no winners gracefully
        assertTrue(getOutput().contains("No winners provided."));
    }

    @Test
    public void testEdgeCaseNoPrizes() {
        // Set up your input data with no prizes
        String input = "\nAlice,Bob,Charlie\nexit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to the outContent stream
        System.setOut(new PrintStream(outContent));

        // Call the main method
        FairLottery.main(new String[0]);

        // Verify that the program handles no prizes gracefully
        assertTrue(getOutput().contains("No prizes provided."));
    }

    @Test
    public void testExcessWinners() {
        // Set up your input data with more winners than prizes
        String input = "100,200,300\nAlice,Bob,Charlie,Dave\nexit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Redirect standard output to the outContent stream
        System.setOut(new PrintStream(outContent));

        // Call the main method
        FairLottery.main(new String[0]);

        // Verify that the program handles excess winners gracefully
        assertTrue(getOutput().contains("Error: Number of winners cannot exceed the number of prizes."));
    }    

    // Helper method to capture the console output
    private String getOutput() {
        return outContent.toString();
    }
    // You can add more test cases as needed
}