var gParamsObj = {};
var serverDomain = "https://www.meiyumeet.com/";
//var serverDomain = "https://sw.miaoaw.com/";
var isTest = false;  //是否测试支付1分

$(document).ready(function () {
    $("html,body,.container,.pop-container").height(document.body.clientHeight);
    analyzeParams();
    getPersonalInfo(gParamsObj["uid"]);
    acquireLabels();
    bindEvents();
});

// 获取地址栏参数
function analyzeParams() {
    var paramsArray = decodeURI(location.search).replace(/^\?/, "").split("&");
    for (var i = 0, len = paramsArray.length; i < len; i++) {
        var keyVal = paramsArray[i].split('=');
        gParamsObj[keyVal[0]] = keyVal[1];
    }
}

// 绑定事件
function bindEvents() {
    $(".pop-container").on("click", closePopContainer);
    $(".reward-amount, .reward-evaluate, .complain-content, .complain-success").on("click", function (e) {
        e.stopPropagation();
    });

    $(".foot-one").on("click", showComplain);   //投诉按钮，显示投诉页
    $(".reward").on("click", userRewardPay);    //自定义金额页打赏确定
    $(".complain-content > .confirm").on("click", complainConfirm);     //投诉内容页确定
    $(".complain-success > .confirm").on("click", closePopContainer);   //投诉成功页确定
    $(".evaluate>.close").on("click", closePopContainer);       //评价页点击关闭
    $(".evaluate-confirm").on("click", showEvaluateSuccess);    //评价页点击确定
    $(".close-page").on("click", closePage);  //评价成功弹窗点击关闭页面
    $(".continue").on("click", closePopContainer);    //评价成功弹窗点击继续评价
}

// 获取各类标签
function acquireLabels() {
    $.ajax({
        url: serverDomain + "MyShang/label/getLabelList",
        success: function (res) {
            if (res.code == 0) {
                //打赏标签
                var rewdList = res.list;
                for (var i = 0, len = rewdList.length; i < len; i++) {
                    var itemVal = rewdList[i].value;
                    var itemName = rewdList[i].labelName;
                    var $rewdItem;
                    if (itemVal == 0) {
                        $rewdItem = $(
                            '<div class="item zero" data-value="0">' +
                                '<span class="value">0</span>' +
                                '<span class="text">' + itemName + '</span>' +
                            '</div>');
                        $rewdItem.on("click", userRewardZero);
                        $(".content").append($rewdItem);
                    }
                    else if (itemVal == 10001) {
                        $rewdItem = $(
                            '<div class="item negative" data-value="-1">' +
                                '<img class="redbag" src="./resource/icon/redbag.png" />' +
                                '<span class="text">' + itemName + '</span>' +
                            '</div>');
                        $rewdItem.on("click", userRewardDefine);
                        $(".content").append($rewdItem);
                    }
                    else if (itemName == "金额下限") {
                        moneyLower = itemVal;
                        $(".range > .lower").text(moneyLower);
                    }
                    else if (itemName == "金额上限") {
                        moneyUpper = itemVal;
                        $(".range > .upper").text(moneyUpper);
                    }
                    else {
                        var $heartGroup = $('<div class="heart-group"></div>');
                        for (var j = 0; j < i; j++) {
                            var $heart = $('<img class="heart" src="./resource/icon/heart.png" />');
                            $heartGroup.append($heart);
                        }
                        var $des = $(
                            '<div class="des">' +
                                '<span class="value">' + itemVal + '</span>' +
                                '<span class="text">' + itemName + '</span>' +
                            '</div>');
                        $rewdItem = $('<div class="item" data-value="' + itemVal + '"></div>');
                        $rewdItem.append($heartGroup);
                        $rewdItem.append($des);
                        $rewdItem.on("click", userReward);
                        $(".content").append($rewdItem);
                    }
                }
                //评价标签
                var evalList = res.freeRoomdata;
                for (var i = 0, len = evalList.length; i < len; i++) {
                    var $evalLab = $('<span class="eval-label">' + evalList[i].labelName + '</span>');
                    $evalLab.on("click", checkLabel);
                    $(".evaluate-labels").append($evalLab);
                }
                //投诉标签
                var comList = res.data;
                for (var i = 0, len = comList.length; i < len; i++) {
                    var $comLab = $('<span id=' + comList[i].labid + ' class="label">' + comList[i].labelName + '</span>');
                    $comLab.on("click", checkLabel);
                    $(".labels").append($comLab);
                }
            }
        }
    });
}

