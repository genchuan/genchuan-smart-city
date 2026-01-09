package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.workorder;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder.vo.WorkOrderCreateReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder.vo.WorkOrderQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder.vo.WorkOrderRespVO;

/**
 * 市政设施专题-派发工单 Service 接口
 *
 */
public interface WorkOrderService {

    /**
     * 查询市政设施专题-派发工单
     *
     * @param workOrderQueryReqVO 查询条件
     * @return 查询结果
     */
    WorkOrderRespVO getWorkOrder(WorkOrderQueryReqVO workOrderQueryReqVO);

    Long createWorkOrder(WorkOrderCreateReqVO workOrderCreateReqVO);
}
