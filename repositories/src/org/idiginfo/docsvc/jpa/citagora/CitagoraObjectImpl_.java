package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-09-21T16:06:38.637-0400")
@StaticMetamodel(CitagoraObjectImpl.class)
public class CitagoraObjectImpl_ {
	public static volatile SingularAttribute<CitagoraObjectImpl, Integer> myId;
	public static volatile SingularAttribute<CitagoraObjectImpl, String> type;
	public static volatile SingularAttribute<CitagoraObjectImpl, String> uri;
	public static volatile SingularAttribute<CitagoraObjectImpl, String> wasAttributedTo;
	public static volatile SingularAttribute<CitagoraObjectImpl, Date> created;
	public static volatile SingularAttribute<CitagoraObjectImpl, Date> updated;
	public static volatile SingularAttribute<CitagoraObjectImpl, String> source;
	public static volatile SingularAttribute<CitagoraObjectImpl, String> rights;
	public static volatile SingularAttribute<CitagoraObjectImpl, PersonImpl> generator;
	public static volatile SingularAttribute<CitagoraObjectImpl, Date> generated;
}
