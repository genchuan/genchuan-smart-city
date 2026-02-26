package cn.iocoder.yudao.module.data.dal.mysql.instance;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.data.dal.dataobject.instance.InstanceDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.data.controller.admin.instance.vo.*;

/**
 * 管理部件实例 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface InstanceMapper extends BaseMapperX<InstanceDO> {

    default PageResult<InstanceDO> selectPage(InstancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InstanceDO>()
                .likeIfPresent(InstanceDO::getName, reqVO.getName())
                .eqIfPresent(InstanceDO::getUniqueCode, reqVO.getUniqueCode())
                .eqIfPresent(InstanceDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(InstanceDO::getGridId, reqVO.getGridId())
                .eqIfPresent(InstanceDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(InstanceDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(InstanceDO::getCoordVerifyFlag, reqVO.getCoordVerifyFlag())
                .eqIfPresent(InstanceDO::getRunStatusId, reqVO.getRunStatusId())
                .eqIfPresent(InstanceDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(InstanceDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(InstanceDO::getMonitorIds, reqVO.getMonitorIds())
                .eqIfPresent(InstanceDO::getMonitorCount, reqVO.getMonitorCount())
                .eqIfPresent(InstanceDO::getEventCount, reqVO.getEventCount())
                .eqIfPresent(InstanceDO::getRemark, reqVO.getRemark())
                .eqIfPresent(InstanceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InstanceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InstanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InstanceDO::getId));
    }

    /**
     * 根据分类ID查询实例列表
     * @param categoryId 分类ID
     * @return 实例列表
     */
    default List<InstanceDO> selectListByCategoryId(String categoryId) {
        return selectList(new LambdaQueryWrapperX<InstanceDO>()
                .eqIfPresent(InstanceDO::getCategoryId, categoryId)
                .orderByDesc(InstanceDO::getId));
    }

}