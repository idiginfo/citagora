package org.idiginfo.docsvc.model.vocabulary;

import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from http://www.w3.org/ns/prov# 
 * @author Auto-generated by schemagen on 18 Sep 2012
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
    
    /** <p>An object property to express the accountability of an agent towards another 
     *  agent. The subordinate agent acted on behalf of the responsible agent in an 
     *  actual activity.</p>
     */
    public static final Property actedOnBehalfOf = m_model.createProperty( "http://www.w3.org/ns/prov#actedOnBehalfOf" );
    
    /** <p>The property used by an prov:ActivityInfluence to cite the prov:Activity that 
     *  influenced an Entity, Activity, or Agent. It can be used to refer to the activity 
     *  involved in generating an entity, informing another activity, or starting 
     *  another activity.</p>
     */
    public static final Property activity = m_model.createProperty( "http://www.w3.org/ns/prov#activity" );
    
    /** <p>The property used by a prov:AgentInfluence to cite the Agent that influenced 
     *  an Entity, Activity, or Agent. It can be used to express the agent involved 
     *  in being responsible for an activity, being attributed to an entity, starting 
     *  or ending an activity, or being responsible for another subordinate agent 
     *  in an activity.</p>
     */
    public static final Property agent = m_model.createProperty( "http://www.w3.org/ns/prov#agent" );
    
    public static final Property alternateOf = m_model.createProperty( "http://www.w3.org/ns/prov#alternateOf" );
    
    public static final Property aq = m_model.createProperty( "http://www.w3.org/ns/prov#aq" );
    
    /** <p>The prov:mentionOf and prov:asInBundle properties are "at risk" (http://www.w3.org/2005/10/Process-20051014/tr#cfi) 
     *  and may be removed from this specification based on feedback. Please send 
     *  feedback to public-prov-comments@w3.org. These two properties are used to 
     *  encode the PROV-DM's Mention construct (http://dvcs.w3.org/hg/prov/raw-file/default/model/prov-dm.html#term-mention), 
     *  which might be removed from PROV if implementation experience reveals problems 
     *  with supporting this construct.When :x prov:mentionOf :y and :y is described 
     *  in Bundle :b, the triple :x prov:asInBundle :b is also asserted to cite the 
     *  Bundle in which :y was described.</p>
     */
    public static final Property asInBundle = m_model.createProperty( "http://www.w3.org/ns/prov#asInBundle" );
    
    /** <p>The Location of any resource.</p> */
    public static final Property atLocation = m_model.createProperty( "http://www.w3.org/ns/prov#atLocation" );
    
    /** <p>The time at which an InstantaneousEvent occurred, in the form of xsd:dateTime.</p> */
    public static final Property atTime = m_model.createProperty( "http://www.w3.org/ns/prov#atTime" );
    
    /** <p>Classify prov-o terms into three categories, including 'starting-point', 'qualifed', 
     *  and 'extended'. This classification is used by the prov-o html document to 
     *  gently introduce prov-o terms to its users.</p>
     */
    public static final Property category = m_model.createProperty( "http://www.w3.org/ns/prov#category" );
    
    /** <p>Classify prov-o terms into six components according to prov-dm, including 
     *  'agents-responsibility', 'alternate', 'annotations', 'collections', 'derivations', 
     *  and 'entities-activities'. This classification is used so that readers of 
     *  prov-o specification can find its correspondence with the prov-dm specification.</p>
     */
    public static final Property component = m_model.createProperty( "http://www.w3.org/ns/prov#component" );
    
    /** <p>A reference to the principal section of the PROV-CONSTRAINTS document that 
     *  describes this concept.</p>
     */
    public static final Property constraints = m_model.createProperty( "http://www.w3.org/ns/prov#constraints" );
    
    /** <p>A definition quoted from PROV-DM or PROV-CONSTRAINTS that describes the concept 
     *  expressed with this OWL term.</p>
     */
    public static final Property definition = m_model.createProperty( "http://www.w3.org/ns/prov#definition" );
    
    /** <p>A reference to the principal section of the PROV-DM document that describes 
     *  this concept.</p>
     */
    public static final Property dm = m_model.createProperty( "http://www.w3.org/ns/prov#dm" );
    
    /** <p>A note by the OWL development team about how this term expresses the PROV-DM 
     *  concept, or how it should be used in context of semantic web or linked data.</p>
     */
    public static final Property editorialNote = m_model.createProperty( "http://www.w3.org/ns/prov#editorialNote" );
    
    /** <p>When the prov-o term does not have a definition drawn from prov-dm, and the 
     *  prov-o editor provides one.</p>
     */
    public static final Property editorsDefinition = m_model.createProperty( "http://www.w3.org/ns/prov#editorsDefinition" );
    
    /** <p>The time at which an activity ended. See also prov:startedAtTime.</p> */
    public static final Property endedAtTime = m_model.createProperty( "http://www.w3.org/ns/prov#endedAtTime" );
    
    /** <p>The property used by an prov:EntityInfluence to cite the Entity that was influenced 
     *  by an Entity, Activity, or Agent. It can be used to refer to the entity involved 
     *  in deriving another entity, being quoted or revised from, being the source 
     *  of another entity, or being used in an activity.</p>
     */
    public static final Property entity = m_model.createProperty( "http://www.w3.org/ns/prov#entity" );
    
    public static final Property generated = m_model.createProperty( "http://www.w3.org/ns/prov#generated" );
    
    /** <p>The time at which an entity was completely created and is available for use.</p> */
    public static final Property generatedAtTime = m_model.createProperty( "http://www.w3.org/ns/prov#generatedAtTime" );
    
    /** <p>The _optional_ Activity of an Influence, which used, generated, invalidated, 
     *  or was the responsibility of some Entity. This property is _not_ used by ActivityInfluence 
     *  (use prov:activity instead).</p>
     */
    public static final Property hadActivity = m_model.createProperty( "http://www.w3.org/ns/prov#hadActivity" );
    
    /** <p>The _optional_ Generation involved in an Entity's Derivation.</p> */
    public static final Property hadGeneration = m_model.createProperty( "http://www.w3.org/ns/prov#hadGeneration" );
    
    public static final Property hadMember = m_model.createProperty( "http://www.w3.org/ns/prov#hadMember" );
    
    /** <p>The _optional_ Plan adopted by an Agent in Association with some Activity. 
     *  Plan specifications are out of the scope of this specification.</p>
     */
    public static final Property hadPlan = m_model.createProperty( "http://www.w3.org/ns/prov#hadPlan" );
    
    public static final Property hadPrimarySource = m_model.createProperty( "http://www.w3.org/ns/prov#hadPrimarySource" );
    
    /** <p>The _optional_ Role that an Entity assumed in the context of an Activity. 
     *  For example, :baking prov:used :spoon; prov:qualified [ a prov:Usage; prov:entity 
     *  :spoon; prov:hadRole roles:mixing_implement ].</p>
     */
    public static final Property hadRole = m_model.createProperty( "http://www.w3.org/ns/prov#hadRole" );
    
    /** <p>The _optional_ Usage involved in an Entity's Derivation.</p> */
    public static final Property hadUsage = m_model.createProperty( "http://www.w3.org/ns/prov#hadUsage" );
    
    public static final Property influenced = m_model.createProperty( "http://www.w3.org/ns/prov#influenced" );
    
    /** <p>Subproperties of prov:influencer are used to cite the object of an unqualified 
     *  PROV-O triple whose predicate is a subproperty of prov:wasInfluencedBy (e.g. 
     *  prov:used, prov:wasGeneratedBy). prov:influencer is used much like rdf:object 
     *  is used.</p>
     */
    public static final Property influencer = m_model.createProperty( "http://www.w3.org/ns/prov#influencer" );
    
    public static final Property invalidated = m_model.createProperty( "http://www.w3.org/ns/prov#invalidated" );
    
    /** <p>The time at which an entity was invalidated (i.e., no longer usable).</p> */
    public static final Property invalidatedAtTime = m_model.createProperty( "http://www.w3.org/ns/prov#invalidatedAtTime" );
    
    /** <p>PROV-O does not define all property inverses. The directionalities defined 
     *  in PROV-O should be given preference over those not defined. However, if users 
     *  wish to name the inverse of a PROV-O property, the local name given by prov:inverse 
     *  should be used.</p>
     */
    public static final Property inverse = m_model.createProperty( "http://www.w3.org/ns/prov#inverse" );
    
    /** <p>The prov:mentionOf and prov:asInBundle properties are "at risk" (http://www.w3.org/2005/10/Process-20051014/tr#cfi) 
     *  and may be removed from this specification based on feedback. Please send 
     *  feedback to public-prov-comments@w3.org. These two properties are used to 
     *  encode the PROV-DM's Mention construct (http://dvcs.w3.org/hg/prov/raw-file/default/model/prov-dm.html#term-mention), 
     *  which might be removed from PROV if implementation experience reveals problems 
     *  with supporting this construct.prov:asInBundle is used to cite the Bundle 
     *  in which the generalization was mentioned.When :x prov:mentionOf :y and :y 
     *  is described in Bundle :b, the triple :x prov:asInBundle :b is also asserted 
     *  to cite the Bundle in which :y was described.</p>
     */
    public static final Property mentionOf = m_model.createProperty( "http://www.w3.org/ns/prov#mentionOf" );
    
    /** <p>A reference to the principal section of the PROV-M document that describes 
     *  this concept.</p>
     */
    public static final Property n = m_model.createProperty( "http://www.w3.org/ns/prov#n" );
    
    /** <p>If this Activity prov:wasAssociatedWith Agent :ag, then it can qualify the 
     *  Association using prov:qualifiedAssociation [ a prov:Association; prov:agent 
     *  :ag; :foo :bar ].</p>
     */
    public static final Property qualifiedAssociation = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedAssociation" );
    
    /** <p>If this Entity prov:wasAttributedTo Agent :ag, then it can qualify how it 
     *  was using prov:qualifiedAttribution [ a prov:Attribution; prov:agent :ag; 
     *  :foo :bar ].</p>
     */
    public static final Property qualifiedAttribution = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedAttribution" );
    
    /** <p>If this Activity prov:wasInformedBy Activity :a, then it can qualify how it 
     *  was Inform[ed] using prov:qualifiedCommunication [ a prov:Communication; prov:activity 
     *  :a; :foo :bar ].</p>
     */
    public static final Property qualifiedCommunication = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedCommunication" );
    
    /** <p>If this Agent prov:actedOnBehalfOf Agent :ag, then it can qualify how with 
     *  prov:qualifiedResponsibility [ a prov:Responsibility; prov:agent :ag; :foo 
     *  :bar ].</p>
     */
    public static final Property qualifiedDelegation = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedDelegation" );
    
    /** <p>If this Entity prov:wasDerivedFrom Entity :e, then it can qualify how it was 
     *  derived using prov:qualifiedDerivation [ a prov:Derivation; prov:entity :e; 
     *  :foo :bar ].</p>
     */
    public static final Property qualifiedDerivation = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedDerivation" );
    
    /** <p>If this Activity prov:wasEndedBy Entity :e1, then it can qualify how it was 
     *  ended using prov:qualifiedEnd [ a prov:End; prov:entity :e1; :foo :bar ].</p>
     */
    public static final Property qualifiedEnd = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedEnd" );
    
    /** <p>This annotation property links a subproperty of prov:wasInfluencedBy with 
     *  the subclass of prov:Influence and the qualifying property that are used to 
     *  qualify it. Example annotation: prov:wasGeneratedBy prov:qualifiedForm prov:qualifiedGeneration, 
     *  prov:Generation . Then this unqualified assertion: :entity1 prov:wasGeneratedBy 
     *  :activity1 . can be qualified by adding: :entity1 prov:qualifiedGeneration 
     *  :entity1Gen . :entity1Gen a prov:Generation, prov:Influence; prov:activity 
     *  :activity1; :customValue 1337 . Note how the value of the unqualified influence 
     *  (prov:wasGeneratedBy :activity1) is mirrored as the value of the prov:activity 
     *  (or prov:entity, or prov:agent) property on the influence class.</p>
     */
    public static final Property qualifiedForm = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedForm" );
    
    /** <p>If this Activity prov:generated Entity :e, then it can qualify how it did 
     *  performed the Generation using prov:qualifiedGeneration [ a prov:Generation; 
     *  prov:entity :e; :foo :bar ].</p>
     */
    public static final Property qualifiedGeneration = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedGeneration" );
    
    /** <p>Because prov:qualifiedInfluence is a broad relation, the more specific relations 
     *  (qualifiedCommunication, qualifiedDelegation, qualifiedEnd, etc.) should be 
     *  used when applicable.</p>
     */
    public static final Property qualifiedInfluence = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedInfluence" );
    
    /** <p>If this Entity prov:wasInvalidatedBy Activity :a, then it can qualify how 
     *  it was invalidated using prov:qualifiedInvalidation [ a prov:Invalidation; 
     *  prov:activity :a; :foo :bar ].</p>
     */
    public static final Property qualifiedInvalidation = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedInvalidation" );
    
    /** <p>If this Entity prov:wasQuotedFrom Entity :e, then it can qualify how using 
     *  prov:qualifiedQuotation [ a prov:Quotation; prov:entity :e; :foo :bar ].</p>
     */
    public static final Property qualifiedQuotation = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedQuotation" );
    
    /** <p>If this Entity prov:wasRevisionOf Entity :e, then it can qualify how it was 
     *  revised using prov:qualifiedRevision [ a prov:Revision; prov:entity :e; :foo 
     *  :bar ].</p>
     */
    public static final Property qualifiedRevision = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedRevision" );
    
    /** <p>If this Entity prov:hadOriginalSource Entity :e, then it can qualify how using 
     *  prov:qualifiedSource [ a prov:Source; prov:entity :e; :foo :bar ].</p>
     */
    public static final Property qualifiedSource = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedSource" );
    
    /** <p>If this Activity prov:wasStartedBy Entity :e1, then it can qualify how it 
     *  was started using prov:qualifiedStart [ a prov:Start; prov:entity :e1; :foo 
     *  :bar ].</p>
     */
    public static final Property qualifiedStart = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedStart" );
    
    /** <p>If this Activity prov:used Entity :e, then it can qualify how it used it using 
     *  prov:qualifiedUsage [ a prov:Usage; prov:entity :e; :foo :bar ].</p>
     */
    public static final Property qualifiedUsage = m_model.createProperty( "http://www.w3.org/ns/prov#qualifiedUsage" );
    
    public static final Property sharesDefinitionWith = m_model.createProperty( "http://www.w3.org/ns/prov#sharesDefinitionWith" );
    
    public static final Property specializationOf = m_model.createProperty( "http://www.w3.org/ns/prov#specializationOf" );
    
    /** <p>The time at which an activity started. See also prov:endedAtTime.</p> */
    public static final Property startedAtTime = m_model.createProperty( "http://www.w3.org/ns/prov#startedAtTime" );
    
    /** <p>Classes and properties used to qualify relationships are annotated with prov:unqualifiedForm 
     *  to indicate the property used to assert an unqualified provenance relation.</p>
     */
    public static final Property unqualifiedForm = m_model.createProperty( "http://www.w3.org/ns/prov#unqualifiedForm" );
    
    /** <p>A prov:Entity that was used by this prov:Activity. For example, :baking prov:used 
     *  :spoon, :egg, :oven .</p>
     */
    public static final Property used = m_model.createProperty( "http://www.w3.org/ns/prov#used" );
    
    /** <p>Provides a value for an Entity.</p> */
    public static final Property value = m_model.createProperty( "http://www.w3.org/ns/prov#value" );
    
    /** <p>An prov:Agent that had some (unspecified) responsibility for the occurrence 
     *  of this prov:Activity.</p>
     */
    public static final Property wasAssociatedWith = m_model.createProperty( "http://www.w3.org/ns/prov#wasAssociatedWith" );
    
    /** <p>Attribution is the ascribing of an entity to an agent.</p> */
    public static final Property wasAttributedTo = m_model.createProperty( "http://www.w3.org/ns/prov#wasAttributedTo" );
    
    public static final Property wasDerivedFrom = m_model.createProperty( "http://www.w3.org/ns/prov#wasDerivedFrom" );
    
    /** <p>End is when an activity is deemed to have ended. An end may refer to an entity, 
     *  known as trigger, that terminated the activity.</p>
     */
    public static final Property wasEndedBy = m_model.createProperty( "http://www.w3.org/ns/prov#wasEndedBy" );
    
    public static final Property wasGeneratedBy = m_model.createProperty( "http://www.w3.org/ns/prov#wasGeneratedBy" );
    
    /** <p>Because prov:wasInfluencedBy is a broad relation, the more specific relations 
     *  (prov:wasInformedBy, prov:actedOnBehalfOf, prov:endedBy, etc.) should be used 
     *  when applicable.</p>
     */
    public static final Property wasInfluencedBy = m_model.createProperty( "http://www.w3.org/ns/prov#wasInfluencedBy" );
    
    /** <p>An activity a2 is dependent on or informed by another activity a1, by way 
     *  of some unspecified entity that is generated by a1 and used by a2.</p>
     */
    public static final Property wasInformedBy = m_model.createProperty( "http://www.w3.org/ns/prov#wasInformedBy" );
    
    public static final Property wasInvalidatedBy = m_model.createProperty( "http://www.w3.org/ns/prov#wasInvalidatedBy" );
    
    /** <p>An entity is derived from an original entity by copying, or 'quoting', some 
     *  or all of it.</p>
     */
    public static final Property wasQuotedFrom = m_model.createProperty( "http://www.w3.org/ns/prov#wasQuotedFrom" );
    
    /** <p>A revision is a derivation that revises an entity into a revised version.</p> */
    public static final Property wasRevisionOf = m_model.createProperty( "http://www.w3.org/ns/prov#wasRevisionOf" );
    
    /** <p>Start is when an activity is deemed to have started. A start may refer to 
     *  an entity, known as trigger, that initiated the activity.</p>
     */
    public static final Property wasStartedBy = m_model.createProperty( "http://www.w3.org/ns/prov#wasStartedBy" );
    
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
