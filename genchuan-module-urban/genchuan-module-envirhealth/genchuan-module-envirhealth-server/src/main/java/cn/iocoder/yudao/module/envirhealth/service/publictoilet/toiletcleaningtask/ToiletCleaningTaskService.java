package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletcleaningtask;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletCleaningTaskDO;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 公厕保洁任务 Service 接口
 *
 * @author 亘川智城
 */
public interface ToiletCleaningTaskService {

    /**
     * 创建公厕保洁任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createToiletCleaningTask(@Valid ToiletCleaningTaskSaveReqVO createReqVO);

    /**
     * 更新公厕保洁任务
     *
     * @param updateReqVO 更新信息
     */
    void updateToiletCleaningTask(@Valid ToiletCleaningTaskSaveReqVO updateReqVO);

    /**
     * 删除公厕保洁任务
     *
     * @param id 编号
     */
    void deleteToiletCleaningTask(Long id);

    /**
     * 批量公厕保洁任务
     *
     * @param ids 编号列表
     */
    void deleteToiletCleaningTaskBatch(List<Long> ids);

    /**
     * 获得公厕保洁任务
     *
     * @param id 编号
     * @return 公厕保洁任务
     */
    ToiletCleaningTaskDO getToiletCleaningTask(Long id);

    /**
     * 获得公厕保洁任务分页
     *
     * @param pageReqVO 分页查询
     * @return 公厕保洁任务分页
     */
    PageResult<ToiletCleaningTaskDO> getToiletCleaningTaskPage(ToiletCleaningTaskPageReqVO pageReqVO);

    /**
     * 获得公厕保洁任务分页（带关联信息）
     */
    PageResult<ToiletCleaningTaskWithJoinRespVO> getToiletCleaningTaskJoinPage(ToiletCleaningTaskPageReqVO pageReqVO);

    /**
     * 上传多张图片
     *
     * @param id 任务ID
     * @param files 图片文件列表
     * @return 图片访问URL列表
     */
    List<String> uploadPhotos(Long id, List<MultipartFile> files);

    /**
     * 获取图片列表
     *
     * @param id 任务ID
     * @return 图片URL列表
     */
    List<String> getPhotos(Long id);

    /**
     * 删除图片
     *
     * @param id 任务ID
     * @param photoUrl 图片URL
     */
    void deletePhoto(Long id, String photoUrl);

    /**
     * 批量调整公厕保洁任务
     *
     * @param reqVO 批量调整请求
     */
    void batchAdjustToiletCleaningTask(ToiletCleaningTaskBatchAdjustReqVO reqVO);

    /**
     * 卡片/圆环图/柱状图统计(待执行)
     */
    ToiletCleaningTaskPendingRespVO getPending();

    /**
     * 卡片/圆环图/柱状图统计(已完成)
     */
    ToiletCleaningTaskSummaryRespVO getSummary();
}