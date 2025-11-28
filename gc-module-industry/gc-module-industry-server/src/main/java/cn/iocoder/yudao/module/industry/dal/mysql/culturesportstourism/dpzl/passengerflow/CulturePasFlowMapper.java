package cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.passengerflow;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.CulturePasFlowQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.CulturePasFlowRespVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CulturePasFlowMapper extends BaseMapper<CulturePasFlowRespVO> {

    /**
     * 获取实时客流总量
     */
    Long selectTotalRptCount(@Param("query") CulturePasFlowQueryReqVO query);

    /**
     * 获取客流峰值时段
     */
    Integer selectMaxHour(@Param("query") CulturePasFlowQueryReqVO query);

    /**
     * 获取分时客流趋势
     */
    List<CulturePasFlowRespVO.HourlyTrend> selectHourlyTrends(@Param("query") CulturePasFlowQueryReqVO query);

    /**
     * 获取区域客流分布
     */
    List<CulturePasFlowRespVO.RegionDistribution> selectRegionDistributions(@Param("query") CulturePasFlowQueryReqVO query);
}