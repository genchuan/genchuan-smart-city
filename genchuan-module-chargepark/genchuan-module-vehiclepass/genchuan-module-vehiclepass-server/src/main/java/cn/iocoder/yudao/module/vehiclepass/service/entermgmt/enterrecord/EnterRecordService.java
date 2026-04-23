package cn.iocoder.yudao.module.vehiclepass.service.entermgmt.enterrecord;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo.*;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.enterrecord.EnterRecordDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.apache.ibatis.annotations.Param;

/**
 * 入场记录 Service 接口
 *
 * @author 亘川智城
 */
public interface EnterRecordService {

    /**
     * 创建入场记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRecord(@Valid EnterRecordSaveReqVO createReqVO);

    /**
     * 更新入场记录
     *
     * @param updateReqVO 更新信息
     */
    void updateRecord(@Valid EnterRecordSaveReqVO updateReqVO);

    /**
     * 删除入场记录
     *
     * @param id 编号
     */
    void deleteRecord(Long id);

    /**
     * 批量删除入场记录
     *
     * @param ids 编号
     */
    void deleteRecordListByIds(List<Long> ids);

    /**
     * 获得入场记录
     *
     * @param id 编号
     * @return 入场记录
     */
    EnterRecordDO getRecord(Long id);

    /**
     * 获得入场记录分页
     *
     * @param pageReqVO 分页查询
     * @return 入场记录分页
     */
    PageResult<EnterRecordDO> getRecordPage(EnterRecordPageReqVO pageReqVO);

    /**
     * 获得入场记录分页
     *
     * @param reqVO 分页查询
     * @return 入场记录分页
     */
    PageResult<MyEnterRecordRespVO> getEnterRecordPage(MyEnterRecordPageReqVO reqVO);

    /**
     * 创建入场记录（人工补录）
     */
    Boolean createEnterRecord(EnterRecordCreateReqVO createReqVO);
    /**
     * 修改入场记录（人工补录）
     */
    Boolean updateEnterRecord(EnterRecordUpdateReqVO updateReqVO);
    /**
     * 获取入场记录图表
     */
    EnterRecordChartRespVO getChart(EnterRecordChartReqVO reqVO);

}