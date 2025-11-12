package cn.iocoder.yudao.module.datacenter.service.monitor.baseReport.basicmonevtrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonevtrpt.vo.BasicMonEvtRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonevtrpt.vo.BasicMonEvtRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonevtrpt.BasicMonEvtRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.baseReport.basicmonevtrpt.BasicMonEvtRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 基础监测事件报 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class BasicMonEvtRptServiceImpl implements BasicMonEvtRptService {

    @Resource
    private BasicMonEvtRptMapper basicMonEvtRptMapper;

    @Override
    public Long createBasicMonEvtRpt(BasicMonEvtRptSaveReqVO createReqVO) {
        // 插入
        BasicMonEvtRptDO basicMonEvtRpt = BeanUtils.toBean(createReqVO, BasicMonEvtRptDO.class);
        basicMonEvtRptMapper.insert(basicMonEvtRpt);
        // 返回
        return basicMonEvtRpt.getId();
    }

    @Override
    public void updateBasicMonEvtRpt(BasicMonEvtRptSaveReqVO updateReqVO) {
        // 校验存在
        validateBasicMonEvtRptExists(updateReqVO.getId());
        // 更新
        BasicMonEvtRptDO updateObj = BeanUtils.toBean(updateReqVO, BasicMonEvtRptDO.class);
        basicMonEvtRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteBasicMonEvtRpt(Long id) {
        // 校验存在
        validateBasicMonEvtRptExists(id);
        // 删除
        basicMonEvtRptMapper.deleteById(id);
    }

    private void validateBasicMonEvtRptExists(Long id) {
        if (basicMonEvtRptMapper.selectById(id) == null) {
            throw exception(BASIC_MON_EVT_RPT_NOT_EXISTS);
        }
    }

    @Override
    public BasicMonEvtRptDO getBasicMonEvtRpt(Long id) {
        return basicMonEvtRptMapper.selectById(id);
    }

    @Override
    public PageResult<BasicMonEvtRptDO> getBasicMonEvtRptPage(BasicMonEvtRptPageReqVO pageReqVO) {
        return basicMonEvtRptMapper.selectPage(pageReqVO);
    }

}