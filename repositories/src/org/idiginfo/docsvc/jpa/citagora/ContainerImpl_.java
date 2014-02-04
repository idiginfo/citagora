package org.idiginfo.docsvc.jpa.citagora;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-02-03T15:09:43.375-0500")
@StaticMetamodel(ContainerImpl.class)
public class ContainerImpl_ extends CitagoraObjectImpl_ {
	public static volatile SingularAttribute<ContainerImpl, ReferenceImpl> isAbout;
	public static volatile ListAttribute<ContainerImpl, ReviewImpl> reviews;
	public static volatile ListAttribute<ContainerImpl, TagImpl> tags;
	public static volatile ListAttribute<ContainerImpl, CommentImpl> comments;
}
