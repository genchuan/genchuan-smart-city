package cn.iocoder.yudao.module.envirhealth.dal.mysql.problemtype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.problemtype.vo.ProblemTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.problemtype.ProblemTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问题类型字典表【通用复用】 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProblemTypeMapper extends BaseMapperX<ProblemTypeDO> {

    default PageResult<ProblemTypeDO> selectPage(ProblemTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProblemTypeDO>()
                .eqIfPresent(ProblemTypeDO::getSysProblemTypeId, reqVO.getSysProblemTypeId())
                .likeIfPresent(ProblemTypeDO::getName, reqVO.getName())
                .eqIfPresent(ProblemTypeDO::getCode, reqVO.getCode())
                .eqIfPresent(ProblemTypeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ProblemTypeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ProblemTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ProblemTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ProblemTypeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ProblemTypeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ProblemTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProblemTypeDO::getId));
    }

}