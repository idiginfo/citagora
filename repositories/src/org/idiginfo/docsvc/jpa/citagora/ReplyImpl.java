package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Reply;

@Entity(name = "replies")
@DiscriminatorValue(value = "reply")
public class ReplyImpl extends CommentImpl implements Reply {

    @ManyToOne(targetEntity = CommentImpl.class, cascade = CascadeType.ALL)
    Comment replyTarget;

    public ReplyImpl() {
	setType(Comment.TYPE);
	setCollection(Comment.COLLECTION);
	// initId();
    }

    public String getType() {
	return Comment.TYPE;
    }

    @Override
    public void setTarget(CitagoraObject target) {
	if (target instanceof Comment)
	    this.replyTarget = (Comment) target;
    }

}
