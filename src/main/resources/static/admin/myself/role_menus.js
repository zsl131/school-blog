var curAuth_array;
$(function() {
    var defaultData = $("#treeJson").val();
    //alert(defaultData);
    defaultData = eval('(' + defaultData + ')'); //JSON.parse(defaultData);

    //alert(defaultData);
    var myMenuTree = $('#tree1').treeview({
        data: defaultData,
        showIcon: true,
        showCheckbox: true,
        showTags: true,
        state:'',
        multiSelect: false,
        //levels: 99, //展开99级，基本也就是全部展开了
        onNodeChecked: function(event, node) {
            operate(node.text, "1");
        },
        onNodeUnchecked: function (event, node) {
            operate(node.text, "2");
        }
    });

    curAuth_array = $("#curAuth").val().split(",");
    for(var i=0; i<curAuth_array.length; i++) {
        //alert(curAuth_array[i]);
        myMenuTree.treeview("checkNode", myMenuTree.treeview("search", "="+curAuth_array[i]+">"));
    }
});

//alert(curAuth_array.length);

//添加或删除数据
function operate(name, flag) {
    var objId = $(name).attr("title"); //获取菜单Id
    //alert(objId+"=="+$.inArray(objId, curAuth_array)>=0);
    if(flag=='1' && $.inArray(objId, curAuth_array)>=0) {} //初始化时不作任何操作
    else {
        var roleId = $("#objId").val();

        $.post("/admin/role/addOrDelRoleMenu", {roleId : roleId, menuId : objId}, function(res) {
            if(res!='1') {alert("授权资源菜单失败！");}
        }, "json");
    }
}