package export.latex;

import export.latex.nodes.DocumentBody;
import export.latex.nodes.Node;
import export.latex.nodes.Preamble;

public class Document implements Node {

    private Preamble preamble;
    private DocumentBody body;

    public void setPreamble(Preamble preamble) {
        this.preamble = preamble;
    }

    public void setBody(DocumentBody body) {
        this.body = body;
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        preamble.laTeXRepresentation(builder);
        body.laTeXRepresentation(builder);
    }
}
