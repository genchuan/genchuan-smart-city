package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.passengerflow;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.PassengerFlowQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.PassengerFlowRespVO;

public interface PassengerFlowServer {

    /**
     * 获取文旅客流总览数据
     */
    PassengerFlowRespVO getPassengerFlowOverview(PassengerFlowQueryReqVO queryVO);

    /**
     * 获取区域筛选列表（用于下拉框）
     */
    Object getRegionOptions();
}