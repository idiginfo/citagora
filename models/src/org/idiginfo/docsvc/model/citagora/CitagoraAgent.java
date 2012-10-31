package org.idiginfo.docsvc.model.citagora;

import java.util.List;

public interface CitagoraAgent extends Person {
    String TYPE = "citagora:agent";

    List<CitagoraObject> getAgentObjects();

    List<Comment> getAgentComments();

    List<Tag> getAgentTags();

    List<Reference> getAgentReferences();

    void addAgentDocument(Container document);
}
