public class Node implements Comparable<Node> {
    private String word;
    private int cost;

    public Node(String word, int cost) {
        this.word = word;
        this.cost = cost;
    }

    public String getWord() {
        return word;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Node other) {
        int costComparison = Integer.compare(cost, other.getCost());
        return costComparison != 0 ? costComparison : word.compareTo(other.getWord());
    }
}
