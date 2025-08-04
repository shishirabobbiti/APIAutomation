import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DataDriven {

	public static void main(String[] args) throws IOException {
		getData("purchase","testData");
	
	}
	public static ArrayList<String> getData(String scenario,String sheetName) throws IOException {
		FileInputStream fis =new FileInputStream("C:\\Users\\Shishira Reddy\\Downloads\\Book1.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		int sheets=workbook.getNumberOfSheets();
		ArrayList<String>al=new ArrayList<String>();	
		for(int i=0;i<sheets;i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet=workbook.getSheetAt(i);
				Iterator<Row>row=sheet.iterator();
				Row firstRow=row.next();
				Iterator<Cell>cell=firstRow.iterator();
				int k=0;;
				int column=0;
				while(cell.hasNext()) {
				Cell value=cell.next();
				
				if(value.getStringCellValue().equalsIgnoreCase("Testcases")) {
					column=k;
				}
				k++;
				}
				System.out.println(column);
				while(row.hasNext()) {
					Row r= row.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(scenario)) {
					Iterator<Cell>cellPurchase=r.cellIterator();
					while(cellPurchase.hasNext()) {
						
						Cell purchasevalue=cellPurchase.next();
						if(purchasevalue.getCellType()==CellType.STRING) {
							al.add(purchasevalue.getStringCellValue());
						}
						else {
							
							al.add(NumberToTextConverter.toText(purchasevalue.getNumericCellValue()));
						}
						
					}
					}
				
				
				}
				System.out.println(al);
				
			}
		
		}
		return al;
	}

}
