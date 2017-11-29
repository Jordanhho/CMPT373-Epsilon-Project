package export.latex.nodes;

public final class SimpleNode implements Node {
    private String text;

    private SimpleNode(String text) {
        this.text = text;
    }

    public static SimpleNode just(String text) {
        return new SimpleNode(text);
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        builder.append(text);
    }
}
