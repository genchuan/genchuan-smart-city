package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.data.basicmonidx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonidx.vo.BasicMonIdxPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonidx.BasicMonIdxDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基础监测指标 Mapper
 *
 * @author zcq
 */
@Mapper
public interface BasicMonIdxMapper extends BaseMapperX<BasicMonIdxDO> {

    default PageResult<BasicMonIdxDO> selectPage(BasicMonIdxPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BasicMonIdxDO>()
                .eqIfPresent(BasicMonIdxDO::getIdxId, reqVO.getIdxId())
                .likeIfPresent(BasicMonIdxDO::getIdxName, reqVO.getIdxName())
                .eqIfPresent(BasicMonIdxDO::getIdxCode, reqVO.getIdxCode())
                .eqIfPresent(BasicMonIdxDO::getIdxUnit, reqVO.getIdxUnit())
                .eqIfPresent(BasicMonIdxDO::getDataType, reqVO.getDataType())
                .eqIfPresent(BasicMonIdxDO::getDataLength, reqVO.getDataLength())
                .eqIfPresent(BasicMonIdxDO::getRelPtType, reqVO.getRelPtType())
                .eqIfPresent(BasicMonIdxDO::getWarnThresholdMax, reqVO.getWarnThresholdMax())
                .eqIfPresent(BasicMonIdxDO::getWarnThresholdMin, reqVO.getWarnThresholdMin())
                .eqIfPresent(BasicMonIdxDO::getIdxDesc, reqVO.getIdxDesc())
                .eqIfPresent(BasicMonIdxDO::getIdxStatus, reqVO.getIdxStatus())
                .eqIfPresent(BasicMonIdxDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(BasicMonIdxDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(BasicMonIdxDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(BasicMonIdxDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(BasicMonIdxDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(BasicMonIdxDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(BasicMonIdxDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BasicMonIdxDO::getId));
    }

}