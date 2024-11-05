package ChatApp.ConversationDomain.Controller;

import ChatApp.ConversationDomain.Dto.ConversationDTO;
import ChatApp.ConversationDomain.Request.ConversationCreateRequest;
import ChatApp.ConversationDomain.Service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequestMapping("api/conversation")
public class ConversationController {

    @Autowired
    ConversationService service;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public List<ConversationDTO> fetchUserMessage(@RequestParam("userId") long userId,
                                                  @RequestParam(value = "startDate", required = false) LocalDate startDate) {
        if (Objects.isNull(startDate)) {
            startDate = LocalDate.now();
            startDate.minusDays(3);
        }
        return this.service.fetchConversationByUserIdAfterDate(userId, startDate);
    }

    @RequestMapping(value = "/conversation/generate", method = RequestMethod.POST)
    public ConversationDTO generateConversation(@RequestBody ConversationCreateRequest request) {
        return this.service.create(request);

    }

    @RequestMapping(value = "/conversation/add/participant", method = RequestMethod.POST)
    public ResponseEntity addParticipant(@RequestParam("userId") long userId,
                                         @RequestParam("conversationId") long conversationId) {
        Long participantId = this.service.addConversationParticipant(userId, conversationId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "OK");
        response.put("userId", userId);
        response.put("conversationId", conversationId);
        response.put("participantId", participantId);
        return ResponseEntity.ok(response);
    }

}
