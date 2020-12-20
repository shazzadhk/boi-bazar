$(document).ready(function () {

    $(".delete_book").on("click",function () {
       var id = $(this).attr('id');
       console.log(id);

        bootbox.confirm({
            message: "Do you want to delete this book? This cannot be undone.",
            buttons: {
                cancel: {
                    label: '<i class="fa fa-times"></i> Cancel'
                },
                confirm: {
                    label: '<i class="fa fa-check"></i> Confirm'
                }
            },
            callback: function (confirmed) {
                // console.log("this works...");
                if(confirmed){
                    console.log("this works...");
                    $.post("/delete_book",{'book_id':id},function (result) {
                        location.reload();
                    });
                }
            }
        });

    });






});