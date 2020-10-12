package gradle_mybatis_spring_study.mapper;

import java.util.List;
import java.util.Map;

import gradle_mybatis_spring_study.dto.Student;

public interface StudentMapper {
    //TypeHandler 이용
    Student selectStudentByNo(Student student);
    //ResultMap 이용
    Student selectStudentByNoWithResultMap(Student student);

    //List반환
    List<Student> selectStudentByAll();

    //ResultMap
    List<Student> selectStudentByAllForResutlMap();
    
    //Result - HashMap
    List<Map<String, Object>> selectStudentByAllForHashMap();

    /* 내포된 결과매핑(ResultMap)을 사용한 일대일 매핑 */
    Student selectStudentByNoAssociation(Student student);

}









