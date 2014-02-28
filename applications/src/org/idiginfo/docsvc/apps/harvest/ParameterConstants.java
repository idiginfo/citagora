package org.idiginfo.docsvc.apps.harvest;

public class ParameterConstants {

	public class Crossref {
		public final static String FILE_DIR = "c:/dev/harvest/crossref/";
		public final static String FILE_PREFIX = FILE_DIR + "doi";
	}

	public class Mas {
		public final static String FILE_DIR = "c:/dev/harvest/mas/";
		public final static String FILE_PREFIX = FILE_DIR + "suicide_";
		public final static String SPLIT_FILE_PREFIX = "c:/dev/split/mas/";
	}

	public class Mendeley {
		public final static String FILE_DIR = "c:/dev/split/mendeleyTitle/";
		public final static String FILE_PREFIX = FILE_DIR + "suicide_";
		public static final String BASE_DIR = "c:/dev/split/test/";
	}

	public class Msrc {
		public final static String FILE_DIR = "c:/dev/harvest/msrc/";
		public final static String FILE_PREFIX = FILE_DIR + "doc_";
		public static final String BASE_DIR = "c:/dev/harvest/msrc/";

	}

	public class Sciverse {
		final static String FILE_DIR = "c:/dev/harvest/sciverse/";
		final static String FILE_PREFIX = FILE_DIR + "abs_suicide_";
		public static final String BASE_DIR = "c:/dev/split/sciverse/";
		final static String SPLIT_FILE_PREFIX = "c:/dev/split/sciverse/";
	}

	public class Springer {
		final static String FILE_DIR = "c:/dev/harvest/springerTitle/";
		final static String FILE_PREFIX = FILE_DIR + "abs_suicide_";
		public static final String BASE_DIR = "c:/dev/split/springer/";
		final static String IN_FILE_PREFIX = FILE_DIR;
		final static String SPLIT_FILE_PREFIX = "c:/dev/split/springer/";
	}

}
