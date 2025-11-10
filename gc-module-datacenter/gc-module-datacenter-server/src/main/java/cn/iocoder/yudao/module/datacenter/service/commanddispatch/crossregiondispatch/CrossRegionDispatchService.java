package cn.iocoder.yudao.module.datacenter.service.commanddispatch.crossregiondispatch;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.crossregiondispatch.vo.CrossRegionDispatchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.crossregiondispatch.vo.CrossRegionDispatchSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.crossregiondispatch.CrossRegionDispatchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 跨域调度协调 Service 接口
 *
 * @author 亘川智城
 */
public interface CrossRegionDispatchService {

    /**
     * 创建跨域调度协调
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCrossRegionDispatch(@Valid CrossRegionDispatchSaveReqVO createReqVO);

    /**
     * 更新跨域调度协调
     *
     * @param updateReqVO 更新信息
     */
    void updateCrossRegionDispatch(@Valid CrossRegionDispatchSaveReqVO updateReqVO);

    /**
     * 删除跨域调度协调
     *
     * @param id 编号
     */
    void deleteCrossRegionDispatch(Long id);

    /**
     * 获得跨域调度协调
     *
     * @param id 编号
     * @return 跨域调度协调
     */
    CrossRegionDispatchDO getCrossRegionDispatch(Long id);

    /**
     * 获得跨域调度协调分页
     *
     * @param pageReqVO 分页查询
     * @return 跨域调度协调分页
     */
    PageResult<CrossRegionDispatchDO> getCrossRegionDispatchPage(CrossRegionDispatchPageReqVO pageReqVO);

}