package cn.iocoder.yudao.module.facility.service.road.roadarchive;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadarchive.vo.RoadArchivePageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadarchive.vo.RoadArchivePageRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadworkorder.vo.RoadWorkOrderPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadworkorder.vo.RoadWorkOrderPageRespVO;


/**
 * 工单 Service 接口
 *
 * @author 亘川智城
 */
public interface RoadArchiveService {

    PageResult<RoadArchivePageRespVO> getArchivePage(RoadArchivePageReqVO pageReqVO);

//    /**
//     * 创建工单
//     *
//     * @param createReqVO 创建信息
//     * @return 编号
//     */
//    Long createWorkOrder(@Valid WorkOrderSaveReqVO createReqVO);
//
//    /**
//     * 更新工单
//     *
//     * @param updateReqVO 更新信息
//     */
//    void updateWorkOrder(@Valid WorkOrderSaveReqVO updateReqVO);
//
//    /**
//     * 删除工单
//     *
//     * @param id 编号
//     */
//    void deleteWorkOrder(Long id);
//
//    /**
//     * 获得工单
//     *
//     * @param id 编号
//     * @return 工单
//     */
//    WorkOrderDO getWorkOrder(Long id);
//
//    /**
//     * 获得工单分页
//     *
//     * @param pageReqVO 分页查询
//     * @return 工单分页
//     */
//    PageResult<WorkOrderDO> getWorkOrderPage(WorkOrderPageReqVO pageReqVO);

}
