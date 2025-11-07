package cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompdatacfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompdatacfg.vo.MonCompDataCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompdatacfg.vo.MonCompDataCfgSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompdatacfg.MonCompDataCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.moncompcfg.moncompdatacfg.MonCompDataCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测部件数据配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonCompDataCfgServiceImpl implements MonCompDataCfgService {

    @Resource
    private MonCompDataCfgMapper monCompDataCfgMapper;

    @Override
    public Long createMonCompDataCfg(MonCompDataCfgSaveReqVO createReqVO) {
        // 插入
        MonCompDataCfgDO monCompDataCfg = BeanUtils.toBean(createReqVO, MonCompDataCfgDO.class);
        monCompDataCfgMapper.insert(monCompDataCfg);
        // 返回
        return monCompDataCfg.getId();
    }

    @Override
    public void updateMonCompDataCfg(MonCompDataCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateMonCompDataCfgExists(updateReqVO.getId());
        // 更新
        MonCompDataCfgDO updateObj = BeanUtils.toBean(updateReqVO, MonCompDataCfgDO.class);
        monCompDataCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonCompDataCfg(Long id) {
        // 校验存在
        validateMonCompDataCfgExists(id);
        // 删除
        monCompDataCfgMapper.deleteById(id);
    }

    private void validateMonCompDataCfgExists(Long id) {
        if (monCompDataCfgMapper.selectById(id) == null) {
            throw exception(MON_COMP_DATA_CFG_NOT_EXISTS);
        }
    }

    @Override
    public MonCompDataCfgDO getMonCompDataCfg(Long id) {
        return monCompDataCfgMapper.selectById(id);
    }

    @Override
    public PageResult<MonCompDataCfgDO> getMonCompDataCfgPage(MonCompDataCfgPageReqVO pageReqVO) {
        return monCompDataCfgMapper.selectPage(pageReqVO);
    }

}