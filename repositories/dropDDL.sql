ALTER TABLE citagora_objects DROP FOREIGN KEY FK_citagora_objects_GENERATOR_MYID
ALTER TABLE annotations DROP FOREIGN KEY FK_annotations_ANNOTATOR_MYID
ALTER TABLE annotations DROP FOREIGN KEY FK_annotations_MYID
ALTER TABLE comments DROP FOREIGN KEY FK_comments_MYID
ALTER TABLE comments DROP FOREIGN KEY FK_comments_REVIEWER_MYID
ALTER TABLE comments DROP FOREIGN KEY FK_comments_TARGET_MYID
ALTER TABLE containers DROP FOREIGN KEY FK_containers_MYID
ALTER TABLE containers DROP FOREIGN KEY FK_containers_ISABOUT_MYID
ALTER TABLE harvest_results DROP FOREIGN KEY FK_harvest_results_REFERENCE_MYID
ALTER TABLE citagora_references DROP FOREIGN KEY FK_citagora_references_isPartOf
ALTER TABLE citagora_references DROP FOREIGN KEY FK_citagora_references_CONTRIBUTEDBY_MYID
ALTER TABLE citagora_references DROP FOREIGN KEY FK_citagora_references_MYID
ALTER TABLE citagora_references DROP KEY UNQ_citagora_references_0
ALTER TABLE citagora_references DROP KEY UNQ_citagora_references_1
ALTER TABLE replies DROP FOREIGN KEY FK_replies_MYID
ALTER TABLE replies DROP FOREIGN KEY FK_replies_REPLYTARGET_MYID
ALTER TABLE reviews DROP FOREIGN KEY FK_reviews_MYID
ALTER TABLE reviews DROP FOREIGN KEY FK_reviews_REVIEWER_MYID
ALTER TABLE reviews DROP FOREIGN KEY FK_reviews_DOCUMENTREVIEWED_MYID
ALTER TABLE tags DROP FOREIGN KEY FK_tags_MYID
ALTER TABLE tags DROP FOREIGN KEY FK_tags_TARGET_MYID
ALTER TABLE reference_authors DROP FOREIGN KEY FK_reference_authors_authorReferences_MYID
ALTER TABLE reference_authors DROP FOREIGN KEY FK_reference_authors_authorList_MYID
ALTER TABLE reference_citations DROP FOREIGN KEY FK_reference_citations_isCitedBy_MYID
ALTER TABLE reference_citations DROP FOREIGN KEY FK_reference_citations_citationList_MYID
DROP TABLE citagora_objects
DROP TABLE annotations
DROP TABLE comments
DROP TABLE containers
DROP TABLE harvest_results
DROP TABLE people
DROP TABLE citagora_references
DROP TABLE replies
DROP TABLE reviews
DROP TABLE tags
DROP TABLE reference_authors
DROP TABLE reference_citations
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN_TABLE'
