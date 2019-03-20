var gParamsObj = {};

$(document).ready(function () {
    analyzeParams();
    resultJudgment();
    $(".confirm").on("click", closePage);
});

// 获取地址栏参数
function analyzeParams() {
    var paramsArray = decodeURI(location.search).replace(/^\?/, "").split("&");
    for (var i = 0, len = paramsArray.length; i < len; i++) {
        var keyVal = paramsArray[i].split('=');
        gParamsObj[keyVal[0]] = keyVal[1];
    }
    console.log("gParamsObj:", gParamsObj);
}

// 结果类型判断
function resultJudgment() {
    var type = gParamsObj["type"];
    var msg = gParamsObj["msg"];
    var $type = $(".type");
    var $msg = $(".message");
    switch (type) {
        case "ViceBindPrincess":
            $type.text("营业经理绑定公关");
            if (msg == "NotInSameBusiness") {
                $msg.text("非同一商户");
            }
            else if (msg == "HasBinded") {
                $msg.text("已绑定过该公关");
            }
            else if (msg == "HadBeenBinded") {
                $msg.text("公关已被别的营业经理绑定");
            }
            else if (msg == "Success") {
                $msg.text("绑定成功");
            }
            else if (msg == "Fail") {
                $msg.text("绑定失败");
            }
            else if (msg == "NotPrincess") {
                $msg.text("绑码人非公关");
            }
            break;
        case "WaiterBindPrincess":
            $type.text("服务员绑定公关到房间");
            if (msg == "AddSuccess") {
                $msg.text("绑定房间成功");
            }
            else if (msg == "AddFail") {
                $msg.text("绑定房间失败");
            }
            else if (msg == "DeleteFail") {
                $msg.text("解绑房间失败");
            }
            else if (msg == "InSameRoom") {
                $msg.text("已在此房间");
            }
            else if (msg == "WaiterNotBindRoom") {
                $msg.text("服务员未绑定房间");
            }
            else if (msg == "NotPrincess") {
                $msg.text("绑码人非公关");
            }
            break;
        case "BindQrcode":
            $type.text("绑定二维码");
            if (msg == "Success") {
                $msg.text("绑码成功");
            }
            else if (msg == "Fail") {
                $msg.text("绑码失败");
            }
            break;
        case "Other":
            $type.text("其他类型");
            if (msg == "DontHaveBindPermission") {
                $msg.text("无绑码权限");
            }
            else if (msg == "NotInSameBusiness") {
                $msg.text("不在同一商户");
            }
            else if (msg == "DontHaveRewardPermission") {
                $msg.text("无打赏权限");
            }
            break;
        default:
            $type.text("未知类型");
    }
    if (msg == "Exception") {
        $msg.text("执行异常");
    }
    if ($msg.text() == "") {
        $msg.text("未知情况");
    }
}

//关闭整个页面
function closePage() {
    WeixinJSBridge.call("closeWindow");
}
