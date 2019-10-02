function go_save(){
	if(document.formm.id.value==""){
		alert("아이디를 입력하세요.");
		document.formm.id.focus();
		
	}else if(document.formm.id.value!=document.formm.reid.value){
		alert("중복아이디를 클릭하세요.");
		document.formm.id.focus();
	
	}else if(document.formm.pwd.value==""){
		alert("비밀번호를 입력하세요.");
		document.formm.pwd.focus();
	
	}else if(document.formm.pwd.value!=document.formm.pwdCheck.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.formm.pwd.focus();
	
	}else if(document.formm.name.value==""){
		alert("이름 입력하세요.");
		document.formm.name.focus();
	
	}else if(document.formm.Email.value==""){
		alert("이메일을 입력하세요.");
		document.formm.Email.focus();
	
	}else{
		document.formm.action="bookShop?command=join";
		document.formm.submit();
	
	}
//	return true;
}

function idcheck(){
	if(document.formm.id.value==""){
		alert("아이디를 입력하세요.");
		document.formm.id.focus();
		return;
	}
	
	var url="bookShop?command=id_check_form&id="+ document.formm.id.value;
	window.open(url, "_blank_1","toolbar=no,menubar=no,scrollbars=yes,resizable=no,width=500,height=300");
}

function post_zip(){
	var url="bookShop?command=find_zip_num";
	window.open(url, "_blank_1","toolbar=no,menubar=no,scrollbars=yes,resizable=no," +
			"width=550,height=300, top=300, left=300");
}

function go_next(){
	if(document.formm.okon1[0].checked==true){
		document.formm.action="bookShop?command=join_form";
		document.formm.submit();
	}else if(document.formm.okon1[1].checked==true){
		alert("약관에 동의 해야 함.")
	}
	
}









