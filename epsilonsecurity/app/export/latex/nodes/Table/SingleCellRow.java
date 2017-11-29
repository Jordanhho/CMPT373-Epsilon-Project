package export.latex.nodes.Table;

import export.latex.nodes.Node;

import java.util.Optional;

import static export.latex.nodes.Command.command;
import static export.latex.nodes.RequiredArgument.of;

public class SingleCellRow implements Row {
    private Cell cell;
    private Optional<Node> color = Optional.empty();

    private SingleCellRow(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        color.ifPresent(col -> {
            command("rowcolor")
                .addingRequiredArgument(of(col))
                .laTeXRepresentation(builder);
        });
        cell.laTeXRepresentation(builder);
    }
}
