package org.idiginfo.docsvc.model.citagoranew;

import java.util.List;

import javax.xml.stream.events.Comment;

public interface CitagoraDocument extends CitagoraObject {
    static final String TYPE = "http://citagora.com/rdf#Document";
    static final String COLLECTION = "document";

    List<Review> getRatings();

    List<Tag> getTags();

    List<Comment> getComments();

    Reference getIsAbout();

    List<Review> getReviews();

    void addComment(Comment comment);

    void addTag(Tag tag);
}
