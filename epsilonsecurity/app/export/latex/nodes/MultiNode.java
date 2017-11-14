package export.latex.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiNode implements Node {
    private List<Node> subnodes = new ArrayList<>();

    private Boolean isOnSeparateLines = true;

    private MultiNode(List<Node> subnodes) {
        this.subnodes = subnodes;
    }

    public static MultiNode of(Node... subnodes) {
        return new MultiNode(Arrays.asList(subnodes));
    }

    public MultiNode adding(Node node) {
        this.subnodes.add(node);
        return this;
    }

    public MultiNode setOneLine() {
        isOnSeparateLines = false;
        return this;
    }

    public MultiNode setSeparateLines() {
        isOnSeparateLines = true;
        return this;
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        subnodes.forEach( node -> {
            node.laTeXRepresentation(builder);
            if (isOnSeparateLines) {
                Nodes.newLineOn(builder);
            }
        });
    }
}
