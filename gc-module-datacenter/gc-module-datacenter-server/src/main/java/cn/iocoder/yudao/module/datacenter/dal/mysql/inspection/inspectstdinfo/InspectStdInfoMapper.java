package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.inspectstdinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.inspectstdinfo.vo.InspectStdInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.inspectstdinfo.InspectStdInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检标准信息 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectStdInfoMapper extends BaseMapperX<InspectStdInfoDO> {

    default PageResult<InspectStdInfoDO> selectPage(InspectStdInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectStdInfoDO>()
                .eqIfPresent(InspectStdInfoDO::getStdId, reqVO.getStdId())
                .likeIfPresent(InspectStdInfoDO::getStdName, reqVO.getStdName())
                .eqIfPresent(InspectStdInfoDO::getStdCode, reqVO.getStdCode())
                .eqIfPresent(InspectStdInfoDO::getStdType, reqVO.getStdType())
                .eqIfPresent(InspectStdInfoDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectStdInfoDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectStdInfoDO::getCkItemList, reqVO.getCkItemList())
                .eqIfPresent(InspectStdInfoDO::getScoreRule, reqVO.getScoreRule())
                .eqIfPresent(InspectStdInfoDO::getQualifiedThreshold, reqVO.getQualifiedThreshold())
                .eqIfPresent(InspectStdInfoDO::getStdStatus, reqVO.getStdStatus())
                .betweenIfPresent(InspectStdInfoDO::getEffectiveTime, reqVO.getEffectiveTime())
                .betweenIfPresent(InspectStdInfoDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(InspectStdInfoDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(InspectStdInfoDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectStdInfoDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(InspectStdInfoDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(InspectStdInfoDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectStdInfoDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectStdInfoDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectStdInfoDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectStdInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectStdInfoDO::getId));
    }

}