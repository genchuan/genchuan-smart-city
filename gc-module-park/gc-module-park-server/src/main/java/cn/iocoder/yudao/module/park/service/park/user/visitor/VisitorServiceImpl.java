package cn.iocoder.yudao.module.park.service.park.user.visitor;

import cn.iocoder.yudao.module.park.controller.admin.park.user.visitor.vo.VisitorPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.visitor.vo.VisitorSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.visitor.VisitorDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.visitor.VisitorMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 访客 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class VisitorServiceImpl implements VisitorService {

    @Resource
    private VisitorMapper visitorMapper;

    @Override
    public Long createVisitor(VisitorSaveReqVO createReqVO) {
        // 插入
        VisitorDO visitor = BeanUtils.toBean(createReqVO, VisitorDO.class);
        visitorMapper.insert(visitor);
        // 返回
        return visitor.getId();
    }

    @Override
    public void updateVisitor(VisitorSaveReqVO updateReqVO) {
        // 校验存在
        validateVisitorExists(updateReqVO.getId());
        // 更新
        VisitorDO updateObj = BeanUtils.toBean(updateReqVO, VisitorDO.class);
        visitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteVisitor(Long id) {
        // 校验存在
        validateVisitorExists(id);
        // 删除
        visitorMapper.deleteById(id);
    }

    private void validateVisitorExists(Long id) {
        if (visitorMapper.selectById(id) == null) {
            throw exception(VISITOR_NOT_EXISTS);
        }
    }

    @Override
    public VisitorDO getVisitor(Long id) {
        return visitorMapper.selectById(id);
    }

    @Override
    public PageResult<VisitorDO> getVisitorPage(VisitorPageReqVO pageReqVO) {
        return visitorMapper.selectPage(pageReqVO);
    }

}
