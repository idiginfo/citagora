/*
 * LingPipe v. 3.9
 * Copyright (C) 2003-2010 Alias-i
 *
 * This program is licensed under the Alias-i Royalty Free License
 * Version 1 WITHOUT ANY WARRANTY, without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the Alias-i
 * Royalty Free License Version 1 for more details.
 *
 * You should have received a copy of the Alias-i Royalty Free License
 * Version 1 along with this program; if not, visit
 * http://alias-i.com/lingpipe/licenses/lingpipe-license-1.txt or contact
 * Alias-i, Inc. at 181 North 11th Street, Suite 401, Brooklyn, NY 11211,
 * +1 (718) 290-9170.
 */

package com.aliasi.lingmed.medline.parser;

import com.aliasi.xml.DelegateHandler;
import com.aliasi.xml.DelegatingHandler;
import com.aliasi.xml.TextAccumulatorHandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

/**
 * An <code>AbstractText</code> represents the text of the abstract
 * for a MEDLINE document.
 * The text of the abstract is drawn from the source
 * articles themselves; abstracts are never created by the NLM.  All
 * abstracts are in English, even for articles that are not in
 * English.  
 *
 * <P>As of 2011 the specification of the Abstract and AbstractText 
 * elements were changed.  Before an abstract contained a single
 * unit of text.  Now an abstract can contain one or more texts.
 * Optional label and NlmCategory attributes have been added.
 * The value of the former is a text string.  
 * The value of the latter is one of:
 *
 * <blockquote>
 * <table border='1' cellpadding='5'>
 * <tr><td><code>UNLABELLED</code></td></tr>
 * <tr><td><code>BACKGROUND</code></td></tr>
 * <tr><td><code>OBJECTIVE</code></td></tr>
 * <tr><td><code>METHODS</code></td></tr>
 * <tr><td><code>RESULTS</code></td></tr>
 * <tr><td><code>CONCLUSIONS</code></td></tr>
 * </table>
 * </blockquote>
 *
 * <P>The text of an abstract created before 2001  may be truncated.
 * One of the following may appear at the end of the text of a
 * truncated abstract:
 * 
 * <blockquote>
 * <table border='1' cellpadding='5'>
 * <tr><td><i>Truncation Marker</i></td></tr>
 * <tr><td><code>(ABSTRACT TRUNCATED AT 250 WORDS)</code></td></tr>
 * <tr><td><code>(ABSTRACT TRUNCATED AT 400 WORDS)</code></td></tr>
 * <tr><td><code>(ABSTRACT TRUNCATED)</code></td></tr>
 * </table>
 * </blockquote>
 *
 * The message without an explicit word length only shows up on
 * abstracts of more than 4,096 characters from records created
 * between 1996 and 2001.
 *
 * @author  Bob Carpenter, Mitzi Morris
 * @version Lingmed1.3
 * @since   LingPipe2.0
 */
public class AbstractText {

    private static final String TRUNCATION_MARKER_250 
        = "(ABSTRACT TRUNCATED AT 250 WORDS)";
    private static final String TRUNCATION_MARKER_400
        = "(ABSTRACT TRUNCATED AT 400 WORDS)";
    private static final String TRUNCATION_MARKER_4096
        = "(ABSTRACT TRUNCATED)";
    private static final String[] TRUNCATION_MARKERS
        = new String[] {
            TRUNCATION_MARKER_250,
            TRUNCATION_MARKER_400,
            TRUNCATION_MARKER_4096 
        };

    private final String mText;
    private final String mLabel;
    private final String mNlmCategory;

    AbstractText(String text, String label, String nlmCategory) {
        mText = text;
        mLabel = label;
	String tmpCategory = MedlineCitationSet.UNLABELLED_VALUE;
	if (nlmCategory != null && isValidNlmCategory(nlmCategory))
	    tmpCategory = nlmCategory;
	mNlmCategory = tmpCategory;
    }

