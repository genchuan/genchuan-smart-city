package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.exchangemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface ExchangeOrderMapper extends BaseMapperX<ExchangeOrderDO> {

    default PageResult<ExchangeOrderDO> selectPage(ExchangeOrderPageReqVO reqVO) {
        LambdaQueryWrapperX<ExchangeOrderDO> wrapper = new LambdaQueryWrapperX<ExchangeOrderDO>()
                .likeIfPresent(ExchangeOrderDO::getNo, reqVO.getNo())
                .eqIfPresent(ExchangeOrderDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(ExchangeOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ExchangeOrderDO::getPayStatus, reqVO.getPayStatus())
                .orderByDesc(ExchangeOrderDO::getId);

        if (reqVO.getStartTime() != null && reqVO.getEndTime() != null) {
            wrapper.between(ExchangeOrderDO::getCreateTime, reqVO.getStartTime(), reqVO.getEndTime());
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

}
