package export.latex.nodes.Table;

import export.latex.nodes.Node;

import java.util.Optional;

public class Cell implements Node {
    private Optional<Node> color = Optional.empty();
    private Node content;

    private Cell(Node content) {
        this.content = content;
    }

    public Cell settingColor(Node color) {
        this.color = Optional.of(color);
        return this;
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        color.ifPresent( col -> col.laTeXRepresentation(builder));
        content.laTeXRepresentation(builder);
    }
}
