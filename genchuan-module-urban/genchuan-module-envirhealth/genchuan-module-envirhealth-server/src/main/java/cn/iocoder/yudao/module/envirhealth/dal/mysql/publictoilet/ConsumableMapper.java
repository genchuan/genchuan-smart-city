package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.consumable.ConsumablePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ConsumableDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 耗材字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ConsumableMapper extends BaseMapperX<ConsumableDO> {

    default PageResult<ConsumableDO> selectPage(ConsumablePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ConsumableDO>()
                .eqIfPresent(ConsumableDO::getConsumableId, reqVO.getConsumableId())
                .likeIfPresent(ConsumableDO::getConsumableName, reqVO.getConsumableName())
                .eqIfPresent(ConsumableDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(ConsumableDO::getDescription, reqVO.getDescription())
                .eqIfPresent(ConsumableDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ConsumableDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ConsumableDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ConsumableDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ConsumableDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ConsumableDO::getId));
    }

}