package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.RuleConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface RuleConfigMapper extends BaseMapperX<RuleConfigDO> {

    default PageResult<RuleConfigDO> selectPage(RuleConfigPageReqVO reqVO) {
        LambdaQueryWrapperX<RuleConfigDO> wrapper = new LambdaQueryWrapperX<RuleConfigDO>()
                .likeIfPresent(RuleConfigDO::getName, reqVO.getName())
                .eqIfPresent(RuleConfigDO::getType, reqVO.getType())
                .eqIfPresent(RuleConfigDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RuleConfigDO::getScene, reqVO.getScene())
                .eqIfPresent(RuleConfigDO::getAuditorId, reqVO.getAuditorId())
                .likeIfPresent(RuleConfigDO::getDescription, reqVO.getDescription())
                .orderByDesc(RuleConfigDO::getId);
        if (reqVO.getAuditStartTime() != null && reqVO.getAuditEndTime() != null) {
            wrapper.between(RuleConfigDO::getAuditTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getAuditStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getAuditEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getAuditStartTime() != null) {
            wrapper.ge(RuleConfigDO::getAuditTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getAuditStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getAuditEndTime() != null) {
            wrapper.le(RuleConfigDO::getAuditTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getAuditEndTime()), java.time.ZoneId.systemDefault()));
        }
        if (reqVO.getEffectStartTime() != null && reqVO.getEffectEndTime() != null) {
            wrapper.between(RuleConfigDO::getEffectTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEffectStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEffectEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getEffectStartTime() != null) {
            wrapper.ge(RuleConfigDO::getEffectTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEffectStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getEffectEndTime() != null) {
            wrapper.le(RuleConfigDO::getEffectTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEffectEndTime()), java.time.ZoneId.systemDefault()));
        }
        return selectPage(reqVO, wrapper);
    }

    default Long selectEnableCount() {
        return selectCount(new LambdaQueryWrapperX<RuleConfigDO>()
                .eq(RuleConfigDO::getStatus, "1"));
    }

    @Select("SELECT AVG(gift_ratio) FROM rule_config WHERE status = '1'")
    BigDecimal selectAvgGiftRatio();

    @Select("SELECT type, COUNT(*) as count FROM rule_config GROUP BY type")
    List<java.util.Map<String, Object>> selectTypeCountList();

    @Select("SELECT scene, COUNT(*) as count FROM rule_config GROUP BY scene")
    List<java.util.Map<String, Object>> selectSceneCountList();

}
