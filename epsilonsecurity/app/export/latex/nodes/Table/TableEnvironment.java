package export.latex.nodes.Table;

import export.latex.nodes.Environment;
import export.latex.nodes.Node;

import static export.latex.nodes.RequiredArgument.of;
import static export.latex.nodes.SimpleNode.just;

public final class TableEnvironment implements Node {

    private Integer columnCount;
    private Environment table;

    private TableEnvironmentDataSource dataSource;

    public enum Width {
        COLUMN_WIDTH
    }

    TableEnvironment(Width width, Integer columnCount, String config) {
        this.columnCount = columnCount;
        this.table = Environment.begin("tabularx")
            .addingRequiredArgument(of(just(widthToString(width))))
            .addingRequiredArgument(of(just(config)));
    }

    public static TableEnvironment with(Width width, Integer columnCount, String config) {
        return new TableEnvironment(width, columnCount, config);
    }

    public TableEnvironment settingDataSource(TableEnvironmentDataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    private static String widthToString(Width width) {
        String result = "columnwidth";
        switch (width) {
            case COLUMN_WIDTH:
                result = "columnwidth";
        }
        return result;
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        table.laTeXRepresentation(builder);
    }
}
