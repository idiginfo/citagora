package org.idiginfo.docsvc.view.rdf.vocabulary;
 
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

 
/**
 * Vocabulary definitions from http://www.w3.org/TR/prov-o/prov-20120724.owl 
 * @author Auto-generated by schemagen on 10 Sep 2012 
 */
public class Provenance {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://www.w3.org/ns/prov#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    public static final Resource Activity = m_model.createResource( "http://www.w3.org/ns/prov#Activity" );
    
    /** <p>ActivityInfluence is intended to be a general subclass of Influence of an 
     *  Activity. It is a superclass for more specific kinds of Influences (e.g. Generation, 
     *  Communication, and Invalidation).It is not recommended that the type ActivityInfluence 
     *  be asserted without also asserting one of its more specific subclasses.</p>
     */
    public static final Resource ActivityInfluence = m_model.createResource( "http://www.w3.org/ns/prov#ActivityInfluence" );
    
    public static final Resource Agent = m_model.createResource( "http://www.w3.org/ns/prov#Agent" );
    
    /** <p>AgentInfluence is intended to be a general subclass of Influence of an Agenty. 
     *  It is a superclass for more specific kinds of Influences (e.g. Association, 
     *  Attribution, Delegation).It is not recommended that the type AgentInfluence 
     *  be asserted without also asserting one of its more specific subclasses.</p>
     */
    public static final Resource AgentInfluence = m_model.createResource( "http://www.w3.org/ns/prov#AgentInfluence" );
    
    /** <p>An instance of prov:Association provides additional descriptions about the 
     *  binary prov:wasAssociatedWith relation from an prov:Activity to some prov:Agent 
     *  that had some responsiblity for it. For example, :baking prov:wasAssociatedWith 
     *  :baker; prov:qualifiedAssociation [ a prov:Association; prov:agent :baker; 
     *  :foo :bar ].</p>
     */
    public static final Resource Association = m_model.createResource( "http://www.w3.org/ns/prov#Association" );
    
    /** <p>An instance of prov:Attribution provides additional descriptions about the 
     *  binary prov:wasAttributedTo relation from an prov:Entity to some prov:Agent 
     *  that had some responsible for it. For example, :cake prov:wasAttributedTo 
     *  :baker; prov:qualifiedAttribution [ a prov:Attribution; prov:entity :baker; 
     *  :foo :bar ].</p>
     */
    public static final Resource Attribution = m_model.createResource( "http://www.w3.org/ns/prov#Attribution" );
    
    /** <p>Note that there are kinds of bundles (e.g. handwritten letters, audio recordings, 
     *  etc.) that are not expressed in PROV-O, but can be still be described by PROV-O.</p>
     */
    public static final Resource Bundle = m_model.createResource( "http://www.w3.org/ns/prov#Bundle" );
    
    public static final Resource Collection = m_model.createResource( "http://www.w3.org/ns/prov#Collection" );
    
    /** <p>An instance of prov:Communication provides additional descriptions about the 
     *  binary prov:wasInformedBy relation from an informed prov:Activity to the prov:Activity 
     *  that informed it. For example, :you_jumping_off_bridge prov:wasInformedBy 
     *  :everyone_else_jumping_off_bridge; prov:qualifiedCommunication [ a prov:Communication; 
     *  prov:activity :everyone_else_jumping_off_bridge; :foo :bar ].</p>
     */
    public static final Resource Communication = m_model.createResource( "http://www.w3.org/ns/prov#Communication" );
    
    /** <p>An instance of prov:Delegation provides additional descriptions about the 
     *  binary prov:actedOnBehalfOf relation from a performing prov:Agent to some 
     *  prov:Agent for whom it was performed. For example, :mixing prov:wasAssociatedWith 
     *  :toddler . :toddler prov:actedOnBehalfOf :mother; prov:qualifiedDelegation 
     *  [ a prov:Delegation; prov:entity :mother; :foo :bar ].</p>
     */
    public static final Resource Delegation = m_model.createResource( "http://www.w3.org/ns/prov#Delegation" );
    
