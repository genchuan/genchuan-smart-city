package cn.iocoder.yudao.module.evaluate.dal.mysql.ruletype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.ruletype.vo.RuleTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.sys.ruletype.RuleTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 规则类型字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RuleTypeMapper extends BaseMapperX<RuleTypeDO> {

    default PageResult<RuleTypeDO> selectPage(RuleTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RuleTypeDO>()
                .eqIfPresent(RuleTypeDO::getTypeId, reqVO.getTypeId())
                .likeIfPresent(RuleTypeDO::getName, reqVO.getName())
                .eqIfPresent(RuleTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(RuleTypeDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(RuleTypeDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(RuleTypeDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(RuleTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RuleTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RuleTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RuleTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RuleTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RuleTypeDO::getId));
    }

}