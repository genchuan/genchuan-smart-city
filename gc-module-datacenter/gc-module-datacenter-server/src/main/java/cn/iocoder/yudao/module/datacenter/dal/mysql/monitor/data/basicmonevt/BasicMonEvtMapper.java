package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.data.basicmonevt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonevt.vo.BasicMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonevt.BasicMonEvtDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基础监测事件 Mapper
 *
 * @author zcq
 */
@Mapper
public interface BasicMonEvtMapper extends BaseMapperX<BasicMonEvtDO> {

    default PageResult<BasicMonEvtDO> selectPage(BasicMonEvtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BasicMonEvtDO>()
                .eqIfPresent(BasicMonEvtDO::getEvtId, reqVO.getEvtId())
                .eqIfPresent(BasicMonEvtDO::getEvtCode, reqVO.getEvtCode())
                .eqIfPresent(BasicMonEvtDO::getEvtType, reqVO.getEvtType())
                .eqIfPresent(BasicMonEvtDO::getRelPtId, reqVO.getRelPtId())
                .likeIfPresent(BasicMonEvtDO::getRelPtName, reqVO.getRelPtName())
                .eqIfPresent(BasicMonEvtDO::getRelIdxId, reqVO.getRelIdxId())
                .likeIfPresent(BasicMonEvtDO::getRelIdxName, reqVO.getRelIdxName())
                .eqIfPresent(BasicMonEvtDO::getRelDeviceCode, reqVO.getRelDeviceCode())
                .likeIfPresent(BasicMonEvtDO::getRelDeviceName, reqVO.getRelDeviceName())
                .eqIfPresent(BasicMonEvtDO::getEvtValue, reqVO.getEvtValue())
                .betweenIfPresent(BasicMonEvtDO::getEvtTime, reqVO.getEvtTime())
                .eqIfPresent(BasicMonEvtDO::getDealStatus, reqVO.getDealStatus())
                .eqIfPresent(BasicMonEvtDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(BasicMonEvtDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(BasicMonEvtDO::getDealUserId, reqVO.getDealUserId())
                .likeIfPresent(BasicMonEvtDO::getDealUserName, reqVO.getDealUserName())
                .eqIfPresent(BasicMonEvtDO::getDealResult, reqVO.getDealResult())
                .betweenIfPresent(BasicMonEvtDO::getDealTime, reqVO.getDealTime())
                .eqIfPresent(BasicMonEvtDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(BasicMonEvtDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(BasicMonEvtDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(BasicMonEvtDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(BasicMonEvtDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(BasicMonEvtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BasicMonEvtDO::getId));
    }

}