    /** <p>An instance of prov:Derivation provides additional descriptions about the 
     *  binary prov:wasDerivedFrom relation from some derived prov:Entity to another 
     *  prov:Entity from which it was derived. For example, :chewed_bubble_gum prov:wasDerivedFrom 
     *  :unwrapped_bubble_gum; prov:qualifiedDerivation [ a prov:Derivation; prov:entity 
     *  :unwrapped_bubble_gum; :foo :bar ].</p>
     */
    public static final Resource Derivation = m_model.createResource( "http://www.w3.org/ns/prov#Derivation" );
    
    public static final Resource EmptyCollection = m_model.createResource( "http://www.w3.org/ns/prov#EmptyCollection" );
    
    /** <p>An instance of prov:End provides additional descriptions about the binary 
     *  prov:wasEndedBy relation from some ended prov:Activity to an prov:Entity that 
     *  ended it. For example, :ball_game prov:wasEndedBy :buzzer; prov:qualifiedEnd 
     *  [ a prov:End; prov:entity :buzzer; :foo :bar; prov:atTime '2012-03-09T08:05:08-05:00'^^xsd:dateTime 
     *  ].</p>
     */
    public static final Resource End = m_model.createResource( "http://www.w3.org/ns/prov#End" );
    
    public static final Resource Entity = m_model.createResource( "http://www.w3.org/ns/prov#Entity" );
    
    /** <p>It is not recommended that the type EntityInfluence be asserted without also 
     *  asserting one of its more specific subclasses.EntityInfluence is intended 
     *  to be a general subclass of Influence of an Entity. It is a superclass for 
     *  more specific kinds of Influences (e.g. Usage, Derivation, Source).</p>
     */
    public static final Resource EntityInfluence = m_model.createResource( "http://www.w3.org/ns/prov#EntityInfluence" );
    
    /** <p>An instance of prov:Generation provides additional descriptions about the 
     *  binary prov:wasGeneratedBy relation from a generated prov:Entity to the prov:Activity 
     *  that generated it. For example, :cake prov:wasGeneratedBy :baking; prov:qualifiedGeneration 
     *  [ a prov:Generation; prov:activity :baking; :foo :bar ].</p>
     */
    public static final Resource Generation = m_model.createResource( "http://www.w3.org/ns/prov#Generation" );
    
    /** <p>An instance of prov:Influence provides additional descriptions about the binary 
     *  prov:wasInfluencedBy relation from some influenced Activity, Entity, or Agent 
     *  to the influencing Activity, Entity, or Agent. For example, :stomach_ache 
     *  prov:wasInfluencedBy :spoon; prov:qualifiedInfluence [ a prov:Influence; prov:entity 
     *  :spoon; :foo :bar ] . Because prov:Influence is a broad relation, the more 
     *  specific relations (Communication, Delegation, End, etc.) should be used when 
     *  applicable.</p>
     */
    public static final Resource Influence = m_model.createResource( "http://www.w3.org/ns/prov#Influence" );
    
    /** <p>An instantaneous event, or event for short, happens in the world and marks 
     *  a change in the world, in its activities and in its entities. The term 'event' 
     *  is commonly used in process algebra with a similar meaning. Events represent 
     *  communications or interactions; they are assumed to be atomic and instantaneous.</p>
     */
    public static final Resource InstantaneousEvent = m_model.createResource( "http://www.w3.org/ns/prov#InstantaneousEvent" );
    
    /** <p>An instance of prov:Invalidation provides additional descriptions about the 
     *  binary prov:wasInvalidatedBy relation from an invalidated prov:Entity to the 
     *  prov:Activity that invalidated it. For example, :uncracked_egg prov:wasInvalidatedBy 
     *  :baking; prov:qualifiedInvalidation [ a prov:Invalidation; prov:activity :baking; 
     *  :foo :bar ].</p>
     */
    public static final Resource Invalidation = m_model.createResource( "http://www.w3.org/ns/prov#Invalidation" );
    
