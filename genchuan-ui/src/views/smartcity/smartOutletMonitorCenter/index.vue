<template>
  <div class="smart-drainage-layout">
    <!-- 左侧树形导航 -->
    <div class="left-panel">
      <el-input
        v-model="searchName"
        placeholder="请输入排口名称搜索"
        clearable
        class="mb-10"
      >
        <template #prefix>
          <Icon icon="ep:search" />
        </template>
      </el-input>

      <el-tree
        ref="treeRef"
        :data="treeData"
        :props="defaultProps"
        :filter-node-method="filterNode"
        default-expand-all
        highlight-current
        node-key="id"
        @node-click="handleNodeClick"
      />
    </div>

    <!-- 右侧视频预览区域 -->
    <div class="right-panel">
      <div class="video-preview-container">
        <!-- 视频展示窗口 -->
        <div id="playWnd" class="playWnd"></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted, nextTick } from 'vue'
import { ElTree } from 'element-plus'

// ================== 静态监控点数据 ==================
const CAMERA_MAPPING = {
  '余坊桥排口': '9413ff15783140a8bcf2c9806da7a28c',
  '八斗种排口': '915e475943be42d9967d92af3591dfb2',
  '八号公路蝶景湾对面河岸排口': '43be116c75ab4f15b5b61d93ea448680',
  '八号公路山松对面河岸排口': '6b41501351554e6fb11a53cfedf9d407',
  '八号公路富丽帝景对面河岸排口': 'ec5b7135934c4fe6b2fd63c306eb0487',
  '八号公路富丽帝景对面河岸2排口': '32b5c94cf5f941cdb7dd8f02900e387c',
  '丽景香江背后河岸排口': 'f830186c387b46eda49afb22fe454db1',
  '西岗小区排口': 'f10420fa63804e90ac163d733decaa59',
  '新护桥排口': 'a9026e6d4382419cabb8fded1b4de5e6',
  '观静山排口': '63d093d620a34a34b7f0e27b1e65d870',
  '水岸帝景背后排口': 'd39da00fced8451589d31b933b6fff6d',
  '水泥厂生活区排口': '97853876442e4437aaafe9a95932b669',
  '水南市场排口': 'ece2d1bf8fbe4a3aba615cef6333c533',
  '碧水湾小区排口': '2233d6ada832402e8011d92d529ee7a8',
  '环卫所背后排口': '639e01d8d4744638ac46e6df7de8f9f7',
  '贮木场背后排口': '0e2de9853be84913bbc43cc8348bad6e'
}

// ========== 构造树形结构 ==========
const treeData = ref([
  {
    id: 1,
    label: '顺昌城管智慧排口',
    children: Object.keys(CAMERA_MAPPING).map((name, index) => ({
      id: index + 2,
      label: name,
      cameraCode: CAMERA_MAPPING[name]
    }))
  }
])

const defaultProps = { label: 'label', children: 'children' }
const searchName = ref('')
const treeRef = ref()

watch(searchName, val => treeRef.value.filter(val))
function filterNode(value, data) {
  if (!value) return true
  return data.label.includes(value)
}

// ================== 视频播放逻辑 ==================
let oWebControl = null
let pubKey = ''
let initCount = 0
const cameraIndexCode = ref('')

function handleNodeClick(node) {
  if (node.cameraCode) {
    cameraIndexCode.value = node.cameraCode
    startPreview()
  }
}

// 初始化插件
onMounted(async () => {
  initPlugin()
  window.addEventListener('resize', handleResize)
  window.addEventListener('scroll', handleResize)

  // 默认选中第一个排口并播放视频
  await nextTick()
  const firstChild = treeData.value[0]?.children?.[0]
  if (firstChild) {
    cameraIndexCode.value = firstChild.cameraCode
    // 设置树形默认高亮选中
    treeRef.value.setCurrentKey(firstChild.id)
    startPreview()
  }
})

