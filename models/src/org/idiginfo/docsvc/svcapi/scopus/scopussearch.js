
var sciverseURL="http://searchapi.scopus.com/";
var sciverseTimeout="40000";  
//<!-- Include sciverse.js -->
var debug = new sciverseDebug();
var sciverse = new sciverseInterface();


function sciverseInterface(){
	this._resultsValid = false;
	this._results = null;
	this._errors = null;
	this._warnings = null;
	
	this._search = null;

	this._callback = null;
	this._errorCallback = null;
	
	this.Backend = new sciverseBackend();
	this.Renderer = new sciverseListRenderer();
	
	// see here for explination of "me": 
	// http://w3future.com/html/stories/callbacks.xml
	var me = this;
	
	//apiKey and it's setter
	this._apiKey = null;
	this.setApiKey = function(apiKey){
		me._apiKey = apiKey;
	}
	
	//set the renderer (overiding the previous or default renderer)
	this.setRenderer = function(newRenderer){
		if (newRenderer != null){
			this.Renderer = newRenderer;
		}else{
			debug.write("setRenderer:no renderer specified");
		}
	}
	
	this.selectRenderer = function(renderer){
		if(renderer == "list"){
			this.Renderer = new sciverseListRenderer();
		}
	}
	
	//set the render location (passthrough to the renderer)
	this.setRenderLocation = function(idName){
		this.Renderer.setRenderLocation(idName);
	}
	
	//set the callback to be called when a search is compleate
	this.setCallback = function(callback){
		if(callback != null){
			this._callback = callback;
		}else{
			debug.write("setCallback:no callback specified");
		}
	}
	
	this.setErrorCallback = function(callback){
		if(callback != null){
			this._errorCallback = callback;
		}else{
			debug.write("setErrorCallback:no callback specified");
		}
	}
	
	//runSearch and it's callback
	this.runSearch = function(searchObj){
		me._resultsValid = false;
		me._search = searchObj;
		me.Backend.submitSearch(searchObj, me._apiKey, me.runSearchCallback);
	}
	this.runSearchCallback = function(responseObj){
		me._errors = null;
		me._warnings = null;
		me._results = null;
		me._resultsValid = false;
		if(responseObj == "timeout"){
			debug.write("runSearch: search timed out");
			if(me._errorCallback!=null){
				me._errorCallback();
			}
		}else if(responseObj.ERROR != null){
			debug.write("runSearch: error occured durring search");
			me._errors = responseObj.ERROR.Errors;
			if(me._errorCallback!=null){
				me._errorCallback();
			}
		}else if(responseObj.PartOK != null){
			debug.write("runSearch: warnings occured durring search");
			me._results = responseObj.PartOK;
			me._warnings = responseObj.PartOK.Warnings;
			me._resultsValid = true;
		}else if(responseObj.OK!= null){
			debug.write("runSearch: search results are OK");
			me._results = responseObj.OK;
			me._resultsValid = true;
		}
		if(me._callback!=null){
			me._callback();
		}else{
			debug.write("runSearch:no callback specified");
		}
	}
	
	//search and it's callback
	this.search = function(searchObj){
		me._resultsValid = false;
		me._search = searchObj;
		me.Backend.submitSearch(searchObj, me._apiKey, me.searchCallback);
	}
	this.searchCallback = function(responseObj){
		me._errors = null;
		me._warnings = null;
		me._results = null;
		me._resultsValid = false;
		if(responseObj == "timeout"){
			debug.write("runSearch: search timed out");
			if(me._errorCallback!=null){
				me._errorCallback();
			}
		}if(responseObj.ERROR != null){
			debug.write("search: error occured durring search");
			me.Renderer.renderErrors(responseObj.ERROR.Errors);
			if(me._errorCallback!=null){
				me._errorCallback();
			}
			me._errors = responseObj.ERROR.Errors
		}else if(responseObj.PartOK != null){
			debug.write("search: warnings occured durring search");
			me.Renderer.renderWarnings(responseObj);
			
			me._warnings = responseObj.PartOK.Warnings;
			me._results = responseObj.PartOK;
			me._resultsValid = true;
		}else if(responseObj.OK!= null){
			debug.write("search: search results are OK");
			me.Renderer.renderResults(responseObj.OK.results);
			
			me._results = responseObj.OK;
			me._resultsValid = true;
		}
		if(me._callback!=null){
			me._callback();
		}else{
			debug.write("search:no callback specified");
		}
	}
	
	this.areSearchResultsValid = function(){
		return this._resultsValid;
	}
	
	this.getSearchResults = function(){
		if(this._resultsValid){
			return this._results;
		}else{
			debug.write("getSearchResults:results are not currently valid");
		}
	}
	
	//Renderer passthrough functions
	this.renderDocument = function(position){
		this.Renderer.renderDocument(this._results, position);
	}
	this.renderField = function(position, field){
		this.Renderer.renderField(this._results, position, field);
	}
	this.getField = function(position, field){
		return this._results.results[position][field];
	}
	this.getTotalHits = function(){
		return this._results.totalResults;
	}
	this.getNumResults = function(){
		return this._results.returnedResults;
	}
	this.getPosition = function(){
		return this._results.position;
	}
	
	//get the last search request
	this.getLastSearchRequest = function(){
		return this._search;
	}
	
	//Debug option setters
	this.setDebug = function(value){
		debug.setDebug(value);
	}
	this.getDebug = function(){
		return debug.getDebug();
	}
	
}

