package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.CardOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface CardOrderMapper extends BaseMapperX<CardOrderDO> {

    default PageResult<CardOrderDO> selectPage(CardOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CardOrderDO>()
                .eqIfPresent(CardOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CardOrderDO::getCardId, reqVO.getCardId())
                .eqIfPresent(CardOrderDO::getPayStatus, reqVO.getPayStatus())
                .eqIfPresent(CardOrderDO::getInvoiceStatus, reqVO.getInvoiceStatus())
                .betweenIfPresent(CardOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CardOrderDO::getId));
    }

    @Select("SELECT COUNT(*) FROM card_order WHERE deleted = 0 AND DATE(create_time) = CURDATE()")
    Long selectTodayCount();

    @Select("SELECT IFNULL(SUM(amount), 0) FROM card_order WHERE deleted = 0 AND DATE(create_time) = CURDATE()")
    BigDecimal selectTodayRevenue();

    @Select("SELECT DATE(create_time) AS date, COUNT(*) AS count, IFNULL(SUM(amount), 0) AS amount " +
            "FROM card_order " +
            "WHERE deleted = 0 AND create_time >= #{startTime} " +
            "GROUP BY DATE(create_time) " +
            "ORDER BY DATE(create_time) ASC")
    List<Map<String, Object>> selectCountByDay(@Param("startTime") LocalDateTime startTime);

    @Select("SELECT cc.type, COUNT(co.id) AS count " +
            "FROM card_order co " +
            "LEFT JOIN card_config cc ON co.card_id = cc.id " +
            "WHERE co.deleted = 0 " +
            "GROUP BY cc.type")
    List<Map<String, Object>> selectTypeCountList();

}
