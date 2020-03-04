package org.university.stm.start.web.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.university.stm.start.bean.ClassOrganize;
import org.university.stm.start.bean.Student;
import org.university.stm.start.common.message.Message;
import org.university.stm.start.common.message.SuccessMessage;
import org.university.stm.start.dao.STMDao;

import java.io.IOException;
import java.util.List;

/**
 * <pre>
 *     server rest controller
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-21
 */
@RestController
@RequestMapping("/stm/api/v1")
public class StudentManagerRestController {

    @Autowired
    private STMDao stmDao;

    @GetMapping("/test")
    public Message test(){
        return new SuccessMessage("helllo world");
    }

    @GetMapping("/students")
    public Message getStudents() throws IOException {
        return new SuccessMessage(stmDao.getStudents());
    }

    @GetMapping("/students/{clsId}")
    public Message getStudent(@PathVariable("clsId") long clsId) throws IOException {
        return new SuccessMessage(stmDao.getStudents(clsId));
    }

    @DeleteMapping("/students/{stId}")
    public Message deleteStudent(@PathVariable("stId")long stId) throws IOException {
        stmDao.removeStudent(stId);
        return new SuccessMessage("success to delete student");
    }

    @PostMapping("/students/{clsId}")
    public Message createOrUpdateStudent(@PathVariable("clsId") long clsId, @RequestBody Student student) throws IOException {
        stmDao.addStudent(student);
        return new SuccessMessage("success with create ro update student");
    }

    @GetMapping("/class")
    public Message getClasses() throws IOException {
        return new SuccessMessage(stmDao.getClassOrganizes());
    }


    @DeleteMapping("/class/{clsId}")
    public Message deleteClass(@PathVariable("clsId") long clsId) throws IOException {
        stmDao.removeClsOrganize(clsId);
        return new SuccessMessage("success to delete class");
    }
    @GetMapping("/class/{clsId}")
    public Message getClass(@PathVariable("clsId") long clsId) throws IOException {
        return new SuccessMessage(stmDao.getClassOrganize(clsId));
    }

    @PostMapping("/class")
    public Message createOrUpdateClass(@RequestBody ClassOrganize classOrganize) throws IOException {
        stmDao.addClsOrganize(classOrganize);
        return new SuccessMessage("success with create ro update class");
    }

}
