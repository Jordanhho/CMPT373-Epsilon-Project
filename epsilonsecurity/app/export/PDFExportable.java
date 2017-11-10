package export;

public interface PDFExportable {
    // TODO: do we want this to be String? or byte[]?
    String constructLaTeXRepresentation();
    String uniqueIdentifier();
}
