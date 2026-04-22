package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigChartReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.RuleConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface RuleConfigMapper extends BaseMapperX<RuleConfigDO> {

    default PageResult<RuleConfigDO> selectPage(RuleConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RuleConfigDO>()
                .likeIfPresent(RuleConfigDO::getName, reqVO.getName())
                .eqIfPresent(RuleConfigDO::getType, reqVO.getType())
                .eqIfPresent(RuleConfigDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RuleConfigDO::getScene, reqVO.getScene())
                .orderByDesc(RuleConfigDO::getId));
    }

    default Long selectEnableCount(RuleConfigChartReqVO reqVO) {
        LambdaQueryWrapperX<RuleConfigDO> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(RuleConfigDO::getStatus, "1");
        if (reqVO.getStartTime() != null) {
            queryWrapperX.ge(RuleConfigDO::getEffectTime, reqVO.getStartTime());
        }
        if (reqVO.getEndTime() != null) {
            queryWrapperX.le(RuleConfigDO::getEffectTime, reqVO.getEndTime());
        }
        return selectCount(queryWrapperX);
    }

    @Select("<script>" +
            "SELECT AVG(gift_ratio) FROM rule_config " +
            "<where>" +
            "status = '1'" +
            "<if test='startTime != null'> AND effect_time &gt;= #{startTime}</if>" +
            "<if test='endTime != null'> AND effect_time &lt;= #{endTime}</if>" +
            "</where>" +
            "</script>")
    BigDecimal selectAvgGiftRatio(RuleConfigChartReqVO reqVO);

    @Select("<script>" +
            "SELECT type, COUNT(*) as count FROM rule_config " +
            "<where>" +
            "<if test='startTime != null'> AND effect_time &gt;= #{startTime}</if>" +
            "<if test='endTime != null'> AND effect_time &lt;= #{endTime}</if>" +
            "</where>" +
            "GROUP BY type" +
            "</script>")
    List<java.util.Map<String, Object>> selectTypeCountList(RuleConfigChartReqVO reqVO);

}
