package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-11-01T11:35:41.297-0400")
@StaticMetamodel(ReferenceImpl.class)
public class ReferenceImpl_ extends CitagoraObjectImpl_ {
	public static volatile SingularAttribute<ReferenceImpl, String> abstractText;
	public static volatile SingularAttribute<ReferenceImpl, String> title;
	public static volatile SingularAttribute<ReferenceImpl, String> subject;
	public static volatile SingularAttribute<ReferenceImpl, String> language;
	public static volatile SingularAttribute<ReferenceImpl, Integer> pageStart;
	public static volatile SingularAttribute<ReferenceImpl, Integer> pageEnd;
	public static volatile SingularAttribute<ReferenceImpl, String> volume;
	public static volatile SingularAttribute<ReferenceImpl, String> biboType;
	public static volatile SingularAttribute<ReferenceImpl, Date> issued;
	public static volatile SingularAttribute<ReferenceImpl, String> pmid;
	public static volatile SingularAttribute<ReferenceImpl, String> doi;
	public static volatile SingularAttribute<ReferenceImpl, String> authorString;
	public static volatile SingularAttribute<ReferenceImpl, String> issn;
	public static volatile SingularAttribute<ReferenceImpl, String> isbn;
	public static volatile SingularAttribute<ReferenceImpl, String> issue;
	public static volatile SingularAttribute<ReferenceImpl, ReferenceImpl> isPartOf;
	public static volatile ListAttribute<ReferenceImpl, ReferenceImpl> contains;
	public static volatile ListAttribute<ReferenceImpl, ContainerImpl> containers;
	public static volatile ListAttribute<ReferenceImpl, PersonImpl> authorList;
	public static volatile ListAttribute<ReferenceImpl, ReferenceImpl> isCitedBy;
	public static volatile ListAttribute<ReferenceImpl, ReferenceImpl> citationList;
	public static volatile SingularAttribute<ReferenceImpl, PersonImpl> contributedBy;
	public static volatile SingularAttribute<ReferenceImpl, String> shortTitle;
	public static volatile SingularAttribute<ReferenceImpl, String> publisher;
	public static volatile SingularAttribute<ReferenceImpl, String> pages;
	public static volatile SingularAttribute<ReferenceImpl, Double> readabilityRating;
	public static volatile SingularAttribute<ReferenceImpl, Double> overallRating;
	public static volatile SingularAttribute<ReferenceImpl, Double> accuracyRating;
	public static volatile SingularAttribute<ReferenceImpl, Double> originalityRating;
}
