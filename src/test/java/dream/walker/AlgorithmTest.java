package dream.walker;

import org.apache.camel.json.simple.Jsoner;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class AlgorithmTest {

    @Test
    public void someTestAlgorithm() {
        Node tree = createTree();
        System.out.println(" -- Original Tree: -- ");
        System.out.println(Jsoner.prettyPrint(tree.toString()));
        System.out.println(" -- Excluded topics: -- ");
        System.out.println(createExcludedTopics());

        Set<String> includedTopics = Algorithm.calculateOnlyIncludedTopics(tree, createExcludedTopics());

        Set<String> expectedTopics = getExpectedTopics();
        assertThat(includedTopics, CoreMatchers.is(expectedTopics));
        System.out.println(" -- Result: included leafs -- ");
        System.out.println(includedTopics);
    }

    public static Set<String> createExcludedTopics() {
        return new HashSet<>(Arrays.asList("Obolonsky", "Alaska", "DC", "Warsaw", "Kyiv"));
    }

    private Set<String> getExpectedTopics() {
        return new HashSet<>(Arrays.asList("Poltava", "Lviv", "Katowice", "Washington"));
    }

    public static Node createTree() {
        Node root = new Node("World");

        Node usa = new Node("USA");
        Node ukraine = new Node("Ukraine");
        Node poland = new Node("Poland");
        root.children.addAll(Arrays.asList(usa, ukraine, poland));

        Node alaska = new Node("Alaska");
        Node washington = new Node("Washington");
        Node dc = new Node("DC");
        usa.children.addAll(Arrays.asList(alaska, washington, dc));

        Node kyiv = new Node("Kyiv");
        Node lviv = new Node("Lviv");
        Node poltava = new Node("Poltava");
        ukraine.children.addAll(Arrays.asList(kyiv, lviv, poltava));

        Node warsaw = new Node("Warsaw");
        Node katowice = new Node("Katowice");
        poland.children.addAll(Arrays.asList(warsaw, katowice));

        Node obolonsky = new Node("Obolonsky");
        Node golosiivsky = new Node("Golosiivsky");
        kyiv.children.addAll(Arrays.asList(obolonsky, golosiivsky));

        return root;
    }


}
