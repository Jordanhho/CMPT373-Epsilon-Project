package export.latex.nodes;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Preamble implements Node {
    private List<Library> libraries = new ArrayList<>();
    private PaperSize paperSize = PaperSize.A4_PAPER;
    private Integer fontSize = 11;
    private DocumentType documentType = DocumentType.ARTICLE;
    private List<CommandDefinition> commandDefinitions = new ArrayList<>();

    enum PaperSize {
        A4_PAPER,
    }

    enum DocumentType {
        ARTICLE
    }

    public Preamble() {
    }

    public void setPaperSize(PaperSize size) {
        this.paperSize = size;
    }

    public void setFontSize(Integer size) {
        this.fontSize = size;
    }

    public void setDocumentType(DocumentType type) {
        this.documentType = documentType;
    }

    public void addLibrary(Library library) {
        libraries.add(library);
    }

    public void addCommandDefinition(CommandDefinition commandDefinition) {
        commandDefinitions.add(commandDefinition);
    }

    @Override
    public void laTeXRepresentation(StringBuilder builder) {
        Lists.newArrayList(
            Comment.of("!TEX encoding = UTF-8 Unicode"),
            Comment.of("Author: Epsilon Security Schedule Generator"),
            getDocumentClass(),
            Comment.of("Packages")
        )
            .forEach( node -> node.laTeXRepresentation(builder) );
        libraries.forEach(library -> library.laTeXRepresentation(builder));
        Comment.of("Macros");
    }

    public DocumentClass getDocumentClass() {
        return new DocumentClass(paperSize, fontSize, DocumentType.ARTICLE);
    }
}
