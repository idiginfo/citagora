package org.idiginfo.docservices.annotate;

import java.util.ArrayList;
import java.util.List;

import org.idiginfo.docservices.model.Users;

public class AnnotateUsers implements Users {

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