    public static final Resource Location = m_model.createResource( "http://www.w3.org/ns/prov#Location" );
    
    public static final Resource Organization = m_model.createResource( "http://www.w3.org/ns/prov#Organization" );
    
    public static final Resource Person = m_model.createResource( "http://www.w3.org/ns/prov#Person" );
    
    /** <p>There exist no prescriptive requirement on the nature of plans, their representation, 
     *  the actions or steps they consist of, or their intended goals. Since plans 
     *  may evolve over time, it may become necessary to track their provenance, so 
     *  plans themselves are entities. Representing the plan explicitly in the provenance 
     *  can be useful for various tasks: for example, to validate the execution as 
     *  represented in the provenance record, to manage expectation failures, or to 
     *  provide explanations.</p>
     */
    public static final Resource Plan = m_model.createResource( "http://www.w3.org/ns/prov#Plan" );
    
    /** <p>An instance of prov:Quotation provides additional descriptions about the binary 
     *  prov:wasQuotedFrom relation from some taken prov:Entity from an earlier, larger 
     *  prov:Entity. For example, :here_is_looking_at_you_kid prov:wasQuotedFrom :casablanca_script; 
     *  prov:qualifiedQuotation [ a prov:Quotation; prov:entity :casablanca_script; 
     *  :foo :bar ].</p>
     */
    public static final Resource Quotation = m_model.createResource( "http://www.w3.org/ns/prov#Quotation" );
    
    /** <p>An instance of prov:Revision provides additional descriptions about the binary 
     *  prov:wasRevisionOf relation from some newer prov:Entity to an earlier prov:Entity. 
     *  For example, :draft_2 prov:wasRevisionOf :draft_1; prov:qualifiedRevision 
     *  [ a prov:Revision; prov:entity :draft_1; :foo :bar ].</p>
     */
    public static final Resource Revision = m_model.createResource( "http://www.w3.org/ns/prov#Revision" );
    
    public static final Resource Role = m_model.createResource( "http://www.w3.org/ns/prov#Role" );
    
    public static final Resource SoftwareAgent = m_model.createResource( "http://www.w3.org/ns/prov#SoftwareAgent" );
    
    /** <p>An instance of prov:Source provides additional descriptions about the binary 
     *  prov:hadPrimarySource relation from some secondary prov:Entity to an earlier, 
     *  primary prov:Entity. For example, :blog prov:hadPrimarySource :newsArticle; 
     *  prov:qualified [ a prov:Source; prov:entity :newsArticle; :foo :bar ] .</p>
     */
    public static final Resource Source = m_model.createResource( "http://www.w3.org/ns/prov#Source" );
    
    /** <p>An instance of prov:Start provides additional descriptions about the binary 
     *  prov:wasStartedBy relation from some started prov:Activity to an prov:Entity 
     *  that started it. For example, :foot_race prov:wasStartedBy :bang; prov:qualifiedStart 
     *  [ a prov:Start; prov:entity :bang; :foo :bar; prov:atTime '2012-03-09T08:05:08-05:00'^^xsd:dateTime 
     *  ] .</p>
     */
    public static final Resource Start = m_model.createResource( "http://www.w3.org/ns/prov#Start" );
    
    /** <p>An instance of prov:Usage provides additional descriptions about the binary 
     *  prov:used relation from some prov:Activity to an prov:Entity that it used. 
     *  For example, :keynote prov:used :podium; prov:qualifiedUsage [ a prov:Usage; 
     *  prov:entity :podium; :foo :bar ].</p>
     */
    public static final Resource Usage = m_model.createResource( "http://www.w3.org/ns/prov#Usage" );
    
}