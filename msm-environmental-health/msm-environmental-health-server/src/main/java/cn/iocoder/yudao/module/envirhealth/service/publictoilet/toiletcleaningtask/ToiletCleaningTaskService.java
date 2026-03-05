package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletcleaningtask;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskWithJoinRespVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletCleaningTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

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
}