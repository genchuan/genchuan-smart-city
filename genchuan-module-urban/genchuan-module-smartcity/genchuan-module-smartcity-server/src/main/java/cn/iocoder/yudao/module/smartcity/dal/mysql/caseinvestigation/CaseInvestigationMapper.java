package cn.iocoder.yudao.module.smartcity.dal.mysql.caseinvestigation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.caseinvestigation.CaseInvestigationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.caseinvestigation.vo.*;

/**
 * 案件调查 Mapper
 *
 * @author 朱聪权
 */
@Mapper
public interface CaseInvestigationMapper extends BaseMapperX<CaseInvestigationDO> {

    default PageResult<CaseInvestigationDO> selectPage(CaseInvestigationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CaseInvestigationDO>()
                .likeIfPresent(CaseInvestigationDO::getCaseId, reqVO.getCaseId())
                .likeIfPresent(CaseInvestigationDO::getInvestigationResult, reqVO.getInvestigationResult())
                .orderByDesc(CaseInvestigationDO::getId));
    }

}