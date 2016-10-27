$(function() {
	var curAuth = $("#userAuthHref").html();
	$(".auth").each(function() {
		var sn = $(this).attr("sn");
		if(curAuth.indexOf("("+sn+")")<0) {$(this).remove();}
	});
});