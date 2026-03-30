package cn.iocoder.yudao.module.facility.service.road.roadworkorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadworkorder.vo.RoadWorkOrderPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadworkorder.vo.RoadWorkOrderPageRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.workorder.vo.WorkOrderRespVO;
import cn.iocoder.yudao.module.facility.dal.mysql.road.roadworkorder.RoadWorkOrderMapper;
import cn.iocoder.yudao.module.facility.service.workorder.WorkOrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 工单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RoadWorkOrderServiceImpl implements RoadWorkOrderService {

    @Resource
    private RoadWorkOrderMapper roadWorkOrderMapper;

    @Resource
    private WorkOrderService workOrderService;

    @Override
    public PageResult<RoadWorkOrderPageRespVO> getWorkOrderPage(RoadWorkOrderPageReqVO pageReqVO) {
        PageResult<WorkOrderRespVO> orginPage = workOrderService.getCompleteWorkOrderPage(pageReqVO);
        PageResult<RoadWorkOrderPageRespVO> resultPage = BeanUtils.toBean(orginPage, RoadWorkOrderPageRespVO.class);
        return resultPage;
    }

//    @Override
//    public Long createWorkOrder(WorkOrderSaveReqVO createReqVO) {
//        // 插入
//        WorkOrderDO workOrder = BeanUtils.toBean(createReqVO, WorkOrderDO.class);
//        workOrderMapper.insert(workOrder);
//        // 返回
//        return workOrder.getId();
//    }
//
//    @Override
//    public void updateWorkOrder(WorkOrderSaveReqVO updateReqVO) {
//        // 校验存在
//        validateWorkOrderExists(updateReqVO.getId());
//        // 更新
//        WorkOrderDO updateObj = BeanUtils.toBean(updateReqVO, WorkOrderDO.class);
//        workOrderMapper.updateById(updateObj);
//    }
//
//    @Override
//    public void deleteWorkOrder(Long id) {
//        // 校验存在
//        validateWorkOrderExists(id);
//        // 删除
//        workOrderMapper.deleteById(id);
//    }
//
//    private void validateWorkOrderExists(Long id) {
//        if (workOrderMapper.selectById(id) == null) {
//            throw exception(WORK_ORDER_NOT_EXISTS);
//        }
//    }
//
//    @Override
//    public WorkOrderDO getWorkOrder(Long id) {
//        return workOrderMapper.selectById(id);
//    }
//
//    @Override
//    public PageResult<WorkOrderDO> getWorkOrderPage(WorkOrderPageReqVO pageReqVO) {
//        return workOrderMapper.selectPage(pageReqVO);
//    }

}
