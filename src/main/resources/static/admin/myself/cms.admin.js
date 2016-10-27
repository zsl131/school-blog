(function($) {
	$.getBaseHref = function(data) {
		//获取一个基本的href，把所有的参数都去除
		if(data && data.indexOf("?")>=0) {
			return data.substr(0,data.indexOf("?"));
		}
		return data;
	};
})(jQuery);