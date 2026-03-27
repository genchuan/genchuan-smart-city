package cn.iocoder.yudao.module.facility.service.manhole.disposalorder;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.disposalorder.DisposalOrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.facility.dal.mysql.manhole.disposalorder.DisposalOrderMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.*;

/**
 * 处置工单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DisposalOrderServiceImpl implements DisposalOrderService {

    @Resource
    private DisposalOrderMapper orderMapper;

    @Override
    public Long createOrder(DisposalOrderSaveReqVO createReqVO) {
        // 插入
        DisposalOrderDO order = BeanUtils.toBean(createReqVO, DisposalOrderDO.class);
        orderMapper.insert(order);
        // 返回
        return order.getId();
    }

    @Override
    public void updateOrder(DisposalOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderExists(updateReqVO.getId());
        // 更新
        DisposalOrderDO updateObj = BeanUtils.toBean(updateReqVO, DisposalOrderDO.class);
        orderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Long id) {
        // 校验存在
        validateOrderExists(id);
        // 删除
        orderMapper.deleteById(id);
    }

    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public DisposalOrderDO getOrder(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public PageResult<DisposalOrderDO> getOrderPage(DisposalOrderPageReqVO pageReqVO) {
        return orderMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ManholeCoverRepairOrderPageRespVO> page(ManholeCoverRepairOrderPageReqVO reqVO) {
        Page<ManholeCoverRepairOrderPageRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<ManholeCoverRepairOrderPageRespVO> resultPage = orderMapper.selectRepairOrderPage(page, reqVO);
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ManholeCoverRepairOrderAddRespVO addRepairOrder(ManholeCoverRepairOrderAddReqVO reqVO) {
        // 1. 生成工单编号 MC-REPAIR-yyyyMM-xxx
        String yyyyMM = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        String orderNo = "MC-REPAIR-" + yyyyMM + "-" + String.format("%05d", System.currentTimeMillis() % 100000);

        // 2. 工单状态：0待派单 1已派单
        Integer orderStatus = (reqVO.getAssignUserId() == null || reqVO.getAssignUserId().isEmpty()) ? 0 : 1;

        // 3. 构造 DO → 只插入 disposal_order
        DisposalOrderDO order = new DisposalOrderDO();
        order.setWarnId(reqVO.getWarnId() == null ? null : Long.valueOf(reqVO.getWarnId()));
        order.setCoverId(Long.valueOf(reqVO.getCoverId()));

        // 异常类型（表枚举：倾斜、振动、开合异常）
        String abnormalType;
        switch (reqVO.getOrderType()) {
            case 1:
                abnormalType = "倾斜";
                break;
            case 2:
                abnormalType = "开合异常";
                break;
            default:
                abnormalType = "振动";
        }
        order.setAbnormalType(abnormalType);

        // 风险等级
        order.setRiskLevelId(1L);

        // 维修人员
        if (reqVO.getRepairUserId() != null && !reqVO.getRepairUserId().isEmpty()) {
            order.setAssignStaffId(Long.valueOf(reqVO.getRepairUserId()));
        } else {
            order.setAssignStaffId(0L);
        }

        // 处置时限：默认 3 天
        order.setDealLimit(LocalDate.now().plusDays(3));

        // 进度状态
        order.setProcessStatus("待处置");

        // 创建人、租户、删除标识
        order.setCreator(reqVO.getOperateUserId());
        order.setDeleted(Boolean.FALSE);

        // 插入数据库
        orderMapper.insert(order);

        // 4. 组装返回
        ManholeCoverRepairOrderAddRespVO resp = new ManholeCoverRepairOrderAddRespVO();
        resp.setOrderId(order.getId().toString());
        resp.setOrderNo(orderNo);
        resp.setOrderStatus(orderStatus);
        resp.setTenantId(reqVO.getTenantId());
        return resp;
    }

}