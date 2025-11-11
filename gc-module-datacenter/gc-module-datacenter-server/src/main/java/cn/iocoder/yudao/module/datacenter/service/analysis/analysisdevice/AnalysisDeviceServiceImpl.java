package cn.iocoder.yudao.module.datacenter.service.analysis.analysisdevice;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisdevice.vo.AnalysisDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisdevice.vo.AnalysisDeviceSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisdevice.AnalysisDeviceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.analysis.analysisdevice.AnalysisDeviceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 按设备分域分析研判统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AnalysisDeviceServiceImpl implements AnalysisDeviceService {

    @Resource
    private AnalysisDeviceMapper analysisDeviceMapper;

    @Override
    public Long createAnalysisDevice(AnalysisDeviceSaveReqVO createReqVO) {
        // 插入
        AnalysisDeviceDO analysisDevice = BeanUtils.toBean(createReqVO, AnalysisDeviceDO.class);
        analysisDeviceMapper.insert(analysisDevice);
        // 返回
        return analysisDevice.getId();
    }

    @Override
    public void updateAnalysisDevice(AnalysisDeviceSaveReqVO updateReqVO) {
        // 校验存在
        validateAnalysisDeviceExists(updateReqVO.getId());
        // 更新
        AnalysisDeviceDO updateObj = BeanUtils.toBean(updateReqVO, AnalysisDeviceDO.class);
        analysisDeviceMapper.updateById(updateObj);
    }

    @Override
    public void deleteAnalysisDevice(Long id) {
        // 校验存在
        validateAnalysisDeviceExists(id);
        // 删除
        analysisDeviceMapper.deleteById(id);
    }

    private void validateAnalysisDeviceExists(Long id) {
        if (analysisDeviceMapper.selectById(id) == null) {
            throw exception(ANALYSIS_DEVICE_NOT_EXISTS);
        }
    }

    @Override
    public AnalysisDeviceDO getAnalysisDevice(Long id) {
        return analysisDeviceMapper.selectById(id);
    }

    @Override
    public PageResult<AnalysisDeviceDO> getAnalysisDevicePage(AnalysisDevicePageReqVO pageReqVO) {
        return analysisDeviceMapper.selectPage(pageReqVO);
    }

}