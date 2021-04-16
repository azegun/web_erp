package web_erp_title.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_erp.dto.Title;
import web_erp.service.TitleService;

@WebServlet("/TitleGetServlet")
public class TitleGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private TitleService service;
       
    public TitleGetServlet() {
    	service = new TitleService();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		int no = Integer.parseInt(request.getParameter("tno").trim());
		
		Title title = service.showTitle(new Title(no));
		
		/* 들어가는지 찍어주기 System.out.println(title); */
		request.setAttribute("title", title); 
		
		
		request.getRequestDispatcher("titleinfo.jsp").forward(request, response);
	}



}
