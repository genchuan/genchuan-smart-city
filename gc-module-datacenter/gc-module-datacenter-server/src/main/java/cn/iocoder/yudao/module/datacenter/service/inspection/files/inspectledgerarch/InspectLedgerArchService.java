package cn.iocoder.yudao.module.datacenter.service.inspection.files.inspectledgerarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectledgerarch.vo.InspectLedgerArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectledgerarch.vo.InspectLedgerArchSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectledgerarch.InspectLedgerArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检台账档案 Service 接口
 *
 * @author zcq
 */
public interface InspectLedgerArchService {

    /**
     * 创建巡查巡检台账档案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectLedgerArch(@Valid InspectLedgerArchSaveReqVO createReqVO);

    /**
     * 更新巡查巡检台账档案
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectLedgerArch(@Valid InspectLedgerArchSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检台账档案
     *
     * @param id 编号
     */
    void deleteInspectLedgerArch(Long id);

    /**
     * 获得巡查巡检台账档案
     *
     * @param id 编号
     * @return 巡查巡检台账档案
     */
    InspectLedgerArchDO getInspectLedgerArch(Long id);

    /**
     * 获得巡查巡检台账档案分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检台账档案分页
     */
    PageResult<InspectLedgerArchDO> getInspectLedgerArchPage(InspectLedgerArchPageReqVO pageReqVO);

}