package cn.iocoder.yudao.module.datacenter.dal.mysql.unitgriddiv;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.unitgriddiv.UnitGridDivDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.unitgriddiv.vo.*;

/**
 * 单元网格划分 Mapper
 *
 * @author zcq
 */
@Mapper
public interface UnitGridDivMapper extends BaseMapperX<UnitGridDivDO> {

    default PageResult<UnitGridDivDO> selectPage(UnitGridDivPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UnitGridDivDO>()
                .eqIfPresent(UnitGridDivDO::getUnitGridId, reqVO.getUnitGridId())
                .likeIfPresent(UnitGridDivDO::getUnitGridName, reqVO.getUnitGridName())
                .eqIfPresent(UnitGridDivDO::getCommId, reqVO.getCommId())
                .eqIfPresent(UnitGridDivDO::getArea, reqVO.getArea())
                .eqIfPresent(UnitGridDivDO::getScale, reqVO.getScale())
                .eqIfPresent(UnitGridDivDO::getBoundaryCoords, reqVO.getBoundaryCoords())
                .betweenIfPresent(UnitGridDivDO::getDivTime, reqVO.getDivTime())
                .betweenIfPresent(UnitGridDivDO::getUpdateTime, reqVO.getUpdateTime())
                .eqIfPresent(UnitGridDivDO::getRemark, reqVO.getRemark())
                .eqIfPresent(UnitGridDivDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(UnitGridDivDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(UnitGridDivDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(UnitGridDivDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(UnitGridDivDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(UnitGridDivDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(UnitGridDivDO::getId));
    }

}