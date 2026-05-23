package cn.iocoder.yudao.module.ordertrade.dal.mysql.splitsetttle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.SettleStatusPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SettleStatusDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SettleStatusMapper extends BaseMapperX<SettleStatusDO> {

    default PageResult<SettleStatusDO> selectPage(SettleStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SettleStatusDO>()
                .eqIfPresent(SettleStatusDO::getBillId, reqVO.getBillId())
                .eqIfPresent(SettleStatusDO::getStatus, reqVO.getStatus())
                .orderByDesc(SettleStatusDO::getId));
    }

    @Select("<script>" +
            "SELECT ss.*, sb.bill_no AS billNo FROM settle_status ss " +
            "LEFT JOIN settle_bill sb ON sb.id = ss.bill_id AND sb.deleted = 0 " +
            "WHERE ss.deleted = 0 " +
            "<if test='req.billId != null'>AND ss.bill_id = #{req.billId} </if>" +
            "<if test='req.billNo != null and req.billNo != \"\"'>AND sb.bill_no LIKE CONCAT('%', #{req.billNo}, '%') </if>" +
            "<if test='req.status != null and req.status != \"\"'>AND ss.status = #{req.status} </if>" +
            "<if test='req.createTimeStart != null'>AND ss.create_time &gt;= #{req.createTimeStart} </if>" +
            "<if test='req.createTimeEnd != null'>AND ss.create_time &lt;= #{req.createTimeEnd} </if>" +
            "ORDER BY ss.id DESC" +
            "</script>")
    PageResult<SettleStatusDO> selectPageWithBill(@Param("req") SettleStatusPageReqVO reqVO);

    @Select("SELECT ss.*, sb.bill_no AS billNo FROM settle_status ss " +
            "LEFT JOIN settle_bill sb ON sb.id = ss.bill_id AND sb.deleted = 0 " +
            "WHERE ss.id = #{id} AND ss.deleted = 0")
    SettleStatusDO selectByIdWithBill(@Param("id") Long id);

    @Select("SELECT status, COUNT(*) AS count FROM settle_status WHERE deleted = 0 GROUP BY status")
    List<Map<String, Object>> selectGroupByStatus();

    @Select("SELECT COUNT(*) FROM settle_status WHERE deleted = 0 AND status = 'normal'")
    Long selectNormalCount();

    @Select("SELECT COUNT(*) FROM settle_status WHERE deleted = 0")
    Long selectTotalCount();

    @Select("SELECT COUNT(*) FROM settle_status WHERE deleted = 0 AND status = 'abnormal'")
    Long selectAbnormalCount();
}
