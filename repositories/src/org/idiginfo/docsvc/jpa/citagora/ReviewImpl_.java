package org.idiginfo.docsvc.jpa.citagora;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-02-03T15:09:43.430-0500")
@StaticMetamodel(ReviewImpl.class)
public class ReviewImpl_ extends CitagoraObjectImpl_ {
	public static volatile SingularAttribute<ReviewImpl, String> ratingType;
	public static volatile SingularAttribute<ReviewImpl, Integer> rating;
	public static volatile SingularAttribute<ReviewImpl, Integer> totalVotes;
	public static volatile SingularAttribute<ReviewImpl, PersonImpl> reviewer;
	public static volatile SingularAttribute<ReviewImpl, ContainerImpl> documentReviewed;
}
