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

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

/**
 * An <code>Abstract</code> represents the abstract of a MEDLINE
 * document.  Abstracts contain the text of an abstract along with the
 * text of the copyright information.  Not every citation necessarily
 * has an abstract.  The text of the abstract is drawn from the source
 * articles themselves; abstracts are never created by the NLM.  All
 * abstracts are in English, even for articles that are not in
 * English.  
 *
 * <P>Some documents may contain an {@link OtherAbstract} as well as
 * an abstract.
 *
 * <P>As of 2011 the specification of the Abstract and AbstractText 
 * elements were changed.  Before an abstract contained a single
 * unit of text.  Now an abstract can contain one or more texts.
 * Therefore the text of the abstract is now modeled as a list of
 * {@link AbstractText} objects.
 *
 * @author  Bob Carpenter, Mitzi Morris
 * @version Lingmed1.3
 * @since   LingPipe2.0
 */
public class Abstract {

    private final AbstractText[] mTexts;
    private final String mCopyrightInformation;

    Abstract(AbstractText[] texts, String copyrightInformation) {
        mTexts = texts;
        mCopyrightInformation = copyrightInformation;
    }

    /**
     * Returns the text of this abstract.  See the class documentation
     * for notes on possible trunctation indicators.
     *
     * @return The text of this abstract.
     */
    public AbstractText[] texts() {
        return mTexts;
    }

    /**
     * Returns the text of this abstract with any final truncation
     * markers removed.
     *
     * @return The text of this abstract with any final truncation
     * markers removed.
     */
    public String textWithoutTruncationMarker() {
	StringBuilder sb = new StringBuilder();
	if (texts().length > 0) {
	    for (AbstractText text : texts()) {
		sb.append(text.textWithoutTruncationMarker());
		sb.append(" ");
	    }
	    sb.setLength(sb.length()-1);
	}
	return sb.toString();
    }

    /**
     * Returns the copyright information for this abstract.  Copyright
     * information is optional; this method returns the empty string
     * if no copyright information was present in the record's
     * abstract.  Copyright information may take any form, but typically
     * includes the publisher name and date (<i>e.g.</i> 
     * <code>Copyright 1999 Academic Press.</code>).
     *
     * @return The copyright information for this abstract.
     */
    public String copyrightInformation() {
        return mCopyrightInformation;
    }

    /**
     * Returns a string-based representation of this abstract.
     *
     * @return A string-based representation of this abstract.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
	for (AbstractText text : mTexts) {
	    sb.append(text.toString());
	}
        if (copyrightInformation().length() > 0) {
            sb.append(" Copyright Information=");
            sb.append(copyrightInformation());
        }
        sb.append('}');
        return sb.toString();
    }

    static class Handler extends DelegateHandler {
        private final List<AbstractText> mAbstractTexts = new ArrayList<AbstractText>();
        private final AbstractText.Handler mAbstractTextHandler;
        private final TextAccumulatorHandler mCopyrightInformationHandler
            = new TextAccumulatorHandler();
        public Handler(DelegatingHandler delegator) {
            super(delegator);
	    mAbstractTextHandler = new AbstractText.Handler();
            setDelegate(MedlineCitationSet.ABSTRACT_TEXT_ELT,
                        mAbstractTextHandler);
            setDelegate(MedlineCitationSet.COPYRIGHT_INFORMATION_ELT,
                        mCopyrightInformationHandler);
        }
        public void reset() {
	    mAbstractTexts.clear();
            mCopyrightInformationHandler.reset();
        }
        @Override
        public void finishDelegate(String qName, DefaultHandler handler) {
            if (qName.equals(MedlineCitationSet.ABSTRACT_TEXT_ELT)) {
                mAbstractTexts.add(mAbstractTextHandler.getAbstractText());
            }
        }
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            reset();
        }
        public Abstract getAbstract() {
            AbstractText[] texts
                = mAbstractTexts.toArray(EMPTY_ABSTRACT_TEXT_ARRAY);
            return new Abstract(texts,
                                mCopyrightInformationHandler.getText());
        }
    }

    static final AbstractText[] EMPTY_ABSTRACT_TEXT_ARRAY = new AbstractText[0];

}
