package cn.iocoder.yudao.module.datacenter.dal.mysql.industrydynamic.internationaldynamic;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.internationaldynamic.vo.InternationalDynamicPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.internationaldynamic.InternationalDynamicDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 国际行业动态 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface InternationalDynamicMapper extends BaseMapperX<InternationalDynamicDO> {

    default PageResult<InternationalDynamicDO> selectPage(InternationalDynamicPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InternationalDynamicDO>()
                .eqIfPresent(InternationalDynamicDO::getInternationalDynamicId, reqVO.getInternationalDynamicId())
                .eqIfPresent(InternationalDynamicDO::getDynamicTitle, reqVO.getDynamicTitle())
                .eqIfPresent(InternationalDynamicDO::getDynamicTitleEn, reqVO.getDynamicTitleEn())
                .eqIfPresent(InternationalDynamicDO::getSourceCountry, reqVO.getSourceCountry())
                .eqIfPresent(InternationalDynamicDO::getDynamicSource, reqVO.getDynamicSource())
                .betweenIfPresent(InternationalDynamicDO::getReleaseTime, reqVO.getReleaseTime())
                .eqIfPresent(InternationalDynamicDO::getDynamicDetail, reqVO.getDynamicDetail())
                .eqIfPresent(InternationalDynamicDO::getDynamicDetailEn, reqVO.getDynamicDetailEn())
                .eqIfPresent(InternationalDynamicDO::getCoverImgId, reqVO.getCoverImgId())
                .eqIfPresent(InternationalDynamicDO::getCoverImgPath, reqVO.getCoverImgPath())
                .eqIfPresent(InternationalDynamicDO::getDynamicStatus, reqVO.getDynamicStatus())
                .eqIfPresent(InternationalDynamicDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(InternationalDynamicDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(InternationalDynamicDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(InternationalDynamicDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InternationalDynamicDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InternationalDynamicDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InternationalDynamicDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(InternationalDynamicDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(InternationalDynamicDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(InternationalDynamicDO::getId));
    }

}