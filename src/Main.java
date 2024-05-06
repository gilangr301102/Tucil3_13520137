import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String startWord = input.nextLine();
        String goalWord = input.nextLine();
        int type = input.nextInt();

        // Read input file txt
        Map<String, Boolean> dictionary = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/test/dictionary.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    dictionary.put(line, true);
                }
            }
            Solver word_ladder_solver = switch (type) {
                case 1 -> new UniformCostSearch(dictionary);
                case 2 -> new GreedyBestFirstSearch(dictionary);
                default -> {
                    assert type == 3;
                    yield new AStar(dictionary);
                }
            };
            word_ladder_solver.solve(startWord, goalWord);
            int solLength = word_ladder_solver.getSolutionLength();
            if(solLength==0){
                System.out.println("No Solution Found!");
            }else{
                System.out.println("Solution length: " + solLength);
                System.out.println("Total visited nodes: " + word_ladder_solver.getTotalVisitedNodes());
            }
            System.out.println("Time Execution: " + word_ladder_solver.getTimeExec() + " miliseconds.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Invalid input, Fail to execute the program!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}