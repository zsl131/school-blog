$(function() {
	$(".need_skip_url").click(function() {
		var targetUrl = $(this).attr("targetUrl");
		if(targetUrl!=null && $.trim(targetUrl)!='') {
			window.location.href = targetUrl;
		}
	});
	
	$(".file-size-show").each(function() {
		var size = parseInt($(this).html());
		var html ;
		if(size<1024) {html = size+"B";}
		else if(size>=1024 && size<1024*1024) {html = parseInt(size/1024) + "KB";}
		else if(size>=1024*1024 && size<1024*1024*1024) {html = parseInt(size/1024/1024) + "MB";}
		else {html = parseInt(size/1024/1024/1024) + "GB";}
		$(this).html(html);
	});
});
/** bootstrap modal */
function showDialog(msg, title) {
	var idStr = "myModal_"+parseInt(Math.random()*100000000);
	if($.trim(title)=='') {title = "系统提示";}
	var html = '';
	html += '<div class="modal fade" id="'+idStr+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">' +
		  	'<div class="modal-dialog" role="document">' +
		  	'<div class="modal-content">' +
		  	'<div class="modal-header">' +
		  	'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
		  	'<h4 class="modal-title" id="myModalLabel">'+title+'</h4>' +
		  	'</div>' +
		  	'<div class="modal-body">'+msg+'</div>' +
		  	'<div class="modal-footer">' +
		  	'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>' +
		  	'</div></div></div></div>';
	
	$(html).appendTo("body");
	$(("#"+idStr)).modal({keyboard:true, show:true});
	$(("#"+idStr)).on('hidden.bs.modal', function (e) {
        $(("#"+idStr)).remove();
    });
    return $(("#"+idStr));
}

/** bootstrap 提示框 */
function confirmDialog(msg, title, okfn) {
	var idStr = "myConfirmDialog_"+parseInt(Math.random()*100000000);
	if($.trim(title)=='') {title = "系统提示";}
	var html = '';
	html += '<div class="modal fade" id="'+idStr+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">' +
		  	'<div class="modal-dialog" role="document">' +
		  	'<div class="modal-content">' +
		  	'<div class="modal-header">' +
		  	'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
		  	'<h4 class="modal-title" id="myModalLabel">'+title+'</h4>' +
		  	'</div>' +
		  	'<div class="modal-body">'+msg+'</div>' +
		  	'<div class="modal-footer">' +
		  	'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>' +
		  	'<button type="button" class="btn btn-primary dialog-ok-btn">确定</button>' +
		  	'</div></div></div></div>';
	
	$(html).appendTo("body");
	$(("#"+idStr)).modal({keyboard:true, show:true});
	$(("#"+idStr)).find(".dialog-ok-btn").click(okfn);
	$(("#"+idStr)).on('hidden.bs.modal', function (e) {
        $(("#"+idStr)).remove();
    });
	return $(("#"+idStr));
}