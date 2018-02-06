function orgSearchById() {
	var orgId = $("#orgId").val();
	if (orgId == "") {
		ComAlert.show(2, 'Id 为空');
		return;
	}

	$.ajax({
				type : 'POST',
				url : base_url + '/org/selectById',
				data : {
					"id" : orgId
				},
				dataType : "json",
				success : function(data) {
					if (data.orgInfo != null) {
						document.getElementById('orgName').value = data.orgInfo.org_cn_name;
						document.getElementById('orgShort').value = data.orgInfo.org_cn_short;
					} else {
						ComAlert.show(2, '没用相应的机构信息');
					}
				},
			});
}

function orgSearchByName() {
	var orgName = $("#orgName").val();
	if (orgName == "") {
		ComAlert.show(2, 'Id 为空');
		return;
	}

	$.ajax({
		type : 'POST',
		url : base_url + '/org/selectByName',
		data : {
			"name" : orgName
		},
		dataType : "json",
		success : function(data) {
			if (data.orgInfo != null) {
				document.getElementById('orgId').value = data.orgInfo.org_id;
				document.getElementById('orgShort').value = data.orgInfo.org_cn_short;
			} else {
				ComAlert.show(2, '没用相应的机构信息');
			}
		},
	});
}

function entSearchById() {
	ComAlert.show(2, '暂不支持企业的查询');
}

function entSearchByName() {
	ComAlert.show(2, '暂不支持企业的查询');
}

function fundSearchByName() {
	var fundName = $("#fundName").val();
	if (fundName == "") {
		ComAlert.show(2, 'Id 为空');
		return;
	}

	$.ajax({
		type : 'POST',
		url : base_url + '/fund/selectByName',
		data : {
			"name" : fundName
		},
		dataType : "json",
		success : function(data) {
			if (data.fundInfo != null) {
				document.getElementById('fundId').value = data.fundInfo.fund_id;
			} else {
				ComAlert.show(2, '没用相应的基金信息');
			}
		},
	});
}

function fundSearchById() {
	var id = $("#fundId").val();
	if (id == "") {
		ComAlert.show(2, 'Id 为空');
		return;
	}

	$.ajax({
		type : 'POST',
		url : base_url + '/fund/selectById',
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			if (data.fundInfo != null) {
				document.getElementById('fundName').value = data.fundInfo.fund_name;
			} else {
				ComAlert.show(2, '没用相应的基金信息');
			}
		},
	});
}

function fundSearchByUUID() {
	ComAlert.show(2, '暂不支持企业相关的查询');
}

function userSearchByName() {
	var orgName = $("#userName").val();
	if (orgName == "") {
		ComAlert.show(2, 'Id 为空');
		return;
	}

	$.ajax({
		type : 'POST',
		url : base_url + '/user/getFromUserName',
		data : {
			"name" : orgName
		},
		dataType : "json",
		success : function(data) {
			if (data.orgInfo != null) {
//				document.getElementById('userId').value = data.userInfo.user_id;
				ComAlert.show(2, '暂时不可用'); //这个要处理成列表形式
			} else {
				ComAlert.show(2, '没用相应的用户信息');
			}
		},
	});
}

function userSearchById() {
	var id = $("#userId").val();
	if (id == "") {
		ComAlert.show(2, 'Id 为空');
		return;
	}

	$.ajax({
		type : 'POST',
		url : base_url + '/user/getFromUserId',
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			if (data.userInfo != null) {
				document.getElementById('userName').value = data.userInfo.user_name;
			} else {
				ComAlert.show(2, '没用相应的用户信息');
			}
		},
	});
}