package cn.iocoder.yudao.module.envirhealth.service.vehicle.route;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.route.RoutePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.route.RouteSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.RouteDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 路线 Service 接口
 *
 * @author 芋道源码
 */
public interface RouteService {

    /**
     * 创建路线
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoute(@Valid RouteSaveReqVO createReqVO);

    /**
     * 更新路线
     *
     * @param updateReqVO 更新信息
     */
    void updateRoute(@Valid RouteSaveReqVO updateReqVO);

    /**
     * 删除路线
     *
     * @param id 编号
     */
    void deleteRoute(Long id);

    /**
     * 获得路线
     *
     * @param id 编号
     * @return 路线
     */
    RouteDO getRoute(Long id);

    /**
     * 获得路线分页
     *
     * @param pageReqVO 分页查询
     * @return 路线分页
     */
    PageResult<RouteDO> getRoutePage(RoutePageReqVO pageReqVO);

    /**
     * 获得路线下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getRouteOptions();
}