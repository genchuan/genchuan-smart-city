package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.data.basicmonpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonpt.vo.BasicMonPtPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonpt.BasicMonPtDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基础监测点位 Mapper
 *
 * @author zcq
 */
@Mapper
public interface BasicMonPtMapper extends BaseMapperX<BasicMonPtDO> {

    default PageResult<BasicMonPtDO> selectPage(BasicMonPtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BasicMonPtDO>()
                .eqIfPresent(BasicMonPtDO::getPtId, reqVO.getPtId())
                .likeIfPresent(BasicMonPtDO::getPtName, reqVO.getPtName())
                .eqIfPresent(BasicMonPtDO::getPtCode, reqVO.getPtCode())
                .eqIfPresent(BasicMonPtDO::getPtType, reqVO.getPtType())
                .eqIfPresent(BasicMonPtDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(BasicMonPtDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(BasicMonPtDO::getDeviceCode, reqVO.getDeviceCode())
                .likeIfPresent(BasicMonPtDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(BasicMonPtDO::getPtCoordX, reqVO.getPtCoordX())
                .eqIfPresent(BasicMonPtDO::getPtCoordY, reqVO.getPtCoordY())
                .eqIfPresent(BasicMonPtDO::getMngrId, reqVO.getMngrId())
                .likeIfPresent(BasicMonPtDO::getMngrName, reqVO.getMngrName())
                .eqIfPresent(BasicMonPtDO::getMngrPhone, reqVO.getMngrPhone())
                .eqIfPresent(BasicMonPtDO::getPtStatus, reqVO.getPtStatus())
                .eqIfPresent(BasicMonPtDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(BasicMonPtDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(BasicMonPtDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(BasicMonPtDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(BasicMonPtDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(BasicMonPtDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(BasicMonPtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BasicMonPtDO::getId));
    }

}