$(function () {

    (function () {
        $.ajax({
            url: "/getIsRun",
            success: function (isRun) {
                if (isRun === "error") {
                    alert("发生错误了，及时联系技术部代毅处理！")
                } else {
                    if (isRun) {
                        $("#startAutoReplyTool").addClass("disabled");
                    } else {
                        $("#stopAutoReplyTool").addClass("disabled");
                    }
                }
            }
        });
    })();


    $("#startAutoReplyTool").on("click", function () {
        let that = this;
        $.ajax({
            url: "/startAutoReplyTool",
            success: function (status) {
                if (status === "error") {
                    alert("发生错误了，及时联系技术部代毅处理！")
                } else {
                    $(that).addClass("disabled");
                }
            }
        });
    });


    $("#getExecutionsCount").on("click", function () {
        let that = this;
        $.ajax({
            url: "/getExecutionsCount",
            success: function (executionsCount) {
                if (executionsCount === "error") {
                    alert("发生错误了，及时联系技术部代毅处理！")
                } else {
                    alert("已执行了" + executionsCount + "次！");
                }
            }
        });
    });

    $("#getReplyCount").on("click", function () {
        let that = this;
        $.ajax({
            url: "/getReplyCount",
            success: function (replyCount) {
                if (replyCount === "error") {
                    alert("发生错误了，及时联系技术部代毅处理！")
                } else {
                    alert("已回复" + replyCount + "次！");
                }
            }
        });
    });


    $("#stopAutoReplyTool").on("click", function () {
        $modal({
            type: 'confirm', //弹框类型  'alert' or  'confirm' or 'message'   message提示(开启之前如果之前含有弹框则清除)
            icon: 'warning', // 提示图标显示 'info' or 'success' or 'warning' or 'error'  or 'question'
            title: '警告', // 提示文字
            content: '您确定要结束自动回复吗?', // 提示文字
            transition: 300, //过渡动画 默认 200   单位ms
            closable: true, // 是否显示可关闭按钮  默认为 false
            mask: true, // 是否显示遮罩层   默认为 false
            top: 400, //距离顶部距离 单位px
            center: true,// 是否绝对居中 默认为false  设置true后   top无效
            pageScroll: false, // 是否禁止页面滚动
            width: 500, // 单位 px 默认显示宽度 最下默认为300
            maskClose: true, // 是否点击遮罩层可以关闭提示框 默认为false
            cancelText: '取消',// 取消按钮 默认为 取消
            confirmText: '确认',// 确认按钮 默认未 确认
            cancel: function (close) {  // 回调函数  //  函数返回关闭事件 调用-关闭弹窗
                close(); // 调用返回的 关闭弹框函数 才能关闭
            },
            confirm: function (close) { // 回调函数  //  函数返回关闭事件 调用-关闭弹窗
                $.ajax({
                    url: "/stopAutoReplyTool",
                    success: function (status) {
                        if (status === "error") {
                            alert("发生错误了，及时联系技术部代毅处理！")
                        } else if (status === 200) {
                            $("#startAutoReplyTool").removeClass("disabled");
                            $("#stopAutoReplyTool").addClass("disabled");
                            close(); // 调用返回的 关闭弹框函数 才能关闭
                        }
                    }
                });
            }
        });
    });


});