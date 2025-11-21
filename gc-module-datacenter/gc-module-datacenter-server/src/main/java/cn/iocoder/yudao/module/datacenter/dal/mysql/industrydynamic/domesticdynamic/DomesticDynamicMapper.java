package cn.iocoder.yudao.module.datacenter.dal.mysql.industrydynamic.domesticdynamic;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.domesticdynamic.vo.DomesticDynamicPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.domesticdynamic.DomesticDynamicDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 国内行业动态 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DomesticDynamicMapper extends BaseMapperX<DomesticDynamicDO> {

    default PageResult<DomesticDynamicDO> selectPage(DomesticDynamicPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DomesticDynamicDO>()
                .eqIfPresent(DomesticDynamicDO::getDomesticDynamicId, reqVO.getDomesticDynamicId())
                .eqIfPresent(DomesticDynamicDO::getDynamicTitle, reqVO.getDynamicTitle())
                .eqIfPresent(DomesticDynamicDO::getDynamicTag, reqVO.getDynamicTag())
                .eqIfPresent(DomesticDynamicDO::getDynamicSource, reqVO.getDynamicSource())
                .betweenIfPresent(DomesticDynamicDO::getReleaseTime, reqVO.getReleaseTime())
                .eqIfPresent(DomesticDynamicDO::getDynamicDetail, reqVO.getDynamicDetail())
                .eqIfPresent(DomesticDynamicDO::getCoverImgId, reqVO.getCoverImgId())
                .eqIfPresent(DomesticDynamicDO::getCoverImgPath, reqVO.getCoverImgPath())
                .eqIfPresent(DomesticDynamicDO::getDynamicStatus, reqVO.getDynamicStatus())
                .eqIfPresent(DomesticDynamicDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(DomesticDynamicDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(DomesticDynamicDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(DomesticDynamicDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DomesticDynamicDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DomesticDynamicDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DomesticDynamicDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(DomesticDynamicDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DomesticDynamicDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(DomesticDynamicDO::getId));
    }

}