package cn.iocoder.yudao.module.smartcity.service.inspectionobject;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionobject.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionobject.InspectionObjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.inspectionobject.InspectionObjectMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 双随机行政检查 Service 实现类
 *
 * @author 朱聪权
 */
@Service
@Validated
public class InspectionObjectServiceImpl implements InspectionObjectService {

    @Resource
    private InspectionObjectMapper inspectionObjectMapper;

    @Override
    public Long createInspectionObject(InspectionObjectSaveReqVO createReqVO) {
        // 插入
        InspectionObjectDO inspectionObject = BeanUtils.toBean(createReqVO, InspectionObjectDO.class);
        inspectionObjectMapper.insert(inspectionObject);
        // 返回
        return inspectionObject.getId();
    }

    @Override
    public void updateInspectionObject(InspectionObjectSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectionObjectExists(updateReqVO.getId());
        // 更新
        InspectionObjectDO updateObj = BeanUtils.toBean(updateReqVO, InspectionObjectDO.class);
        inspectionObjectMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectionObject(Long id) {
        // 校验存在
        validateInspectionObjectExists(id);
        // 删除
        inspectionObjectMapper.deleteById(id);
    }

    private void validateInspectionObjectExists(Long id) {
        if (inspectionObjectMapper.selectById(id) == null) {
            throw exception(INSPECTION_OBJECT_NOT_EXISTS);
        }
    }

    @Override
    public InspectionObjectDO getInspectionObject(Long id) {
        return inspectionObjectMapper.selectById(id);
    }

    @Override
    public PageResult<InspectionObjectDO> getInspectionObjectPage(InspectionObjectPageReqVO pageReqVO) {
        return inspectionObjectMapper.selectPage(pageReqVO);
    }

}