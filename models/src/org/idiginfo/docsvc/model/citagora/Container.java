package org.idiginfo.docsvc.model.citagora;

import java.util.List;

public interface Container extends CitagoraObject {
    static final String TYPE = "http://citagora.com/rdf#Container";
    static final String COLLECTION = "document";

    List<Review> getReviews();

    List<Tag> getTags();

    List<Comment> getComments();

    Reference getIsAbout();

    void setIsAbout(Reference reference);

    void addReview(Review review1);

    void addTag(Tag tag);

    void addComment(Comment comment);

}
