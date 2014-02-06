package org.idiginfo.docsvc.model.harvest;

public interface ApiHarvest {

	String harvestFiles(String keywords, String filePrefix, int numPerFile);

}
