package cn.iocoder.yudao.module.industry.dal.mysql.park.through.specialrelease;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.specialrelease.vo.ParkSpecialReleasePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.specialrelease.ParkSpecialReleaseDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 特殊放行 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkSpecialReleaseMapper extends BaseMapperX<ParkSpecialReleaseDO> {

    default PageResult<ParkSpecialReleaseDO> selectPage(ParkSpecialReleasePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkSpecialReleaseDO>()
                .eqIfPresent(ParkSpecialReleaseDO::getReleaseId, reqVO.getReleaseId())
                .eqIfPresent(ParkSpecialReleaseDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(ParkSpecialReleaseDO::getReleaseType, reqVO.getReleaseType())
                .eqIfPresent(ParkSpecialReleaseDO::getReason, reqVO.getReason())
                .eqIfPresent(ParkSpecialReleaseDO::getEntryExitId, reqVO.getEntryExitId())
                .eqIfPresent(ParkSpecialReleaseDO::getReleaseBy, reqVO.getReleaseBy())
                .betweenIfPresent(ParkSpecialReleaseDO::getReleaseTime, reqVO.getReleaseTime())
                .eqIfPresent(ParkSpecialReleaseDO::getVerifyStatus, reqVO.getVerifyStatus())
                .eqIfPresent(ParkSpecialReleaseDO::getVerifyBy, reqVO.getVerifyBy())
                .betweenIfPresent(ParkSpecialReleaseDO::getVerifyTime, reqVO.getVerifyTime())
                .betweenIfPresent(ParkSpecialReleaseDO::getReleaseCreateTime, reqVO.getReleaseCreateTime())
                .betweenIfPresent(ParkSpecialReleaseDO::getReleaseUpdateTime, reqVO.getReleaseUpdateTime())
                .eqIfPresent(ParkSpecialReleaseDO::getReleaseRemark, reqVO.getReleaseRemark())
                .betweenIfPresent(ParkSpecialReleaseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkSpecialReleaseDO::getId));
    }

}