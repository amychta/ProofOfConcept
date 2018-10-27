package dream.walker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Algorythm {

    private Set<String> excludedTopics;

    private Set<String> globalIncludedTopics = new HashSet<>();

    Algorythm(Set<String> excludedTopics) {
        this.excludedTopics = excludedTopics;
    }

    public static Set<String> includedTopics(Node tree, Set<String> excludedTopics) {
        Algorythm algorythm = new Algorythm(excludedTopics);
        algorythm.allChildrenNodesIncluded(tree.childrens, tree.title);
        return algorythm.getAllIncludedTopics();
    }

    private boolean allChildrenNodesIncluded(List<Node> nodes, String currentTitle) {

        Set<String> directChildrenIncluded = new HashSet<>();

        if (nodes.isEmpty()) {
            if (includedTopic(currentTitle)) {
                globalIncludedTopics.add(currentTitle);
            }
            return true;
        }

        for (Node node : nodes) {
            if (includedTopic(node.title) && allChildrenNodesIncluded(node.childrens, node.title)) {
                directChildrenIncluded.add(node.title);
            }
        }

        if (directChildrenIncluded.size() == nodes.size()) {
            return true;                                // all child was included. No need to add them. Only add parent
        } else {
            return false;                               // not all children was included. So this current node does not included
        }
    }

    public Set<String> getAllIncludedTopics() {
        return globalIncludedTopics;
    }

    private boolean includedTopic(String title) {
        return !excludedTopics.contains(title);
    }
}
