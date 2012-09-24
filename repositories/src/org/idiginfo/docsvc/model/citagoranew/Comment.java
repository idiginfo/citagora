package org.idiginfo.docsvc.model.citagoranew;

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

    String getRatingType();

    Person getReviewer();

    Integer getRating();

    List<Reply> getReplies();
}
