package cn.iocoder.yudao.module.datacenter.service.inspection.record.inspectdailyexecrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectdailyexecrec.vo.InspectDailyExecRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectdailyexecrec.vo.InspectDailyExecRecSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectdailyexecrec.InspectDailyExecRecDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 日常巡查执行记录 Service 接口
 *
 * @author zcq
 */
public interface InspectDailyExecRecService {

    /**
     * 创建日常巡查执行记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectDailyExecRec(@Valid InspectDailyExecRecSaveReqVO createReqVO);

    /**
     * 更新日常巡查执行记录
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectDailyExecRec(@Valid InspectDailyExecRecSaveReqVO updateReqVO);

    /**
     * 删除日常巡查执行记录
     *
     * @param id 编号
     */
    void deleteInspectDailyExecRec(Long id);

    /**
     * 获得日常巡查执行记录
     *
     * @param id 编号
     * @return 日常巡查执行记录
     */
    InspectDailyExecRecDO getInspectDailyExecRec(Long id);

    /**
     * 获得日常巡查执行记录分页
     *
     * @param pageReqVO 分页查询
     * @return 日常巡查执行记录分页
     */
    PageResult<InspectDailyExecRecDO> getInspectDailyExecRecPage(InspectDailyExecRecPageReqVO pageReqVO);

}