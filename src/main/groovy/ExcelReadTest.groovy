import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.ss.usermodel.Sheet

class ExcelReadTest {
    static void main(String[] args) {

        def filePath = "/Users/apus/IdeaProjects/GroovyTest/testexcel.xlsx"
        def file = new File(filePath)

        Workbook workbook = WorkbookFactory.create(file)
        def sheetCount = workbook.getNumberOfSheets()
        println("sheetCount -> $sheetCount")
        Sheet sheet = workbook.getSheetAt(0)
        for (final def row in sheet) {
            for (final def cell in row) {
                print(cell.toString()+" ")
            }
            println()
        }

//        Sheet sheet = workbook.getSheetAt(0)

//        for (Row row : sheet) {
//            for (Cell cell : row) {
//                println cell.toString() + "\t"
//            }
//            println ""
//        }

        workbook.close()

    }
}