INSERT INTO `geo_code` (`code`, `location_name`, `area_code`, `layer_type_id`, `beidou_grid_code`, `longitude`, `latitude`, `admin_code`, `unique_code`, `status_id`, `check_result_id`, `rule_enable_flag`, `rule_audit_status_id`, `parent_geo_code_id`, `change_log`, `coord_verify_flag`, `remark`, `creator`, `updater`, `tenant_id`) VALUES
-- 第一级：泉州市级节点
('QZ350500000001', '泉州市', '350500', 'LY000', 'G350500A', 118.675700, 24.874000, '350500', '350500000000001', 'ST001', 'CH001', 1, 'AU001', NULL, '{"create_time":"2024-01-15","creator":"admin"}', 1, '泉州市级地理编码根节点', 'admin', 'admin', 1),

-- 第二级：各区县节点（父级为泉州市）
('QZ350502000001', '鲤城区', '350502', 'LY001', 'G350502B', 118.586500, 24.907600, '350502', '350502000001001', 'ST002', 'CH002', 1, 'AU002', 'QZ350500000001', '{"create_time":"2024-01-16","creator":"admin"}', 1, '鲤城区地理编码节点', 'admin', 'admin', 1),
('QZ350503000001', '丰泽区', '350503', 'LY001', 'G350503C', 118.605000, 24.913000, '350503', '350503000001001', 'ST002', 'CH002', 1, 'AU002', 'QZ350500000001', '{"create_time":"2024-01-16","creator":"admin"}', 1, '丰泽区地理编码节点', 'admin', 'admin', 1),
('QZ350504000001', '洛江区', '350504', 'LY001', 'G350504D', 118.670000, 24.940000, '350504', '350504000001001', 'ST002', 'CH002', 1, 'AU002', 'QZ350500000001', '{"create_time":"2024-01-16","creator":"admin"}', 1, '洛江区地理编码节点', 'admin', 'admin', 1),

-- 第三级：具体地标节点（父级为各区县）
('QZ350502001001', '泉州开元寺', '350502', 'LY002', 'G350502B1', 118.588000, 24.958000, '350502', '350502001001001', 'ST001', 'CH001', 1, 'AU001', 'QZ350502000001', '{"create_time":"2024-01-17","creator":"admin"}', 1, '泉州著名佛教寺院，世界文化遗产', 'admin', 'admin', 1),
('QZ350502002001', '泉州西街钟楼', '350502', 'LY002', 'G350502B2', 118.586500, 24.913200, '350502', '350502002002001', 'ST001', 'CH001', 1, 'AU001', 'QZ350502000001', '{"create_time":"2024-01-17","creator":"admin"}', 1, '泉州地标建筑，位于鲤城区中心', 'admin', 'admin', 1),
('QZ350503003001', '泉州清源山风景区', '350503', 'LY002', 'G350503C1', 118.605000, 24.940000, '350503', '350503003003001', 'ST001', 'CH001', 1, 'AU001', 'QZ350503000001', '{"create_time":"2024-01-18","creator":"admin"}', 1, '泉州5A级旅游景区，道教名山', 'admin', 'admin', 1),
('QZ350504004001', '泉州闽台缘博物馆', '350504', 'LY002', 'G350504D1', 118.595000, 24.950000, '350504', '350504004004001', 'ST001', 'CH001', 1, 'AU001', 'QZ350504000001', '{"create_time":"2024-01-18","creator":"admin"}', 1, '国家级博物馆，位于丰泽区', 'admin', 'admin', 1),

