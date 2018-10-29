package dream.walker;

import java.util.HashSet;
import java.util.Set;

public class Algorithm {

    public static Set<String> calculateOnlyIncludedTopics(Node root, Set<String> excludedTopics) {

        Set<String>  includedLeafs = new HashSet<>();
        Node newNode = buildCopyTreeWithoutExcludedNodes(root, excludedTopics);
        findAllLeafs(newNode, includedLeafs);
        return includedLeafs;
    }

    /**
     * Copy existing tree to the new one that does not contains excluded topics
     */
    private static Node buildCopyTreeWithoutExcludedNodes(Node originalParentNode, Set<String> excludedTopics) {

        Node newParentNode = new Node(originalParentNode.title);

        for (Node originalChild : originalParentNode.children) {
            if (notExcludedTopic(originalChild.title, excludedTopics)) {
                Node newChild = buildCopyTreeWithoutExcludedNodes(originalChild, excludedTopics);
                newParentNode.children.add(newChild);
            }
        }
        return newParentNode;
    }

    /**
     * Find only leaf's in result tree of included topics
     */
    private static void findAllLeafs(Node node, Set<String> allIncludedLeafs) {
        if (node.children.isEmpty()) {
            allIncludedLeafs.add(node.title);
        } else {
            for (Node children : node.children) {
                findAllLeafs(children, allIncludedLeafs);
            }
        }
    }

    /**
     * Check that topic is not marked as excluded
     */
    private static boolean notExcludedTopic(String topicTitle, Set<String> excludedTopics) {
        return !excludedTopics.contains(topicTitle);
    }
}
