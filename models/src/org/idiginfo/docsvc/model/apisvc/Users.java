package org.idiginfo.docsvc.model.apisvc;

import java.util.List;

/**
 * Interface to implement the Users object
 * 
 * @author griccardi
 * 
 */

public interface Users {
	public List<String> getMembers();

	public void setMembers(List<String> members);

	public List<String> getAnnotaters();

	public void setAnnotaters(List<String> annotators);
}
