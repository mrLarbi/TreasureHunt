$.fn.editable.defaults.mode = 'inline';

$(document).ready(function () {

   	$('#pUsername').editable({
   		type: 'text',
       	placement: 'right',
       	inputclass: 'editableInput'
    });

    $('#pPhone').editable({
   		type: 'text',
       	placement: 'right',
       	inputclass: 'editableInput'
    });

    $('#pZIP').editable({
   		type: 'text',
       	placement: 'right',
       	inputclass: 'editableInput'
    });

	$( "#changeAvatar" ).click(function() {
  		
	});

	$( "#editProfile" ).click(function() {
  		
	});
});