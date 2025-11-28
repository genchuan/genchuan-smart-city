// ServiceItemsMapper.java
package cn.iocoder.yudao.module.industry.dal.mysql.businessservices.dpzl.serviceitems;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import java.math.BigDecimal;
import java.util.Map;

@Mapper
public interface BusiServiceItemsMapper {

    // 服务事项总数
    Integer selectTotalSvcCount();

    // 当日待办量
    Integer selectDailyPendCount();

    // 事项办结率
    BigDecimal selectSvcCompleteRate();

    // 超期事项数
    Integer selectOverdueSvcCount();

    // 事项类型分布
    @MapKey("typeName")
    Map<String, Map<String, Object>> selectTypeDistribution();

    // 近24小时事项办理趋势
    @MapKey("hour")
    Map<Integer, Map<String, Object>> selectHourlyTrend();

}