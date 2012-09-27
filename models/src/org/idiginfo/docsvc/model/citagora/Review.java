package org.idiginfo.docsvc.model.citagora;

public interface Review extends CitagoraObject {
    static final String TYPE = "http://purl.org/stuff/rev#Review";
    static final String COLLECTION = "review";

    String getRatingType();

    void setRatingType(String uri);

    Person getReviewer();

    void setReviewer(Person person);

    Integer getRating();

    void setRating(int i);

    CitagoraDocument getDocumentReviewed();

    void setDocumentReviewed(CitagoraDocument document);

    Integer getTotalVotes();

    void setTotalVotes(int i);

}
