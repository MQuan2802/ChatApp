package ChatApp.MessageExchangeDomain.Handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SocketImageHandler extends BinaryWebSocketHandler {

    public static final Logger logger = LoggerFactory.getLogger(SocketImageHandler.class);

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
        System.out.println("New Binary ConversationDomain Received");
        byte[] imageData = message.getPayload().array();
        String filePath = "/Users/quannguyen/Desktop/softwareArchitecture/webSocketImageHandler.png";
        File imageFile = new File(filePath);
        FileOutputStream fos = new FileOutputStream(imageFile);
        fos.write(imageData);
        fos.close();

        logger.info("Finished Storing image");
    }
}
