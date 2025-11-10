package cn.iocoder.yudao.module.datacenter.service.coopcfg.coopflowcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopflowcfg.vo.CoopFlowCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopflowcfg.vo.CoopFlowCfgSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopflowcfg.CoopFlowCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.coopcfg.coopflowcfg.CoopFlowCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 联动流程配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CoopFlowCfgServiceImpl implements CoopFlowCfgService {

    @Resource
    private CoopFlowCfgMapper coopFlowCfgMapper;

    @Override
    public Long createCoopFlowCfg(CoopFlowCfgSaveReqVO createReqVO) {
        // 插入
        CoopFlowCfgDO coopFlowCfg = BeanUtils.toBean(createReqVO, CoopFlowCfgDO.class);
        coopFlowCfgMapper.insert(coopFlowCfg);
        // 返回
        return coopFlowCfg.getId();
    }

    @Override
    public void updateCoopFlowCfg(CoopFlowCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateCoopFlowCfgExists(updateReqVO.getId());
        // 更新
        CoopFlowCfgDO updateObj = BeanUtils.toBean(updateReqVO, CoopFlowCfgDO.class);
        coopFlowCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteCoopFlowCfg(Long id) {
        // 校验存在
        validateCoopFlowCfgExists(id);
        // 删除
        coopFlowCfgMapper.deleteById(id);
    }

    private void validateCoopFlowCfgExists(Long id) {
        if (coopFlowCfgMapper.selectById(id) == null) {
            throw exception(COOP_FLOW_CFG_NOT_EXISTS);
        }
    }

    @Override
    public CoopFlowCfgDO getCoopFlowCfg(Long id) {
        return coopFlowCfgMapper.selectById(id);
    }

    @Override
    public PageResult<CoopFlowCfgDO> getCoopFlowCfgPage(CoopFlowCfgPageReqVO pageReqVO) {
        return coopFlowCfgMapper.selectPage(pageReqVO);
    }

}