package cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompinfo.vo.MonCompInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompinfo.vo.MonCompInfoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompinfo.MonCompInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.moncompcfg.moncompinfo.MonCompInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测部件信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonCompInfoServiceImpl implements MonCompInfoService {

    @Resource
    private MonCompInfoMapper monCompInfoMapper;

    @Override
    public Long createMonCompInfo(MonCompInfoSaveReqVO createReqVO) {
        // 插入
        MonCompInfoDO monCompInfo = BeanUtils.toBean(createReqVO, MonCompInfoDO.class);
        monCompInfoMapper.insert(monCompInfo);
        // 返回
        return monCompInfo.getId();
    }

    @Override
    public void updateMonCompInfo(MonCompInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateMonCompInfoExists(updateReqVO.getId());
        // 更新
        MonCompInfoDO updateObj = BeanUtils.toBean(updateReqVO, MonCompInfoDO.class);
        monCompInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonCompInfo(Long id) {
        // 校验存在
        validateMonCompInfoExists(id);
        // 删除
        monCompInfoMapper.deleteById(id);
    }

    private void validateMonCompInfoExists(Long id) {
        if (monCompInfoMapper.selectById(id) == null) {
            throw exception(MON_COMP_INFO_NOT_EXISTS);
        }
    }

    @Override
    public MonCompInfoDO getMonCompInfo(Long id) {
        return monCompInfoMapper.selectById(id);
    }

    @Override
    public PageResult<MonCompInfoDO> getMonCompInfoPage(MonCompInfoPageReqVO pageReqVO) {
        return monCompInfoMapper.selectPage(pageReqVO);
    }

}