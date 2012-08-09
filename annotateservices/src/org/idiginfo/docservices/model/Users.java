package org.idiginfo.docservices.model;

import java.util.List;

public interface Users {
	public List<String> getMembers();

	public void setMembers(List<String> members);

	public List<String> getAnnotaters();

	public void setAnnotaters(List<String> annotators);
}
