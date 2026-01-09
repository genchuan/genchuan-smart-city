package cn.iocoder.yudao.module.smartcity.service.inspectionandpatrolcategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionandpatrolcategory.vo.InspectionAndPatrolCategoryPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionandpatrolcategory.vo.InspectionAndPatrolCategorySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionandpatrolcategory.InspectionAndPatrolCategoryDO;
import jakarta.validation.Valid;

/**
 * 巡查巡检类 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface InspectionAndPatrolCategoryService {

    /**
     * 创建巡查巡检类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectionAndPatrolCategory(@Valid InspectionAndPatrolCategorySaveReqVO createReqVO);

    /**
     * 更新巡查巡检类
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectionAndPatrolCategory(@Valid InspectionAndPatrolCategorySaveReqVO updateReqVO);

    /**
     * 删除巡查巡检类
     *
     * @param id 编号
     */
    void deleteInspectionAndPatrolCategory(Long id);

    /**
     * 获得巡查巡检类
     *
     * @param id 编号
     * @return 巡查巡检类
     */
    InspectionAndPatrolCategoryDO getInspectionAndPatrolCategory(Long id);

    /**
     * 获得巡查巡检类分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检类分页
     */
    PageResult<InspectionAndPatrolCategoryDO> getInspectionAndPatrolCategoryPage(InspectionAndPatrolCategoryPageReqVO pageReqVO);

}