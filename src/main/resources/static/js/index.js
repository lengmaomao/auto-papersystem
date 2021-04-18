//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function(){
    var element = layui.element;

    //…
});
//调用jQuery.load()方法添加添加试卷的html
function addPaper() {
    $("#infoPanelBody").empty();
    $("#infoPanelBody").load("/papers/addPaper/",function () {
        layui.use('form',function () {
            const form = layui.form;
            form.render();
        })
    })
}
//调用jQuery.load()方法添加我的试卷模块的html
function myPaper() {
    $("#infoPanelBody").empty();
    $("#infoPanelBody").load("/papers/myPaper/",function () {
        layui.use('form',function () {
            const form = layui.form;
            form.render();
        })
    })
}
//调用jQuery.load()方法添加添加试题模块的html
function addSubject() {
    $("#infoPanelBody").empty();
    $("#infoPanelBody").load("/subjects/addSubjects/",function () {
        layui.use('form', function(){
            const form = layui.form;
            form.render();
        });
    });
}
//调用jQuery.load()方法添加题目模块的html
function publicSubjects() {
    $("#infoPanelBody").empty();
    $("#infoPanelBody").load("/subjects/subjectList/",function () {
        layui.use('form', function(){
            const form = layui.form;
            form.render();
        });
    });
}
//调用jQuery.load()方法添加我的试题模块的html
function mySubject() {
    $("#infoPanelBody").empty();
    $("#infoPanelBody").load("/subjects/mySubject/",function () {
        layui.use('form', function(){
            const form = layui.form;
            form.render();
        });
    });
}