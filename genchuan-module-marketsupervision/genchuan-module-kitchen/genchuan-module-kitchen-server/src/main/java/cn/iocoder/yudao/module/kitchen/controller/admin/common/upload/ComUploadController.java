package cn.iocoder.yudao.module.kitchen.controller.admin.common.upload;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.common.upload.vo.UploadFileReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.common.upload.vo.UploadFileRespVO;

import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.issue.IssueReqVO;
import cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.file.FileUploadService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 通用上传接口")
@RestController
@RequestMapping("/kitchen/common-upload")
@Validated
@Slf4j
public class ComUploadController {
    @Resource
    private FileUploadService fileUploadService;


    @PostMapping("/upload-file")
    @Operation(summary = "上传资料")
    @PreAuthorize("@ss.hasPermission('kitchen:common-upload:upload-file')")
    public CommonResult<UploadFileRespVO> uploadEvidenceFile(
            @RequestPart("file") MultipartFile file,
            @Valid @ModelAttribute UploadFileReqVO reqVO) {
        try {

            // 2. 上传文件到 MinIO
            String fileUrl = fileUploadService.uploadAvatar(file);

            // 3. 构建文件信息对象
            Map<String, String> fileInfo = new HashMap<>();
            fileInfo.put("url", fileUrl);
            fileInfo.put("name", file.getOriginalFilename());

            // 根据后缀决定 type
            String lowerName = file.getOriginalFilename().toLowerCase();
            if (lowerName.endsWith(".png") || lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") || lowerName.endsWith(".gif")) {
                fileInfo.put("type", "image");
            } else if (lowerName.endsWith(".xls") || lowerName.endsWith(".xlsx")) {
                fileInfo.put("type", "excel");
            } else if (lowerName.endsWith(".doc") || lowerName.endsWith(".docx")) {
                fileInfo.put("type", "word");
            } else {
                fileInfo.put("type", "file"); // 其他通用文件
            }

            // 8. 返回结果
            UploadFileRespVO respVO = new UploadFileRespVO();
//            respVO.setBizDataId(entRectifyRecordDO.getId());
            respVO.setFileUrl(fileUrl);
            respVO.setFileName(file.getOriginalFilename());

            return success(respVO);

        } catch (Exception e) {
            log.error("上传文件资料失败", e);

            if (e instanceof ServiceException) {
                throw (ServiceException) e;
            }

            throw new ServiceException(500, "上传文件失败");
        }

    }
}
