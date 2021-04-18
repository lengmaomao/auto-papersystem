let paperTemp = null;
let selectSubject = 0;
$(document).ready(function(){

    $("#subject_nav").on('click','.subject_button',function () {
            selectSubject = parseInt($(this).text());
            changePanel(selectSubject);
    });
    //下一题
    $("#next_subject").click(function () {
        if (selectSubject < paperTemp.totalSubjects.length){
            changePanel(++selectSubject);
        }
    })
    //重新获取试卷
    $("#reload_paper").click(function () {
        $.ajax({
            url: "/paper/reload",
            success: function (data) {
                console.log(data);
                window.location.href = "/create/paper";
            }
        })
    })
    //上传提交确认组卷
    $("#submit_paper").click(function () {
        $.ajax({
            url: "/paper/add_auto_paper",
            success: function () {
                alert("添加试卷成功!");
            }
        })
    })
    //点击删除试题
    $("#delete_subject").click(function () {
        console.log('selectSubject');
        console.log(selectSubject);
        layui.use('layer',function () {
            let layer = layui.layer
            layer.confirm('确定从本试卷中删除 '+paperTemp.totalSubjects[selectSubject-1].subjectName+' 试题吗',{
                    btn: ['确定','取消'],
                },function f1(index) {
                    //确定删除函数
                    $.ajax({
                        type: 'POST',
                        url: '/subject/delete_subject_from_paper',
                        data: {
                            'paperId': paperTemp.paperId,
                            'subjectId': paperTemp.totalSubjects[selectSubject-1].subjectId
                        }
                    })
                    location.reload();
                    layer.close(index);
                },
                function f2(index) {
                    layer.close(index)
                })
        })
    })
});

function changePanel(i) {
    let subject = paperTemp.totalSubjects[i-1];
    $("#paper_name").text(paperTemp.paperName);
    $("#subject_type").text(subject.subjectType);
    $("#subject_dec").text(subject.subjectDescribe);
    $("#subject_answer").text(subject.subjectAnswer);
    $("#subject_name").text(subject.subjectName);
    if (subject.subjectDifficulty == 1){
        $("#subject_difficulty").removeClass("layui-bg-red layui-bg-orange").addClass("layui-bg-green");
        $("#subject_difficulty").text("简单");
    }

    if (subject.subjectDifficulty == 2){
        $("#subject_difficulty").removeClass("layui-bg-red layui-bg-green").addClass("layui-bg-orange");
        $("#subject_difficulty").text("中等");
    }

    if (subject.subjectDifficulty == 3){
        $("#subject_difficulty").removeClass("layui-bg-orange layui-bg-green").addClass("layui-bg-red");
        $("#subject_difficulty").text("困难");
    }
}

//修改导航栏
function changeNav(paper){
    if (paper == null)
        alert("请先创建试卷");
    $("#subject_nav").empty();
    const size = paper.totalSubjects.length;
    console.log(size);
    for (let n = 1; n<=size; n++){
        $("#subject_nav").append("<button type=\"button\" class=\"layui-btn layui-btn-sm layui-btn-primary subject_button\">\n" +
            n +
            "            </button>")
    }

}
//获取缓存的试卷
function getTempPaper() {
    let paper = null;
    //获取创建的paper
    $.ajax({
        url: "/paper/getTemp",
        success: function (data) {
            paper = data;
            console.log("paper1");
            console.log(paper);
            if (data != null)
            paperTemp = data.paper;
            changeNav(data.paper);
        }
    })
}