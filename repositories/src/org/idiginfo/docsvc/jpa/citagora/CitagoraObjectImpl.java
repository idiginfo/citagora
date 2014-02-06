package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.apisvc.GsonTransient;

import com.google.gson.annotations.SerializedName;

/**
 * Class to implement the Citagora persistence CitagoraObject
 * 
 * @author griccardi
 * 
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "citagora_objects")
public class CitagoraObjectImpl implements CitagoraObject {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@GsonTransient
	Integer myId;
	@SerializedName("type")
	String type;
	@Column(unique = true)
	@SerializedName("uri")
	String uri;
	@SerializedName("attributedTo")
	String wasAttributedTo;
	@Temporal(TemporalType.TIMESTAMP)
	@SerializedName("created")
	Date created;
	@Temporal(TemporalType.TIMESTAMP)
	@SerializedName("updated")
	Date updated;
	@SerializedName("source")
	String source;
	@SerializedName("sourceId")
	String sourceId;
	@Column(length = 1000)
	@SerializedName("rights")
	String rights;
	@ManyToOne(targetEntity = PersonImpl.class, cascade = CascadeType.ALL)
	@GsonTransient
	CitagoraAgent generator;
	@Temporal(TemporalType.TIMESTAMP)
	@SerializedName("generated")
	Date generated;
	// TODO still need to decide whether there is a set of annotations, or only
	// tags and comments
	// This property will not work for existing implementation, as is
	// @OneToMany(mappedBy = "target", targetEntity = AnnotationImpl.class,
	// cascade = CascadeType.ALL)
	@Transient
	List<Annotation> annotations;

	// Non-persistent members
	@Transient
	transient static int objectId = 0;
	@Transient
	transient String myCollection;

	// @Transient
	// String id = null;

	/**
	 * Perform operations required before persisting an object myId is set
	 * before this method is called and so the URI can be created here
	 */
	@PrePersist
	protected void onCreate() {
		updated = new Date();
		// created may not be the database timestamp. It may be set by the
		// creator
		if (created == null)
			created = updated;
		getUri();
	}

	/**
	 * after the object has been inserted, update the uri field with the new
	 * value of myId
	 */
	@PostPersist
	protected void afterCreate() {
		// getUri();
	}

	/**
	 * Perform operations required before updating an object
	 */
	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}

	public String getCollecton() {
		return myCollection;
	}

	void setCollection(String collection) {
		myCollection = collection;
	}

	@Override
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static String makeId(String collection, int myId) {
		String id = CitagoraObject.NAMESPACE + collection + "/" + myId;
		return id;
	}

	@Override
	public String getUri() {
		if (uri == null && myId != null)
			return makeUri();
		return uri;
	}

	public String makeUri() {
		if (uri == null && myId != null) {
			uri = makeId(myCollection, myId);
		}
		return uri;
	}

	@Override
	public void setUri(String uri) {
		// check for doi
		if (uri != null) {
			if (uri.startsWith("10.")) {
				// uri is doi
				this.uri = "doi:" + uri;
			} else {
				this.uri = uri;
			}
		} else {
			makeUri();
		}
	}

	@Override
	public String getWasAttributedTo() {
		return wasAttributedTo;
	}

	@Override
	public void setWasAttributedTo(String wasAttributedTo) {
		this.wasAttributedTo = wasAttributedTo;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String getRights() {
		return rights;
	}

	@Override
	public void setRights(String rights) {
		this.rights = rights;
	}

	@Override
	public CitagoraAgent getGenerator() {
		return generator;
	}

	/**
	 * Set both sides of the relationship, carefully. This code is repeated for
	 * every ManyToOne field.
	 */
	@Override
	public void setGenerator(CitagoraAgent generator) {
		if (this.generator == generator)
			return; // no change
		if (this.generator != null) {
			// remove from inverse relationship
			this.generator.getAgentObjects().remove(this);
		}
		// set forward relationship
		this.generator = generator;
		if (generator == null)
			return;
		generator.getAgentObjects().add(this);
	}

	@Override
	public Date getGenerated() {
		return generated;
	}

	@Override
	public void setGenerated(Date generated) {
		this.generated = generated;
	}

	@Override
	public List<Annotation> getAnnotations() {
		if (annotations == null) {
			annotations = new Vector<Annotation>();
		}
		return annotations;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return uri;
	}

	@Override
	public Integer getMyId() {
		return myId;
	}

	public void setMyId(Integer myId) {
		this.myId = myId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public Date getUpdated() {
		return updated;
	}

	public static int getObjectId() {
		return objectId;
	}
}
