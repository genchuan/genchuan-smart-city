package cn.iocoder.yudao.module.evaluate.dal.mysql.object;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
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
    List<ObjectRespVO> selectJoinPage(@Param("reqVo") ObjectPageReqVO reqVo);

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
    default PageResult<ObjectRespVO> selectAllObjectJoinPage(PageParam reqVO) { // 参数名必须是 reqVO
        // 1. 构建分页对象（模仿参考代码的 Objects.requireNonNullElse）
        Page<ObjectRespVO> page = new Page<>(
                Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                Objects.requireNonNullElse(reqVO.getPageSize(), 10)
        );

        // 2. 【关键3】使用 MPJLambdaWrapper (不带 X)
        MPJLambdaWrapper<ObjectDO> wrapper = new MPJLambdaWrapper<ObjectDO>()
                // ===== 主表字段 =====
                .selectAll(ObjectDO.class)

                // ===== 关联表字段（完全模仿 selectAs 写法）=====
                .selectAs(AreaDO::getAreaName, ObjectRespVO::getAreaName)
                .selectAs(ObjectTypeDO::getName, ObjectRespVO::getObjectTypeName)
                .selectAs(StatusDO::getName, ObjectRespVO::getStatusName)
                .selectAs(UserDO::getUserName, ObjectRespVO::getManagerName)
                .selectAs(UserDO::getUserPhone, ObjectRespVO::getManagerPhone)
                // 创建人（别名 u1）
                .selectAs("u1", UserDO::getUserName, ObjectRespVO::getCreateUserName)
                // 更新人（别名 u2）
                .selectAs("u2", UserDO::getUserName, ObjectRespVO::getUpdateUserName)
                .selectAs(RelatedObjectDO::getRelatedName, ObjectRespVO::getRelatedName)

                // ===== 联表关系（模仿参考代码）=====
                .leftJoin(AreaDO.class, AreaDO::getAreaCode, ObjectDO::getAreaCode)
                .leftJoin(ObjectTypeDO.class, ObjectTypeDO::getTypeId, ObjectDO::getObjectTypeId)
                .leftJoin(StatusDO.class, StatusDO::getStatusId, ObjectDO::getStatusId)
                .leftJoin(UserDO.class, UserDO::getUserId, ObjectDO::getManagerId)
                // 创建人关联（别名 u1）
                .leftJoin(UserDO.class, "u1", UserDO::getUserId, ObjectDO::getCreateBy)
                // 更新人关联（别名 u2）
                .leftJoin(UserDO.class, "u2", UserDO::getUserId, ObjectDO::getUpdateBy)
                .leftJoin(RelatedObjectDO.class, RelatedObjectDO::getRelatedId, ObjectDO::getRelatedId)

//                // ===== 过滤条件（模仿参考代码的 like/eq）=====
//                .like(StrUtil.isNotBlank(reqVO.getKeyword()), ObjectDO::getName, reqVO.getKeyword())
//                .like(StrUtil.isNotBlank(reqVO.getKeyword()), ObjectDO::getCode, reqVO.getKeyword())

                // ===== 排序 =====
                .orderByDesc(ObjectDO::getCreateTime);

        // 3. 执行查询（模仿参考代码，用 IPage 接收）
        IPage<ObjectRespVO> resultPage = selectJoinPage(page, ObjectRespVO.class, wrapper);

        // 4. 处理变更日志截取（模仿参考代码的后置处理）
        resultPage.getRecords().forEach(vo -> {
            if (vo.getChangeLog() != null) {
                vo.setChangeLogShort(vo.getChangeLog().length() > 50
                        ? vo.getChangeLog().substring(0, 50)
                        : vo.getChangeLog());
            }
        });

        // 5. 返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }
}