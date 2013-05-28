package metridoc.rid

import grails.converters.JSON
import org.apache.poi.ss.usermodel.Workbook
import org.apache.shiro.SecurityUtils
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartFile

import java.text.SimpleDateFormat

class RidTransactionController {

    static homePage = [title: "Research Consultation & Instruction Database",
            description: "Add/Update/Review data on consultation and instructional activity"]

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def ridTransactionService
    def spreadsheetService
    def ridManageLibraryUnitSpreadsheetsService
    //def scaffold = true

    def ajaxChooseType = {
        def response = ridTransactionService.ajaxMethod(params)
        render response as JSON
    }

    def index() {
        session.setAttribute("transType", new String("consultation"))//Sets default mode to consultation
        redirect(action: "create")
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (session.getAttribute("transType") == "consultation") {
            [ridTransactionInstanceList: RidConsTransaction.list(params), ridTransactionInstanceTotal: RidConsTransaction.count(),
                    ridTransactionAllList: RidConsTransaction.list()]
        } else {
            [ridTransactionInstanceList: RidInsTransaction.list(params), ridTransactionInstanceTotal: RidInsTransaction.count(),
                    ridTransactionAllList: RidInsTransaction.list()]
        }
    }

    def templateList() {
        if (SecurityUtils.getSubject().getPrincipal()) {
            if (session.getAttribute("transType") == "consultation") {
                def query = RidConsTransactionTemplate.where {
                    templateOwner == SecurityUtils.getSubject().getPrincipal().toString()
                }
                [ridTransactionInstanceList: query.list()]
            } else {
                def query = RidInsTransactionTemplate.where {
                    templateOwner == SecurityUtils.getSubject().getPrincipal().toString()
                }
                [ridTransactionInstanceList: query.list()]
            }
        } else {
            redirect(action: "create")
        }
    }

    def create() {
        session.setAttribute("prev", new String("create"))
        if (session.getAttribute("transType") == "consultation") {
            try {
                RidConsTransactionBase ridTransactionInstance = new RidConsTransaction(params)
                if (params.tmp != null && RidConsTransactionTemplate.get(Long.valueOf(params.tmp))) {
                    ridTransactionInstance = RidConsTransactionTemplate.get(Long.valueOf(params.tmp))
                }
                [ridTransactionInstance: ridTransactionInstance]
            } catch (Exception e) {
                flash.alerts << e.message
                if (params.tmp.equals("templateList"))
                    redirect(action: "templateList")
                else
                    [ridTransactionInstance: new RidConsTransaction(params)]
            }
        } else {
            try {
                RidInsTransactionBase ridTransactionInstance = new RidInsTransaction(params)
                if (params.tmp != null && RidInsTransactionTemplate.get(Long.valueOf(params.tmp))) {
                    ridTransactionInstance = RidInsTransactionTemplate.get(Long.valueOf(params.tmp))
                }
                [ridTransactionInstance: ridTransactionInstance]
            } catch (Exception e) {
                flash.alerts << e.message
                if (params.tmp.equals("templateList"))
                    redirect(action: "templateList")
                else
                    [ridTransactionInstance: new RidInsTransaction(params)]
            }
        }
    }

    def save() {
        withForm {
            def ridTransactionInstance
            if (session.getAttribute("transType") == "consultation") {

                if (!params.dateOfConsultation.empty)
                    params.dateOfConsultation = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation);
                ridTransactionInstance = new RidConsTransaction(params)
                ridTransactionService.createNewConsInstanceMethod(params, ridTransactionInstance)
            } else {

                if (!params.dateOfInstruction.empty)
                    params.dateOfInstruction = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfInstruction);
                ridTransactionInstance = new RidInsTransaction(params)
                ridTransactionService.createNewInsInstanceMethod(params, ridTransactionInstance)
            }

