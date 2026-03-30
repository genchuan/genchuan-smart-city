package cn.iocoder.yudao.module.evaluate.dal.mysql.option;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.option.vo.OptionPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.option.OptionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 选项 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OptionMapper extends BaseMapperX<OptionDO> {

    default PageResult<OptionDO> selectPage(OptionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OptionDO>()
                .eqIfPresent(OptionDO::getOptionId, reqVO.getOptionId())
                .eqIfPresent(OptionDO::getQuestionId, reqVO.getQuestionId())
                .eqIfPresent(OptionDO::getOptionContent, reqVO.getOptionContent())
                .eqIfPresent(OptionDO::getScore, reqVO.getScore())
                .eqIfPresent(OptionDO::getSortNo, reqVO.getSortNo())
                .eqIfPresent(OptionDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(OptionDO::getUpdateBy, reqVO.getUpdateBy())
                .betweenIfPresent(OptionDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(OptionDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(OptionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(OptionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(OptionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(OptionDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(OptionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OptionDO::getId));
    }

}