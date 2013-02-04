var user = user || {};

user.showDeleteModal = function() {	
	$('.delete').click(function() {
		var id = $(this).next('.user-id').val();		
		$("#dialog-confirm").dialog({			
			resizable : false,		
			modal : true,
			buttons : {
				"Delete" : function() {
					var formEl = $('#delete-user-form');
					formEl.find('[name=id]').val(id);
					formEl.submit();
					//$(this).dialog("close");								
				},
				Cancel : function() {
					$(this).dialog("close");
				}
			}
		});
	});
};

$(document).ready(user.showDeleteModal);
$(document).ready(function() {
	$("#users-list").tablesorter();
});