-- 第四级：更细粒度节点（父级为具体地标）
('QZ350502001002', '开元寺大雄宝殿', '350502', 'LY003', 'G350502B1A', 118.588100, 24.958100, '350502', '350502001002001', 'ST003', 'CH003', 1, 'AU003', 'QZ350502001001', '{"create_time":"2024-01-19","creator":"admin"}', 1, '开元寺主殿建筑', 'admin', 'admin', 1),
('QZ350502001003', '开元寺东西塔', '350502', 'LY003', 'G350502B1B', 118.588200, 24.958200, '350502', '350502001003001', 'ST003', 'CH003', 1, 'AU003', 'QZ350502001001', '{"create_time":"2024-01-19","creator":"admin"}', 1, '开元寺标志性建筑', 'admin', 'admin', 1),
('QZ350503003002', '清源山老君岩', '350503', 'LY003', 'G350503C1A', 118.605100, 24.940100, '350503', '350503003002001', 'ST003', 'CH003', 1, 'AU003', 'QZ350503003001', '{"create_time":"2024-01-20","creator":"admin"}', 1, '清源山核心景点，宋代老君造像', 'admin', 'admin', 1),
('QZ350503003003', '清源山天湖', '350503', 'LY003', 'G350503C1B', 118.605200, 24.940200, '350503', '350503003003001', 'ST003', 'CH003', 1, 'AU003', 'QZ350503003001', '{"create_time":"2024-01-20","creator":"admin"}', 1, '清源山人工湖景区', 'admin', 'admin', 1);




-- 管理部件分类表数据
INSERT INTO `part_category` (`name`, `code`, `code_sort_type`, `parent_id`, `parent_name`, `icon_id`, `icon_audit_status_id`, `category_type_id`, `status_id`, `audit_status_id`, `instance_count`, `purpose`, `notify_flag`, `remark`, `creator`, `updater`, `tenant_id`) VALUES
-- 第一级：基础部件分类
('交通设施', 'JTSS', '国标正排', NULL, NULL, 'ICON001', 'AUDIT001', 'CT001', 'STATUS001', 'AUDIT001', 3, '道路、桥梁、交通信号等交通基础设施', 1, '道路交通基础设施分类', 'admin', 'admin', 1),
('市容环境', 'SRHJ', '国标正排', NULL, NULL, 'ICON002', 'AUDIT001', 'CT001', 'STATUS001', 'AUDIT001', 2, '垃圾箱、公厕、绿化等市容环境设施', 1, '市容环境设施分类', 'admin', 'admin', 1),
('公共设施', 'GGSS', '国标正排', NULL, NULL, 'ICON003', 'AUDIT001', 'CT001', 'STATUS001', 'AUDIT001', 2, '公园、广场、文体设施等公共设施', 1, '公共设施分类', 'admin', 'admin', 1);

-- 获取刚刚插入的分类ID，用于后续的父子关系
-- 假设：交通设施 id=1, 市容环境 id=2, 公共设施 id=3

-- 第二级：交通设施子分类
INSERT INTO `part_category` (`name`, `code`, `code_sort_type`, `parent_id`, `parent_name`, `icon_id`, `icon_audit_status_id`, `category_type_id`, `status_id`, `audit_status_id`, `instance_count`, `purpose`, `notify_flag`, `remark`, `creator`, `updater`, `tenant_id`) VALUES
('道路交通设施', 'DLJTSS', '国标正排', 1, '交通设施', 'ICON004', 'AUDIT001', 'CT002', 'STATUS001', 'AUDIT001', 2, '道路及附属交通设施', 1, '道路交通设施子分类', 'admin', 'admin', 1),
('停车场设施', 'TCCSS', '国标正排', 1, '交通设施', 'ICON005', 'AUDIT001', 'CT002', 'STATUS001', 'AUDIT001', 1, '停车场、车位等停车相关设施', 1, '停车场设施子分类', 'admin', 'admin', 1);

-- 假设：道路交通设施 id=4, 停车场设施 id=5

-- 第二级：市容环境子分类
INSERT INTO `part_category` (`name`, `code`, `code_sort_type`, `parent_id`, `parent_name`, `icon_id`, `icon_audit_status_id`, `category_type_id`, `status_id`, `audit_status_id`, `instance_count`, `purpose`, `notify_flag`, `remark`, `creator`, `updater`, `tenant_id`) VALUES
('环卫设施', 'HWSS', '国标正排', 2, '市容环境', 'ICON009', 'AUDIT001', 'CT002', 'STATUS001', 'AUDIT001', 1, '垃圾箱、公厕等环卫设施', 1, '环卫设施子分类', 'admin', 'admin', 1);

