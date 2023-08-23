package sub3;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class MySessionListener implements HttpSessionAttributeListener {
	int count = 0;
	
	public MySessionListener() {
		System.out.println("MySessionListener()...");
	}
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) { // 로그인할때
		System.out.println("attributeAdded()...");
		
		count++;
		System.out.println("로그인 사용자 수 : "+count);
	}
	
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) { // 로그아웃할때
		System.out.println("attributeRemoved()...");
		
		count--;
		System.out.println("로그인 사용자 수 : "+count);
	}
}
