import java.util.*;

public class Algorithm implements Solver {
    private boolean isSolvable;
    private String start;
    private String goal;
    private List<String> path;
    private Map<String, Boolean> englishWordsMap;
    private Integer totalVisitedNodes;
    private Double timeExec;

    public void setSolveStatus(boolean status){
        this.isSolvable = status;
    }

    public void setPath(List<String> path){
        this.path = path;
    }

    public void setEnglishWordsMap(Map<String, Boolean> englishWordsMap){
        this.englishWordsMap = englishWordsMap;
    }

    public void setStart(String start){
        this.start = start;
    }

    public void setGoal(String goal){
        this.goal = goal;
    }

    public void setTotalVisitedNodes(int total){
        this.totalVisitedNodes = total;
    }

    public void setTimeExec(Double time){
        this.timeExec = time;
    }

    public void reversePath(){
        Collections.reverse(path);
    }

    public void addWord(String word){
        this.path.add(word);
    }

    public void clearPath(){
        this.path.clear();
    }

    public boolean isContainKey(String nextWord){
        return this.englishWordsMap.containsKey(nextWord);
    }

    @Override
    public void solve(String startWord, String goalWord) throws Exception {

    }

    public boolean getIsSolvable(){
        return this.isSolvable;
    }

    public List<String> getPath(){
        return this.path;
    }

    public String getStart(){
        return this.start;
    }

    public String getGoal(){
        return this.goal;
    }

    public Integer getTotalVisitedNodes(){
        return this.totalVisitedNodes;
    }

    public void incTotalVisNodes(){
        this.totalVisitedNodes++;
    }

    public Double getTimeExec(){
        return this.timeExec;
    }

    public Integer getSolutionLength(){
        int sz = this.getPath().size();

        for(int i = 0; i<sz; i++){
            System.out.println("Word"+(i+1)+": "+this.getPath().get(i));
        }
        return sz;
    }
}
