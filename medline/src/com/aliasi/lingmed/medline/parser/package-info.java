/**
 *
 * Classes for manipulating the 2010 version of MEDLINE data.  The
 * classes in this package are able to read the MEDLINE database from
 * its gzipped distribution format and render them completely as
 * structured Java objects.
 *
 * <P> The basic method for handling the complete set of MEDLINE
 * citations with Java is based on the visitor pattern, as described
 * in the class documentation for {@link
 * com.aliasi.lingmed.medline.parser.MedlineCitationSet}.
 *
 * <P> MEDLINE contains citations to roughly 15 million articles drawn
 * from books and journals on the broad topic of biomedicine dating
 * from 1966 to the present.  It is updated with new citations five
 * times weekly.  Roughly 500,000 new citations are added each year
 * (that's 10,000/week, or 2,000 per update).  MEDLINE was created and
 * is maintained by the <a
 * href="http://www.nlm.nih.gov/bsd/bsdhome.html">Bibliographic
 * Services Division</a> (BSD), a part of Library Operations at the
 * (United States) National Library of Medicine (NLM).  The National
 * Library of Medicine is itself a division of the National Institute
 * of Health (NIH).  MEDLINE data is free for just about any purpose,
 * including serving data and as the basis of commercial applications.
 *
 * <P>Thorough documentation for the content of a
 * <code>MedlineCitationSet</code> document is provided by NIH in the
 * document:
 *
 * <blockquote> <a
 * href="http://www.nlm.nih.gov/bsd/licensee/data_elements_doc.html">MEDLINE
 * XML Element Descriptions and Their Attributes</a>.  </blockquote>
 *
 * <P>Because the MEDLINE data format changes on a yearly basis, the
 * classes in this package will also change yearly.  Rather than
 * trying to version this package by year of release, it will be kept
 * current with the latest version of MEDLINE.  This means that there
 * is no guarantee of backward compatibility for these classes as the
 * MEDLINE yearly cycle changes.  
 *
 * <P>Our own benchmarks indicate that it will take roughly 4 hours to
 * visit each MEDLINE citation on a modern desktop PC running Java in
 * server mode.  The memory required for the parsing and visiting
 * itself is negligible, being just enough to do the XML parsing and
 * hold a single citation after being constructed.
 *
 * <P>For general information on MEDLINE, see: <UL> <LI>NLM's <a
 * href="http://www.nlm.nih.gov/pubs/factsheets/medline.html">MEDLINE
 * Fact Sheet</a>.
 *
 * </UL>
 */
package com.aliasi.lingmed.medline.parser;