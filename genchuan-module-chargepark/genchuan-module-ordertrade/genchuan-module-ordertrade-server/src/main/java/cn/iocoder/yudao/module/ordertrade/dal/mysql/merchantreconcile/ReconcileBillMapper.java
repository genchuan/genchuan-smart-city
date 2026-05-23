package cn.iocoder.yudao.module.ordertrade.dal.mysql.merchantreconcile;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo.ReconcileBillPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile.ReconcileBillDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
                .eqIfPresent(ReconcileBillDO::getCycle, reqVO.getCycle())
                .orderByDesc(ReconcileBillDO::getId));
    }

    @Select("<script>" +
            "SELECT rb.*, mi.name AS merchant_name " +
            "FROM reconcile_bill rb " +
            "LEFT JOIN merchant_info mi ON mi.id = rb.merchant_id AND mi.deleted = 0 " +
            "WHERE rb.deleted = 0 " +
            "<if test='req.billNo != null and req.billNo != \"\"'>AND rb.bill_no LIKE CONCAT('%', #{req.billNo}, '%') </if>" +
            "<if test='req.merchantId != null'>AND rb.merchant_id = #{req.merchantId} </if>" +
            "<if test='req.status != null and req.status != \"\"'>AND rb.status = #{req.status} </if>" +
            "<if test='req.cycle != null and req.cycle != \"\"'>AND rb.cycle = #{req.cycle} </if>" +
            "ORDER BY rb.id DESC" +
            "</script>")
    IPage<ReconcileBillDO> selectPageWithMerchant(Page<ReconcileBillDO> page, @Param("req") ReconcileBillPageReqVO reqVO);

    @Select("SELECT rb.*, mi.name AS merchant_name " +
            "FROM reconcile_bill rb " +
            "LEFT JOIN merchant_info mi ON mi.id = rb.merchant_id AND mi.deleted = 0 " +
            "WHERE rb.id = #{id} AND rb.deleted = 0")
    ReconcileBillDO selectByIdWithMerchant(@Param("id") Long id);

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

    @Select("SELECT COUNT(*) FROM reconcile_bill WHERE deleted = 0 AND status = 'abnormal'")
    Long selectDisputedCount();

    @Select("SELECT COUNT(*) FROM reconcile_bill WHERE deleted = 0 AND status = 'reconciled'")
    Long selectConfirmedCount();

    @Select("SELECT COUNT(*) FROM reconcile_bill WHERE deleted = 0")
    Long selectTotalCount();
}
