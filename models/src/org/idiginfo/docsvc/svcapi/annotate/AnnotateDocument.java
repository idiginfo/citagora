package org.idiginfo.docsvc.svcapi.annotate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

public class AnnotateDocument implements Document {
    String code;
    String date;
    String name;
    String owner;
    String type;
    String parentId;
    AnnotateNote[] notes;
    AnnotateMeta meta;

    static DateFormat formatter = new SimpleDateFormat("yyyy-MM.dd");

    @Override
    public Annotation getAnnotation(int i) {
	if (notes == null || i < 0 || i >= notes.length)
	    return null;
	return notes[i];
    }

    @Override
    public Annotation[] getAnnotations() {
	return notes;
    }

    @Override
    public List<String> getAuthorList() {
	List<String> authorList = new Vector<String>();
	authorList.add(getAuthors());
	return authorList;
    }

    @Override
    public String getAuthors() {
	if (meta != null)
	    return meta.getAuthors();
	return null;
    }

    public String getCode() {
	return code;
    }

    @Override
    public String getCopyright() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getDate() {
	return date;
    }

    @Override
    public Date getDateObject() {
	try {
	    return formatter.parse(date);
	} catch (ParseException e) {
	}
	return null;
    }

    @Override
    public String getDoi() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getGUID() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getId() {
	return code;
    }

    @Override
    public String getIsbn() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getIssn() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getIssue() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<String> getKeywords() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<String> getMeshTerms() {
	// TODO Auto-generated method stub
	return null;
    }

    AnnotateMeta getMeta() {
	return meta;

    }

    public String getName() {
	return name;
    }

    AnnotateNote getNote(int i) {
	if (notes == null || i < 0 || i >= notes.length)
	    return null;
	// TODO exception!
	return notes[i];
    }

    @Override
    public int getNumAnnotations() {
	if (notes == null)
	    return 0;
	return notes.length;
    }

    public String getOwner() {
	return owner;
    }

    @Override
    public Integer getPageEnd() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getPages() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Integer getPageStart() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Document getParent() {
	if (meta != null)
	    return meta.getParent();
	return null;
    }

    public String getParentId() {
	return parentId;
    }

    @Override
    public String getPublicationName() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getPublisher() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getSource() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getTitle() {
	if (meta != null)
	    return meta.getTitle();
	return name;
    }

    public String getType() {
	return type;
    }

    @Override
    public String getUri() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getUrl() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getVolume() {
	// TODO Auto-generated method stub
	return null;
    }

	@Override
	public String getAbstractText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAggregationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getarXivId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthorNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCoverDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEdition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String geteIssn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGenre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getItemNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPublicationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPubMedId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSeriesTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
