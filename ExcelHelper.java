package swiggy.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelHelper {

	/**
	 * it will read the data from excel sheet
	 * @param excellocation
	 * @param sheetname
	 * @return
	 */
	public String[][] excelData(String excellocation, String sheetname){
		try {
			String datasets[][]=null;
			
			FileInputStream file=new FileInputStream(new File(excellocation));
			
			XSSFWorkbook workbook=new XSSFWorkbook(file);
			
			XSSFSheet sheet=workbook.getSheet(sheetname);
			
			//count no. of active rows
			int  totalRow=sheet.getLastRowNum();
			
			// count no. of active columns in row
			
			int totalColumn=sheet.getRow(0).getLastCellNum();
			
			//create array for rows and columns
			
			datasets=new String[totalRow][totalColumn];
			
			// iterate each row one by one
			
			Iterator<Row> rowIterator = sheet.iterator();
			
			int i=0;
			while (rowIterator.hasNext()){
				//System.out.println(i);
								
				Row row = rowIterator.next();
				
				// for each row iterate through all columns
				
				Iterator<Cell> cellIterator = row.iterator();
				
				int j=0;
				
				while(cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					
					if(cell.getStringCellValue().contains("userName")){
						break;
					}
					
					// check the cell type accordingly
					
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						datasets[i][j++]=cell.getStringCellValue();
						//System.out.println(cell.getNumericCellValue());						
						break;
					case Cell.CELL_TYPE_STRING:
						datasets[i][j++]=cell.getStringCellValue();
						//System.out.println(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						datasets[i][j++]=cell.getStringCellValue();
						//System.out.println(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						datasets[i][j++]=cell.getStringCellValue();
						//System.out.println(cell.getStringCellValue());
						break;

					default:
						break;
					}
				}
				
				//System.out.println();
				
			}
			file.close();
			return datasets;		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	/**
	 * it will update the result in excel sheet
	 * @param excellocation
	 * @param sheetname
	 * @param testcasename
	 * @param status
	 */
	
public void updateresult(String excellocation, String sheetname, String testcasename, String status ){
		
	try {
		FileInputStream file= new FileInputStream(new File(excellocation));
		
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		
		XSSFSheet sheet=workbook.getSheet(sheetname);
		
		//count no. of rows		
		int totalRow=sheet.getLastRowNum()+1;
		
		//count no. of columns
		
		for(int i=1; i<totalRow; i++){
			
			XSSFRow row=sheet.getRow(i);
			String cell=row.getCell(1).getStringCellValue();
			
			if(cell.contains(testcasename)){
				row.createCell(2).setCellValue(status);
				file.close();
				//System.out.println("Result Updated");
				FileOutputStream outFIle=new FileOutputStream(new File(excellocation));
				workbook.write(outFIle);
				outFIle.close();
				break;				
			}			
		}		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
      /**
       * it will read multiple data in single excel sheet
       * @param excellocation
       * @param sheetName
       * @param testName
       * @return
       */

    public Object[][] masterExcelData(String excellocation, String sheetName, String testName){
		try {
			String dataSets[][]=null;
			FileInputStream file=new FileInputStream(new File(excellocation));
			
			XSSFWorkbook workbook=new XSSFWorkbook(file);
			
			XSSFSheet sheet=workbook.getSheet(sheetName);
			
			//count no. of rows		
			int totalRow=sheet.getLastRowNum();
			int totalColumn=0;
			
			Iterator<Row> rowIterator = sheet.iterator();
			int i=0;
			int count=1;
			while(rowIterator.hasNext() && count==1 || count==2){
				//System.out.println(i);
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j=0;
				while(cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					if(cell.getStringCellValue().contains(testName + "end")){
						count=0;
						break;
					}
					
					if(cell.getStringCellValue().contains(testName + "start")){
					// count no. of active columns in row
						totalColumn=row.getPhysicalNumberOfCells() - 1;
					// create array of rows and column	
						dataSets=new String[totalRow][totalColumn];							
					}
					
					if(cell.getStringCellValue().contains(testName + "start")|| count==2 ){
						//System.out.println(sheetName + "start");
						count=2;
									
					
// check the cell type accordingly
					
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						dataSets[i-1][j++]=cell.getStringCellValue();
						//System.out.println(cell.getNumericCellValue());						
						break;
					case Cell.CELL_TYPE_STRING:
						dataSets[i-1][j++]=cell.getStringCellValue();
						//System.out.println(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						dataSets[i-1][j++]=cell.getStringCellValue();
						//System.out.println(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						dataSets[i-1][j++]=cell.getStringCellValue();
						//System.out.println(cell.getStringCellValue());
						break;
					}

					}
				}
				//System.out.println("");
				i++;
			}
			file.close();
			return parseData(dataSets, totalColumn);
			
		}			
		catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    	
    }
    /**
     * Parsing the data
     * @param data
     * @param colSize
     * @return
     */
    
    public Object[][] parseData(Object[][] data, int colSize){
    	
    	// Creating array list to  store data 
    ArrayList<ArrayList<String>> list=new ArrayList<ArrayList<String>>();
    
    /* This array list will store one array index data, every array index
     has three sets of data */
    
    ArrayList<String> list1;
    
   // System.out.println(data.length);
    
    //running forloop on array size
    
    for(int i=0; i<data.length; i++){
    	// create a list to store the elements !=zero
    	//System.out.println(data[i].length);
    	
    	list1=new ArrayList<String>();
    	
    	// this for loop runs on array index, since each array index 
    	// has three sets of data
    	
    	for(int j=0; j<data[i].length; j++){
    		
    		// this if condition will checks null
    		
    		if(data[i][j] !=null){
    			list1.add((String)data[i][j]);
    		}
    	}
    	
  // once  all array index data is entered in array list 
  // then put this data in parent array list
    			
    			if(list1.size()>0){
    				list.add(list1);
    			}
    		}
    // convert array list data into 2D array
    			
    	Object[][] arr2d=new Object[list.size()][colSize];	
    	// run loop on array list data
    	
    	for(int i=0; i<list.size(); i++){
    		// every array index has inner array index
    		
    		 ArrayList<String> t = list.get(i);
    		 
    		 // loop for inner array
    		 
    		 for(int j=0; j<t.size(); j++){
    			 
    			 arr2d[i][j]=t.get(j);
    		 }
    	}
    	//System.out.println(list);
    	//System.out.println(arr2d);
    	   
		return arr2d;
    	
    }

	/*
	public static void main(String[] args){
		
		String excellocation="C:\\Users\\prash\\Desktop\\Selenium\\javaprojects_JDK8\\dataDrivenFrame\\src\\main\\resources\\testData\\DataDriven.xlsx";
		String sheetname="Result";
		ReaddatafromExcel excel = new ReaddatafromExcel();
		String[][] data=excel.excelData(excellocation, sheetname);
		System.out.println(data);
		excel.updateresult(excellocation, sheetname, "Login","Fail");
		excel.updateresult(excellocation, sheetname, "Registration","pass");
		excel.updateresult(excellocation, sheetname, "Dashboard","Pass");
		excel.updateresult(excellocation, sheetname, "Payment","Fail");		
		
	}
*/
}
