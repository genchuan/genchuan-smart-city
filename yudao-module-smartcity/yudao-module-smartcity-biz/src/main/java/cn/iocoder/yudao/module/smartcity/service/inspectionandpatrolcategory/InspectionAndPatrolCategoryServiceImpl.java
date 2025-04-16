package cn.iocoder.yudao.module.smartcity.service.inspectionandpatrolcategory;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionandpatrolcategory.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionandpatrolcategory.InspectionAndPatrolCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.inspectionandpatrolcategory.InspectionAndPatrolCategoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检类 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class InspectionAndPatrolCategoryServiceImpl implements InspectionAndPatrolCategoryService {

    @Resource
    private InspectionAndPatrolCategoryMapper inspectionAndPatrolCategoryMapper;

    @Override
    public Long createInspectionAndPatrolCategory(InspectionAndPatrolCategorySaveReqVO createReqVO) {
        // 插入
        InspectionAndPatrolCategoryDO inspectionAndPatrolCategory = BeanUtils.toBean(createReqVO, InspectionAndPatrolCategoryDO.class);
        inspectionAndPatrolCategoryMapper.insert(inspectionAndPatrolCategory);
        // 返回
        return inspectionAndPatrolCategory.getId();
    }

    @Override
    public void updateInspectionAndPatrolCategory(InspectionAndPatrolCategorySaveReqVO updateReqVO) {
        // 校验存在
        validateInspectionAndPatrolCategoryExists(updateReqVO.getId());
        // 更新
        InspectionAndPatrolCategoryDO updateObj = BeanUtils.toBean(updateReqVO, InspectionAndPatrolCategoryDO.class);
        inspectionAndPatrolCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectionAndPatrolCategory(Long id) {
        // 校验存在
        validateInspectionAndPatrolCategoryExists(id);
        // 删除
        inspectionAndPatrolCategoryMapper.deleteById(id);
    }

    private void validateInspectionAndPatrolCategoryExists(Long id) {
        if (inspectionAndPatrolCategoryMapper.selectById(id) == null) {
            throw exception(INSPECTION_AND_PATROL_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public InspectionAndPatrolCategoryDO getInspectionAndPatrolCategory(Long id) {
        return inspectionAndPatrolCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<InspectionAndPatrolCategoryDO> getInspectionAndPatrolCategoryPage(InspectionAndPatrolCategoryPageReqVO pageReqVO) {
        return inspectionAndPatrolCategoryMapper.selectPage(pageReqVO);
    }

}