package cn.iocoder.yudao.module.smartcity.service.lawenforcementvehicle;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementvehicle.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcementvehicle.LawEnforcementVehicleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.lawenforcementvehicle.LawEnforcementVehicleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 执法车辆管理 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class LawEnforcementVehicleServiceImpl implements LawEnforcementVehicleService {

    @Resource
    private LawEnforcementVehicleMapper lawEnforcementVehicleMapper;

    @Override
    public Long createLawEnforcementVehicle(LawEnforcementVehicleSaveReqVO createReqVO) {
        // 插入
        LawEnforcementVehicleDO lawEnforcementVehicle = BeanUtils.toBean(createReqVO, LawEnforcementVehicleDO.class);
        lawEnforcementVehicleMapper.insert(lawEnforcementVehicle);
        // 返回
        return lawEnforcementVehicle.getId();
    }

    @Override
    public void updateLawEnforcementVehicle(LawEnforcementVehicleSaveReqVO updateReqVO) {
        // 校验存在
        validateLawEnforcementVehicleExists(updateReqVO.getId());
        // 更新
        LawEnforcementVehicleDO updateObj = BeanUtils.toBean(updateReqVO, LawEnforcementVehicleDO.class);
        lawEnforcementVehicleMapper.updateById(updateObj);
    }

    @Override
    public void deleteLawEnforcementVehicle(Long id) {
        // 校验存在
        validateLawEnforcementVehicleExists(id);
        // 删除
        lawEnforcementVehicleMapper.deleteById(id);
    }

    private void validateLawEnforcementVehicleExists(Long id) {
        if (lawEnforcementVehicleMapper.selectById(id) == null) {
            throw exception(LAW_ENFORCEMENT_VEHICLE_NOT_EXISTS);
        }
    }

    @Override
    public LawEnforcementVehicleDO getLawEnforcementVehicle(Long id) {
        return lawEnforcementVehicleMapper.selectById(id);
    }

    @Override
    public PageResult<LawEnforcementVehicleDO> getLawEnforcementVehiclePage(LawEnforcementVehiclePageReqVO pageReqVO) {
        return lawEnforcementVehicleMapper.selectPage(pageReqVO);
    }

}