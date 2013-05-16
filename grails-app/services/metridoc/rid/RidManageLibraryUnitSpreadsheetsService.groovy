package metridoc.rid

import org.codehaus.groovy.grails.io.support.ClassPathResource

class RidManageLibraryUnitSpreadsheetsService {

    final def DEFAULT_SPREADSHEET_DIRECTORY = System.getProperty("user.home") + "/.metridoc/files/rid/libraryUnit"
    File unitSpreadsheetDir = new File(DEFAULT_SPREADSHEET_DIRECTORY)

    def download(response, flash, params) {
        def file = new File(DEFAULT_SPREADSHEET_DIRECTORY + "/" + params.sname)
        if (!file.exists()) {
            flash.message = "File not found"
        }
        try {
            response.setContentType('application/vnd.openxmlformats-officedocument.spreadsheetml.sheet')
            response.setHeader("Content-disposition", "filename=${file.name}")
            response.outputStream << file.newInputStream() // Performing a binary stream copy
        } catch (Exception e) {
            flash.alerts << e.message
        }
    }

    def transferSpreadsheets() {

        unitSpreadsheetDir.mkdirs()

        def resource = new ClassPathResource("spreadsheet")
        if (resource.exists()) {
            def ssDir = resource.getFile()
            ssDir.eachFile {
                if (it.isFile()) {
                    if (!(new File(unitSpreadsheetDir, it.getName())).exists()) {
                        log.info "Transferring " + it.getName()
                        it.renameTo(new File(unitSpreadsheetDir, it.getName()))
                    } else {
                        log.info it.getName() + " is already in " + unitSpreadsheetDir
                    }
                }
            }
        } else {
            log.error "Can't find classpath ${resource.path}, so can't transfer spreadsheets to the local directory .metridoc/files: "
        }
    }

}
