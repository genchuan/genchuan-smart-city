<!-- src/components/ThingsBoardWsExample.vue -->
<template>
  <div class="ws-example">

    <div class="connection-status">
      <p>连接状态:
        <span :class="isConnected ? 'connected' : 'disconnected'">
          {{ isConnected ? '已连接' : '未连接' }}
        </span>
      </p>
      <p v-if="connectionError" class="error-message">{{ connectionError }}</p>
    </div>

    <div class="controls">
      <input
          v-model="accessToken"
          placeholder="输入设备访问令牌"
          class="token-input"
      />
      <button @click="handleConnect" :disabled="isConnected">
        连接
      </button>
      <button @click="handleDisconnect" :disabled="!isConnected">
        断开连接
      </button>
    </div>

<!--    <div class="telemetry-controls" v-if="isConnected">
      <h3>发送遥测数据</h3>
      <div class="telemetry-inputs">
        <div>
          <label>温度: </label>
          <input v-model.number="temperature" type="number" step="0.1" />
        </div>
        <div>
          <label>湿度: </label>
          <input v-model.number="humidity" type="number" step="0.1" />
        </div>
        <button @click="sendTelemetryData">发送</button>
      </div>
    </div>-->

    <div class="received-data" v-if="receivedTelemetry">
      <h3>收到的遥测数据</h3>
      <pre>{{ aa }}</pre>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useThingsBoardWs } from './thingsboardWsService';

// 引入WebSocket服务
const {
  isConnected,
  connectionError,
  receivedTelemetry,
  connect,
  disconnect,
  sendTelemetry,
  subscribeToDeviceTelemetry
} = useThingsBoardWs();

let aa=ref([]);
watch(receivedTelemetry, (newVal, oldVal) => {
  console.log(`count变化了：${oldVal} → ${newVal}`)
  aa.value.push(newVal)
  console.log(aa.value)
})




// 组件状态
const accessToken = ref(window.localStorage.getItem('thingsBoardJwt_token')); // 设备的访问令牌
const temperature = ref(25.0);
const humidity = ref(60.0);

// 处理连接
const handleConnect = () => {
  if (accessToken.value) {
    connect(accessToken.value);
    // 如果需要订阅其他设备数据，可以在这里调用
    // subscribeToDeviceTelemetry('目标设备ID');
  }
};

// 处理断开连接
const handleDisconnect = () => {
  disconnect();
};

// 发送遥测数据
/*const sendTelemetryData = () => {
  const data = {
    temperature: temperature.value,
    humidity: humidity.value
  };
  const success = sendTelemetry(data);
  if (success) {
    console.log('遥测数据已发送:', data);
  } else {
    console.error('发送失败，连接未建立');
  }
};*/
</script>

<style scoped>
.ws-example {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
}

.connection-status {
  margin: 15px 0;
  padding: 10px;
  background-color: #f5f5f5;
  border-radius: 4px;
}

.connected {
  color: green;
  font-weight: bold;
}

.disconnected {
  color: red;
}

.error-message {
  color: red;
  margin: 10px 0;
}

.controls {
  margin: 15px 0;
  display: flex;
  gap: 10px;
  align-items: center;
}

.token-input {
  flex-grow: 1;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 8px 16px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.telemetry-controls {
  margin: 20px 0;
  padding: 15px;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
}

.telemetry-inputs {
  display: flex;
  gap: 15px;
  margin: 10px 0;
  align-items: center;
}

.telemetry-inputs input {
  width: 100px;
  padding: 5px;
}

.received-data {
  margin: 20px 0;
  padding: 15px;
  background-color: #f9f9f9;
  border-radius: 4px;
}

pre {
  white-space: pre-wrap;
  word-wrap: break-word;
}
</style>
