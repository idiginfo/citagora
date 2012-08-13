
package com.thomsonreuters.wokmws.cxf.woksearchlite;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.thomsonreuters.wokmws.cxf.woksearchlite package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RetrieveResponse_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "retrieveResponse");
    private final static QName _RelatedRecords_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "relatedRecords");
    private final static QName _SessionException_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "SessionException");
    private final static QName _RelatedRecordsResponse_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "relatedRecordsResponse");
    private final static QName _InvalidInputException_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "InvalidInputException");
    private final static QName _InternalServerException_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "InternalServerException");
    private final static QName _ESTIWSException_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "ESTIWSException");
    private final static QName _SearchResponse_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "searchResponse");
    private final static QName _RetrieveByIdResponse_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "retrieveByIdResponse");
    private final static QName _QueryException_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "QueryException");
    private final static QName _CitingArticles_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "citingArticles");
    private final static QName _CitingArticlesResponse_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "citingArticlesResponse");
    private final static QName _AuthenticationException_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "AuthenticationException");
    private final static QName _Retrieve_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "retrieve");
    private final static QName _RetrieveById_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "retrieveById");
    private final static QName _Search_QNAME = new QName("http://woksearchlite.cxf.wokmws.thomsonreuters.com", "search");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.thomsonreuters.wokmws.cxf.woksearchlite
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvalidInputException }
     * 
     */
    public InvalidInputException createInvalidInputException() {
        return new InvalidInputException();
    }

    /**
     * Create an instance of {@link InternalServerException }
     * 
     */
    public InternalServerException createInternalServerException() {
        return new InternalServerException();
    }

    /**
     * Create an instance of {@link RelatedRecordsResponse }
     * 
     */
    public RelatedRecordsResponse createRelatedRecordsResponse() {
        return new RelatedRecordsResponse();
    }

    /**
     * Create an instance of {@link SearchResponse }
     * 
     */
    public SearchResponse createSearchResponse() {
        return new SearchResponse();
    }

    /**
     * Create an instance of {@link ESTIWSException }
     * 
     */
    public ESTIWSException createESTIWSException() {
        return new ESTIWSException();
    }

    /**
     * Create an instance of {@link RelatedRecords }
     * 
     */
    public RelatedRecords createRelatedRecords() {
        return new RelatedRecords();
    }

    /**
     * Create an instance of {@link RetrieveResponse }
     * 
     */
    public RetrieveResponse createRetrieveResponse() {
        return new RetrieveResponse();
    }

    /**
     * Create an instance of {@link SessionException }
     * 
     */
    public SessionException createSessionException() {
        return new SessionException();
    }

    /**
     * Create an instance of {@link CitingArticlesResponse }
     * 
     */
    public CitingArticlesResponse createCitingArticlesResponse() {
        return new CitingArticlesResponse();
    }

    /**
     * Create an instance of {@link RetrieveById }
     * 
     */
    public RetrieveById createRetrieveById() {
        return new RetrieveById();
    }

    /**
     * Create an instance of {@link Search }
     * 
     */
    public Search createSearch() {
        return new Search();
    }

    /**
     * Create an instance of {@link AuthenticationException }
     * 
     */
    public AuthenticationException createAuthenticationException() {
        return new AuthenticationException();
    }

    /**
     * Create an instance of {@link Retrieve }
     * 
     */
    public Retrieve createRetrieve() {
        return new Retrieve();
    }

    /**
     * Create an instance of {@link RetrieveByIdResponse }
     * 
     */
    public RetrieveByIdResponse createRetrieveByIdResponse() {
        return new RetrieveByIdResponse();
    }

    /**
     * Create an instance of {@link CitingArticles }
     * 
     */
    public CitingArticles createCitingArticles() {
        return new CitingArticles();
    }

    /**
     * Create an instance of {@link QueryException }
     * 
     */
    public QueryException createQueryException() {
        return new QueryException();
    }

    /**
     * Create an instance of {@link QueryField }
     * 
     */
    public QueryField createQueryField() {
        return new QueryField();
    }

    /**
     * Create an instance of {@link RetrieveParameters }
     * 
     */
    public RetrieveParameters createRetrieveParameters() {
        return new RetrieveParameters();
    }

    /**
     * Create an instance of {@link QueryParameters }
     * 
     */
    public QueryParameters createQueryParameters() {
        return new QueryParameters();
    }

    /**
     * Create an instance of {@link LabelValuesPair }
     * 
     */
    public LabelValuesPair createLabelValuesPair() {
        return new LabelValuesPair();
    }

    /**
     * Create an instance of {@link SearchResults }
     * 
     */
    public SearchResults createSearchResults() {
        return new SearchResults();
    }

    /**
     * Create an instance of {@link TimeSpan }
     * 
     */
    public TimeSpan createTimeSpan() {
        return new TimeSpan();
    }

    /**
     * Create an instance of {@link EditionDesc }
     * 
     */
    public EditionDesc createEditionDesc() {
        return new EditionDesc();
    }

    /**
     * Create an instance of {@link LiteRecord }
     * 
     */
    public LiteRecord createLiteRecord() {
        return new LiteRecord();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "retrieveResponse")
    public JAXBElement<RetrieveResponse> createRetrieveResponse(RetrieveResponse value) {
        return new JAXBElement<RetrieveResponse>(_RetrieveResponse_QNAME, RetrieveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RelatedRecords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "relatedRecords")
    public JAXBElement<RelatedRecords> createRelatedRecords(RelatedRecords value) {
        return new JAXBElement<RelatedRecords>(_RelatedRecords_QNAME, RelatedRecords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "SessionException")
    public JAXBElement<SessionException> createSessionException(SessionException value) {
        return new JAXBElement<SessionException>(_SessionException_QNAME, SessionException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RelatedRecordsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "relatedRecordsResponse")
    public JAXBElement<RelatedRecordsResponse> createRelatedRecordsResponse(RelatedRecordsResponse value) {
        return new JAXBElement<RelatedRecordsResponse>(_RelatedRecordsResponse_QNAME, RelatedRecordsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidInputException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "InvalidInputException")
    public JAXBElement<InvalidInputException> createInvalidInputException(InvalidInputException value) {
        return new JAXBElement<InvalidInputException>(_InvalidInputException_QNAME, InvalidInputException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InternalServerException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "InternalServerException")
    public JAXBElement<InternalServerException> createInternalServerException(InternalServerException value) {
        return new JAXBElement<InternalServerException>(_InternalServerException_QNAME, InternalServerException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ESTIWSException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "ESTIWSException")
    public JAXBElement<ESTIWSException> createESTIWSException(ESTIWSException value) {
        return new JAXBElement<ESTIWSException>(_ESTIWSException_QNAME, ESTIWSException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "searchResponse")
    public JAXBElement<SearchResponse> createSearchResponse(SearchResponse value) {
        return new JAXBElement<SearchResponse>(_SearchResponse_QNAME, SearchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "retrieveByIdResponse")
    public JAXBElement<RetrieveByIdResponse> createRetrieveByIdResponse(RetrieveByIdResponse value) {
        return new JAXBElement<RetrieveByIdResponse>(_RetrieveByIdResponse_QNAME, RetrieveByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "QueryException")
    public JAXBElement<QueryException> createQueryException(QueryException value) {
        return new JAXBElement<QueryException>(_QueryException_QNAME, QueryException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CitingArticles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "citingArticles")
    public JAXBElement<CitingArticles> createCitingArticles(CitingArticles value) {
        return new JAXBElement<CitingArticles>(_CitingArticles_QNAME, CitingArticles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CitingArticlesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "citingArticlesResponse")
    public JAXBElement<CitingArticlesResponse> createCitingArticlesResponse(CitingArticlesResponse value) {
        return new JAXBElement<CitingArticlesResponse>(_CitingArticlesResponse_QNAME, CitingArticlesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenticationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "AuthenticationException")
    public JAXBElement<AuthenticationException> createAuthenticationException(AuthenticationException value) {
        return new JAXBElement<AuthenticationException>(_AuthenticationException_QNAME, AuthenticationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Retrieve }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "retrieve")
    public JAXBElement<Retrieve> createRetrieve(Retrieve value) {
        return new JAXBElement<Retrieve>(_Retrieve_QNAME, Retrieve.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "retrieveById")
    public JAXBElement<RetrieveById> createRetrieveById(RetrieveById value) {
        return new JAXBElement<RetrieveById>(_RetrieveById_QNAME, RetrieveById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Search }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com", name = "search")
    public JAXBElement<Search> createSearch(Search value) {
        return new JAXBElement<Search>(_Search_QNAME, Search.class, null, value);
    }

}