-- 假设：环卫设施 id=6

-- 第二级：公共设施子分类
INSERT INTO `part_category` (`name`, `code`, `code_sort_type`, `parent_id`, `parent_name`, `icon_id`, `icon_audit_status_id`, `category_type_id`, `status_id`, `audit_status_id`, `instance_count`, `purpose`, `notify_flag`, `remark`, `creator`, `updater`, `tenant_id`) VALUES
('公园设施', 'GYSS', '扩展倒排', 3, '公共设施', 'ICON011', 'AUDIT001', 'CT002', 'STATUS001', 'AUDIT001', 1, '公园内各类设施', 0, '公园设施子分类', 'admin', 'admin', 1);

-- 假设：公园设施 id=7

-- 第三级：道路交通设施细分
INSERT INTO `part_category` (`name`, `code`, `code_sort_type`, `parent_id`, `parent_name`, `icon_id`, `icon_audit_status_id`, `category_type_id`, `status_id`, `audit_status_id`, `instance_count`, `purpose`, `notify_flag`, `remark`, `creator`, `updater`, `tenant_id`) VALUES
('路灯', 'LD', '国标正排', 4, '道路交通设施', 'ICON006', 'AUDIT002', 'CT003', 'STATUS002', 'AUDIT002', 1, '道路照明设施', 1, '路灯设施', 'admin', 'admin', 1),
('交通信号灯', 'JTXHD', '国标正排', 4, '道路交通设施', 'ICON007', 'AUDIT002', 'CT003', 'STATUS002', 'AUDIT002', 1, '交通信号控制设施', 1, '交通信号灯', 'admin', 'admin', 1);

-- 假设：路灯 id=8, 交通信号灯 id=9

-- 第三级：停车场设施细分
INSERT INTO `part_category` (`name`, `code`, `code_sort_type`, `parent_id`, `parent_name`, `icon_id`, `icon_audit_status_id`, `category_type_id`, `status_id`, `audit_status_id`, `instance_count`, `purpose`, `notify_flag`, `remark`, `creator`, `updater`, `tenant_id`) VALUES
('停车位', 'TCW', '国标正排', 5, '停车场设施', 'ICON008', 'AUDIT002', 'CT003', 'STATUS002', 'AUDIT002', 1, '机动车停车位', 1, '停车位设施', 'admin', 'admin', 1);

-- 假设：停车位 id=10

-- 第三级：环卫设施细分
INSERT INTO `part_category` (`name`, `code`, `code_sort_type`, `parent_id`, `parent_name`, `icon_id`, `icon_audit_status_id`, `category_type_id`, `status_id`, `audit_status_id`, `instance_count`, `purpose`, `notify_flag`, `remark`, `creator`, `updater`, `tenant_id`) VALUES
('垃圾箱', 'LJX', '国标正排', 6, '环卫设施', 'ICON010', 'AUDIT002', 'CT003', 'STATUS002', 'AUDIT002', 1, '生活垃圾收集设施', 1, '垃圾箱设施', 'admin', 'admin', 1);

-- 假设：垃圾箱 id=11

-- 第三级：公园设施细分
INSERT INTO `part_category` (`name`, `code`, `code_sort_type`, `parent_id`, `parent_name`, `icon_id`, `icon_audit_status_id`, `category_type_id`, `status_id`, `audit_status_id`, `instance_count`, `purpose`, `notify_flag`, `remark`, `creator`, `updater`, `tenant_id`) VALUES
('公园长椅', 'GYCY', '扩展倒排', 7, '公园设施', 'ICON012', 'AUDIT002', 'CT003', 'STATUS002', 'AUDIT002', 1, '公园休息设施', 0, '公园长椅', 'admin', 'admin', 1);

-- 假设：公园长椅 id=12


ALTER TABLE part_instance AUTO_INCREMENT = 1;