//The debug class
function sciverseDebug(){
	this._debug = false;
	this.setDebug = function(value){
		this._debug = value;
	}
	this.getDebug = function(){
		return this._debug;
	}
	
	//if debuging is enabled writes the message to the end of the specified div tag
	this.write = function(message){
		if(this.getDebug()){
			var newElement=document.createElement("p");
			var newtext=document.createTextNode(message);
			newElement.appendChild(newtext);
			document.getElementById("sciverseDebugArea").appendChild(newElement);
		}
	}
}

//This class handles parsing the response from the server and rendering it into 
//html which is then inserted into the html page.
function sciverseListRenderer(){
	//the id of the div tag to replace the contents of
	this._renderLocation = "sciverse";
	this._sciverseURL = sciverseURL;
	
	this._contentProvidedByString = " <a href=\"http://www.scopus.com\"><img src=\""+this._sciverseURL+"images/content_provided_by.GIF\" width=\"101\" height=\"11\" border=\"0\" /><img src=\""+this._sciverseURL+"images/scopus_content.GIF\" width=\"40\" height=\"11\" border=\"0\" /></a>"
	
	this._resultsTableStart = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"4\" class=\"txt\">";
	this._resultsTableEnd 	= "<tr><td>&nbsp;</td><td>"+this._contentProvidedByString +"</tr></table>"
	
	this._errorTableStart = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"4\" class=\"txt\">";
	this._errorTableEnd 	= "</table>"
	
	this._errorRowStart1	= "<td valign=\"top\" class=\""
	this._errorRowStart2	= "\">&nbsp;</td><td valign=\"top\" class=\"";
	this._errorRowStart3 = "\" class=\"txt\">";
	
	this._documentRowStart1	= "<td valign=\"top\" width=\"11\" class=\""
	this._documentRowStart2	= "\"><img src=\""+this._sciverseURL+"images/bullet.gif\" width=\"10\" height=\"12\" /></td><td valign=\"top\" class=\"";
	this._documentRowStart3 = "\" class=\"txt\">";
	this._documentRowEnd	= "</td></tr>";
	
	this._singlefieldtitle	= " <span class=\"txtSmall\">(found in <a href=\"http://www.scopus.com\">Scopus</a>)</span>";
	this._singlefieldcited	= " in <a href=\"http://www.scopus.com\">Scopus</a>";
	
	this._white				="whitebg";
	this._grey				="greybg";
	
	this._titleStart		= "<strong>";
	this._titleEnd			= "</strong>";
	
	this._errorStart		= "<strong class=\"txtRedBold\">";
	this._errorEnd			= "</strong>";
	
	this._title				= "title";

	this._firstAuth			="firstauth";
	this._firstAuthName		="First Author";
	
	this._authlist			="authlist";
	this._authlistName		="Author(s)";
	
	this._abstract			="abstract";
	this._abstractName		="Abstract";
	
	this._affiliations		="affiliations";
	this._affiliationsName	="Affiliation"
	
	this._doctype			="doctype";
	this._doctypeName		="Document Type";
	
	this._sourcetitle		="sourcetitle";
	this._sourcetitleName	="Source title";
	
	this._issn				="issn";
	this._issnName			="ISSN";
	
	this._vol				="vol";
	this._volName			="Volume";
	
	this._issue				="issue";
	this._issueName			="Issue";
	
	this._page				="page";
	this._pageName			="Pages";
	
	this._pubdate			="pubdate";
	this._pubdateName		="Year";
	
	this._eid				="eid";
	this._eidName			="EID";
	
	this._scp				="scp";
	this._scpName			="Scopus ID";
	
	this._doi				="doi";
	this._doiName			="DOI";
	
	this._citedbycount		="citedbycount";
	this._citedbycountStart	="Cited ";
	this._citedbycountEnd	=" times";
	
	this._inwardurl			="inwardurl";
	this._inwardurlStart	="<a class=\"noDeco\" href=\"";
	this._inwardurlEndWhite		="\">View in"
	this._inwardurlEndWhite+= "&nbsp;<img style=\"vertical-align:middle\" border=0 src="+sciverseURL+"images/scopus_white_new.gif></img></a>";
	this._inwardurlEndGrey		="\">View in"
	this._inwardurlEndGrey+= "&nbsp;<img  style=\"vertical-align:bottom\" border=0 src="+sciverseURL+"images/scopus_grey_new.gif></img></a>";
	
	
	this._fieldSeperator	=": ";
	this._fieldStart		="";
	this._fieldEnd			="<br />";
	
	this._noResults			="<strong class=\"txtRedBold\">No results were found for this search</strong>";
	var orderedFields=[this._title,this._doctype,this._citedbycount,this._inwardurl,this._sourcetitle,this._issn,this._vol,this._issue,this._page,this._pubdate,this._eid,this._scp,this._doi,this._firstAuth,this._affiliations,this._abstract,this._authlist];
	
	//sets a new render location overriding the previous one
	this.setRenderLocation = function(idName){
		if(idName != null && idName != ""){
			this._renderLocation = idName;
		}else{
			debug.write("sciverseRenderer.setRenderLocation: no render location specified");
		}
	}
	
	//render any errors 
	this.renderErrors = function(errors){
		var output = this._resultsTableStart;
		var hilight = false;
		
		for (i = 0; i < errors.length; i++){
			if(hilight){
				output+= this._errorRowStart1+this._grey+this._errorRowStart2+this._grey+this._errorRowStart3;
			}else{
				output+= this._errorRowStart1+this._white+this._errorRowStart2+this._white+this._errorRowStart3;
			}
			
			error = errors[i].split("\n");
			output += this._errorStart+error[0]+this._errorEnd+this._fieldEnd;
			
			if(typeof(error[1])=='undefined'||error[1]==null||error[1]==""){
			output += "Please enter a valid input";
			}
			else{
			output += error[1];
			}
			
			
			
			output += this._documentRowEnd;
			if(hilight){
				hilight = false;
			}else{
				hilight = true;
			}
		}
		output += this._resultsTableEnd;
		this.writeString(output);
	}
	
	//render any warnings
	this.renderWarnings = function(warnings){
		//render warnings
		var output = "";
		output+= this.getAllRenderedWarningsString(warnings.Warnings);
		
		//renderresults
		output += this.getAllRenderedResultsString(warnings.PartOK.results);
		this.writeString(output);
	}
	
	this.getAllRenderedWarningsString = function(warnings){
		for (i = 0; i < warnings.length; i++){
			alert(warnings[i]);
		}
		return "";
	}
		
	//render the response
	this.renderResults = function(response){
		var output = this._resultsTableStart;
		output += this.getAllRenderedResultsString(response);
		this.writeString(output);
	}
	
	this.getAllRenderedResultsString = function(results){
		var output = this._resultsTableStart;
		var hilight = false;
		if(results.length >0){
			for( var i in results){
				output += this.getRenderedResultString(results[i], hilight);
				if(hilight){
					hilight = false;
				}else{
					hilight = true;
				}
			}
		}else{
			output += this._documentRowStart1+this._white+this._documentRowStart2+this._white+this._documentRowStart3;
			output += this._noResults;
			output += this._documentRowEnd;
		}
		output += this._resultsTableEnd;
		return output;
	}
	
	this.renderDocument = function(response, position){
		var output = this._resultsTableStart;
		output += this.getRenderedResultString(response.results[position], false);
		output += this._resultsTableEnd;
		this.writeString(output);
	}
	
	this.renderField = function(response, position, field){
		output = this.getRenderedFieldString(field, response.results[position][field], true);
		this.writeString(output);
	}
	
	this.getRenderedResultString = function(result, hilight){
		output = "";
		if(hilight){
			output+= this._documentRowStart1+this._grey+this._documentRowStart2+this._grey+this._documentRowStart3;
		}else{
			output+= this._documentRowStart1+this._white+this._documentRowStart2+this._white+this._documentRowStart3;
		}
		for (var i=0;i<=orderedFields.length;i++){
		output += this.getRenderedFieldString(orderedFields[i], result[orderedFields[i]], false,hilight);
		
		}
		/*output += this.getRenderedFieldString(this._title, result[this._title], false,hilight);
			for(var i in result){
			if(i!=this._title){
				output += this.getRenderedFieldString(i, result[i], false,hilight);
				}
			}*/
		output += this._documentRowEnd;
		return output;
	}
	
	this.getRenderedFieldString = function(field, string, single,hilight){
		output = "";
		if(typeof(string)=='undefined'||string==null||string==""){return "";}
		if(field == this._title){
			if(single){
				output += this._titleStart+string+this._singlefieldtitle+this._titleEnd;
			}else{
				output += this._titleStart+string+this._titleEnd;
			}
		}else if( field == this._doctype){
			output += this._doctypeName+this._fieldSeperator+string;
		}else if( field == this._citedbycount){
			if(single){
				output += this._citedbycountStart+string+this._citedbycountEnd;
			}else{
				output += this._citedbycountStart+string+this._citedbycountEnd;
			}
		}else if( field == this._inwardurl){
		if(hilight){
			output += this._inwardurlStart+string+this._inwardurlEndGrey;
			}
			else {
			output += this._inwardurlStart+string+this._inwardurlEndWhite;
			}
		}else if( field == this._sourcetitle){
			output += this._sourcetitleName+this._fieldSeperator+string;
		}else if( field == this._issn){
			output += this._issnName+this._fieldSeperator+string;
		}else if( field == this._vol){
			output += this._volName+this._fieldSeperator+string;
		}else if( field == this._issue){
			output += this._issueName+this._fieldSeperator+string;
		}else if( field == this._page){
			output += this._pageName+this._fieldSeperator+string;
		}else if( field == this._pubdate){
			output += this._pubdateName+this._fieldSeperator+string;
		}else if( field == this._eid){
			output += this._eidName+this._fieldSeperator+string;
		}else if( field == this._scp){
			output += this._scpName+this._fieldSeperator+string;
		}else if( field == this._doi){
			output += this._doiName+this._fieldSeperator+string;
		}else if( field == this._firstAuth){
			output += this._firstAuthName+this._fieldSeperator+string;
		}else if( field == this._authlist){
			output += this._authlistName+this._fieldSeperator+string;
		}else if( field == this._affiliations){
			output += this._affiliationsName+this._fieldSeperator+string;
		}else if( field == this._abstract){
			output += this._abstractName+this._fieldSeperator+string;
		}
		return output+this._fieldEnd;
	}
	
	this.writeString = function(output){
		document.getElementById(this._renderLocation).innerHTML = output;
	}
}

