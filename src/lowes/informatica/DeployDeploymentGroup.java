package lowes.informatica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeployDeploymentGroup
 */
@WebServlet("/DeployDeploymentGroup")
public class DeployDeploymentGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeployDeploymentGroup() {
		super();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = null;
		String msg = "";
		GetDeploymentProperties gp = new GetDeploymentProperties();

		PrintWriter out = response.getWriter();
		out.println("Properties Value " + gp.getPropValues(2) + "<br />");
		String rundeck_command = gp.getPropValues(2);
		String env = request.getParameter("environment");
		out.println("Environment Selected :" + env);
		String username=request.getParameter("username");
		String password=request.getParameter("password"); 
		if (request.getParameter("deplname") != null) {
			name = request.getParameter("deplname");
			Runtime r = Runtime.getRuntime();
			String cmd = new StringBuilder(rundeck_command).append(" ").append("-- -DEPLOY_DGROUP").append(" ").append(name).append(" ").append("-Password").append(" ").append(password).append(" ").append("-UserName").append(" ").append(username).toString();
			out.println("---------LOG OUTPUT----------------------<br />");
			out.println("[INFO] : Command being executed:"+rundeck_command+"<br/>");
			out.println("[INFO] : Please wait until Job Completes.... <br />");
			out.println("[INFO]: Environment Selected :" + env);
			out.println("[INFO]: Command Selected :"+cmd);
			Process p = r.exec(cmd);
			try {
				p.waitFor();
				BufferedReader inOut = new BufferedReader(
						new InputStreamReader(p.getInputStream()));

				while ((msg = inOut.readLine()) != null) {
					out.println(msg);

				}

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		} else {
			name = "";
		}
	
		out.println(name);
	}
}
