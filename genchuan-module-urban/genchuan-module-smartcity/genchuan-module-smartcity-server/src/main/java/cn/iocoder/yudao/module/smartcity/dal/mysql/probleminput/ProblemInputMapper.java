package cn.iocoder.yudao.module.smartcity.dal.mysql.probleminput;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.probleminput.ProblemInputDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.probleminput.vo.*;

/**
 * 问题录入 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface ProblemInputMapper extends BaseMapperX<ProblemInputDO> {

    default PageResult<ProblemInputDO> selectPage(ProblemInputPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProblemInputDO>()
                .eqIfPresent(ProblemInputDO::getQuestionTitle, reqVO.getQuestionTitle())
                .eqIfPresent(ProblemInputDO::getProblemDescription, reqVO.getProblemDescription())
                .betweenIfPresent(ProblemInputDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProblemInputDO::getId));
    }

}