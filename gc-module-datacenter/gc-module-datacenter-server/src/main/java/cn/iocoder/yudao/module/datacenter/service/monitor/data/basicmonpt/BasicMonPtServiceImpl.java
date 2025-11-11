package cn.iocoder.yudao.module.datacenter.service.monitor.data.basicmonpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonpt.vo.BasicMonPtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonpt.vo.BasicMonPtSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonpt.BasicMonPtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.data.basicmonpt.BasicMonPtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 基础监测点位 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class BasicMonPtServiceImpl implements BasicMonPtService {

    @Resource
    private BasicMonPtMapper basicMonPtMapper;

    @Override
    public Long createBasicMonPt(BasicMonPtSaveReqVO createReqVO) {
        // 插入
        BasicMonPtDO basicMonPt = BeanUtils.toBean(createReqVO, BasicMonPtDO.class);
        basicMonPtMapper.insert(basicMonPt);
        // 返回
        return basicMonPt.getId();
    }

    @Override
    public void updateBasicMonPt(BasicMonPtSaveReqVO updateReqVO) {
        // 校验存在
        validateBasicMonPtExists(updateReqVO.getId());
        // 更新
        BasicMonPtDO updateObj = BeanUtils.toBean(updateReqVO, BasicMonPtDO.class);
        basicMonPtMapper.updateById(updateObj);
    }

    @Override
    public void deleteBasicMonPt(Long id) {
        // 校验存在
        validateBasicMonPtExists(id);
        // 删除
        basicMonPtMapper.deleteById(id);
    }

    private void validateBasicMonPtExists(Long id) {
        if (basicMonPtMapper.selectById(id) == null) {
            throw exception(BASIC_MON_PT_NOT_EXISTS);
        }
    }

    @Override
    public BasicMonPtDO getBasicMonPt(Long id) {
        return basicMonPtMapper.selectById(id);
    }

    @Override
    public PageResult<BasicMonPtDO> getBasicMonPtPage(BasicMonPtPageReqVO pageReqVO) {
        return basicMonPtMapper.selectPage(pageReqVO);
    }

}