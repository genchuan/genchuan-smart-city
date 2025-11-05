package cn.iocoder.yudao.module.datacenter.service.emerplan.emerplanlib;

import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplanlib.vo.EmerPlanLibPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplanlib.vo.EmerPlanLibSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;


import cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerplanlib.EmerPlanLibDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.emerplan.emerplanlib.EmerPlanLibMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预案库 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EmerPlanLibServiceImpl implements EmerPlanLibService {

    @Resource
    private EmerPlanLibMapper emerPlanLibMapper;

    @Override
    public Long createEmerPlanLib(EmerPlanLibSaveReqVO createReqVO) {
        // 插入
        EmerPlanLibDO emerPlanLib = BeanUtils.toBean(createReqVO, EmerPlanLibDO.class);
        emerPlanLibMapper.insert(emerPlanLib);
        // 返回
        return emerPlanLib.getId();
    }

    @Override
    public void updateEmerPlanLib(EmerPlanLibSaveReqVO updateReqVO) {
        // 校验存在
        validateEmerPlanLibExists(updateReqVO.getId());
        // 更新
        EmerPlanLibDO updateObj = BeanUtils.toBean(updateReqVO, EmerPlanLibDO.class);
        emerPlanLibMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmerPlanLib(Long id) {
        // 校验存在
        validateEmerPlanLibExists(id);
        // 删除
        emerPlanLibMapper.deleteById(id);
    }

    private void validateEmerPlanLibExists(Long id) {
        if (emerPlanLibMapper.selectById(id) == null) {
            throw exception(EMER_PLAN_LIB_NOT_EXISTS);
        }
    }

    @Override
    public EmerPlanLibDO getEmerPlanLib(Long id) {
        return emerPlanLibMapper.selectById(id);
    }

    @Override
    public PageResult<EmerPlanLibDO> getEmerPlanLibPage(EmerPlanLibPageReqVO pageReqVO) {
        return emerPlanLibMapper.selectPage(pageReqVO);
    }

}