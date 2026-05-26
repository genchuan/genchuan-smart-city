package cn.iocoder.yudao.module.ordertrade.dal.mysql.invoicemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.InvoiceAuditPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceAuditDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface InvoiceAuditMapper extends BaseMapperX<InvoiceAuditDO> {

    @Select("<script>" +
            "SELECT ia.* " +
            "FROM invoice_audit ia " +
            "WHERE ia.deleted = 0 " +
            "<if test='req.applicantId != null'>AND ia.applicant_id = #{req.applicantId} </if>" +
            "<if test='req.creator != null and req.creator != \"\"'>AND ia.creator LIKE CONCAT('%', #{req.creator}, '%') </if>" +
            "<if test='req.status != null and req.status != \"\"'>AND ia.status = #{req.status} </if>" +
            "<if test='req.applyTimeStart != null'>AND ia.apply_time &gt;= #{req.applyTimeStart} </if>" +
            "<if test='req.applyTimeEnd != null'>AND ia.apply_time &lt;= #{req.applyTimeEnd} </if>" +
            "ORDER BY ia.id DESC" +
            "</script>")
    IPage<InvoiceAuditDO> selectPageWithApplicant(Page<InvoiceAuditDO> page, @Param("req") InvoiceAuditPageReqVO reqVO);

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM invoice_audit WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM invoice_audit WHERE deleted = 0 AND status = 'pending'")
    Long selectPendingCount();

    @Select("SELECT COUNT(*) FROM invoice_audit WHERE deleted = 0 AND status = 'approved'")
    Long selectApprovedCount();

    @Select("SELECT COUNT(*) FROM invoice_audit WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM invoice_audit WHERE deleted = 0")
    Long selectTotalCount();
}
