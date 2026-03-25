package cn.iocoder.yudao.module.smartcity.dal.mysql.caseclosure;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.caseclosure.CaseClosureDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.caseclosure.vo.*;

/**
 * 案件结案 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface CaseClosureMapper extends BaseMapperX<CaseClosureDO> {

    default PageResult<CaseClosureDO> selectPage(CaseClosurePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CaseClosureDO>()
                .likeIfPresent(CaseClosureDO::getCaseId, reqVO.getCaseId())
                .betweenIfPresent(CaseClosureDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CaseClosureDO::getId));
    }

}