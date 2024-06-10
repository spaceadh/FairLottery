import java.util.*;

public class FairLottery {

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
        
        Map<String, List<Integer>> allocation = allocatePrizes(prizes, winners);
        
        System.out.println("Fair distribution of prizes:");
        // Print the allocation
        for (String winner : winners) {
            List<Integer> allocatedPrizes = allocation.get(winner);
            System.out.println(winner + ":" + String.join(",", 
                                 allocatedPrizes.stream().map(String::valueOf).toArray(String[]::new)));
        }
    }
    
    private static Map<String, List<Integer>> allocatePrizes(int[] prizes, String[] winners) {

        //Initialize Hashmap for Prize Allocations.
        Map<String, List<Integer>> allocation = new HashMap<>();
        for (String winner : winners) {
            allocation.put(winner, new ArrayList<>());
        }
        
        int n = winners.length;
        int[] totalValues = new int[n];
        
        // Distribute the prizes in a way to minimize the range of total values
        for (int i = prizes.length - 1; i >= 0; i--) {
            int minIndex = findMinIndex(totalValues);
            allocation.get(winners[minIndex]).add(prizes[i]);
            totalValues[minIndex] += prizes[i];
        }
        
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
}