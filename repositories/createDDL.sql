CREATE TABLE citagora_objects (MYID INTEGER NOT NULL, DTYPE VARCHAR(31), CREATED DATETIME, GENERATED DATETIME, RIGHTS VARCHAR(1000), SOURCE VARCHAR(255), TYPE VARCHAR(255), UPDATED DATETIME, URI VARCHAR(255) UNIQUE, WASATTRIBUTEDTO VARCHAR(255), GENERATOR_MYID INTEGER, PRIMARY KEY (MYID))
CREATE TABLE annotations (MYID INTEGER NOT NULL, CONTEXT VARCHAR(2000), MODELVERSION VARCHAR(255), SPECIFIER VARCHAR(255), TAGS VARCHAR(255), CHARACTERENCODING VARCHAR(255), CHARS VARCHAR(2000), ANNOTATOR_MYID INTEGER, PRIMARY KEY (MYID))
CREATE TABLE comments (MYID INTEGER NOT NULL, ratingType VARCHAR(255), RATING INTEGER, REVIEWER_MYID INTEGER, TARGET_MYID INTEGER, PRIMARY KEY (MYID))
CREATE TABLE containers (MYID INTEGER NOT NULL, ISABOUT_MYID INTEGER, PRIMARY KEY (MYID))
CREATE TABLE people (MYID INTEGER NOT NULL, ACCOUNT VARCHAR(255), ACCOUNTNAME VARCHAR(255), CREATED DATETIME, FAMILYNAME VARCHAR(255), GIVENNAME VARCHAR(255), HOMEPAGE VARCHAR(255), ISAGENT TINYINT(1) default 0, ISAUTHOR TINYINT(1) default 0, ISPERSON TINYINT(1) default 0, NAME VARCHAR(255), TYPE VARCHAR(255), UPDATED DATETIME, URI VARCHAR(255), PRIMARY KEY (MYID))
CREATE TABLE citagora_references (MYID INTEGER NOT NULL, ABSTRACTTEXT LONGTEXT, ACCURACYRATING DOUBLE, AGGREGATIONTYPE VARCHAR(255), ARXIVID VARCHAR(255), AUTHORNOTES VARCHAR(255), AUTHORSTRING LONGTEXT, BIBOTYPE VARCHAR(255), COVERDATE VARCHAR(255), DOI VARCHAR(255) UNIQUE, EISSN VARCHAR(255), EDITION VARCHAR(255), GENRE VARCHAR(255), ISBN VARCHAR(255) UNIQUE, ISSN VARCHAR(255) UNIQUE, ISSUE VARCHAR(255), ISSUED DATETIME, ITEMNUMBER VARCHAR(255), KEYWORDS LONGTEXT, LANGUAGE VARCHAR(255), MESHTERMS VARCHAR(255), ORIGINALITYRATING DOUBLE, OVERALLRATING DOUBLE, PAGEEND INTEGER, PAGESTART INTEGER, PAGES VARCHAR(255), PMID VARCHAR(255) UNIQUE, PUBLICATIONDATE VARCHAR(255), PUBLISHER VARCHAR(255), READABILITYRATING DOUBLE, SEEALSO LONGBLOB, SERIESTITLE VARCHAR(255), SHORTTITLE VARCHAR(255), SUBJECT VARCHAR(1000), TITLE VARCHAR(1000), URL VARCHAR(255), VOLUME VARCHAR(255), CONTRIBUTEDBY_MYID INTEGER, isPartOf INTEGER, PRIMARY KEY (MYID))
CREATE TABLE replies (MYID INTEGER NOT NULL, REPLYTARGET_MYID INTEGER, PRIMARY KEY (MYID))
CREATE TABLE reviews (MYID INTEGER NOT NULL, RATING INTEGER, RATINGTYPE VARCHAR(255), TOTALVOTES INTEGER, DOCUMENTREVIEWED_MYID INTEGER, REVIEWER_MYID INTEGER, PRIMARY KEY (MYID))
CREATE TABLE tags (MYID INTEGER NOT NULL, TARGET_MYID INTEGER, PRIMARY KEY (MYID))
CREATE TABLE reference_authors (authorList_MYID INTEGER NOT NULL, authorReferences_MYID INTEGER NOT NULL, PRIMARY KEY (authorList_MYID, authorReferences_MYID))
CREATE TABLE reference_citations (isCitedBy_MYID INTEGER NOT NULL, citationList_MYID INTEGER NOT NULL, PRIMARY KEY (isCitedBy_MYID, citationList_MYID))
ALTER TABLE citagora_objects ADD CONSTRAINT FK_citagora_objects_GENERATOR_MYID FOREIGN KEY (GENERATOR_MYID) REFERENCES people (MYID)
ALTER TABLE annotations ADD CONSTRAINT FK_annotations_ANNOTATOR_MYID FOREIGN KEY (ANNOTATOR_MYID) REFERENCES people (MYID)
ALTER TABLE annotations ADD CONSTRAINT FK_annotations_MYID FOREIGN KEY (MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE comments ADD CONSTRAINT FK_comments_MYID FOREIGN KEY (MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE comments ADD CONSTRAINT FK_comments_REVIEWER_MYID FOREIGN KEY (REVIEWER_MYID) REFERENCES people (MYID)
ALTER TABLE comments ADD CONSTRAINT FK_comments_TARGET_MYID FOREIGN KEY (TARGET_MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE containers ADD CONSTRAINT FK_containers_MYID FOREIGN KEY (MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE containers ADD CONSTRAINT FK_containers_ISABOUT_MYID FOREIGN KEY (ISABOUT_MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE citagora_references ADD CONSTRAINT FK_citagora_references_isPartOf FOREIGN KEY (isPartOf) REFERENCES citagora_objects (MYID)
ALTER TABLE citagora_references ADD CONSTRAINT FK_citagora_references_CONTRIBUTEDBY_MYID FOREIGN KEY (CONTRIBUTEDBY_MYID) REFERENCES people (MYID)
ALTER TABLE citagora_references ADD CONSTRAINT FK_citagora_references_MYID FOREIGN KEY (MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE replies ADD CONSTRAINT FK_replies_MYID FOREIGN KEY (MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE replies ADD CONSTRAINT FK_replies_REPLYTARGET_MYID FOREIGN KEY (REPLYTARGET_MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE reviews ADD CONSTRAINT FK_reviews_MYID FOREIGN KEY (MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE reviews ADD CONSTRAINT FK_reviews_REVIEWER_MYID FOREIGN KEY (REVIEWER_MYID) REFERENCES people (MYID)
ALTER TABLE reviews ADD CONSTRAINT FK_reviews_DOCUMENTREVIEWED_MYID FOREIGN KEY (DOCUMENTREVIEWED_MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE tags ADD CONSTRAINT FK_tags_MYID FOREIGN KEY (MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE tags ADD CONSTRAINT FK_tags_TARGET_MYID FOREIGN KEY (TARGET_MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE reference_authors ADD CONSTRAINT FK_reference_authors_authorReferences_MYID FOREIGN KEY (authorReferences_MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE reference_authors ADD CONSTRAINT FK_reference_authors_authorList_MYID FOREIGN KEY (authorList_MYID) REFERENCES people (MYID)
ALTER TABLE reference_citations ADD CONSTRAINT FK_reference_citations_isCitedBy_MYID FOREIGN KEY (isCitedBy_MYID) REFERENCES citagora_objects (MYID)
ALTER TABLE reference_citations ADD CONSTRAINT FK_reference_citations_citationList_MYID FOREIGN KEY (citationList_MYID) REFERENCES citagora_objects (MYID)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN_TABLE', 0)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN_TABLE', 0)
