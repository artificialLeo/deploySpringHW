import React, { useEffect } from 'react';
import Dashboard from "../../material/Layout/Dashboard";
import {Client} from '@stomp/stompjs';

const SOCKET_URL = "ws://localhost:9000/ws-message";

const App = () => {

    useEffect(() => {
        const onConnected = () => {
            console.log("Connected websocket")
            console.log("Subscribe on /topic/message");
            client.subscribe('/account/changes', function (msg) {
                console.log("Received message: ", JSON.parse(msg.body))

            });
        }

        const onDisconnected = () => {
            console.log("Disconnected websocket!")
        }

        const client = new Client({
            brokerURL: SOCKET_URL,
            reconnectDelay: 5000,
            heartbeatIncoming: 4000,
            heartbeatOutgoing: 4000,
            onConnect: onConnected,
            onDisconnect: onDisconnected
        });

        client.activate();
    }, [])

    return (
        <div>
            <Dashboard/>
        </div>
    );
};

export default App;
