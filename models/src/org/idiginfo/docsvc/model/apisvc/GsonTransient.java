package org.idiginfo.docsvc.model.apisvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface to implement control of transience for GSON writer This interface
 * allows fields to be marked as transient for output to JSON without being
 * transient to the persistence model.
 * 
 * Used in org.idiginfo.docsvc.jpa.citagora classes that have fields that are
 * stored in the database but not included in JSON output.
 * 
 * See also class org.idiginfo.docsvc.view.MyExclusionStrategy which is used in
 * classorg.idiginfo.docsvc.view.JsonWriter
 * 
 * @author griccardi
 * 
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GsonTransient {

}
