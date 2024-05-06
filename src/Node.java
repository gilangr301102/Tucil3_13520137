public class Node implements Comparable<Node>{
    private String word;
    private int cost;

    public Node(String word, int cost){
        this.word = word;
        this.cost = cost;
    }

    public String getWord() {
        return this.word;
    }

    public int getCost(){
        return this.cost;
    }

    @Override
    public int compareTo(Node other) {
        if (this.cost == other.getCost()) {
            return this.word.compareTo(other.getWord());
        } else {
            // Compare based on cost if costs are not equal
            return Integer.compare(this.cost, other.getCost());
        }
    }
}
