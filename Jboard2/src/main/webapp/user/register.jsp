<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	
	window.onload = function(){
		
		const inputUid = document.getElementsByName('uid')[0];
		const btnCheckUdi = document.getElementById('btnCheckUid');
		const uidResult = document.getElementsByClassName('uidResult')[0];
		
		btnCheckUid.onclick = function(){
			
			const uid = inputUid.value; // 회원가입에서 현재 입력한 아이디
			
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
							
						} else {
							uidResult.innerText = '사용가능한 아이디입니다.';
							uidResult.style.color = 'green';
						}
					
					
					}
					
				} // readyState end
			} // onreadystatechange end
			
		} // 아이디 체크 end
		
		$('#btnCheckNick').click(function(){

			const nick = $('input[name=nick]').val();
			
			$.ajax({
				url:'/Jboard2/user/checkNick.do?nick='+nick,
				type:'get',
				dataType:'json',
				success : function(data){
					
					if(data.result > 0){
						$('.resultHp').css('color','red').text('이미 사용중인 별명입니다.');
					}else {
						$('.resultHp').css('color','green').text('사용가능한 별명입니다.');
					}
					
					
				}
			});
			
		}); // 닉네임 체크 end
		
		$('input[name=hp]').focusout(function(){
			
			const hp = $(this).val();
			const url = '/Jboard2/user/checkHp.do?hp='+hp;
			
			$.get(url, function(result){ // data -> result
				
				const data = JSON.parse(result); // 문자열로 들어오기에 parse
				
				if(data.result > 0){
					$('.resultHp').css('color', 'red').text('이미 사용중인 휴대폰입니다.');
				} else {
					$('.resultHp').css('color', 'green').text('사용 가능한 휴대폰입니다.');
				}
				
			});
			
		}); // 휴대폰 체크 end
		
	} // onload end

</script>
<%@ include file="./_header.jsp" %>
        <main id="user">
            <section class="register">

                <form action="/Jboard2/user/register.do" method="post">
                    <table border="1">
                        <caption>사이트 이용정보 입력</caption>
                        <tr>
                            <td>아이디</td>
                            <td>
                                <input type="text" name="uid" placeholder="아이디 입력"/>
                                <button type="button" id="btnCheckUid"><img src="../img/chk_id.gif" alt="중복확인"/></button>
                                <span class="uidResult"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td><input type="password" name="pass1" placeholder="비밀번호 입력"/></td>
                        </tr>
                        <tr>
                            <td>비밀번호 확인</td>
                            <td><input type="password" name="pass2" placeholder="비밀번호 입력 확인"/></td>
                        </tr>
                    </table>

                    <table border="1">
                        <caption>개인정보 입력</caption>
                        <tr>
                            <td>이름</td>
                            <td>
                                <input type="text" name="name" placeholder="이름 입력"/>                        
                            </td>
                        </tr>
                        <tr>
                            <td>별명</td>
                            <td>
                                <p class="nickInfo">공백없는 한글, 영문, 숫자 입력</p>
                                <input type="text" name="nick" placeholder="별명 입력"/>
                                <button type="button" id="btnCheckNick"><img src="../img/chk_id.gif" alt="중복확인"/></button>
                                <span class="nickResult"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td>
                                <input type="email" name="email" placeholder="이메일 입력"/>
                                <button type="button"><img src="../img/chk_auth.gif" alt="인증번호 받기"/></button>
                                <div class="auth">
                                    <input type="text" name="auth" placeholder="인증번호 입력"/>
                                    <button type="button"><img src="../img/chk_confirm.gif" alt="확인"/></button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>휴대폰</td>
                            <td>
                            	<input type="text" name="hp" placeholder="휴대폰 입력"/>
                            	<span class="resultHp"></span>	
                            </td>
                        </tr>
                        <tr>
                            <td>주소</td>
                            <td>
                                <input type="text" name="zip" placeholder="우편번호"/>
                                <button type="button"><img src="../img/chk_post.gif" alt="우편번호찾기"/></button>
                                <input type="text" name="addr1" placeholder="주소 검색"/>
                                <input type="text" name="addr2" placeholder="상세주소 입력"/>
                            </td>
                        </tr>
                    </table>

                    <div>
                        <a href="/Jboard2/user/login.do" class="btn btnCancel">취소</a>
                        <input type="submit" value="회원가입" class="btn btnRegister"/>
                    </div>

                </form>

            </section>
        </main>
<%@ include file="./_footer.jsp" %>