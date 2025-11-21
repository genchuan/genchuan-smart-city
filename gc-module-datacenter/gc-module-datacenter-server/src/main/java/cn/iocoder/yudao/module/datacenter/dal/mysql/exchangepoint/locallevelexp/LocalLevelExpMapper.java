package cn.iocoder.yudao.module.datacenter.dal.mysql.exchangepoint.locallevelexp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.locallevelexp.vo.LocalLevelExpPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.locallevelexp.LocalLevelExpDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 本级经验分享 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface LocalLevelExpMapper extends BaseMapperX<LocalLevelExpDO> {

    default PageResult<LocalLevelExpDO> selectPage(LocalLevelExpPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LocalLevelExpDO>()
                .eqIfPresent(LocalLevelExpDO::getLocalLevelExpId, reqVO.getLocalLevelExpId())
                .eqIfPresent(LocalLevelExpDO::getExpTitle, reqVO.getExpTitle())
                .eqIfPresent(LocalLevelExpDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(LocalLevelExpDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(LocalLevelExpDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(LocalLevelExpDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(LocalLevelExpDO::getExpContent, reqVO.getExpContent())
                .eqIfPresent(LocalLevelExpDO::getImplementationSteps, reqVO.getImplementationSteps())
                .eqIfPresent(LocalLevelExpDO::getAchievementData, reqVO.getAchievementData())
                .eqIfPresent(LocalLevelExpDO::getAttachId, reqVO.getAttachId())
                .eqIfPresent(LocalLevelExpDO::getAttachPath, reqVO.getAttachPath())
                .eqIfPresent(LocalLevelExpDO::getPublishStatus, reqVO.getPublishStatus())
                .eqIfPresent(LocalLevelExpDO::getShareCount, reqVO.getShareCount())
                .eqIfPresent(LocalLevelExpDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(LocalLevelExpDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(LocalLevelExpDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(LocalLevelExpDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(LocalLevelExpDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(LocalLevelExpDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(LocalLevelExpDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(LocalLevelExpDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(LocalLevelExpDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(LocalLevelExpDO::getId));
    }

}