package gradle_mybatis_spring_study.mapper;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import gradle_mybatis_spring_study.config.ContextRoot;
import gradle_mybatis_spring_study.dto.Course;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextRoot.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseMapperTest {
    protected static final Log log = LogFactory.getLog(CourseMapperTest.class);
    
    @After
    public void tearDown() throws Exception {
        System.out.println();
    }
    
    @Autowired
    private CourseMapper mapper;
    
    @Test
    public void test01SelectCoursesByCondition() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tutorId", 1);
           
        List<Course> list = mapper.selectCoursesByCondition(map);
        Assert.assertNotNull(list);
        list.stream().forEach(course -> log.debug(course.toString()));
    }

    @Test
    public void test02SelectCoursesByCondition() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("courseName", "%Java%");
        List<Course> list = mapper.selectCoursesByCondition(map);
        Assert.assertNotNull(list);
        list.stream().forEach(course -> log.debug(course.toString()));
    }

    @Test
    public void test03SelectCoursesByCondition() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

        GregorianCalendar cal = new GregorianCalendar(2013, 5, 1);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startDate", cal.getTime());
        List<Course> list = mapper.selectCoursesByCondition(map);
        Assert.assertNotNull(list);
        list.stream().forEach(course -> log.debug(course.toString()));
    }

    @Test
    public void test04SelectCaseCourses() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchBy", "Tutor");
        map.put("tutorId", 1);
        List<Course> list = mapper.selectCaseCourses(map);
        Assert.assertNotNull(list);
        list.stream().forEach(course -> log.debug(course.toString()));
            
        map.replace("searchBy", "CourseName");
        map.remove("tutorId");
        map.put("courseName", "%Java%");
        list = mapper.selectCaseCourses(map);
        Assert.assertNotNull(list);
        list.stream().forEach(course -> log.debug(course.toString()));
    }

    @Test
    public void test05SelectWhereCourses() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Course> list = mapper.selectWhereCourses(map);
        Assert.assertNotNull(list);
        list.stream().forEach(course -> log.debug(course.toString()));
            
        map.put("tutorId", 1);
        list = mapper.selectWhereCourses(map);
        list.stream().forEach(course -> log.debug(course.toString()));
            
        map.put("courseName", "%Java%");
        list = mapper.selectWhereCourses(map);
        list.stream().forEach(course -> log.debug(course.toString()));
            
        map.clear();
        map.put("endDate", new Date());
        list = mapper.selectWhereCourses(map);
        list.stream().forEach(course -> log.debug(course.toString()));  
    }

    @Test
    public void test06SelectTrimCourses() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

        Map<String, Object> map = new HashMap<String, Object>();
        List<Course> list = mapper.selectTrimCourses(map);
        Assert.assertNotNull(list);
        list.stream().forEach(course -> log.debug(course.toString()));  
            
        map.put("tutorId", 1); 
        list = mapper.selectTrimCourses(map);
        Assert.assertNotNull(list);
        list.stream().forEach(course -> log.debug(course.toString()));  
        
        map.clear();
        map.put("courseName", "%Java%");
        list = mapper.selectTrimCourses(map);
        Assert.assertNotNull(list);
        list.stream().forEach(course -> log.debug(course.toString()));  
        
        map.put("tutorId", 1);
        list = mapper.selectTrimCourses(map);
        Assert.assertNotNull(list);
        list.stream().forEach(course -> log.debug(course.toString()));  
    }

}
