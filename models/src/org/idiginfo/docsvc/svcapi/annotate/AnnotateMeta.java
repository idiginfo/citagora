package org.idiginfo.docsvc.svcapi.annotate;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

public class AnnotateMeta implements Document {
    static class Payment {
	String date;
	String type;
	String cost;
    }

    static class Reader {
	String email;
	String role;
	String sig;
    }

    String code;
    String date;
    String name;
    String owner;
    String type;
    String parentId;
    String notes; // may be string?
    String title;
    String created;
    String tags;
    String pages;
    String cost;
    String srcext;
    String srcname;
    String srcsize;
    String imageformat;
    String imagequant;
    String thumb;
    String hasindex;

    String tinycode;

    String authors;
    Reader[] readers;

    String status;

    Payment[] payments;
    String expiry;

    @Override
    public Annotation getAnnotation(int i) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Annotation[] getAnnotations() {
	return null;
    }

    @Override
    public List<String> getAuthorList() {
	List<String> authorList = new Vector<String>();
	authorList.add(getAuthors());
	return authorList;
    }

    public String getAuthors() {
	return authors;
    }

    public String getCode() {
	return code;
    }

    @Override
    public String getCopyright() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getCost() {
	return cost;
    }

    public String getCreated() {
	return created;
    }

    public String getDate() {
	return date;
    }

    @Override
    public Date getDateObject() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getDoi() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getExpiry() {
	return expiry;
    }

    @Override
    public String getGUID() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getHasindex() {
	return hasindex;
    }

    @Override
    public String getId() {
	return code;
    }

    public String getImageformat() {
	return imageformat;
    }

    public String getImagequant() {
	return imagequant;
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

    public String getName() {
	return name;
    }

    public String getNotes() {
	return notes;
    }

    @Override
    public int getNumAnnotations() {
	// TODO Auto-generated method stub
	return 0;
    }

    public String getOwner() {
	return owner;
    }

    @Override
    public Integer getPageEnd() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getPages() {
	return pages;
    }

    @Override
    public Integer getPageStart() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Document getParent() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getParentId() {
	return parentId;
    }

    public Payment[] getPayments() {
	return payments;
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

    public Reader[] getReaders() {
	return readers;
    }

    @Override
    public String getSource() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getSrcext() {
	return srcext;
    }

    public String getSrcname() {
	return srcname;
    }

    public String getSrcsize() {
	return srcsize;
    }

    public String getStatus() {
	return status;
    }

    public String getTags() {
	return tags;
    }

    public String getThumb() {
	return thumb;
    }

    public String getTinycode() {
	return tinycode;
    }

    public String getTitle() {
	return title;
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

    public void setAuthors(String authors) {
	this.authors = authors;
    }

    public void setCode(String code) {
	this.code = code;
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
	public String getAuthorNotes() {
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
	public String getPMId() {
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

    @Override
    public String getIssued() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Date getIssuedDate() {
	// TODO Auto-generated method stub
	return null;
    }

}
