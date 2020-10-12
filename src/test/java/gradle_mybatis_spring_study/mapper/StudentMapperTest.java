package gradle_mybatis_spring_study.mapper;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import gradle_mybatis_spring_study.dto.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextRoot.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentMapperTest {
 protected static final Log log = LogFactory.getLog(StudentMapperTest.class);
    
    @After
    public void tearDown() throws Exception {
        System.out.println();
    }
    
    @Autowired
    private StudentMapper mapper;
    
    @Test
    public void testSelectStudentByNo() {
        System.out.println("testSelectStudentByNo()");
        Student student = new Student();
        student.setStudId(1);
        Student selectedStd = mapper.selectStudentByNo(student);
        Assert.assertNotNull(selectedStd);
        log.debug(selectedStd.toString());
    }

    @Test
    public void testSelectStudentByNoWithResultMap() {
        System.out.println("testSelectStudentByNoWithResultMap()");
        Student student = new Student();
        student.setStudId(1);
        Student selectedStd = mapper.selectStudentByNoWithResultMap(student);
        Assert.assertNotNull(selectedStd);
        log.debug(selectedStd.toString());
    }
    
    @Test
    public void testSelectStudentByAll() {
        System.out.println("testSelectStudentByAll()");
        List<Student> list = mapper.selectStudentByAll();
        Assert.assertNotNull(list);
        list.stream().forEach(System.out::println);
    }
    
    @Test
    public void test8SelectStudentByAllForResutlMap(){
       log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
       List<Student> list = mapper.selectStudentByAllForResutlMap();
       Assert.assertNotNull(list);
       list.stream().forEach(System.out::println);
    }
    
    @Test
    public void test9SelectStudentByAllForHashMap(){
       log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
       List<Map<String,Object>> list = mapper.selectStudentByAllForHashMap();
       Assert.assertNotNull(list);
       for(Map<String, Object> map : list) {
           for(Entry<String, Object> e : map.entrySet()) {
               log.debug(String.format("%s -> %s", e.getKey(), e.getValue()));
           }
       }
    }

    @Test
    public void test10SelectStudentByNoAssociation() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Student student = new Student();
        student.setStudId(1);
        Student seletedStd = mapper.selectStudentByNoAssociation(student);
        Assert.assertNotNull(seletedStd);
        log.debug(seletedStd.toString());
    }

}
