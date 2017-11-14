package export.latex.nodes;

public class RequiredArgument implements Node {
    private Node argument;

    private RequiredArgument(Node argument) {
        this.argument = argument;
    }

    public static RequiredArgument of(String argument) {
        return new RequiredArgument(SimpleNode.of(argument));
    }

    public static RequiredArgument of(Node argument) {
        return new RequiredArgument(argument);
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        builder.append('{');
        argument.laTeXRepresentation(builder);
        builder.append('}');
    }
}
