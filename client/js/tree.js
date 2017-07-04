/*
 * Forked from Collapsible Indented Tree by Mike Bostock
 * https://bl.ocks.org/mbostock/1093025
 * Released under GPL v3
 */

var tab = '#none';
var baseURL = 'http://aquasmart.uninova.pt:5904/';
//var baseURL = 'http://localhost:5904/';

$('#topics button').click(function() {
	var ajaxOptions = {
		method : 'POST',
		contentType : 'application/json',
		data : JSON.stringify($('#keywords-input').tagsinput('items')),
		url : baseURL + $('input[name=granularity]:checked', '.switch-toggle').val(),
		crossDomain : true,
		dataType : 'json',
		success : function(json) { buildTree(json, '#topics'); },
		error : function(xhr, status, error) { console.log(status + error); }
	};
	$.ajax(ajaxOptions);
});

var audienceButton = $('#audience button');
audienceButton.prop('disabled', true);
$('#audience select').on('changed.bs.select', function (event, clickedIndex, newValue, oldValue) {
	audienceButton.prop('disabled', false);
});

audienceButton.click(function() {
	var ajaxOptions = {
		method : 'GET',
		url : baseURL + 'programme/' + $('input[name=expertise]:checked', '.switch-toggle').val() + "/" +
				encodeURIComponent($("#audience select option:selected").text()),
		crossDomain : true,
		dataType : 'json',
		success : function(json) { buildTree(json, '#audience'); },
		error : function(xhr, status, error) { console.log(status + error); }
	};
	$.ajax(ajaxOptions);
});

function buildTree(json, tab) {
	var treeDiv = $(tab + ' .tree');
	treeDiv.empty();
	treeDiv.json2html(json,transforms.course);
	
	// Register open/close events
	$(tab + ' .header .clickable').click(function(){
		var parent = $(this).parent().parent();
		if(parent.hasClass('closed')) {
			parent.removeClass('closed');
			parent.addClass('open');
		} else {
			parent.removeClass('open');
			parent.addClass('closed');
		}
		parent.children('.children, .summary').toggle('slow');
	});

	$(tab + ' .closed .children, ' + tab + ' .closed .summary').hide();
}

var transforms = {
	'course': {'<>':'div', 'class':'package closed course', 'html': [
		{'<>':'div', 'class':'header', 'html': function(obj){return($.json2html(obj,transforms.header));}},
		{'<>':'div', 'class':'summary', 'html':'${summary}'},
		{'<>':'div', 'class':'children','html': function(obj){return($.json2html(obj.modules,transforms.module));}}
	]},
	'module': {'<>':'div', 'class':'package closed module', 'html': [
		{'<>':'div', 'class':'header', 'html': function(obj){return($.json2html(obj,transforms.header));}},
		{'<>':'div', 'class':'summary', 'html':'${summary}'},
	]},
	'header': [
		{'<>':'div', 'class':'clickable', 'html': [
			{'<>':'span', 'class':'arrow'},
			{'<>':'span', 'class':'name', 'html':'${name}'}
		]},
		{'<>':'a', 'class':'follow btn btn-primary btn-xs', 'href':'${url}', 'target':'_blank', 'html': [
			{'<>':'span', 'class':'glyphicon glyphicon-new-window', 'aria-hidden':'true'},
			{'<>':'span', 'html':' Follow ${type}'}
		]}
	]
};
