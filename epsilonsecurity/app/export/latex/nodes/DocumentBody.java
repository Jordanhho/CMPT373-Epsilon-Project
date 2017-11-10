package export.latex.nodes;

import java.util.function.Consumer;

public class DocumentBody implements Node {

    Environment document = Environment.begin("document");


    public DocumentBody() {

    }

    public DocumentBody addingChild(Consumer<StringBuilder> childClosure) {
        document.addingChild(childClosure);
        return this;
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        Comment.of("Begin Document")
                .laTeXRepresentation(builder);
        document.laTeXRepresentation(builder);
    }
}
