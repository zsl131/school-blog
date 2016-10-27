(function($) {
	$.fn.sorttable = function(opts) {
		var _isSort = false;
		var sortEle = $(this).find("tbody");
		var _that = this;
		var _cfg = {
			url:null,
			sn:"show",
			idIndex:0
		};
		var settings = $.extend(_cfg,opts||{});
		function _init() {
			$(_that).find("tfoot").append("<tr class='sort_tr'><td colspan='11' style='padding:15px 0px; ' class='text-right'>" +
					"<a href='#' id='beginOrder' class='auth btn btn-info' style='width:120px' sn='"+settings.sn+"'>开始排序</a>&nbsp;" +
					"<span id='sortRemind' class='red'>请拖动数据行进行排序&nbsp;&nbsp;</span>" +
					"<a href='#' class='btn' id='cancelOrder'>取消排序</a>&nbsp;<a href='#' class='btn btn-primary' id='saveOrder'>存储排序</a>&nbsp;" +
					"</td></tr>");
			_setBeginBtn(false);
			$(_that).find("tbody tr").each(function(){
				$(this).attr("id","id_"+$(this).find("td:eq("+settings.idIndex+")").html());
			});
			sortEle.sortable({
				axis:"y",
				helper:function(e,ele) {
					//原始元素的td对象
					var _original = ele.children();
					var _helper = ele.clone();
					_helper.children().each(function(index){
						$(this).width(_original.eq(index).width());
					});
					_helper.css("background","#888");
					_helper.addClass("white");
					return _helper;
				},
				update:function(e,ui) {
					_setOrder();
				}
			});
			sortEle.sortable("disable");
			$("#beginOrder").click(_beginOrder);
			$("#saveOrder").click(_saveOrder);
			$("#cancelOrder").click(_cancelOrder);
			return sortEle;
		}
		function _setBeginBtn(isBegin) {
			if(isBegin) {
				$(_that).find("tfoot").find("a[id='beginOrder']").css("display","none");
				$(_that).find("tfoot").find("a[id='saveOrder']").css("display","inline");
				$(_that).find("tfoot").find("a[id='cancelOrder']").css("display","inline");
				$(_that).find("tfoot").find("span[id='sortRemind']").css("display","inline");
			} else {
				$(_that).find("tfoot").find("a[id='beginOrder']").css("display","inline");
				$(_that).find("tfoot").find("a[id='saveOrder']").css("display","none");
				$(_that).find("tfoot").find("a[id='cancelOrder']").css("display","none");
				$(_that).find("tfoot").find("span[id='sortRemind']").css("display","none");
			}
		}
		function _beginOrder(event) {
			event.preventDefault();
			if(!_isSort) {
				$(_that).find("thead tr").append("<th>序号</th>");
				_setOrder();
				sortEle.sortable("enable");
				_isSort = true;
				_setBeginBtn(true);
			} else {
				alert("已经处于排序状态");
			}
		}
		
		function _cancelOrder() {
			_isSort = false;
			$(_that).find("thead tr th:last-child").remove();
			$(_that).find("tbody tr td:last-child").remove();
			_setBeginBtn(false);
			sortEle.sortable("disable");
			
		}
		
		function _saveOrder(event) {
			event.preventDefault();
			if(_isSort) {
				var idArg = sortEle.sortable("serialize",{key:"ids"});
				//alert(settings.url+"?"+idArg);
				$.post(settings.url+"?"+idArg,function(data){
					//if($.ajaxCheck(data)) {
					if(data=='1') {
						_cancelOrder();
						_setBeginBtn(false);
					}
					//}
				});
			} else {
				alert("还不是排序状态");
			}
		}
		
		function _setOrder() {
			$(_that).find("tbody tr").each(function(index){
				if(_isSort) {
					$(this).find("td:last").html((index+1));
				} else
					$(this).append("<td>"+(index+1)+"</td>");
			});
		}
		return _init();
	};
})(jQuery);