-- 管理部件实例表数据
INSERT INTO `part_instance` (`part_name`, `unique_code`, `parent_category_id`, `grid_id`, `grid_name`, `longitude`, `latitude`, `coord_verify_flag`, `coordinate`, `run_status`, `dept_name`, `area_code`, `areaName`, `monitor_ids`, `monitor_count`, `event_count`, `remark`, `creator`, `updater`, `tenant_id`) VALUES
-- 路灯实例 (关联分类id=8)
('胜利西路路灯001', '350602LD00100001', 8, 'GRID001', '开元街道网格01', 118.586500, 24.907600, 1, '118.586500,24.907600', 'RUN001', '市城管局照明管理处', '350602', '福建省泉州市鲤城区', '["MON001", "MON002"]', 2, 5, '胜利西路北段第一盏路灯', 'admin', 'admin', 1),
('胜利西路路灯002', '350602LD00200002', 8, 'GRID001', '开元街道网格01', 118.586700, 24.907800, 1, '118.586700,24.907800', 'RUN002', '市城管局照明管理处', '350602', '福建省泉州市鲤城区', '["MON003"]', 1, 2, '胜利西路北段第二盏路灯，近期维修过', 'admin', 'admin', 1),

-- 交通信号灯实例 (关联分类id=9)
('胜利西路钟楼路口信号灯', '350602JTXHD001', 9, 'GRID001', '开元街道网格01', 118.586500, 24.913200, 1, '118.586500,24.913200', 'RUN001', '市交警支队设施科', '350602', '福建省泉州市鲤城区', '["MON004", "MON005", "MON006"]', 3, 8, '胜利西路与钟楼路口交通信号灯', 'admin', 'admin', 1),

-- 停车位实例 (关联分类id=10)
('万达广场地下停车位A001', '350602TCW001A01', 10, 'GRID002', '丰泽街道网格02', 118.595000, 24.915000, 1, '118.595000,24.915000', 'RUN001', '万达物业公司', '350502', '福建省泉州市丰泽区', '["MON007"]', 1, 12, '万达广场地下停车场A区001号车位', 'admin', 'admin', 1),
('万达广场地下停车位A002', '350602TCW002A02', 10, 'GRID002', '丰泽街道网格02', 118.595100, 24.915100, 1, '118.595100,24.915100', 'RUN002', '万达物业公司', '350502', '福建省泉州市丰泽区', '["MON008"]', 1, 5, '万达广场地下停车场A区002号车位，传感器故障', 'admin', 'admin', 1),

-- 垃圾箱实例 (关联分类id=11)
('中山公园北门垃圾箱', '350602LJX0010001', 11, 'GRID003', '海滨街道网格03', 118.588000, 24.910000, 1, '118.588000,24.910000', 'RUN001', '市环卫处', '350602', '福建省泉州市鲤城区', '["MON009"]', 1, 3, '中山公园北门分类垃圾箱', 'admin', 'admin', 1),
('中山公园南门垃圾箱', '350602LJX0020002', 11, 'GRID003', '海滨街道网格03', 118.588200, 24.909800, 0, '118.588200,24.909800', 'RUN003', '市环卫处', '350602', '福建省泉州市鲤城区', '[]', 0, 1, '中山公园南门垃圾箱，坐标待校准', 'admin', 'admin', 1),

-- 公园长椅实例 (关联分类id=12)
('中山公园东区长椅001', '350602GYCY00101', 12, 'GRID003', '海滨街道网格03', 118.588500, 24.910500, 1, '118.588500,24.910500', 'RUN001', '市园林局', '350602', '福建省泉州市鲤城区', '["MON010"]', 1, 0, '中山公园东区第一张长椅', 'admin', 'admin', 1),
('中山公园西区长椅001', '350602GYCY00201', 12, 'GRID003', '海滨街道网格03', 118.587500, 24.910300, 1, '118.587500,24.910300', 'RUN002', '市园林局', '350602', '福建省泉州市鲤城区', '["MON011"]', 1, 2, '中山公园西区第一张长椅，近期维修过', 'admin', 'admin', 1),

