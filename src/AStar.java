import java.util.*;
import java.util.PriorityQueue;


public class AStar extends Algorithm{
    public AStar(Map<String, Boolean> dictionary){
        setEnglishWordsMap(dictionary);
        setPath(new ArrayList<String>());
        setSolveStatus(false);
    }

    private int getHeuristicCostToGoal(String currWord){
        int ret = 0;
        int sz = currWord.length();
        for(int i = 0; i<sz; i++){
            if(currWord.charAt(i)!=getGoal().charAt(i)){
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

    private void setToDefault(){
        setTotalVisitedNodes(0);
        setSolveStatus(false);
        clearPath();
    }

    private long getTimeNow(){
        return System.nanoTime();
    }

    public void solve(String startWord, String goalWord) throws Exception {
        this.setToDefault();
        this.setStart(startWord);
        this.setGoal(goalWord);
        long currTime = getTimeNow();
        if(startWord.length() != goalWord.length()){
            throw new Exception("Invalid Input (Length are not same)");
        }

        this.setStart(startWord);
        this.setGoal(goalWord);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(startWord, this.getHeuristicCostToGoal(startWord)));

        Map<String, Integer> dist = new HashMap<>();
        dist.put(startWord, 0);
        Map<String, String> parent = new HashMap<>();
        char[] alphabets = Constant.ALPHABETS;
        while(!queue.isEmpty()){
            Node curr_node = queue.poll();
            this.incTotalVisNodes();

            if(curr_node.getWord().equals(goalWord)){
                setSolveStatus(true);
                break;
            }

            String curr = curr_node.getWord();
            int curr_len = curr.length();
            for(int i = 0; i<curr_len; i++){
                for (char alphabet : alphabets) {
                    if (curr.charAt(i) == alphabet) continue;
                    String next = curr.substring(0, i) + alphabet
                            + curr.substring(i + 1);
                    if (isContainKey(next)) {
                        int heuristic = this.getHeuristicCostToGoal(next);
                        int prevCost = ((dist.get(next) != null ? dist.get(next) : 0))
                                + heuristic;
                        int currCost = dist.get(curr) + 1 + heuristic;
                        if (prevCost <= currCost && parent.containsKey(next)) {
                            continue;
                        }
                        parent.put(next, curr);
                        dist.put(next, dist.get(curr) + 1);
                        queue.add(new Node(next, dist.get(next) + heuristic)); // f(n) = g(n) + h(n)
                    }
                }
            }
        }

        if(getIsSolvable()){
            this.traverseAndReversePath(parent, startWord, goalWord);
        }

        Double calTime = (getTimeNow()-currTime)/1000000.0;
        setTimeExec(calTime);
    }
}
