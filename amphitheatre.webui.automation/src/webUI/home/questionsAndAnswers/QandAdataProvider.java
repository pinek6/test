package webUI.home.questionsAndAnswers;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import au.com.bytecode.opencsv.CSVReader;

public class QandAdataProvider {
	
	//static String csvFileName = "resources/qandaInputsQ1.csv";
	static String csvFileName = "resources/qandaDemo.csv";
	
	/*Set test inputs*/
	private String userType;
	private String contentType;
	private String CRUD;
	private String Anonymous;
	private String itemType;
	private String myYN;
	private String currentLine;
	
	
   public void setUserType(String userType){
	   this.userType = userType;
   }
   public void setContentType(String contentType){
	   this.contentType = contentType;
   }
   public void setCRUD(String CRUD){
	   this.CRUD = CRUD;
   }
   public void setAnonymous(String Anonymous){
	   this.Anonymous = Anonymous;
   }
   public void setItemType(String itemType){
	   this.itemType = itemType;
   }
   public void setMyYN(String myYN){
	   this.myYN = myYN;
   }
   public void setCurrentLine(String currentLine){
	   this.currentLine = currentLine;
   }
   public String getUserType(){
	   return userType;
   }
   public String getContentType(){
	   return contentType;
   }
   public String getCRUD(){
	   return CRUD;
   }
   public Boolean getAnonymous(){
	   return Boolean.valueOf(Anonymous);
   }
   public String getItemType(){
	   return itemType;
   }
   public Boolean getMyYN(){
	   return Boolean.valueOf(myYN);
   }
	
	@DataProvider(name="qandaInputs")
	public static Object[][] qandaInputs() throws IOException{
			CSVReader csvReader = new CSVReader(new FileReader(csvFileName), ',', '\'', 1);
			List<String[]>dataList = csvReader.readAll();
			Object[][] data = new Object[dataList.size()][1];
			List<QandAdataProvider> regList = new ArrayList <QandAdataProvider>();
			
			int iterator = 0;
			
			for (String[] strArray:dataList){
			   QandAdataProvider QandA = new QandAdataProvider();
			   QandA.setUserType(strArray[0].trim());
			   QandA.setContentType(strArray[1].trim());
			   QandA.setCRUD(strArray[2].trim());
			   QandA.setAnonymous(strArray[3].trim());
			   QandA.setItemType(strArray[4].trim());
			   QandA.setMyYN(strArray[5].trim());
			  
			   iterator++;
			   int size = dataList.size();
			   String str = "["+iterator+"/"+size+"]";
			   QandA.setCurrentLine(str);
			   
			   regList.add(QandA);
		   }
		   for(int i=0; i<data.length; i++){
			   for(int j=0; j<data[i].length; j++) {
				   data[i][j]=regList.get(i);
			   }
		   }
		  
		csvReader.close();
		
		return data;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("INPUTS").append(currentLine).append(": ")
			   .append(userType).append("/")
			   .append(contentType).append("/")
			   .append(CRUD).append("/")
			   .append(Anonymous).append("/")
			   .append(itemType).append("/")
			   .append(myYN);
		return builder.toString();
	}
	
}
