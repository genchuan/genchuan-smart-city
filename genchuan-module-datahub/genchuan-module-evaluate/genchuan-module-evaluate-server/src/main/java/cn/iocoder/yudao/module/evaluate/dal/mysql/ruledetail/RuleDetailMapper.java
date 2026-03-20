package cn.iocoder.yudao.module.evaluate.dal.mysql.ruledetail;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.ruledetail.RuleDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.*;

/**
 * 评分规则明细 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RuleDetailMapper extends BaseMapperX<RuleDetailDO> {

    default PageResult<RuleDetailDO> selectPage(RuleDetailPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RuleDetailDO>()
                .eqIfPresent(RuleDetailDO::getRuleId, reqVO.getRuleId())
                .eqIfPresent(RuleDetailDO::getMinValue, reqVO.getMinValue())
                .eqIfPresent(RuleDetailDO::getMaxValue, reqVO.getMaxValue())
                .eqIfPresent(RuleDetailDO::getOperatorMin, reqVO.getOperatorMin())
                .eqIfPresent(RuleDetailDO::getOperatorMax, reqVO.getOperatorMax())
                .eqIfPresent(RuleDetailDO::getScore, reqVO.getScore())
                .eqIfPresent(RuleDetailDO::getSortOrder, reqVO.getSortOrder())
                .eqIfPresent(RuleDetailDO::getRemark, reqVO.getRemark())
                .eqIfPresent(RuleDetailDO::getCreator, reqVO.getCreator())
                .eqIfPresent(RuleDetailDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(RuleDetailDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(RuleDetailDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(RuleDetailDO::getId));
    }

    @InterceptorIgnore(tenantLine = "true")
    @Select("<script>SELECT * FROM eval_rule_detail WHERE rule_id = #{ruleId} ORDER BY sort_order ASC</script>")
    List<RuleDetailDO> selectListByRuleId(Long ruleId);

}