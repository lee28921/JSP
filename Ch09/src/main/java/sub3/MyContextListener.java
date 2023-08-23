package sub3;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// WAS 실행/종료를 감시하는 옵저버(컴포넌트)
public class MyContextListener implements ServletContextListener{

	public MyContextListener() {
		System.out.println("MyContextListener()...");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized()...");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed()...");
	}
}
