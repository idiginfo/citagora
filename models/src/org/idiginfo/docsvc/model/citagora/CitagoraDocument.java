package org.idiginfo.docsvc.model.citagora;

import java.util.List;

public interface CitagoraDocument extends CitagoraObject {
    static final String TYPE = "http://citagora.com/rdf#Document";
    static final String COLLECTION = "document";

    List<Review> getReviews();

    void addReview(Review review);

    List<Tag> getTags();

    void addTag(Tag tag);

    List<Comment> getComments();

    void addComment(Comment comment);

    Reference getIsAbout();

    void setIsAbout(Reference reference);

}
