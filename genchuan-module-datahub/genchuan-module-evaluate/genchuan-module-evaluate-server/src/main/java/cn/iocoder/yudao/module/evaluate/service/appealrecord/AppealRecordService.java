package cn.iocoder.yudao.module.evaluate.service.appealrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealrecord.vo.AppealRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealrecord.vo.AppealRecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.appealrecord.AppealRecordDO;
import jakarta.validation.Valid;

/**
 * 申诉复核 Service 接口
 *
 * @author 亘川智城
 */
public interface AppealRecordService {

    /**
     * 创建申诉复核
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAppealRecord(@Valid AppealRecordSaveReqVO createReqVO);

    /**
     * 更新申诉复核
     *
     * @param updateReqVO 更新信息
     */
    void updateAppealRecord(@Valid AppealRecordSaveReqVO updateReqVO);

    /**
     * 删除申诉复核
     *
     * @param id 编号
     */
    void deleteAppealRecord(Long id);

    /**
     * 获得申诉复核
     *
     * @param id 编号
     * @return 申诉复核
     */
    AppealRecordDO getAppealRecord(Long id);

    /**
     * 获得申诉复核分页
     *
     * @param pageReqVO 分页查询
     * @return 申诉复核分页
     */
    PageResult<AppealRecordDO> getAppealRecordPage(AppealRecordPageReqVO pageReqVO);

}