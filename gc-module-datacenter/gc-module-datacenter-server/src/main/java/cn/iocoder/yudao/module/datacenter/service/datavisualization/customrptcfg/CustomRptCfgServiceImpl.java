package cn.iocoder.yudao.module.datacenter.service.datavisualization.customrptcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.customrptcfg.vo.CustomRptCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.customrptcfg.vo.CustomRptCfgSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.datavisualization.customrptcfg.CustomRptCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.datavisualization.customrptcfg.CustomRptCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 自定义报表配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CustomRptCfgServiceImpl implements CustomRptCfgService {

    @Resource
    private CustomRptCfgMapper customRptCfgMapper;

    @Override
    public Long createCustomRptCfg(CustomRptCfgSaveReqVO createReqVO) {
        // 插入
        CustomRptCfgDO customRptCfg = BeanUtils.toBean(createReqVO, CustomRptCfgDO.class);
        customRptCfgMapper.insert(customRptCfg);
        // 返回
        return customRptCfg.getId();
    }

    @Override
    public void updateCustomRptCfg(CustomRptCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateCustomRptCfgExists(updateReqVO.getId());
        // 更新
        CustomRptCfgDO updateObj = BeanUtils.toBean(updateReqVO, CustomRptCfgDO.class);
        customRptCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomRptCfg(Long id) {
        // 校验存在
        validateCustomRptCfgExists(id);
        // 删除
        customRptCfgMapper.deleteById(id);
    }

    private void validateCustomRptCfgExists(Long id) {
        if (customRptCfgMapper.selectById(id) == null) {
            throw exception(CUSTOM_RPT_CFG_NOT_EXISTS);
        }
    }

    @Override
    public CustomRptCfgDO getCustomRptCfg(Long id) {
        return customRptCfgMapper.selectById(id);
    }

    @Override
    public PageResult<CustomRptCfgDO> getCustomRptCfgPage(CustomRptCfgPageReqVO pageReqVO) {
        return customRptCfgMapper.selectPage(pageReqVO);
    }

}