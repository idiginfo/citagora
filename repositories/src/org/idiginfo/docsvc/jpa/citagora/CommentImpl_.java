package org.idiginfo.docsvc.jpa.citagora;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-20T08:22:23.376-0500")
@StaticMetamodel(CommentImpl.class)
public class CommentImpl_ extends AnnotationImpl_ {
	public static volatile SingularAttribute<CommentImpl, String> commentType;
	public static volatile SingularAttribute<CommentImpl, PersonImpl> reviewer;
	public static volatile SingularAttribute<CommentImpl, Integer> rating;
	public static volatile ListAttribute<CommentImpl, ReplyImpl> replies;
	public static volatile SingularAttribute<CommentImpl, ContainerImpl> target;
}
