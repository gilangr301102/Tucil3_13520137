import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class GreedyBestFirstSearch extends Algorithm{
    public GreedyBestFirstSearch(Map<String, Boolean> dictionary){
        setEnglishWordsMap(dictionary);
        setPath(new ArrayList<String>());
        setSolveStatus(false);
    }
    public void solve(String startWord, String goalWord) {
        System.out.println("debug");
    }
}
