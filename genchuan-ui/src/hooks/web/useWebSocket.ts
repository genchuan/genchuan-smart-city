import { ref, onMounted, onUnmounted, Ref } from 'vue';

interface UseWebSocketOptions {
  autoReconnect?: boolean; // 是否自动重连
  reconnectInterval?: number; // 重连间隔(ms)
  maxReconnectAttempts?: number; // 最大重连次数
}

/**
 * WebSocket 钩子函数，用于处理实时通信
 * @param url WebSocket 连接地址
 * @param options 配置项（自动重连等）
 * @returns 连接状态、消息、发送方法等
 */
export function useWebSocket(
  url: string,
  options: UseWebSocketOptions = {}
) {
  // 默认配置
  const {
    autoReconnect = true,
    reconnectInterval = 3000,
    maxReconnectAttempts = 10
  } = options;

  // 状态管理
  const socket: Ref<WebSocket | null> = ref(null);
  const isConnected: Ref<boolean> = ref(false);
  const message: Ref<any> = ref(null); // 接收的消息
  const reconnectAttempts: Ref<number> = ref(0); // 重连次数
  const error: Ref<Error | null> = ref(null); // 错误信息

  // 创建连接
  const connect = () => {
    // 关闭现有连接
    if (socket.value) {
      socket.value.close();
    }

    // 创建新连接
    socket.value = new WebSocket(url);

    // 连接成功
    socket.value.onopen = () => {
      console.log('WebSocket 连接成功');
      isConnected.value = true;
      reconnectAttempts.value = 0; // 重置重连次数
      error.value = null;
    };

    // 接收消息
    socket.value.onmessage = (event) => {
      try {
        // 尝试解析 JSON 格式消息
        message.value = JSON.parse(event.data);
      } catch (e) {
        // 非 JSON 格式直接返回
        message.value = event.data;
      }
    };

    // 连接错误
    socket.value.onerror = (evt) => {
      console.error('WebSocket 错误:', evt);
      error.value = new Error('WebSocket 连接错误');
    };

    // 连接关闭
    socket.value.onclose = (event) => {
      console.log(`WebSocket 关闭: ${event.code} - ${event.reason}`);
      isConnected.value = false;

      // 自动重连逻辑
      if (autoReconnect && reconnectAttempts.value < maxReconnectAttempts) {
        reconnectAttempts.value++;
        console.log(`正在尝试第 ${reconnectAttempts.value} 次重连...`);
        setTimeout(connect, reconnectInterval);
      }
    };
  };

  // 发送消息
  const sendMessage = (data: any) => {
    if (!socket.value || !isConnected.value) {
      console.error('WebSocket 未连接，无法发送消息');
      return false;
    }

    try {
      // 发送 JSON 字符串
      socket.value.send(JSON.stringify(data));
      return true;
    } catch (e) {
      console.error('发送消息失败:', e);
      return false;
    }
  };

  // 手动关闭连接
  const close = () => {
    if (socket.value) {
      socket.value.close(1000, '手动关闭连接');
    }
  };

  // 组件挂载时连接
  onMounted(() => {
    connect();
  });

  // 组件卸载时关闭连接
  onUnmounted(() => {
    close();
  });

  return {
    socket,
    isConnected,
    message,
    error,
    connect,
    sendMessage,
    close
  };
}
