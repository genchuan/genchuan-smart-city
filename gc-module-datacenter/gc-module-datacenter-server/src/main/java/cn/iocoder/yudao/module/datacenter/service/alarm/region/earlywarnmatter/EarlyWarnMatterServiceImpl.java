package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmatter.vo.EarlyWarnMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmatter.vo.EarlyWarnMatterSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmatter.EarlyWarnMatterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarnmatter.EarlyWarnMatterMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 按管理事项预警告警统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnMatterServiceImpl implements EarlyWarnMatterService {

    @Resource
    private EarlyWarnMatterMapper earlyWarnMatterMapper;

    @Override
    public Long createEarlyWarnMatter(EarlyWarnMatterSaveReqVO createReqVO) {
        // 插入
        EarlyWarnMatterDO earlyWarnMatter = BeanUtils.toBean(createReqVO, EarlyWarnMatterDO.class);
        earlyWarnMatterMapper.insert(earlyWarnMatter);
        // 返回
        return earlyWarnMatter.getId();
    }

    @Override
    public void updateEarlyWarnMatter(EarlyWarnMatterSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnMatterExists(updateReqVO.getId());
        // 更新
        EarlyWarnMatterDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnMatterDO.class);
        earlyWarnMatterMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnMatter(Long id) {
        // 校验存在
        validateEarlyWarnMatterExists(id);
        // 删除
        earlyWarnMatterMapper.deleteById(id);
    }

    private void validateEarlyWarnMatterExists(Long id) {
        if (earlyWarnMatterMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_MATTER_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnMatterDO getEarlyWarnMatter(Long id) {
        return earlyWarnMatterMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnMatterDO> getEarlyWarnMatterPage(EarlyWarnMatterPageReqVO pageReqVO) {
        return earlyWarnMatterMapper.selectPage(pageReqVO);
    }

}