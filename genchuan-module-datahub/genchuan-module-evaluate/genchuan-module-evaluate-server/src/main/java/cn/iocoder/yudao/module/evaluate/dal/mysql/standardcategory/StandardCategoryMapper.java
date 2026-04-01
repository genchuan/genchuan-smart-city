package cn.iocoder.yudao.module.evaluate.dal.mysql.standardcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.standardcategory.vo.StandardCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.standardcategory.vo.StandardCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.standardcategory.StandardCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
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
                .likeIfPresent(StandardCategoryDO::getName, reqVO.getName())
                .eqIfPresent(StandardCategoryDO::getSystemId, reqVO.getSystemId())
                .eqIfPresent(StandardCategoryDO::getItemCount, reqVO.getItemCount())
                .eqIfPresent(StandardCategoryDO::getStatusId, reqVO.getStatusId())
                .betweenIfPresent(StandardCategoryDO::getLastUseTime, reqVO.getLastUseTime())
                .eqIfPresent(StandardCategoryDO::getUseCount, reqVO.getUseCount())
                .betweenIfPresent(StandardCategoryDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(StandardCategoryDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(StandardCategoryDO::getChangeLog, reqVO.getChangeLog())
                .eqIfPresent(StandardCategoryDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(StandardCategoryDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(StandardCategoryDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(StandardCategoryDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(StandardCategoryDO::getCreator, reqVO.getCreator())
                .eqIfPresent(StandardCategoryDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(StandardCategoryDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(StandardCategoryDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(StandardCategoryDO::getId));
    }

    /**
     * 联表分页查询（使用 XML 实现），关联指标体系、状态、创建人、更新人表
     */
    IPage<StandardCategoryRespVO> selectJoinPage(Page<StandardCategoryRespVO> page, @Param("reqVO") StandardCategoryPageReqVO reqVO);

    // ==================== 统计查询 ====================

    /**
     * 统计标准分类总数
     */
    @Select("SELECT COUNT(*) AS totalCategoryCount FROM eval_standard_category WHERE deleted = 0")
    Map<String, Object> selectTotalCategoryCount();

    /**
     * 统计标准项总数
     */
    @Select("SELECT COUNT(*) AS totalItemCount FROM eval_standard_item WHERE deleted = 0")
    Map<String, Object> selectTotalItemCount();

    /**
     * 统计启用状态的标准分类数（statusId = 1）
     */
    @Select("SELECT COUNT(*) AS enabledCategoryCount FROM eval_standard_category WHERE deleted = 0 AND status_id = 1")
    Map<String, Object> selectEnabledCategoryCount();

    /**
     * 按状态分组统计标准分类数量
     */
    @Select("SELECT status_id, COUNT(*) AS categoryCount FROM eval_standard_category WHERE deleted = 0 GROUP BY status_id")
    List<Map<String, Object>> selectStatusGroupCount();

    /**
     * 按指标体系分组统计标准分类数量
     */
    @Select("SELECT system_id, COUNT(*) AS categoryCount FROM eval_standard_category WHERE deleted = 0 GROUP BY system_id")
    List<Map<String, Object>> selectSystemGroupCount();

    /**
     * 按等级分组统计标准项数量
     */
    @Select("SELECT grade, COUNT(*) AS itemCount FROM eval_standard_item WHERE deleted = 0 AND grade IS NOT NULL GROUP BY grade")
    List<Map<String, Object>> selectGradeGroupCount();

    /**
     * 按标准分类ID统计标准项数量
     */
    @Select("SELECT standard_category_id, COUNT(*) AS itemCount FROM eval_standard_item WHERE deleted = 0 GROUP BY standard_category_id")
    List<Map<String, Object>> selectCategoryItemCount();

    /**
     * 批量查询指标体系名称
     */
    @Select("<script>" +
            "SELECT id, name FROM eval_index_system WHERE deleted = 0 AND id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<IndexSystemDO> selectSystemByIds(@Param("ids") List<Long> ids);

    /**
     * 批量查询状态名称
     */
    @Select("<script>" +
            "SELECT id, name FROM sys_status WHERE deleted = 0 AND id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<StatusDO> selectStatusByIds(@Param("ids") List<Long> ids);

}