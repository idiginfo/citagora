package org.idiginfo.docsvc.svcapi.annotate;

import java.util.Date;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.svcapi.annotate.AnnotateMeta.Payment;
import org.idiginfo.docsvc.svcapi.annotate.AnnotateMeta.Reader;

public class AnnotateDocumentNotes implements Document {
    public AnnotateMeta meta;
    public AnnotateNote[] notes;

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
	return meta.getAuthorList();
    }

    public String getAuthors() {
	return meta.authors;
    }

    public String getCode() {
	return meta.code;
    }

    @Override
    public String getCopyright() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getCost() {
	return meta.cost;
    }

    public String getCreated() {
	return meta.created;
    }

    public String getDate() {
	return meta.date;
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
	return meta.expiry;
    }

    @Override
    public String getGUID() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getHasindex() {
	return meta.hasindex;
    }

    @Override
    public String getId() {
	return meta.code;
    }

    public String getImageformat() {
	return meta.imageformat;
    }

    public String getImagequant() {
	return meta.imagequant;
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
	return meta.name;
    }

    public AnnotateNote[] getNotes() {
	// TODO Auto-generated method stub
	return notes;
    }

    @Override
    public int getNumAnnotations() {
	if (notes == null)
	    return 0;
	return notes.length;
    }

    public String getOwner() {
	return meta.owner;
    }

    @Override
    public Integer getPageEnd() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getPages() {
	return meta.pages;
    }

    @Override
    public Integer getPageStart() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Document getParent() {
	if (meta == null)
	    return null;
	return meta.getParent();
    }

    public String getParentId() {
	return meta.parentId;
    }

    public Payment[] getPayments() {
	return meta.payments;
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
	return meta.readers;
    }

    @Override
    public String getSource() {
	// TODO Auto-generated method stub
	return null;
    }

    public String getSrcext() {
	return meta.srcext;
    }

    public String getSrcname() {
	return meta.srcname;
    }

    public String getSrcsize() {
	return meta.srcsize;
    }

    public String getStatus() {
	return meta.status;
    }

    public String getTags() {
	return meta.tags;
    }

    public String getThumb() {
	return meta.thumb;
    }

    public String getTinycode() {
	return meta.tinycode;
    }

    public String getTitle() {
	return meta.title;
    }

    public String getType() {
	return meta.type;
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
}
