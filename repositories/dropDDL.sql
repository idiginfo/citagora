ALTER TABLE citagora_objects DROP FOREIGN KEY FK_citagora_objects_GENERATOR_MYID
ALTER TABLE CITAGORA_REFERENCES DROP FOREIGN KEY FK_CITAGORA_REFERENCES_MYID
ALTER TABLE CITAGORA_REFERENCES DROP FOREIGN KEY FK_CITAGORA_REFERENCES_isPartOf
ALTER TABLE CITAGORA_REFERENCES DROP FOREIGN KEY FK_CITAGORA_REFERENCES_CONTRIBUTEDBY_MYID
ALTER TABLE citagora.citagora_documents DROP FOREIGN KEY FK_citagora_documents_ISABOUT_MYID
ALTER TABLE citagora.citagora_documents DROP FOREIGN KEY FK_citagora_documents_MYID
ALTER TABLE citagora.annotations DROP FOREIGN KEY FK_annotations_ANNOTATOR_MYID
ALTER TABLE citagora.annotations DROP FOREIGN KEY FK_annotations_MYID
ALTER TABLE COMMENTS DROP FOREIGN KEY FK_COMMENTS_REVIEWER_MYID
ALTER TABLE COMMENTS DROP FOREIGN KEY FK_COMMENTS_MYID
ALTER TABLE COMMENTS DROP FOREIGN KEY FK_COMMENTS_TARGET_MYID
ALTER TABLE TAGS DROP FOREIGN KEY FK_TAGS_MYID
ALTER TABLE TAGS DROP FOREIGN KEY FK_TAGS_TARGET_MYID
ALTER TABLE REPLIES DROP FOREIGN KEY FK_REPLIES_MYID
ALTER TABLE REPLIES DROP FOREIGN KEY FK_REPLIES_REPLYTARGET_MYID
ALTER TABLE REVIEWS DROP FOREIGN KEY FK_REVIEWS_REVIEWER_MYID
ALTER TABLE REVIEWS DROP FOREIGN KEY FK_REVIEWS_DOCUMENTREVIEWED_MYID
ALTER TABLE REVIEWS DROP FOREIGN KEY FK_REVIEWS_MYID
ALTER TABLE reference_authors DROP FOREIGN KEY FK_reference_authors_authorReferences_MYID
ALTER TABLE reference_authors DROP FOREIGN KEY FK_reference_authors_authorList_MYID
ALTER TABLE reference_citations DROP FOREIGN KEY FK_reference_citations_isCitedBy_MYID
ALTER TABLE reference_citations DROP FOREIGN KEY FK_reference_citations_citationList_MYID
DROP TABLE citagora_objects
DROP TABLE CITAGORA_REFERENCES
DROP TABLE citagora.citagora_documents
DROP TABLE citagora.annotations
DROP TABLE COMMENTS
DROP TABLE TAGS
DROP TABLE PEOPLE
DROP TABLE REPLIES
DROP TABLE REVIEWS
DROP TABLE reference_authors
DROP TABLE reference_citations
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN_TABLE'
