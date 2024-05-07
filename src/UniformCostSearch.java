import java.util.*;
import java.util.PriorityQueue;

public class UniformCostSearch extends Algorithm{
    public UniformCostSearch(Map<String, Boolean> dictionary){
        setEnglishWordsMap(dictionary);
        setPath(new ArrayList<>());
        setSolveStatus(false);
        setAlphabets(Constant.ALPHABETS);
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


    // In this case, it happened with cost equal to step, so it most like similar as BFS
    public void solve(String startWord, String goalWord) throws Exception {
        this.setToDefault();
        this.setStart(startWord);
        this.setGoal(goalWord);
        long currTime = getTimeNow();
        if(startWord.length() != goalWord.length()){
            throw new Exception("Invalid Input (Length are not same)");
        }

        Map<String, String> parent = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startWord);

        while(!queue.isEmpty()){
            int sz = queue.size();
            for(int i = 0; i<sz; i++){
                String curr = queue.poll();
                this.incTotalVisNodes();

                if(Objects.equals(curr, goalWord)){
                    this.setSolveStatus(true);
                    this.traverseAndReversePath(parent, startWord, goalWord);
                    break;
                }

                assert curr != null;
                int subNodeSz = curr.length();
                for(int j = 0; j<subNodeSz; j++){
                    for (char alphabet : getAlphabets()) {
                        if(curr.charAt(j) == alphabet) continue;

                        String next = curr.substring(0, j) + alphabet +
                                curr.substring(j + 1);

                        if (isContainKey(next)
                                && !parent.containsKey(next)) {
                            parent.put(next,curr);
                            queue.add(next);
                        }
                    }
                }
            }

            if(this.getIsSolvable()){
                break;
            }
        }

        Double calTime = (getTimeNow()-currTime)/1000000.0;
        setTimeExec(calTime);
    }
}
