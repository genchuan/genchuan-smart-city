package cn.iocoder.yudao.module.evaluate.dal.mysql.object;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.EvalObjectOverviewVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.baseinfo.relatedobject.RelatedObjectDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.object.ObjectDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.user.UserDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 评价对象 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ObjectMapper extends BaseMapperX<ObjectDO> {

    default PageResult<ObjectDO> selectPage(ObjectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ObjectDO>()
                .eqIfPresent(ObjectDO::getObjectId, reqVO.getObjectId())
                .likeIfPresent(ObjectDO::getName, reqVO.getName())
                .eqIfPresent(ObjectDO::getCode, reqVO.getCode())
                .eqIfPresent(ObjectDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(ObjectDO::getObjectTypeId, reqVO.getObjectTypeId())
                .eqIfPresent(ObjectDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(ObjectDO::getRelatedId, reqVO.getRelatedId())
                .eqIfPresent(ObjectDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(ObjectDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(ObjectDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(ObjectDO::getUpdateBy, reqVO.getUpdateBy())
                .betweenIfPresent(ObjectDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(ObjectDO::getChangeLog, reqVO.getChangeLog())
                .eqIfPresent(ObjectDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ObjectDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ObjectDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ObjectDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ObjectDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ObjectDO::getId));
    }
    // 1. 联表分页查询（对应XML的selectJoinPage）
//    List<ObjectRespVO> selectJoinPage(@Param("reqVo") ObjectPageReqVO reqVo);

    // 2. 统计联表查询总条数（对应XML的selectJoinCount）
    Long selectJoinCount(@Param("reqVo") ObjectPageReqVO reqVo);

    /**
     * 根据对象名称和区域查询
     */
    default ObjectDO selectByNameAndArea(String name, String areaCode) {
        return selectOne(new LambdaQueryWrapperX<ObjectDO>()
                .eq(ObjectDO::getName, name)
                .eq(ObjectDO::getAreaCode, areaCode)
                .eq(ObjectDO::getDeleted, false));
    }

    /**
     * 根据ID列表查询对象名称列表
     */
    default List<String> selectNamesByIds(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        LambdaQueryWrapper<ObjectDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(ObjectDO::getObjectId, ids)
                .eq(ObjectDO::getDeleted, 0)
                .select(ObjectDO::getName);
        return selectList(wrapper).stream()
                .map(ObjectDO::getName)
                .toList();
    }
    /**
     * 根据ID查询详情（带关联信息）
     */
    ObjectRespVO selectDetailById(@Param("objectId") String objectId);
    // 3. 联表查询详情（对应XML的selectJoinDetail）
    ObjectRespVO selectJoinDetail(@Param("id") Long id);

    /**
     *
     * @param reqVO
     * @return
     */
    default PageResult<ObjectRespVO> selectAllObjectJoinPage(ObjectPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<ObjectRespVO> page = new Page<>(
                Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                Objects.requireNonNullElse(reqVO.getPageSize(), 10)
        );

        // 2. 构建 MPJ 联表 Wrapper
        MPJLambdaWrapper<ObjectDO> wrapper = new MPJLambdaWrapper<ObjectDO>()
                // ===== 主表字段（建议按需选择，替代 selectAll）=====
                .selectAll(ObjectDO.class)

                // ===== 关联表字段映射 =====
                .selectAs(AreaDO::getAreaName, ObjectRespVO::getAreaName)
                .selectAs(ObjectTypeDO::getName, ObjectRespVO::getObjectTypeName)
                .selectAs(StatusDO::getName, ObjectRespVO::getStatusName)
                .selectAs(UserDO::getUserName, ObjectRespVO::getManagerName)
                .selectAs(UserDO::getUserPhone, ObjectRespVO::getManagerPhone)
//                .selectAs("u1", UserDO::getUserName, ObjectRespVO::getCreateUserName)
//                .selectAs("u2", UserDO::getUserName, ObjectRespVO::getUpdateUserName)
                .selectAs(RelatedObjectDO::getRelatedName, ObjectRespVO::getRelatedName)

                // ===== 联表关系 =====
                .leftJoin(AreaDO.class, AreaDO::getAreaCode, ObjectDO::getAreaCode)
                .leftJoin(ObjectTypeDO.class, ObjectTypeDO::getTypeId, ObjectDO::getObjectTypeId)
                .leftJoin(StatusDO.class, StatusDO::getStatusId, ObjectDO::getStatusId)
                .leftJoin(UserDO.class, UserDO::getUserId, ObjectDO::getManagerId)
//                .leftJoin(UserDO.class, "u1", UserDO::getUserId, ObjectDO::getCreateBy)
//                .leftJoin(UserDO.class, "u2", UserDO::getUserId, ObjectDO::getUpdateBy)
                .leftJoin(RelatedObjectDO.class, RelatedObjectDO::getRelatedId, ObjectDO::getRelatedId)

                // ==========================================
                // ========== 【核心新增】动态查询条件 ==========
                // ==========================================

                // 1. 对象名称（主表，模糊查询）
                .like(StrUtil.isNotBlank(reqVO.getName()), ObjectDO::getName, reqVO.getName())
                // 2. 对象编码（主表，模糊查询）
                .like(StrUtil.isNotBlank(reqVO.getCode()), ObjectDO::getCode, reqVO.getCode())

                // 3. 所属区域名称（关联表 AreaDO，精确匹配，用于钻取）
                .eq(StrUtil.isNotBlank(reqVO.getAreaName()), AreaDO::getAreaName, reqVO.getAreaName())
                // 4. 对象类型名称（关联表 ObjectTypeDO，精确匹配，用于钻取）
                .eq(StrUtil.isNotBlank(reqVO.getObjectTypeName()), ObjectTypeDO::getName, reqVO.getObjectTypeName())
                // 5. 状态名称（关联表 StatusDO，精确匹配，用于钻取）
                .eq(StrUtil.isNotBlank(reqVO.getStatusName()), StatusDO::getName, reqVO.getStatusName())

                // 6. 负责人姓名（关联表 UserDO，精确匹配）
                .eq(StrUtil.isNotBlank(reqVO.getManagerName()), UserDO::getUserName, reqVO.getManagerName())
                // 7. 联系电话（关联表 UserDO，精确匹配）
                .eq(StrUtil.isNotBlank(reqVO.getManagerPhone()), UserDO::getUserPhone, reqVO.getManagerPhone())

                // 8. 关联网格/部门名称（关联表 RelatedObjectDO，精确匹配）
                .eq(StrUtil.isNotBlank(reqVO.getRelatedName()), RelatedObjectDO::getRelatedName, reqVO.getRelatedName())
                //
                .eq(StrUtil.isNotBlank(reqVO.getStatusId()),StatusDO::getStatusId,reqVO.getStatusId())
                .eq(StrUtil.isNotBlank(reqVO.getAreaCode()),AreaDO::getAreaCode,reqVO.getAreaCode())
                // ===== 排序 =====
                .orderByDesc(ObjectDO::getCreateTime);

        // 3. 执行联表分页查询
        IPage<ObjectRespVO> resultPage = selectJoinPage(page, ObjectRespVO.class, wrapper);

        // 4. 后置处理：截取变更日志
        resultPage.getRecords().forEach(vo -> {
            if (StrUtil.isNotBlank(vo.getChangeLog())) {
                vo.setChangeLogShort(vo.getChangeLog().length() > 50
                        ? vo.getChangeLog().substring(0, 50) + "..."
                        : vo.getChangeLog());
            }
        });

        // 5. 返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }
    // ========== 1. 卡片核心数据（总数量、正常/待校验状态数） ==========
    @Select("SELECT " +
            "COUNT(*) AS totalCount, " +
            "SUM(CASE WHEN status_id = 1 THEN 1 ELSE 0 END) AS normalStatusCount, " +
            "SUM(CASE WHEN status_id = 2 THEN 1 ELSE 0 END) AS pendingCheckCount " +
            "FROM eval_object " +
            "WHERE deleted = 0")
    EvalObjectOverviewVO.CardData selectCardCoreData();

    // ========== 2. 各类型对象数（卡片子项，关联类型表获取名称） ==========
    @Select("SELECT " +
            "o.object_type_id AS typeId, " +
            "t.name AS typeName, " +
            "COUNT(*) AS count " +
            "FROM eval_object o " +
            "LEFT JOIN sys_object_type t ON o.object_type_id = t.type_id " +
            "WHERE o.deleted = 0 " +
            "GROUP BY o.object_type_id, t.name")
    List<EvalObjectOverviewVO.TypeCountItem> selectTypeCounts();

    // ========== 3. 对象类型占比（圆环图） ==========
    @Select("SELECT " +
            "t.name AS name, " +
            "COUNT(*) AS value " +
            "FROM eval_object o " +
            "LEFT JOIN sys_object_type t ON o.object_type_id = t.type_id " +
            "WHERE o.deleted = 0 " +
            "GROUP BY o.object_type_id, t.name")
    List<EvalObjectOverviewVO.PieChartItem> selectTypePieChart();

    // ========== 4. 所属区域占比（圆环图） ==========
    @Select("SELECT " +
            "a.area_name AS name, " +
            "COUNT(*) AS value " +
            "FROM eval_object o " +
            "LEFT JOIN sys_area a ON o.area_code = a.area_code " +
            "WHERE o.deleted = 0 " +
            "GROUP BY o.area_code, a.area_name")
    List<EvalObjectOverviewVO.PieChartItem> selectAreaPieChart();

    // ========== 5. 状态占比（圆环图） ==========
    @Select("SELECT " +
            "s.name AS name, " +
            "COUNT(*) AS value " +
            "FROM eval_object o " +
            "LEFT JOIN sys_status s ON o.status_id = s.status_id " +
            "WHERE o.deleted = 0 " +
            "GROUP BY o.status_id, s.name")
    List<EvalObjectOverviewVO.PieChartItem> selectStatusPieChart();

    // ========== 6. 不同区域对象数量对比（柱状图） ==========
    @Select("SELECT " +
            "a.area_name AS areaName, " +
            "COUNT(*) AS count " +
            "FROM eval_object o " +
            "LEFT JOIN sys_area a ON o.area_code = a.area_code " +
            "WHERE o.deleted = 0 " +
            "GROUP BY o.area_code, a.area_name " +
            "ORDER BY count DESC")
    List<EvalObjectOverviewVO.BarChartItem> selectAreaBarChart();
}