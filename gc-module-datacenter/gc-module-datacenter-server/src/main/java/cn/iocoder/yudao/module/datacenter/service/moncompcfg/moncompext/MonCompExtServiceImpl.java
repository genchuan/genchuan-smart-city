package cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompext;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompext.vo.MonCompExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompext.vo.MonCompExtSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompext.MonCompExtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.moncompcfg.moncompext.MonCompExtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测部件扩展配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonCompExtServiceImpl implements MonCompExtService {

    @Resource
    private MonCompExtMapper monCompExtMapper;

    @Override
    public Long createMonCompExt(MonCompExtSaveReqVO createReqVO) {
        // 插入
        MonCompExtDO monCompExt = BeanUtils.toBean(createReqVO, MonCompExtDO.class);
        monCompExtMapper.insert(monCompExt);
        // 返回
        return monCompExt.getId();
    }

    @Override
    public void updateMonCompExt(MonCompExtSaveReqVO updateReqVO) {
        // 校验存在
        validateMonCompExtExists(updateReqVO.getId());
        // 更新
        MonCompExtDO updateObj = BeanUtils.toBean(updateReqVO, MonCompExtDO.class);
        monCompExtMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonCompExt(Long id) {
        // 校验存在
        validateMonCompExtExists(id);
        // 删除
        monCompExtMapper.deleteById(id);
    }

    private void validateMonCompExtExists(Long id) {
        if (monCompExtMapper.selectById(id) == null) {
            throw exception(MON_COMP_EXT_NOT_EXISTS);
        }
    }

    @Override
    public MonCompExtDO getMonCompExt(Long id) {
        return monCompExtMapper.selectById(id);
    }

    @Override
    public PageResult<MonCompExtDO> getMonCompExtPage(MonCompExtPageReqVO pageReqVO) {
        return monCompExtMapper.selectPage(pageReqVO);
    }

}