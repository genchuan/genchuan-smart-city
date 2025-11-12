package cn.iocoder.yudao.module.datacenter.service.monitor.comprehensiveReport.keyidxsummaryrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.keyidxsummaryrpt.vo.KeyIdxSummaryRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.keyidxsummaryrpt.vo.KeyIdxSummaryRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.keyidxsummaryrpt.KeyIdxSummaryRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.comprehensiveReport.keyidxsummaryrpt.KeyIdxSummaryRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 关键指标汇总报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class KeyIdxSummaryRptServiceImpl implements KeyIdxSummaryRptService {

    @Resource
    private KeyIdxSummaryRptMapper keyIdxSummaryRptMapper;

    @Override
    public Long createKeyIdxSummaryRpt(KeyIdxSummaryRptSaveReqVO createReqVO) {
        // 插入
        KeyIdxSummaryRptDO keyIdxSummaryRpt = BeanUtils.toBean(createReqVO, KeyIdxSummaryRptDO.class);
        keyIdxSummaryRptMapper.insert(keyIdxSummaryRpt);
        // 返回
        return keyIdxSummaryRpt.getId();
    }

    @Override
    public void updateKeyIdxSummaryRpt(KeyIdxSummaryRptSaveReqVO updateReqVO) {
        // 校验存在
        validateKeyIdxSummaryRptExists(updateReqVO.getId());
        // 更新
        KeyIdxSummaryRptDO updateObj = BeanUtils.toBean(updateReqVO, KeyIdxSummaryRptDO.class);
        keyIdxSummaryRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteKeyIdxSummaryRpt(Long id) {
        // 校验存在
        validateKeyIdxSummaryRptExists(id);
        // 删除
        keyIdxSummaryRptMapper.deleteById(id);
    }

    private void validateKeyIdxSummaryRptExists(Long id) {
        if (keyIdxSummaryRptMapper.selectById(id) == null) {
            throw exception(KEY_IDX_SUMMARY_RPT_NOT_EXISTS);
        }
    }

    @Override
    public KeyIdxSummaryRptDO getKeyIdxSummaryRpt(Long id) {
        return keyIdxSummaryRptMapper.selectById(id);
    }

    @Override
    public PageResult<KeyIdxSummaryRptDO> getKeyIdxSummaryRptPage(KeyIdxSummaryRptPageReqVO pageReqVO) {
        return keyIdxSummaryRptMapper.selectPage(pageReqVO);
    }

}