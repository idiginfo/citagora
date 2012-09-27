package org.idiginfo.docsvc.jpa.citagora;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-09-26T14:41:56.622-0400")
@StaticMetamodel(CitagoraDocumentImpl.class)
public class CitagoraDocumentImpl_ extends CitagoraObjectImpl_ {
	public static volatile SingularAttribute<CitagoraDocumentImpl, ReferenceImpl> isAbout;
	public static volatile ListAttribute<CitagoraDocumentImpl, ReviewImpl> reviews;
	public static volatile ListAttribute<CitagoraDocumentImpl, TagImpl> tags;
	public static volatile ListAttribute<CitagoraDocumentImpl, CommentImpl> comments;
}
