package org.idiginfo.docsvc.model.harvest;

import java.io.File;

import org.idiginfo.docsvc.model.citagora.Container;

public interface ApiLoad {
	public int loadFile(Container containerFields, File file);

	public int loadFiles(Container containerFields, File baseDir);
}
