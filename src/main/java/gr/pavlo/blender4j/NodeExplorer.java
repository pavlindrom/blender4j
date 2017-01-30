package gr.pavlo.blender4j;

import com.github.javaparser.ast.Node;

/**
 * Created by Pavlo on 1/29/2017.
 * Copied from https://github.com/ftomassetti/analyze-java-code-examples/blob/master/src/main/java/me/tomassetti/support/NodeIterator.java
 */
public class NodeExplorer {
    public interface NodeHandler {
        boolean handle(Node node);
    }

    private NodeHandler nodeHandler;

    public NodeExplorer(NodeHandler nodeHandler) {
        this.nodeHandler = nodeHandler;
    }

    public void explore(Node node) {
        if (nodeHandler.handle(node)) {
            for (Node child : node.getChildNodes()) {
                explore(child);
            }
        }
    }
}
