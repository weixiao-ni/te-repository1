package com.example.hello_springboot.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.hello_springboot.entity.UserTblEntity;
@Repository("jpaUserTblRepository")
public interface UserTblRepository extends JpaRepository<UserTblEntity, Long>{
	// 必须接收 Pageable 参数，Spring Batch 分页读取需要它
	@Query(value = "SELECT USER_CD, USER_PWD, COMPANY_ID, AUTHORITY, " +
            "FIRST_NAME_KANA, FIRST_NAME, LAST_NAME_KANA, LAST_NAME, " +
            "SEX, COUNTRY_ZIP, USER_TEL, USER_MAIL, USER_PHOTO, " +
            "REGIST_DATE, REGIST_USER, UPDATE_DATE, USER_STATUS " +
            "FROM USER_TBL " +
            "WHERE REGIST_DATE >= ADD_MONTHS(TRUNC(CURRENT_TIMESTAMP, 'MM'), -1) " +
            "AND REGIST_DATE < TRUNC(CURRENT_TIMESTAMP, 'MM')", 
    nativeQuery = true)
    Page<UserTblEntity> findByIsAdminTrue(Pageable pageable);
}