            if (!ridTransactionInstance.save(flush: true)) {
                render(view: "create", model: [ridTransactionInstance: ridTransactionInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridTransaction.label', default: 'RidConsTransaction'), ridTransactionInstance.id])
            redirect(action: "show", id: ridTransactionInstance.id)
        }.invalidToken {
            flash.alerts << "Don't click the create button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }

    def remember() {
        withForm {
            def ridTransactionInstance
            if (session.getAttribute("transType") == "consultation") {

                if (!params.dateOfConsultation.empty)
                    params.dateOfConsultation = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation);
                ridTransactionInstance = new RidConsTransaction(params)
            } else {

                if (!params.dateOfInstruction.empty)
                    params.dateOfInstruction = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfInstruction);
                ridTransactionInstance = new RidInsTransaction(params)
            }
            ridTransactionInstance.templateOwner = SecurityUtils.getSubject().getPrincipal().toString()
            ridTransactionService.createNewInstanceMethod(params, ridTransactionInstance)
            if (!ridTransactionInstance.save(flush: true)) {
                render(view: "create", model: [ridTransactionInstance: ridTransactionInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'ridTransaction.label', default: 'RidConsTransaction Template'), ridTransactionInstance.id])
            redirect(action: "create")
        }.invalidToken {
            if (SecurityUtils.getSubject().getPrincipal())
                flash.alerts << "Don't click the remember button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }

    def update(Long id, Long version) {
        withForm {
            def ridTransactionInstance = RidConsTransaction.get(id)
            if (!ridTransactionInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidConsTransaction'), id])
                redirect(action: "list")
                return
            }

            if (version != null) {
                if (ridTransactionInstance.version > version) {
                    ridTransactionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                            [message(code: 'ridTransaction.label', default: 'RidConsTransaction')] as Object[],
                            "Another rank has updated this RidConsTransaction while you were editing")
                    render(view: "edit", model: [ridTransactionInstance: ridTransactionInstance])
                    return
                }
            }

            if (!params.dateOfConsultation.empty)
                params.dateOfConsultation = new SimpleDateFormat("MM/dd/yyyy").parse(params.dateOfConsultation);
            ridTransactionInstance.properties = params
            ridTransactionService.createNewInstanceMethod(params, ridTransactionInstance)
            if (!ridTransactionInstance.save(flush: true)) {
                render(view: "edit", model: [ridTransactionInstance: ridTransactionInstance])
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'ridTransaction.label', default: 'RidConsTransaction'), ridTransactionInstance.id])
            redirect(action: "show", id: ridTransactionInstance.id)
        }.invalidToken {
            flash.alerts << "Don't click the update button more than one time to make duplicated submission!"
            redirect(action: "list")
        }
    }

    def edit(Long id) {
        if (session.getAttribute("transType") == "consultation") {
            def ridTransactionInstance = RidConsTransaction.get(id)
            if (!ridTransactionInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidConsTransaction'), id])
                redirect(action: "list")
                return
            }

            [ridTransactionInstance: ridTransactionInstance]
        } else {
            def ridTransactionInstance = RidInsTransaction.get(id)
            if (!ridTransactionInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidInsTransaction'), id])
                redirect(action: "list")
                return
            }

            [ridTransactionInstance: ridTransactionInstance]
        }
    }

    def show(Long id) {
        if (session.getAttribute("transType") == "consultation") {
            def ridTransactionInstance = RidConsTransaction.get(id)
            if (!ridTransactionInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidConsTransaction'), id])
                redirect(action: "list")
                return
            }

            [ridTransactionInstance: ridTransactionInstance]
        } else {
            def ridTransactionInstance = RidInsTransaction.get(id)
            if (!ridTransactionInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidInsTransaction'), id])
                redirect(action: "list")
                return
            }

            [ridTransactionInstance: ridTransactionInstance]
        }
    }

    def delete(Long id) {
        if (session.getAttribute("transType") == "consultation") {
            RidConsTransactionBase ridTransactionInstance = RidConsTransaction.get(id)
            if (params.isTemplate == 'true')
                ridTransactionInstance = RidConsTransactionTemplate.get(id)
            if (!ridTransactionInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidConsTransaction'), id])
                redirect(action: "list")
                return
            }

            def msg = message(code: 'ridTransaction.label', default: 'RidConsTransaction')
            if (ridTransactionInstance.properties.containsKey('templateOwner'))
                msg = message(code: 'ridTransaction.label', default: 'RidConsTransaction Template')
        } else {
            RidInsTransactionBase ridTransactionInstance = RidInsTransaction.get(id)
            if (params.isTemplate == 'true')
                ridTransactionInstance = RidInsTransactionTemplate.get(id)
            if (!ridTransactionInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ridTransaction.label', default: 'RidInsTransaction'), id])
                redirect(action: "list")
                return
            }

            def msg = message(code: 'ridTransaction.label', default: 'RidInsTransaction')
            if (ridTransactionInstance.properties.containsKey('templateOwner'))
                msg = message(code: 'ridTransaction.label', default: 'RidInsTransaction Template')
        }
        try {
            ridTransactionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [msg, id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [msg, id])
            redirect(action: "show", id: id)
        }
    }