// 点击零元红包
function userRewardZero() {
    showEvaluate();
}

// 选中或不选标签
function checkLabel() {
    if ($(this).hasClass("checked")) {
        $(this).removeClass("checked");
    }
    else {
        $(this).addClass("checked");
    }
}

//获取选中标签id集合
function getLabelIds() {
    var labIds = "";
    $(".label.checked").each(function () {
        var id = $(this).attr("id");
        labIds += id + ",";
    });
    return labIds;
}
// 投诉确认
function complainConfirm() {
    var labIds = getLabelIds();
    var text = $("#textarea").val();
    if (labIds == "" && text == "") {
        alert("请输入投诉内容！");
        return;
    }
    $.ajax({
        url: serverDomain + "MyShang/messages/createMessages",
        type: "POST",
        data: {
            messageType: 2,
            messageContent: text,
            labelSet: labIds,
            uid: 0,
            roomName: "无",
            complainId: gParamsObj["uid"],
            businessId: gParamsObj["businessId"],
        },
        success: function (res) {
            if (res.code == 0) {
                $(".complain-content").hide();
                $(".complain-success").show();
            }
        }
    });
}

// 微信支付
function onBridgeReady(data) {
    WeixinJSBridge.invoke(
        "getBrandWCPayRequest",
        {
            appId: data.appId,
            timeStamp: data.timeStamp,
            nonceStr: data.nonceStr,
            signType: data.signType,
            paySign: data.sign,
            package: data.package
        },
        function (res) {
            if (res.err_msg == "get_brand_wcpay_request:ok") {
                showEvaluate();
            }
        }
    );
}
// 打赏统一下单
function rewardOrder(amount) {
    $.ajax({
        url: serverDomain + "MyShang/reward/getAdvanceOrder",
        type: "POST",
        data: {
            money: isTest ? 1 : amount * 100,
            unionid: gParamsObj["unionid"],
            openid: gParamsObj["openid"],
            uid: gParamsObj["uid"],
            explain: "觅尚用户打赏"
        },
        success: function (res) {
            if (res.code == 0) {
                onBridgeReady(res.data);
            }
        }
    });
}
// 打赏预定义金额
function userReward() {
    var money = $(this).attr("data-value");
    rewardOrder(money);
}
// 打赏自定义金额
var moneyLower = 100;
var moneyUpper = 1000;
function userRewardPay() {
    var number = $("#input").val();
    if (number == "") {
        alert("请输入评价金额！");
        return;
    }
    else if (number < moneyLower) {
        alert("评价金额不得低于：" + moneyLower);
        return;
    }
    else if (number > moneyUpper) {
        alert("评价金额不可高于：" + moneyUpper);
        return;
    }
    $(".pop-container").hide();
    $(".reward-amount").hide();
    rewardOrder(parseFloat(number));
}

// 获取被打赏人信息
function getPersonalInfo(uid) {
    $.ajax({
        type: "POST",
        url: serverDomain + "MyShang/user/getUserByUid",
        data: { uid: uid },
        success: function (res) {
            if (res.code == 0) {
                $(".name").text(res.data.nickName);
                $(".number").text(res.data.uid);
                $(".headimg").attr("src", res.data.avatarUrl);
            }
        }
    });
}

// 关闭遮罩弹窗
function closePopContainer() {
    $(".pop-container").hide();
    $(".reward-amount").hide();
    $(".reward-evaluate").hide();
    $(".evaluate-success").hide();
    $(".complain-content").hide();
    $(".complain-success").hide();
}

// 显示投诉面板
function showComplain() {
    $("#textarea").val("");
    $(".pop-container").show();
    $(".complain-content").show();
}

// 用户自定义打赏
function userRewardDefine() {
    $("#input").val("");
    $(".pop-container").show();
    $(".reward-amount").show();
}

// 显示评价页面
function showEvaluate() {
    $(".pop-container").show();
    $(".reward-evaluate").show();
}

// 显示评价成功弹窗
function showEvaluateSuccess() {
    $(".reward-evaluate").hide();
    $(".evaluate-success").show();
}

//关闭整个页面
function closePage() {
    WeixinJSBridge.call("closeWindow");
}