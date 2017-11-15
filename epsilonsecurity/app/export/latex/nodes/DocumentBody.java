package export.latex.nodes;

public class DocumentBody implements Node {

    Environment document = Environment.begin("document");


    public DocumentBody() {

    }

    public DocumentBody addingChild(Node subnode) {
        document.addingChild(subnode);
        return this;
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        Comment.of("Begin Document")
                .laTeXRepresentation(builder);
        document.laTeXRepresentation(builder);
    }
}
