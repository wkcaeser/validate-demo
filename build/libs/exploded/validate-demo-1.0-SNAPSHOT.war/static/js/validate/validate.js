/**
 * 刷新验证码
 */
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
    $("#validateText").unbind('click');
}

function checkValidateCode() {
    var code = $("#validateText").val();
    code = {'validateCodeText' : code};
    $.ajax({
        type:"GET",
        url:"/checkValidateCode",
        dataType:"json",
        contentType: "application/json",
        data:code,
        success:function (data) {
            var status = data.status;
            $("#validateStatus").html(status);
            if(status === "ERROR")
                refreshValidateCode();
            console.log(status);
        },
        error:function (data) {
            console.log("error : " + data);
            refreshValidateCode();
        }
    })
}
$("#validateText").on("click", showValidate);
$("#refreshValidateCode").on("click", refreshValidateCode);
$("#validateText").on("blur", checkValidateCode);