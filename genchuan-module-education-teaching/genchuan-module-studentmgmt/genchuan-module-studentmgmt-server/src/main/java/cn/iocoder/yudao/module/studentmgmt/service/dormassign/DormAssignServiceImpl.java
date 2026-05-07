package cn.iocoder.yudao.module.studentmgmt.service.dormassign;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.bedmgmt.BedMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormassign.DormAssignDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.bedmgmt.BedMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.dormassign.DormAssignMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.BedStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.DormAssignStatusEnum;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.DORM_ASSIGN_NOT_EXISTS;

/**
 * 宿舍分配 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DormAssignServiceImpl implements DormAssignService {

    @Resource
    private DormAssignMapper dormAssignMapper;
    @Resource
    private BedMgmtMapper bedMgmtMapper;

    @Override
    public Long createDormAssign(DormAssignSaveReqVO createReqVO) {
        // 插入
        DormAssignDO dormAssign = BeanUtils.toBean(createReqVO, DormAssignDO.class);
        dormAssignMapper.insert(dormAssign);

        // 返回
        return dormAssign.getId();
    }

    @Override
    public void updateDormAssign(DormAssignSaveReqVO updateReqVO) {
        // 校验存在
        validateDormAssignExists(updateReqVO.getId());
        // 更新
        DormAssignDO updateObj = BeanUtils.toBean(updateReqVO, DormAssignDO.class);
        dormAssignMapper.updateById(updateObj);
    }

    @Override
    public void deleteDormAssign(Long id) {
        // 校验存在
        validateDormAssignExists(id);
        // 删除
        dormAssignMapper.deleteById(id);
    }

    @Override
        public void deleteDormAssignListByIds(List<Long> ids) {
        // 删除
        dormAssignMapper.deleteByIds(ids);
        }


    private DormAssignDO validateDormAssignExists(Long id) {
        DormAssignDO dormAssign = dormAssignMapper.selectById(id);
        if (dormAssign == null) {
            throw exception(DORM_ASSIGN_NOT_EXISTS);
        }
        return dormAssign;
    }

    @Override
    public DormAssignDO getDormAssign(Long id) {
        return dormAssignMapper.selectById(id);
    }

    @Override
    public PageResult<DormAssignDO> getDormAssignPage(DormAssignPageReqVO pageReqVO) {
        return dormAssignMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean assign(DormAssignAssignReqVO reqVO) {
        Long[] ids = reqVO.getIds();
        Long[] bedIds = reqVO.getBedIds();
        int total = 0;
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            Long bedId = bedIds[i];

            // 校验存在
            DormAssignDO dormAssign = validateDormAssignExists(id);
            dormAssign.setBedId(bedId);
            dormAssign.setAssignTime(LocalDateTime.now());
            dormAssign.setStatus(DormAssignStatusEnum.ASSIGNED.getStatus());

            BedMgmtDO bedMgmt = bedMgmtMapper.selectById(bedId);
            if (bedMgmt == null) {
                throw exception("床位不存在");
            }
            if (bedMgmt.getStudentId() != null) {
                throw exception(bedId+"床位已被分配，不可分配");
            }
            bedMgmt.setStudentId(dormAssign.getStudentId());
            bedMgmt.setAssignTime(LocalDateTime.now());
            bedMgmt.setStatus(BedStatusEnum.BED_STATUS_ALLOCATED.getStatus());
            bedMgmtMapper.updateById(bedMgmt);

            // 更新
            int j = dormAssignMapper.updateById(dormAssign);
            total += j;
        }
        return total > 0;
    }

    @Override
    public Boolean adjust(DormAssignAdjustReqVO reqVO) {
        Long[] ids = reqVO.getIds();
        Long newDormId = reqVO.getNewDormId();
        Long[] newBedIds = reqVO.getNewBedIds();
        int total = 0;
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            Long bedId = newBedIds[i];

            // 校验存在
            DormAssignDO dormAssign = validateDormAssignExists(id);

            Long oldBedId = dormAssign.getBedId();
            // 查询原床位
            BedMgmtDO bedMgmtDO = bedMgmtMapper.selectById(oldBedId);
            if (bedMgmtDO == null) {
                throw exception("原床位不存在");
            }
            bedMgmtDO.setStudentId(null);
            bedMgmtDO.setStatus(BedStatusEnum.BED_STATUS_UNALLOCATED.getStatus());
            bedMgmtDO.setAssignTime(null);
            bedMgmtMapper.updateById(bedMgmtDO);


            dormAssign.setAssignTime(LocalDateTime.now());
            dormAssign.setStatus(DormAssignStatusEnum.ASSIGNED.getStatus());

            BedMgmtDO bedMgmt = bedMgmtMapper.selectById(bedId);
            if (bedMgmt == null) {
                throw exception("床位不存在");
            }
            if (bedMgmt.getStudentId() != null) {
                throw exception(bedId+"床位已被分配，不可分配");
            }
            bedMgmt.setStudentId(dormAssign.getStudentId());
            bedMgmt.setAssignTime(LocalDateTime.now());
            bedMgmt.setStatus(BedStatusEnum.BED_STATUS_ALLOCATED.getStatus());
            bedMgmtMapper.updateById(bedMgmt);

            dormAssign.setBedId(bedId);
            dormAssign.setDormNum(bedMgmt.getRoomNum());

            // 更新
            int j = dormAssignMapper.updateById(dormAssign);
            total += j;
        }
        return total > 0;
    }

    @Override
    public DormAssignChartRespVO chart(DormAssignChartReqVO reqVO) {
        DormAssignChartRespVO vo = new DormAssignChartRespVO();

        Integer year = reqVO.getYear();
        if (year == null) {
            year = LocalDateTime.now().getYear();
        }
        // 1. 卡片数据
        vo = dormAssignMapper.selectTotalCount(year);
        // 如果统计为空，则设置为0
        if (vo == null) {
            vo = new DormAssignChartRespVO();
        }
        if (vo.getProgress() == null) {
            vo.setProgress(BigDecimal.ZERO);
        }
        if (vo.getTotalCount() == null) {
            vo.setTotalCount(0);
        }
        if (vo.getFinishedCount() == null) {
            vo.setFinishedCount(0);
        }
        if (vo.getWaitAssignCount() == null) {
            vo.setWaitAssignCount(0);
        }

        // 各楼栋已分配人数列表  //TODO 表中没有楼栋，先按房号统计
        List<JSONObject> buildingAssignCountList = dormAssignMapper.selectBuildingAssignCountList(year);
        List<String> buildingList = new ArrayList<>();
        List<Integer> assignCountList = new ArrayList<>();
        List<Integer> bedCountList = new ArrayList<>();
        for (JSONObject jsonObject : buildingAssignCountList) {
            buildingList.add(jsonObject.getString("building"));
            assignCountList.add(jsonObject.getInteger("assignCount"));
            bedCountList.add(jsonObject.getInteger("bedCount"));
        }
        vo.setBuildingList(buildingList);
        vo.setBuildingAssignCountList(assignCountList);
        vo.setBuildingBedCountList(bedCountList);
        return vo;
    }

    @Override
    public DormAssignIndexRespVO assignIndex(DormAssignChartReqVO reqVO) {
        DormAssignIndexRespVO vo = new DormAssignIndexRespVO();
        Integer year = reqVO.getYear();
        if (year == null) {
            year = LocalDateTime.now().getYear();
        }
        vo = dormAssignMapper.selectIndexCount(year);
        return vo;
    }

}