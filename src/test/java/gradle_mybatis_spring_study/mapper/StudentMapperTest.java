package gradle_mybatis_spring_study.mapper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import gradle_mybatis_spring_study.dto.Gender;
import gradle_mybatis_spring_study.dto.PhoneNumber;
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

    @Test
    public void test11InsertEnumStudent() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");

        Calendar newDate = GregorianCalendar.getInstance();
        newDate.set(1990, 2, 28);
        Student student = new Student();
        student.setStudId(3);
        student.setName("test");
        student.setEmail("test@test.co.kr");
        student.setDob(newDate.getTime());
        student.setPhone(new PhoneNumber("010-1234-1234"));
        student.setGender(Gender.FEMALE);
        int res = mapper.insertEnumStudent(student);
        Assert.assertEquals(1, res);
            
        Student std3 = mapper.selectStudentByNoForEnum(student);
        log.debug("student 3 > " + std3.toString());
        
        
        student.setStudId(4);
        student.setName("test4");
        student.setEmail("test4@test.co.kr");
        student.setDob(newDate.getTime());
        student.setPhone(new PhoneNumber("010-1234-1234"));
        student.setGender(Gender.MALE);
        int res1 = mapper.insertEnumStudent(student);
        Assert.assertEquals(1, res1);
            
        Student std4 = mapper.selectStudentByNoForEnum(student);
        log.debug("student 4 > " + std4.toString());
        

    }
    
    @Test
    public void test12SelectStudentByMap() {
       log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
       Map<String, String> maps = new HashMap<>();
       maps.put("name", "Timothy");
       maps.put("email", "timothy@gmail.com");
       Student student = mapper.selectStudentByMap(maps);
       Assert.assertNotNull(student);
       log.debug(student.toString());

       maps.remove("email");
       student = mapper.selectStudentByMap(maps);
       log.debug(student.toString());
      
       maps.clear();
       maps.put("email", "timothy@gmail.com");
       student = mapper.selectStudentByMap(maps);
       log.debug(student.toString()); 
   }

    @Test
    public void test13SelectAllStudentByMap() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Map<String, String> maps = new HashMap<>();
        maps.put("name", "Timothy");
        maps.put("email", "timothy@gmail.com");
        List<Student> list = mapper.selectAllStudentByMap(maps);
        Assert.assertNotNull(list);
        list.stream().forEach(System.out::println);
        
        maps.remove("email");
        list = mapper.selectAllStudentByMap(maps);
        list.stream().forEach(System.out::println);
        
        maps.clear();
        maps.put("email", "timothy@gmail.com");
        list = mapper.selectAllStudentByMap(maps);
        list.stream().forEach(System.out::println);
        
        maps.clear();
        list = mapper.selectAllStudentByMap(maps);
        list.stream().forEach(System.out::println);
    }
    
    @Test
    public void testInsertStudent() {
        Calendar newDate = GregorianCalendar.getInstance();
        newDate.set(1990, 2, 28);

        Student student = new Student();
        student.setStudId(3);
        student.setName("홍길동3");
        student.setEmail("lee@test.co.kr");
        student.setPhone(new PhoneNumber("010-1234-1234"));
        student.setDob(newDate.getTime());
        int res = mapper.insertStudent(student);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test5DeleteStudent(){
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        int deleteStudent = mapper.deleteStudent(3);
        mapper.deleteStudent(4);
        Assert.assertSame(1, deleteStudent);
    }

    @Test
    public void test7UpdateStudent(){
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Student student = new Student();
        student.setStudId(1);
        student.setName("Timothy");
        student.setEmail("test@test.co.kr");
        student.setPhone(new PhoneNumber("987-654-3211"));
        student.setDob(new Date());
            
        int result = mapper.updateStudent(student);
        Assert.assertSame(1, result);
                    
        student.setEmail("timothy@gmail.com");
        student.setPhone(new PhoneNumber("123-123-1234"));
        student.setDob(new GregorianCalendar(1988, 04, 25).getTime());
        result = mapper.updateStudent(student);
        Assert.assertSame(1, result);
    }


}
