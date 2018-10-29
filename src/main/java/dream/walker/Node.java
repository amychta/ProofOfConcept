package dream.walker;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Node {

    Set<Node> children;

    String title;

    public Node(String title) {
        this.children = new HashSet<>();
        this.title = title;
    }

    @Override
    public String toString() {

        String value;

        if (children.isEmpty())
            value = "null";
        else
            value = "[" + children.stream().map(Node::toString).collect(Collectors.joining(", ")) + "]";

        return new StringBuilder()
            .append("{ \"")
            .append(title)
            .append("\": ")
            .append(value)
            .append("}")
            .toString();
    }
}
