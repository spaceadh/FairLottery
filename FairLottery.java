import java.util.*;

public class FairLottery {
    private static Map<String, List<Integer>> allocation;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Digital Vision Demo Lottery Prize Distribution System!");
        System.out.println("Please enter the prize values separated by commas:");
        String[] prizesInput = scanner.nextLine().split(",");

        int[] prizes;
        try {
            prizes = Arrays.stream(prizesInput).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for prize values. Please enter integers only.");
            return; // Exit the program gracefully
        }

        System.out.println("Now, please enter the names of the winners separated by commas:");
        String[] winners = scanner.nextLine().split(",");

        // Validate winner names
        if (!validateWinnerNames(winners)) {
            System.out.println("Invalid winner names. Winner names should not contain numbers.");
            return; // Exit the program gracefully
        }

        // // Check if the number of winners exceeds the number of prizes

        // if (winners.length > prizes.length) {
        //     System.out.println("Error: Number of winners cannot exceed the number of prizes.");
        //     return; // Exit the program gracefully
        // }

        //On second thought, the above is redudant, as they can be ignored.

        allocation = allocatePrizes(prizes, winners);

        
        System.out.println("Fair distribution of prizes:");
        // Print the allocation with correct index numbering
        for (int index = 1; index <= winners.length; index++) {
            String winner = winners[index - 1];
            List<Integer> allocatedPrizes = allocation.get(winner);
            int totalValue = allocatedPrizes.stream().mapToInt(Integer::intValue).sum();
            System.out.println(index + ". " + winner + ": " + String.join(",",
                    allocatedPrizes.stream().map(String::valueOf).toArray(String[]::new)) + " (Total: " + totalValue + ")");
        }
    }


    private static boolean validateWinnerNames(String[] winners) {
        for (String winner : winners) {
            if (winner.matches(".*\\d.*")) { // Check for digits in the name
                return false;
            }
        }
        return true;
    }

    private static Map<String, List<Integer>> allocatePrizes(int[] prizes, String[] winners) {
        // Initialize Hashmap for Prize Allocations.
        Map<String, List<Integer>> allocation = new HashMap<>();
        for (String winner : winners) {
            allocation.put(winner, new ArrayList<>());
        }

        // Sort prizes in descending order for fair distribution
        Arrays.sort(prizes);
        int n = winners.length;
        int[] totalValues = new int[n];

        // Distribute the prizes in a way to minimize the range of total values
        
        //LIFO APPROACH i.e This means that the last winner to receive a prize is the one with the least cumulative prize value so far. 
        for (int i = prizes.length - 1; i >= 0; i--) {
            int minIndex = findMinIndex(totalValues);
            allocation.get(winners[minIndex]).add(prizes[i]);
            totalValues[minIndex] += prizes[i];
        }

        //LILO APPROACH i.e where the first winner to receive a prize is the one with the least cumulative prize value
        // for (int i = 0; i < prizes.length; i++) { // Change loop direction to start from 0
        //     int minIndex = findMinIndex(totalValues);
        //     allocation.get(winners[minIndex]).add(prizes[i]);
        //     totalValues[minIndex] += prizes[i];
        // }

        // LILO is not suitable as there is a large variation, between the prizes awarded to each, while LIFO has a much smaller one 
        // Also, incase of excess winners, LILO, Will not handle such an edge case
        return allocation;
    }

    private static int findMinIndex(int[] array) {
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    // Getter method for allocation
    public static Map<String, List<Integer>> getAllocation() {
        return allocation;
    }
}
