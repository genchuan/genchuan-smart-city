package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emervehicleinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehicleinfo.vo.EmerVehicleInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehicleinfo.vo.EmerVehicleInfoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emervehicleinfo.EmerVehicleInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emervehicleinfo.EmerVehicleInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 救援车辆信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EmerVehicleInfoServiceImpl implements EmerVehicleInfoService {

    @Resource
    private EmerVehicleInfoMapper emerVehicleInfoMapper;

    @Override
    public Long createEmerVehicleInfo(EmerVehicleInfoSaveReqVO createReqVO) {
        // 插入
        EmerVehicleInfoDO emerVehicleInfo = BeanUtils.toBean(createReqVO, EmerVehicleInfoDO.class);
        emerVehicleInfoMapper.insert(emerVehicleInfo);
        // 返回
        return emerVehicleInfo.getId();
    }

    @Override
    public void updateEmerVehicleInfo(EmerVehicleInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateEmerVehicleInfoExists(updateReqVO.getId());
        // 更新
        EmerVehicleInfoDO updateObj = BeanUtils.toBean(updateReqVO, EmerVehicleInfoDO.class);
        emerVehicleInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmerVehicleInfo(Long id) {
        // 校验存在
        validateEmerVehicleInfoExists(id);
        // 删除
        emerVehicleInfoMapper.deleteById(id);
    }

    private void validateEmerVehicleInfoExists(Long id) {
        if (emerVehicleInfoMapper.selectById(id) == null) {
            throw exception(EMER_VEHICLE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public EmerVehicleInfoDO getEmerVehicleInfo(Long id) {
        return emerVehicleInfoMapper.selectById(id);
    }

    @Override
    public PageResult<EmerVehicleInfoDO> getEmerVehicleInfoPage(EmerVehicleInfoPageReqVO pageReqVO) {
        return emerVehicleInfoMapper.selectPage(pageReqVO);
    }

}