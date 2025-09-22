package staffs.staffleave.ui.Events;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import staffs.staffleave.application.EventStoreQueryHandler;

import java.util.Optional;

@RestController
@RequestMapping("/eventStore")
@RequiredArgsConstructor
public class EventStoreController {

    private final EventStoreQueryHandler queryHandler;

    /**
     * GET /leaveRequests
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<?> getAllEvents() {
        return queryHandler.findAllEvents();
    }

    /**
     * GET /leaveRequests/{Id}
     */
    @GetMapping("/id/{Id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<?> getEventByID(@PathVariable Long Id) {
        return queryHandler.findEventById(Id);
    }


}
