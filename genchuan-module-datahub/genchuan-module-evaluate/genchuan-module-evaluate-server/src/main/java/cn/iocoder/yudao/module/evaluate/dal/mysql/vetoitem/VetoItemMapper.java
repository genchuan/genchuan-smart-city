package cn.iocoder.yudao.module.evaluate.dal.mysql.vetoitem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.vetoitem.vo.VetoItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.vetoitem.VetoItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 否决项 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface VetoItemMapper extends BaseMapperX<VetoItemDO> {

    default PageResult<VetoItemDO> selectPage(VetoItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VetoItemDO>()
                .eqIfPresent(VetoItemDO::getVetoItemId, reqVO.getVetoItemId())
                .likeIfPresent(VetoItemDO::getName, reqVO.getName())
                .eqIfPresent(VetoItemDO::getObjectTypeId, reqVO.getObjectTypeId())
                .eqIfPresent(VetoItemDO::getCondition, reqVO.getCondition())
                .eqIfPresent(VetoItemDO::getValidCycle, reqVO.getValidCycle())
                .eqIfPresent(VetoItemDO::getCount, reqVO.getCount())
                .eqIfPresent(VetoItemDO::getStatusId, reqVO.getStatusId())
                .betweenIfPresent(VetoItemDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(VetoItemDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(VetoItemDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(VetoItemDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(VetoItemDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(VetoItemDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(VetoItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(VetoItemDO::getId));
    }

}