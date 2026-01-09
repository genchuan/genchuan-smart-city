package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.passengerflow;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.CulturePasFlowQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.CulturePasFlowRespVO;

public interface CulturePasFlowService {

    /**
     * 获取文旅客流总览数据
     */
    CulturePasFlowRespVO getPassengerFlowOverview(CulturePasFlowQueryReqVO queryVO);

    /**
     * 获取区域筛选列表（用于下拉框）
     */
    Object getRegionOptions();
}