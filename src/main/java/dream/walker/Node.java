package dream.walker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {

    List<Node> childrens;

    String title;

    public Node(String title) {
        this.childrens = new ArrayList<>();
        this.title = title;
    }

    @Override
    public String toString() {
        String text = "{ \"" + title + "\": [";
        text += childrens.stream().map(e -> e.toString()).collect(Collectors.joining(", "));
        text += "] }";
        return text;
    }
}
