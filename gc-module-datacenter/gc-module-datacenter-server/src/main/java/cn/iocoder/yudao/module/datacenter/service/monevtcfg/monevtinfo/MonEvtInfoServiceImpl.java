package cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtinfo.vo.MonEvtInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtinfo.vo.MonEvtInfoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtinfo.MonEvtInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monevtcfg.monevtinfo.MonEvtInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测事件信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonEvtInfoServiceImpl implements MonEvtInfoService {

    @Resource
    private MonEvtInfoMapper monEvtInfoMapper;

    @Override
    public Long createMonEvtInfo(MonEvtInfoSaveReqVO createReqVO) {
        // 插入
        MonEvtInfoDO monEvtInfo = BeanUtils.toBean(createReqVO, MonEvtInfoDO.class);
        monEvtInfoMapper.insert(monEvtInfo);
        // 返回
        return monEvtInfo.getId();
    }

    @Override
    public void updateMonEvtInfo(MonEvtInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateMonEvtInfoExists(updateReqVO.getId());
        // 更新
        MonEvtInfoDO updateObj = BeanUtils.toBean(updateReqVO, MonEvtInfoDO.class);
        monEvtInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonEvtInfo(Long id) {
        // 校验存在
        validateMonEvtInfoExists(id);
        // 删除
        monEvtInfoMapper.deleteById(id);
    }

    private void validateMonEvtInfoExists(Long id) {
        if (monEvtInfoMapper.selectById(id) == null) {
            throw exception(MON_EVT_INFO_NOT_EXISTS);
        }
    }

    @Override
    public MonEvtInfoDO getMonEvtInfo(Long id) {
        return monEvtInfoMapper.selectById(id);
    }

    @Override
    public PageResult<MonEvtInfoDO> getMonEvtInfoPage(MonEvtInfoPageReqVO pageReqVO) {
        return monEvtInfoMapper.selectPage(pageReqVO);
    }

}