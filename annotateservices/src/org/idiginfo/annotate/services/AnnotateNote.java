package org.idiginfo.annotate.services;

public class AnnotateNote {

	String pageurl;
	String type;// "note",
	String pagetitle;
	String context;
	String subject;
	String notetext;// "this is the text of the note itself",
	String author; // userid of the note author (an email)
	String signed; // public signature for note
	String date; // time note written in GMT
	String tags;// tags when first created
	String match; // where note is attached (page 1, from word #8 to word #10)
	String num; // num of this note
	String gid; // ID of this note
	String color; // index of the background color of note
	String mark; // "h" highlight, "s" strikethrough, "i" insert, "l" link
	String state; // "dead" for deleted notes
	String finalTags; // latest version of tags (maybe modified by replies)
	AnnotateNote[] replies; // {optional} list of replies to this note

}
