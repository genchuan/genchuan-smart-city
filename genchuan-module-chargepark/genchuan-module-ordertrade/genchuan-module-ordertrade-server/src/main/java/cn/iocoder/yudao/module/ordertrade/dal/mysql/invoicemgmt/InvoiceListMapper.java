package cn.iocoder.yudao.module.ordertrade.dal.mysql.invoicemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.InvoiceListPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceListDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface InvoiceListMapper extends BaseMapperX<InvoiceListDO> {

    default PageResult<InvoiceListDO> selectPage(InvoiceListPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InvoiceListDO>()
                .likeIfPresent(InvoiceListDO::getInvoiceNo, reqVO.getInvoiceNo())
                .likeIfPresent(InvoiceListDO::getTitle, reqVO.getTitle())
                .eqIfPresent(InvoiceListDO::getStatus, reqVO.getStatus())
                .eqIfPresent(InvoiceListDO::getOrderId, reqVO.getOrderId())
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
}
