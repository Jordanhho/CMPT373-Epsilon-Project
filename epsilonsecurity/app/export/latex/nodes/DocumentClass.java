package export.latex.nodes;

public class DocumentClass implements Node {
    private Preamble.PaperSize paperSize;
    private Integer fontSize;
    private Preamble.DocumentType type;

    public DocumentClass(Preamble.PaperSize paperSize, Integer fontSize, Preamble.DocumentType type) {
        this.paperSize = paperSize;
        this.fontSize = fontSize;
        this.type = type;
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        builder.append("\\documentclass");
        getPaperAndSizeArgument().laTeXRepresentation(builder);
        getDocumentTypeArgument().laTeXRepresentation(builder);
        Nodes.newLineOn(builder);
    }

    private static String paperSizeToString(Preamble.PaperSize paperSize) {
        String string = "a4paper";
        switch (paperSize) {
            case A4_PAPER:
                string = "a4paper";
        }
        return string;
    }

    private static String fontSizeToString(Integer fontSize) {
        return fontSize.toString() + "pt";
    }

    private static String documentTypeToString(Preamble.DocumentType type) {
        String string = "article";
        switch (type) {
            case ARTICLE:
                string = "article";
        }
        return string;
    }

    private OptionalArgument getPaperAndSizeArgument() {
        // These commas almost never occur in regular LaTeX so they won't matter
        return OptionalArgument.of(
            paperSizeToString(paperSize)
                + ","
                + fontSizeToString(fontSize)
        );
    }

    private RequiredArgument getDocumentTypeArgument() {
        return RequiredArgument.of(documentTypeToString(type));
    }

}
