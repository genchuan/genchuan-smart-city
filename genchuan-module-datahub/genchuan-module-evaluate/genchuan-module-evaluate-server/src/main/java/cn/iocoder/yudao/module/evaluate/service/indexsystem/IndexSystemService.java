package cn.iocoder.yudao.module.evaluate.service.indexsystem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexsystem.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;

/**
 * 指标体系 Service 接口
 *
 * @author 亘川智城
 */
public interface IndexSystemService {

    /**
     * 创建指标体系
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIndexSystem(@Valid IndexSystemSaveReqVO createReqVO);

    /**
     * 更新指标体系
     *
     * @param updateReqVO 更新信息
     */
    void updateIndexSystem(@Valid IndexSystemSaveReqVO updateReqVO);

    /**
     * 删除指标体系
     *
     * @param id 编号
     */
    void deleteIndexSystem(Long id);

    /**
     * 获得指标体系
     *
     * @param id 编号
     * @return 指标体系
     */
    IndexSystemDO getIndexSystem(Long id);

    /**
     * 获得指标体系分页
     *
     * @param pageReqVO 分页查询
     * @return 指标体系分页
     */
    PageResult<IndexSystemDO> getIndexSystemPage(IndexSystemPageReqVO pageReqVO);

    PageResult<IndexSystemPageItemVO> getIndexSystemPageWithJoin(IndexSystemPageReqVO pageReqVO);

    @Transactional(rollbackFor = Exception.class)
    IndexSystemDetailVO getIndexSystemDetail(Long id);

    WeightCheckRespVO checkWeight(WeightCheckReqVO reqVO);

    //----------------------xin-------------------
    PageResult<IndexSystemRespVO> getIndexSystemJoinPage(IndexSystemPageReqVO reqVO);

    IndexSystemRespVO getStatusCount(Integer statusId);

    IndexSystemOverviewVO getOverview();

    /**
     * 刷新指定指标体系的分类总数、指标项总数，并返回最新的体系数据
     *
     * @param systemId 指标体系业务ID（eval_index_system.system_id）
     * @return 更新后的指标体系 DO（可能为 null，表示体系不存在）
     */
    IndexSystemDO refreshSystemCountsBySystemId(String systemId);

    /**
     * 根据分类业务ID刷新所属指标体系的分类总数、指标项总数
     *
     * @param categoryId 指标分类业务ID（eval_index_category.category_id）
     */
    void refreshSystemCountsByCategoryId(String categoryId);

    /**
     * 更新指标体系的变更日志
     * 自动追加新的变更记录，包含操作人、操作时间和操作类型
     *
     * @param systemId   指标体系业务ID
     * @param actionType 操作类型：CREATE/UPDATE/DELETE
     * @param detail     变更详情描述
     */
    void updateChangeLog(String systemId, String actionType, String detail);

    /**
     * 完整保存指标体系（含分类和指标项）
     * 支持新增和修改：
     * - systemId 有值：修改体系，同时处理分类和指标项
     * - systemId 无值：新增体系
     *
     * @param saveFullReqVO 完整保存信息
     * @return 体系ID
     */
    String saveFull(@Valid IndexSystemSaveFullReqVO saveFullReqVO);

    /**
     * 完整新增指标体系（含分类和指标项）
     *
     * @param createReqVO 完整创建信息
     * @return 体系ID
     */
    String createFull(IndexSystemSaveFullReqVO createReqVO);

    /**
     * 完整更新指标体系（含分类和指标项）
     *
     * @param updateReqVO 完整更新信息
     */
    void updateFull(IndexSystemSaveFullReqVO updateReqVO);

}