package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.code.unitgridcode;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.unitgridcode.vo.UnitGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.unitgridcode.UnitGridCodeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 单元网格编码 Mapper
 *
 * @author zcq
 */
@Mapper
public interface UnitGridCodeMapper extends BaseMapperX<UnitGridCodeDO> {

    default PageResult<UnitGridCodeDO> selectPage(UnitGridCodePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UnitGridCodeDO>()
                .eqIfPresent(UnitGridCodeDO::getUnitCodeId, reqVO.getUnitCodeId())
                .eqIfPresent(UnitGridCodeDO::getUnitGridCode, reqVO.getUnitGridCode())
                .eqIfPresent(UnitGridCodeDO::getUnitGridId, reqVO.getUnitGridId())
                .eqIfPresent(UnitGridCodeDO::getAreaFullCode, reqVO.getAreaFullCode())
                .eqIfPresent(UnitGridCodeDO::getSeqCode, reqVO.getSeqCode())
                .betweenIfPresent(UnitGridCodeDO::getGenerateTime, reqVO.getGenerateTime())
                .eqIfPresent(UnitGridCodeDO::getCodeStatus, reqVO.getCodeStatus())
                .eqIfPresent(UnitGridCodeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(UnitGridCodeDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(UnitGridCodeDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(UnitGridCodeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(UnitGridCodeDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(UnitGridCodeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UnitGridCodeDO::getId));
    }


    /**
     * 根据行政区划代码查询最大的顺序码
     */
    /**
     * 根据行政区划代码查询最大的顺序码
     */
    default Integer selectMaxSeqCodeByArea(String areaFullCode) {
        // 方法1：使用 selectOne 查询最大的顺序码
        UnitGridCodeDO maxRecord = selectOne(new LambdaQueryWrapperX<UnitGridCodeDO>()
                .eq(UnitGridCodeDO::getAreaFullCode, areaFullCode)
                .select(UnitGridCodeDO::getSeqCode)
                .orderByDesc(UnitGridCodeDO::getSeqCode)
                .last("LIMIT 1"));

        if (maxRecord != null && maxRecord.getSeqCode() != null) {
            try {
                return Integer.valueOf(maxRecord.getSeqCode());
            } catch (NumberFormatException e) {
                // 如果顺序码不是数字，返回null
                return null;
            }
        }
        return null;
    }

    /**
     * 根据编码查询数量（用于唯一性校验）
     */
    default Long selectCountByCode(String unitGridCode, Long excludeId) {
        return selectCount(new LambdaQueryWrapperX<UnitGridCodeDO>()
                .eq(UnitGridCodeDO::getUnitGridCode, unitGridCode)
                .neIfPresent(UnitGridCodeDO::getId, excludeId)
        );
    }

    /**
     * 根据单元网格ID查询
     */
    default UnitGridCodeDO selectByUnitGridId(String unitGridId) {
        return selectOne(UnitGridCodeDO::getUnitGridId, unitGridId);
    }

    /**
     * 根据编码查询
     */
    default UnitGridCodeDO selectByCode(String unitGridCode) {
        return selectOne(UnitGridCodeDO::getUnitGridCode, unitGridCode);
    }

    /**
     * 根据行政区划代码查询列表
     */
    default List<UnitGridCodeDO> selectListByArea(String areaFullCode) {
        return selectList(UnitGridCodeDO::getAreaFullCode, areaFullCode);
    }

}