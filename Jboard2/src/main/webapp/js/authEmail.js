/**
 * 이메일 인증
 */

// 이메일 인증
$(function(){
	
	let preventDoubleClick = false;
	
	// 이메일 인증번호 받기
	$('#btnEmailCode').click(function(){
		
		const email = $('input[name=email]').val();
		const jsonData = {
			"email":email
		};
		
		if(preventDoubleClick){ // 더블클릭 막기
			return;
		}
		
		preventDoubleClick = true;
		$('.resultEmail').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		
		setTimeout(function(){ // 1초 후에 서버 요청
			
			$.ajax({
				url:'/Jboard2/user/authEmail.do',
				type: 'GET',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					console.log(data);
					
					if(data.status > 0){
						$('.resultEmail').text('이메일을 확인 후 인증코드를 입력하세요.');
						$('.auth').show();
						$('input[name=email]').attr('readonly',true);
					} else {
						$('.resultEmail').text('인증코드 전송이 실패했습니다. 잠시 후 다시 시도해 주십시오.');
					}
					
					preventDoubleClick = false;
					
				}
			});
		},1000);
		
	});
	
	$('#btnEmailAuth').click(function(){
		
		const code = $('input[name=auth]').val();
		const jsonData = {
				"code":code
		}
		
		$.ajax({
			url:'/Jboard2/user/authEmail.do',
			type:'POST',
			data: jsonData,
			dataType:'json',
			success: function(data){
				
				console.log(data);
				
				if(data.result > 0){
					$('.resultEmail').css('color','green').text('이메일 인증이 완료 되었습니다.');
					isEmailOk = true;
				}else{
					$('.resultEmail').css('color','red').text('이메일 인증이 실패했습니다. 다시 시도하십시오.');
					isEmailOk = false;
				}
				
			}
		});
		
	});
	
});