onUnmounted(() => {
  if (oWebControl) {
    oWebControl.JS_HideWnd()
    oWebControl.JS_Disconnect()
  }
  window.removeEventListener('resize', handleResize)
  window.removeEventListener('scroll', handleResize)
})

function initPlugin() {
  oWebControl = new WebControl({
    szPluginContainer: 'playWnd',
    iServicePortStart: 15900,
    iServicePortEnd: 15900,
    szClassId: '23BF3B0A-2C56-4D97-9C03-0CB103AA8F11',
    cbConnectSuccess: () => {
      oWebControl
        .JS_StartService('window', { dllPath: './VideoPluginConnect.dll' })
        .then(() => {
          oWebControl.JS_CreateWnd('playWnd', 1000, 600).then(() => {
            init()
          })
        })
    },
    cbConnectError: handleConnectError,
    cbConnectClose: handleConnectError
  })
}

function handleConnectError() {
  oWebControl = null
  const playWnd = document.getElementById('playWnd')
  playWnd.innerHTML = '插件未启动，正在尝试启动，请稍候...'
  WebControl.JS_WakeUp('VideoWebPlugin://')
  initCount++
  if (initCount < 3) {
    setTimeout(() => initPlugin(), 3000)
  } else {
    playWnd.innerHTML = '插件启动失败，请检查插件是否安装！'
  }
}

function init() {
  getPubKey(() => {
    const appkey = '26384193'
    const secret = setEncrypt('cF8bcvtd00v7c3BQfL4o')
    const ip = '112.5.87.163'
    const port = 1443
    const snapDir = 'D:\\SnapDir'
    const videoDir = 'D:\\VideoDir'
    const layout = '1x1'

    oWebControl.JS_RequestInterface({
      funcName: 'init',
      argument: JSON.stringify({
        appkey,
        secret,
        ip,
        port,
        playMode: 0,
        snapDir,
        videoDir,
        layout,
        enableHTTPS: 1,
        encryptedFields: 'secret',
        showToolbar: 1,
        showSmart: 1
      })
    })
  })
}

function getPubKey(callback) {
  oWebControl.JS_RequestInterface({
    funcName: 'getRSAPubKey',
    argument: JSON.stringify({ keyLength: 1024 })
  }).then(oData => {
    if (oData.responseMsg?.data) {
      pubKey = oData.responseMsg.data
      callback()
    }
  })
}

function setEncrypt(value) {
  const encrypt = new JSEncrypt()
  encrypt.setPublicKey(pubKey)
  return encrypt.encrypt(value)
}

function handleResize() {
  if (oWebControl) {
    oWebControl.JS_Resize(1000, 600)
  }
}

function startPreview() {
  const code = cameraIndexCode.value.trim()
  if (!code) return alert('请选择一个排口')

  oWebControl.JS_RequestInterface({
    funcName: 'startPreview',
    argument: JSON.stringify({
      cameraIndexCode: code,
      streamMode: 0,
      transMode: 1,
      gpuMode: 0,
      wndId: -1
    })
  })
}

function stopAllPreview() {
  oWebControl.JS_RequestInterface({ funcName: 'stopAllPreview' })
}
</script>

<style scoped>
.smart-drainage-layout {
  display: flex;
  width: 100%;
  height: 100%;
  background: #f9fafc;
}
.left-panel {
  width: 280px;
  background: #fff;
  border-right: 1px solid #e5e5e5;
  padding: 16px;
  overflow-y: auto;
}
.right-panel {
  flex: 1;
  padding: 20px;
}
.video-preview-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.playWnd {
  margin-top: 20px;
  width: 1000px;
  height: 600px;
  border: 1px solid #ccc;
}
.module {
  width: 360px;
  padding: 12px;
  background: #fff;
  border: 1px solid #eee;
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
}
.item {
  margin-bottom: 10px;
}
.btn {
  background-color: #409eff;
  color: #fff;
  border: none;
  padding: 6px 12px;
  border-radius: 4px;
  cursor: pointer;
}
.btn:hover {
  background-color: #66b1ff;
}
</style>
