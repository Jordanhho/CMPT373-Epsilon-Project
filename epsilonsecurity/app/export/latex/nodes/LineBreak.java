package export.latex.nodes;

import static export.latex.nodes.SimpleNode.just;

public class LineBreak implements Node {

    private LineBreak() {

    }

    public static LineBreak lineBreak() {
        return new LineBreak();
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        just(" \\").laTeXRepresentation(builder);
        Nodes.newLineOn(builder);
    }
}
