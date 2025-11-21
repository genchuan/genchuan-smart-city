package cn.iocoder.yudao.module.datacenter.service.inspection.inspectstdinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.inspectstdinfo.vo.InspectStdInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.inspectstdinfo.vo.InspectStdInfoSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.inspectstdinfo.InspectStdInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检标准信息 Service 接口
 *
 * @author zcq
 */
public interface InspectStdInfoService {

    /**
     * 创建巡查巡检标准信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectStdInfo(@Valid InspectStdInfoSaveReqVO createReqVO);

    /**
     * 更新巡查巡检标准信息
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectStdInfo(@Valid InspectStdInfoSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检标准信息
     *
     * @param id 编号
     */
    void deleteInspectStdInfo(Long id);

    /**
     * 获得巡查巡检标准信息
     *
     * @param id 编号
     * @return 巡查巡检标准信息
     */
    InspectStdInfoDO getInspectStdInfo(Long id);

    /**
     * 获得巡查巡检标准信息分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检标准信息分页
     */
    PageResult<InspectStdInfoDO> getInspectStdInfoPage(InspectStdInfoPageReqVO pageReqVO);

}