package cn.iocoder.yudao.module.appearance.service.outdoorad;

import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo.*;
import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.OutdoorAdOrderDO;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.OutdoorAdDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.appearance.dal.mysql.outdoorad.OutdoorAdMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * 户外广告 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class OutdoorAdServiceImpl implements OutdoorAdService {

    @Resource
    private OutdoorAdMapper outdoorAdMapper;

    @Override
    public PageResult<OutdoorAdPageRespVO> getOutdoorAdPage(OutdoorAdPageReqVO pageReqVO) {
        Page<OutdoorAdPageRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<OutdoorAdPageRespVO> result = outdoorAdMapper.selectPageWithJoin(page, pageReqVO);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

    @Override
    public OutdoorAdGetRespVO getOutdoorAd(String id) {
        // 1. 查询主表及区域、网格信息，并关联附件列表
        OutdoorAdGetRespVO respVO = outdoorAdMapper.selectDetailById(id);
        if (respVO == null) {
            throw new RuntimeException("户外广告不存在");
        }
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OutdoorAdAddRespVO addOutdoorAd(OutdoorAdAddReqVO addReqVO) {
        // 生成ID和编码
        String id = UUID.randomUUID().toString();
        String adCode = generateAdCode();
        String chainHash = generateChainHash(); // 模拟区块链哈希

        // 构建DO
        OutdoorAdDO adDO = new OutdoorAdDO();
        adDO.setId(id);
        adDO.setAdCode(adCode);
        adDO.setAdName(addReqVO.getAdName());
        adDO.setAdType(addReqVO.getAdType());
        adDO.setAdSize(addReqVO.getAdSize());
        adDO.setAdLocation(addReqVO.getLocation());
        adDO.setLng(new BigDecimal(addReqVO.getLng()));
        adDO.setLat(new BigDecimal(addReqVO.getLat()));
        adDO.setAreaCode(addReqVO.getAreaCode());
        adDO.setGridCode(addReqVO.getGridCode());
        adDO.setApprovalStatus("待审批");
        adDO.setDataStatus(0);
        adDO.setChainHash(chainHash);
        adDO.setRemark(addReqVO.getRemark());
        // 其他字段使用默认值

        outdoorAdMapper.insert(adDO);

        // 关联附件
        if (CollectionUtils.isNotEmpty(addReqVO.getAttachFileIds())) {
            outdoorAdMapper.updateAdIdForAttachments(addReqVO.getAttachFileIds(), id);
        }

        // 返回响应
        OutdoorAdAddRespVO respVO = new OutdoorAdAddRespVO();
        respVO.setOutdoorAdId(id);
        respVO.setAdCode(adCode);
        respVO.setChainHash(chainHash);
        respVO.setCreateTime(String.valueOf(LocalDateTime.now()));
        return respVO;
    }

    private String generateAdCode() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        String random = RandomStringUtils.randomNumeric(6);
        return "AD-" + date + "-" + random;
    }

    private String generateChainHash() {
        return "0x" + UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editOutdoorAd(OutdoorAdEditReqVO editReqVO) {
        // 1. 查询原广告
        OutdoorAdDO existAd = outdoorAdMapper.selectById(editReqVO.getId());
        if (existAd == null) {
            throw new RuntimeException("广告不存在");
        }
        // 2. 已归档广告禁止编辑
        if (existAd.getDataStatus() != null && existAd.getDataStatus() == 2) {
            throw new RuntimeException("已归档的广告信息禁止编辑");
        }

        // 3. 构建更新对象（只更新非空字段）
        OutdoorAdDO updateDO = new OutdoorAdDO();
        updateDO.setId(editReqVO.getId());
        if (editReqVO.getAdName() != null) updateDO.setAdName(editReqVO.getAdName());
        if (editReqVO.getAdType() != null) updateDO.setAdType(editReqVO.getAdType());
        if (editReqVO.getAdSize() != null) updateDO.setAdSize(editReqVO.getAdSize());
        if (editReqVO.getLocation() != null) updateDO.setAdLocation(editReqVO.getLocation());
        if (editReqVO.getLng() != null) updateDO.setLng(new BigDecimal(editReqVO.getLng()));
        if (editReqVO.getLat() != null) updateDO.setLat(new BigDecimal(editReqVO.getLat()));
        if (editReqVO.getAreaCode() != null) updateDO.setAreaCode(editReqVO.getAreaCode());
        if (editReqVO.getGridCode() != null) updateDO.setGridCode(editReqVO.getGridCode());
        if (editReqVO.getActualSize() != null) updateDO.setActualSize(editReqVO.getActualSize());
        if (editReqVO.getRemark() != null) updateDO.setRemark(editReqVO.getRemark());

        // 可选：更新区块链哈希（示例，实际可调用外部服务）
        // updateDO.setChainHash(generateChainHash());

        // 4. 更新主表
        int rows = outdoorAdMapper.updateById(updateDO);
        if (rows == 0) {
            throw new RuntimeException("编辑失败");
        }

        // 5. 处理附件：全量覆盖（先删除原关联，再绑定新附件）
        if (editReqVO.getAttachFileIds() != null) {
            // 5.1 解绑原附件（将 ad_id 置为 NULL）
            outdoorAdMapper.clearAdIdByAdId(editReqVO.getId());
            // 5.2 绑定新附件
            if (!editReqVO.getAttachFileIds().isEmpty()) {
                outdoorAdMapper.updateAdIdForAttachments(editReqVO.getAttachFileIds(), editReqVO.getId());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeOutdoorAds(OutdoorAdRemoveReqVO removeReqVO) {
        List<String> ids = removeReqVO.getIds();
        if (ids == null || ids.isEmpty()) {
            return;
        }
        // 1. 查询待删除的广告列表
        List<OutdoorAdDO> adList = outdoorAdMapper.selectBatchIds(ids);
        if (adList.size() != ids.size()) {
            throw new RuntimeException("部分广告不存在");
        }
        // 2. 校验每个广告的状态（已审批或已启用禁止删除）
        for (OutdoorAdDO ad : adList) {
            if ("已审批".equals(ad.getApprovalStatus())) {
                throw new RuntimeException(String.format("广告 %s 已审批，禁止删除", ad.getAdCode()));
            }
            if (ad.getDataStatus() != null && ad.getDataStatus() == 1) {
                throw new RuntimeException(String.format("广告 %s 已启用，禁止删除", ad.getAdCode()));
            }
        }
        // 3. 逻辑删除广告（设置 deleted = 1）
        outdoorAdMapper.deleteBatchIds(ids);
        // 4. 解绑附件：将关联的附件的 ad_id 置为 NULL
        outdoorAdMapper.clearAdIdByAdIds(ids);
        // 5. 记录删除原因到日志（可根据需要输出到业务日志或操作日志）
//        log.info("删除户外广告，广告ID列表：{}，删除原因：{}，操作人：{}",
//                ids, removeReqVO.getRemoveReason(), SecurityUtils.getLoginUser().getNickname());
    }

    @Override
    public PageResult<OutdoorAdOrderPageRespVO> getOrderPage(OutdoorAdOrderPageReqVO pageReqVO) {
        Page<OutdoorAdOrderPageRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<OutdoorAdOrderPageRespVO> result = outdoorAdMapper.selectOrderPage(page, pageReqVO);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

    @Override
    public OutdoorAdOrderGetRespVO getOrderDetail(String id) {
        // 1. 查询工单主信息及关联广告、处理人
        OutdoorAdOrderGetRespVO detail = outdoorAdMapper.selectOrderDetailById(id);
        if (detail == null) {
            throw new RuntimeException("整改工单不存在");
        }
        // 2. 查询问题图片列表
        List<OutdoorAdOrderGetRespVO.ImageItem> problemImgs = outdoorAdMapper.selectImagesByOrderIdAndType(id, "problem");
        detail.setProblemImgList(problemImgs);
        // 3. 查询整改后图片列表
        List<OutdoorAdOrderGetRespVO.ImageItem> handleImgs = outdoorAdMapper.selectImagesByOrderIdAndType(id, "handle");
        detail.setHandleImgList(handleImgs);
        return detail;
    }

//    @Override
//    public PageResult<OutdoorAdDO> getOutdoorAdPage( OutdoorAdPageReqVO pageReqVO) {
//        Page<OutdoorAdDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
//        // 直接调用自定义分页方法，传入 reqVO
//        IPage<OutdoorAdDO> pageResult = outdoorAdMapper.selectPageVO(page, pageReqVO);
//        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
//    }

//    @Override
//    public OutdoorAdDO getOutdoorAd( OutdoorAdGetReqVO getReqVO) {
//        return outdoorAdMapper.selectOneWithRelations(getReqVO);
//    }

//    @Override
//    public OutdoorAdDO addOutdoorAd( OutdoorAdAddReqVO addReqVO ) {
//        // 插入
//        OutdoorAdDO outdoorAd = BeanUtils.toBean(addReqVO, OutdoorAdDO.class);
//
//        String outdoorAdId = IdUtil.fastUUID();
//        outdoorAd.setOutdoorAdId(outdoorAdId);
//
//        outdoorAd.setAreaId(outdoorAdMapper.getAreaIdByCode(outdoorAd.getAreaCode()));
//        outdoorAd.setGridId(outdoorAdMapper.getGridIdByCode(outdoorAd.getGridCode()));
//
//        String adCode = generateAdCode();
//        outdoorAd.setAdCode(adCode);
//
//        outdoorAd.setDataStatus(0L);
//        outdoorAd.setApprovalStatus("待审批");
//
//        String chainHash = generateChainHash();
//        outdoorAd.setChainHash(chainHash);
//
//        outdoorAdMapper.insert(outdoorAd);
//        // 返回
//        return outdoorAd;
//    }

//    @Override
//    public Boolean editOutdoorAd( OutdoorAdEditReqVO editReqVO ) {
//        // 更新
//        OutdoorAdDO outdoorAd = BeanUtils.toBean(editReqVO, OutdoorAdDO.class);
//
//        outdoorAd.setId(outdoorAdMapper.getIdByOutdoorAdId(outdoorAd.getOutdoorAdId()));
//
//        outdoorAd.setAreaId(outdoorAdMapper.getAreaIdByCode(outdoorAd.getAreaCode()));
//        outdoorAd.setGridId(outdoorAdMapper.getGridIdByCode(outdoorAd.getGridCode()));
//
//        String chainHash = generateChainHash();
//        outdoorAd.setChainHash(chainHash);
//
//        int rows = outdoorAdMapper.updateById(outdoorAd);
//        // 返回
//        return rows > 0;
//        return null;
//    }

//    @Override
//    public Boolean removeOutdoorAd( OutdoorAdRemoveReqVO removeReqVO ) {
//        // 删除
//        OutdoorAdDO outdoorAd = BeanUtils.toBean(removeReqVO, OutdoorAdDO.class);
//
//        outdoorAd.setId(outdoorAdMapper.getIdByOutdoorAdId(outdoorAd.getOutdoorAdId()));
//
//        int rows = outdoorAdMapper.deleteById(outdoorAd.getId());
//        // 返回
//        return rows > 0;
//        return null;
//    }

    @Override
    public PageResult<OutdoorAdOrderDO> getOutdoorAdOrderPage( OutdoorAdOrderPageReqVO pageReqVO ) {
        Page<OutdoorAdOrderDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 直接调用自定义分页方法，传入 reqVO
        IPage<OutdoorAdOrderDO> pageResult = outdoorAdMapper.selectOrderPageVO(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public OutdoorAdOrderDO getOutdoorAdOrder( OutdoorAdOrderGetReqVO getReqVO ) {
        return outdoorAdMapper.selectOneOrder(getReqVO);
    }


//    /**
//     * 生成广告编码
//     * 格式：AD-YYYYMM-xxxxxx (YYYYMM为年月，xxxxxx为6位随机数)
//     */
//    private String generateAdCode() {
//        String yearMonth = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
//        int randomNum = new Random().nextInt(999999); // 生成 0-999998 的随机数
//        return String.format("AD-%s-%06d", yearMonth, randomNum);
//    }
//
//    /**
//     * 生成区块链存证哈希（模拟）
//     * 实际应调用区块链服务，这里使用 UUID 模拟唯一哈希值
//     */
//    private String generateChainHash() {
//        return IdUtil.fastSimpleUUID(); // 返回32位无横线UUID
//    }
}