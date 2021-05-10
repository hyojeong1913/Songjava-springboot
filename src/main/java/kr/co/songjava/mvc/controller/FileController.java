package kr.co.songjava.mvc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.songjava.configuration.GlobalConfig;
import kr.co.songjava.configuration.exception.BaseException;
import kr.co.songjava.configuration.http.BaseResponse;
import kr.co.songjava.configuration.http.BaseResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;

@RestController
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GlobalConfig config;

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

        String uploadFilePath = config.getUploadFilePath();
        logger.debug("uploadFilePath : {}", uploadFilePath);

        if (config.isProd()) {
            logger.debug("isProd calendar : {}", Calendar.getInstance());
        }

        return new BaseResponse<Boolean>(true);
    }
}
