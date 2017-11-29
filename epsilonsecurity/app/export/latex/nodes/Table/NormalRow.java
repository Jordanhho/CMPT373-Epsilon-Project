package export.latex.nodes.Table;

import export.latex.nodes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static export.latex.nodes.Command.command;
import static export.latex.nodes.LineBreak.lineBreak;
import static export.latex.nodes.RequiredArgument.of;
import static export.latex.nodes.SimpleNode.just;

public class NormalRow implements Row {
    private List<Cell> cells = new ArrayList<>();
    private Optional<Node> color = Optional.empty();

    private NormalRow() {

    }

    public NormalRow addingCells(Cell... cells) {
        this.cells.addAll(Arrays.asList(cells));
        return this;
    }

    public NormalRow addCell(Cell cell) {
        this.cells.add(cell);
        return this;
    }

    public NormalRow settingColor(Node color) {
        this.color = Optional.of(color);
        return this;
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        color.ifPresent(col -> {
            command("rowcolor")
                .addingRequiredArgument(of(col))
                .laTeXRepresentation(builder);
        });
        Integer count = cells.size();
        for (Integer index = 0; index < count; index++ ) {
            Cell cell = cells.get(index);
            cell.laTeXRepresentation(builder);
            if (index < (count - 1)) {
                MultiNode.of(cell,
                             just(" & "))
                    .laTeXRepresentation(builder);
            } else {
                MultiNode.of(cell,
                             lineBreak())
                    .laTeXRepresentation(builder);
            }
            Nodes.newLineOn(builder);
        }

    }
}
