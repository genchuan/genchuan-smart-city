package cn.iocoder.yudao.module.datacenter.dal.mysql.datavisualization.screentemplatemng;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.screentemplatemng.vo.ScreenTemplateMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.datavisualization.screentemplatemng.ScreenTemplateMngDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 大屏模板管理 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ScreenTemplateMngMapper extends BaseMapperX<ScreenTemplateMngDO> {

    default PageResult<ScreenTemplateMngDO> selectPage(ScreenTemplateMngPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ScreenTemplateMngDO>()
                .eqIfPresent(ScreenTemplateMngDO::getScreenTemplateId, reqVO.getScreenTemplateId())
                .likeIfPresent(ScreenTemplateMngDO::getTemplateName, reqVO.getTemplateName())
                .eqIfPresent(ScreenTemplateMngDO::getScreenLayout, reqVO.getScreenLayout())
                .eqIfPresent(ScreenTemplateMngDO::getDataSourceCfg, reqVO.getDataSourceCfg())
                .eqIfPresent(ScreenTemplateMngDO::getComponentCfg, reqVO.getComponentCfg())
                .eqIfPresent(ScreenTemplateMngDO::getRefreshFreq, reqVO.getRefreshFreq())
                .eqIfPresent(ScreenTemplateMngDO::getTemplateStatus, reqVO.getTemplateStatus())
                .eqIfPresent(ScreenTemplateMngDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(ScreenTemplateMngDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ScreenTemplateMngDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(ScreenTemplateMngDO::getTemplatePreview, reqVO.getTemplatePreview())
                .eqIfPresent(ScreenTemplateMngDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ScreenTemplateMngDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ScreenTemplateMngDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ScreenTemplateMngDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ScreenTemplateMngDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(ScreenTemplateMngDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(ScreenTemplateMngDO::getId));
    }

}