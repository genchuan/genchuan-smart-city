package cn.iocoder.yudao.module.datacenter.service.inspection.count.inspecttyperpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspecttyperpt.vo.InspectTypeRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspecttyperpt.vo.InspectTypeRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspecttyperpt.InspectTypeRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检类型统计 Service 接口
 *
 * @author zcq
 */
public interface InspectTypeRptService {

    /**
     * 创建巡查巡检类型统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectTypeRpt(@Valid InspectTypeRptSaveReqVO createReqVO);

    /**
     * 更新巡查巡检类型统计
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectTypeRpt(@Valid InspectTypeRptSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检类型统计
     *
     * @param id 编号
     */
    void deleteInspectTypeRpt(Long id);

    /**
     * 获得巡查巡检类型统计
     *
     * @param id 编号
     * @return 巡查巡检类型统计
     */
    InspectTypeRptDO getInspectTypeRpt(Long id);

    /**
     * 获得巡查巡检类型统计分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检类型统计分页
     */
    PageResult<InspectTypeRptDO> getInspectTypeRptPage(InspectTypeRptPageReqVO pageReqVO);

}