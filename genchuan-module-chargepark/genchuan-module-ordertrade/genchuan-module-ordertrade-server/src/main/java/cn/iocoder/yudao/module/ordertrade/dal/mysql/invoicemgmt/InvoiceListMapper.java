package cn.iocoder.yudao.module.ordertrade.dal.mysql.invoicemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.InvoiceListPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceListDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface InvoiceListMapper extends BaseMapperX<InvoiceListDO> {

    default PageResult<InvoiceListDO> selectPage(InvoiceListPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InvoiceListDO>()
                .likeIfPresent(InvoiceListDO::getInvoiceNo, reqVO.getInvoiceNo())
                .likeIfPresent(InvoiceListDO::getTitle, reqVO.getTitle())
                .eqIfPresent(InvoiceListDO::getStatus, reqVO.getStatus())
                .eqIfPresent(InvoiceListDO::getOrderId, reqVO.getOrderId())
                .likeIfPresent(InvoiceListDO::getCreator, reqVO.getCreator())
                .betweenIfPresent(InvoiceListDO::getCreateTime, reqVO.getCreateTimeStart(), reqVO.getCreateTimeEnd())
                .orderByDesc(InvoiceListDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM invoice_list WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM invoice_list WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM invoice_list WHERE deleted = 0 AND status = 'invoiced'")
    Long selectInvoicedCount();

    @Select("SELECT COUNT(*) FROM invoice_list WHERE deleted = 0")
    Long selectTotalCount();

    @Select("SELECT * FROM invoice_list WHERE deleted = 0 AND order_id = #{orderId} LIMIT 1")
    InvoiceListDO selectByOrderId(@Param("orderId") Long orderId);

    @Select("<script>" +
            "SELECT order_id, status FROM invoice_list WHERE deleted = 0 AND order_id IN " +
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>#{id}</foreach>" +
            "</script>")
    List<InvoiceListDO> selectByOrderIds(@Param("ids") List<Long> ids);

    default Map<Long, String> selectStatusMapByOrderIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptyMap();
        return selectByOrderIds(ids).stream()
                .collect(Collectors.toMap(InvoiceListDO::getOrderId, InvoiceListDO::getStatus, (existing, replacement) -> existing));
    }

    @Select("<script>" +
            "SELECT il.*, ao.order_no AS orderNo " +
            "FROM invoice_list il " +
            "LEFT JOIN all_order ao ON ao.id = il.order_id AND ao.deleted = 0 " +
            "WHERE il.deleted = 0 " +
            "<if test='req.invoiceNo != null and req.invoiceNo != \"\"'>AND il.invoice_no LIKE CONCAT('%', #{req.invoiceNo}, '%') </if>" +
            "<if test='req.title != null and req.title != \"\"'>AND il.title LIKE CONCAT('%', #{req.title}, '%') </if>" +
            "<if test='req.status != null and req.status != \"\"'>AND il.status = #{req.status} </if>" +
            "<if test='req.orderId != null'>AND il.order_id = #{req.orderId} </if>" +
            "<if test='req.creator != null and req.creator != \"\"'>AND il.creator LIKE CONCAT('%', #{req.creator}, '%') </if>" +
            "<if test='req.createTimeStart != null'>AND il.create_time &gt;= #{req.createTimeStart} </if>" +
            "<if test='req.createTimeEnd != null'>AND il.create_time &lt;= #{req.createTimeEnd} </if>" +
            "ORDER BY il.id DESC" +
            "</script>")
    IPage<InvoiceListDO> selectPageWithDetails(Page<InvoiceListDO> page, @Param("req") InvoiceListPageReqVO reqVO);

    @Select("SELECT il.*, ao.order_no AS orderNo " +
            "FROM invoice_list il " +
            "LEFT JOIN all_order ao ON ao.id = il.order_id AND ao.deleted = 0 " +
            "WHERE il.id = #{id} AND il.deleted = 0")
    InvoiceListDO selectByIdWithDetails(@Param("id") Long id);

    @Select("<script>" +
            "SELECT il.*, ao.order_no AS orderNo " +
            "FROM invoice_list il " +
            "LEFT JOIN all_order ao ON ao.id = il.order_id AND ao.deleted = 0 " +
            "WHERE il.deleted = 0 " +
            "<if test='req.invoiceNo != null and req.invoiceNo != \"\"'>AND il.invoice_no LIKE CONCAT('%', #{req.invoiceNo}, '%') </if>" +
            "<if test='req.title != null and req.title != \"\"'>AND il.title LIKE CONCAT('%', #{req.title}, '%') </if>" +
            "<if test='req.status != null and req.status != \"\"'>AND il.status = #{req.status} </if>" +
            "<if test='req.orderId != null'>AND il.order_id = #{req.orderId} </if>" +
            "<if test='req.creator != null and req.creator != \"\"'>AND il.creator LIKE CONCAT('%', #{req.creator}, '%') </if>" +
            "<if test='req.createTimeStart != null'>AND il.create_time &gt;= #{req.createTimeStart} </if>" +
            "<if test='req.createTimeEnd != null'>AND il.create_time &lt;= #{req.createTimeEnd} </if>" +
            "ORDER BY il.id DESC" +
            "</script>")
    List<InvoiceListDO> selectListWithDetails(@Param("req") InvoiceListPageReqVO reqVO);
}
