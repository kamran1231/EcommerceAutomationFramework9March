package Utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	static Workbook workbook;
	static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {

		String path = System.getProperty("user.dir") + "/src/main/resources/testData/TestData.xlsx";

		FileInputStream fis;

		try {

			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

		} catch (Exception e) {

			e.printStackTrace();
		}

		sheet = workbook.getSheet(sheetName);

		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] data = new Object[rows - 1][cols];
		
		//Important Formatter
		DataFormatter formatter = new DataFormatter();

		for (int i = 1; i < rows; i++) {

			for (int j = 0; j < cols; j++) {
				
				
				data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
			}
		}
		
		return data;

	}

}
