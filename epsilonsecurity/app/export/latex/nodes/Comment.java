package export.latex.nodes;

public class Comment implements Node {
    String comment;

    private Comment(String comment) {
        this.comment = comment;
    }

    public static Comment of(String comment) {
        return new Comment(comment);
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        builder.append("% ");
        builder.append(comment);
        Nodes.newLineOn(builder);
    }
}
