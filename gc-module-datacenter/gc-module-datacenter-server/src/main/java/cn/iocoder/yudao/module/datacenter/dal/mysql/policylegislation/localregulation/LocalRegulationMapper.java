package cn.iocoder.yudao.module.datacenter.dal.mysql.policylegislation.localregulation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.localregulation.vo.LocalRegulationPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.localregulation.LocalRegulationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地方法规信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface LocalRegulationMapper extends BaseMapperX<LocalRegulationDO> {

    default PageResult<LocalRegulationDO> selectPage(LocalRegulationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LocalRegulationDO>()
                .eqIfPresent(LocalRegulationDO::getLocalRegulationId, reqVO.getLocalRegulationId())
                .eqIfPresent(LocalRegulationDO::getRegulationTitle, reqVO.getRegulationTitle())
                .eqIfPresent(LocalRegulationDO::getRegulationDocNo, reqVO.getRegulationDocNo())
                .eqIfPresent(LocalRegulationDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(LocalRegulationDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(LocalRegulationDO::getIssueDeptCode, reqVO.getIssueDeptCode())
                .likeIfPresent(LocalRegulationDO::getIssueDeptName, reqVO.getIssueDeptName())
                .betweenIfPresent(LocalRegulationDO::getIssueTime, reqVO.getIssueTime())
                .betweenIfPresent(LocalRegulationDO::getImplementTime, reqVO.getImplementTime())
                .eqIfPresent(LocalRegulationDO::getRegulationFileId, reqVO.getRegulationFileId())
                .eqIfPresent(LocalRegulationDO::getRegulationFilePath, reqVO.getRegulationFilePath())
                .eqIfPresent(LocalRegulationDO::getRegulationStatus, reqVO.getRegulationStatus())
                .eqIfPresent(LocalRegulationDO::getRegulationSummary, reqVO.getRegulationSummary())
                .eqIfPresent(LocalRegulationDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(LocalRegulationDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(LocalRegulationDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(LocalRegulationDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(LocalRegulationDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(LocalRegulationDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(LocalRegulationDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(LocalRegulationDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(LocalRegulationDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(LocalRegulationDO::getId));
    }

}