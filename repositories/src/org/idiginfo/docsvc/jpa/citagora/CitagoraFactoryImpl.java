package org.idiginfo.docsvc.jpa.citagora;

import org.idiginfo.docsvc.model.citagora.Author;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Reply;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.model.citagora.Tag;

public class CitagoraFactoryImpl extends CitagoraFactory {

    public CitagoraFactoryImpl() {
	CitagoraFactory.factory = this;
    }

    @Override
    public Author createAuthor() {
	Author person = new PersonImpl(Author.class);
	return person;
    }

    @Override
    public Author createAuthor(int key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public CitagoraAgent createCitagoraAgent() {
	CitagoraAgent agent = new PersonImpl(CitagoraAgent.class);
	return agent;
    }

    @Override
    public CitagoraAgent createCitagoraAgent(int key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public CitagoraDocument createCitagoraDocument() {
	return new CitagoraDocumentImpl();
    }

    @Override
    public CitagoraDocument createCitagoraDocument(int key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Comment createComment() {
	return new CommentImpl();
    }

    @Override
    public Comment createComment(int key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Person createPerson() {
	return new PersonImpl();
    }

    @Override
    public Person createPerson(int key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Person createPerson(Class subclass) {
	return new PersonImpl(subclass);
    }

    @Override
    public RatingType createRatingType(String type) {
	return new RatingType(type);
    }

    @Override
    public RatingType createRatingType(int key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Reference createReference() {
	return new ReferenceImpl();
    }

    @Override
    public Reference createReference(int key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Reply createReply() {
	return new ReplyImpl();
    }

    @Override
    public Reply createReply(int key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Review createReview() {
	return new ReviewImpl();
    }

    @Override
    public Review createReview(int key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Tag createTag() {
	return new TagImpl();
    }

    @Override
    public Tag createTag(int key) {
	// TODO Auto-generated method stub
	return null;
    }

}
