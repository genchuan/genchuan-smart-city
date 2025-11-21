package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.resource.inspectpersoninfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectpersoninfo.vo.InspectPersonInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectpersoninfo.InspectPersonInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检人员信息 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectPersonInfoMapper extends BaseMapperX<InspectPersonInfoDO> {

    default PageResult<InspectPersonInfoDO> selectPage(InspectPersonInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectPersonInfoDO>()
                .eqIfPresent(InspectPersonInfoDO::getPersonId, reqVO.getPersonId())
                .likeIfPresent(InspectPersonInfoDO::getPersonName, reqVO.getPersonName())
                .eqIfPresent(InspectPersonInfoDO::getPersonAccount, reqVO.getPersonAccount())
                .eqIfPresent(InspectPersonInfoDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(InspectPersonInfoDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(InspectPersonInfoDO::getPersonPhone, reqVO.getPersonPhone())
                .eqIfPresent(InspectPersonInfoDO::getGridId, reqVO.getGridId())
                .likeIfPresent(InspectPersonInfoDO::getGridName, reqVO.getGridName())
                .eqIfPresent(InspectPersonInfoDO::getPersonRole, reqVO.getPersonRole())
                .eqIfPresent(InspectPersonInfoDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(InspectPersonInfoDO::getPersonStatus, reqVO.getPersonStatus())
                .betweenIfPresent(InspectPersonInfoDO::getEntryDate, reqVO.getEntryDate())
                .eqIfPresent(InspectPersonInfoDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(InspectPersonInfoDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectPersonInfoDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(InspectPersonInfoDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(InspectPersonInfoDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectPersonInfoDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectPersonInfoDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectPersonInfoDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectPersonInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectPersonInfoDO::getId));
    }

}