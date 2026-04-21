package cn.iocoder.yudao.module.ordertrade.dal.mysql.merchantreconcile;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo.ReconcileRecordPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile.ReconcileRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface ReconcileRecordMapper extends BaseMapperX<ReconcileRecordDO> {

    default PageResult<ReconcileRecordDO> selectPage(ReconcileRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ReconcileRecordDO>()
                .eqIfPresent(ReconcileRecordDO::getBillId, reqVO.getBillId())
                .likeIfPresent(ReconcileRecordDO::getBillNo, reqVO.getBillNo())
                .likeIfPresent(ReconcileRecordDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(ReconcileRecordDO::getMatchResult, reqVO.getMatchResult())
                .eqIfPresent(ReconcileRecordDO::getMerchantId, reqVO.getMerchantId())
                .orderByDesc(ReconcileRecordDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM reconcile_record WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM reconcile_record WHERE deleted = 0 AND match_result = 'unmatched'")
    Long selectUnmatchedCount();

    @Select("SELECT COUNT(*) FROM reconcile_record WHERE deleted = 0")
    Long selectTotalCount();
}
