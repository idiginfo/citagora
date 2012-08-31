package org.idiginfo.docsvc.svcapi.annotate.model;

import org.idiginfo.docsvc.model.model.*;

public class AnnotateNote implements Annotation {

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
	String origTags;
	String recentdate;
	Integer pagenum;

	public String getOrigTags() {
		return origTags;
	}

	public void setOrigTags(String origTags) {
		this.origTags = origTags;
	}

	public String getRecentdate() {
		return recentdate;
	}

	public void setRecentdate(String recentdate) {
		this.recentdate = recentdate;
	}

	public Integer getPagenum() {
		return pagenum;
	}

	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}

	AnnotateNote[] replies; // {optional} list of replies to this note

	public static final String BASE_URL = "http://annotate.msrc.fsu.edu/php/pdfnotate.php?";

	public String getFullPageUrl() {
		// sample local:2012-06-29/TZpwu9je
		if (pageurl != null && pageurl.startsWith("local:")) {
			String[] fields = pageurl.split(":|/");
			String url = BASE_URL + "d=" + fields[1] + "&c=" + fields[2]
					+ "#page" + pagenum + "_note" + gid;
			return url;
		}
		return pageurl;
	}

	public String getPageurl() {
		return pageurl;
	}

	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPagetitle() {
		return pagetitle;
	}

	public void setPagetitle(String pagetitle) {
		this.pagetitle = pagetitle;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getNotetext() {
		return notetext;
	}

	public void setNotetext(String notetext) {
		this.notetext = notetext;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSigned() {
		return signed;
	}

	public void setSigned(String signed) {
		this.signed = signed;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFinalTags() {
		return finalTags;
	}

	public void setFinalTags(String finalTags) {
		this.finalTags = finalTags;
	}

	public AnnotateNote[] getReplies() {
		return replies;
	}

	public void setReplies(AnnotateNote[] replies) {
		this.replies = replies;
	}

}
