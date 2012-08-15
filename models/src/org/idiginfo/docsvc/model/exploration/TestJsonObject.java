package org.idiginfo.docsvc.model.exploration;

import java.util.ArrayList;
import java.util.List;

public class TestJsonObject {

	private int data1 = 100;
	private String data2 = "hello";
	@SuppressWarnings("serial")
	private List<String> list = new ArrayList<String>() {
		{
			add("String 1");
			add("String 2");
			add("String 3");
		}
	};

	// getter and setter methods

	@Override
	public String toString() {
		return "DataObject [data1=" + data1 + ", data2=" + data2 + ", list="
				+ list + "]";
	}

}
