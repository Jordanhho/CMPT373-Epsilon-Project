package export;

import com.google.common.collect.Lists;
import export.latex.Document;
import export.latex.nodes.DocumentBody;
import export.latex.nodes.Library;
import export.latex.nodes.OptionalArgument;
import export.latex.nodes.Preamble;
import models.schedule.Schedule;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class PDFExportableSchedule implements PDFExportable {

    private Instant initTime;
    private Schedule schedule;

    private Optional<Document> document;

    public PDFExportableSchedule(Schedule schedule) {
        this.initTime = Instant.now();
        this.schedule = schedule;
    }

    @Override
    public String uniqueIdentifier() {
        return initTime.toString();
    }

    @Override
    public StringBuilder constructLaTeXRepresentation() {
        // constructs document lazily
        StringBuilder builder = new StringBuilder();
        Document document = this.document.orElse(constructDocument());

        return null;
    }

    private Document constructDocument() {
        Document document = new Document();
        document.setPreamble(constructPreamble());
        document.setBody(constructDocumentBody());
        return document;
    }

    private Preamble constructPreamble() {
        // write pre-amble
        Preamble preamble = new Preamble();
        preamble.setFontSize(12);
        requiredLibraries()
                .forEach(preamble::addLibrary);
        // maybe color definitions depending on shifts? Maybe not
        return preamble;
    }

    private DocumentBody constructDocumentBody() {
        DocumentBody body = new DocumentBody();
        return body;
    }

    private static List<Library> requiredLibraries() {
        return Lists.newArrayList(
            Library.named("inputenc").addingOptional(OptionalArgument.of("utf8")),
            Library.named("tabularx"),
            Library.named("pdflscape"),
            Library.named("xcolor").addingOptional(OptionalArgument.of("dvipsnames")),
            Library.named("colortbl"),
            Library.named("geometry").addingOptional(OptionalArgument.of("margin=0.5in")),
            Library.named("ltablex")
        );
    }
}
