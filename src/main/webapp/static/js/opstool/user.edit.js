function orgSearchById(){
         var orgId=$("#orgId").val();
         if(orgId==""){
        	 alert("id is nill");
             return;
         }

         $.ajax({
				type : 'POST',
				url : base_url + '/org/selectById',
				data : {"id":orgId},
				dataType : "json",
				success : function(data){
					if (data.orgInfo != null ) {
						document.getElementById('orgName').value = data.orgInfo.org_cn_name;
					} else {
						ComAlert.show(2, '没用相应的机构信息');
					}
				},
			});
     }
	
	function orgSearchByName(){
        var orgName=$("#orgName").val();
        if(orgName==""){
       	 alert("id is nill");
            return;
        }

        $.ajax({
				type : 'POST',
				url : base_url + '/org/selectByName',
				data : {"name":orgName},
				dataType : "json",
				success : function(data){
					if (data.orgInfo != null ) {
						document.getElementById('orgId').value = data.orgInfo.org_id;
					} else {
						ComAlert.show(2, '没用相应的机构信息');
					}
				},
			});
    }
	
	function searchUser(){
        
        var key=$("#userName").val();
        var type=$("select option:selected").val();

        var url = base_url + '/user/list?key='+key+'&type='+type;
        document.location.href = url;
    }
