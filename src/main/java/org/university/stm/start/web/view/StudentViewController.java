package org.university.stm.start.web.view;

import com.google.common.collect.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.university.stm.start.bean.ClassOrganize;
import org.university.stm.start.bean.Student;
import org.university.stm.start.dao.STMDao;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 *     Web view controller
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-21
 */
@Controller
public class StudentViewController {
    @Autowired
    private STMDao stmDao;

    @GetMapping("/signin")
    ModelAndView signin(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("signin");
        return mv;
    }

    @GetMapping("/")
    ModelAndView container(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student/dashboard");
        return mv;
    }

    @GetMapping("/server/class-board")
    ModelAndView classBoard(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student/class-board");
        return mv;
    }

    @GetMapping("/server/class-manager")
    ModelAndView classManager(HttpServletRequest request) throws IOException {
        ModelAndView mv = new ModelAndView();

        List<ClassOrganize> classOrganizes = stmDao.getClassOrganizes();
        List<Student> students = stmDao.getStudents();

        List<ClassOrganize> sortClasses = new Ordering<ClassOrganize>() {
            @Override
            public int compare(ClassOrganize left, ClassOrganize right) {
                return (int) (left.getClsId() - right.getClsId());
            }
        }.immutableSortedCopy(classOrganizes);

        mv.addObject("stuSize", students.size());
        mv.addObject("classes", sortClasses);

        mv.setViewName("student/class-manager");
        return mv;
    }

    @GetMapping("/server/class-manager/{clsId}")
    ModelAndView classMessage(HttpServletRequest request, @PathVariable("clsId")long clsId) throws IOException {
        ModelAndView mv = new ModelAndView();
        ClassOrganize classOrganize = stmDao.getClassOrganize(clsId);
        if(classOrganize == null){
            throw new IOException("Invalid class id");
        }

        List<Student> students = stmDao.getStudents(clsId);

        List<Student> sortStudents = new Ordering<Student>(){
            @Override
            public int compare(Student st1, Student st2) {
                return (int) (st1.getStId() - st2.getStId());
            }
        }.immutableSortedCopy(students);

        mv.addObject("classOrganize", classOrganize);
        mv.addObject("students", sortStudents);

        mv.setViewName("student/student-manager");

        return mv;
    }
}
