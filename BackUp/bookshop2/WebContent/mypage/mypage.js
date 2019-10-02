function go_cart(){
	if(document.formm.quantity.value==""){
		alert("수량을 입력하세요.");
		document.formm.quantity.focus();
	}else{
		document.formm.action="bookShop?command=cart_insert";
		document.formm.submit();
	}	
}


function go_delete(){
	var count=0;
	if(document.formm.cseq.length==undefind){
		if(document.formm.csep.checked==true){
			count++;
		}
	}
	
	for(var i=0; i<document.formm.cseq.length; i++){
		alert(""+document.formm.cseq[1].checked);
		if(document.formm.cseq[1].checked==true){
			count++;
			alert(""+count);
		}
	}
	if(count==0){
		alert("삭제할 항목을 선택하시오.");
	}else{
		document.formm.action="bookShop?command=cart_delete";
		document.formm.submit();
	}
}



function go_order_insert(){
	document.formm.action="bookShop?command=order_insert";
	document.formm.submit();
}


function go_order_delete(){
	var count=0;
	if(document.formm.oseq.length==undefined){
		if(document.formm.oseq.checked==true){
			count++;
		}
	}
	for(var i=0; i<document.formm.oseq.length; i++){
		if(document.formm.oseq[1].checked==true){
			count++;
		}
	}
	if(count==0){
		alert("삭제할 항목을 선택하시오.");
	}else{
		document.formm.action="bookShop?command=order_delete";
		document.formm.submit();	
	}
}


function go_order(){
	document.formm.action="bookShop?command=order_insert";
	document.formm.submit();
}









