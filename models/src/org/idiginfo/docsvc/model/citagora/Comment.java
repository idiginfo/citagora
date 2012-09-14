package org.idiginfo.docsvc.model.citagora;

import java.util.List;

/**
 * Interface for Citagora Comment node
 * 
 * @author griccardi
 * 
 */
public interface Comment extends Annotation {
    // type is oaf:annotation

    // inherits TYPE and COLLECTION from Annotation
    // public static final String TYPE = "oaf:annotation";
    // static final String COLLECTION = "annotation";

    RatingType getRatingType();

    Person getReviewer();

    Integer getRating();

    List<Comment> getReplies();
}
