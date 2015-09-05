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

@WebServlet("/createdeployment")
public class CreateDeployment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateDeployment() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String name = null;
		String msg = "";
		GetDeploymentProperties gp = new GetDeploymentProperties();

		PrintWriter out = response.getWriter();
		
		out.println("Properties Value " + gp.getPropValues(1) + "<br />");
		String rundeck_command = gp.getPropValues(1);
		String env = request.getParameter("environment");
		out.println("---------LOG OUTPUT----------------------<br />");
		out.println("[INFO] : Command being executed:"+rundeck_command+"<br/>");
		out.println("[INFO] : Please wait until Job Completes.... <br />");
		out.println("[INFO]: Environment Selected :" + env);
		if (request.getParameter("deplname") != null) {
			name = request.getParameter("deplname");
			Runtime r = Runtime.getRuntime();
			String cmd = new StringBuilder(rundeck_command).append(" ").append(name).toString();
			out.println("<br />[INFO]: Command with Parameter == "+name);
			Process p = r.exec(cmd);
			try {
				p.waitFor();
				BufferedReader inOut = new BufferedReader(
						new InputStreamReader(p.getInputStream()));

				while ((msg = inOut.readLine()) != null) {
					out.println(msg);

				}

			} catch (InterruptedException e) {
				out.println("[ERROR]: Unable to Process the Request <br /> ");
				e.printStackTrace();

			}

		} else {
			name = "";
		}
		 out.println("<br /> [SUCCESS] : Create DeploymentGroup" + " " + name + " "+ "has been executed Successfully<br />");
	}
}
