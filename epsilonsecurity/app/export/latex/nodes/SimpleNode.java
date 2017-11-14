package export.latex.nodes;

public class SimpleNode implements Node {
    private String text;

    private SimpleNode(String text) {
        this.text = text;
    }

    public static SimpleNode of(String text) {
        return new SimpleNode(text);
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        builder.append(text);
    }
}
