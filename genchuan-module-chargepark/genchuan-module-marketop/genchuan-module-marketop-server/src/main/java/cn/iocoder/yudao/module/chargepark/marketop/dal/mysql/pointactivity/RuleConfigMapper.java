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
        return selectPage(reqVO, new LambdaQueryWrapperX<RuleConfigDO>()
                .likeIfPresent(RuleConfigDO::getName, reqVO.getName())
                .eqIfPresent(RuleConfigDO::getType, reqVO.getType())
                .eqIfPresent(RuleConfigDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RuleConfigDO::getScene, reqVO.getScene())
                .orderByDesc(RuleConfigDO::getId));
    }

    default Long selectEnableCount() {
        return selectCount(new LambdaQueryWrapperX<RuleConfigDO>()
                .eq(RuleConfigDO::getStatus, "1"));
    }

    @Select("SELECT AVG(gift_ratio) FROM rule_config WHERE status = '1'")
    BigDecimal selectAvgGiftRatio();

    @Select("SELECT type, COUNT(*) as count FROM rule_config GROUP BY type")
    List<java.util.Map<String, Object>> selectTypeCountList();

}
