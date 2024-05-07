import java.util.*;
import java.util.PriorityQueue;

public class GreedyBestFirstSearch extends Algorithm {

    public GreedyBestFirstSearch(Map<String, Boolean> dictionary) {
        setDictionary(dictionary);
        setPath(new ArrayList<>());
        setSolveStatus(false);
        setAlphabets(Constant.ALPHABETS);
    }

    private int getHeuristicCostToGoal(String currWord) {
        int ret = 0;
        String goalWord = getGoal();
        int sz = currWord.length();
        for (int i = 0; i < sz; i++) {
            if (currWord.charAt(i) != goalWord.charAt(i)) {
                ret++;
            }
        }
        return ret;
    }

    private void traverseAndReversePath(Map<String, String> parent, String startWord, String goalWord) {
        String currWord = goalWord;
        while (!Objects.equals(currWord, startWord)) {
            addWord(currWord);
            currWord = parent.get(currWord);
        }
        addWord(currWord);
        reversePath();
    }

    private void setToDefault() {
        setTotalVisitedNodes(0);
        setSolveStatus(false);
        clearPath();
    }

    private long getTimeNow() {
        return System.nanoTime();
    }

    @Override
    public void solve(String startWord, String goalWord) {
        setToDefault();
        setStart(startWord);
        setGoal(goalWord);
        long currTime = getTimeNow();

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(startWord, getHeuristicCostToGoal(startWord)));

        Map<String, String> parent = new HashMap<>();
        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            incTotalVisNodes();

            if (currNode.getWord().equals(goalWord)) {
                setSolveStatus(true);
                traverseAndReversePath(parent, startWord, goalWord);
                break;
            }

            String curr = currNode.getWord();
            int currLen = curr.length();
            for (int i = 0; i < currLen; i++) {
                for (char alphabet : getAlphabets()) {
                    String next = curr.substring(0, i) + alphabet + curr.substring(i + 1);
                    if (isContainKey(next) && !parent.containsKey(next)) {
                        parent.put(next, curr);
                        int heuristic = getHeuristicCostToGoal(next);
                        queue.add(new Node(next, heuristic));
                    }
                }
            }
        }

        double calTime = (getTimeNow() - currTime) / 1000000.0;
        setTimeExec(calTime);
    }
}
