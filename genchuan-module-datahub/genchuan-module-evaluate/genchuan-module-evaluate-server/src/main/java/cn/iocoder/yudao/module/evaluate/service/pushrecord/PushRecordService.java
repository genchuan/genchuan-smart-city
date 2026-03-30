package cn.iocoder.yudao.module.evaluate.service.pushrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.pushrecord.vo.PushRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.pushrecord.vo.PushRecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.pushrecord.PushRecordDO;
import jakarta.validation.Valid;

/**
 * 结果推送记录 Service 接口
 *
 * @author 亘川智城
 */
public interface PushRecordService {

    /**
     * 创建结果推送记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPushRecord(@Valid PushRecordSaveReqVO createReqVO);

    /**
     * 更新结果推送记录
     *
     * @param updateReqVO 更新信息
     */
    void updatePushRecord(@Valid PushRecordSaveReqVO updateReqVO);

    /**
     * 删除结果推送记录
     *
     * @param id 编号
     */
    void deletePushRecord(Long id);

    /**
     * 获得结果推送记录
     *
     * @param id 编号
     * @return 结果推送记录
     */
    PushRecordDO getPushRecord(Long id);

    /**
     * 获得结果推送记录分页
     *
     * @param pageReqVO 分页查询
     * @return 结果推送记录分页
     */
    PageResult<PushRecordDO> getPushRecordPage(PushRecordPageReqVO pageReqVO);

}