-- 停车场实例 (关联分类id=5，停车场设施)
('万达广场停车场', '350602TCC001001', 5, 'GRID002', '丰泽街道网格02', 118.595000, 24.915000, 1, '118.595000,24.915000', 'RUN001', '万达物业公司', '350502', '福建省泉州市丰泽区', '["MON012", "MON013", "MON014"]', 3, 25, '万达广场地下停车场整体设施', 'admin', 'admin', 1);



-- 管理事项分类表数据
INSERT INTO `matter_category` (`matter_category_id`, `category_name`, `category_code`, `parent_id`, `parent_name`, `dept_id`, `dept_name`, `deal_limit`, `workflow_id`, `workflow_code`, `workflow_desc`, `category_type_id`, `category_type_name`, `status_name`, `audit_status_name`, `related_matter_count`, `purpose`, `remark`, `creator`, `updater`, `tenant_id`) VALUES
-- 第一级：基础事项分类
('CAT001', '市容环境', 'SRHJ', NULL, NULL, 'DEPT001', '市城管局', 24, 'WF001', 'SRHJ001', '市容环境问题处理标准流程', 'CT001', '基础事项', '启用', '已审核', 4, '市容环境类问题管理', '市容环境卫生管理事项分类', 'admin', 'admin', 1),
('CAT002', '交通秩序', 'JTZX', NULL, NULL, 'DEPT002', '市交警支队', 12, 'WF002', 'JTZX001', '交通秩序问题处理流程', 'CT001', '基础事项', '启用', '已审核', 3, '交通秩序管理事项', '道路交通秩序管理分类', 'admin', 'admin', 1),
('CAT003', '市政设施', 'SZSS', NULL, NULL, 'DEPT003', '市住建局', 48, 'WF003', 'SZSS001', '市政设施维护管理流程', 'CT001', '基础事项', '启用', '已审核', 3, '市政设施维护管理', '市政基础设施维护分类', 'admin', 'admin', 1),

-- 第二级：市容环境子分类
('CAT004', '市容秩序', 'SRZX', 'CAT001', '市容环境', 'DEPT001', '市城管局', 12, 'WF004', 'SRZX001', '市容秩序问题快速处置流程', 'CT002', '子项事项', '启用', '已审核', 2, '市容秩序问题管理', '占道经营、乱堆乱放等市容问题', 'admin', 'admin', 1),
('CAT005', '环境卫生', 'HJWS', 'CAT001', '市容环境', 'DEPT004', '市环卫处', 24, 'WF005', 'HJWS001', '环境卫生问题处理流程', 'CT002', '子项事项', '启用', '已审核', 2, '环境卫生问题管理', '垃圾清扫、公厕保洁等环卫问题', 'admin', 'admin', 1),

-- 第二级：交通秩序子分类
('CAT006', '车辆违停', 'CLWT', 'CAT002', '交通秩序', 'DEPT002', '市交警支队', 4, 'WF006', 'CLWT001', '车辆违停处置流程', 'CT002', '子项事项', '启用', '已审核', 2, '车辆违停处理', '机动车、非机动车违停问题', 'admin', 'admin', 1),
('CAT007', '交通设施', 'JTSS', 'CAT002', '交通秩序', 'DEPT005', '市政工程处', 24, 'WF007', 'JTSS001', '交通设施维护流程', 'CT002', '子项事项', '启用', '已审核', 1, '交通设施维护', '信号灯、标志标线等设施维护', 'admin', 'admin', 1),

-- 第二级：市政设施子分类
('CAT008', '道路设施', 'DLSS', 'CAT003', '市政设施', 'DEPT003', '市住建局', 36, 'WF008', 'DLSS001', '道路设施维护流程', 'CT002', '子项事项', '启用', '已审核', 2, '道路设施维护', '路面、人行道等道路设施问题', 'admin', 'admin', 1),
('CAT009', '照明设施', 'ZMSS', 'CAT003', '市政设施', 'DEPT006', '市照明管理处', 24, 'WF009', 'ZMSS001', '照明设施维护流程', 'CT002', '子项事项', '启用', '已审核', 1, '照明设施维护', '路灯、景观灯等照明设施问题', 'admin', 'admin', 1),

