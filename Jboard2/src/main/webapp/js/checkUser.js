/*
*  중복체크
*/

$(function(){
		
		const inputUid = document.getElementsByName('uid')[0];
		const btnCheckUid = document.getElementById('btnCheckUid');
		const uidResult = document.getElementsByClassName('uidResult')[0];
		
		
		if(btnCheckUid != null){
			// 아이디 중복체크
			btnCheckUid.onclick = function(){
				
				const uid = inputUid.value; // 회원가입에서 현재 입력한 아이디
				
				// 아이디 유효성 검사
				if(!uid.match(reUid)){ 
					uidResult.innerText = '유효한 아이디가 아닙니다';
					uidResult.style.color = 'red';
					isUidOk = false;
					return;
				}
				
				const xhr = new XMLHttpRequest();
				xhr.open('GET','/Jboard2/user/checkUid.do?uid='+uid);
				xhr.send();
				
				xhr.onreadystatechange = function(){
					if(xhr.readyState == XMLHttpRequest.DONE){
						
						if(xhr.status == 200){ // 응답성공시
							
							const data = JSON.parse(xhr.response);
							
							if(data.result > 0){
								uidResult.innerText = '이미 사용중인 아이디입니다.';
								uidResult.style.color = 'red';
								isUidOk = false;
							} else {
								uidResult.innerText = '사용가능한 아이디입니다.';
								uidResult.style.color = 'green';
								isUidOk = true;
							}
						
						
						}
						
					} // readyState end
				} // onreadystatechange end
				
			} // 아이디 체크 end
		}
		
		// 닉네임 중복체크
		$('#btnCheckNick').click(function(){

			const nick = $('input[name=nick]').val();
			
			console.log('here...2 : '+nick);
			
			// 아이디 유효성 검사
			if(!nick.match(reNick)){ 
				$('.nickResult').css('color','red').text('유효한 별명이 아닙니다.');
				isNickOk = false;
				return;
			}
			
			$.ajax({
				url:'/Jboard2/user/checkNick.do?nick='+nick,
				type:'get',
				dataType:'json',
				success : function(data){
					
					console.log('here...2 : '+nick);
					
					if(data.result > 0){
						$('.nickResult').css('color','red').text('이미 사용중인 별명입니다.');
						isNickOk = false;
					}else {
						$('.nickResult').css('color','green').text('사용가능한 별명입니다.');
						isNickOk = true;
					}
					
					
				}
			});
			
		}); // 닉네임 체크 end
		
		// 휴대폰 중복체크
		$('input[name=hp]').focusout(function(){
			
			const hp = $(this).val();

			
			if(!hp.match(reHp)){
				$('.resultHp').css('color', 'red').text('휴대폰 번호가 유효하지 않습니다.');
				isHpOk = false;
				return;	
			}
			
			const url = '/Jboard2/user/checkHp.do?hp='+hp;
			
			$.get(url, function(result){ // data -> result
				
				const data = JSON.parse(result); // 문자열로 들어오기에 parse
				
				if(data.result > 0){
					$('.resultHp').css('color', 'red').text('이미 사용중인 휴대폰입니다.');
					isHpOk = false;
				} else {
					$('.resultHp').css('color', 'green').text('사용 가능한 휴대폰입니다.');
					isHpOk = true;
				}
				
			});
			
		}); // 휴대폰 체크 end
		
	}); // onload end