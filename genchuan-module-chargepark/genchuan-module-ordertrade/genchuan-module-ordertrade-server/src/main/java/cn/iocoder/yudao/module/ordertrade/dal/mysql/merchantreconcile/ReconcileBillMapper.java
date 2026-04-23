package cn.iocoder.yudao.module.ordertrade.dal.mysql.merchantreconcile;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo.ReconcileBillPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile.ReconcileBillDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface ReconcileBillMapper extends BaseMapperX<ReconcileBillDO> {

    default PageResult<ReconcileBillDO> selectPage(ReconcileBillPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ReconcileBillDO>()
                .likeIfPresent(ReconcileBillDO::getBillNo, reqVO.getBillNo())
                .eqIfPresent(ReconcileBillDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(ReconcileBillDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ReconcileBillDO::getBillDate, reqVO.getBillDate())
                .orderByDesc(ReconcileBillDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM reconcile_bill WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM reconcile_bill WHERE deleted = 0 AND status = 'pending'")
    Long selectPendingCount();

    @Select("SELECT COUNT(*) FROM reconcile_bill WHERE deleted = 0 AND status = 'disputed'")
    Long selectDisputedCount();

    @Select("SELECT COUNT(*) FROM reconcile_bill WHERE deleted = 0 AND status = 'confirmed'")
    Long selectConfirmedCount();

    @Select("SELECT COUNT(*) FROM reconcile_bill WHERE deleted = 0")
    Long selectTotalCount();
}
