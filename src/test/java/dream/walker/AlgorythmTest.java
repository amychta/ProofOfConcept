package dream.walker;

import org.apache.camel.json.simple.Jsoner;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

public class AlgorythmTest {

    @Test
    public void someTest() {
        Node tree = createTree();
        System.out.println(Jsoner.prettyPrint(tree.toString()));

        Set<String> realIncludedTopics = Algorythm.includedTopics(tree, createExcludedTopics());
        Set<String> expectedTopics = getExpectedTopics();
        assertThat(realIncludedTopics, CoreMatchers.is(expectedTopics));
        System.out.println(realIncludedTopics);
    }

    public static Set<String> createExcludedTopics() {
        return new HashSet<>(Arrays.asList("Obolonsky", "Alaska", "DC", "Warsaw", "Kyiv", "Washington"));
    }

    private HashSet<String> getExpectedTopics() {
        return new HashSet<>(Arrays.asList("Poltava", "Lviv", "Katowice"));
    }

    public static Node createTree() {
        Node root = new Node("World");

        Node usa = new Node("USA");
        Node ukraine = new Node("Ukraine");
        Node poland = new Node("Poland");
        root.childrens.addAll(Arrays.asList(usa, ukraine, poland));


        Node alaska = new Node("Alaska");
        Node washington = new Node("Washington");
        Node dc = new Node("DC");
        usa.childrens.addAll(Arrays.asList(alaska, washington, dc));

        Node kyiv = new Node("Kyiv");
        Node lviv = new Node("Lviv");
        Node poltava = new Node("Poltava");
        ukraine.childrens.addAll(Arrays.asList(kyiv, lviv, poltava));

        Node warsaw = new Node("Warsaw");
        Node katowice = new Node("Katowice");
        poland.childrens.addAll(Arrays.asList(warsaw, katowice));

        Node obolonsky = new Node("Obolonsky");
        Node golosiivsky = new Node("Golosiivsky");
        kyiv.childrens.addAll(Arrays.asList(obolonsky, golosiivsky));

        return root;
    }


}