-- 第三级：市容秩序细分
('CAT010', '占道经营', 'ZDJY', 'CAT004', '市容秩序', 'DEPT001', '市城管局', 6, 'WF010', 'ZDJY001', '占道经营快速处置流程', 'CT003', '具体事项', '启用', '已审核', 2, '占道经营管理', '流动摊贩、店外经营等问题', 'admin', 'admin', 1);



-- 管理事项实例表数据
INSERT INTO `matter_instance` (`matter_instance_id`, `name`, `unique_code`, `category_id`, `category_name`, `parent_category_id`, `location`, `grid_id`, `grid_name`, `description`, `status_id`, `status_name`, `dept_id`, `dept_name`, `attachment_info`, `part_ids`, `part_count`, `timeout_flag`, `timeout_duration`, `deal_opinion`, `create_by`, `creator_name`, `matter_create_time`, `deal_by`, `handler_name`, `deal_time`, `remark`, `creator`, `updater`, `tenant_id`) VALUES
-- 占道经营事项 (关联分类CAT010)
('MAT001', '西街流动摊贩占道经营', '350502ZD001', 'CAT010', '占道经营', 'CAT004', '泉州市鲤城区西街钟楼路口', 'GRID001', '开元街道网格01', '西街钟楼路口有多处流动摊贩占用机动车道经营，影响交通通行', 'ST001', '已受理', 'DEPT001', '市城管局', '["photo_001.jpg", "photo_002.jpg"]', '[]', 0, 0, 0, '已劝导摊贩撤离，恢复道路通行', 'USER001', '网格员张三', '2024-01-25 14:30:00', 'USER101', '城管队员李四', '2024-01-25 15:00:00', '西街旅游区占道经营问题，已及时处置', 'admin', 'admin', 1),
('MAT002', '新华北路店外经营', '350503ZD002', 'CAT010', '占道经营', 'CAT004', '泉州市丰泽区新华北路98号', 'GRID002', '丰泽街道网格02', '新华北路多家店铺将商品摆放到店外人行道经营，影响行人通行', 'ST002', '处置中', 'DEPT001', '市城管局', '["photo_003.jpg"]', '[]', 0, 1, 120, '已下达整改通知书，限今日内整改', 'USER002', '网格员王五', '2024-01-25 10:00:00', NULL, NULL, NULL, '商业区店外经营问题，需加强巡查', 'admin', 'admin', 1),

-- 环境卫生事项 (关联分类CAT005)
('MAT003', '中山公园垃圾堆积', '350502HJ001', 'CAT005', '环境卫生', 'CAT001', '泉州市鲤城区中山公园北门', 'GRID003', '海滨街道网格03', '中山公园北门垃圾箱满溢，周边有垃圾堆积，影响环境卫生', 'ST001', '已处置', 'DEPT004', '市环卫处', '["photo_004.jpg", "photo_005.jpg"]', '["11"]', 1, 0, 0, '已安排环卫工人及时清运，并增加清运频次', 'USER003', '市民陈先生', '2024-01-25 08:30:00', 'USER102', '环卫工赵六', '2024-01-25 09:00:00', '节假日公园垃圾量增大，需加强清运', 'admin', 'admin', 1),
('MAT004', '清源山公厕卫生问题', '350503HJ002', 'CAT005', '环境卫生', 'CAT001', '泉州市丰泽区清源山景区入口公厕', 'GRID004', '清源街道网格04', '清源山景区入口公厕卫生状况差，有异味，设施损坏', 'ST002', '处置中', 'DEPT004', '市环卫处', '["photo_006.jpg"]', '[]', 0, 0, 0, '已安排保洁人员清洁消毒，维修损坏设施', 'USER004', '游客张女士', '2024-01-25 11:20:00', NULL, NULL, NULL, '景区公厕需加强日常维护', 'admin', 'admin', 1),

