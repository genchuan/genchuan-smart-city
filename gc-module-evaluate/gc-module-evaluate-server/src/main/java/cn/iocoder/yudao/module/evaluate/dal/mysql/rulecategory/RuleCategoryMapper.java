package cn.iocoder.yudao.module.evaluate.dal.mysql.rulecategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.rulecategory.RuleCategoryDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

}