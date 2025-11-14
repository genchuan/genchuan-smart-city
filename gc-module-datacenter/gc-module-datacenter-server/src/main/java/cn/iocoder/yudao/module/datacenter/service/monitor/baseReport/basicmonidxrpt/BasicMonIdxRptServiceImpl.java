package cn.iocoder.yudao.module.datacenter.service.monitor.baseReport.basicmonidxrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonidxrpt.vo.BasicMonIdxRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonidxrpt.vo.BasicMonIdxRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonidxrpt.BasicMonIdxRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.baseReport.basicmonidxrpt.BasicMonIdxRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 基础监测指标报 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class BasicMonIdxRptServiceImpl implements BasicMonIdxRptService {

    @Resource
    private BasicMonIdxRptMapper basicMonIdxRptMapper;

    @Override
    public Long createBasicMonIdxRpt(BasicMonIdxRptSaveReqVO createReqVO) {
        // 插入
        BasicMonIdxRptDO basicMonIdxRpt = BeanUtils.toBean(createReqVO, BasicMonIdxRptDO.class);
        basicMonIdxRptMapper.insert(basicMonIdxRpt);
        // 返回
        return basicMonIdxRpt.getId();
    }

    @Override
    public void updateBasicMonIdxRpt(BasicMonIdxRptSaveReqVO updateReqVO) {
        // 校验存在
        validateBasicMonIdxRptExists(updateReqVO.getId());
        // 更新
        BasicMonIdxRptDO updateObj = BeanUtils.toBean(updateReqVO, BasicMonIdxRptDO.class);
        basicMonIdxRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteBasicMonIdxRpt(Long id) {
        // 校验存在
        validateBasicMonIdxRptExists(id);
        // 删除
        basicMonIdxRptMapper.deleteById(id);
    }

    private void validateBasicMonIdxRptExists(Long id) {
        if (basicMonIdxRptMapper.selectById(id) == null) {
            throw exception(BASIC_MON_IDX_RPT_NOT_EXISTS);
        }
    }

    @Override
    public BasicMonIdxRptDO getBasicMonIdxRpt(Long id) {
        return basicMonIdxRptMapper.selectById(id);
    }

    @Override
    public PageResult<BasicMonIdxRptDO> getBasicMonIdxRptPage(BasicMonIdxRptPageReqVO pageReqVO) {
        return basicMonIdxRptMapper.selectPage(pageReqVO);
    }

}