package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.resource.inspectdeviceinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectdeviceinfo.vo.InspectDeviceInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectdeviceinfo.InspectDeviceInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检设备信息 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectDeviceInfoMapper extends BaseMapperX<InspectDeviceInfoDO> {

    default PageResult<InspectDeviceInfoDO> selectPage(InspectDeviceInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectDeviceInfoDO>()
                .eqIfPresent(InspectDeviceInfoDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(InspectDeviceInfoDO::getDeviceCode, reqVO.getDeviceCode())
                .likeIfPresent(InspectDeviceInfoDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(InspectDeviceInfoDO::getDeviceType, reqVO.getDeviceType())
                .eqIfPresent(InspectDeviceInfoDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectDeviceInfoDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectDeviceInfoDO::getGridId, reqVO.getGridId())
                .likeIfPresent(InspectDeviceInfoDO::getGridName, reqVO.getGridName())
                .eqIfPresent(InspectDeviceInfoDO::getPersonId, reqVO.getPersonId())
                .likeIfPresent(InspectDeviceInfoDO::getPersonName, reqVO.getPersonName())
                .eqIfPresent(InspectDeviceInfoDO::getDeviceStatus, reqVO.getDeviceStatus())
                .betweenIfPresent(InspectDeviceInfoDO::getInstallTime, reqVO.getInstallTime())
                .betweenIfPresent(InspectDeviceInfoDO::getWarrantyEndTime, reqVO.getWarrantyEndTime())
                .eqIfPresent(InspectDeviceInfoDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(InspectDeviceInfoDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectDeviceInfoDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(InspectDeviceInfoDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(InspectDeviceInfoDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectDeviceInfoDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectDeviceInfoDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectDeviceInfoDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectDeviceInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectDeviceInfoDO::getId));
    }

}