    def search() {
        session.setAttribute("prev", new String("search"))
    }

    def query(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def queryResult = ridTransactionService.queryMethod(params, session.getAttribute("transType"))

        render(view: "list",
                model: [ridTransactionInstanceList: queryResult.list(params),
                        ridTransactionInstanceTotal: queryResult.count(),
                        ridTransactionAllList: queryResult.list()])
        return
    }

    def spreadsheetUpload() {
        session.setAttribute("prev", new String("spreadsheetUpload"))
    }

    def upload() {
        withForm {
            MultipartFile uploadedFile = request.getFile("spreadsheetUpload")
            if (uploadedFile == null || uploadedFile.empty) {
                flash.alerts << "No file was provided"
                redirect(action: "spreadsheetUpload")
                return
            }

            if (!spreadsheetService.checkFileType(uploadedFile.getContentType())) {
                flash.alerts << "Invalid File Type. Only Excel Files are accepted!"
                redirect(action: "spreadsheetUpload")
                return
            }

            if (!spreadsheetService.checkSpreadsheetFormat(uploadedFile)) {
                flash.alerts << "Invalid Spreadsheet Format. Cannot Parse it."
                redirect(action: "spreadsheetUpload")
                return
            }
            if (session.getAttribute("transType") == "consultation") {
                if (RidConsTransaction.findBySpreadsheetName(uploadedFile.originalFilename)) {
                    flash.alerts << "This spreadsheet has been uploaded before. Change the file name, for example!"
                    redirect(action: "spreadsheetUpload")
                    return
                }
            } else {
                if (RidInsTransaction.findBySpreadsheetName(uploadedFile.originalFilename)) {
                    flash.alerts << "This spreadsheet has been uploaded before. Change the file name, for example!"
                    redirect(action: "spreadsheetUpload")
                    return
                }
            }

            List<List<String>> allInstances = spreadsheetService.getInstancesFromSpreadsheet(uploadedFile, flash)
            if (!allInstances.size()) {
                redirect(action: "spreadsheetUpload")
                return
            }

            if (spreadsheetService.saveToDatabase(allInstances, uploadedFile.originalFilename, flash)) {
                flash.infos << "Spreadsheet successfully uploaded. " +
                        String.valueOf(allInstances.size()) + " instances uploaded."
                redirect(action: "list")
            } else {
                redirect(action: "spreadsheetUpload")
                return
            }
        }.invalidToken {
            flash.alerts << "Don't click the uploading button more than one time to make duplicated submission!"
            redirect(action: "spreadsheetUpload")
        }
    }

    def export() {
        def queryResult = ridTransactionService.queryMethod(params)
        if (queryResult.count()) {
            Workbook wb = spreadsheetService.exportAsFile(queryResult.list())

            try {
                response.setContentType('application/vnd.openxmlformats-officedocument.spreadsheetml.sheet')
                response.setHeader("Content-disposition",
                        "filename=Transaction_List_" + new Date().format("MMddyyyy-HHmmss"))
                wb.write(response.outputStream) // Performing a binary stream copy
            } catch (Exception e) {
                flash.alerts << e.message
            }
        }

    }

    def download() {
        if (ridManageLibraryUnitSpreadsheetsService.download(response, flash, params) == false) {
            redirect(action: "index")
        }
    }

    def consultation() {

        session.setAttribute("transType", new String("consultation"))
        redirect(action: session.getAttribute("prev"))
    }

    def instructional() {

        session.setAttribute("transType", new String("instructional"))
        redirect(action: session.getAttribute("prev"))
    }
}