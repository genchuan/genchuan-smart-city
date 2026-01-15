package cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parksmstemplate;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parksmstemplate.vo.ParkSmsTemplatePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parksmstemplate.ParkSmsTemplateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信模板 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkSmsTemplateMapper extends BaseMapperX<ParkSmsTemplateDO> {

    default PageResult<ParkSmsTemplateDO> selectPage(ParkSmsTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkSmsTemplateDO>()
                .eqIfPresent(ParkSmsTemplateDO::getTemplateCode, reqVO.getTemplateCode())
                .likeIfPresent(ParkSmsTemplateDO::getTemplateName, reqVO.getTemplateName())
                .eqIfPresent(ParkSmsTemplateDO::getSmsType, reqVO.getSmsType())
                .eqIfPresent(ParkSmsTemplateDO::getContent, reqVO.getContent())
                .eqIfPresent(ParkSmsTemplateDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkSmsTemplateDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkSmsTemplateDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkSmsTemplateDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkSmsTemplateDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkSmsTemplateDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkSmsTemplateDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkSmsTemplateDO::getId));
    }

}
