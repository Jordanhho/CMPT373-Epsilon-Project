package export.latex.nodes.Table;

import export.latex.nodes.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubColumn {
    private Optional<Node> header = Optional.empty();
    private List<Node> nodes = new ArrayList<>();

    private SubColumn() {

    }

    public static SubColumn column() {
        return new SubColumn();
    }

    public SubColumn settingHeader(Node header) {
        this.header = Optional.of(header);
        return this;
    }

    public SubColumn addingVericalNodes(List<Node> nodes) {
        this.nodes.addAll(nodes);
        return this;
    }

    public List<Node> getVerticalNodes() {
        return this.nodes;
    }

    public Optional<Node> getHeader() {
        return this.header;
    }


}
