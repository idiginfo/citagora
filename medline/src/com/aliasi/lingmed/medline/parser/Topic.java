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

import com.aliasi.xml.TextAccumulatorHandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * A <code>Topic</code> consists of a string-based topic and
 * an indication as to whether the topic is a major topic for
 * an article.  
 *
 * @author  Bob Carpenter
 * @version 2.0
 * @since   LingPipe2.0
 */
public class Topic {

    private final boolean mMajor;
    private final boolean mGeographic;
    private final String mTopic;

    Topic(String topic, boolean major,  boolean geographic) {
        mTopic = topic;
        mMajor = major;
        mGeographic = geographic;
    }
    
    /**
     * Returns <code>true</code> if this topic is a major
     * topic for the article.
     *
     * @return <code>true</code> if this topic is a major
     * topic.
     */
    public boolean isMajor() {
        return mMajor;
    }

    /**
     * Returns <code>true</code> if topic type is geographic.
     *
     * @return <code>true</code> if topic type is geographic.
     * topic.
     */
    public boolean isGeographic() {
        return mGeographic;
    }

    /**
     * Return the text of the topic.
     *
     * @return The text of the topic.
     */
    public String topic() {
        return mTopic;
    }

    /**
     * Return a string-based representation of this topic.
     *
     * @return A string-based representation of this topic.
     */
    @Override
    public String toString() {
        return mTopic 
	    + (isMajor() ? " [MAJ=Y]" : " [MAJ=N]")
	    + (isGeographic() ? " [GEOGRAPHIC=Y]" : " [GEOGRAPHIC=N]");
    }

    static class Handler extends TextAccumulatorHandler {
        private boolean mMajor;
        private boolean mGeographic;
        @Override
        public void startElement(String namespaceURI, String localName,
                                 String qName, Attributes atts)
            throws SAXException {

            super.startElement(namespaceURI,localName,qName,atts);
	    mGeographic = MedlineCitationSet
		.GEOGRAPHIC_VALUE
		.equals(atts.getValue(MedlineCitationSet.TYPE_ATT));
            mMajor = MedlineCitationSet
                .YES_VALUE
                .equals(atts.getValue(MedlineCitationSet.MAJOR_TOPIC_YN_ATT));
        }
        public Topic getTopic() {
            return new Topic(getText(),mMajor,mGeographic);
        }
    }
}
