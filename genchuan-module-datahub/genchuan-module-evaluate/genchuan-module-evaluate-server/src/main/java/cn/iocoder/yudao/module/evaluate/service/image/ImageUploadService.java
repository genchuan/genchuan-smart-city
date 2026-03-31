package cn.iocoder.yudao.module.evaluate.service.image;

import cn.iocoder.yudao.module.evaluate.controller.admin.imageUpdate.vo.ImageUploadRespVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 图片上传 Service 接口
 */
public interface ImageUploadService {

    /**
     * 批量上传图片
     *
     * @param files 上传的图片文件数组
     * @return 图片上传结果列表
     */
    List<ImageUploadRespVO> batchUploadImages(MultipartFile[] files);

    /**
     * 校验图片文件的合法性
     *
     * @param file 待校验的图片文件
     */
    void validateImageFile(MultipartFile file);
}
