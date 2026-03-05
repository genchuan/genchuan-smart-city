package cn.iocoder.yudao.module.evaluate.dal.mysql.tasktemplate;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo.TaskTemplatePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo.TaskTemplateRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.cycletype.CycleTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subject.SubjectDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.tasktemplate.TaskTemplateDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.user.UserDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Objects;

/**
 * 评价任务模板 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TaskTemplateMapper extends BaseMapperX<TaskTemplateDO> {

    default PageResult<TaskTemplateDO> selectPage(TaskTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TaskTemplateDO>()
                .eqIfPresent(TaskTemplateDO::getTemplateId, reqVO.getTemplateId())
                .likeIfPresent(TaskTemplateDO::getName, reqVO.getName())
                .eqIfPresent(TaskTemplateDO::getCode, reqVO.getCode())
                .eqIfPresent(TaskTemplateDO::getObjectTypeId, reqVO.getObjectTypeId())
                .eqIfPresent(TaskTemplateDO::getSystemId, reqVO.getSystemId())
                .eqIfPresent(TaskTemplateDO::getSubjectId, reqVO.getSubjectId())
                .eqIfPresent(TaskTemplateDO::getCycleTypeId, reqVO.getCycleTypeId())
                .eqIfPresent(TaskTemplateDO::getDescription, reqVO.getDescription())
                .eqIfPresent(TaskTemplateDO::getUseCount, reqVO.getUseCount())
                .betweenIfPresent(TaskTemplateDO::getLastUseTime, reqVO.getLastUseTime())
                .eqIfPresent(TaskTemplateDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(TaskTemplateDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(TaskTemplateDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(TaskTemplateDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(TaskTemplateDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TaskTemplateDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(TaskTemplateDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(TaskTemplateDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(TaskTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TaskTemplateDO::getId));
    }
    default PageResult<TaskTemplateRespVO> selectTaskTemplateJoinPage(TaskTemplatePageReqVO reqVO) {
        // 1. 构建分页对象（兼容空值，默认1页10条）
        Page<TaskTemplateRespVO> page = new Page<>(
                Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                Objects.requireNonNullElse(reqVO.getPageSize(), 10)
        );

        // 2. 构建联表查询条件（Lambda为主，自定义字段用字符串）
        MPJLambdaWrapper<TaskTemplateDO> wrapper = new MPJLambdaWrapper<TaskTemplateDO>()
                // ===== 1. 映射所有展示字段（Lambda方式）=====
                // 主表基础字段
                .select(TaskTemplateDO::getTemplateId)
                .selectAs(TaskTemplateDO::getName, TaskTemplateRespVO::getName)
                .selectAs(TaskTemplateDO::getCode, TaskTemplateRespVO::getCode)
                .selectAs(TaskTemplateDO::getDescription, TaskTemplateRespVO::getDescription)
                .select(TaskTemplateDO::getCreateTime)
                .select(TaskTemplateDO::getUseCount)
                .select(TaskTemplateDO::getLastUseTime)
                // 关联ID（用于前端钻取筛选，不展示但需要返回）
                .select(TaskTemplateDO::getObjectTypeId)
                .select(TaskTemplateDO::getSystemId)
                .select(TaskTemplateDO::getSubjectId)
                .select(TaskTemplateDO::getCycleTypeId)
                .select(TaskTemplateDO::getStatusId)

                // 关联表字段映射
                .selectAs(ObjectTypeDO::getName, TaskTemplateRespVO::getObjectTypeName) // 适用对象类型
                .selectAs(IndexSystemDO::getName, TaskTemplateRespVO::getIndexSystemName) // 关联指标体系
                .selectAs(SubjectDO::getName, TaskTemplateRespVO::getSubjectName) // 评价主体
                .selectAs(CycleTypeDO::getName, TaskTemplateRespVO::getCycleTypeName) // 任务周期
                .selectAs(StatusDO::getName, TaskTemplateRespVO::getStatusName) // 状态
                .selectAs(UserDO::getUserName, TaskTemplateRespVO::getCreateUserName) // 创建人

                // ===== 2. 联表关系（Lambda方式）=====
                .leftJoin(ObjectTypeDO.class, ObjectTypeDO::getTypeId, TaskTemplateDO::getObjectTypeId) // 对象类型
                .leftJoin(IndexSystemDO.class, IndexSystemDO::getSystemId, TaskTemplateDO::getSystemId) // 指标体系
                .leftJoin(SubjectDO.class, SubjectDO::getSubjectId, TaskTemplateDO::getSubjectId) // 评价主体
                .leftJoin(CycleTypeDO.class, CycleTypeDO::getTypeId, TaskTemplateDO::getCycleTypeId) // 周期类型
                .leftJoin(StatusDO.class, StatusDO::getStatusId, TaskTemplateDO::getStatusId) // 状态
                .leftJoin(UserDO.class, UserDO::getUserId, TaskTemplateDO::getCreateBy) // 创建人

                // ===== 3. 过滤条件（Lambda方式，支持钻取筛选）=====
                .eq(TaskTemplateDO::getDeleted, 0) // 主表删除标记
                // 动态筛选条件（适配钻取点击筛选）
                .like(StrUtil.isNotBlank(reqVO.getName()), TaskTemplateDO::getName, reqVO.getName())
                .eq(StrUtil.isNotBlank(reqVO.getCode()), TaskTemplateDO::getCode, reqVO.getCode())
                .eq(reqVO.getObjectTypeId() != null, TaskTemplateDO::getObjectTypeId, reqVO.getObjectTypeId())
                .eq(StrUtil.isNotBlank(reqVO.getSystemId()), TaskTemplateDO::getSystemId, reqVO.getSystemId())
                .eq(StrUtil.isNotBlank(reqVO.getSubjectId()), TaskTemplateDO::getSubjectId, reqVO.getSubjectId())
                .eq(reqVO.getCycleTypeId() != null, TaskTemplateDO::getCycleTypeId, reqVO.getCycleTypeId())
                .eq(reqVO.getStatusId() != null, TaskTemplateDO::getStatusId, reqVO.getStatusId())
                .eq(StrUtil.isNotBlank(reqVO.getCreateBy()), TaskTemplateDO::getCreateBy, reqVO.getCreateBy())

                // ===== 4. 排序 =====
                .orderByDesc(TaskTemplateDO::getId);

        // 3. 执行联表分页查询
        IPage<TaskTemplateRespVO> resultPage = selectJoinPage(page, TaskTemplateRespVO.class, wrapper);

        // 4. 转换为项目通用分页结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }
}