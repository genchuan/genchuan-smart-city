package cn.iocoder.yudao.module.evaluate.dal.mysql.subject;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo.SubjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo.SubjectRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subject.SubjectDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.subjecttype.SubjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.user.UserDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价主体 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface SubjectMapper extends BaseMapperX<SubjectDO> {

    default PageResult<SubjectDO> selectPage(SubjectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SubjectDO>()
                .eqIfPresent(SubjectDO::getSubjectId, reqVO.getSubjectId())
                .likeIfPresent(SubjectDO::getName, reqVO.getName())
                .eqIfPresent(SubjectDO::getCode, reqVO.getCode())
                .eqIfPresent(SubjectDO::getSubjectTypeId, reqVO.getSubjectTypeId())
                .eqIfPresent(SubjectDO::getContactId, reqVO.getContactId())
                .eqIfPresent(SubjectDO::getMemberCount, reqVO.getMemberCount())
                .eqIfPresent(SubjectDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(SubjectDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(SubjectDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(SubjectDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(SubjectDO::getChangeLog, reqVO.getChangeLog())
                .eqIfPresent(SubjectDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SubjectDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SubjectDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(SubjectDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(SubjectDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SubjectDO::getId));
    }
    // -------------------------- 新增联表查询方法 --------------------------
//    /**
//     * 联表查询分页数据（用于列表页展示，含关联表名称字段）
//     * @param page 分页参数
//     * @param queryWrapper 筛选条件
//     * @return 分页后的联表查询结果
//     */
//    Page<SubjectRespVO> selectSubjectPageWithJoin(Page<?> page, @Param("ew") LambdaQueryWrapperX<SubjectDO> queryWrapper);
//
//    /**
//     * 联表查询主体详情（含成员列表，仅人工主体返回成员）
//     * @param subjectId 评价主体UUID
//     * @return 完整联表详情
//     */
//    SubjectRespVO selectSubjectDetailWithJoin(@Param("subjectId") String subjectId);
//
//    /**
//     * 查询统计指标（总主体数、人工/系统主体数、启用数）
//     * @return 统计结果
//     */
//    SubjectStatRespVO selectSubjectStat();
//
//    List<SubjectRespVO.SubjectMemberRespVO> selectSubjectMemberList(String subjectId);
    default PageResult<SubjectRespVO> selectSubjectJoinPage(SubjectPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<SubjectRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());

        // 2. 构建查询条件
        MPJLambdaWrapper<SubjectDO> wrapper = new MPJLambdaWrapper<SubjectDO>()
                .selectAll(SubjectDO.class)
                // 关联主体类型表
                .selectAs(SubjectTypeDO::getName, SubjectRespVO::getSubjectTypeName)
                .leftJoin(SubjectTypeDO.class, SubjectTypeDO::getTypeId, SubjectDO::getSubjectTypeId)
                // 关联状态表
                .selectAs(StatusDO::getName, SubjectRespVO::getStatusName)
                .leftJoin(StatusDO.class, StatusDO::getStatusId, SubjectDO::getStatusId)
                // 关联联系人用户表
                .selectAs(UserDO::getUserName, SubjectRespVO::getContactName)
                .selectAs(UserDO::getUserPhone, SubjectRespVO::getContactPhone)
                .leftJoin(UserDO.class, UserDO::getUserId, SubjectDO::getContactId)
                // 关联创建人用户表
                .selectAs("creator", UserDO::getUserName, SubjectRespVO::getCreateByName)
                .leftJoin(UserDO.class, "creator", UserDO::getUserId, SubjectDO::getCreateBy)
                // 关联更新人用户表
                .selectAs("updater", UserDO::getUserName, SubjectRespVO::getUpdateByName)
                .leftJoin(UserDO.class, "updater", UserDO::getUserId, SubjectDO::getUpdateBy)
                // ==========================================
                // ========== 【核心新增】动态查询条件 ==========
                // ==========================================

                // 1. 主体名称（主表，模糊查询）
                .like(StrUtil.isNotBlank(reqVO.getName()), SubjectDO::getName, reqVO.getName())
                // 2. 主体编码（主表，模糊查询）
                .like(StrUtil.isNotBlank(reqVO.getCode()), SubjectDO::getCode, reqVO.getCode())
                // 3. 成员数量（主表，精确查询）
                .eq(reqVO.getMemberCount() != null, SubjectDO::getMemberCount, reqVO.getMemberCount())
                // 可选：成员数量范围查询
                // .between(reqVO.getMemberCountStart() != null && reqVO.getMemberCountEnd() != null,
                //         SubjectDO::getMemberCount, reqVO.getMemberCountStart(), reqVO.getMemberCountEnd())

                // 4. 主体类型名称（关联表，精确匹配，钻取筛选）
                .eq(StrUtil.isNotBlank(reqVO.getSubjectTypeName()), SubjectTypeDO::getName, reqVO.getSubjectTypeName())
                // 5. 状态名称（关联表，精确匹配，钻取筛选）
                .eq(StrUtil.isNotBlank(reqVO.getStatusName()), StatusDO::getName, reqVO.getStatusName())
                // 6. 联系人姓名（关联表，精确匹配）
                .eq(StrUtil.isNotBlank(reqVO.getContactName()), UserDO::getUserName, reqVO.getContactName())
                // 7. 联系电话（关联表，精确匹配）
                .eq(StrUtil.isNotBlank(reqVO.getContactPhone()), UserDO::getUserPhone, reqVO.getContactPhone())
// 排序
                .orderByDesc(SubjectDO::getCreateTime);

        // 3. 执行查询
        IPage<SubjectRespVO> resultPage = selectJoinPage(page, SubjectRespVO.class, wrapper);

        // 4. 后处理：变更日志截取前50字
        resultPage.getRecords().forEach(vo -> {
            if (vo.getChangeLog() != null) {
                vo.setChangeLogShort(vo.getChangeLog().length() > 50
                        ? vo.getChangeLog().substring(0, 50)
                        : vo.getChangeLog());
            }
        });

        // 5. 返回分页结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }
}