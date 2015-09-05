
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getname
 */
@WebServlet("/getname")
public class getname extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public getname() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String name = null;
		PrintWriter out = response.getWriter();
 		if (request.getParameter("name") != null) {
			name = request.getParameter("name");
		} else {
			name = "";
		}
		out.println("You have successfully made Ajax Call:" + name);
		}
}
