package cn.iocoder.yudao.module.datacenter.dal.mysql.policylegislation.nationalpol;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.nationalpol.vo.NationalPolPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.nationalpol.NationalPolDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 国家政策信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface NationalPolMapper extends BaseMapperX<NationalPolDO> {

    default PageResult<NationalPolDO> selectPage(NationalPolPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<NationalPolDO>()
                .eqIfPresent(NationalPolDO::getNationalPolId, reqVO.getNationalPolId())
                .eqIfPresent(NationalPolDO::getPolTitle, reqVO.getPolTitle())
                .eqIfPresent(NationalPolDO::getPolDocNo, reqVO.getPolDocNo())
                .eqIfPresent(NationalPolDO::getIssueDeptCode, reqVO.getIssueDeptCode())
                .likeIfPresent(NationalPolDO::getIssueDeptName, reqVO.getIssueDeptName())
                .betweenIfPresent(NationalPolDO::getIssueTime, reqVO.getIssueTime())
                .betweenIfPresent(NationalPolDO::getImplementTime, reqVO.getImplementTime())
                .eqIfPresent(NationalPolDO::getPolFileId, reqVO.getPolFileId())
                .eqIfPresent(NationalPolDO::getPolFilePath, reqVO.getPolFilePath())
                .eqIfPresent(NationalPolDO::getPolStatus, reqVO.getPolStatus())
                .eqIfPresent(NationalPolDO::getPolSummary, reqVO.getPolSummary())
                .eqIfPresent(NationalPolDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(NationalPolDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(NationalPolDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(NationalPolDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(NationalPolDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(NationalPolDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(NationalPolDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(NationalPolDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(NationalPolDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(NationalPolDO::getId));
    }

}