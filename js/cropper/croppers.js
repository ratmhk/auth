/*!
 * Cropper v3.0.0
 */

layui.config({
    base: '/assets/js/cropper/' //layui自定义layui组件目录
}).define(['jquery','layer','cropper'],function (exports) {
    var $ = layui.jquery
        ,layer = layui.layer;
    var html = "<link rel=\"stylesheet\" href=\"/assets/js/cropper/cropper.css\">\n" +
        "<div class=\"layui-fluid showImgEdit\" style=\"display: none\">\n" +
        "    <div class=\"layui-form-item\">\n" +
        "        <div class=\"layui-input-inline layui-btn-container\" style=\"width: auto;\">\n" +
        "            <label for=\"cropper_avatarImgUpload\" class=\"layui-btn layui-btn-primary\">\n" +
        "                <i class=\"layui-icon\">&#xe67c;</i>选择图片\n" +
        "            </label>\n" +
        "            <input class=\"layui-upload-file\" id=\"cropper_avatarImgUpload\" type=\"file\" value=\"选择图片\" name=\"file\">\n" +
        "        </div>\n" +
        "    </div>\n" +
        "    <div class=\"layui-row layui-col-space15\">\n" +
        "        <div class=\"layui-col-xs9\">\n" +
        "            <div class=\"readyimg\" style=\"height:450px;background-color: rgb(247, 247, 247);\">\n" +
        "                <img src=\"\" >\n" +
        "            </div>\n" +
        "        </div>\n" +
        "        <div class=\"layui-col-xs3\">\n" +
        "            <div class=\"img-preview\" style=\"width:200px;height:200px;overflow:hidden\">\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "    <div class=\"layui-row layui-col-space15\">\n" +
        "        <div class=\"layui-col-xs9\">\n" +
        "            <div class=\"layui-row\">\n" +
        "                <div class=\"layui-col-xs6\">\n" +
        "                    <button type=\"button\" class=\"layui-btn layui-icon layui-icon-left\" cropper-event=\"rotate\" data-option=\"-15\" title=\"Rotate -90 degrees\"> 向左旋转</button>\n" +
        "                    <button type=\"button\" class=\"layui-btn layui-icon layui-icon-right\" cropper-event=\"rotate\" data-option=\"15\" title=\"Rotate 90 degrees\"> 向右旋转</button>\n" +
        "                </div>\n" +
        "                <div class=\"layui-col-xs5\" style=\"text-align: right;\">\n" +
        "                    <button type=\"button\" class=\"layui-btn\" title=\"放大图片\">放大</button>\n" +
        "                    <button type=\"button\" class=\"layui-btn\" title=\"缩小图片\">缩小</button>\n" +
        "                    <button type=\"button\" class=\"layui-btn layui-icon layui-icon-refresh\" cropper-event=\"reset\" title=\"重置图片\"></button>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "        <div class=\"layui-col-xs3\">\n" +
        "            <button class=\"layui-btn layui-btn-fluid\" cropper-event=\"confirmSave\" type=\"button\"> 保存修改</button>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "\n" +
        "</div>";
    var obj = {
        render: function(e){
            $('body').append(html);
            var self = this,
                elem = e.elem,
                saveW = e.saveW,
                saveH = e.saveH,
                mark = e.mark,
                area = e.area,
                url = e.url,
                done = e.done;

            var content = $('.showImgEdit')
                ,image = $(".showImgEdit .readyimg img")
                ,preview = '.showImgEdit .img-preview'
                ,file = $(".showImgEdit input[name='file']")
                , options = {aspectRatio: mark,preview: preview,viewMode:1};

            $(elem).on('click',function () {
                Index = layer.open({
                    type: 1
                    , content: content
                    , area: area
                    , success: function () {
                        image.cropper(options);
                    }
                    , cancel: function (index) {
                        layer.close(index);
                        image.cropper('destroy');
                    }
                });
            });
            $(".layui-btn").on('click',function () {
                var event = $(this).attr("cropper-event");
                //监听确认保存图像
                if(event === 'confirmSave'){

                    image.cropper("getCroppedCanvas",{
                        width: saveW,
                        height: saveH
                    }).toBlob(function(blob){

                        var formData=new FormData();
                        formData.append('file',blob,'head.jpg');
                        $.ajax({
                            method:"post",
                            url: "/big1/file/uploadImage/", //用于文件上传的服务器端请求地址  url
                            data: formData,
                            processData: false,
                            contentType: false,
                            success:function(result){

                                var img = result.body.fileName;
                                console.info("res   "+ img);
                                if(result.errorCode == 0){
                                    layer.msg(result.msg,{icon: 1});
                                    layer.close(Index);
                                    console.info("result     ---   "+result.body);
                                    $("#srcimgurl").attr("src", "/big1/file/showImage?fileName="+img);
                                    updateImg(img)
                                   // layer.done(result.body.fileName);
                                }else if(result.errorCode == -1){
                                    console.info("失败");
                                    layer.alert(result.msg,{icon: 2});
                                }

                            }
                        });
                    });
                    //监听旋转
                }else if(event === 'rotate'){
                    var option = $(this).attr('data-option');
                    image.cropper('rotate', option);
                    //重设图片
                }else if(event === 'reset'){
                    image.cropper('reset');
                }
                //文件选择
                file.change(function () {
                    var r= new FileReader();
                    var f=this.files[0];
                    r.readAsDataURL(f);
                    r.onload=function (e) {
                        image.cropper('destroy').attr('src', this.result).cropper(options);
                    };
                });
            });
        }

    };
    exports('croppers', obj);
});




function updateImg(img) {
    var webPath = "/big1";
    var userId = sessionStorage.getItem("userId");
    console.info("user id  "+ userId+ "  "+typeof userId);

    var data = {userId:userId,img:img};
    $.ajax({
        url: webPath+"/user/changeImg",
        type: "POST",
        data:JSON.stringify(data),
        dataType: "json",
        contentType: 'application/json;charset=utf-8',
        success: function(data) {
            if (data.errorCode == "0") {
                layer.msg("更换头像成功");
                //修改缓存
                var userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
                userInfo.img =  img;
                sessionStorage.setItem("userInfo",JSON.stringify(userInfo));
            }else  console.info("更换失败 "+JSON.stringify(data ))
        },
        error: function(xhr, type, errorThrown) {
            layer.msg("失败  "+xhr.responseJSON + "  "+errorThrown)
        }
    })//ajax


}