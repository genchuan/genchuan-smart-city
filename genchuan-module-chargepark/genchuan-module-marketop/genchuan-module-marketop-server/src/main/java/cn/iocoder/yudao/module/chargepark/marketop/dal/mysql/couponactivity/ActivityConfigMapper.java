package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ActivityConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ActivityConfigMapper extends BaseMapperX<ActivityConfigDO> {

    default PageResult<ActivityConfigDO> selectPage(ActivityConfigPageReqVO reqVO) {
        LambdaQueryWrapperX<ActivityConfigDO> wrapper = new LambdaQueryWrapperX<ActivityConfigDO>()
                .likeIfPresent(ActivityConfigDO::getName, reqVO.getName())
                .eqIfPresent(ActivityConfigDO::getType, reqVO.getType())
                .eqIfPresent(ActivityConfigDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ActivityConfigDO::getUserGroup, reqVO.getUserGroup())
                .likeIfPresent(ActivityConfigDO::getJoinCondition, reqVO.getJoinCondition())
                .likeIfPresent(ActivityConfigDO::getRuleContent, reqVO.getRuleContent())
                .likeIfPresent(ActivityConfigDO::getDescription, reqVO.getDescription())
                .eqIfPresent(ActivityConfigDO::getAuditorId, reqVO.getAuditorId())
                .orderByDesc(ActivityConfigDO::getId);
        if (reqVO.getAuditStartTime() != null && reqVO.getAuditEndTime() != null) {
            wrapper.between(ActivityConfigDO::getAuditTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getAuditStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getAuditEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getAuditStartTime() != null) {
            wrapper.ge(ActivityConfigDO::getAuditTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getAuditStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getAuditEndTime() != null) {
            wrapper.le(ActivityConfigDO::getAuditTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getAuditEndTime()), java.time.ZoneId.systemDefault()));
        }
        if (reqVO.getEffectStartTime() != null && reqVO.getEffectEndTime() != null) {
            wrapper.between(ActivityConfigDO::getEffectTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEffectStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEffectEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getEffectStartTime() != null) {
            wrapper.ge(ActivityConfigDO::getEffectTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEffectStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getEffectEndTime() != null) {
            wrapper.le(ActivityConfigDO::getEffectTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEffectEndTime()), java.time.ZoneId.systemDefault()));
        }
        return selectPage(reqVO, wrapper);
    }

    @Select("SELECT type, COUNT(*) AS count FROM activity_config GROUP BY type")
    List<Map<String, Object>> selectTypeCountList();

    @Select("SELECT user_group AS userGroup, COUNT(*) AS count FROM activity_config GROUP BY user_group")
    List<Map<String, Object>> selectUserGroupCountList();

}
