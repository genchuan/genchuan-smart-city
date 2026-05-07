package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo.CardConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CardConfigMapper extends BaseMapperX<CardConfigDO> {

    default PageResult<CardConfigDO> selectPage(CardConfigPageReqVO reqVO) {
        LambdaQueryWrapperX<CardConfigDO> wrapper = new LambdaQueryWrapperX<CardConfigDO>()
                .likeIfPresent(CardConfigDO::getName, reqVO.getName())
                .eqIfPresent(CardConfigDO::getType, reqVO.getType())
                .eqIfPresent(CardConfigDO::getScope, reqVO.getScope())
                .eqIfPresent(CardConfigDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CardConfigDO::getAuditorId, reqVO.getAuditorId())
                .likeIfPresent(CardConfigDO::getDescription, reqVO.getDescription())
                .geIfPresent(CardConfigDO::getPrice, reqVO.getMinPrice())
                .leIfPresent(CardConfigDO::getPrice, reqVO.getMaxPrice())
                .geIfPresent(CardConfigDO::getValidDays, reqVO.getMinValidDays())
                .leIfPresent(CardConfigDO::getValidDays, reqVO.getMaxValidDays())
                .orderByDesc(CardConfigDO::getId);
        if (reqVO.getAuditStartTime() != null && reqVO.getAuditEndTime() != null) {
            wrapper.between(CardConfigDO::getAuditTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getAuditStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getAuditEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getAuditStartTime() != null) {
            wrapper.ge(CardConfigDO::getAuditTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getAuditStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getAuditEndTime() != null) {
            wrapper.le(CardConfigDO::getAuditTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getAuditEndTime()), java.time.ZoneId.systemDefault()));
        }
        if (reqVO.getEffectStartTime() != null && reqVO.getEffectEndTime() != null) {
            wrapper.between(CardConfigDO::getEffectTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEffectStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEffectEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getEffectStartTime() != null) {
            wrapper.ge(CardConfigDO::getEffectTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEffectStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getEffectEndTime() != null) {
            wrapper.le(CardConfigDO::getEffectTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEffectEndTime()), java.time.ZoneId.systemDefault()));
        }
        return selectPage(reqVO, wrapper);
    }

    @Select("SELECT type, COUNT(*) AS count FROM card_config GROUP BY type")
    List<Map<String, Object>> selectTypeCountList();

    @Select("SELECT scope, COUNT(*) AS count FROM card_config GROUP BY scope")
    List<Map<String, Object>> selectScopeCountList();
}
