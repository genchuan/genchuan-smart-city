package cn.iocoder.yudao.module.evaluate.dal.mysql.indexsystem;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo.IndexSystemDetailVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo.IndexSystemPageItemVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo.IndexSystemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo.IndexSystemRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.user.UserDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 指标体系 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface IndexSystemMapper extends BaseMapperX<IndexSystemDO> {

    default PageResult<IndexSystemDO> selectPage(IndexSystemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IndexSystemDO>()
                .eqIfPresent(IndexSystemDO::getSystemId, reqVO.getSystemId())
                .likeIfPresent(IndexSystemDO::getName, reqVO.getName())
                .eqIfPresent(IndexSystemDO::getCode, reqVO.getCode())
                .eqIfPresent(IndexSystemDO::getObjectTypeId, reqVO.getObjectTypeId())
                .eqIfPresent(IndexSystemDO::getVersion, reqVO.getVersion())
                .eqIfPresent(IndexSystemDO::getDesc, reqVO.getDesc())
                .eqIfPresent(IndexSystemDO::getCategoryCount, reqVO.getCategoryCount())
                .eqIfPresent(IndexSystemDO::getItemCount, reqVO.getItemCount())
                .eqIfPresent(IndexSystemDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(IndexSystemDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(IndexSystemDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(IndexSystemDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(IndexSystemDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(IndexSystemDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(IndexSystemDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(IndexSystemDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(IndexSystemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IndexSystemDO::getId));
    }
    /**
     * 联表分页查询指标体系列表（带关联字典信息）
     */
    PageResult<IndexSystemPageItemVO> selectPageWithJoin(@Param("reqVO") IndexSystemPageReqVO reqVO);

    /**
     * 根据体系ID查询体系详情（基本信息）
     */
    IndexSystemDetailVO.BaseInfo selectDetailBaseInfo(@Param("systemId") String systemId);

    /**
     * 查询指定体系下的所有分类
     */
    List<IndexSystemDetailVO.CategoryVO> selectCategoriesBySystemId(@Param("systemId") String systemId);

    /**
     * 查询指定分类下的所有指标项（带字典信息）
     */
    List<IndexSystemDetailVO.IndexItemVO> selectItemsByCategoryIds(@Param("categoryIds") List<String> categoryIds);

    /**
     * 校验分类权重总和
     */
    Double selectCategoryWeightSum(@Param("systemId") String systemId);

    /**
     * 校验指标项权重总和
     */
    Double selectItemWeightSum(@Param("categoryId") String categoryId);
    default PageResult<IndexSystemRespVO> selectSystemJoinPage(IndexSystemPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<IndexSystemRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());

        // 2. 构建查询条件
        MPJLambdaWrapper<IndexSystemDO> wrapper = new MPJLambdaWrapper<IndexSystemDO>()
                .selectAll(IndexSystemDO.class)
                // 关联适用对象类型表
                .selectAs(ObjectTypeDO::getName, IndexSystemRespVO::getObjectTypeName)
                .leftJoin(ObjectTypeDO.class, ObjectTypeDO::getTypeId, IndexSystemDO::getObjectTypeId)
                // 关联状态表
                .selectAs(StatusDO::getName, IndexSystemRespVO::getStatusName)
                .leftJoin(StatusDO.class, StatusDO::getStatusId, IndexSystemDO::getStatusId)
                // 关联创建人用户表
                .selectAs("creator", UserDO::getUserName, IndexSystemRespVO::getCreateUserName)
                .leftJoin(UserDO.class, "creator", UserDO::getUserId, IndexSystemDO::getCreateBy)
                // 关联更新人用户表
                .selectAs("updater", UserDO::getUserName, IndexSystemRespVO::getUpdateUserName)
                .leftJoin(UserDO.class, "updater", UserDO::getUserId, IndexSystemDO::getUpdateBy)
                // 动态条件：体系本身字段
                .like(StrUtil.isNotBlank(reqVO.getName()), IndexSystemDO::getName, reqVO.getName())
                .eq(StrUtil.isNotBlank(reqVO.getCode()), IndexSystemDO::getCode, reqVO.getCode())
                .eq(reqVO.getObjectTypeId() != null, IndexSystemDO::getObjectTypeId, reqVO.getObjectTypeId())
                .eq(reqVO.getStatusId() != null, IndexSystemDO::getStatusId, reqVO.getStatusId())
                .eq(StrUtil.isNotBlank(reqVO.getVersion()), IndexSystemDO::getVersion, reqVO.getVersion());

        // 3. 分类名称筛选（使用 EXISTS 子查询）
        if (StrUtil.isNotBlank(reqVO.getCategoryName())) {
            wrapper.exists("SELECT 1 FROM eval_index_category c WHERE c.system_id = t.id AND c.name LIKE CONCAT('%', {0}, '%')",
                    reqVO.getCategoryName());
        }

        // 4. 指标项名称筛选（使用 EXISTS 子查询）
        if (StrUtil.isNotBlank(reqVO.getItemName())) {
            wrapper.exists("SELECT 1 FROM eval_index_item i WHERE i.system_id = t.id AND i.name LIKE CONCAT('%', {0}, '%')",
                    reqVO.getItemName());
        }

        // 5. 排序
        wrapper.orderByDesc(IndexSystemDO::getCreateTime);

        // 6. 执行查询
        IPage<IndexSystemRespVO> resultPage = selectJoinPage(page, IndexSystemRespVO.class, wrapper);

        // 7. 后处理：变更日志截取前50字
        resultPage.getRecords().forEach(vo -> {
            if (vo.getChangeLog() != null) {
                vo.setChangeLogShort(vo.getChangeLog().length() > 50
                        ? vo.getChangeLog().substring(0, 50)
                        : vo.getChangeLog());
            }
        });

        // 8. 返回分页结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }


}