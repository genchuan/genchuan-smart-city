package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.RuleConfigDO;
import org.apache.ibatis.annotations.Mapper;

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

}
