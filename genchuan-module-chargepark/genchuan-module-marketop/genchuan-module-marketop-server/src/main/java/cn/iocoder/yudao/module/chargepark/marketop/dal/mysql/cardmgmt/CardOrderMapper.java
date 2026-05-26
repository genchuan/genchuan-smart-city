package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.CardOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.CardOrderRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardOrderDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        LambdaQueryWrapperX<CardOrderDO> wrapper = new LambdaQueryWrapperX<CardOrderDO>()
                .eqIfPresent(CardOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CardOrderDO::getCardId, reqVO.getCardId())
                .likeIfPresent(CardOrderDO::getNo, reqVO.getNo())
                .eqIfPresent(CardOrderDO::getPayStatus, reqVO.getPayStatus())
                .eqIfPresent(CardOrderDO::getInvoiceStatus, reqVO.getInvoiceStatus())
                .betweenIfPresent(CardOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CardOrderDO::getId);
        // createTime 范围
        if (reqVO.getStartTime() != null && reqVO.getEndTime() != null) {
            wrapper.between(CardOrderDO::getCreateTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getStartTime() != null) {
            wrapper.ge(CardOrderDO::getCreateTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getEndTime() != null) {
            wrapper.le(CardOrderDO::getCreateTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEndTime()), java.time.ZoneId.systemDefault()));
        }
        // payTime 范围
        if (reqVO.getPayStartTime() != null && reqVO.getPayEndTime() != null) {
            wrapper.between(CardOrderDO::getPayTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getPayStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getPayEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getPayStartTime() != null) {
            wrapper.ge(CardOrderDO::getPayTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getPayStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getPayEndTime() != null) {
            wrapper.le(CardOrderDO::getPayTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getPayEndTime()), java.time.ZoneId.systemDefault()));
        }
        // activeTime 范围
        if (reqVO.getActiveStartTime() != null && reqVO.getActiveEndTime() != null) {
            wrapper.between(CardOrderDO::getActiveTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getActiveStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getActiveEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getActiveStartTime() != null) {
            wrapper.ge(CardOrderDO::getActiveTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getActiveStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getActiveEndTime() != null) {
            wrapper.le(CardOrderDO::getActiveTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getActiveEndTime()), java.time.ZoneId.systemDefault()));
        }
        // archiveTime 范围
        if (reqVO.getArchiveStartTime() != null && reqVO.getArchiveEndTime() != null) {
            wrapper.between(CardOrderDO::getArchiveTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getArchiveStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getArchiveEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getArchiveStartTime() != null) {
            wrapper.ge(CardOrderDO::getArchiveTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getArchiveStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getArchiveEndTime() != null) {
            wrapper.le(CardOrderDO::getArchiveTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getArchiveEndTime()), java.time.ZoneId.systemDefault()));
        }
        return selectPage(reqVO, wrapper);
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

    @Select("SELECT pay_status, COUNT(id) AS count " +
            "FROM card_order  " +
            "WHERE deleted = 0 " +
            "GROUP BY pay_status")
    List<Map<String, Object>> selectPayStatusCountList();

    IPage<CardOrderRespVO> selectPageJoin(Page<?> page, @Param("reqVO") CardOrderPageReqVO reqVO);

    CardOrderRespVO selectByIdJoin(@Param("id") Long id);

    List<CardOrderRespVO> selectListByIdsJoin(@Param("ids") List<Long> ids);

}
