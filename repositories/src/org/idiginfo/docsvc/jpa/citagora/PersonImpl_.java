package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-02-07T16:36:11.913-0500")
@StaticMetamodel(PersonImpl.class)
public class PersonImpl_ {
	public static volatile SingularAttribute<PersonImpl, Integer> myId;
	public static volatile SingularAttribute<PersonImpl, String> type;
	public static volatile SingularAttribute<PersonImpl, String> uri;
	public static volatile SingularAttribute<PersonImpl, String> givenName;
	public static volatile SingularAttribute<PersonImpl, String> familyName;
	public static volatile SingularAttribute<PersonImpl, String> name;
	public static volatile SingularAttribute<PersonImpl, String> accountName;
	public static volatile SingularAttribute<PersonImpl, String> account;
	public static volatile SingularAttribute<PersonImpl, String> homePage;
	public static volatile SingularAttribute<PersonImpl, Date> created;
	public static volatile SingularAttribute<PersonImpl, Date> updated;
	public static volatile SingularAttribute<PersonImpl, Boolean> isAuthor;
	public static volatile SingularAttribute<PersonImpl, Boolean> isAgent;
	public static volatile SingularAttribute<PersonImpl, Boolean> isPerson;
	public static volatile ListAttribute<PersonImpl, ReferenceImpl> authorReferences;
	public static volatile ListAttribute<PersonImpl, CitagoraObjectImpl> agentDocuments;
	public static volatile ListAttribute<PersonImpl, CommentImpl> agentComments;
	public static volatile ListAttribute<PersonImpl, TagImpl> agentTags;
	public static volatile ListAttribute<PersonImpl, ReferenceImpl> agentReferences;
	public static volatile ListAttribute<PersonImpl, ReviewImpl> agentReviews;
}
