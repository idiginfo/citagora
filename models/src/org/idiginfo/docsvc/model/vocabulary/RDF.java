package org.idiginfo.docsvc.model.vocabulary;
 
import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from http://www.w3.org/1999/02/22-rdf-syntax-ns# 
 * @author Auto-generated by schemagen on 03 Oct 2012
 */
public class RDF {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    public static final Property description = m_model.createProperty( "http://purl.org/dc/elements/1.1/description" );
    
    public static final Property title = m_model.createProperty( "http://purl.org/dc/elements/1.1/title" );
    
    /** <p>The first item in the subject RDF list.</p> */
    public static final Property first = m_model.createProperty( "http://www.w3.org/1999/02/22-rdf-syntax-ns#first" );
    
    /** <p>The object of the subject RDF statement.</p> */
    public static final Property object = m_model.createProperty( "http://www.w3.org/1999/02/22-rdf-syntax-ns#object" );
    
    /** <p>The predicate of the subject RDF statement.</p> */
    public static final Property predicate = m_model.createProperty( "http://www.w3.org/1999/02/22-rdf-syntax-ns#predicate" );
    
    /** <p>The rest of the subject RDF list after the first item.</p> */
    public static final Property rest = m_model.createProperty( "http://www.w3.org/1999/02/22-rdf-syntax-ns#rest" );
    
    /** <p>The subject of the subject RDF statement.</p> */
    public static final Property subject = m_model.createProperty( "http://www.w3.org/1999/02/22-rdf-syntax-ns#subject" );
    
    /** <p>The subject is an instance of a class.</p> */
    public static final Property type = m_model.createProperty( "http://www.w3.org/1999/02/22-rdf-syntax-ns#type" );
    
    /** <p>Idiomatic property used for structured values.</p> */
    public static final Property value = m_model.createProperty( "http://www.w3.org/1999/02/22-rdf-syntax-ns#value" );
    
    public static final Property comment = m_model.createProperty( "http://www.w3.org/2000/01/rdf-schema#comment" );
    
    public static final Property domain = m_model.createProperty( "http://www.w3.org/2000/01/rdf-schema#domain" );
    
    public static final Property isDefinedBy = m_model.createProperty( "http://www.w3.org/2000/01/rdf-schema#isDefinedBy" );
    
    public static final Property label = m_model.createProperty( "http://www.w3.org/2000/01/rdf-schema#label" );
    
    public static final Property range = m_model.createProperty( "http://www.w3.org/2000/01/rdf-schema#range" );
    
    public static final Property seeAlso = m_model.createProperty( "http://www.w3.org/2000/01/rdf-schema#seeAlso" );
    
    public static final Property subClassOf = m_model.createProperty( "http://www.w3.org/2000/01/rdf-schema#subClassOf" );
    
    public static final Property subPropertyOf = m_model.createProperty( "http://www.w3.org/2000/01/rdf-schema#subPropertyOf" );
    
    public static final Property backwardCompatibleWith = m_model.createProperty( "http://www.w3.org/2002/07/owl#backwardCompatibleWith" );
    
    public static final Property differentFrom = m_model.createProperty( "http://www.w3.org/2002/07/owl#differentFrom" );
    
    public static final Property disjointWith = m_model.createProperty( "http://www.w3.org/2002/07/owl#disjointWith" );
    
    public static final Property equivalentClass = m_model.createProperty( "http://www.w3.org/2002/07/owl#equivalentClass" );
    
    public static final Property imports = m_model.createProperty( "http://www.w3.org/2002/07/owl#imports" );
    
    public static final Property incompatibleWith = m_model.createProperty( "http://www.w3.org/2002/07/owl#incompatibleWith" );
    
    public static final Property intersectionOf = m_model.createProperty( "http://www.w3.org/2002/07/owl#intersectionOf" );
    
    public static final Property onProperty = m_model.createProperty( "http://www.w3.org/2002/07/owl#onProperty" );
    
    public static final Property oneOf = m_model.createProperty( "http://www.w3.org/2002/07/owl#oneOf" );
    
    public static final Property priorVersion = m_model.createProperty( "http://www.w3.org/2002/07/owl#priorVersion" );
    
    public static final Property sameAs = m_model.createProperty( "http://www.w3.org/2002/07/owl#sameAs" );
    
    /** <p>The class of containers of alternatives.</p> */
    public static final Resource Alt = m_model.createResource( "http://www.w3.org/1999/02/22-rdf-syntax-ns#Alt" );
    
    /** <p>The class of unordered containers.</p> */
    public static final Resource Bag = m_model.createResource( "http://www.w3.org/1999/02/22-rdf-syntax-ns#Bag" );
    
    /** <p>The class of RDF Lists.</p> */
    public static final Resource List = m_model.createResource( "http://www.w3.org/1999/02/22-rdf-syntax-ns#List" );
    
    /** <p>The class of RDF properties.</p> */
    public static final Resource Property = m_model.createResource( "http://www.w3.org/1999/02/22-rdf-syntax-ns#Property" );
    
    /** <p>The class of ordered containers.</p> */
    public static final Resource Seq = m_model.createResource( "http://www.w3.org/1999/02/22-rdf-syntax-ns#Seq" );
    
    /** <p>The class of RDF statements.</p> */
    public static final Resource Statement = m_model.createResource( "http://www.w3.org/1999/02/22-rdf-syntax-ns#Statement" );
    
}