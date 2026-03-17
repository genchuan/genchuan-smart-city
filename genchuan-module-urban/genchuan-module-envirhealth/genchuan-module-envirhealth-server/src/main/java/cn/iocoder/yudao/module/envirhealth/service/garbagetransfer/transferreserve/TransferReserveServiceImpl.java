package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferreserve;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.TransferReserveBatchSortReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.TransferReserveDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.TransferReservePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.TransferReserveSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferReserveDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.TransferReserveDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferReserveMapper;
import cn.iocoder.yudao.module.envirhealth.util.codegenerator.garbagetransfer.TransferReserveCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TRANSFER_RESERVE_NOT_EXISTS;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TRANSFER_RESERVE_SORT_TYPE_INVALID;

/**
 * 进站预约 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TransferReserveServiceImpl implements TransferReserveService {

    @Resource
    private TransferReserveMapper transferReserveMapper;

    @Resource
    private TransferReserveCodeGenerator codeGenerator;

    @Override
    public Long createTransferReserve(TransferReserveSaveReqVO createReqVO) {
        // 插入
        TransferReserveDO transferReserve = BeanUtils.toBean(createReqVO, TransferReserveDO.class);

        transferReserve.setReserveId(codeGenerator.generateReserveId());

        transferReserveMapper.insert(transferReserve);
        // 返回
        return transferReserve.getId();
    }

    @Override
    public void updateTransferReserve(TransferReserveSaveReqVO updateReqVO) {
        // 校验存在
        validateTransferReserveExists(updateReqVO.getId());
        // 更新
        TransferReserveDO updateObj = BeanUtils.toBean(updateReqVO, TransferReserveDO.class);
        transferReserveMapper.updateById(updateObj);
    }

    @Override
    public void deleteTransferReserve(Long id) {
        // 校验存在
        validateTransferReserveExists(id);
        // 删除
        transferReserveMapper.deleteById(id);
    }

    private void validateTransferReserveExists(Long id) {
        if (transferReserveMapper.selectById(id) == null) {
            throw exception(TRANSFER_RESERVE_NOT_EXISTS);
        }
    }

    @Override
    public TransferReserveDO getTransferReserve(Long id) {
        return transferReserveMapper.selectById(id);
    }

    @Override
    public PageResult<TransferReserveDO> getTransferReservePage(TransferReservePageReqVO pageReqVO) {
        return transferReserveMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<TransferReserveDetailDO> getTransferReserveDetailPage(TransferReservePageReqVO pageReqVO) {
        Long total = transferReserveMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<TransferReserveDetailDO> list = transferReserveMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSortTransferReserve(TransferReserveBatchSortReqVO reqVO) {
        List<Long> ids = reqVO.getIds();
        if (ids == null || ids.isEmpty()) {
            return;
        }
        // 1. 查询这些预约
        List<TransferReserveDO> list = transferReserveMapper.selectBatchIds(ids);
        if (list.isEmpty()) {
            return;
        }
        // 只对“待排序”的做排序
        list.removeIf(item -> !"待排序".equals(item.getReserveStatus()));
        if (list.isEmpty()) {
            return;
        }
        // 2. 根据排序规则在内存中排序
        switch (reqVO.getSortType()) {
            case "time":
                // 按预计进站时间从早到晚
                list.sort(Comparator.comparing(TransferReserveDO::getExpectedTime));
                break;
            case "type":
                // 先按垃圾品类，再按预计时间
                list.sort(Comparator
                        .comparing(TransferReserveDO::getGarbageTypeId, Comparator.nullsLast(String::compareTo))
                        .thenComparing(TransferReserveDO::getExpectedTime, Comparator.nullsLast(LocalDateTime::compareTo)));
                break;
            default:
                throw exception(TRANSFER_RESERVE_SORT_TYPE_INVALID);
        }

        // 当前登录用户，作为处理人/更新人
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        String username = loginUser != null ? String.valueOf(loginUser.getId()) : null;

        // 3. 生成排序号，从 已排序的最大值 开始
        Integer maxSortNo = transferReserveMapper.selectMaxSortNo();
        int sortNo = (maxSortNo != null ? maxSortNo : 0) + 1;

        LocalDateTime now = LocalDateTime.now();
        for (TransferReserveDO reserve : list) {
            TransferReserveDO update = new TransferReserveDO();
            update.setId(reserve.getId());
            update.setSortNo(sortNo++);
            update.setReserveStatus("已排序");
            update.setHandleBy(username);    // 记录处理人
            update.setUpdater(username);     // 审计字段
            update.setAbnormalCreateTime(now);

            transferReserveMapper.updateById(update);
        }
    }

    // 在 TransferReserveServiceImpl.java 中添加

    @Override
    public TransferReserveDashboardRespVO getDashboardStats() {
        TransferReserveDashboardRespVO resp = new TransferReserveDashboardRespVO();

        // 1. 卡片数据 - 直接从Mapper查询
        resp.setPendingVehicles(transferReserveMapper.selectPendingVehicles());
        resp.setSortedVehicles(transferReserveMapper.selectSortedVehicles());
        resp.setTodayTotalReserves(transferReserveMapper.selectTodayTotalReserves());

        // 2. 圆环图数据 - 垃圾品类分布
        resp.setGarbageTypeDistribution(transferReserveMapper.selectGarbageTypePie());

        // 3. 圆环图数据 - 区域分布
        resp.setAreaDistribution(transferReserveMapper.selectAreaPie());

        // 4. 柱状图数据 - 不同时段预约车辆数量对比
        resp.setReserveCountByTimeSlot(transferReserveMapper.selectReserveCountByTimeSlot());

        return resp;
    }
}