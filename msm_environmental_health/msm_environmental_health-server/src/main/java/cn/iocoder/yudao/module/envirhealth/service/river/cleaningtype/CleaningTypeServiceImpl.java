package cn.iocoder.yudao.module.envirhealth.service.river.cleaningtype;

import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.cleaningtype.CleaningTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.cleaningtype.CleaningTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.CleaningTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.river.CleaningTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 保洁类型字典表 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CleaningTypeServiceImpl implements CleaningTypeService {

    @Resource
    private CleaningTypeMapper cleaningTypeMapper;

    @Override
    public Long createCleaningType(CleaningTypeSaveReqVO createReqVO) {
        // 插入
        CleaningTypeDO cleaningType = BeanUtils.toBean(createReqVO, CleaningTypeDO.class);
        cleaningTypeMapper.insert(cleaningType);
        // 返回
        return cleaningType.getId();
    }

    @Override
    public void updateCleaningType(CleaningTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateCleaningTypeExists(updateReqVO.getId());
        // 更新
        CleaningTypeDO updateObj = BeanUtils.toBean(updateReqVO, CleaningTypeDO.class);
        cleaningTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteCleaningType(Long id) {
        // 校验存在
        validateCleaningTypeExists(id);
        // 删除
        cleaningTypeMapper.deleteById(id);
    }

    private void validateCleaningTypeExists(Long id) {
        if (cleaningTypeMapper.selectById(id) == null) {
            throw exception(CLEANING_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public CleaningTypeDO getCleaningType(Long id) {
        return cleaningTypeMapper.selectById(id);
    }

    @Override
    public PageResult<CleaningTypeDO> getCleaningTypePage(CleaningTypePageReqVO pageReqVO) {
        return cleaningTypeMapper.selectPage(pageReqVO);
    }

}