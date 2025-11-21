package cn.iocoder.yudao.module.datacenter.dal.mysql.policylegislation.industrystd;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.industrystd.vo.IndustryStdPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.industrystd.IndustryStdDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 行业规范信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface IndustryStdMapper extends BaseMapperX<IndustryStdDO> {

    default PageResult<IndustryStdDO> selectPage(IndustryStdPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IndustryStdDO>()
                .eqIfPresent(IndustryStdDO::getIndustryStdId, reqVO.getIndustryStdId())
                .eqIfPresent(IndustryStdDO::getStdTitle, reqVO.getStdTitle())
                .eqIfPresent(IndustryStdDO::getIndustryTypeCode, reqVO.getIndustryTypeCode())
                .likeIfPresent(IndustryStdDO::getIndustryTypeName, reqVO.getIndustryTypeName())
                .eqIfPresent(IndustryStdDO::getStdNo, reqVO.getStdNo())
                .eqIfPresent(IndustryStdDO::getIssueOrg, reqVO.getIssueOrg())
                .betweenIfPresent(IndustryStdDO::getIssueTime, reqVO.getIssueTime())
                .eqIfPresent(IndustryStdDO::getStdFileId, reqVO.getStdFileId())
                .eqIfPresent(IndustryStdDO::getStdFilePath, reqVO.getStdFilePath())
                .eqIfPresent(IndustryStdDO::getStdStatus, reqVO.getStdStatus())
                .eqIfPresent(IndustryStdDO::getCoreClause, reqVO.getCoreClause())
                .eqIfPresent(IndustryStdDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(IndustryStdDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(IndustryStdDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(IndustryStdDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(IndustryStdDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(IndustryStdDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(IndustryStdDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(IndustryStdDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(IndustryStdDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(IndustryStdDO::getId));
    }

}