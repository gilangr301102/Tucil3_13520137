import java.util.List;
import java.util.Map;

public interface Solver {
    public void setSolveStatus(boolean status);
    public void setPath(List<String> path);
    public void setEnglishWordsMap(Map<String, Boolean> englishWordsMap);
    public void setStart(String start);
    public void setGoal(String goal);
    public void setTotalVisitedNodes(int total);
    public void setTimeExec(Double time);
    public void reversePath();
    public void addWord(String word);
    public void clearPath();
    public boolean getIsSolvable();
    public List<String> getPath();
    public String getStart();
    public String getGoal();
    public Integer getTotalVisitedNodes();
    public Integer getSolutionLength();
    public Double getTimeExec();
    public boolean isContainKey(String nextWord);
    void solve(String startWord, String goalWord)  throws Exception;
}
