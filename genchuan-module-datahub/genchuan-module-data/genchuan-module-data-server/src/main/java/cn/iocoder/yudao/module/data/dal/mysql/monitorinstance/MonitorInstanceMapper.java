package cn.iocoder.yudao.module.data.dal.mysql.monitorinstance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo.MonitorInstancePageReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.monitorinstance.MonitorInstanceDO;
import cn.iocoder.yudao.module.data.service.monitorcategory.MonitorCategoryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 监测部件实例 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface MonitorInstanceMapper extends BaseMapperX<MonitorInstanceDO> {

    default PageResult<MonitorInstanceDO> selectPage(MonitorInstancePageReqVO reqVO, MonitorCategoryService monitorCategoryService) {
        // 创建分页对象
        Page<MonitorInstanceDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());

        // 构建查询条件
        LambdaQueryWrapperX<MonitorInstanceDO> wrapper = new LambdaQueryWrapperX<MonitorInstanceDO>()
                .likeIfPresent(MonitorInstanceDO::getName, reqVO.getName())
                .eqIfPresent(MonitorInstanceDO::getUniqueCode, reqVO.getUniqueCode())
                .eqIfPresent(MonitorInstanceDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(MonitorInstanceDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(MonitorInstanceDO::getGridId, reqVO.getGridId())
                .eqIfPresent(MonitorInstanceDO::getGridName, reqVO.getGridName())
                .eqIfPresent(MonitorInstanceDO::getCoordinate, reqVO.getCoordinate())
                .eqIfPresent(MonitorInstanceDO::getRunStatus, reqVO.getRunStatus())
                .eqIfPresent(MonitorInstanceDO::getProtocol, reqVO.getProtocol())
                .betweenIfPresent(MonitorInstanceDO::getInstallTime, reqVO.getInstallTime())
                .eqIfPresent(MonitorInstanceDO::getCalibrateCycle, reqVO.getCalibrateCycle())
                .betweenIfPresent(MonitorInstanceDO::getNextCalibrateTime, reqVO.getNextCalibrateTime())
                .betweenIfPresent(MonitorInstanceDO::getLastCalibrateTime, reqVO.getLastCalibrateTime())
                .betweenIfPresent(MonitorInstanceDO::getLastCheckTime, reqVO.getLastCheckTime())
                .eqIfPresent(MonitorInstanceDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(MonitorInstanceDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(MonitorInstanceDO::getRelatedPartId, reqVO.getRelatedPartId())
                .eqIfPresent(MonitorInstanceDO::getRelatedPartName, reqVO.getRelatedPartName())
                .eqIfPresent(MonitorInstanceDO::getCalibrateLog, reqVO.getCalibrateLog())
                .eqIfPresent(MonitorInstanceDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MonitorInstanceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MonitorInstanceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(MonitorInstanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MonitorInstanceDO::getId);

        // 处理树形查询
        if (reqVO.getTreeParentId() != null && !reqVO.getTreeParentId().trim().isEmpty()) {
            try {
                // 将字符串形式的treeParentId转换为Long
                Long parentIdLong = Long.parseLong(reqVO.getTreeParentId());

                // 调用正确的服务方法获取分类ID列表
                List<Long> subCategoryIds = monitorCategoryService.getCategoryAndChildrenIds(parentIdLong);

                // 根据includeSelf参数决定是否移除父节点
                boolean includeSelf = reqVO.getIncludeSelf() != null ? reqVO.getIncludeSelf() : true;
                if (!includeSelf) {
                    subCategoryIds.remove(parentIdLong);
                }

                // 如果子分类ID列表为空，则返回空结果
                if (subCategoryIds.isEmpty()) {
                    return new PageResult<>(Collections.emptyList(), 0L);
                }

                // 将Long类型的ID转换为String类型（因为categoryId是String类型）
                List<String> categoryIdStrs = subCategoryIds.stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList());

                // 使用in条件查询
                wrapper.in(MonitorInstanceDO::getCategoryId, categoryIdStrs);
            } catch (NumberFormatException e) {
                // 如果转换失败，说明treeParentId不是有效的Long字符串，返回空结果
                return new PageResult<>(Collections.emptyList(), 0L);
            }
        }

        // 执行分页查询
        IPage<MonitorInstanceDO> resultPage = selectPage(page, wrapper);
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

}