package cn.iocoder.yudao.module.datacenter.service.inspection.record.inspectspecexecrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectspecexecrec.vo.InspectSpecExecRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectspecexecrec.vo.InspectSpecExecRecSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectspecexecrec.InspectSpecExecRecDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 专项巡查执行记录 Service 接口
 *
 * @author zcq
 */
public interface InspectSpecExecRecService {

    /**
     * 创建专项巡查执行记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectSpecExecRec(@Valid InspectSpecExecRecSaveReqVO createReqVO);

    /**
     * 更新专项巡查执行记录
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectSpecExecRec(@Valid InspectSpecExecRecSaveReqVO updateReqVO);

    /**
     * 删除专项巡查执行记录
     *
     * @param id 编号
     */
    void deleteInspectSpecExecRec(Long id);

    /**
     * 获得专项巡查执行记录
     *
     * @param id 编号
     * @return 专项巡查执行记录
     */
    InspectSpecExecRecDO getInspectSpecExecRec(Long id);

    /**
     * 获得专项巡查执行记录分页
     *
     * @param pageReqVO 分页查询
     * @return 专项巡查执行记录分页
     */
    PageResult<InspectSpecExecRecDO> getInspectSpecExecRecPage(InspectSpecExecRecPageReqVO pageReqVO);

}