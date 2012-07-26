package org.idiginfo.annotate;

import java.util.ArrayList;
import java.util.List;

public class AnnotateUsers {

	private List<String> members = new ArrayList<String>();
	private List<String> annotators = new ArrayList<String>();

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}

	public List<String> getAnnotaters() {
		return annotators;
	}

	public void setAnnotaters(List<String> annotators) {
		this.annotators = annotators;
	}

}
