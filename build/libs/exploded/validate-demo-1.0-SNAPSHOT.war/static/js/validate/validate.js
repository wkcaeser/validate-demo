function refreshValidateCode() {
    $.ajax({
        type:'post',
        url:'/validateCode',
        success:function (data) {
            var img = $("#validateCode");
            img.attr("src", "data:image/jpg;base64,"+data);
        },
        error:function () {
            console.log("validateCode gg !");
        }
    })
}

function showValidate() {
    var imgDiv = $("#validate");
    refreshValidateCode();
    imgDiv.css("display", "");
    $("#validateText").on("click", "");
}
$("#validateText").on("click", showValidate);
$("#refreshValidateCode").on("click", refreshValidateCode);