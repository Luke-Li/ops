/*
 * jQuery File Upload Plugin JS Example
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2010, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT
 */

/* global $, window */
function isBlankTaskName() {
	if ($("input[name='taskName']").val() == undefined
			|| $("input[name='taskName']").val() == "") {
		return true;
	} else {
		return false;
	}
}
function getJobName(){
	return $("input[name='taskName']").val();
}
function listFile(jobName) {
	// 如果任务名称不为空值，就加载该任务下的JAR文件
	// Load existing files:
	$('#fileupload').addClass('fileupload-processing');
	$.ajax({
		// Uncomment the following to send cross-domain cookies:
		// xhrFields: {withCredentials: true},
		// url: $('#fileupload').fileupload('option', 'url')+"list",
		url : base_url + "/fileUpload/list",
		data : {
			"jobName" : jobName
		},
		dataType : 'json',
		context : $('#fileupload')[0]
	}).always(function() {
		$(this).removeClass('fileupload-processing');
	}).done(function(result) {
		$(this).fileupload('option', 'done').call(this, $.Event('done'), {
			result : result
		});
	});
}

$(function() {
	'use strict';
	// Initialize the jQuery File Upload widget:
	var url = base_url + '/fileUpload/';
	$('#fileupload').fileupload({
		// Uncomment the following to send cross-domain cookies:
		// xhrFields: {withCredentials: true},
		url : url
	});

	// 文件上传前触发事件
	$('#fileupload').bind('fileuploadsubmit', function(e, data) {
		data.url = url + "upload";
		data.formData = {
			"jobName" : getJobName()
		};// 如果需要额外添加参数可以在这里添加
		if (isBlankTaskName()) {
			alert("任务名称不能为空值!");
			return false;
		}
	});
	$('#fileupload').bind('fileuploaddrop', function(e, data) {
		data.formData = {
			"jobName" : getJobName()
		}; // 如果需要额外添加参数可以在这里添加
		if (isBlankTaskName()) {
			alert("任务名称不能为空值!");
			return false;
		}
	});

	// Enable iframe cross-domain access via redirect option:
	$('#fileupload').fileupload('option', 'redirect',
			window.location.href.replace(/\/[^\/]*$/, '/cors/result.html?%s'));
});
