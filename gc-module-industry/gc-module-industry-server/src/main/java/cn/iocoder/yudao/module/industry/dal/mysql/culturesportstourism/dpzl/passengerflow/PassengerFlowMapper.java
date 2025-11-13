package cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.passengerflow;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.PassengerFlowQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.PassengerFlowRespVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PassengerFlowMapper extends BaseMapper<PassengerFlowRespVO> {

    /**
     * 获取实时客流总量
     */
    Long selectTotalRptCount(@Param("query") PassengerFlowQueryReqVO query);

    /**
     * 获取客流峰值时段
     */
    Integer selectMaxHour(@Param("query") PassengerFlowQueryReqVO query);

    /**
     * 获取分时客流趋势
     */
    List<PassengerFlowRespVO.HourlyTrend> selectHourlyTrends(@Param("query") PassengerFlowQueryReqVO query);

    /**
     * 获取区域客流分布
     */
    List<PassengerFlowRespVO.RegionDistribution> selectRegionDistributions(@Param("query") PassengerFlowQueryReqVO query);
}