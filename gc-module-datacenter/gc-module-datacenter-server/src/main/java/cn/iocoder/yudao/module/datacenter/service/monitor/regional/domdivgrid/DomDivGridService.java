package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivgrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivgrid.vo.DomDivGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivgrid.vo.DomDivGridSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivgrid.DomDivGridDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 网格分域 Service 接口
 *
 * @author zcq
 */
public interface DomDivGridService {

    /**
     * 创建网格分域
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomDivGrid(@Valid DomDivGridSaveReqVO createReqVO);

    /**
     * 更新网格分域
     *
     * @param updateReqVO 更新信息
     */
    void updateDomDivGrid(@Valid DomDivGridSaveReqVO updateReqVO);

    /**
     * 删除网格分域
     *
     * @param id 编号
     */
    void deleteDomDivGrid(Long id);

    /**
     * 获得网格分域
     *
     * @param id 编号
     * @return 网格分域
     */
    DomDivGridDO getDomDivGrid(Long id);

    /**
     * 获得网格分域分页
     *
     * @param pageReqVO 分页查询
     * @return 网格分域分页
     */
    PageResult<DomDivGridDO> getDomDivGridPage(DomDivGridPageReqVO pageReqVO);

}