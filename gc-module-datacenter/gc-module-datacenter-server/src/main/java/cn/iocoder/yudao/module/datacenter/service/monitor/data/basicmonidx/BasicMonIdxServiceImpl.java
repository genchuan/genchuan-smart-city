package cn.iocoder.yudao.module.datacenter.service.monitor.data.basicmonidx;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonidx.vo.BasicMonIdxPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonidx.vo.BasicMonIdxSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonidx.BasicMonIdxDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.data.basicmonidx.BasicMonIdxMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 基础监测指标 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class BasicMonIdxServiceImpl implements BasicMonIdxService {

    @Resource
    private BasicMonIdxMapper basicMonIdxMapper;

    @Override
    public Long createBasicMonIdx(BasicMonIdxSaveReqVO createReqVO) {
        // 插入
        BasicMonIdxDO basicMonIdx = BeanUtils.toBean(createReqVO, BasicMonIdxDO.class);
        basicMonIdxMapper.insert(basicMonIdx);
        // 返回
        return basicMonIdx.getId();
    }

    @Override
    public void updateBasicMonIdx(BasicMonIdxSaveReqVO updateReqVO) {
        // 校验存在
        validateBasicMonIdxExists(updateReqVO.getId());
        // 更新
        BasicMonIdxDO updateObj = BeanUtils.toBean(updateReqVO, BasicMonIdxDO.class);
        basicMonIdxMapper.updateById(updateObj);
    }

    @Override
    public void deleteBasicMonIdx(Long id) {
        // 校验存在
        validateBasicMonIdxExists(id);
        // 删除
        basicMonIdxMapper.deleteById(id);
    }

    private void validateBasicMonIdxExists(Long id) {
        if (basicMonIdxMapper.selectById(id) == null) {
            throw exception(BASIC_MON_IDX_NOT_EXISTS);
        }
    }

    @Override
    public BasicMonIdxDO getBasicMonIdx(Long id) {
        return basicMonIdxMapper.selectById(id);
    }

    @Override
    public PageResult<BasicMonIdxDO> getBasicMonIdxPage(BasicMonIdxPageReqVO pageReqVO) {
        return basicMonIdxMapper.selectPage(pageReqVO);
    }

}