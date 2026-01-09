package cn.iocoder.yudao.module.smartcity.service.maintenanceplot;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplot.vo.MaintenancePlotPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplot.vo.MaintenancePlotSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenanceplot.MaintenancePlotDO;
import jakarta.validation.Valid;

/**
 * 养护地块 Service 接口
 *
 * @author zcq
 */
public interface MaintenancePlotService {

    /**
     * 创建养护地块
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMaintenancePlot(@Valid MaintenancePlotSaveReqVO createReqVO);

    /**
     * 更新养护地块
     *
     * @param updateReqVO 更新信息
     */
    void updateMaintenancePlot(@Valid MaintenancePlotSaveReqVO updateReqVO);

    /**
     * 删除养护地块
     *
     * @param id 编号
     */
    void deleteMaintenancePlot(Long id);

    /**
     * 获得养护地块
     *
     * @param id 编号
     * @return 养护地块
     */
    MaintenancePlotDO getMaintenancePlot(Long id);

    /**
     * 获得养护地块分页
     *
     * @param pageReqVO 分页查询
     * @return 养护地块分页
     */
    PageResult<MaintenancePlotDO> getMaintenancePlotPage(MaintenancePlotPageReqVO pageReqVO);

}