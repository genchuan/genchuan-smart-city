// src/services/thingsboardWsService.js
import {ref, onMounted, onUnmounted} from 'vue';

export function useThingsBoardWs() {
    // 状态管理
    const isConnected = ref(false);  //是否链接
    const connectionError = ref(null); //错误信息
    const ws = ref(null);
    const receivedTelemetry = ref(null);//收到的遥测数据


    // 连接WebSocket
    const connect = (accessToken) => {
        // 关闭现有连接
        if (ws.value) {
            ws.value.close();
        }

        // 创建新连接
        ws.value = new WebSocket('ws://127.0.0.1:8080/api/ws');

        // 连接打开时
        ws.value.onopen = () => {
            console.log('WebSocket连接已打开');
            isConnected.value = true;
            connectionError.value = null;

            // 发送认证消息
            authenticate(accessToken);
        };

        // 接收消息时
        ws.value.onmessage = (event) => {
            const message = JSON.parse(event.data);
            console.log('收到消息:', message);

            // 处理遥测数据
            /*if (message.action === 'telemetry') {
                receivedTelemetry.value = message;
            }*/

            if (message.update) {
                receivedTelemetry.value = message;
            }

            // 处理认证响应
            if (message.action === 'auth' && message.status !== 'success') {
                connectionError.value = '认证失败: ' + (message.error || '未知错误');
            }
        };

        // 连接关闭时
        ws.value.onclose = (event) => {
            console.log(`WebSocket连接已关闭，代码: ${event.code}`);
            isConnected.value = false;
            // 可以在这里实现自动重连逻辑
        };

        // 连接错误时
        ws.value.onerror = (error) => {
            console.error('WebSocket错误:', error);
            connectionError.value = '连接错误: ' + error.message;
        };
    };

    // 发送认证消息
    const authenticate = (accessToken) => {
        if (ws.value && isConnected.value) {
            const authMessage = {
                "cmds": [{
                    "type": "ENTITY_DATA",
                    "query": {
                        "entityFilter": {
                            "type": "singleEntity",
                            "singleEntity": {
                                "entityType": "DEVICE",
                                "id": "e8325940-6db4-11f0-bd39-2dc5ccdb39ef"
                            }
                        },
                        "pageLink": {
                            "pageSize": 1024,
                            "page": 0,
                            "sortOrder": {
                                "key": {
                                    "type": "ENTITY_FIELD",
                                    "key": "createdTime"
                                },
                                "direction": "DESC"
                            }
                        },
                        "entityFields": [
                            {
                                "type": "ENTITY_FIELD",
                                "key": "name"
                            },
                            {
                                "type": "ENTITY_FIELD",
                                "key": "label"
                            },
                            {
                                "type": "ENTITY_FIELD",
                                "key": "additionalInfo"
                            }
                        ],
                        "latestValues": []
                    },
                    "cmdId": 1
                }],
                "authCmd": {
                    "cmdId": 0,
                    "token": accessToken
                }
            };
            ws.value.send(JSON.stringify(authMessage));

            const authMessage2 = {
                "cmds": [{
                    "type": "ENTITY_DATA",
                    "cmdId": 1,
                    "tsCmd": {
                        "keys": [
                            "流量",
                            "湿度",
                            "液位",
                            "雨量"
                        ],
                        "startTs": 1753081454021,
                        "timeWindow": 2592000000,
                        "interval": 1000,
                        "intervalType": "MILLISECONDS",
                        "limit": 200,
                        "timeZoneId": "Asia/Shanghai",
                        "agg": "NONE"
                    }
                }],
            };
            ws.value.send(JSON.stringify(authMessage2));

        }
    };


    // 发送遥测数据 没测试
    const sendTelemetry = (telemetryData, timestamp) => {
        if (ws.value && isConnected.value) {
            const telemetryMessage = {
                action: 'telemetry',
                ts: timestamp || Date.now(), // 可选时间戳，默认当前时间
                values: telemetryData
            };
            ws.value.send(JSON.stringify(telemetryMessage));
            return true;
        }
        return false;
    };

    // 订阅设备遥测（用于客户端）没测试
    const subscribeToDeviceTelemetry = (deviceId) => {
        if (ws.value && isConnected.value) {
            const subscribeMessage = {
                action: 'subscribeToTelemetry',
                entityType: 'DEVICE',
                entityId: deviceId,
                keys: ['temperature', 'humidity'], // 要订阅的遥测键
                scope: 'LATEST_TELEMETRY'
            };
            ws.value.send(JSON.stringify(subscribeMessage));
        }
    };

    // 关闭连接
    const disconnect = () => {
        if (ws.value) {
            ws.value.close();
            ws.value = null;
        }
    };

    // 组件卸载时关闭连接
    onUnmounted(() => {
        disconnect();
    });

    return {
        isConnected,
        connectionError,
        receivedTelemetry,
        connect,
        disconnect,
        sendTelemetry,
        subscribeToDeviceTelemetry
    };
}