-- 车辆违停事项 (关联分类CAT006)
('MAT005', '万达广场周边车辆违停', '350502WT001', 'CAT006', '车辆违停', 'CAT002', '泉州市丰泽区万达广场金街', 'GRID002', '丰泽街道网格02', '万达广场金街有多辆机动车占用消防通道违停', 'ST003', '已结案', 'DEPT002', '市交警支队', '["photo_007.jpg", "photo_008.jpg", "video_001.mp4"]', '["5"]', 1, 0, 0, '已对违停车辆进行处罚，拖移严重阻塞车辆', 'USER005', '保安刘师傅', '2024-01-24 18:00:00', 'USER103', '交警王警官', '2024-01-24 18:30:00', '商业区高峰时段违停问题', 'admin', 'admin', 1),
('MAT006', '泉州师范学院门口共享单车乱停放', '350503WT002', 'CAT006', '车辆违停', 'CAT002', '泉州市丰泽区泉州师范学院正门', 'GRID005', '东湖街道网格05', '泉州师范学院正门共享单车大量堆积，占用行人通道', 'ST001', '已受理', 'DEPT002', '市交警支队', '["photo_009.jpg"]', '[]', 0, 1, 180, '已通知共享单车公司调度车辆，规范停放', 'USER006', '学生李同学', '2024-01-25 09:00:00', 'USER104', '交警林警官', '2024-01-25 12:00:00', '校园周边共享单车管理问题', 'admin', 'admin', 1),

-- 交通设施事项 (关联分类CAT007)
('MAT007', '胜利西路信号灯故障', '350502SS001', 'CAT007', '交通设施', 'CAT002', '泉州市鲤城区胜利西路钟楼路口', 'GRID001', '开元街道网格01', '胜利西路与钟楼路口南北向信号灯不亮，存在安全隐患', 'ST001', '已处置', 'DEPT005', '市政工程处', '["photo_010.jpg"]', '["9"]', 1, 0, 0, '已修复信号灯线路故障，恢复正常运行', 'USER007', '网格员周七', '2024-01-24 20:00:00', 'USER105', '维修工吴八', '2024-01-25 08:00:00', '交通信号灯及时维修，保障交通安全', 'admin', 'admin', 1),

-- 道路设施事项 (关联分类CAT008)
('MAT008', '东街人行道地砖破损', '350502DL001', 'CAT008', '道路设施', 'CAT003', '泉州市鲤城区东街中段', 'GRID001', '开元街道网格01', '东街中段人行道多处地砖破损、松动，存在安全隐患', 'ST002', '处置中', 'DEPT003', '市住建局', '["photo_011.jpg", "photo_012.jpg"]', '[]', 0, 0, 0, '已设置警示标识，安排维修队伍进行修补', 'USER008', '市民郑女士', '2024-01-25 14:00:00', NULL, NULL, NULL, '人行道设施老化，需全面排查', 'admin', 'admin', 1),
('MAT009', '刺桐路路面塌陷', '350503DL002', 'CAT008', '道路设施', 'CAT003', '泉州市丰泽区刺桐路万达段', 'GRID002', '丰泽街道网格02', '刺桐路万达段路面出现塌陷，面积约2平方米，深度约30厘米', 'ST001', '已处置', 'DEPT003', '市住建局', '["photo_013.jpg", "video_002.mp4"]', '[]', 0, 1, 240, '已封闭危险区域，完成路面修复', 'USER009', '出租车司机王师傅', '2024-01-24 22:00:00', 'USER106', '施工队陈工', '2024-01-25 10:00:00', '道路塌陷应急抢修', 'admin', 'admin', 1),

-- 照明设施事项 (关联分类CAT009)
('MAT010', '江滨北路路灯不亮', '350502ZM001', 'CAT009', '照明设施', 'CAT003', '泉州市鲤城区江滨北路防洪堤段', 'GRID006', '临江街道网格06', '江滨北路防洪堤段连续5盏路灯不亮，影响夜间照明', 'ST001', '已处置', 'DEPT006', '市照明管理处', '["photo_014.jpg"]', '["8"]', 1, 0, 0, '已更换损坏的LED灯管，恢复照明', 'USER010', '夜跑爱好者孙先生', '2024-01-24 19:30:00', 'USER107', '电工钱师傅', '2024-01-25 09:30:00', '沿江步道照明设施维护', 'admin', 'admin', 1);






















