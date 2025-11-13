package cn.iocoder.yudao.module.datacenter.dal.mysql.datavisualization.customrptcfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.customrptcfg.vo.CustomRptCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.datavisualization.customrptcfg.CustomRptCfgDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 自定义报表配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CustomRptCfgMapper extends BaseMapperX<CustomRptCfgDO> {

    default PageResult<CustomRptCfgDO> selectPage(CustomRptCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomRptCfgDO>()
                .eqIfPresent(CustomRptCfgDO::getCustomRptCfgId, reqVO.getCustomRptCfgId())
                .likeIfPresent(CustomRptCfgDO::getRptTemplateName, reqVO.getRptTemplateName())
                .eqIfPresent(CustomRptCfgDO::getRptType, reqVO.getRptType())
                .eqIfPresent(CustomRptCfgDO::getDataSourceTable, reqVO.getDataSourceTable())
                .eqIfPresent(CustomRptCfgDO::getStatDimensionCfg, reqVO.getStatDimensionCfg())
                .eqIfPresent(CustomRptCfgDO::getStatIdxCfg, reqVO.getStatIdxCfg())
                .eqIfPresent(CustomRptCfgDO::getDisplayStyleCfg, reqVO.getDisplayStyleCfg())
                .eqIfPresent(CustomRptCfgDO::getFilterCfg, reqVO.getFilterCfg())
                .eqIfPresent(CustomRptCfgDO::getTemplateStatus, reqVO.getTemplateStatus())
                .eqIfPresent(CustomRptCfgDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(CustomRptCfgDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(CustomRptCfgDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(CustomRptCfgDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(CustomRptCfgDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(CustomRptCfgDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CustomRptCfgDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CustomRptCfgDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(CustomRptCfgDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(CustomRptCfgDO::getId));
    }

}