//This class handles building and submitting the search request to the server as well as
//the callback response from the server.
function sciverseBackend(){
	this._busy = false;
	this._sciverseCallback = null;
	
	this._reqCounter = 0;
	this._requests = {};
	
	//Request static vars (should be config vars in a perfect world)
	this._sciverseURL = sciverseURL+"documentSearch.url";
	this._timeoutVal = sciverseTimeout;
	
	//request field names
	this._preventCache = "preventCache";
	this._apiKey = "apiKey";
	this._search = "search";
	this._sort = "sort";
	this._sortDirection = "sortDirection";
	this._fields = "sciverseField";
	this._numResults = "numResults";
	this._content = "content";
	this._offset = "offset";
	this._callback = "callback";

	//useful callback strings
	this._callbackStringStart = "sciverse.Backend._requests.";
	this._callbackStringEnd = ".callback";
	
	//useful url chars
	this._equals = "=";
	this._connector = "&";
	this._post = "?";
	
	//functions calling this method need to provide a callback method which is
	//in the form function(response).  This is due to the fact that this is an 
	//asyncronus library so when control is returned, processing is not done.
	//When the search is complete submitSearch will call the callback function.
	this.submitSearch = function(searchObj, apiKey, callback){
		if(this._busy == true){
			debug.write("Search already in flight...ignoring search request");
			return;
		}else{
			//set busy to true
			this._busy = true;
			
			debug.write("Search submitted: query="+searchObj.getSearch());
			this._sciverseCallback = callback;
			
			var searchRequestURL = this._sciverseURL+this._post;
			
			//add in browser cacheing prevention.  This is used to change the generated URL
			//slightly each time to prevent the browser from caching a response to identical searches.
			//That's bad because if it happens the browser won't "run" the response.
			searchRequestURL = this.appendVarToURL(searchRequestURL,this._preventCache,this.randomString());
			
			//add in apiKey
			searchRequestURL = this.appendVarToURL(searchRequestURL,this._apiKey,apiKey);
			
			//add in the search
			searchRequestURL = this.appendVarToURL(searchRequestURL,this._search,searchObj.getSearch());
			
			//add in the sort
			searchRequestURL = this.appendVarToURL(searchRequestURL,this._sort,searchObj.getSort());

			//add in the sortDirection
			searchRequestURL = this.appendVarToURL(searchRequestURL,this._sortDirection,searchObj.getSortDirection());

			//add in the fields
			searchRequestURL = this.appendVarToURL(searchRequestURL,this._fields,searchObj.getFields());
			
			//add in the numResults
			searchRequestURL = this.appendVarToURL(searchRequestURL,this._numResults,searchObj.getNumResults());

			//add in the content
			searchRequestURL = this.appendVarToURL(searchRequestURL,this._content,searchObj.getContent());
			
			//add in the offset
			searchRequestURL = this.appendVarToURL(searchRequestURL,this._offset,searchObj.getOffset());
			
			//build the callback string
			var searchID = "search"+this._reqCounter++;
			var searchObjectString = this._callbackStringStart+searchID;
			var callbackString = this._callbackStringStart+searchID+this._callbackStringEnd;
			//add in callback
			searchRequestURL = this.appendVarToURL(searchRequestURL,this._callback,callbackString);
			
			//create a new inflight object
			flight = new inflight(searchID);
			//and add it to the requests array
			this._requests[searchID] = flight;
			
			//make the async call
			this.addRequestToHeader(searchRequestURL);
			//setup a timeout in case bad things happen
			setTimeout('sciverse.Backend.timeout('+searchObjectString+')', this._timeoutVal);
		}
	}
	
	//if the value is not null this appends a field and value to an URL 
	//and returns it.  Otherwise it just returns the URL
	this.appendVarToURL = function(url, field, value){
		if(value != null && value != ""){
			url += this._connector+field+this._equals+value;
			return url;
		}else{
			return url;
		}
	}
	
	//Adds an URL to the header of an HTML document in a javascript tag
	//The purpose here is to cause the browser to load the new URL asyncronusly
	this.addRequestToHeader = function(searchURL){
		var head = document.getElementsByTagName("head")[0];
 		script = document.createElement('script');
 		script.id = 'sciverseSearch';
 		script.type = 'text/javascript';
 		script.src = searchURL;
 		head.appendChild(script);
	}
	
	//Creates a random string that is 20 chars long.
	this.randomString = function() {
		var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
		var string_length = 20;
		var randomstring = '';
		for (var i=0; i<string_length; i++) {
			var rnum = Math.floor(Math.random() * chars.length);
			randomstring += chars.substring(rnum,rnum+1);
		}
		return randomstring;
	}
	
	//This method is called by the inflight object when the response is returned.  At
	//that time it will handle reseting the state of the backend to idle, and the state 
	//of the request to complete, and call the callback function.
	this.callback = function(search, response){
		debug.write("Results recieved by callback...");
		if(search.inflight == false){
			debug.write("...Javascript just recieved a response for a search that had timed out.");
		}else{
			search.inflight = false;
			debug.write("...looking good");
			
			
			this._busy = false;
			this._sciverseCallback(response);
		}
		
	}
	
	//This method is called when the timer for a search runs down.  If the search is still active
	//at that time it resets the state of the backend to idle, marks the request as complete(timedout)
	this.timeout = function(search){
		debug.write("Checking for timedout search...");
		if(search.inflight == true){
			debug.write("...Search was timed out");
			search.inflight = false;
			this._busy = false;
			this._sciverseCallback("timeout");
		}else{
			debug.write("...search had already compleated");
		}
		//timeout code
	}
}

