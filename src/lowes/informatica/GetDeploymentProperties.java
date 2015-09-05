package lowes.informatica;
 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 
/**
 * @author Ahshan M.D
 * 
 */
 
public class GetDeploymentProperties{
	String result = "";
	InputStream inputStream1;
 
	public String getPropValues(Integer in) throws IOException {
		String rundeck_command1=null;
		try {
			Properties prop = new Properties();
			String propFileName = "deploymentconfig.properties";
			
 
			inputStream1 = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream1 != null) {
				prop.load(inputStream1);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
		    if(in.equals(1)){
			rundeck_command1 = prop.getProperty("CREATE_RUNDECK_COMMAND");
		    }
		    
		    if(in.equals(2)){
				rundeck_command1 = prop.getProperty("DEPLOY_RUNDECK_COMMAND");
			    }
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream1.close();
		}
		return rundeck_command1;
	}
}