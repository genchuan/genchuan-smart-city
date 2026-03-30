package cn.iocoder.yudao.module.waterdetection.service.invaliddata;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.invaliddata.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.invaliddata.InvalidDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.invaliddata.InvalidDataMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 不合格数据处理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InvalidDataServiceImpl implements InvalidDataService {

    @Resource
    private InvalidDataMapper invalidDataMapper;

    @Override
    public Long createInvalidData(InvalidDataSaveReqVO createReqVO) {
        // 插入
        InvalidDataDO invalidData = BeanUtils.toBean(createReqVO, InvalidDataDO.class);
        invalidDataMapper.insert(invalidData);
        // 返回
        return invalidData.getId();
    }

    @Override
    public void updateInvalidData(InvalidDataSaveReqVO updateReqVO) {
        // 校验存在
        validateInvalidDataExists(updateReqVO.getId());
        // 更新
        InvalidDataDO updateObj = BeanUtils.toBean(updateReqVO, InvalidDataDO.class);
        invalidDataMapper.updateById(updateObj);
    }

    @Override
    public void deleteInvalidData(Long id) {
        // 校验存在
        validateInvalidDataExists(id);
        // 删除
        invalidDataMapper.deleteById(id);
    }

    private void validateInvalidDataExists(Long id) {
        if (invalidDataMapper.selectById(id) == null) {
            throw exception(INVALID_DATA_NOT_EXISTS);
        }
    }

    @Override
    public InvalidDataDO getInvalidData(Long id) {
        return invalidDataMapper.selectById(id);
    }

    @Override
    public PageResult<InvalidDataDO> getInvalidDataPage(InvalidDataPageReqVO pageReqVO) {
        return invalidDataMapper.selectPage(pageReqVO);
    }

}