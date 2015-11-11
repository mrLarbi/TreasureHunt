$.fn.editable.defaults.mode = 'inline';

$(document).ready(function () {

	$('head').append('<link />');

	$('#title').replaceWith( "<a id=\"huntTitle\" href=\"#\">New Hunt</a>" );
	$('#huntTitle').addClass("text-center");

   	$('#huntTitle').editable({
   		type: 'text',
       	title: 'Enter hunt name',
       	placeholder: 'New Hunt',
       	placement: 'right',
       	inputclass: 'editableInput'
    });
});