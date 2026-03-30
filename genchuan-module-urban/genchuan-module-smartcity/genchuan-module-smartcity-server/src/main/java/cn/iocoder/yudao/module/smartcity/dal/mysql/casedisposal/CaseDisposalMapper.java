package cn.iocoder.yudao.module.smartcity.dal.mysql.casedisposal;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.casedisposal.CaseDisposalDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.casedisposal.vo.*;

/**
 * 案件处理 Mapper
 *
 * @author 朱聪权
 */
@Mapper
public interface CaseDisposalMapper extends BaseMapperX<CaseDisposalDO> {

    default PageResult<CaseDisposalDO> selectPage(CaseDisposalPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CaseDisposalDO>()
                .likeIfPresent(CaseDisposalDO::getCaseId, reqVO.getCaseId())
                .likeIfPresent(CaseDisposalDO::getDisposalType, reqVO.getDisposalType())
                .betweenIfPresent(CaseDisposalDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CaseDisposalDO::getId));
    }

}