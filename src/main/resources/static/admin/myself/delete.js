(function($) {
	$.fn.deleteFun = function(opts) {
		var _config = {
			title:"操作提示",
			msg:''
		};
		var _event = {
			click:_click,
			init:_init
		};
		$.extend(_config, _event);
		
		var that = this;
		var settings = $.extend(_config, opts||{});
		function _click(event) {
			var href = $(this).attr("href");
			var tempMsg = settings.msg;
			if(!tempMsg) {
				tempMsg = $(this).attr("title");
				if(!tempMsg) {
					tempMsg = $(this).html();
				}
			}
			//alert(tempMsg);
			
			var modalDiv = '<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">' + 
						   '<div class="modal-dialog modal-sm">' +
						   '<div class="modal-content">'+
						   '<div class="modal-header">' +
					       '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
					       '<h4 class="modal-title red"><span class="glyphicon glyphicon-exclamation-sign"></span>  '+settings.title+'</h4>' +
						   '</div>' +
						   '<div class="modal-body">' +
						   '<script type="text/javascript"> function runDel() {$.post("'+href+'", {}, function(res) {if(res=="1") {window.location.reload();}}, "json")}</script>'+
					       '<p>'+tempMsg+'</p>' +
					       '</div>' +
					       '<div class="modal-footer">' +
					       '<button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-remove bigger-110"></i>&nbsp;取消并关闭</button>' +
					       '<a type="button" href="javascript:runDel()" class="btn btn-danger"><i class="icon-trash bigger-110"></i>&nbsp;确定并提交</a>' +
					       '</div>' +
						   '</div></div></div>';

			$(modalDiv).modal({keyboard:false, backdrop:'static'});
            event.preventDefault();
		}
		
		function _init() {
			$(that).on("click", settings.click);
		}
		
		settings.init();
		
		return this;
	}
})(jQuery);