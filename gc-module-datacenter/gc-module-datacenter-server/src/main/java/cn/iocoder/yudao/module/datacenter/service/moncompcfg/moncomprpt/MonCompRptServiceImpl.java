package cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncomprpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncomprpt.vo.MonCompRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncomprpt.vo.MonCompRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncomprpt.MonCompRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.moncompcfg.moncomprpt.MonCompRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测部件统计报 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonCompRptServiceImpl implements MonCompRptService {

    @Resource
    private MonCompRptMapper monCompRptMapper;

    @Override
    public Long createMonCompRpt(MonCompRptSaveReqVO createReqVO) {
        // 插入
        MonCompRptDO monCompRpt = BeanUtils.toBean(createReqVO, MonCompRptDO.class);
        monCompRptMapper.insert(monCompRpt);
        // 返回
        return monCompRpt.getId();
    }

    @Override
    public void updateMonCompRpt(MonCompRptSaveReqVO updateReqVO) {
        // 校验存在
        validateMonCompRptExists(updateReqVO.getId());
        // 更新
        MonCompRptDO updateObj = BeanUtils.toBean(updateReqVO, MonCompRptDO.class);
        monCompRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonCompRpt(Long id) {
        // 校验存在
        validateMonCompRptExists(id);
        // 删除
        monCompRptMapper.deleteById(id);
    }

    private void validateMonCompRptExists(Long id) {
        if (monCompRptMapper.selectById(id) == null) {
            throw exception(MON_COMP_RPT_NOT_EXISTS);
        }
    }

    @Override
    public MonCompRptDO getMonCompRpt(Long id) {
        return monCompRptMapper.selectById(id);
    }

    @Override
    public PageResult<MonCompRptDO> getMonCompRptPage(MonCompRptPageReqVO pageReqVO) {
        return monCompRptMapper.selectPage(pageReqVO);
    }

}