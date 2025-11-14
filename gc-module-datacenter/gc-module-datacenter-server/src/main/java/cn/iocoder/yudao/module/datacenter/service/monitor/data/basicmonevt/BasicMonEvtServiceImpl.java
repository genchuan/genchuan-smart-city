package cn.iocoder.yudao.module.datacenter.service.monitor.data.basicmonevt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonevt.vo.BasicMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonevt.vo.BasicMonEvtSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.data.basicmonevt.BasicMonEvtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.data.basicmonevt.BasicMonEvtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 基础监测事件 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class BasicMonEvtServiceImpl implements BasicMonEvtService {

    @Resource
    private BasicMonEvtMapper basicMonEvtMapper;

    @Override
    public Long createBasicMonEvt(BasicMonEvtSaveReqVO createReqVO) {
        // 插入
        BasicMonEvtDO basicMonEvt = BeanUtils.toBean(createReqVO, BasicMonEvtDO.class);
        basicMonEvtMapper.insert(basicMonEvt);
        // 返回
        return basicMonEvt.getId();
    }

    @Override
    public void updateBasicMonEvt(BasicMonEvtSaveReqVO updateReqVO) {
        // 校验存在
        validateBasicMonEvtExists(updateReqVO.getId());
        // 更新
        BasicMonEvtDO updateObj = BeanUtils.toBean(updateReqVO, BasicMonEvtDO.class);
        basicMonEvtMapper.updateById(updateObj);
    }

    @Override
    public void deleteBasicMonEvt(Long id) {
        // 校验存在
        validateBasicMonEvtExists(id);
        // 删除
        basicMonEvtMapper.deleteById(id);
    }

    private void validateBasicMonEvtExists(Long id) {
        if (basicMonEvtMapper.selectById(id) == null) {
            throw exception(BASIC_MON_EVT_NOT_EXISTS);
        }
    }

    @Override
    public BasicMonEvtDO getBasicMonEvt(Long id) {
        return basicMonEvtMapper.selectById(id);
    }

    @Override
    public PageResult<BasicMonEvtDO> getBasicMonEvtPage(BasicMonEvtPageReqVO pageReqVO) {
        return basicMonEvtMapper.selectPage(pageReqVO);
    }

}