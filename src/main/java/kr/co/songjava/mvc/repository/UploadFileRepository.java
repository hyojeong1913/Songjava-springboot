package kr.co.songjava.mvc.repository;

import kr.co.songjava.mvc.parameter.UploadFileParameter;
import org.springframework.stereotype.Repository;

/**
 * 파일 업로드 repository
 */
@Repository
public interface UploadFileRepository {

    void save(UploadFileParameter parameter);
}
