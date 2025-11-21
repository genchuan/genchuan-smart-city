package cn.iocoder.yudao.module.datacenter.dal.mysql.industrydynamic.localdynamic;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.localdynamic.vo.LocalDynamicPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.localdynamic.LocalDynamicDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 本地行业动态 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface LocalDynamicMapper extends BaseMapperX<LocalDynamicDO> {

    default PageResult<LocalDynamicDO> selectPage(LocalDynamicPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LocalDynamicDO>()
                .eqIfPresent(LocalDynamicDO::getLocalDynamicId, reqVO.getLocalDynamicId())
                .eqIfPresent(LocalDynamicDO::getDynamicTitle, reqVO.getDynamicTitle())
                .eqIfPresent(LocalDynamicDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(LocalDynamicDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(LocalDynamicDO::getDynamicSource, reqVO.getDynamicSource())
                .betweenIfPresent(LocalDynamicDO::getReleaseTime, reqVO.getReleaseTime())
                .eqIfPresent(LocalDynamicDO::getDynamicDetail, reqVO.getDynamicDetail())
                .eqIfPresent(LocalDynamicDO::getCoverImgId, reqVO.getCoverImgId())
                .eqIfPresent(LocalDynamicDO::getCoverImgPath, reqVO.getCoverImgPath())
                .eqIfPresent(LocalDynamicDO::getDynamicStatus, reqVO.getDynamicStatus())
                .eqIfPresent(LocalDynamicDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(LocalDynamicDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(LocalDynamicDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(LocalDynamicDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(LocalDynamicDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(LocalDynamicDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(LocalDynamicDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(LocalDynamicDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(LocalDynamicDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(LocalDynamicDO::getId));
    }

}