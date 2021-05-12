package kr.co.songjava.mvc.service;

import kr.co.songjava.mvc.domain.UploadFile;
import kr.co.songjava.mvc.parameter.UploadFileParameter;
import kr.co.songjava.mvc.repository.UploadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadFileService {

    @Autowired
    private UploadFileRepository repository;

    /**
     * 파일 등록 처리
     * 
     * @param parameter
     */
    public void save(UploadFileParameter parameter) {
        repository.save(parameter);
    }

    /**
     * 업로드된 파일 조회
     * 
     * @param uploadFileSeq
     * @return
     */
    public UploadFile get(int uploadFileSeq) {
        return repository.get(uploadFileSeq);
    }
}
