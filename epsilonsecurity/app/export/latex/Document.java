package export.latex;

import export.latex.nodes.Preamble;

public class Document {

    private Preamble preamble;
    private String body = "";

    public void setPreamble(Preamble preamble) {
        this.preamble = preamble;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
