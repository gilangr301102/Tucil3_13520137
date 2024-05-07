import java.util.PriorityQueue;
import java.util.*;

public class AStar extends Algorithm {

    public AStar(Map<String, Boolean> dictionary) {
        setEnglishWordsMap(dictionary);
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
        addWord(startWord);
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

    public void solve(String startWord, String goalWord) throws Exception {
        setToDefault();
        setStart(startWord);
        setGoal(goalWord);
        long currTime = getTimeNow();
        if (startWord.length() != goalWord.length()) {
            throw new Exception("Invalid Input (Lengths are not the same)");
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(startWord, getHeuristicCostToGoal(startWord)));

        Map<String, Integer> dist = new HashMap<>();
        dist.put(startWord, 0);
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
                    if (curr.charAt(i) == alphabet) continue;
                    String next = curr.substring(0, i) + alphabet + curr.substring(i + 1);
                    if (isContainKey(next)) {
                        int heuristic = getHeuristicCostToGoal(next);
                        int prevCost = dist.getOrDefault(next, 0) + heuristic;
                        int currCost = dist.getOrDefault(curr, 0) + 1 + heuristic;
                        if (prevCost <= currCost && parent.containsKey(next)) {
                            continue;
                        }
                        parent.put(next, curr);
                        dist.put(next, dist.getOrDefault(curr, 0) + 1);
                        queue.add(new Node(next, dist.get(next) + heuristic)); // f(n) = g(n) + h(n)
                    }
                }
            }
        }

        double calTime = (getTimeNow() - currTime) / 1000000.0;
        setTimeExec(calTime);
    }
}
