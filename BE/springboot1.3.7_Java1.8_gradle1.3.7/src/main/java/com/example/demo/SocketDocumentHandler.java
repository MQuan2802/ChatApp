package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class SocketDocumentHandler extends BinaryWebSocketHandler {

    public static final Logger logger = LoggerFactory.getLogger(SocketImageHandler.class);

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
        logger.info("New Binary Message of Document Received");

        ByteBuffer payload = message.getPayload().asReadOnlyBuffer();
        byte opCode = payload.get();

        // Check if the message is binary (0x82 is binary message)
        if ((opCode & 0x0F) == 0x02) {
            // Get the payload length
            int payloadLength = payload.get() & 0xFF; // Convert to unsigned byte

            // Check if payload length indicates extended length
            if (payloadLength == 126) {
                payloadLength = payload.getShort() & 0xFFFF; // Convert to unsigned short
            } else if (payloadLength == 127) {
                payloadLength = (int) payload.getLong();
            }

            // Read the file name bytes
            byte[] fileNameBytes = new byte[payloadLength];
            payload.get(fileNameBytes);

            String fileName = new String(fileNameBytes, StandardCharsets.UTF_8);
            byte[] fileContent = new byte[payload.remaining()];
            payload.get(fileContent);

            String filePath = String.format("/Users/quannguyen/Desktop/softwareArchitecture/%s", fileName);
            File imageFile = new File(filePath);
            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(fileContent);
            fos.close();

            logger.info("Finished Storing document");
        }
    }
}
