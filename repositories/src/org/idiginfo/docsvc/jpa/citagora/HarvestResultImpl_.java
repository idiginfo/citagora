package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-02-03T15:09:43.383-0500")
@StaticMetamodel(HarvestResultImpl.class)
public class HarvestResultImpl_ {
	public static volatile SingularAttribute<HarvestResultImpl, Integer> myId;
	public static volatile SingularAttribute<HarvestResultImpl, String> source;
	public static volatile SingularAttribute<HarvestResultImpl, Date> created;
	public static volatile SingularAttribute<HarvestResultImpl, Date> updated;
	public static volatile SingularAttribute<HarvestResultImpl, String> identifier;
	public static volatile SingularAttribute<HarvestResultImpl, ReferenceImpl> reference;
	public static volatile SingularAttribute<HarvestResultImpl, String> description;
	public static volatile SingularAttribute<HarvestResultImpl, Boolean> success;
	public static volatile SingularAttribute<HarvestResultImpl, String> uri;
}
