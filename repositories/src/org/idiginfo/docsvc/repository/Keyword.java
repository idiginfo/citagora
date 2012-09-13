package org.idiginfo.docsvc.repository;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Keyword
 * 
 */
@Entity
public class Keyword implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Long citationCount;
	@Id
	protected Long id;

	protected String name;

	protected Long publicationCount;

	public Keyword() {
		super();
	}

}
