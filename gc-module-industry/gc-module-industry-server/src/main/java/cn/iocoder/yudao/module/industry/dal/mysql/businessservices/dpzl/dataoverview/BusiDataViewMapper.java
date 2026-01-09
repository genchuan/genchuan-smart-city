// DataOverviewMapper.java
package cn.iocoder.yudao.module.industry.dal.mysql.businessservices.dpzl.dataoverview;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.dataview.vo.BusiDataViewRespVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BusiDataViewMapper {

    /**
     * 获取企业总数
     */
    Integer selectTotalEntCount();

    /**
     * 获取当日办件量
     */
    Integer selectDailyHandleCount(@Param("date") String date);

    /**
     * 获取政策兑现总额
     */
    java.math.BigDecimal selectTotalPolAmount();

    /**
     * 获取诉求响应率
     */
    java.math.BigDecimal selectAppealRespRate();

    /**
     * 获取区域企业分布
     */
    List<BusiDataViewRespVO.RegionEntDistribution> selectRegionEntDistribution();

    /**
     * 获取近7天办件趋势
     */
    List<BusiDataViewRespVO.DailyHandleTrend> selectDailyHandleTrend(@Param("beginDate") String beginDate,
                                                                             @Param("endDate") String endDate);

}