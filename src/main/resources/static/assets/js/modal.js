$('#modal_add_task').on('shown.bs.modal', function (event) { 
  var e = $(event.relatedTarget);    
  $('#kanban_id').val(e.data('kanbanid')) ;
    
});

$('#modal_edit_task').on('shown.bs.modal', function (event) { 
    var e = $(event.relatedTarget);    
    $('#board_name').html(e.data('board_name')) ;
    $('#create_nick').html(e.data('create_nick')) ;
    $('#create_time').html(e.data('create_time')) ;
    $('#content').html(e.data('content')) ;
    $('#start_nick').html(e.data('start_nick')) ;
    $('#start_user_email').html(e.data('start_user_email')) ;
    
});