    /**
     * Returns the text of this abstract.  See the class documentation
     * for notes on possible trunctation indicators.
     *
     * @return The abstract text block.
     */
    public String text() {
        return mText;
    }

    /**
     * Returns the NlmCategory of this abstract text block.
     *
     * @return The NlmCategory of this abstract text block.
     */
    public String nlmCategory() {
        return mNlmCategory;
    }

    /**
     * Returns the label of this abstract text block.
     *
     * @return The label of this abstract text block.
     */
    public String label() {
        return mLabel;
    }

    /**
     * Returns <code>true</code> if the text of the abstract
     * has been truncated.  This is determined by inspecting
     * the last characters in the abstract as indicated in
     * the class documentation above.
     *
     * @return <code>true</code> if this abstract has been truncated.
     */
    public boolean isTruncated() {
        for (int i = 0; i < TRUNCATION_MARKERS.length; ++i)
            if (text().endsWith(TRUNCATION_MARKERS[i]))
                return true;
        return false;
    }

    /**
     * Returns the text of this abstract with any final truncation
     * markers removed.
     *
     * @return The text of this abstract with any final truncation
     * markers removed.
     */
    public String textWithoutTruncationMarker() {
        return textWithoutTruncationMarker(text());
    }


    /**
     * Returns a trimmed version of the specified text with any
     * final truncation markers removed.
     *
     * @param text Input text.
     * @return Trimmed output with truncation markers stripped.
     */
    public static String textWithoutTruncationMarker(String text) {
        String trimmedText = text.trim();
        for (int i = 0; i < TRUNCATION_MARKERS.length; ++i)
            if (trimmedText.endsWith(TRUNCATION_MARKERS[i]))
                return trimmedText.substring(0,
                                      trimmedText.length()
                                      - TRUNCATION_MARKERS[i].length());
        return trimmedText;
    }

    /**
     * Returns a string-based representation of this abstract text block.
     *
     * @return A string-based representation of this abstract text block.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
	if (! nlmCategory().equals(MedlineCitationSet.UNLABELLED_VALUE)){
	    sb.append("NlmCategory=" + nlmCategory() + " ");
	    if (label() != null) {
		sb.append("Label=" + label() + " ");
	    }
	}
        sb.append("Text=");
        sb.append(text());
        sb.append('}');
        return sb.toString();
    }

    /** 
     * Checks the category text against the set of allowable values
     * for the AbstractText NlmCategory attribute.
     *
     * @return true if value is valid, otherwise false
     */
    boolean isValidNlmCategory(String nlmCategory) {
	if (nlmCategory == null) return false;
	if (nlmCategory.equals(MedlineCitationSet.UNLABELLED_VALUE)
	    || nlmCategory.equals(MedlineCitationSet.BACKGROUND_VALUE)
	    || nlmCategory.equals(MedlineCitationSet.OBJECTIVE_VALUE)
	    || nlmCategory.equals(MedlineCitationSet.METHODS_VALUE)
	    || nlmCategory.equals(MedlineCitationSet.RESULTS_VALUE)
	    || nlmCategory.equals(MedlineCitationSet.CONCLUSIONS_VALUE))
	    return true;
	return false;
    }

    static class Handler extends TextAccumulatorHandler {
	private String mLabel;
	private String mNlmCategory;
        @Override
	public void startDocument() {
            super.startDocument();
            reset();
        }
        @Override
        public void startElement(String namespaceURI, String localName,
                                 String qName, Attributes atts) 
            throws SAXException {
            if (qName.equals(MedlineCitationSet.ABSTRACT_TEXT_ELT)) {
                mLabel = atts.getValue(MedlineCitationSet.ABSTRACT_TEXT_LABEL_ATT);
                mNlmCategory = atts.getValue(MedlineCitationSet.ABSTRACT_TEXT_NLM_CATEGORY_ATT);
            } else {
                super.startElement(namespaceURI,localName,qName,atts);
            }
        }
        public AbstractText getAbstractText() {
            return new AbstractText(getText(),mLabel,mNlmCategory);
        }
    }

}
