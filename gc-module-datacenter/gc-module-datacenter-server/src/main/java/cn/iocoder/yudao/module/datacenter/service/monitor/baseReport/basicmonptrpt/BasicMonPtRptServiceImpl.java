package cn.iocoder.yudao.module.datacenter.service.monitor.baseReport.basicmonptrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonptrpt.vo.BasicMonPtRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonptrpt.vo.BasicMonPtRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonptrpt.BasicMonPtRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.baseReport.basicmonptrpt.BasicMonPtRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 基础监测点位报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class BasicMonPtRptServiceImpl implements BasicMonPtRptService {

    @Resource
    private BasicMonPtRptMapper basicMonPtRptMapper;

    @Override
    public Long createBasicMonPtRpt(BasicMonPtRptSaveReqVO createReqVO) {
        // 插入
        BasicMonPtRptDO basicMonPtRpt = BeanUtils.toBean(createReqVO, BasicMonPtRptDO.class);
        basicMonPtRptMapper.insert(basicMonPtRpt);
        // 返回
        return basicMonPtRpt.getId();
    }

    @Override
    public void updateBasicMonPtRpt(BasicMonPtRptSaveReqVO updateReqVO) {
        // 校验存在
        validateBasicMonPtRptExists(updateReqVO.getId());
        // 更新
        BasicMonPtRptDO updateObj = BeanUtils.toBean(updateReqVO, BasicMonPtRptDO.class);
        basicMonPtRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteBasicMonPtRpt(Long id) {
        // 校验存在
        validateBasicMonPtRptExists(id);
        // 删除
        basicMonPtRptMapper.deleteById(id);
    }

    private void validateBasicMonPtRptExists(Long id) {
        if (basicMonPtRptMapper.selectById(id) == null) {
            throw exception(BASIC_MON_PT_RPT_NOT_EXISTS);
        }
    }

    @Override
    public BasicMonPtRptDO getBasicMonPtRpt(Long id) {
        return basicMonPtRptMapper.selectById(id);
    }

    @Override
    public PageResult<BasicMonPtRptDO> getBasicMonPtRptPage(BasicMonPtRptPageReqVO pageReqVO) {
        return basicMonPtRptMapper.selectPage(pageReqVO);
    }

}