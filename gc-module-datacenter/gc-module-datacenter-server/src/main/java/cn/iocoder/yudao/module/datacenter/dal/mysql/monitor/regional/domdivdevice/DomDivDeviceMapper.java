package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivdevice;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivdevice.vo.DomDivDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivdevice.DomDivDeviceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备分域 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DomDivDeviceMapper extends BaseMapperX<DomDivDeviceDO> {

    default PageResult<DomDivDeviceDO> selectPage(DomDivDevicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomDivDeviceDO>()
                .eqIfPresent(DomDivDeviceDO::getDomId, reqVO.getDomId())
                .likeIfPresent(DomDivDeviceDO::getDomName, reqVO.getDomName())
                .eqIfPresent(DomDivDeviceDO::getDomCode, reqVO.getDomCode())
                .eqIfPresent(DomDivDeviceDO::getDeviceMajorId, reqVO.getDeviceMajorId())
                .likeIfPresent(DomDivDeviceDO::getDeviceMajorName, reqVO.getDeviceMajorName())
                .eqIfPresent(DomDivDeviceDO::getDeviceMinorId, reqVO.getDeviceMinorId())
                .likeIfPresent(DomDivDeviceDO::getDeviceMinorName, reqVO.getDeviceMinorName())
                .eqIfPresent(DomDivDeviceDO::getRelDeviceCount, reqVO.getRelDeviceCount())
                .eqIfPresent(DomDivDeviceDO::getDeviceOnlineRate, reqVO.getDeviceOnlineRate())
                .eqIfPresent(DomDivDeviceDO::getRelPtCount, reqVO.getRelPtCount())
                .eqIfPresent(DomDivDeviceDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(DomDivDeviceDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(DomDivDeviceDO::getDomStatus, reqVO.getDomStatus())
                .eqIfPresent(DomDivDeviceDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(DomDivDeviceDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(DomDivDeviceDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomDivDeviceDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomDivDeviceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomDivDeviceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DomDivDeviceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DomDivDeviceDO::getId));
    }

}