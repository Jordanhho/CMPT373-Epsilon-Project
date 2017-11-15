package export.latex.nodes.Table;

import export.latex.nodes.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Section {
    private List<Row> rows = new ArrayList<>();
    private List<List<SubColumn>> columns = new ArrayList<>();
    private Optional<Node> header = Optional.empty();

    private Section() {

    }

    public static Section section() {
        return new Section();
    }

    public Section addingColumn(SubColumn column, Integer index) {
        this.columns.get(index).add(column);
        return this;
    }





}
