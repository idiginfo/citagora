package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-09-21T10:50:34.776-0400")
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
	public static volatile ListAttribute<PersonImpl, CitagoraDocumentImpl> agentDocuments;
	public static volatile ListAttribute<PersonImpl, ReferenceImpl> agentReferences;
}
