package org.idiginfo.docsvc.controller.resttest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import org.idiginfo.docsvc.controller.rest.CrossrefRest;

public class CrossrefTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	CrossrefTest tester = new CrossrefTest();
	tester.run();

    }

    CrossrefRest svc = new CrossrefRest();

    public void run() {
	String[] refArray = { "xxx" };
	List<String> refs = Arrays.asList(refArray);
	Response response = svc.getCrossref(refs);
	Object obj = response.getEntity();
	System.out.println("obj: " + obj);
    }

}
