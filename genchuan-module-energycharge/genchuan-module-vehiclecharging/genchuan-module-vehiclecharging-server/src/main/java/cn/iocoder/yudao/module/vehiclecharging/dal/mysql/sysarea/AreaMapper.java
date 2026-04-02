package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.sysarea;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sysarea.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sysarea.AreaDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 统一行政区划配置表（树形结构） Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AreaMapper extends BaseMapperX<AreaDO> {

    default PageResult<AreaDO> selectPage(AreaPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AreaDO>()
                .eqIfPresent(AreaDO::getParentId, reqVO.getParentId())
                .eqIfPresent(AreaDO::getFullCode, reqVO.getFullCode())
                .eqIfPresent(AreaDO::getShortCode, reqVO.getShortCode())
                .eqIfPresent(AreaDO::getCommId, reqVO.getCommId())
                .likeIfPresent(AreaDO::getName, reqVO.getName())
                .eqIfPresent(AreaDO::getLevel, reqVO.getLevel())
                .eqIfPresent(AreaDO::getAreaType, reqVO.getAreaType())
                .betweenIfPresent(AreaDO::getEffectiveTime, reqVO.getEffectiveTime())
                .betweenIfPresent(AreaDO::getInvalidTime, reqVO.getInvalidTime())
                .eqIfPresent(AreaDO::getBoundary, reqVO.getBoundary())
                .eqIfPresent(AreaDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AreaDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AreaDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AreaDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AreaDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AreaDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AreaDO::getId));
    }

}