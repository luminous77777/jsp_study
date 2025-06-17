package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextPathLisnter implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) { //톰캣 구동시 딱 한번만
		ServletContext sc = sce.getServletContext();
		sc.setAttribute("cp", sc.getContextPath()); // /pbl
	}

	
}
