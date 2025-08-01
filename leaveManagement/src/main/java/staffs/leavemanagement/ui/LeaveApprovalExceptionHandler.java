package staffs.leavemanagement.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import staffs.leavemanagement.domain.leaveApproval.LeaveApprovalDomainException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LeaveApprovalExceptionHandler {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(LeaveApprovalDomainException.class)
    public ResponseEntity<Object> handleLeaveApprovalException(LeaveApprovalDomainException ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request, "Internal Server Error");
    }

    private ResponseEntity<Object> buildErrorResponse(Exception ex, HttpStatus status, WebRequest request, String errorTitle) {
        LOG.error("Exception caught: {}", ex.getMessage(), ex);

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", errorTitle);
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, status);
    }
}
