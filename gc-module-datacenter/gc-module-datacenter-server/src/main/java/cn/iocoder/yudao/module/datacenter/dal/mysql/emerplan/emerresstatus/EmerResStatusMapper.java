package cn.iocoder.yudao.module.datacenter.dal.mysql.emerplan.emerresstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus.vo.EmerResStatusPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerresstatus.EmerResStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资源状态统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EmerResStatusMapper extends BaseMapperX<EmerResStatusDO> {

    default PageResult<EmerResStatusDO> selectPage(EmerResStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmerResStatusDO>()
                .eqIfPresent(EmerResStatusDO::getStatId, reqVO.getStatId())
                .eqIfPresent(EmerResStatusDO::getStatCycle, reqVO.getStatCycle())
                .betweenIfPresent(EmerResStatusDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EmerResStatusDO::getResType, reqVO.getResType())
                .eqIfPresent(EmerResStatusDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(EmerResStatusDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(EmerResStatusDO::getTotalQuantity, reqVO.getTotalQuantity())
                .eqIfPresent(EmerResStatusDO::getAvailableQuantity, reqVO.getAvailableQuantity())
                .eqIfPresent(EmerResStatusDO::getAbnQuantity, reqVO.getAbnQuantity())
                .orderByDesc(EmerResStatusDO::getId));
    }

}