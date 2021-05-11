package kr.co.songjava.mvc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.songjava.configuration.GlobalConfig;
import kr.co.songjava.configuration.exception.BaseException;
import kr.co.songjava.configuration.http.BaseResponse;
import kr.co.songjava.configuration.http.BaseResponseCode;
import kr.co.songjava.mvc.parameter.UploadFileParameter;
import kr.co.songjava.mvc.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GlobalConfig config;

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 업로드 리턴
     *
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "업로드", notes = "")
    public BaseResponse<Boolean> save(@RequestParam("uploadFile") MultipartFile multipartFile) {

        logger.debug("multipartFile : {}", multipartFile);

        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL);
        }

        // 파일이 많으면 관리가 힘드므로 날짜 폴더 추가
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String uploadFilePath = config.getUploadFilePath() + currentDate + "/";

        logger.debug("uploadFilePath : {}", uploadFilePath);

        String prefix = multipartFile
                .getOriginalFilename()
                .substring(multipartFile
                        .getOriginalFilename()
                        .lastIndexOf(".") + 1, multipartFile.getOriginalFilename().length());
        String filename = UUID.randomUUID().toString() + "." + prefix;

        logger.debug("filename : {}", filename);

        File folder = new File(uploadFilePath);

        // 폴더가 없다면 생성
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }

        String pathname = uploadFilePath + filename;
        String resourcePathname = config.getUploadResourcePath() + currentDate + "/" + filename;

        File dest = new File(pathname);

        try {
            multipartFile.transferTo(dest);

            UploadFileParameter parameter = new UploadFileParameter();

            // 컨텐츠 종류
            parameter.setContentType(multipartFile.getContentType());

            // 원본 파일명
            parameter.setOriginalFilename(multipartFile.getOriginalFilename());

            // 저장 파일명
            parameter.setFilename(filename);

            // 실제 파일 저장 경로
            parameter.setPathname(pathname);

            // 파일 크기
            parameter.setSize((int) multipartFile.getSize());

            // static resource 접근 경로
            parameter.setResourcePathname(resourcePathname);

            // 파일 업로드 후 DB 에 저장
            uploadFileService.save(parameter);

        } catch (IllegalStateException | IOException e) {
            logger.error("e", e);
        }

        return new BaseResponse<Boolean>(true);
    }
}
