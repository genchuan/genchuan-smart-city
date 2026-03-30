package cn.iocoder.yudao.module.evaluate.dal.mysql.standardcategory;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo.StandardCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo.StandardCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.standardcategory.StandardCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.standarditem.StandardItemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.user.UserDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Objects;

/**
 * 标准分类 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface StandardCategoryMapper extends BaseMapperX<StandardCategoryDO> {

    default PageResult<StandardCategoryDO> selectPage(StandardCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StandardCategoryDO>()
                .eqIfPresent(StandardCategoryDO::getStandardCategoryId, reqVO.getStandardCategoryId())
                .likeIfPresent(StandardCategoryDO::getName, reqVO.getName())
                .eqIfPresent(StandardCategoryDO::getSystemId, reqVO.getSystemId())
                .eqIfPresent(StandardCategoryDO::getItemCount, reqVO.getItemCount())
                .eqIfPresent(StandardCategoryDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(StandardCategoryDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(StandardCategoryDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(StandardCategoryDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(StandardCategoryDO::getChangeLog, reqVO.getChangeLog())
                .eqIfPresent(StandardCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(StandardCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(StandardCategoryDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(StandardCategoryDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(StandardCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StandardCategoryDO::getId));
    }

    /**
     * 标准分类联表分页查询（完整映射所有展示字段，支持钻取）
     * @param reqVO 分页+筛选参数
     * @return 分页结果
     */
        default PageResult<StandardCategoryRespVO> selectStandardCategoryJoinPage(StandardCategoryPageReqVO reqVO) {
            // 1. 构建分页对象（兼容空值，默认1页10条）
            Page<StandardCategoryRespVO> page = new Page<>(
                    Objects.requireNonNullElse(reqVO.getPageNo(), 1),
                    Objects.requireNonNullElse(reqVO.getPageSize(), 10)
            );

            // 2. 构建联表查询条件（纯Lambda风格，移除所有tenant_id相关逻辑）
            MPJLambdaWrapper<StandardCategoryDO> wrapper = new MPJLambdaWrapper<StandardCategoryDO>()
                    // ===== 1. 映射所有展示字段（Lambda方式，替代字符串）=====
                    .select(StandardCategoryDO::getId)
                    .select(StandardCategoryDO::getSystemId)
                    .select(StandardCategoryDO::getStatusId)
                    .select(StandardCategoryDO::getCreateTime)
                    .selectAs(StandardCategoryDO::getItemCount, StandardCategoryRespVO::getStandardItemCount)
                    .selectAs(StandardCategoryDO::getName, StandardCategoryRespVO::getStandardCategoryName)
                    // 自定义字段：变更日志前50字
                    .select("IFNULL(SUBSTRING(t.change_log, 1, 50), '') AS changeLogShort")
                    // 关联表字段映射
                    .selectAs(IndexSystemDO::getName, StandardCategoryRespVO::getIndexSystemName)
                    .selectAs(StandardItemDO::getGrade, StandardCategoryRespVO::getStandardItemGrade)
                    .selectAs(StandardItemDO::getScoreRange, StandardCategoryRespVO::getScoreRange)
                    .selectAs(StandardItemDO::getSortNo, StandardCategoryRespVO::getSortNo)
                    .selectAs(StatusDO::getName, StandardCategoryRespVO::getStatusName)
                    .selectAs(UserDO::getUserName, StandardCategoryRespVO::getCreateUserName)

                    // ===== 2. 联表关系（Lambda方式，替代字符串ON条件）=====
                    .leftJoin(IndexSystemDO.class, IndexSystemDO::getSystemId, StandardCategoryDO::getSystemId)
                    .leftJoin(StandardItemDO.class, StandardItemDO::getStandardCategoryId, StandardCategoryDO::getStandardCategoryId)
                    .leftJoin(StatusDO.class, StatusDO::getStatusId, StandardCategoryDO::getStatusId)
                    .leftJoin(UserDO.class, UserDO::getUserId, StandardCategoryDO::getCreateBy)

                    // ===== 3. 过滤条件（Lambda方式，仅保留主表删除标记 + 动态筛选）=====
                    .eq(StandardCategoryDO::getDeleted, 0) // 主表删除标记
                    // 动态筛选条件
                    .like(StrUtil.isNotBlank(reqVO.getName()), StandardCategoryDO::getName, reqVO.getName())
                    .eq(StrUtil.isNotBlank(reqVO.getSystemId()), StandardCategoryDO::getSystemId, reqVO.getSystemId())
                    .eq(reqVO.getStatusId() != null, StandardCategoryDO::getStatusId, reqVO.getStatusId())

                    // ===== 4. 排序（Lambda方式）=====
                    .orderByDesc(StandardCategoryDO::getId);

            // 3. 执行联表分页查询
            IPage<StandardCategoryRespVO> resultPage = selectJoinPage(page, StandardCategoryRespVO.class, wrapper);

            // 4. 转换为项目通用分页结果
            return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
        }
}