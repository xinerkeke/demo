package practiceTwo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * ∂¡»°≈‰÷√
 * @author pc
 *
 */
public class ParseProperties {
	private Properties pro = new Properties();

	public ParseProperties(String propertiespath) {
		this.loadProperties(propertiespath);
	}
	
	private void loadProperties(String propertiespath) {
		try {
			InputStream in = new FileInputStream(propertiespath);
			pro.load(in);
			
		} catch (Exception e) {
			
		}
	}
	
	public String getProperty(String keyname) {
		return pro.getProperty(keyname).trim();
	}
}
