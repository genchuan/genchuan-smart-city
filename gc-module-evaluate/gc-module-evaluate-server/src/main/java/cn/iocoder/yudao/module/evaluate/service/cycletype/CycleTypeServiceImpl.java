package cn.iocoder.yudao.module.evaluate.service.cycletype;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.cycletype.vo.CycleTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.cycletype.vo.CycleTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.cycletype.CycleTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.cycletype.CycleTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 周期类型字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CycleTypeServiceImpl implements CycleTypeService {

    @Resource
    private CycleTypeMapper cycleTypeMapper;

    @Override
    public Long createCycleType(CycleTypeSaveReqVO createReqVO) {
        // 插入
        CycleTypeDO cycleType = BeanUtils.toBean(createReqVO, CycleTypeDO.class);
        cycleTypeMapper.insert(cycleType);
        // 返回
        return cycleType.getId();
    }

    @Override
    public void updateCycleType(CycleTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateCycleTypeExists(updateReqVO.getId());
        // 更新
        CycleTypeDO updateObj = BeanUtils.toBean(updateReqVO, CycleTypeDO.class);
        cycleTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteCycleType(Long id) {
        // 校验存在
        validateCycleTypeExists(id);
        // 删除
        cycleTypeMapper.deleteById(id);
    }

    private void validateCycleTypeExists(Long id) {
        if (cycleTypeMapper.selectById(id) == null) {
            throw exception(CYCLE_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public CycleTypeDO getCycleType(Long id) {
        return cycleTypeMapper.selectById(id);
    }

    @Override
    public PageResult<CycleTypeDO> getCycleTypePage(CycleTypePageReqVO pageReqVO) {
        return cycleTypeMapper.selectPage(pageReqVO);
    }

}