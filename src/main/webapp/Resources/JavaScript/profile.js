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

	$( "#editProfile" ).click(function() {
  		var edit = {};
  		edit["newName"] = $('#pUsername').text();
  		edit["newPhone"] = $('#pPhone').text();
  		edit["newZIP"] = $('#pZIP').text();

  		var params = {param:JSON.stringify(edit)};

  		$.post("/user/editProfile",params, function(response) {
			if (response == "false") {
				return;
			}
		});

  		console.log(JSON.stringify(edit))
	});
});