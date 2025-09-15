package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviders {
    @DataProvider(name = "data")
    public String[][] get_all_data() throws IOException {
        String path = "C:\\Users\\QA\\PetStoreRestAssured\\Testdata\\Userdata.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path, "Sheet1");

        int row_num = excelUtility.getRowCount();
        int col_num = excelUtility.getColCount();

        // skip header -> row_num - 1
        String[][] api_data = new String[row_num - 1][col_num];

        for (int i = 1; i < row_num; i++) {  // start from row 1
            for (int j = 0; j < col_num; j++) {
                String cellValue = excelUtility.getCellData(i, j);

                // handle null cells -> empty string instead of null
                if (cellValue == null) {
                    api_data[i - 1][j] = "";
                } else {
                    api_data[i - 1][j] = cellValue;
                }
            }
        }
        excelUtility.close();
        return api_data;
    }

    @DataProvider(name = "Usernames")
    public String[] get_usernames() throws IOException {
        String path = "C:\\Users\\QA\\PetStoreRestAssured\\Testdata\\Userdata.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path, "Sheet1");

        int row_num = excelUtility.getRowCount();
        List<String> usernames = new ArrayList<>();

        for (int i = 1; i < row_num; i++) {  // skip header row
            String username = excelUtility.getCellData(i, 1);

            if (username != null && !username.trim().isEmpty()) {
                usernames.add(username.trim());
            }
        }

        excelUtility.close();
        return usernames.toArray(new String[0]);
    }
}
