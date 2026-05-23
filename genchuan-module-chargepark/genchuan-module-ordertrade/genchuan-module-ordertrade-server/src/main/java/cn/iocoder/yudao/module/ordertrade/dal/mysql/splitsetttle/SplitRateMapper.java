package cn.iocoder.yudao.module.ordertrade.dal.mysql.splitsetttle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.SplitRatePageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SplitRateDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SplitRateMapper extends BaseMapperX<SplitRateDO> {

    default PageResult<SplitRateDO> selectPage(SplitRatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SplitRateDO>()
                .eqIfPresent(SplitRateDO::getPartnerId, reqVO.getPartnerId())
                .eqIfPresent(SplitRateDO::getSplitMode, reqVO.getSplitMode())
                .eqIfPresent(SplitRateDO::getStatus, reqVO.getStatus())
                .orderByDesc(SplitRateDO::getId));
    }

    @Select("<script>" +
            "SELECT sr.*, mi.name AS partnerName FROM split_rate sr " +
            "LEFT JOIN merchant_info mi ON mi.id = sr.partner_id AND mi.deleted = 0 " +
            "WHERE sr.deleted = 0 " +
            "<if test='req.partnerId != null'>AND sr.partner_id = #{req.partnerId} </if>" +
            "<if test='req.splitMode != null and req.splitMode != \"\"'>AND sr.split_mode = #{req.splitMode} </if>" +
            "<if test='req.status != null and req.status != \"\"'>AND sr.status = #{req.status} </if>" +
            "ORDER BY sr.id DESC" +
            "</script>")
    PageResult<SplitRateDO> selectPageWithPartner(@Param("req") SplitRatePageReqVO reqVO);

    @Select("SELECT sr.*, mi.name AS partnerName FROM split_rate sr " +
            "LEFT JOIN merchant_info mi ON mi.id = sr.partner_id AND mi.deleted = 0 " +
            "WHERE sr.id = #{id} AND sr.deleted = 0")
    SplitRateDO selectByIdWithPartner(@Param("id") Long id);

    @Select("SELECT split_mode, COUNT(*) AS count FROM split_rate WHERE deleted = 0 GROUP BY split_mode")
    List<Map<String, Object>> selectGroupBySplitMode();

    @Select("SELECT COUNT(*) FROM split_rate WHERE deleted = 0 AND status = 'enabled'")
    Long selectEnabledCount();

    @Select("SELECT COUNT(*) FROM split_rate WHERE deleted = 0 AND partner_id = #{partnerId}")
    Long selectCountByPartnerId(@Param("partnerId") Long partnerId);

    default boolean existsByPartnerId(Long partnerId) {
        return selectCountByPartnerId(partnerId) > 0;
    }
}
