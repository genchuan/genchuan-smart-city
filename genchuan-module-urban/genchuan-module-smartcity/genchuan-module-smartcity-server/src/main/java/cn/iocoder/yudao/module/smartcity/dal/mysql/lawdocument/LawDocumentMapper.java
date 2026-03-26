package cn.iocoder.yudao.module.smartcity.dal.mysql.lawdocument;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawdocument.LawDocumentDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawdocument.vo.*;

/**
 * 执法文书 Mapper
 *
 * @author 朱聪权
 */
@Mapper
public interface LawDocumentMapper extends BaseMapperX<LawDocumentDO> {

    default PageResult<LawDocumentDO> selectPage(LawDocumentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LawDocumentDO>()
                .eqIfPresent(LawDocumentDO::getCaseId, reqVO.getCaseId())
                .eqIfPresent(LawDocumentDO::getDocumentType, reqVO.getDocumentType())
                .betweenIfPresent(LawDocumentDO::getApprovalTime, reqVO.getApprovalTime())
                .eqIfPresent(LawDocumentDO::getApprovalStatus, reqVO.getApprovalStatus())
                .orderByDesc(LawDocumentDO::getId));
    }

}