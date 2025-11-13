package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarncustomcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarncustomcfg.vo.EarlyWarnCustomCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarncustomcfg.vo.EarlyWarnCustomCfgSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarncustomcfg.EarlyWarnCustomCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarncustomcfg.EarlyWarnCustomCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警自定义统计配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnCustomCfgServiceImpl implements EarlyWarnCustomCfgService {

    @Resource
    private EarlyWarnCustomCfgMapper earlyWarnCustomCfgMapper;

    @Override
    public Long createEarlyWarnCustomCfg(EarlyWarnCustomCfgSaveReqVO createReqVO) {
        // 插入
        EarlyWarnCustomCfgDO earlyWarnCustomCfg = BeanUtils.toBean(createReqVO, EarlyWarnCustomCfgDO.class);
        earlyWarnCustomCfgMapper.insert(earlyWarnCustomCfg);
        // 返回
        return earlyWarnCustomCfg.getId();
    }

    @Override
    public void updateEarlyWarnCustomCfg(EarlyWarnCustomCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnCustomCfgExists(updateReqVO.getId());
        // 更新
        EarlyWarnCustomCfgDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnCustomCfgDO.class);
        earlyWarnCustomCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnCustomCfg(Long id) {
        // 校验存在
        validateEarlyWarnCustomCfgExists(id);
        // 删除
        earlyWarnCustomCfgMapper.deleteById(id);
    }

    private void validateEarlyWarnCustomCfgExists(Long id) {
        if (earlyWarnCustomCfgMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_CUSTOM_CFG_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnCustomCfgDO getEarlyWarnCustomCfg(Long id) {
        return earlyWarnCustomCfgMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnCustomCfgDO> getEarlyWarnCustomCfgPage(EarlyWarnCustomCfgPageReqVO pageReqVO) {
        return earlyWarnCustomCfgMapper.selectPage(pageReqVO);
    }

}