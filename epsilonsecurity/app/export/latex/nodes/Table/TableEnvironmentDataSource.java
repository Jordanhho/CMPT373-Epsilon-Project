package export.latex.nodes.Table;

import export.latex.nodes.Environment;

import java.util.ArrayList;
import java.util.List;

public final class TableEnvironmentDataSource {

    private Integer columnCount;
    private Environment table;

    private List<Section> sections = new ArrayList<>();

    TableEnvironmentDataSource(Integer columnCount, Environment table) {
        this.columnCount = columnCount;
        this.table = table;
    }

}
