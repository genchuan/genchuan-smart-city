package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.area.area;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.area.AreaDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 统一行政区划配置表（树形结构） Mapper
 *
 * @author zcq
 */
@Mapper
public interface AreaMapper extends BaseMapperX<AreaDO> {

    default PageResult<AreaDO> selectPage(AreaPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AreaDO>()
                .eqIfPresent(AreaDO::getParentId, reqVO.getParentId())
                .eqIfPresent(AreaDO::getFullCode, reqVO.getFullCode())
                .eqIfPresent(AreaDO::getShortCode, reqVO.getShortCode())
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

    // 新增：查询所有数据的便捷方法
    default List<AreaDO> selectList() {
        return selectList(new LambdaQueryWrapperX<AreaDO>()
                .orderByAsc(AreaDO::getParentId)
                .orderByAsc(AreaDO::getId));
    }

    // 新增：根据社区ID查询行政区划
    default AreaDO selectByCommId(String commId) {
        return selectOne(new LambdaQueryWrapperX<AreaDO>()
                .eq(AreaDO::getCommId, commId)
                .eq(AreaDO::getLevel, 5) // 层级5表示社区
                .last("LIMIT 1"));
    }


}