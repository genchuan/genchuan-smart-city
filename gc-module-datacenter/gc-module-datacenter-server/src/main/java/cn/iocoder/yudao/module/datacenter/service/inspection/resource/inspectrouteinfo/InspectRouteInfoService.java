package cn.iocoder.yudao.module.datacenter.service.inspection.resource.inspectrouteinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectrouteinfo.vo.InspectRouteInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectrouteinfo.vo.InspectRouteInfoSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectrouteinfo.InspectRouteInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检路线信息 Service 接口
 *
 * @author zcq
 */
public interface InspectRouteInfoService {

    /**
     * 创建巡查巡检路线信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectRouteInfo(@Valid InspectRouteInfoSaveReqVO createReqVO);

    /**
     * 更新巡查巡检路线信息
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectRouteInfo(@Valid InspectRouteInfoSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检路线信息
     *
     * @param id 编号
     */
    void deleteInspectRouteInfo(Long id);

    /**
     * 获得巡查巡检路线信息
     *
     * @param id 编号
     * @return 巡查巡检路线信息
     */
    InspectRouteInfoDO getInspectRouteInfo(Long id);

    /**
     * 获得巡查巡检路线信息分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检路线信息分页
     */
    PageResult<InspectRouteInfoDO> getInspectRouteInfoPage(InspectRouteInfoPageReqVO pageReqVO);

}