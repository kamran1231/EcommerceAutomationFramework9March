package Utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
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
		
		if (sheet == null) {

		    throw new RuntimeException(
		        "Sheet not found: " + sheetName);
		}

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();

		DataFormatter formatter = new DataFormatter();

		List<Object[]> dataList = new ArrayList<>();

		for (int i = 1; i <= rows; i++) {

		    Row row = sheet.getRow(i);

		    if (row == null)
		        continue;

		    boolean isRowEmpty = true;

		    Object[] rowData = new Object[cols];

		    for (int j = 0; j < cols; j++) {

		        Cell cell = row.getCell(j);

		        String cellValue =
		                formatter.formatCellValue(cell);

		        if (!cellValue.isEmpty()) {

		            isRowEmpty = false;
		        }

		        rowData[j] = cellValue;
		    }

		    if (!isRowEmpty) {

		        dataList.add(rowData);
		    }
		}

		Object[][] data =
		        new Object[dataList.size()][cols];

		for (int i = 0; i < dataList.size(); i++) {

		    data[i] = dataList.get(i);
		}

		return data;

	}

}
