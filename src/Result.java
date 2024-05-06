import java.util.List;

public class Result {
    private List<String> path;
    private int level;
    private int num_generated_nodes;

    public Result(List<String> path, int level, int num_generated_nodes){
        this.path = path;
        this.level = level;
        this.num_generated_nodes = num_generated_nodes;
    }

    public List<String> getPath() {
        return this.path;
    }

    public int getLevel(){
        return this.level;
    }

    public int getNumGeneratedNodes() {
        return this.num_generated_nodes;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setPath(List<String> path){
        this.path = path;
    }

    public void setNumGeneratedNodes(int num_generated_nodes){
        this.num_generated_nodes = num_generated_nodes;
    }
}
