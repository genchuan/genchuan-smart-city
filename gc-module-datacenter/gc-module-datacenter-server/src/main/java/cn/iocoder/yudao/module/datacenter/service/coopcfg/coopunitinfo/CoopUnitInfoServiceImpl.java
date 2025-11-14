package cn.iocoder.yudao.module.datacenter.service.coopcfg.coopunitinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopunitinfo.vo.CoopUnitInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopunitinfo.vo.CoopUnitInfoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopunitinfo.CoopUnitInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.coopcfg.coopunitinfo.CoopUnitInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 联动单位信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CoopUnitInfoServiceImpl implements CoopUnitInfoService {

    @Resource
    private CoopUnitInfoMapper coopUnitInfoMapper;

    @Override
    public Long createCoopUnitInfo(CoopUnitInfoSaveReqVO createReqVO) {
        // 插入
        CoopUnitInfoDO coopUnitInfo = BeanUtils.toBean(createReqVO, CoopUnitInfoDO.class);
        coopUnitInfoMapper.insert(coopUnitInfo);
        // 返回
        return coopUnitInfo.getId();
    }

    @Override
    public void updateCoopUnitInfo(CoopUnitInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateCoopUnitInfoExists(updateReqVO.getId());
        // 更新
        CoopUnitInfoDO updateObj = BeanUtils.toBean(updateReqVO, CoopUnitInfoDO.class);
        coopUnitInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteCoopUnitInfo(Long id) {
        // 校验存在
        validateCoopUnitInfoExists(id);
        // 删除
        coopUnitInfoMapper.deleteById(id);
    }

    private void validateCoopUnitInfoExists(Long id) {
        if (coopUnitInfoMapper.selectById(id) == null) {
            throw exception(COOP_UNIT_INFO_NOT_EXISTS);
        }
    }

    @Override
    public CoopUnitInfoDO getCoopUnitInfo(Long id) {
        return coopUnitInfoMapper.selectById(id);
    }

    @Override
    public PageResult<CoopUnitInfoDO> getCoopUnitInfoPage(CoopUnitInfoPageReqVO pageReqVO) {
        return coopUnitInfoMapper.selectPage(pageReqVO);
    }

}