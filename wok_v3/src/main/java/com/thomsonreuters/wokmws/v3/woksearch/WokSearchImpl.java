
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.thomsonreuters.wokmws.v3.woksearch;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.0
 * 2012-10-29T13:06:12.261-04:00
 * Generated source version: 2.7.0
 * 
 */

@javax.jws.WebService(
                      serviceName = "WokSearchService",
                      portName = "WokSearchPort",
                      targetNamespace = "http://woksearch.v3.wokmws.thomsonreuters.com",
                      wsdlLocation = "http://search.webofknowledge.com/esti/wokmws/ws/WokSearch?wsdl",
                      endpointInterface = "com.thomsonreuters.wokmws.v3.woksearch.WokSearch")
                      
public class WokSearchImpl implements WokSearch {

    private static final Logger LOG = Logger.getLogger(WokSearchImpl.class.getName());

    /* (non-Javadoc)
     * @see com.thomsonreuters.wokmws.v3.woksearch.WokSearch#citedReferencesRetrieve(java.lang.String  queryId ,)com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters  retrieveParameters )*
     */
    public java.util.List<com.thomsonreuters.wokmws.v3.woksearch.CitedReference> citedReferencesRetrieve(java.lang.String queryId,com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters retrieveParameters) throws InternalServerException_Exception , QueryException_Exception , SessionException_Exception , AuthenticationException_Exception , InvalidInputException_Exception , ESTIWSException_Exception    { 
        LOG.info("Executing operation citedReferencesRetrieve");
        System.out.println(queryId);
        System.out.println(retrieveParameters);
        try {
            java.util.List<com.thomsonreuters.wokmws.v3.woksearch.CitedReference> _return = new java.util.ArrayList<com.thomsonreuters.wokmws.v3.woksearch.CitedReference>();
            com.thomsonreuters.wokmws.v3.woksearch.CitedReference _returnVal1 = new com.thomsonreuters.wokmws.v3.woksearch.CitedReference();
            _returnVal1.setUid("Uid93568444");
            _returnVal1.setDocid("Docid-1431069830");
            _returnVal1.setArticleId("ArticleId-340933940");
            _returnVal1.setCitedAuthor("CitedAuthor811079260");
            _returnVal1.setTimesCited("TimesCited179598510");
            _returnVal1.setYear("Year-766500520");
            _returnVal1.setPage("Page970329574");
            _returnVal1.setVolume("Volume-136968834");
            _returnVal1.setCitedTitle("CitedTitle1955162133");
            _returnVal1.setCitedWork("CitedWork-1383974100");
            _returnVal1.setHot("Hot700790157");
            _return.add(_returnVal1);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new InternalServerException_Exception("InternalServerException...");
        //throw new QueryException_Exception("QueryException...");
        //throw new SessionException_Exception("SessionException...");
        //throw new AuthenticationException_Exception("AuthenticationException...");
        //throw new InvalidInputException_Exception("InvalidInputException...");
        //throw new ESTIWSException_Exception("ESTIWSException...");
    }

    /* (non-Javadoc)
     * @see com.thomsonreuters.wokmws.v3.woksearch.WokSearch#relatedRecords(java.lang.String  databaseId ,)java.lang.String  uid ,)java.util.List<com.thomsonreuters.wokmws.v3.woksearch.EditionDesc>  editions ,)com.thomsonreuters.wokmws.v3.woksearch.TimeSpan  timeSpan ,)java.lang.String  queryLanguage ,)com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters  retrieveParameters )*
     */
    public com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults relatedRecords(java.lang.String databaseId,java.lang.String uid,java.util.List<com.thomsonreuters.wokmws.v3.woksearch.EditionDesc> editions,com.thomsonreuters.wokmws.v3.woksearch.TimeSpan timeSpan,java.lang.String queryLanguage,com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters retrieveParameters) throws InternalServerException_Exception , QueryException_Exception , SessionException_Exception , AuthenticationException_Exception , InvalidInputException_Exception , ESTIWSException_Exception    { 
        LOG.info("Executing operation relatedRecords");
        System.out.println(databaseId);
        System.out.println(uid);
        System.out.println(editions);
        System.out.println(timeSpan);
        System.out.println(queryLanguage);
        System.out.println(retrieveParameters);
        try {
            com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults _return = new com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults();
            _return.setQueryId("QueryId-474918977");
            _return.setRecordsFound(145915171);
            _return.setRecordsSearched(-3592802446698372339l);
            _return.setParent("Parent-102978825");
            java.util.List<com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair> _returnOptionValue = new java.util.ArrayList<com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair>();
            com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair _returnOptionValueVal1 = new com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair();
            _returnOptionValueVal1.setLabel("Label-271877438");
            java.util.List<java.lang.String> _returnOptionValueVal1Value = new java.util.ArrayList<java.lang.String>();
            _returnOptionValueVal1.getValue().addAll(_returnOptionValueVal1Value);
            _returnOptionValue.add(_returnOptionValueVal1);
            _return.getOptionValue().addAll(_returnOptionValue);
            _return.setRecords("Records878790427");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new InternalServerException_Exception("InternalServerException...");
        //throw new QueryException_Exception("QueryException...");
        //throw new SessionException_Exception("SessionException...");
        //throw new AuthenticationException_Exception("AuthenticationException...");
        //throw new InvalidInputException_Exception("InvalidInputException...");
        //throw new ESTIWSException_Exception("ESTIWSException...");
    }

    /* (non-Javadoc)
     * @see com.thomsonreuters.wokmws.v3.woksearch.WokSearch#citingArticles(java.lang.String  databaseId ,)java.lang.String  uid ,)java.util.List<com.thomsonreuters.wokmws.v3.woksearch.EditionDesc>  editions ,)com.thomsonreuters.wokmws.v3.woksearch.TimeSpan  timeSpan ,)java.lang.String  queryLanguage ,)com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters  retrieveParameters )*
     */
    public com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults citingArticles(java.lang.String databaseId,java.lang.String uid,java.util.List<com.thomsonreuters.wokmws.v3.woksearch.EditionDesc> editions,com.thomsonreuters.wokmws.v3.woksearch.TimeSpan timeSpan,java.lang.String queryLanguage,com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters retrieveParameters) throws InternalServerException_Exception , QueryException_Exception , SessionException_Exception , AuthenticationException_Exception , InvalidInputException_Exception , ESTIWSException_Exception    { 
        LOG.info("Executing operation citingArticles");
        System.out.println(databaseId);
        System.out.println(uid);
        System.out.println(editions);
        System.out.println(timeSpan);
        System.out.println(queryLanguage);
        System.out.println(retrieveParameters);
        try {
            com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults _return = new com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults();
            _return.setQueryId("QueryId-1724739277");
            _return.setRecordsFound(-1431324172);
            _return.setRecordsSearched(469750847931552160l);
            _return.setParent("Parent1904533687");
            java.util.List<com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair> _returnOptionValue = new java.util.ArrayList<com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair>();
            com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair _returnOptionValueVal1 = new com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair();
            _returnOptionValueVal1.setLabel("Label-1175864858");
            java.util.List<java.lang.String> _returnOptionValueVal1Value = new java.util.ArrayList<java.lang.String>();
            _returnOptionValueVal1.getValue().addAll(_returnOptionValueVal1Value);
            _returnOptionValue.add(_returnOptionValueVal1);
            _return.getOptionValue().addAll(_returnOptionValue);
            _return.setRecords("Records197108527");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new InternalServerException_Exception("InternalServerException...");
        //throw new QueryException_Exception("QueryException...");
        //throw new SessionException_Exception("SessionException...");
        //throw new AuthenticationException_Exception("AuthenticationException...");
        //throw new InvalidInputException_Exception("InvalidInputException...");
        //throw new ESTIWSException_Exception("ESTIWSException...");
    }

    /* (non-Javadoc)
     * @see com.thomsonreuters.wokmws.v3.woksearch.WokSearch#citedReferences(java.lang.String  databaseId ,)java.lang.String  uid ,)java.lang.String  queryLanguage ,)com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters  retrieveParameters )*
     */
    public com.thomsonreuters.wokmws.v3.woksearch.CitedReferencesSearchResults citedReferences(java.lang.String databaseId,java.lang.String uid,java.lang.String queryLanguage,com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters retrieveParameters) throws InternalServerException_Exception , QueryException_Exception , SessionException_Exception , AuthenticationException_Exception , InvalidInputException_Exception , ESTIWSException_Exception    { 
        LOG.info("Executing operation citedReferences");
        System.out.println(databaseId);
        System.out.println(uid);
        System.out.println(queryLanguage);
        System.out.println(retrieveParameters);
        try {
            com.thomsonreuters.wokmws.v3.woksearch.CitedReferencesSearchResults _return = new com.thomsonreuters.wokmws.v3.woksearch.CitedReferencesSearchResults();
            _return.setQueryId("QueryId-633629515");
            java.util.List<com.thomsonreuters.wokmws.v3.woksearch.CitedReference> _returnReferences = new java.util.ArrayList<com.thomsonreuters.wokmws.v3.woksearch.CitedReference>();
            com.thomsonreuters.wokmws.v3.woksearch.CitedReference _returnReferencesVal1 = new com.thomsonreuters.wokmws.v3.woksearch.CitedReference();
            _returnReferencesVal1.setUid("Uid772554742");
            _returnReferencesVal1.setDocid("Docid-10018307");
            _returnReferencesVal1.setArticleId("ArticleId1048533924");
            _returnReferencesVal1.setCitedAuthor("CitedAuthor1996757430");
            _returnReferencesVal1.setTimesCited("TimesCited702847459");
            _returnReferencesVal1.setYear("Year1168054608");
            _returnReferencesVal1.setPage("Page930116318");
            _returnReferencesVal1.setVolume("Volume294325740");
            _returnReferencesVal1.setCitedTitle("CitedTitle-1837957057");
            _returnReferencesVal1.setCitedWork("CitedWork-572004608");
            _returnReferencesVal1.setHot("Hot536619090");
            _returnReferences.add(_returnReferencesVal1);
            _return.getReferences().addAll(_returnReferences);
            _return.setRecordsFound(-558339751);
            _return.setRecordsSearched(-7562523405378928483l);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new InternalServerException_Exception("InternalServerException...");
        //throw new QueryException_Exception("QueryException...");
        //throw new SessionException_Exception("SessionException...");
        //throw new AuthenticationException_Exception("AuthenticationException...");
        //throw new InvalidInputException_Exception("InvalidInputException...");
        //throw new ESTIWSException_Exception("ESTIWSException...");
    }

    /* (non-Javadoc)
     * @see com.thomsonreuters.wokmws.v3.woksearch.WokSearch#retrieve(java.lang.String  queryId ,)com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters  retrieveParameters )*
     */
    public com.thomsonreuters.wokmws.v3.woksearch.FullRecordData retrieve(java.lang.String queryId,com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters retrieveParameters) throws InternalServerException_Exception , QueryException_Exception , SessionException_Exception , AuthenticationException_Exception , InvalidInputException_Exception , ESTIWSException_Exception    { 
        LOG.info("Executing operation retrieve");
        System.out.println(queryId);
        System.out.println(retrieveParameters);
        try {
            com.thomsonreuters.wokmws.v3.woksearch.FullRecordData _return = new com.thomsonreuters.wokmws.v3.woksearch.FullRecordData();
            java.util.List<com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair> _returnOptionValue = new java.util.ArrayList<com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair>();
            com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair _returnOptionValueVal1 = new com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair();
            _returnOptionValueVal1.setLabel("Label-575255066");
            java.util.List<java.lang.String> _returnOptionValueVal1Value = new java.util.ArrayList<java.lang.String>();
            _returnOptionValueVal1.getValue().addAll(_returnOptionValueVal1Value);
            _returnOptionValue.add(_returnOptionValueVal1);
            _return.getOptionValue().addAll(_returnOptionValue);
            _return.setRecords("Records-418562636");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new InternalServerException_Exception("InternalServerException...");
        //throw new QueryException_Exception("QueryException...");
        //throw new SessionException_Exception("SessionException...");
        //throw new AuthenticationException_Exception("AuthenticationException...");
        //throw new InvalidInputException_Exception("InvalidInputException...");
        //throw new ESTIWSException_Exception("ESTIWSException...");
    }

    /* (non-Javadoc)
     * @see com.thomsonreuters.wokmws.v3.woksearch.WokSearch#retrieveById(java.lang.String  databaseId ,)java.util.List<java.lang.String>  uid ,)java.lang.String  queryLanguage ,)com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters  retrieveParameters )*
     */
    public com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults retrieveById(java.lang.String databaseId,java.util.List<java.lang.String> uid,java.lang.String queryLanguage,com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters retrieveParameters) throws InternalServerException_Exception , QueryException_Exception , SessionException_Exception , AuthenticationException_Exception , InvalidInputException_Exception , ESTIWSException_Exception    { 
        LOG.info("Executing operation retrieveById");
        System.out.println(databaseId);
        System.out.println(uid);
        System.out.println(queryLanguage);
        System.out.println(retrieveParameters);
        try {
            com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults _return = new com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults();
            _return.setQueryId("QueryId-911427794");
            _return.setRecordsFound(1573493447);
            _return.setRecordsSearched(3572891359266976178l);
            _return.setParent("Parent1581954700");
            java.util.List<com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair> _returnOptionValue = new java.util.ArrayList<com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair>();
            com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair _returnOptionValueVal1 = new com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair();
            _returnOptionValueVal1.setLabel("Label-1086070760");
            java.util.List<java.lang.String> _returnOptionValueVal1Value = new java.util.ArrayList<java.lang.String>();
            _returnOptionValueVal1.getValue().addAll(_returnOptionValueVal1Value);
            _returnOptionValue.add(_returnOptionValueVal1);
            _return.getOptionValue().addAll(_returnOptionValue);
            _return.setRecords("Records270510447");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new InternalServerException_Exception("InternalServerException...");
        //throw new QueryException_Exception("QueryException...");
        //throw new SessionException_Exception("SessionException...");
        //throw new AuthenticationException_Exception("AuthenticationException...");
        //throw new InvalidInputException_Exception("InvalidInputException...");
        //throw new ESTIWSException_Exception("ESTIWSException...");
    }

    /* (non-Javadoc)
     * @see com.thomsonreuters.wokmws.v3.woksearch.WokSearch#search(com.thomsonreuters.wokmws.v3.woksearch.QueryParameters  queryParameters ,)com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters  retrieveParameters )*
     */
    public com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults search(com.thomsonreuters.wokmws.v3.woksearch.QueryParameters queryParameters,com.thomsonreuters.wokmws.v3.woksearch.RetrieveParameters retrieveParameters) throws InternalServerException_Exception , QueryException_Exception , SessionException_Exception , AuthenticationException_Exception , InvalidInputException_Exception , ESTIWSException_Exception    { 
        LOG.info("Executing operation search");
        System.out.println(queryParameters);
        System.out.println(retrieveParameters);
        try {
            com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults _return = new com.thomsonreuters.wokmws.v3.woksearch.FullRecordSearchResults();
            _return.setQueryId("QueryId214756487");
            _return.setRecordsFound(-875097323);
            _return.setRecordsSearched(5276303692520422938l);
            _return.setParent("Parent1999353259");
            java.util.List<com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair> _returnOptionValue = new java.util.ArrayList<com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair>();
            com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair _returnOptionValueVal1 = new com.thomsonreuters.wokmws.v3.woksearch.LabelValuesPair();
            _returnOptionValueVal1.setLabel("Label1004268366");
            java.util.List<java.lang.String> _returnOptionValueVal1Value = new java.util.ArrayList<java.lang.String>();
            _returnOptionValueVal1.getValue().addAll(_returnOptionValueVal1Value);
            _returnOptionValue.add(_returnOptionValueVal1);
            _return.getOptionValue().addAll(_returnOptionValue);
            _return.setRecords("Records648649074");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new InternalServerException_Exception("InternalServerException...");
        //throw new QueryException_Exception("QueryException...");
        //throw new SessionException_Exception("SessionException...");
        //throw new AuthenticationException_Exception("AuthenticationException...");
        //throw new InvalidInputException_Exception("InvalidInputException...");
        //throw new ESTIWSException_Exception("ESTIWSException...");
    }

}
