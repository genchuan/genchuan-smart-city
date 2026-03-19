package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletcomplaint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintBatchHandleReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintPendingRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletComplaintDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletComplaintDetailDO;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 公厕投诉 Service 接口
 *
 * @author 芋道源码
 */
public interface ToiletComplaintService {

    /**
     * 创建公厕投诉
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createToiletComplaint(@Valid ToiletComplaintSaveReqVO createReqVO);

    /**
     * 更新公厕投诉
     *
     * @param updateReqVO 更新信息
     */
    void updateToiletComplaint(@Valid ToiletComplaintSaveReqVO updateReqVO);

    /**
     * 删除公厕投诉
     *
     * @param id 编号
     */
    void deleteToiletComplaint(Long id);

    /**
     * 批量公厕投诉
     *
     * @param ids 编号列表
     */
    void deleteToiletComplaintBatch(List<Long> ids);

    /**
     * 获得公厕投诉
     *
     * @param id 编号
     * @return 公厕投诉
     */
    ToiletComplaintDO getToiletComplaint(Long id);

    /**
     * 获得公厕投诉分页
     *
     * @param pageReqVO 分页查询
     * @return 公厕投诉分页
     */
    PageResult<ToiletComplaintDO> getToiletComplaintPage(ToiletComplaintPageReqVO pageReqVO);

    /**
     * 获得公厕投诉详情分页
     *
     * @param pageReqVO 分页查询
     * @return 公厕投诉分页
     */
    PageResult<ToiletComplaintDetailDO> getToiletComplaintDetailPage(ToiletComplaintPageReqVO pageReqVO);

    /**
     * 上传多张图片
     *
     * @param id 投诉ID
     * @param files 图片文件列表
     * @return 图片访问URL列表
     */
    List<String> uploadPhotos(Long id, List<MultipartFile> files);

    /**
     * 获取图片列表
     *
     * @param id 投诉ID
     * @return 图片URL列表
     */
    List<String> getPhotos(Long id);

    /**
     * 删除图片
     *
     * @param id 投诉ID
     * @param photoUrl 图片URL
     */
    void deletePhoto(Long id, String photoUrl);

    /**
     * 批量更新处理状态
     */
    void batchUpdateDispatchStatus(@Valid ToiletComplaintBatchHandleReqVO reqVO);

    /**
     * 卡片/圆环图/柱状图统计(待处置)
     */
    ToiletComplaintPendingRespVO getPending();
}