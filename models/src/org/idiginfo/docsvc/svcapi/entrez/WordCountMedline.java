package org.idiginfo.docsvc.svcapi.entrez;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
import com.aliasi.util.Streams;
import com.aliasi.util.Strings;

public class WordCountMedline {

    public static final String FILE = "c:/dev/api_samples/medsamp.xml";
    public static void main(String[] args) throws IOException, SAXException {
        boolean saveXML = false;
        MedlineParser parser = new MedlineParser(saveXML);
        WordCountHandler handler = new WordCountHandler();
        parser.setHandler(handler);
        for (String arg : args) {
            System.out.println("Processing file=" + arg);
            if (arg.endsWith(".xml")) {
                InputSource inputSource = new InputSource(arg);
                parser.parse(inputSource);
            } else if (arg.endsWith(".gz")) {
                File file = new File(arg);
                FileInputStream fileIn = null;
                GZIPInputStream gzipIn = null;
                InputStreamReader inReader = null;
                BufferedReader bufReader = null;
                InputSource inSource = null;
                try {
                    fileIn = new FileInputStream(file);
                    gzipIn = new GZIPInputStream(fileIn);
                    inReader = new InputStreamReader(gzipIn,Strings.UTF8);
                    bufReader = new BufferedReader(inReader);
                    inSource = new InputSource(bufReader);
                    inSource.setSystemId(file.toURI().toURL().toString());
                    parser.parse(inSource);
                } finally {
                    Streams.closeReader(bufReader);
                    Streams.closeReader(inReader);
                    Streams.closeInputStream(gzipIn);
                    Streams.closeInputStream(fileIn);
                }
            } else {
                throw new IllegalArgumentException("arguments must end with .xml or .gz");
            }
        }
        handler.report();
    }

    static class WordCountHandler implements MedlineHandler {
        long mCitationCount = 0L;
        ObjectToCounterMap<String> mCorrexCounter = new ObjectToCounterMap<String>();
        ObjectToCounterMap<String> mCounter = new ObjectToCounterMap<String>();
        public void handle(MedlineCitation citation) {
            ++mCitationCount;

            for (CommentOrCorrection cc : citation.commentOrCorrections())
                mCorrexCounter.increment(cc.type());

            String id = citation.pmid();
            // System.out.println("processing pmid=" + id);

            Article article = citation.article();
            String titleText = article.articleTitleText();
            addText(titleText);

            Abstract abstrct = article.abstrct();
            if (abstrct != null) {
                String abstractText = abstrct.textWithoutTruncationMarker();
                addText(abstractText);
            }

            MeshHeading[] headings = citation.meshHeadings();
            for (MeshHeading heading : headings) {
                for (Topic topic : heading.topics()) { 
                    String topicText = topic.topic();
                    addText(topicText);
                }
            }
        }
        public void delete(String pmid) {
            throw new UnsupportedOperationException("not expecting deletes. found pmid=" + pmid);
        }
        public void addText(String text) {
            char[] cs = text.toCharArray();
            TokenizerFactory factory = IndoEuropeanTokenizerFactory.INSTANCE;
            Tokenizer tokenizer = factory.tokenizer(cs,0,cs.length);
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
                if (count < 10) break;
                System.out.printf("%9d %s\n",count,key);
            }
            System.out.println("\nCorrection Counts");
            List<String> ccKeysByCount = mCorrexCounter.keysOrderedByCountList();
            for (String ccKey : ccKeysByCount) {
                int count = mCorrexCounter.getCount(ccKey);
                System.out.printf("%9d %s\n",count,ccKey);
            }
        }
    }

}

