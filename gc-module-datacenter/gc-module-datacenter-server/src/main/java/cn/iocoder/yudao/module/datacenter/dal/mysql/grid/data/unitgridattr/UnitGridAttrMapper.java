package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.data.unitgridattr;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.unitgridattr.vo.UnitGridAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.unitgridattr.UnitGridAttrDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 单元网格属性 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface UnitGridAttrMapper extends BaseMapperX<UnitGridAttrDO> {

    default PageResult<UnitGridAttrDO> selectPage(UnitGridAttrPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UnitGridAttrDO>()
                .eqIfPresent(UnitGridAttrDO::getUnitAttrId, reqVO.getUnitAttrId())
                .eqIfPresent(UnitGridAttrDO::getUnitGridId, reqVO.getUnitGridId())
                .eqIfPresent(UnitGridAttrDO::getUnitGridCode, reqVO.getUnitGridCode())
                .eqIfPresent(UnitGridAttrDO::getArea, reqVO.getArea())
                .betweenIfPresent(UnitGridAttrDO::getInitTime, reqVO.getInitTime())
                .betweenIfPresent(UnitGridAttrDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(UnitGridAttrDO::getRemark, reqVO.getRemark())
                .eqIfPresent(UnitGridAttrDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(UnitGridAttrDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(UnitGridAttrDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(UnitGridAttrDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(UnitGridAttrDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UnitGridAttrDO::getId));
    }

}