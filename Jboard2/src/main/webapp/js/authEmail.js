/**
 * 이메일 인증
 */

// 이메일 인증
$(function(){
	
	let preventDoubleClick = false;
	
	// 이메일 인증번호 받기
	$('#btnEmailCode').click(function(){
		
		const type = $('input[name=type]').val();
		const uid = $('input[name=uid]').val();
		const name = $('input[name=name]').val();
		const email = $('input[name=email]').val();
		const jsonData = {
			"type":type,
			"uid":uid,
			"name":name,
			"email":email
		};
		
		if(preventDoubleClick){ // 더블클릭 막기
			return;
		}
		
		preventDoubleClick = true;
		$('.resultEmail').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		$('.resultEmailForId').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		$('.resultEmailForPass').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		
		setTimeout(function(){ // 1초 후에 서버 요청
			
			$.ajax({
				url:'/Jboard2/user/authEmail.do',
				type: 'GET',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					console.log(data);
					
					if(data.result > 0){ // 중복제어
						$('.resultEmail').css('color','green').text('이미 사용중인 이메일 입니다.');
						isEmailOk = false;
						
						// 아이디, 비밀번호 찾기
						if(data.status > 0){ // 인증받기
							$('.resultEmailForId').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.resultEmailForPass').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('input[name=auth]').prop('disabled',false);
						} else {
							$('.resultEmailForId').text('인증코드 전송이 실패했습니다. 잠시 후 다시 시도해 주십시오.');
							$('.resultEmailForPass').text('인증코드 전송이 실패했습니다. 잠시 후 다시 시도해 주십시오.');
						}
						
					}else{
						
						// 회원가입
						if(data.status > 0){ // 인증받기
							$('.resultEmail').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.auth').show();
							$('input[name=email]').attr('readonly',true);
						} else {
							$('.resultEmail').css('color','red').text('인증코드 전송이 실패했습니다. 잠시 후 다시 시도해 주십시오.'); // 회원가입
							$('.resultEmailForId').css('color','red').text('해당하는 사용자, 이메일이 일치하지 않습니다.'); // 로그인찾기
							$('.resultEmailForPass').css('color','red').text('해당하는 사용자, 이메일이 일치하지 않습니다.'); // 비밀번호찾기
						}
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
					$('.resultEmailForId').css('color','green').text('이메일 인증이 완료 되었습니다.');
					$('.resultEmailForPass').css('color','green').text('이메일 인증이 완료 되었습니다.');
					isEmailOk = true;
				}else{
					$('.resultEmail').css('color','red').text('이메일 인증이 실패했습니다. 다시 시도하십시오.');
					$('.resultEmailForId').css('color','red').text('이메일 인증이 실패했습니다. 다시 시도하십시오.');
					$('.resultEmailForPass').css('color','red').text('이메일 인증이 실패했습니다. 다시 시도하십시오.');
					isEmailOk = false;
				}
				
			}
		});
		
	});
	
});