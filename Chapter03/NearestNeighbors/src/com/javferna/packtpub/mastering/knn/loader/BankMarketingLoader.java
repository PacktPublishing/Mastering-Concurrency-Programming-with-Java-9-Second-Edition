package com.javferna.packtpub.mastering.knn.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.javferna.packtpub.mastering.knn.data.BankMarketing;

/**
 * Class that loads the examples of the Bank Marketing data set from a file
 * @author author
 *
 */
public class BankMarketingLoader {

	/**
	 * Method that loads the examples of the Bank Marketing data set from a file
	 * @param dataPath Path to the file where the data items are stored
	 * @return List of BankMarketing examples
	 */
	public List<BankMarketing> load (String dataPath) {
		Path file=Paths.get(dataPath);
		List<BankMarketing> dataSet=new ArrayList<>();
		try (InputStream in = Files.newInputStream(file);
			    BufferedReader reader =
			      new BufferedReader(new InputStreamReader(in))) {
			    String line = null;
			    while ((line = reader.readLine()) != null) {
			    	String data[]=line.split(";");
			    	BankMarketing dataObject=new BankMarketing();
			    	dataObject.setData(data);
			    	dataSet.add(dataObject);
			    }
			} catch (IOException x) {
			  x.printStackTrace();
			} catch (Exception e) {
			  e.printStackTrace();
			}
		return dataSet;
	}
}
