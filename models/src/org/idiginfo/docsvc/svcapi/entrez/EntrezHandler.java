package org.idiginfo.docsvc.svcapi.entrez;

import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;

import com.aliasi.lingmed.medline.parser.Abstract;
import com.aliasi.lingmed.medline.parser.Article;
import com.aliasi.lingmed.medline.parser.CommentOrCorrection;
import com.aliasi.lingmed.medline.parser.MedlineCitation;
import com.aliasi.lingmed.medline.parser.MedlineHandler;
import com.aliasi.lingmed.medline.parser.MedlineParser;
import com.aliasi.lingmed.medline.parser.MeshHeading;
import com.aliasi.lingmed.medline.parser.Topic;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;
import com.aliasi.util.ObjectToCounterMap;

public class EntrezHandler implements MedlineHandler {
    boolean saveXML = false;
    MedlineParser parser = new MedlineParser(saveXML);
    Documents documents = new EntrezDocuments();

    public Documents getDocuments() {
	return documents;
    }

    EntrezHandler() {
	parser.setHandler(this);
    }

    long mCitationCount = 0L;
    ObjectToCounterMap<String> mCorrexCounter = new ObjectToCounterMap<String>();
    ObjectToCounterMap<String> mCounter = new ObjectToCounterMap<String>();

    @Override
    public void handle(MedlineCitation citation) {
	map(citation);
	// ++mCitationCount;
	//
	// for (CommentOrCorrection cc : citation.commentOrCorrections())
	// mCorrexCounter.increment(cc.type());
	//
	// String id = citation.pmid();
	// // System.out.println("processing pmid=" + id);
	//
	// Article article = citation.article();
	// String titleText = article.articleTitleText();
	// addText(titleText);
	//
	// Abstract abstrct = article.abstrct();
	// if (abstrct != null) {
	// String abstractText = abstrct.textWithoutTruncationMarker();
	// addText(abstractText);
	// }
	//
	// MeshHeading[] headings = citation.meshHeadings();
	// for (MeshHeading heading : headings) {
	// for (Topic topic : heading.topics()) {
	// String topicText = topic.topic();
	// addText(topicText);
	// }
	// }
    }

    private void map(MedlineCitation citation) {
	Document document = new EntrezDocument(citation);
	documents.add(document);
    }

    public void delete(String pmid) {
	throw new UnsupportedOperationException(
		"not expecting deletes. found pmid=" + pmid);
    }

    public void addText(String text) {
	char[] cs = text.toCharArray();
	TokenizerFactory factory = IndoEuropeanTokenizerFactory.INSTANCE;
	Tokenizer tokenizer = factory.tokenizer(cs, 0, cs.length);
	for (String token : tokenizer) {
	    mCounter.increment(token);
	}
    }

    public void report() {
	System.out.println("\nCitation Count=" + mCitationCount);
	System.out.println("\nWord Counts");
	List<String> keysByCount = mCounter.keysOrderedByCountList();
	for (String key : keysByCount) {
	    int count = mCounter.getCount(key);
	    if (count < 10)
		break;
	    System.out.printf("%9d %s\n", count, key);
	}
	System.out.println("\nCorrection Counts");
	List<String> ccKeysByCount = mCorrexCounter.keysOrderedByCountList();
	for (String ccKey : ccKeysByCount) {
	    int count = mCorrexCounter.getCount(ccKey);
	    System.out.printf("%9d %s\n", count, ccKey);
	}
    }
}
