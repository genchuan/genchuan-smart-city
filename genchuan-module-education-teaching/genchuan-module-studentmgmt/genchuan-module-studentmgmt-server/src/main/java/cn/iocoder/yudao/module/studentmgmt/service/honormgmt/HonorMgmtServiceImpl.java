package cn.iocoder.yudao.module.studentmgmt.service.honormgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.honormgmt.HonorMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.honormgmt.HonorMgmtMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.HONOR_MGMT_NOT_EXISTS;

/**
 * 荣誉管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class HonorMgmtServiceImpl implements HonorMgmtService {

    @Resource
    private HonorMgmtMapper honorMgmtMapper;

    @Override
    public Long createHonorMgmt(HonorMgmtSaveReqVO createReqVO) {
        // 插入
        HonorMgmtDO honorMgmt = BeanUtils.toBean(createReqVO, HonorMgmtDO.class);
        honorMgmtMapper.insert(honorMgmt);

        // 返回
        return honorMgmt.getId();
    }

    @Override
    public void updateHonorMgmt(HonorMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateHonorMgmtExists(updateReqVO.getId());
        // 更新
        HonorMgmtDO updateObj = BeanUtils.toBean(updateReqVO, HonorMgmtDO.class);
        honorMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteHonorMgmt(Long id) {
        // 校验存在
        validateHonorMgmtExists(id);
        // 删除
        honorMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteHonorMgmtListByIds(List<Long> ids) {
        // 删除
        honorMgmtMapper.deleteByIds(ids);
    }


    private void validateHonorMgmtExists(Long id) {
        if (honorMgmtMapper.selectById(id) == null) {
            throw exception(HONOR_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public HonorMgmtDO getHonorMgmt(Long id) {
        return honorMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<HonorMgmtDO> getHonorMgmtPage(HonorMgmtPageReqVO pageReqVO) {
        return honorMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    public boolean pushHonorMgmt(HonorMgmtPushReqVO reqVO) {
        HonorMgmtDO honorMgmtDO = honorMgmtMapper.selectById(reqVO.getId());
        honorMgmtDO.setPushTime(LocalDateTime.now());
        honorMgmtDO.setStatus("已推送");
        int i = honorMgmtMapper.updateById(honorMgmtDO);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * 荣誉信息分布看板
     *
     * @param reqVO
     * @return
     */
    @Override
    public HonorMgmtChartRespVO getChart(HonorMgmtChartReqVO reqVO) {

        HonorMgmtChartRespVO vo = new HonorMgmtChartRespVO();

        String grade = reqVO.getGrade();
        String major = reqVO.getMajor();

        // 1. 卡片数据
        vo.setTotalHonorCount(honorMgmtMapper.selectTotalHonorCount(grade, major, "", ""));
        vo.setPendingAuditCount(honorMgmtMapper.selectTotalHonorCount(grade, major, "待审核", ""));
        vo.setExcellentStudentCount(honorMgmtMapper.selectTotalHonorCount(grade, major, "", "优秀学生"));
        vo.setScholarshipCount(honorMgmtMapper.selectTotalHonorCount(grade, major, "", "奖学金"));
        vo.setCompetitionCount(honorMgmtMapper.selectTotalHonorCount(grade, major, "", "竞赛获奖"));

        vo.setTodayPushCount(honorMgmtMapper.selectTodayPushCount(grade, major));

        return vo;
    }

}