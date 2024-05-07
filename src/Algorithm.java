import java.util.*;

public class Algorithm implements Solver {
    private boolean isSolvable;
    private String start;
    private String goal;
    private List<String> path;
    private Map<String, Boolean> dictionary;
    private Integer totalVisitedNodes;
    private Double timeExec;
    private char[] alphabets;

    @Override
    public void setSolveStatus(boolean status){
        this.isSolvable = status;
    }

    @Override
    public void setPath(List<String> path){
        this.path = path;
    }

    @Override
    public void setDictionary(Map<String, Boolean> dictionary){
        this.dictionary = dictionary;
    }

    @Override
    public void setStart(String start){
        this.start = start;
    }

    @Override
    public void setGoal(String goal){
        this.goal = goal;
    }

    @Override
    public void setTotalVisitedNodes(int total){
        this.totalVisitedNodes = total;
    }

    @Override
    public void setTimeExec(Double time){
        this.timeExec = time;
    }

    @Override
    public void reversePath(){
        Collections.reverse(path);
    }

    @Override
    public void addWord(String word){
        this.path.add(word);
    }

    @Override
    public void clearPath(){
        this.path.clear();
    }

    @Override
    public void setAlphabets(char[] alphabets){this.alphabets = alphabets;}

    @Override
    public boolean isContainKey(String nextWord){
        return this.dictionary.containsKey(nextWord);
    }

    @Override
    public void solve(String startWord, String goalWord){

    }

    @Override
    public boolean getIsSolvable(){
        return this.isSolvable;
    }

    @Override
    public char[] getAlphabets(){return this.alphabets;}

    @Override
    public List<String> getPath(){
        return this.path;
    }

    @Override
    public String getStart(){
        return this.start;
    }

    @Override
    public String getGoal(){
        return this.goal;
    }

    @Override
    public Integer getTotalVisitedNodes(){
        return this.totalVisitedNodes;
    }

    public void incTotalVisNodes(){
        this.totalVisitedNodes++;
    }

    @Override
    public Double getTimeExec(){
        return this.timeExec;
    }

    @Override
    public Integer getSolutionLength(){
        int sz = this.getPath().size();

        for(int i = 0; i<sz; i++){
            System.out.println("Word"+(i+1)+": "+this.getPath().get(i));
        }
        return sz;
    }
}
