package cn.iocoder.yudao.module.evaluate.dal.mysql.rulecategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.rulecategory.RuleCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.vetoitem.VetoItemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.sys.RuleTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.user.UserDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 规则分类 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RuleCategoryMapper extends BaseMapperX<RuleCategoryDO> {

    default PageResult<RuleCategoryDO> selectPage(RuleCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RuleCategoryDO>()
                .eqIfPresent(RuleCategoryDO::getRuleCategoryId, reqVO.getRuleCategoryId())
                .likeIfPresent(RuleCategoryDO::getName, reqVO.getName())
                .eqIfPresent(RuleCategoryDO::getSystemId, reqVO.getSystemId())
                .eqIfPresent(RuleCategoryDO::getItemCount, reqVO.getItemCount())
                .eqIfPresent(RuleCategoryDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(RuleCategoryDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(RuleCategoryDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(RuleCategoryDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(RuleCategoryDO::getChangeLog, reqVO.getChangeLog())
                .eqIfPresent(RuleCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RuleCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RuleCategoryDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RuleCategoryDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RuleCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RuleCategoryDO::getId));
    }

    IPage<RuleCategoryRespVO> selectRuleCategoryPage(IPage<?> page, @Param("query") RuleCategoryPageReqVO reqVO);
/**
 *     default List<AdminUserDetailDO> selectList2ByStatusAndDeptName(Integer status, String deptName) {
 *         return selectJoinList(AdminUserDetailDO.class, new MPJLambdaWrapper<AdminUserDO>() // 查询 List
 *                 .selectAll(AdminUserDO.class) // 查询 system_users 表的 all 所有字段
 *                 .selectAs(DeptDO::getName, AdminUserDetailDO::getDeptName) // 查询 system_dept 表的 name 字段，使用 deptName 字段“部分”返回
 *                 .eq(AdminUserDO::getStatus, status) // WHERE system_users.status = ? 【部门名为 `芋道源码`】
 *                 .leftJoin(DeptDO.class, DeptDO::getId, AdminUserDO::getDeptId) // 联表 WHERE system_users.dept_id = system_dept.id
 *                 .eq(DeptDO::getName, deptName) // WHERE system_dept.name = ? 【用户状态为开启】
 *         );
 *     }
 */
    /**
     * 规则分类分页查询（支持全部/启用/停用状态，联表查询所有字段）
     *
     * @param page 分页参数
     * @param reqVO 查询条件
     * @return 分页结果
     */
    default IPage<RuleCategoryRespVO> selectRuleCategoryPage(Page<RuleCategoryRespVO> page, @Param("reqVO") RuleCategoryPageReqVO reqVO) {
        // 构建 MPJ 联表查询条件
        MPJLambdaWrapper<RuleCategoryDO> wrapper = new MPJLambdaWrapper<RuleCategoryDO>()
                // ========== 1. 主表：规则分类表（eval_rule_category） ==========
                .selectAll(RuleCategoryDO.class) // 查询主表所有字段
                // ========== 2. 关联指标体系表（eval_index_system） ==========
                .leftJoin(IndexSystemDO.class, IndexSystemDO::getSystemId, RuleCategoryDO::getSystemId)
                .selectAs(IndexSystemDO::getName, RuleCategoryRespVO::getIndexSystemName) // 适用指标体系名称
                // ========== 3. 关联规则项表（eval_rule_item） ==========
                .leftJoin(RuleItemDO.class, RuleItemDO::getRuleCategoryId, RuleCategoryDO::getRuleCategoryId)
                .selectAs(RuleItemDO::getName, RuleCategoryRespVO::getRuleItemName) // 规则项名称
                .selectAs(RuleItemDO::getScoreLogic, RuleCategoryRespVO::getScoreLogic) // 评分逻辑
                .selectAs(RuleItemDO::getFullScore, RuleCategoryRespVO::getFullScore) // 满分值
                // ========== 4. 关联指标项表（eval_index_item） ==========
                .leftJoin(IndexItemDO.class, IndexItemDO::getItemId, RuleItemDO::getIndexId)
                .selectAs(IndexItemDO::getName, RuleCategoryRespVO::getIndexItemName) // 关联指标项名称
                // ========== 5. 关联规则类型字典表（sys_rule_type） ==========
                .leftJoin(RuleTypeDO.class, RuleTypeDO::getTypeId, RuleItemDO::getRuleTypeId)
                .selectAs(RuleTypeDO::getName, RuleCategoryRespVO::getRuleTypeName) // 规则类型名称
                // ========== 6. 关联否决项表（eval_veto_item） ==========
                .leftJoin(VetoItemDO.class, VetoItemDO::getVetoItemId, RuleCategoryDO::getVetoItemId)
                .selectAs(VetoItemDO::getName, RuleCategoryRespVO::getVetoItemName) // 否决项名称
                .selectAs(VetoItemDO::getCondition, RuleCategoryRespVO::getVetoCondition) // 否决条件
                .selectAs(VetoItemDO::getValidCycle, RuleCategoryRespVO::getValidCycle) // 生效周期
                // ========== 7. 关联对象类型字典表（sys_object_type） ==========
                .leftJoin(ObjectTypeDO.class, ObjectTypeDO::getTypeId, VetoItemDO::getObjectTypeId)
                .selectAs(ObjectTypeDO::getName, RuleCategoryRespVO::getObjectTypeName) // 适用对象类型名称
                // ========== 8. 关联状态字典表（sys_status） ==========
                .leftJoin(StatusDO.class, StatusDO::getId, RuleCategoryDO::getStatusId)
                .selectAs(StatusDO::getName, RuleCategoryRespVO::getStatusName) // 状态名称
                // ========== 9. 关联创建人用户表（sys_user） ==========
                .leftJoin(UserDO.class, UserDO::getId, RuleCategoryDO::getCreateBy)
                .selectAs(UserDO::getUserName, RuleCategoryRespVO::getCreateUserName) // 创建人名称
                // ========== 10. 关联停用操作人用户表（sys_user - 别名） ==========
                .leftJoin(UserDO.class, "u2", UserDO::getId, RuleCategoryDO::getUpdateBy)
                .selectAs("u2.user_name", RuleCategoryRespVO::getStopUserName) // 停用操作人名称
                // ========== 自定义字段（统计/截取） ==========
                .selectAs(RuleCategoryDO::getItemCount, RuleCategoryRespVO::getRuleItemCount) // 规则项数量
                .selectAs("COUNT(DISTINCT eval_veto_item.id)", RuleCategoryRespVO::getVetoItemCount) // 否决项数量（统计）
                .selectAs("SUBSTRING(eval_rule_category.change_log, 1, 50)", RuleCategoryRespVO::getShortChangeLog) // 截取变更日志前50字
                .selectAs(RuleCategoryDO::getUpdateTime, RuleCategoryRespVO::getStopTime) // 停用时间（复用update_time）

                // ========== 查询条件 ==========
                // 1. 规则分类ID
                .eq(reqVO.getRuleCategoryId() != null, RuleCategoryDO::getRuleCategoryId, reqVO.getRuleCategoryId())
                // 2. 规则分类名称（模糊查询）
                .like(reqVO.getName() != null, RuleCategoryDO::getName, reqVO.getName())
                // 3. 适用指标体系ID
                .eq(reqVO.getSystemId() != null, RuleCategoryDO::getSystemId, reqVO.getSystemId())
                // 4. 状态筛选（核心：全部/启用/停用）
                .eq(reqVO.getStatusId() != null, RuleCategoryDO::getStatusId, reqVO.getStatusId())
                // 5. 规则项数量
                .eq(reqVO.getItemCount() != null, RuleCategoryDO::getItemCount, reqVO.getItemCount())
                // 6. 最近使用时间范围
                .between(reqVO.getLastUseTime() != null && reqVO.getLastUseTime().length == 2,
                        RuleCategoryDO::getLastUseTime,
                        reqVO.getLastUseTime()[0], reqVO.getLastUseTime()[1])
                // 7. 使用次数
                .eq(reqVO.getUseCount() != null, RuleCategoryDO::getUseCount, reqVO.getUseCount())
                // 8. 创建人ID
                .eq(reqVO.getCreateBy() != null, RuleCategoryDO::getCreateBy, reqVO.getCreateBy())
                // 9. 更新人ID
                .eq(reqVO.getUpdateBy() != null, RuleCategoryDO::getUpdateBy, reqVO.getUpdateBy())
                // 10. 创建时间（业务字段）范围
                .between(reqVO.getBizCreateTime() != null && reqVO.getBizCreateTime().length == 2,
                        RuleCategoryDO::getBizCreateTime,
                        reqVO.getBizCreateTime()[0], reqVO.getBizCreateTime()[1])
                // 11. 更新时间（业务字段）范围
                .between(reqVO.getBizUpdateTime() != null && reqVO.getBizUpdateTime().length == 2,
                        RuleCategoryDO::getBizUpdateTime,
                        reqVO.getBizUpdateTime()[0], reqVO.getBizUpdateTime()[1])
                // 12. 变更日志（模糊查询）
                .like(reqVO.getChangeLog() != null, RuleCategoryDO::getChangeLog, reqVO.getChangeLog())
                // 13. 创建时间范围
                .between(reqVO.getCreateTime() != null && reqVO.getCreateTime().length == 2,
                        RuleCategoryDO::getCreateTime,
                        reqVO.getCreateTime()[0], reqVO.getCreateTime()[1])
                // 14. 状态名称（模糊查询）
                .like(reqVO.getStatusName() != null, StatusDO::getName, reqVO.getStatusName())

                // ========== 分组（避免联表后数据重复） ==========
                .groupBy(RuleCategoryDO::getId);

        // 执行分页查询
        return selectJoinPage(page, RuleCategoryRespVO.class, wrapper);
    }
}