package cn.iocoder.yudao.module.ordertrade.dal.mysql.splitsetttle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.SettleBillPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SettleBillDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface SettleBillMapper extends BaseMapperX<SettleBillDO> {

    default PageResult<SettleBillDO> selectPage(SettleBillPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SettleBillDO>()
                .likeIfPresent(SettleBillDO::getBillNo, reqVO.getBillNo())
                .eqIfPresent(SettleBillDO::getPartnerId, reqVO.getPartnerId())
                .eqIfPresent(SettleBillDO::getStatus, reqVO.getStatus())
                .orderByDesc(SettleBillDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM settle_bill WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT IFNULL(SUM(split_amount), 0) FROM settle_bill WHERE deleted = 0 AND status = 'settled'")
    BigDecimal selectTotalSettleAmount();

    @Select("SELECT COUNT(*) FROM settle_bill WHERE deleted = 0 AND status = 'settled'")
    Long selectSettledCount();

    @Select("SELECT COUNT(*) FROM settle_bill WHERE deleted = 0")
    Long selectTotalCount();
}
