package org.idiginfo.docsvc.jpa.citagora;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-09-19T19:42:05.740-0400")
@StaticMetamodel(ReviewImpl.class)
public class ReviewImpl_ extends CitagoraObjectImpl_ {
	public static volatile SingularAttribute<ReviewImpl, String> ratingType;
	public static volatile SingularAttribute<ReviewImpl, Integer> rating;
	public static volatile SingularAttribute<ReviewImpl, Integer> totalVotes;
	public static volatile SingularAttribute<ReviewImpl, PersonImpl> reviewer;
	public static volatile SingularAttribute<ReviewImpl, CitagoraDocumentImpl> documentReviwed;
}
