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

import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

/**
 * A <code>Name</code> is a structured record of a person's first,
 * middle, last name and name suffixes, along with a standardized set
 * of initials.  Only the last name is guaranteed to exist.
 *
 * @author  Bob Carpenter
 * @version 3.9
 * @since   LingPipe2.0
 */
public class Name {

    private final String mForeName;
    private final String mMiddleName;
    private final String mLastName;
    private final String mSuffix;
    private final String mInitials;
    private final NameId mNameId;

    Name(String foreName, String middleName, String lastName,
         String suffix, String initials, NameId nameId) {
        mForeName = foreName;
        mMiddleName = middleName;
        mLastName = lastName;
        mSuffix = suffix;
        mInitials = initials;
        mNameId = nameId;
    }

    /**
     * Return the name identifier for this name, which includes
     * the source and an identifier, or {@code null} if there is
     * no identifier.
     *
     * @return The name identifier for this name.
     */
    public NameId nameId() {
        return mNameId;
    }

    /**
     * Returns a formatted version of this name without field names.
     * The order of names is fore name, middle name, last name, and
     * suffix. A single space character is inserted between each
     * non-null name.
     */
    public String fullName() {
        StringBuilder fullName = new StringBuilder();
        appendNonNull(fullName,mForeName);
        appendNonNull(fullName,mMiddleName);
        appendNonNull(fullName,mLastName);
        appendNonNull(fullName,mSuffix);
        return fullName.toString();
    }

    private void appendNonNull(StringBuilder sb, String x) {
        if (x == null || x.length() == 0) return;
        if (sb.length() > 0) sb.append(' ');
        sb.append(x);
    }

    /**
     * Returns the last name.
     *
     * @return The last name.
     */
    public String lastName() {
        return mLastName;
    }

    /**
     * Returns the first name.  May be the empty (zero length) string.
     *
     * @return The first name.
     *
     */
    public String foreName() {
        return mForeName;
    }

    /**
     * Returns the middle name.  May be the empty (zero length) string.
     *
     * @return The middle name.
     *
     */
    public String middleName() {
        return mMiddleName;
    }

    /**
     * Returns any suffix for the name.  May be the empty (zero
     * length) string.
     *
     * @return The suffix for this name.
     */
    public String suffix() {
        return mSuffix;
    }

    /**
     * Return the standardized initials for the first name
     * and middle name of this name.
     *
     * @return The initials for this name.
     */
    public String initials() {
        return mInitials;
    }

    /**
     * Returns a string-based representation of this name.
     * The initials are elided.
     *
     * @return A string-based representation of this name.
     */
    @Override
    public String toString() {
        return "Fore=" + foreName()
            + " Mid=" + middleName()
            + " Last=" + lastName()
            + " Suffix=" + suffix();
    }

    // <!ENTITY % personal.name "(LastName,(ForeName|(FirstName,MiddleName?))?,
    //                            Initials?,Suffix?)">
    static class Handler extends DelegateHandler {
        private final TextAccumulatorHandler mForeNameHandler
            = new TextAccumulatorHandler();
        private final TextAccumulatorHandler mMiddleNameHandler
            = new TextAccumulatorHandler();
        private final TextAccumulatorHandler mLastNameHandler
            = new TextAccumulatorHandler();
        private final TextAccumulatorHandler mSuffixHandler
            = new TextAccumulatorHandler();
        private final TextAccumulatorHandler mInitialsHandler
            = new TextAccumulatorHandler();
        private final NameId.Handler mNameIdHandler;
        private NameId mNameId;
        public Handler(DelegatingHandler delegator) {
            super(delegator);
            setDelegate(MedlineCitationSet.FORE_NAME_ELT,
                        mForeNameHandler);
            setDelegate(MedlineCitationSet.MIDDLE_NAME_ELT,
                        mMiddleNameHandler);
            setDelegate(MedlineCitationSet.LAST_NAME_ELT,
                        mLastNameHandler);
            setDelegate(MedlineCitationSet.SUFFIX_ELT,
                        mSuffixHandler);
            setDelegate(MedlineCitationSet.INITIALS_ELT,
                        mInitialsHandler);
            mNameIdHandler = new NameId.Handler();
            setDelegate(MedlineCitationSet.NAME_ID_ELT,
                        mNameIdHandler);
        }
        @Override
        public void startDocument() throws SAXException {
            mForeNameHandler.reset();
            mMiddleNameHandler.reset();
            mLastNameHandler.reset();
            mSuffixHandler.reset();
            mInitialsHandler.reset();
            mNameIdHandler.reset();
            mNameId = null;
            super.startDocument();
        }
        public void finishDelegate(String qName, DefaultHandler handler) {
            if (MedlineCitationSet.NAME_ID_ELT.equals(qName))
                mNameId = mNameIdHandler.getNameId();
        }
        public Name getName() {
            return new Name(mForeNameHandler.getText(),
                            mMiddleNameHandler.getText(),
                            mLastNameHandler.getText(),
                            mSuffixHandler.getText(),
                            mInitialsHandler.getText(),
                            mNameId);
        }
    }


}