//This class represents any request that has been submitted to the server
//and contains an id, a status, and a callback function that is called by the 
//response from the server
function inflight(id){
	this.searchID = id;
	this.inflight = true;
	this.callback = function(response){
		sciverse.Backend.callback(this,response);
	}
}

//This class is effectivly a bean, and represents all of the fields that a 
//user can specify in a search 
function searchObj(){
	this._search = null;
	this._sort = null;
	this._sortDirection = null;
	this._fields = null;
	this._numResults = null;
	this._content = null;
	this._offset = null;
	
	//search
	this.setSearch = function(search){
		this._search = search;
	}
	this.getSearch = function(){
		return this._search;
	}
	
	//sort
	this.setSort = function(sort){
		this._sort = sort;
	}
	this.getSort = function(){
		return this._sort;
	}
	
	//sortDirection
	this.setSortDirection = function(sortDirection){
		this._sortDirection = sortDirection;
	}
	this.getSortDirection = function(){
		return this._sortDirection;
	}
	
	//fields
	this.setFields = function(fields){
		this._fields = fields;
	}
	this.getFields = function(){
		return this._fields;
	}
	
	//numResults
	this.setNumResults = function(numResults){
		this._numResults = numResults;
	}
	this.getNumResults = function(){
		return this._numResults;
	}
	
	//content
	this.setContent = function(content){
		this._content = content;
	}
	this.getContent = function(){
		return this._content;
	}
	
	//offset
	this.setOffset = function(offset){
		this._offset = offset;
	}
	this.getOffset = function(){
		return this._offset;
	}
}

