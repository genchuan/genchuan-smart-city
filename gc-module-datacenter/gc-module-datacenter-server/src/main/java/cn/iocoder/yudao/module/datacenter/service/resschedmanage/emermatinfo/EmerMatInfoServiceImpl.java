package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emermatinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatinfo.vo.EmerMatInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatinfo.vo.EmerMatInfoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emermatinfo.EmerMatInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emermatinfo.EmerMatInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 应急物资信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EmerMatInfoServiceImpl implements EmerMatInfoService {

    @Resource
    private EmerMatInfoMapper emerMatInfoMapper;

    @Override
    public Long createEmerMatInfo(EmerMatInfoSaveReqVO createReqVO) {
        // 插入
        EmerMatInfoDO emerMatInfo = BeanUtils.toBean(createReqVO, EmerMatInfoDO.class);
        emerMatInfoMapper.insert(emerMatInfo);
        // 返回
        return emerMatInfo.getId();
    }

    @Override
    public void updateEmerMatInfo(EmerMatInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateEmerMatInfoExists(updateReqVO.getId());
        // 更新
        EmerMatInfoDO updateObj = BeanUtils.toBean(updateReqVO, EmerMatInfoDO.class);
        emerMatInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmerMatInfo(Long id) {
        // 校验存在
        validateEmerMatInfoExists(id);
        // 删除
        emerMatInfoMapper.deleteById(id);
    }

    private void validateEmerMatInfoExists(Long id) {
        if (emerMatInfoMapper.selectById(id) == null) {
            throw exception(EMER_MAT_INFO_NOT_EXISTS);
        }
    }

    @Override
    public EmerMatInfoDO getEmerMatInfo(Long id) {
        return emerMatInfoMapper.selectById(id);
    }

    @Override
    public PageResult<EmerMatInfoDO> getEmerMatInfoPage(EmerMatInfoPageReqVO pageReqVO) {
        return emerMatInfoMapper.selectPage(pageReqVO);
    }

}