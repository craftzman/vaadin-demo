package org.zot.chai.vaadindemo.view;

import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Command;
import jakarta.annotation.security.PermitAll;
import org.zot.chai.vaadindemo.service.ChatService;

import java.util.ArrayList;

@Route("")
@PermitAll
public class ChatView extends VerticalLayout {

    ChatView(ChatService service) {
        var messageList = new MessageList();
        var textInput = new MessageInput();

        setSizeFull();
        add(messageList, textInput);
        expand(messageList);
        textInput.setWidthFull();

        service.join().subscribe(message -> {
            var ml = new ArrayList<>(messageList.getItems());
            ml.add(new MessageListItem(message.text(), message.time(), message.userName()));
            getUI().ifPresent(ui -> ui.access((Command) () -> messageList.setItems(ml)));
        });

        textInput.addSubmitListener(event -> service.add(event.getValue()));
    }
}
