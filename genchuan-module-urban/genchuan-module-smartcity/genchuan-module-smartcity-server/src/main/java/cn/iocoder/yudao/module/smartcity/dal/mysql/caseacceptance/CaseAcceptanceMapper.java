package cn.iocoder.yudao.module.smartcity.dal.mysql.caseacceptance;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.caseacceptance.CaseAcceptanceDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.caseacceptance.vo.*;

/**
 * 案件受理 Mapper
 *
 * @author 朱聪权
 */
@Mapper
public interface CaseAcceptanceMapper extends BaseMapperX<CaseAcceptanceDO> {

    default PageResult<CaseAcceptanceDO> selectPage(CaseAcceptancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CaseAcceptanceDO>()
                .likeIfPresent(CaseAcceptanceDO::getCaseCode, reqVO.getCaseCode())
                .likeIfPresent(CaseAcceptanceDO::getCaseName, reqVO.getCaseName())
                .betweenIfPresent(CaseAcceptanceDO::getCaseTime, reqVO.getCaseTime())
                .orderByDesc(CaseAcceptanceDO::getId));
    }

}