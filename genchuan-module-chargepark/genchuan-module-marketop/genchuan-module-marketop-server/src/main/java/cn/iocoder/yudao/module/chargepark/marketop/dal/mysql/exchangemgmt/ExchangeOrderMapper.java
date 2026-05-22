package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.exchangemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface ExchangeOrderMapper extends BaseMapperX<ExchangeOrderDO> {

    default PageResult<ExchangeOrderDO> selectPage(ExchangeOrderPageReqVO reqVO) {
        LambdaQueryWrapperX<ExchangeOrderDO> wrapper = new LambdaQueryWrapperX<ExchangeOrderDO>()
                .likeIfPresent(ExchangeOrderDO::getNo, reqVO.getNo())
                .eqIfPresent(ExchangeOrderDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(ExchangeOrderDO::getGoodsId, reqVO.getGoodsId())
                .eqIfPresent(ExchangeOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ExchangeOrderDO::getPayStatus, reqVO.getPayStatus())
                .betweenIfPresent(ExchangeOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExchangeOrderDO::getId);

        // date不为空时，转为当天起止时间查询create_time
        if (reqVO.getDate() != null && !reqVO.getDate().isEmpty()) {
            LocalDate localDate = LocalDate.parse(reqVO.getDate());
            LocalDateTime dayStart = localDate.atStartOfDay();
            LocalDateTime dayEnd = localDate.atTime(LocalTime.MAX);
            wrapper.between(ExchangeOrderDO::getCreateTime, dayStart, dayEnd);
        } else if (reqVO.getStartTime() != null && reqVO.getEndTime() != null) {
            // 兼容旧的Long类型时间戳
            LocalDateTime startDateTime = LocalDateTime.ofInstant(
                    java.time.Instant.ofEpochMilli(reqVO.getStartTime()), java.time.ZoneId.systemDefault());
            LocalDateTime endDateTime = LocalDateTime.ofInstant(
                    java.time.Instant.ofEpochMilli(reqVO.getEndTime()), java.time.ZoneId.systemDefault());
            wrapper.between(ExchangeOrderDO::getCreateTime, startDateTime, endDateTime);
        }
        return selectPage(reqVO, wrapper);
    }

    @Select("SELECT COUNT(*) FROM exchange_order WHERE deleted = 0 AND DATE(create_time) = CURDATE()")
    Long selectTodayCount();

    @Select("SELECT COUNT(*) FROM exchange_order WHERE deleted = 0 AND pay_status = '2' AND DATE(create_time) = CURDATE()")
    Long selectTodayExchangeCount();

    @Select("SELECT DATE(create_time) AS date, COUNT(*) AS count " +
            "FROM exchange_order " +
            "WHERE deleted = 0 AND create_time >= #{startTime} " +
            "GROUP BY DATE(create_time) " +
            "ORDER BY DATE(create_time) ASC")
    List<Map<String, Object>> selectCountByDay(@Param("startTime") LocalDateTime startTime);

    @Select("SELECT category_id, COUNT(*) AS count " +
            "FROM exchange_order " +
            "WHERE deleted = 0 " +
            "GROUP BY category_id")
    List<Map<String, Object>> selectCategoryCountList();

    IPage<ExchangeOrderRespVO> selectPageJoin(Page<?> page, @Param("reqVO") ExchangeOrderPageReqVO reqVO);

    ExchangeOrderRespVO selectByIdJoin(@Param("id") Long id);

    List<ExchangeOrderRespVO> selectListByIdsJoin(@Param("ids") List<Long> ids);

}
